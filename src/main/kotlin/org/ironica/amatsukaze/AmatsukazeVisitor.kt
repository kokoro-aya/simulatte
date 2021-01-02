package org.ironica.amatsukaze

import amatsukazeGrammarVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.round

enum class SpecialRetVal {
    Interr, Loop, Statements, Declaration, Branch, Empty, ReDef, NotDef
}

enum class Variability {
    VAR, CST
}

data class TerminatedReturn(override val message: String?): Throwable()

sealed class DataType
object _SOME: DataType() // TODO just a placeholder for complicated array type, should be fixed
object _INT: DataType()
object _DOUBLE: DataType()
object _CHARACTER: DataType()
object _STRING: DataType()
object _BOOL: DataType()
object _VOID: DataType()
object _CALL: DataType()
object _FUNCTION: DataType()
object _STRUCT: DataType()
object _ENUM: DataType()
object _PLAYER: DataType()
object _SPECIALIST: DataType()
data class _ARRAY(val type: DataType): DataType()

private fun DataType.typeEquals(other: DataType): Boolean {
    return if (this !is _ARRAY) this == other
    else other is _ARRAY
}

sealed class Proto {
    abstract val prototype: Proto?
}
sealed class Literal(): Proto() {
    abstract val variability: Variability
}
data class IntegerLiteral(override val variability: Variability, var content: Int, override val prototype: Prototype): Literal()
data class DoubleLiteral(override val variability: Variability, var content: Double, override val prototype: Prototype): Literal()
data class BooleanLiteral(override val variability: Variability, var content: Boolean, override val prototype: Prototype): Literal()
data class CharacterLiteral(override val variability: Variability, var content: Char, override val prototype: Prototype): Literal()
data class StringLiteral(override val variability: Variability, var content: String, override val prototype: Prototype): Literal()

data class ReturnedLiteral(val content: Any): Throwable()
data class StructLiteral(override val variability: Variability, var body: MutableMap<String, Literal>, override var prototype: Prototype): Literal()
data class FunctionLiteral(override val variability: Variability, var head: FuncHead, var body: ParseTree, var closureScope: List<Scope>, override var prototype: Prototype): Literal()
data class ArrayLiteral(override val variability: Variability, var subType: DataType, var content: MutableList<Literal> = mutableListOf(), override var prototype: Prototype): Literal()
data class Prototype (
    val members: MutableMap<String, Literal>,
    override var prototype: Proto? = null,
    var ctor: Literal? = null
): Proto()

data class PlayerLiteral(override val variability: Variability, var id: Int, override val prototype: Prototype): Literal()
data class SpecialistLiteral(override val variability: Variability, var id: Int, override val prototype: Prototype): Literal()

data class FuncHead(var name: String, val params: List<String>, val types: List<DataType>, val refs: List<Boolean>, val ret: DataType) {

    fun pseudoEquals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FuncHead

        if (name != other.name) return false
        if (refs != other.refs) return false
        if (types != other.types) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + types.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FuncHead

        if (name != other.name) return false
        if (params != other.params) return false
        if (types != other.types) return false
        if (refs != other.refs) return false
        if (ret != other.ret) return false

        return true
    }
}

typealias Scope = MutableMap<String, Literal>

class AmatsukazeVisitor(private val manager: AmatsukazeManager): amatsukazeGrammarVisitor<Any> {

    private val providedFunctions = mapOf(
        "moveForward" to {e: Int -> manager.moveForward(e)},
        "turnLeft" to {e: Int -> manager.turnLeft(e)},
        "toggleSwitch" to {e: Int -> manager.toggleSwitch(e)},
        "collectGem" to {e: Int -> manager.collectGem(e)},
        "takeBeeper" to {e: Int -> manager.takeBeeper(e)},
        "dropBeeper" to {e: Int -> manager.dropBeeper(e)},
        "turnLockUp" to {e: Int -> manager.turnLockUp(e)},
        "turnLockDown" to {e: Int -> manager.turnLockDown(e)}
    )

    private val providedProperties = mapOf(
        "isOnGem" to {e: Int -> manager.isOnGem(e)},
        "isOnOpenedSwitch" to {e: Int -> manager.isOnOpenedSwitch(e)},
        "isOnClosedSwitch" to {e: Int -> manager.isOnClosedSwitch(e)},
        "isOnBeeper" to {e: Int -> manager.isOnBeeper(e)},
        "isAtHome" to {e: Int -> manager.isAtHome(e)},
        "isInDesert" to {e: Int -> manager.isInDesert(e)},
        "isInForest" to {e: Int -> manager.isInForest(e)},
        "isOnPortal" to {e: Int -> manager.isOnPortal(e)},
        "isBlocked" to {e: Int -> manager.isBlocked(e)},
        "isBlockedLeft" to {e: Int -> manager.isBlockedLeft(e)},
        "isBlockedRight" to {e: Int -> manager.isBlockedRight(e)}
    )

    private var _break = false
    private var _continue = false

    private val typeTable = mutableMapOf(
        "Integer" to Prototype(mutableMapOf()), "Double" to Prototype(mutableMapOf()), "Bool" to Prototype(mutableMapOf()),
        "Character" to Prototype(mutableMapOf()), "String" to Prototype(mutableMapOf()), "Struct" to Prototype(mutableMapOf()),
        "Function" to Prototype(mutableMapOf()), "Array" to Prototype(mutableMapOf()),
        "Player" to Prototype(mutableMapOf()), "Specialist" to Prototype(mutableMapOf())
    )

    private var inFunc = mutableListOf<Boolean>()
    private var currentFunc = 0
    private var externalDepth = 0
    private var internalDepth = 0
    private val funcEntriesDepth = mutableListOf<Int>()

    private val functionTable = mutableMapOf<FuncHead, Pair<ParseTree, Int>>()

    private val closures = mutableListOf<Scope>()

    private val anonymousFuncIndices = mutableListOf(0)

    private fun assignAnonymousFuncIndex(level: Int): Int {
        return anonymousFuncIndices[level]++
    }

    var playerList = manager.playground.players.toMutableList()

    /*
        default: print, typeof, isSame(a, b)
        Object -> toString
        String -> size, replace(), subStr(), toCharArray()
        Boolean -> not(), and(), or(), xor()
        Character -> isDigit, isAlpha, isUpper, isLower, isEmpty, toInt()
        Struct -> varDump, count, erase(), clear()
        Array -> size, clear(), swap(), first, last, pushBack(), pushFront(), popBack(), popFront()
              -> (hof) foreach(), map(), filter(), all(), any() (not supported)
        Some functions can be done by adding extensions on prototype chain.
         */

    private val variableTable = mutableMapOf<String, Literal>()
    private val variableDepth = mutableMapOf<String, Int>()
    private val internalVariables = mutableListOf<Scope>(mutableMapOf())
    private val internalVariableDepth = mutableMapOf<String, Int>()

    private fun queryVariableTable(of: String): Pair<Literal?, Int> {
        for (i in closures.indices.reversed()) {
            if (closures[i].containsKey(of)) {
                return closures[i][of] to i
            }
        }
        for (i in internalVariables.indices.reversed()) {
            if (internalVariables[i].containsKey(of)) {
                return internalVariables[i][of] to i
            }
        }
        if (variableTable.containsKey(of)) {
            return variableTable[of] to -1
        }
        return null to -2
    }

    private fun assignVariable(left: Literal, right: Any): Literal {
        when (left) {
            is IntegerLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is Int -> left.content = right
                    is IntegerLiteral -> left.content = right.content
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is DoubleLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is Double -> left.content = right
                    is DoubleLiteral -> left.content = right.content
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is BooleanLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is Boolean -> left.content = right
                    is BooleanLiteral -> left.content = right.content
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is CharacterLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is Pair<*, *>  -> {
                        if (right.second is _CHARACTER)
                            left.content = right.first as Char
                    }
                    is CharacterLiteral -> left.content = right.content
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is StringLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is Pair<*, *>  -> {
                        if (right.second is _STRING)
                            left.content = right.first as String
                    }
                    is StringLiteral -> left.content = right.content
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is StructLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is StructLiteral -> left.body = right.body
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is FunctionLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is FunctionLiteral -> {
                        left.head = right.head
                        left.body = right.body
                        left.closureScope = right.closureScope
                    }
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is ArrayLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is ArrayLiteral -> {
                        if (left.subType.typeEquals(right.subType)) {
                            left.content = right.content
                        }
                        else throw Exception("Array type mismatch on lhs and rhs")
                    }
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }

            is PlayerLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is PlayerLiteral -> left.id = right.id
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
            is SpecialistLiteral -> {
                if (left.variability == Variability.CST) throw Exception("Attempt modify constant")
                when (right) {
                    is SpecialistLiteral -> left.id = right.id
                    else -> throw Exception("Type of lhs and rhs of assignment don't match")
                }
            }
        }
        return left
    }

    private fun checkTypeEquality(right: Literal, type: DataType): DataType? {
        return if (right is IntegerLiteral && type is _INT
                || right is DoubleLiteral && type is _DOUBLE
                || right is BooleanLiteral && type is _BOOL
                || right is CharacterLiteral && type is _CHARACTER
                || right is StringLiteral && type is _STRING
                || right is FunctionLiteral && type is _FUNCTION
                || right is StructLiteral && type is _STRUCT
                || right is ArrayLiteral && type is _ARRAY

                || right is PlayerLiteral && type is _PLAYER
                || right is SpecialistLiteral && type is _SPECIALIST) type else null
    }

    private fun checkTypeOfLiteralsIdentical(left: Literal, right: Literal): Boolean {
        return left is IntegerLiteral && right is IntegerLiteral
                || left is DoubleLiteral && right is DoubleLiteral
                || left is BooleanLiteral && right is BooleanLiteral
                || left is CharacterLiteral && right is CharacterLiteral
                || left is StringLiteral && right is StringLiteral
                || left is FunctionLiteral && right is FunctionLiteral
                || left is StructLiteral && right is StructLiteral
                || left is ArrayLiteral && right is ArrayLiteral

                || left is PlayerLiteral && right is PlayerLiteral
                || left is SpecialistLiteral && right is SpecialistLiteral
    }

    private fun checkArrayType(type: DataType, right: ArrayLiteral) {
        when (type) {
            is _INT -> if (right.content.none { it !is IntegerLiteral }) return
            is _DOUBLE -> if (right.content.none { it !is DoubleLiteral }) return
            is _BOOL -> if (right.content.none { it !is BooleanLiteral }) return
            is _CHARACTER -> if (right.content.none { it !is CharacterLiteral }) return
            is _STRING -> if (right.content.none { it !is StringLiteral }) return
            is _STRUCT -> if (right.content.none { it !is StructLiteral }) return
            is _ARRAY -> if (right.content.none { it !is ArrayLiteral }) return
            is _FUNCTION -> if (right.content.none { it !is FunctionLiteral }) return

            is _PLAYER -> if (right.content.none { it !is PlayerLiteral }) return
            is _SPECIALIST -> if (right.content.none { it !is SpecialistLiteral }) return
        }
        throw Exception("Array type check failed")
    }

    private fun inferArrayType(right: ArrayLiteral): DataType {
        if (right.content.isEmpty()) throw Exception("Cannot infer type on empty array")
        if (right.content.all { it is IntegerLiteral }) return _INT
        if (right.content.all { it is DoubleLiteral }) return _DOUBLE
        if (right.content.all { it is BooleanLiteral }) return _BOOL
        if (right.content.all { it is CharacterLiteral }) return _CHARACTER
        if (right.content.all { it is StringLiteral }) return _STRING
        if (right.content.all { it is StructLiteral }) return _STRUCT
        if (right.content.all { it is FunctionLiteral }) return _FUNCTION
        if (right.content.all { it is ArrayLiteral }) return _ARRAY(_SOME)

        if (right.content.all { it is PlayerLiteral }) return _PLAYER
        if (right.content.all { it is SpecialistLiteral }) return _SPECIALIST
        throw Exception("Array type inference failed")
    }

    private fun inferPrimaryToArrayType(right: Array<Literal>): DataType {
        if (right.isEmpty()) return _SOME
        if (right.all { it is IntegerLiteral }) return _INT
        if (right.all { it is DoubleLiteral }) return _DOUBLE
        if (right.all { it is BooleanLiteral }) return _BOOL
        if (right.all { it is CharacterLiteral }) return _CHARACTER
        if (right.all { it is StringLiteral }) return _STRING
        if (right.all { it is StructLiteral }) return _STRUCT
        if (right.all { it is FunctionLiteral }) return _FUNCTION
        if (right.all { it is ArrayLiteral }) return _ARRAY(_SOME) // TODO Fix this

        if (right.all { it is PlayerLiteral }) return _PLAYER
        if (right.all { it is SpecialistLiteral }) return _SPECIALIST
        throw Exception("Array type inference failed")
    }

    private fun declareConstantOrVariable(type: DataType?, right: Literal, constant: Boolean): Literal? {
        if (type != null) {
            val checkedType = checkTypeEquality(type = type, right = right) ?: throw Exception("Declaration type check failed")
            return when (checkedType) {
                _INT -> {
                    if (constant) IntegerLiteral(Variability.CST, (right as IntegerLiteral).content, right.prototype)
                    else IntegerLiteral(Variability.VAR, (right as IntegerLiteral).content, right.prototype)
                }
                _DOUBLE -> {
                    if (constant) DoubleLiteral(Variability.CST, (right as DoubleLiteral).content, right.prototype)
                    else DoubleLiteral(Variability.VAR, (right as DoubleLiteral).content, right.prototype)
                }
                _BOOL -> {
                    if (constant) BooleanLiteral(Variability.CST, (right as BooleanLiteral).content, right.prototype)
                    else BooleanLiteral(Variability.VAR, (right as BooleanLiteral).content, right.prototype)
                }
                _CHARACTER -> {
                    if (constant) CharacterLiteral(Variability.CST, (right as CharacterLiteral).content, right.prototype)
                    else CharacterLiteral(Variability.VAR, (right as CharacterLiteral).content, right.prototype)
                }
                _STRING -> {
                    if (constant) StringLiteral(Variability.CST, (right as StringLiteral).content, right.prototype)
                    else StringLiteral(Variability.VAR, (right as StringLiteral).content, right.prototype)
                }
                _STRUCT -> {
                    if (constant) StructLiteral(Variability.CST, (right as StructLiteral).body, right.prototype)
                    else StructLiteral(Variability.VAR, (right as StructLiteral).body, right.prototype)
                }
                _FUNCTION -> {
                    if (constant) FunctionLiteral(Variability.CST, (right as FunctionLiteral).head, right.body, right.closureScope, right.prototype)
                    else FunctionLiteral(Variability.VAR, (right as FunctionLiteral).head, right.body, right.closureScope, right.prototype)
                }
                is _ARRAY -> {
                    checkArrayType(checkedType.type, (right as ArrayLiteral))
                    if (constant) ArrayLiteral(Variability.CST, checkedType.type, (right as ArrayLiteral).content, right.prototype)
                    else ArrayLiteral(Variability.VAR, checkedType.type, (right as ArrayLiteral).content, right.prototype)
                }

                _PLAYER -> {
                    if (constant) PlayerLiteral(Variability.CST, (right as PlayerLiteral).id, right.prototype)
                    else PlayerLiteral(Variability.VAR, (right as PlayerLiteral).id, right.prototype)
                }
                _SPECIALIST -> {
                    if (constant) SpecialistLiteral(Variability.CST, (right as PlayerLiteral).id, right.prototype)
                    else SpecialistLiteral(Variability.VAR, (right as PlayerLiteral).id, right.prototype)
                }
                else -> throw Exception("This could not happen")
            }

        } else {
            return when (right) {
                is IntegerLiteral -> {
                    if (constant) IntegerLiteral(Variability.CST, right.content, right.prototype)
                    else IntegerLiteral(Variability.VAR, right.content, right.prototype)
                }
                is DoubleLiteral -> {
                    if (constant) DoubleLiteral(Variability.CST, right.content, right.prototype)
                    else DoubleLiteral(Variability.VAR, right.content, right.prototype)
                }
                is BooleanLiteral -> {
                    if (constant) BooleanLiteral(Variability.CST, right.content, right.prototype)
                    else BooleanLiteral(Variability.VAR, right.content, right.prototype)
                }
                is CharacterLiteral -> {
                    if (constant) CharacterLiteral(Variability.CST, right.content, right.prototype)
                    else CharacterLiteral(Variability.VAR, right.content, right.prototype)
                }
                is StringLiteral -> {
                    if (constant) StringLiteral(Variability.CST, right.content, right.prototype)
                    else StringLiteral(Variability.VAR, right.content, right.prototype)
                }
                is StructLiteral -> {
                    if (constant) StructLiteral(Variability.CST, right.body, right.prototype)
                    else StructLiteral(Variability.VAR, right.body, right.prototype)
                }
                is FunctionLiteral -> {
                    if (constant) FunctionLiteral(Variability.CST, right.head, right.body, right.closureScope, right.prototype)
                    else FunctionLiteral(Variability.VAR, right.head, right.body, right.closureScope, right.prototype)
                }
                is ArrayLiteral -> {
                    val inferredType = inferArrayType(right)
                    if (constant) ArrayLiteral(Variability.CST, inferredType, right.content, right.prototype)
                    else ArrayLiteral(Variability.VAR, inferredType, right.content, right.prototype)
                }

                is PlayerLiteral -> {
                    if (constant) PlayerLiteral(Variability.CST, right.id, right.prototype)
                    else PlayerLiteral(Variability.VAR, right.id, right.prototype)
                }
                is SpecialistLiteral -> {
                    if (constant) SpecialistLiteral(Variability.CST, right.id, right.prototype)
                    else SpecialistLiteral(Variability.VAR, right.id, right.prototype)
                }
            }
        }
    }

    private fun literalsToDataType(l: Array<Literal>): List<DataType> {
        return l.map { when (it) {
            is IntegerLiteral -> _INT
            is DoubleLiteral -> _DOUBLE
            is CharacterLiteral -> _CHARACTER
            is StringLiteral -> _STRING
            is BooleanLiteral -> _BOOL
            is FunctionLiteral -> _FUNCTION
            is StructLiteral -> _STRUCT
            is ArrayLiteral -> _ARRAY(inferArrayType(it))
            is PlayerLiteral -> _PLAYER
            is SpecialistLiteral -> _SPECIALIST
        } }
    }

    private fun declareNewPlaygroundObject(type: String): Literal {
        when (type) {
            "PLAYER" -> {
                if (playerList.any { it !is Specialist }) {
                    val pp = playerList.filter { it !is Specialist }[0]
                    val p = PlayerLiteral(Variability.CST, pp.id, typeTable["Player"]!!)
                    playerList.remove(pp)
                    return p
                } else {
                    throw Exception("Declared a player but there is no available slot")
                }
            }
            "SPECIALIST" -> {
                if (playerList.filterIsInstance<Specialist>().isNotEmpty()) {
                    val pp = playerList.filterIsInstance<Specialist>()[0]
                    val p = SpecialistLiteral(Variability.CST, pp.id, typeTable["Specialist"]!!)
                    playerList.remove(pp)
                    return p
                } else {
                    throw Exception("Declared a player but there is no available slot")
                }
            }
            else -> throw Exception("Unsupported playground object type declaration")
        }
    }

    private fun internal_typeOf(l: Literal): StringLiteral {
        val type = when (l) {
            is IntegerLiteral -> "Integer"
            is DoubleLiteral -> "Double"
            is StringLiteral -> "String"
            is CharacterLiteral -> "Character"
            is BooleanLiteral -> "Bool"
            is FunctionLiteral -> "Function"
            is StructLiteral -> "Struct"
            is ArrayLiteral -> "Array"
            is PlayerLiteral -> "Player"
            is SpecialistLiteral -> "Specialist"
            // TODO test array internal content
        }
        return StringLiteral(Variability.CST, type, typeTable["String"]!!)
    }

    private fun internal_isSame(l: Literal, o: Literal): BooleanLiteral {
        return BooleanLiteral(Variability.CST, internal_typeOf(l) == internal_typeOf(o), typeTable["Bool"]!!)
    }

    override fun visit(tree: ParseTree?): Any {
        return tree?.accept(this) ?: Exception("Encountered error while visiting AST")
//        try {
//            return tree?.accept(this)!!
//        } catch (e: Exception) {
//            throw Exception("Encountered error while visiting AST:\n    $e")
//        }
    }

    override fun visitChildren(node: RuleNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitTerminal(node: TerminalNode?): Any {
        if (node?.text != null) return node.text
        throw Exception("Encountered error while visiting terminal node")
    }

    override fun visitErrorNode(node: ErrorNode?): Any {
        throw Exception("Return from ErrorNode")
    }

    override fun visitTop_level(ctx: amatsukazeGrammarParser.Top_levelContext?): Any {
        try {
            return visit(ctx?.statements())
        } catch (e: Throwable) {
            if (e is TerminatedReturn) {
                return e.message!!
            } else {
                throw e
            }
        }
    }

    override fun visitLiteral(ctx: amatsukazeGrammarParser.LiteralContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitNumeric_literal(ctx: amatsukazeGrammarParser.Numeric_literalContext?): Any {
        return when {
            ctx?.childCount == 1 -> visit(ctx.getChild(0))
            ctx?.integer_literal()?.isEmpty!! -> - (visit(ctx.getChild(1)) as Double)
            else -> - (visit(ctx.getChild(1)) as Int)
        }
    }

    override fun visitBoolean_literal(ctx: amatsukazeGrammarParser.Boolean_literalContext?): Any {
        return when (ctx?.text) {
            "true" -> true
            "false" -> false
            else -> throw Exception("Cannot parse literal to boolean type")
        }
    }

    override fun visitChar_sequence_literal(ctx: amatsukazeGrammarParser.Char_sequence_literalContext?): Any {
        return if (ctx?.STRING_LITERAL() == null)
            ctx?.CHARACTER_LITERAL()?.text!![0] to _CHARACTER
        else {
            val str = ctx.STRING_LITERAL()?.text!!
            return str.substring(1, str.length - 1) to _STRING
        }
    }

    override fun visitNil_literal(ctx: amatsukazeGrammarParser.Nil_literalContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitInteger_literal(ctx: amatsukazeGrammarParser.Integer_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL())}".toInt()
    }

    override fun visitDouble_literal(ctx: amatsukazeGrammarParser.Double_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL(0))}.${visit(ctx?.DECIMAL_LITERAL(1))}".toDouble()
    }

    override fun visitAssignmentExpr(ctx: amatsukazeGrammarParser.AssignmentExprContext?): Any {
        return visit(ctx?.assignment_expression())
    }

    // TODO need to rewrite this, add comparable to players
    override fun visitBoolComparativeExpr(ctx: amatsukazeGrammarParser.BoolComparativeExprContext?): Any {
        val left = visit(ctx?.expression(0))
        val right = visit(ctx?.expression(1))
        if (left is BooleanLiteral && right is BooleanLiteral) {
            left.content = if (ctx?.op?.type == amatsukazeGrammarParser.EQ) left.content == right.content else left.content != right.content
            return left
        }
        if ((left is IntegerLiteral || left is DoubleLiteral) && right is BooleanLiteral) {
            when (left) {
                is IntegerLiteral -> right.content = if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0) == right.content else (left.content != 0) != right
                is DoubleLiteral -> right.content = if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0.0) == right.content else (left.content != 0.0) != right
            }
            return right // bad idea to modify right or left
        }
        if (left is BooleanLiteral && (right is IntegerLiteral || right is DoubleLiteral)) {
            when (right) {
                is IntegerLiteral -> left.content = if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (right.content != 0) == left.content else (right.content != 0) != left
                is DoubleLiteral -> left.content = if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (right.content != 0.0) == left.content else (right.content != 0.0) != left
            }
            return left
        }
        if ((left is IntegerLiteral || left is DoubleLiteral) && (right is IntegerLiteral || right is DoubleLiteral)) {
            when (left) {
                is IntegerLiteral -> when (right) {
                    is IntegerLiteral -> return BooleanLiteral(Variability.CST, if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0) == (right.content != 0) else (left.content != 0) != (right.content != 0), typeTable["Bool"]!!)
                    is DoubleLiteral -> return BooleanLiteral(Variability.CST, if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0) == (right.content != 0.0) else (left.content != 0) != (right.content != 0.0), typeTable["Bool"]!!)
                }
                is DoubleLiteral -> when (right) {
                    is IntegerLiteral -> return BooleanLiteral(Variability.CST, if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0.0) == (right.content != 0) else (left.content != 0.0) != (right.content != 0), typeTable["Bool"]!!)
                    is DoubleLiteral -> return BooleanLiteral(Variability.CST, if (ctx?.op?.type == amatsukazeGrammarParser.EQ) (left.content != 0.0) == (right.content != 0.0) else (left.content != 0.0) != (right.content != 0.0), typeTable["Bool"]!!)
                }
            } // Looks like a mess
        }
        TODO("Equality test for function, struct and array")
        throw Exception("Boolean comparative expression on unsupported type")
    }

    override fun visitStruct_call(ctx: amatsukazeGrammarParser.Struct_callContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAriAssignmentExpr(ctx: amatsukazeGrammarParser.AriAssignmentExprContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) throw Exception("Illegal variable name")
        var right = visit(ctx?.expression())
        if (right is BooleanLiteral) {
            val newright = if (right.content) IntegerLiteral(Variability.CST, 1, typeTable["Integer"]!!) else IntegerLiteral(Variability.CST, 0, typeTable["Integer"]!!)
            right = newright
        }
        val lvar = queryVariableTable(left)
        if (lvar.first == null) throw Exception("Variable not defined!")
        if (!checkTypeOfLiteralsIdentical(lvar.first as Literal, right as Literal)) throw Exception("Type mismatch")
        val new: Literal = when (val old = lvar.first) {
            is IntegerLiteral -> when (ctx?.op?.type) {
                amatsukazeGrammarParser.ADDEQ -> IntegerLiteral(Variability.CST, old.content + (right as IntegerLiteral).content, typeTable["Integer"]!!)
                amatsukazeGrammarParser.SUBEQ -> IntegerLiteral(Variability.CST, old.content - (right as IntegerLiteral).content, typeTable["Integer"]!!)
                amatsukazeGrammarParser.MULEQ -> IntegerLiteral(Variability.CST, old.content * (right as IntegerLiteral).content, typeTable["Integer"]!!)
                amatsukazeGrammarParser.DIVEQ -> IntegerLiteral(Variability.CST, old.content / (right as IntegerLiteral).content, typeTable["Integer"]!!)
                else -> IntegerLiteral(Variability.CST, old.content % (right as IntegerLiteral).content, typeTable["Integer"]!!)
            }
            is DoubleLiteral -> when (ctx?.op?.type) {
                amatsukazeGrammarParser.ADDEQ -> DoubleLiteral(Variability.CST, old.content + (right as DoubleLiteral).content, typeTable["Double"]!!)
                amatsukazeGrammarParser.SUBEQ -> DoubleLiteral(Variability.CST, old.content - (right as DoubleLiteral).content, typeTable["Double"]!!)
                amatsukazeGrammarParser.MULEQ -> DoubleLiteral(Variability.CST, old.content * (right as DoubleLiteral).content, typeTable["Double"]!!)
                amatsukazeGrammarParser.DIVEQ -> DoubleLiteral(Variability.CST, old.content / (right as DoubleLiteral).content, typeTable["Double"]!!)
                else -> DoubleLiteral(Variability.CST, old.content % (right as IntegerLiteral).content, typeTable["Double"]!!)
            }
            is StringLiteral -> {
                if (ctx?.op?.type == amatsukazeGrammarParser.ADDEQ)
                    StringLiteral(Variability.CST, old.content + (right as StringLiteral).content, typeTable["String"]!!)
                else throw Exception("Arithmetic assignment: unsupported operation")
            }
            else -> throw Exception("Arithmetic assignment: unsupported type")
        }
        return assignVariable(lvar.first!!, new)
    }

    override fun visitExponentExpr(ctx: amatsukazeGrammarParser.ExponentExprContext?): Any {
        val left = visit(ctx?.expression(0))
        val right = visit(ctx?.expression(1))
        assert((left is IntegerLiteral || left is DoubleLiteral) && (right is DoubleLiteral || right is IntegerLiteral))
        var leftctn = 0.0
        var rightctn = 0.0
        if (left is IntegerLiteral) leftctn = left.content.toDouble()
        if (left is DoubleLiteral) leftctn = left.content
        if (right is IntegerLiteral) rightctn = right.content.toDouble()
        if (right is DoubleLiteral) rightctn = right.content
        return DoubleLiteral(Variability.CST, leftctn.pow(rightctn), typeTable["Double"]!!)
    }

    override fun visitAddSubExpr(ctx: amatsukazeGrammarParser.AddSubExprContext?): Any {
        val left = visit(ctx?.expression(0)) as Literal
        val right = visit(ctx?.expression(1)) as Literal
        if ((left is BooleanLiteral || left is DoubleLiteral || left is IntegerLiteral) && (right is BooleanLiteral || right is DoubleLiteral || right is IntegerLiteral)) {
            var lcontentInt = 0
            var lcontentDbl = 0.0
            var rcontentInt = 0
            var rcontentDbl = 0.0
            var dblFlag = false
            if (left is BooleanLiteral)
                lcontentInt = if (left.content) 1 else 0
            if (right is BooleanLiteral)
                rcontentInt = if (right.content) 1 else 0
            if (left is IntegerLiteral)
                lcontentInt = left.content
            if (right is IntegerLiteral)
                rcontentInt = right.content
            if (left is DoubleLiteral) {
                lcontentDbl = left.content
                dblFlag = true
            }
            if (right is DoubleLiteral) {
                rcontentDbl = right.content
                dblFlag = true
            }
            if (dblFlag) {
                val ans = when (ctx?.op?.type) {
                    amatsukazeGrammarParser.ADD -> lcontentDbl + rcontentDbl + lcontentInt + rcontentInt
                    else -> lcontentDbl + lcontentInt  - rcontentDbl - rcontentInt
                }
                return DoubleLiteral(Variability.CST, ans, typeTable["Double"]!!)
            } else {
                val ans = when (ctx?.op?.type) {
                    amatsukazeGrammarParser.ADD -> lcontentInt + rcontentInt
                    else -> lcontentInt  - rcontentInt
                }
                return IntegerLiteral(Variability.CST, ans, typeTable["Integer"]!!)
            }
        }
        if ((left is StringLiteral || left is CharacterLiteral) && (right is StringLiteral || right is CharacterLiteral)) {
            if (ctx?.op?.type == amatsukazeGrammarParser.ADD) {
                var leftctn = ""
                var rightctn = ""
                if (left is StringLiteral) leftctn = left.content
                if (left is CharacterLiteral) leftctn = left.content.toString()
                if (right is StringLiteral) rightctn = right.content
                if (right is CharacterLiteral) rightctn = right.content.toString()
                return StringLiteral(Variability.CST, leftctn + rightctn, typeTable["String"]!!)
            }
        }
        throw Exception("AddSub: on unsupported type")
    }

    private fun MutableList<Literal>.swap(i: Int, j: Int) {
        assert (i in this.indices && j in this.indices)
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

    private fun handleFunctionCall(
        func: amatsukazeGrammarParser.Function_call_expressionContext,
        vari: amatsukazeGrammarParser.Variable_expressionContext? = null
    ): Any {
        if (vari == null) {
            return visit(func)
        } else {
            try {
                val obj = visit(vari) as Literal
                val functionName = visit(func.function_name()) as String
                val clause =
                    if (func.childCount == 2) listOf() else visit(func.call_argument_clause()) as List<Pair<Literal, Boolean>>
                val funcArgu = clause.map { it.first }
                val refs = clause.map { it.second }
                when (obj) {
                    is PlayerLiteral -> {
                        if (funcArgu.isEmpty() && providedFunctions.containsKey(functionName)) {
                            providedFunctions.getValue(functionName)(obj.id)
                            if (manager.dead())
                                throw TerminatedReturn("GAMEOVER")
                            if (manager.win())
                                throw TerminatedReturn("WIN")
                            return SpecialRetVal.Empty
                        }
                        if (funcArgu.size == 1 && functionName == "changeColor") {
                            if (funcArgu[0] is StringLiteral) {
                                val color = when ((funcArgu[0] as StringLiteral).content.toLowerCase()) {
                                    "black" -> Color.BLACK
                                    "silver" -> Color.SILVER
                                    "grey" -> Color.GREY
                                    "white" -> Color.WHITE
                                    "red" -> Color.RED
                                    "orange" -> Color.ORANGE
                                    "gold" -> Color.GOLD
                                    "pink" -> Color.PINK
                                    "yellow" -> Color.YELLOW
                                    "beige" -> Color.BEIGE
                                    "brown" -> Color.BROWN
                                    "green" -> Color.GREEN
                                    "azure" -> Color.AZURE
                                    "cyan" -> Color.CYAN
                                    "aliceblue" -> Color.ALICEBLUE
                                    "purple" -> Color.PURPLE
                                    else -> throw Exception("Unsupported color")
                                }
                                manager.changeColor(obj.id, color)
                                return SpecialRetVal.Empty
                            } else {
                                throw Exception("Wrong argument type in color function.")
                            }
                        }
                    }
                    is SpecialistLiteral -> {
                        if (funcArgu.isEmpty() && providedFunctions.containsKey(functionName)) {
                            providedFunctions.getValue(functionName)(obj.id)
                            if (manager.dead())
                                throw TerminatedReturn("GAMEOVER")
                            if (manager.win())
                                throw TerminatedReturn("WIN")
                            return SpecialRetVal.Empty
                        }
                        if (funcArgu.size == 1 && functionName == "changeColor") {
                            if (funcArgu[0] is StringLiteral) {
                                val color = when ((funcArgu[0] as StringLiteral).content.toLowerCase()) {
                                    "black" -> Color.BLACK
                                    "silver" -> Color.SILVER
                                    "grey" -> Color.GREY
                                    "white" -> Color.WHITE
                                    "red" -> Color.RED
                                    "orange" -> Color.ORANGE
                                    "gold" -> Color.GOLD
                                    "pink" -> Color.PINK
                                    "yellow" -> Color.YELLOW
                                    "beige" -> Color.BEIGE
                                    "brown" -> Color.BROWN
                                    "green" -> Color.GREEN
                                    "azure" -> Color.AZURE
                                    "cyan" -> Color.CYAN
                                    "aliceblue" -> Color.ALICEBLUE
                                    "purple" -> Color.PURPLE
                                    else -> throw Exception("Unsupported color")
                                }
                                manager.changeColor(obj.id, color)
                                return SpecialRetVal.Empty
                            } else {
                                throw Exception("Wrong argument type in color function.")
                            }
                        }
                    }
                    is IntegerLiteral -> {
                        if (funcArgu.isEmpty() && functionName == "toDouble") {
                            return DoubleLiteral(Variability.CST, obj.content.toDouble(), typeTable["Double"]!!)
                        }
                        if (funcArgu.isEmpty() && functionName == "toString") {
                            return StringLiteral(Variability.CST, obj.content.toString(), typeTable["String"]!!)
                        }
                    }
                    is DoubleLiteral -> {
                        if (funcArgu.isEmpty() && functionName == "floor") {
                            return IntegerLiteral(Variability.CST, floor(obj.content).toInt(), typeTable["Integer"]!!)
                        }
                        if (funcArgu.isEmpty() && functionName == "ceil") {
                            return IntegerLiteral(Variability.CST, ceil(obj.content).toInt(), typeTable["Integer"]!!)
                        }
                        if (funcArgu.isEmpty() && functionName == "round") {
                            return IntegerLiteral(Variability.CST, round(obj.content).toInt(), typeTable["Integer"]!!)
                        }
                        if (funcArgu.isEmpty() && functionName == "toString") {
                            return StringLiteral(Variability.CST, obj.content.toString(), typeTable["String"]!!)
                        }
                    }
                    is StringLiteral -> {
                        if (funcArgu.size == 2 && functionName == "replace") {
                            val old = (funcArgu[0] as StringLiteral).content
                            val new = (funcArgu[1] as StringLiteral).content
                            return StringLiteral(Variability.CST, obj.content.replace(old, new), obj.prototype)
                        }
                        if (funcArgu.size == 2 && functionName == "subStr") {
                            val begin = (funcArgu[0] as IntegerLiteral).content
                            val end = (funcArgu[1] as IntegerLiteral).content
                            return StringLiteral(Variability.CST, obj.content.substring(begin, end), obj.prototype)
                        }
                        if (funcArgu.isEmpty() && functionName == "toCharArray") {
                            val content: MutableList<Literal> = obj.content.toCharArray()
                                .map { CharacterLiteral(Variability.CST, it, typeTable["Character"]!!) }.toMutableList()
                            return ArrayLiteral(Variability.CST, _CHARACTER, content, typeTable["Array"]!!)
                        }
                    }
                    is BooleanLiteral -> {
                        if (funcArgu.isEmpty() && functionName == "not")
                            return BooleanLiteral(Variability.CST, !obj.content, obj.prototype)
                        if (funcArgu.size == 1 && functionName == "and") {
                            val other = (funcArgu[0] as BooleanLiteral).content
                            return BooleanLiteral(Variability.CST, obj.content.and(other), obj.prototype)
                        }
                        if (funcArgu.size == 1 && functionName == "or") {
                            val other = (funcArgu[0] as BooleanLiteral).content
                            return BooleanLiteral(Variability.CST, obj.content.or(other), obj.prototype)
                        }
                        if (funcArgu.size == 1 && functionName == "xor") {
                            val other = (funcArgu[0] as BooleanLiteral).content
                            return BooleanLiteral(Variability.CST, obj.content.xor(other), obj.prototype)
                        }
                    }
                    is CharacterLiteral -> {
                        if (funcArgu.isEmpty() && functionName == "toInt")
                            return IntegerLiteral(Variability.CST, obj.content.toInt(), typeTable["Integer"]!!)
                    }
                    is ArrayLiteral -> {
                        if (funcArgu.isEmpty() && functionName == "clear") {
                            obj.content.clear()
                            return obj
                        }
                        if (funcArgu.size == 2 && functionName == "swap") {
                            val pos1 = (funcArgu[0] as IntegerLiteral).content
                            val pos2 = (funcArgu[1] as IntegerLiteral).content
                            obj.content.swap(pos1, pos2)
                            return obj
                        }
                        if (funcArgu.size == 1 && functionName == "pushBack") {
                            val elem = funcArgu[0]
                            val type = checkTypeEquality(elem, obj.subType)
                            if (type != null) {
                                obj.content.add(elem)
                                return obj
                            } else throw Exception("Attempt to add incoherent type object into array")
                        }
                        if (funcArgu.size == 1 && functionName == "pushFront") {
                            val elem = funcArgu[0]
                            val type = checkTypeEquality(elem, obj.subType)
                            if (type != null) {
                                obj.content.add(0, elem)
                                return obj
                            } else throw Exception("Attempt to add incoherent type object into array")
                        }
                        if (funcArgu.isEmpty() && functionName == "popBack") {
                            val ret = obj.content.last()
                            obj.content.removeAt(obj.content.lastIndex)
                            return ret
                        }
                        if (funcArgu.isEmpty() && functionName == "popFront") {
                            val ret = obj.content.last()
                            obj.content.removeAt(0)
                            return ret
                        }
                        if (funcArgu.size == 2 && functionName == "insert") {
                            val elem = funcArgu[0]
                            val pos = (funcArgu[1] as IntegerLiteral).content
                            obj.content.add(pos, elem)
                            return obj
                        }
                        if (funcArgu.size == 1 && functionName == "drop") {
                            val pos = (funcArgu[0] as IntegerLiteral).content
                            obj.content.removeAt(pos)
                            return obj
                        }
                        if (funcArgu.size == 1 && functionName == "at") {
                            val pos = (funcArgu[0] as IntegerLiteral).content
                            return obj.content[pos]
                        }
                    }
                    else -> throw Exception("Not implemented methods for object")
                }
                throw Exception("Unsupported methods for object")
            } catch (e: Exception) {
                throw Exception("Something went wrong while passing method call: \n    ${e.message}")
            }
        }
    }

    override fun visitFunction_call(ctx: amatsukazeGrammarParser.Function_callContext?): Any {
        return handleFunctionCall(ctx?.function_call_expression()!!)
    }

    override fun visitMemberExpr(ctx: amatsukazeGrammarParser.MemberExprContext?): Any {
        return visit(ctx?.member_expression())
    }

    override fun visitAriComparativeExpr(ctx: amatsukazeGrammarParser.AriComparativeExprContext?): Any {
        var trueLeft: Literal
        var trueRight: Literal
        val left = visit(ctx?.expression(0))
        val right = visit(ctx?.expression(1))
        trueLeft = if (left is String) queryVariableTable(left).first ?: throw Exception("Variable $left not found in ariComparativeExpr")
        else left as Literal
        trueRight = if (right is String) queryVariableTable(right).first ?: throw Exception("Variable $left not found in ariComparativeExpr")
        else right as Literal
        if ((trueLeft is IntegerLiteral || trueLeft is DoubleLiteral) && (trueRight is IntegerLiteral || trueRight is DoubleLiteral)) {
            var lvalue = 0.0
            var rvalue = 0.0
            if (trueLeft is IntegerLiteral) lvalue = trueLeft.content.toDouble()
            if (trueLeft is DoubleLiteral) lvalue = trueLeft.content
            if (trueRight is IntegerLiteral) rvalue = trueRight.content.toDouble()
            if (trueRight is DoubleLiteral) rvalue = trueRight.content
            val ret = when (ctx?.op?.type) {
                amatsukazeGrammarParser.GT -> lvalue > rvalue
                amatsukazeGrammarParser.LT -> lvalue < rvalue
                amatsukazeGrammarParser.GEQ -> lvalue >= rvalue
                else -> lvalue <= rvalue
            }
            return BooleanLiteral(Variability.CST, ret, typeTable["Bool"]!!)
        }
        throw Exception("Arithmetic comparable expression on unsupported type")
    }

    override fun visitLiteralValueExpr(ctx: amatsukazeGrammarParser.LiteralValueExprContext?): Any {
        return visit(ctx?.literal_expression())
    }

    override fun visitMulDivModExpr(ctx: amatsukazeGrammarParser.MulDivModExprContext?): Any {
        val left = visit(ctx?.expression(0)) as Literal
        val right = visit(ctx?.expression(1)) as Literal
        if ((left is BooleanLiteral || left is DoubleLiteral || left is IntegerLiteral) && (right is BooleanLiteral || right is DoubleLiteral || right is IntegerLiteral)) {
            var lcontentInt = 1
            var lcontentDbl = 1.0
            var rcontentInt = 1
            var rcontentDbl = 1.0
            var dblFlag = false
            if (left is BooleanLiteral)
                lcontentInt = if (left.content) 1 else 0
            if (right is BooleanLiteral)
                rcontentInt = if (right.content) 1 else 0
            if (left is IntegerLiteral)
                lcontentInt = left.content
            if (right is IntegerLiteral)
                rcontentInt = right.content
            if (left is DoubleLiteral) {
                lcontentDbl = left.content
                dblFlag = true
            }
            if (right is DoubleLiteral) {
                rcontentDbl = right.content
                dblFlag = true
            }
            if (dblFlag) {
                val ans = when (ctx?.op?.type) {
                    amatsukazeGrammarParser.MUL -> lcontentDbl * lcontentInt * rcontentDbl * rcontentInt
                    amatsukazeGrammarParser.DIV -> lcontentDbl * lcontentInt / rcontentDbl / rcontentInt
                    else -> (lcontentDbl * lcontentInt) % (rcontentDbl * rcontentInt)
                }
                return DoubleLiteral(Variability.CST, ans, typeTable["Double"]!!)
            } else {
                val ans = when (ctx?.op?.type) {
                    amatsukazeGrammarParser.MUL -> lcontentInt * rcontentInt
                    amatsukazeGrammarParser.DIV -> lcontentInt / rcontentInt
                    else -> lcontentInt % rcontentInt
                }
                return IntegerLiteral(Variability.CST, ans, typeTable["Double"]!!)
            }
        }
        throw Exception("MulDivMod: on unsupported type")
    }

    override fun visitVariableExpr(ctx: amatsukazeGrammarParser.VariableExprContext?): Any {
        return visit(ctx?.variable_expression())
    }

    override fun visitIsNestedCondition(ctx: amatsukazeGrammarParser.IsNestedConditionContext?): Any {
        val left = visit(ctx?.expression(0)) as Literal
        val right = visit(ctx?.expression(1)) as Literal
        assert((left is BooleanLiteral || left is IntegerLiteral || left is DoubleLiteral) && (right is BooleanLiteral || right is IntegerLiteral || right is BooleanLiteral))
        var leftctn = false
        var rightctn = false
        if (left is BooleanLiteral) leftctn = left.content
        if (right is BooleanLiteral) rightctn = right.content
        if (left is IntegerLiteral) leftctn = left.content != 0
        if (right is IntegerLiteral) rightctn = right.content != 0
        if (left is DoubleLiteral) leftctn = left.content != 0.0
        if (right is DoubleLiteral) rightctn = right.content != 0.0
        return BooleanLiteral(Variability.CST, if (ctx?.op?.type == amatsukazeGrammarParser.AND) leftctn && rightctn else leftctn || rightctn, typeTable["Bool"]!!)
    }

    override fun visitExprFuncDeclExpr(ctx: amatsukazeGrammarParser.ExprFuncDeclExprContext?): Any {
        return visit(ctx?.expressional_function_declaration())
    }

    override fun visitIsNegativeCondition(ctx: amatsukazeGrammarParser.IsNegativeConditionContext?): Any {
        return BooleanLiteral(Variability.CST, !(visit(ctx?.getChild(1)) as BooleanLiteral).content, typeTable["Bool"]!!)
    }

    override fun visitRangeExpression(ctx: amatsukazeGrammarParser.RangeExpressionContext?): Any {
        try {
            val lowerLiteral = visit(ctx?.expression(0))
            val upperLiteral = visit(ctx?.expression(1))
            var upper: Int
            var lower: Int
            upper = if (upperLiteral is IntegerLiteral) {
                upperLiteral.content
            } else {
                (visit(ctx?.expression(1)) as IntegerLiteral).content
            }
            lower = if (lowerLiteral is IntegerLiteral) {
                lowerLiteral.content
            } else {
                (visit(ctx?.expression(0)) as IntegerLiteral).content
            }
            if (ctx?.op?.type == amatsukazeGrammarParser.UNTIL) {
                if (ctx.STEP() == null) {
                    return (lower until upper)
                }
                val steps = (visit(ctx.expression(2)) as IntegerLiteral).content
                return (lower until upper step steps)
            }
            if (ctx?.op?.type == amatsukazeGrammarParser.THROUGH) {
                if (ctx.STEP() == null) {
                    return (lower..upper)
                }
                val steps = (visit(ctx.expression(2)) as IntegerLiteral).content
                return (lower .. upper step steps)
            }
            if (ctx?.op?.type == amatsukazeGrammarParser.DUNTIL) {
                if (ctx.STEP() == null) {
                    return (lower downTo upper + 1)
                }
                val steps = (visit(ctx.expression(2)) as IntegerLiteral).content
                return (lower downTo upper + 1 step steps)
            }
            if (ctx?.op?.type == amatsukazeGrammarParser.DTHROUGH) {
                if (ctx.STEP() == null) {
                    return (lower downTo upper)
                }
                val steps = (visit(ctx.expression(2)) as IntegerLiteral).content
                return (lower downTo upper step steps)
            }
            throw Exception("Range expression on unsupported values")
        } catch (e: Exception) {
            throw Exception("Something goes wrong within range expression:\n    ${e.message}")
        }
    }

    override fun visitSubscriExpr(ctx: amatsukazeGrammarParser.SubscriExprContext?): Any {
        return visit(ctx?.subscript_expression())
    }

    override fun visitParenthesisExpr(ctx: amatsukazeGrammarParser.ParenthesisExprContext?): Any {
        return visit(ctx?.expression())
    }

    // TODO review logic of assignment regarding struct
    override fun visitAssignment_expression(ctx: amatsukazeGrammarParser.Assignment_expressionContext?): Any {
        val left = visit(ctx?.pattern())
        if (left is String) {
            if (!(left[0].isLetter() || left[0] == '_')) throw Exception("Illegal variable name")
            val right = visit(ctx?.expression())
            // println("right: $right")
            val lvar = queryVariableTable(left)
            if (lvar.first == null) throw Exception("Variable not defined!")
            return assignVariable(lvar.first!!, right)
        } else { // (Literal) or (Literal) -> attribute's name
            when (left) {
                is Literal -> {
                    val right = visit(ctx?.expression())
                    if (!checkTypeOfLiteralsIdentical(left, right as Literal)) throw Exception("Type mismatch")
                    return assignVariable(left, right)
                }
                is Pair<*, *> -> {
                    val right = visit(ctx?.expression())
                    val variable = left.first
                    when (variable) {
                        is StructLiteral -> {
                            variable.body[left.second as String] = right as Literal
                            return variable.body[left.second as String]!!
                        }
                        is Prototype -> {
                            variable.members[left.second as String] = right as Literal
                            return variable.members[left.second as String]!!
                        }
                    }
                }
                else -> throw Exception("This could not happen")
            }
        }
        throw Exception("Something goes wrong while visiting assignment expression")
    }

    override fun visitLiteral_expression(ctx: amatsukazeGrammarParser.Literal_expressionContext?): Any {
        return when(val content = visit(ctx?.getChild(0))) {
            is Int -> IntegerLiteral(Variability.CST, content, typeTable["Integer"]!!)
            is Double -> DoubleLiteral(Variability.CST, content, typeTable["Double"]!!)
            is Boolean -> BooleanLiteral(Variability.CST, content, typeTable["Bool"]!!)
            is Pair<*, *> ->
                when (content.second) {
                    _STRING -> StringLiteral(Variability.CST, content.first as String, typeTable["String"]!!)
                    _CHARACTER -> CharacterLiteral(Variability.CST, content.first as Char, typeTable["Character"]!!)
                    else -> throw Exception("This cannot happen.")
                }
            is Array<*> -> {
                content as Array<Literal>
                ArrayLiteral(Variability.CST, inferPrimaryToArrayType(content), content.toMutableList(), typeTable["Array"]!!)
            }
            else -> content as Literal
        }
    }

    // TODO Array type check
    override fun visitArray_literal(ctx: amatsukazeGrammarParser.Array_literalContext?): Any {
        if (ctx?.childCount == 2) {
            return Array<Literal?>(0) { IntegerLiteral(Variability.CST, 0, typeTable["Integer"]!!) }
        } else {
            val array = Array<Literal?>(ctx?.childCount!! / 2) { IntegerLiteral(Variability.CST, 0, typeTable["Integer"]!!) }
            val variability =
                if (ctx.parent?.parent?.parent is amatsukazeGrammarParser.Array_literal_itemContext
                    || ctx.parent?.parent?.parent?.getChild(0).toString() == "cst") Variability.CST else Variability.VAR
            for (i in 0 until ctx.childCount / 2) {
                array[i] = when (val content = visit(ctx.getChild(i * 2 + 1))) {
                    is IntegerLiteral -> IntegerLiteral(variability, content.content, content.prototype)
                    is DoubleLiteral -> DoubleLiteral(variability, content.content, content.prototype)
                    is BooleanLiteral -> BooleanLiteral(variability, content.content, content.prototype)
                    is StringLiteral -> StringLiteral(variability, content.content, content.prototype)
                    is CharacterLiteral -> CharacterLiteral(variability, content.content, content.prototype)
                    is StructLiteral -> StructLiteral(variability, content.body, content.prototype)
                    is FunctionLiteral -> FunctionLiteral(
                        variability,
                        content.head,
                        content.body,
                        content.closureScope,
                        content.prototype
                    )
                    is ArrayLiteral -> ArrayLiteral(variability, inferArrayType(content), content.content, content.prototype)
                    else -> throw Exception("This should not happen.")
                }
            }
            return array
        }
    }

    override fun visitArray_literal_item(ctx: amatsukazeGrammarParser.Array_literal_itemContext?): Any {
        return visit(ctx?.expression())
    }

    override fun visitMember_expression(ctx: amatsukazeGrammarParser.Member_expressionContext?): Any {
        return visit(ctx?.explicit_member_expression())
    }

    override fun visitSubscript_expression(ctx: amatsukazeGrammarParser.Subscript_expressionContext?): Any {
        val left = visit(ctx?.getChild(0))
        val right = visit(ctx?.subscript())
        if (ctx?.variable_expression() != null || ctx?.subscript_expression() != null) {
            if (right is Int) {
                left as ArrayLiteral
                return left.content[right]
            } else if (right is String) {
                val trueRight = queryVariableTable(right).first ?: throw Exception("Variable $right not found on subscript expression")
                if (trueRight is IntegerLiteral) {
                    left as ArrayLiteral
                    return left.content[trueRight.content]
                }
                else throw Exception("Unsupported operation on subscript expression")
            } else if (right is IntegerLiteral) {
                left as ArrayLiteral
                return left.content[right.content]
            } else {
                throw Exception("Unsupported operation on subscript expression.")
            }
        } else {
            throw Exception("Unsupported subscript operation on structures.")
        }
    }

    override fun visitSubscript(ctx: amatsukazeGrammarParser.SubscriptContext?): Any {
        return if (ctx?.DECIMAL_LITERAL() == null) ctx?.IDENTIFIER()?.text ?: throw Exception("Null identifier encountered")
        else "${visit(ctx?.DECIMAL_LITERAL())}".toInt()
    }

    override fun visitVariable_expression(ctx: amatsukazeGrammarParser.Variable_expressionContext?): Any {
        var name: String = ""
        try {
            name = visit(ctx?.IDENTIFIER()).toString()
            return if (providedProperties.containsKey(name)) {
                val ans = providedProperties.getValue(name)(manager.firstId)
                return BooleanLiteral(Variability.CST, ans.invoke(), typeTable["Bool"]!!)
            } else
                queryVariableTable(name).first!!
        } catch (e: Exception) {
            throw Exception("The variable $name looking for doesn't exist!")
        }
    }

    private fun preprocessPrintRule(argument: Literal): String {
        return when (argument) {
            is IntegerLiteral -> argument.content.toString()
            is DoubleLiteral -> argument.content.toString()
            is CharacterLiteral -> argument.content.toString()
            is StringLiteral -> argument.content
            is BooleanLiteral -> if (argument.content) "true" else "false"
            is StructLiteral -> "Struct #"
            is FunctionLiteral -> "Function #${argument.head.name}@${argument.head.params.joinToString(",")}"
            is ArrayLiteral -> argument.content.toString()
            is PlayerLiteral -> "Player #id${argument.id}"
            is SpecialistLiteral -> "Specialist #id${argument.id}"
            else -> throw Exception("Unsupported print operation on type")
        }
    }

    private fun copyOnFunctionCall(l: Literal): Literal {
        return when (l) {
            is IntegerLiteral -> IntegerLiteral(Variability.VAR, l.content, l.prototype)
            is DoubleLiteral -> DoubleLiteral(Variability.VAR, l.content, l.prototype)
            is StringLiteral -> StringLiteral(Variability.VAR, l.content, l.prototype)
            is CharacterLiteral -> CharacterLiteral(Variability.VAR, l.content, l.prototype)
            is BooleanLiteral -> BooleanLiteral(Variability.VAR, l.content, l.prototype)
            is PlayerLiteral -> PlayerLiteral(Variability.VAR, l.id, l.prototype)
            is SpecialistLiteral -> SpecialistLiteral(Variability.VAR, l.id, l.prototype)
            else -> l
        }
    }

    private fun handleFunctionCallVisit(realHead: FuncHead, funcArgu: List<Literal>, func: ParseTree, closure: List<Scope>? = null): Any {
        try {
            funcEntriesDepth.add(++internalDepth)
            currentFunc += 1
            anonymousFuncIndices.add(0)
            internalVariables.add(mutableMapOf())
            if (closure != null) {
                closures.addAll(closure)
            }
            if (funcArgu.isNotEmpty()) {
                val newVariables = realHead.params.zip(funcArgu)
                newVariables.forEach { internalVariables[currentFunc][it.first] = it.second }
            }
            inFunc.add(true)
            val ret = visit(func)
            inFunc.removeAt(inFunc.size - 1)
            anonymousFuncIndices.removeAt(anonymousFuncIndices.size - 1)
            functionTable.filter { it.value.second >= currentFunc }.keys.forEach { name -> functionTable.remove(name) }
            internalVariables.removeAt(internalVariables.size - 1)
            internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name ->
                internalVariableDepth.remove(name)
            }
            if (closure != null) {
                for (x in 1 .. closure.size) closures.removeAt(closures.lastIndex)
            }
            funcEntriesDepth.remove(funcEntriesDepth.lastIndex)
            currentFunc -= 1
            return ret
        } catch (e: Throwable) {
            if (e is ReturnedLiteral) {
//                    internalDepth = funcEntriesDepth.last()
//                    internalVariableDepth.filter { it.value > internalDepth }.keys.forEach { name -> internalVariables[currentFunc].remove(name) }
//                    internalVariableDepth.filter { it.value > internalDepth }.keys.forEach { name -> internalVariableDepth.remove(name) }
                anonymousFuncIndices.removeAt(anonymousFuncIndices.size - 1)
                functionTable.filter { it.value.second >= currentFunc }.keys.forEach { name -> functionTable.remove(name) }
                internalVariables.removeAt(internalVariables.size - 1)
                internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name ->
                    internalVariableDepth.remove(name)
                }
                if (closure != null) {
                    for (x in 1..closure.size) closures.removeAt(closures.lastIndex)
                }
                funcEntriesDepth.removeAt(funcEntriesDepth.lastIndex)
                currentFunc -= 1
                return e.content
            } else if (e is TerminatedReturn) {
                throw e
            } else throw Exception("Something went wrong in function call visit\n    ${e.message}")
        }
    }

    override fun visitFunction_call_expression(ctx: amatsukazeGrammarParser.Function_call_expressionContext?): Any {
        try {
            val functionName = visit(ctx?.function_name()) as String
            val clause =
                if (ctx?.childCount == 2) listOf() else visit(ctx?.call_argument_clause()) as List<Pair<Literal, Boolean>>
            val funcArgument = clause.map { it.first }
            val refs = clause.map { it.second }
            if (functionName == "Player" && funcArgument.isEmpty()) {
                return declareNewPlaygroundObject("PLAYER")
            } else if (functionName == "Specialist" && funcArgument.isEmpty()) {
                return declareNewPlaygroundObject("SPECIALIST")
            } else if (funcArgument.isEmpty() && providedFunctions.containsKey(functionName)) {
                providedFunctions.getValue(functionName)(manager.firstId)
                if (manager.dead())
                    throw TerminatedReturn("GAMEOVER")
                if (manager.win())
                    throw TerminatedReturn("WIN")
                return SpecialRetVal.Empty
            } else if (functionName == "changeColor" && funcArgument.size == 1) {
                if (funcArgument[0] is StringLiteral) {
                    val color = when ((funcArgument[0] as StringLiteral).content.toLowerCase()) {
                        "black" -> Color.BLACK
                        "silver" -> Color.SILVER
                        "grey" -> Color.GREY
                        "white" -> Color.WHITE
                        "red" -> Color.RED
                        "orange" -> Color.ORANGE
                        "gold" -> Color.GOLD
                        "pink" -> Color.PINK
                        "yellow" -> Color.YELLOW
                        "beige" -> Color.BEIGE
                        "brown" -> Color.BROWN
                        "green" -> Color.GREEN
                        "azure" -> Color.AZURE
                        "cyan" -> Color.CYAN
                        "aliceblue" -> Color.ALICEBLUE
                        "purple" -> Color.PURPLE
                        else -> throw Exception("Unsupported color")
                    }
                    manager.changeColor(manager.firstId, color)
                    return SpecialRetVal.Empty
                } else {
                    throw Exception("Wrong argument type in color function.")
                }
            } else if (functionName == "typeOf" && funcArgument.size == 1) {
                return internal_typeOf(funcArgument[0])
            } else if (functionName == "isSame" && funcArgument.size == 2) {
                return internal_isSame(funcArgument[0], funcArgument[1])
            } else if (functionName == "print") {
                manager.print(funcArgument.map{ preprocessPrintRule(it) })
                return SpecialRetVal.Empty
            } else {
                val copiedFuncArgu = funcArgument.mapIndexed { i, l -> if (!refs[i]) copyOnFunctionCall(l) else l }
                val funcHead = FuncHead(
                    functionName, listOf(), literalsToDataType(copiedFuncArgu.toTypedArray()), refs, _CALL)
                for (key in functionTable.keys)
                    if (funcHead.pseudoEquals(key))
                        return handleFunctionCallVisit(key, copiedFuncArgu, functionTable[key]!!.first)
                for (i in internalVariables.size - 1 downTo 0)
                    for (entry in internalVariables[i].entries)
                        if (entry.key == functionName && entry.value is FunctionLiteral) {
                            val value = entry.value as FunctionLiteral
                            if (funcHead.pseudoEquals(value.head))
                                return handleFunctionCallVisit(value.head, copiedFuncArgu, value.body, value.closureScope)
                        }
                for (entry in variableTable.entries) {
                    if (entry.key == functionName && entry.value is FunctionLiteral) {
                        val value = entry.value as FunctionLiteral
                        if (funcHead.pseudoEquals(value.head))
                            return handleFunctionCallVisit(value.head, copiedFuncArgu, value.body, value.closureScope)
                    }
                }
                return SpecialRetVal.NotDef
            }
        } catch (e: Exception) {
            throw Exception("Something went wrong while passing function call: \n    ${e.message}")
        }
    }

    override fun visitCall_argument_clause(ctx: amatsukazeGrammarParser.Call_argument_clauseContext?): Any {
        return ctx?.call_argument()?.map { visit(it) }!! as List<Pair<Literal, Boolean>>
    }

    override fun visitCall_argument(ctx: amatsukazeGrammarParser.Call_argumentContext?): Any {
        return visit(ctx?.expression()) to (null != ctx?.REF())
    }

    override fun visitStruct_call_expression(ctx: amatsukazeGrammarParser.Struct_call_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitDot_operator(ctx: amatsukazeGrammarParser.Dot_operatorContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatement(ctx: amatsukazeGrammarParser.StatementContext?): Any {
        return visit(ctx?.getChild(0))
    }

    override fun visitStatements(ctx: amatsukazeGrammarParser.StatementsContext?): Any {
        try {
            if (inFunc.isNotEmpty()) internalDepth += 1 else externalDepth += 1
            var ret: Any = SpecialRetVal.Statements
            for (child in ctx?.children!!) {
                if (_break || _continue)
                    break
                ret = visit(child)
            }
            if (inFunc.isNotEmpty()) {
                internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name -> internalVariables[currentFunc].remove(name) }
                internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name -> internalVariableDepth.remove(name) }
                internalDepth -= 1
            } else {
                variableDepth.filter { it.value == externalDepth }.keys.forEach { name -> variableTable.remove(name) }
                variableDepth.filter { it.value == externalDepth }.keys.forEach { name -> variableDepth.remove(name) }
                externalDepth -= 1
            }
            return ret
        } catch (e: Throwable) {
            if (e is ReturnedLiteral) {
                internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name -> internalVariables[currentFunc].remove(name) }
                internalVariableDepth.filter { it.value == internalDepth }.keys.forEach { name -> internalVariableDepth.remove(name) }
                internalDepth -= 1
                throw e
            } else if (e is TerminatedReturn) {
                throw e
            } else
                throw Exception("Something goes wrong while visiting statements:\n    ${e.message}")
        }
    }

    override fun visitLoop_statement(ctx: amatsukazeGrammarParser.Loop_statementContext?): Any {
        return visit(ctx?.getChild(0))
    }

    override fun visitFor_in_statement(ctx: amatsukazeGrammarParser.For_in_statementContext?): Any {
        try {
            val preRange = visit(ctx?.expression())
            val range = mutableListOf<Any>()
            when (preRange) {
                is IntRange -> {
                    preRange.forEach { range.add(it) }
                }
                is StringLiteral -> {
                    range.addAll(preRange.content.map { CharacterLiteral(Variability.CST, it, typeTable["Character"]!!) })
                }
                is ArrayLiteral -> {
                    range.addAll(preRange.content.map { when (it) {
                        is IntegerLiteral -> it.content
                        is DoubleLiteral -> it.content
                        is StringLiteral -> it.content
                        is CharacterLiteral -> it.content
                        is BooleanLiteral -> it.content
                        else -> it
                    } })
                }
                is String -> {
                    val literal = queryVariableTable(preRange).first ?: throw Exception("Variable not found")
                    if (literal is ArrayLiteral) {
                        range.addAll(literal.content.map { when (it) {
                            is IntegerLiteral -> it.content
                            is DoubleLiteral -> it.content
                            is StringLiteral -> it.content
                            is CharacterLiteral -> it.content
                            is BooleanLiteral -> it.content
                            else -> it
                        } })
                    }
                }
            }
            // Future possibility: range of an object
            val pattern = visit(ctx?.pattern()) as String
            val vTable = if (ctx?.parent?.parent?.parent?.parent?.parent is amatsukazeGrammarParser.Function_bodyContext)
                internalVariables[internalVariables.size - 1] else variableTable
            // println(range)
            for (x in range) {
                if (pattern != "_") {
                    when (x) {
                        is Int -> vTable[pattern] = IntegerLiteral(Variability.CST, x, typeTable["Integer"]!!)
                        is Double -> vTable[pattern] = DoubleLiteral(Variability.CST, x, typeTable["Double"]!!)
                        is String -> vTable[pattern] = StringLiteral(Variability.CST, x, typeTable["String"]!!)
                        is Char -> vTable[pattern] = CharacterLiteral(Variability.CST, x, typeTable["Character"]!!)
                        is Boolean -> vTable[pattern] = BooleanLiteral(Variability.CST, x, typeTable["Bool"]!!)
                        is FunctionLiteral -> vTable[pattern] = FunctionLiteral(Variability.CST, x.head, x.body, x.closureScope, x.prototype)
                        is StructLiteral -> vTable[pattern] = StructLiteral(Variability.CST, x.body, x.prototype)
                        is ArrayLiteral -> vTable[pattern] = ArrayLiteral(Variability.CST, x.subType, x.content, x.prototype)
                        is PlayerLiteral -> vTable[pattern] = PlayerLiteral(Variability.CST, x.id, x.prototype)
                        is SpecialistLiteral -> vTable[pattern] = SpecialistLiteral(Variability.CST, x.id, x.prototype)
                        else -> throw Exception("Unsupported operation on for-in statement")
                        // TODO this might cause problem if the pattern shallows another variable in the outer scope?
                    }
                }
                if (_break) {
                    _break = false
                    break
                }
                if (_continue) {
                    _continue = false
                    continue
                }
                visit(ctx?.code_block())
            }
            vTable.remove(pattern)
        } catch (e: Exception) {
            throw Exception("Something goes wrong while visiting for-in expression:\n    ${e.message}")
        }
        return SpecialRetVal.Loop
    }

    override fun visitWhile_statement(ctx: amatsukazeGrammarParser.While_statementContext?): Any {
        while ((visit(ctx?.expression()) as BooleanLiteral).content) {
            if (_break) {
                _break = false
                break
            }
            if (_continue) {
                _continue = false
                break
            }
            visit(ctx?.code_block())
        }
        return SpecialRetVal.Loop
    }

    override fun visitRepeat_while_statement(ctx: amatsukazeGrammarParser.Repeat_while_statementContext?): Any {
        do {
            if (_break) {
                _break = false
                break
            }
            if (_continue) {
                _continue = false
                continue
            }
            visit(ctx?.code_block())
        } while (visit(ctx?.expression()) == true)
        return SpecialRetVal.Loop
    }

    override fun visitBranch_statement(ctx: amatsukazeGrammarParser.Branch_statementContext?): Any {
        return visit(ctx?.if_statement())
    }

    override fun visitIf_statement(ctx: amatsukazeGrammarParser.If_statementContext?): Any {
        if ((visit(ctx?.expression()) as BooleanLiteral).content) {
            visit(ctx?.code_block())
        } else {
            if (ctx?.else_clause() != null) {
                visit(ctx.else_clause())
            }
        }
        return SpecialRetVal.Branch
    }

    override fun visitElse_clause(ctx: amatsukazeGrammarParser.Else_clauseContext?): Any {
        return visit(ctx?.getChild(1))
    }

    override fun visitControl_transfer_statement(ctx: amatsukazeGrammarParser.Control_transfer_statementContext?): Any {
        return visit(ctx?.getChild(0))
    }

    override fun visitBreak_statement(ctx: amatsukazeGrammarParser.Break_statementContext?): Any {
        _break = true
        return SpecialRetVal.Interr
    }

    override fun visitContinue_statement(ctx: amatsukazeGrammarParser.Continue_statementContext?): Any {
        _continue = true
        return SpecialRetVal.Interr
    }

    override fun visitReturn_statement(ctx: amatsukazeGrammarParser.Return_statementContext?): Any {
        val ret = visit(ctx?.expression())
        throw ReturnedLiteral(ret) // Proto or Triple<FuncHead, amatsukazeGrammarParser.Function_bodyContext, List<Scope>>
    }

    override fun visitYield_statement(ctx: amatsukazeGrammarParser.Yield_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitConstantDecl(ctx: amatsukazeGrammarParser.ConstantDeclContext?): Any {
        return visit(ctx?.constant_declaration())
    }

    override fun visitVariableDecl(ctx: amatsukazeGrammarParser.VariableDeclContext?): Any {
        return visit(ctx?.variable_declaration())
    }

    override fun visitFunctionDecl(ctx: amatsukazeGrammarParser.FunctionDeclContext?): Any {
        return visit(ctx?.function_declaration())
    }

    override fun visitEnumDecl(ctx: amatsukazeGrammarParser.EnumDeclContext?): Any {
        return visit(ctx?.enum_declaration())
    }

    override fun visitStructDecl(ctx: amatsukazeGrammarParser.StructDeclContext?): Any {
        return visit(ctx?.struct_declaration())
    }

    override fun visitCode_block(ctx: amatsukazeGrammarParser.Code_blockContext?): Any {
        return visit(ctx?.statements())
    }

    override fun visitConstant_declaration(ctx: amatsukazeGrammarParser.Constant_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) throw Exception("Illegal variable name")
        try {
            val right = visit(ctx?.expression())
            var trueRight: Literal
            if (right is Triple<*, *, *>) {
                right as Triple<FuncHead, ParseTree, MutableList<Scope>>
                right.first.name = left
                trueRight = FunctionLiteral(Variability.CST, right.first, right.second, right.third, typeTable["Function"]!!)
            }
            else {
                trueRight = right as Literal
            }
            val type = if (ctx?.childCount == 4) null else visit(ctx?.type()) as DataType?
            if (ctx?.parent?.parent?.parent?.parent?.parent is amatsukazeGrammarParser.Function_bodyContext) {
                internalVariables[internalVariables.size - 1][left] =
                    declareConstantOrVariable(type, trueRight, constant = true) ?: throw Exception("Encountered error while declaring constant")
                internalVariableDepth[left] = internalDepth
                return SpecialRetVal.Declaration
            } else {
                variableTable[left] =
                    declareConstantOrVariable(type, trueRight, constant = true) ?: throw Exception("Encountered error while declaring constant")
                variableDepth[left] = externalDepth
                return SpecialRetVal.Declaration
            }
        } catch (e: Exception) {
            throw Exception("Unknown literal type on right-hand side while declaring constant ${left}\n    $e")
        }
    }

    override fun visitVariable_declaration(ctx: amatsukazeGrammarParser.Variable_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) throw Exception("Illegal variable name")
        try {
            val right = visit(ctx?.expression())
            var trueRight: Literal
            if (right is Triple<*, *, *>) {
                right as Triple<FuncHead, ParseTree, MutableList<Scope>>
                right.first.name = left
                trueRight = FunctionLiteral(Variability.CST, right.first, right.second, right.third, typeTable["Function"]!!)
            }
            else {
                trueRight = right as Literal
            }
            val type = if (ctx?.childCount == 4) null else visit(ctx?.type()) as DataType?
            if (ctx?.parent?.parent?.parent?.parent?.parent is amatsukazeGrammarParser.Function_bodyContext) {
                internalVariables[internalVariables.size - 1][left] =
                    declareConstantOrVariable(type, trueRight, constant = false)
                        ?: throw Exception("Encountered error while declaring constant")
                internalVariableDepth[left] = internalDepth
                return SpecialRetVal.Declaration
            } else {
                variableTable[left] =
                    declareConstantOrVariable(type, trueRight, constant = false)
                        ?: throw Exception("Encountered error while declaring constant")
                variableDepth[left] = externalDepth
                return SpecialRetVal.Declaration
            }
        } catch (e: Exception) {
            throw Exception("Unknown literal type on right-hand side while declaring variable")
        }
    }

    private fun handleFunctionDeclaration(nameNode: amatsukazeGrammarParser.Function_nameContext?,
                                          signatureNode: amatsukazeGrammarParser.Function_signatureContext,
                                          bodyNode: amatsukazeGrammarParser.Function_bodyContext): Any {
        try {
            val functionSignature = visit(signatureNode) as Pair<List<Triple<String, DataType, Boolean>>, DataType>
            if (nameNode == null) {
                val funcHead = FuncHead(
                    "$#${assignAnonymousFuncIndex(currentFunc)}",
                    functionSignature.first.map { it.first },
                    functionSignature.first.map { it.second },
                    functionSignature.first.map { it.third },
                    functionSignature.second
                )
                val newScope = mutableListOf<Scope>()
                newScope.addAll(closures)
                newScope.addAll(internalVariables)
                return Triple(funcHead, bodyNode, newScope)
            } else {
                val funcHead = FuncHead(
                    visit(nameNode) as String,
                    functionSignature.first.map { it.first },
                    functionSignature.first.map { it.second },
                    functionSignature.first.map { it.third },
                    functionSignature.second
                )
                for (key in functionTable.keys)
                    if (funcHead.pseudoEquals(key))
                        throw Exception("Redefined function!")
                functionTable[funcHead] = bodyNode to currentFunc
                val newScope = mutableListOf<Scope>()
                newScope.addAll(closures)
                newScope.addAll(internalVariables)
                return Triple(funcHead, bodyNode, newScope)
            }
        } catch (e: Exception) {
            throw Exception("Encountered error within function declaration: \n    ${e.message}")
        }
    }

    override fun visitFunction_declaration(ctx: amatsukazeGrammarParser.Function_declarationContext?): Any {
        return handleFunctionDeclaration(ctx!!.function_name(), ctx.function_signature(), ctx.function_body())
    }

    override fun visitFunction_name(ctx: amatsukazeGrammarParser.Function_nameContext?): Any {
        return ctx?.IDENTIFIER().toString()
    }

    override fun visitFunction_signature(ctx: amatsukazeGrammarParser.Function_signatureContext?): Any {
        val paramClause = visit(ctx?.parameter_clause())
        paramClause as List<Triple<String, DataType, Boolean>>
        val resultType = if (ctx?.childCount == 1) _VOID else visit(ctx?.function_result_type())
        return (paramClause to resultType) as Pair<List<Triple<String, DataType, Boolean>>, DataType>
    }

    override fun visitFunction_result_type(ctx: amatsukazeGrammarParser.Function_result_typeContext?): Any {
        return visit(ctx?.type())
    }

    override fun visitFunction_body(ctx: amatsukazeGrammarParser.Function_bodyContext?): Any {
        return visit(ctx?.code_block())
    }

    override fun visitExpressional_function_declaration(ctx: amatsukazeGrammarParser.Expressional_function_declarationContext?): Any {
        return if (ctx?.function_declaration() == null) visit(ctx?.arrowfun_declaration()) else visit(ctx.function_declaration())
    }

    override fun visitArrowfun_declaration(ctx: amatsukazeGrammarParser.Arrowfun_declarationContext?): Any {
        return handleFunctionDeclaration(null, ctx!!.function_signature(), ctx.function_body())
    }

    override fun visitParameter_clause(ctx: amatsukazeGrammarParser.Parameter_clauseContext?): Any {
        return if (ctx?.childCount == 1) listOf<Triple<String, DataType, Boolean>>() else visit(ctx?.parameter_list())
    }

    override fun visitParameter_list(ctx: amatsukazeGrammarParser.Parameter_listContext?): Any {
        return ctx?.children?.filterIndexed { index, _ -> index % 2 == 0 }?.map { visit(it) }!!
    }

    override fun visitParameter(ctx: amatsukazeGrammarParser.ParameterContext?): Any {
        val ref = ctx?.REF() != null
        return Triple(visit(ctx?.param_name()), visit(ctx?.type_annotation()), ref) as Triple<String, DataType, Boolean>
    }

    override fun visitParam_name(ctx: amatsukazeGrammarParser.Param_nameContext?): Any {
        return ctx?.IDENTIFIER().toString()
    }

    override fun visitEnum_declaration(ctx: amatsukazeGrammarParser.Enum_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_name(ctx: amatsukazeGrammarParser.Enum_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_members(ctx: amatsukazeGrammarParser.Enum_membersContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_member(ctx: amatsukazeGrammarParser.Enum_memberContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_declaration(ctx: amatsukazeGrammarParser.Struct_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_name(ctx: amatsukazeGrammarParser.Struct_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_body(ctx: amatsukazeGrammarParser.Struct_bodyContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_initializer(ctx: amatsukazeGrammarParser.Struct_initializerContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAttribute_assignment(ctx: amatsukazeGrammarParser.Attribute_assignmentContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_member(ctx: amatsukazeGrammarParser.Struct_memberContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType_inheritance_clause(ctx: amatsukazeGrammarParser.Type_inheritance_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType_annotation(ctx: amatsukazeGrammarParser.Type_annotationContext?): Any {
        return visit(ctx?.type())
    }

    override fun visitPattern(ctx: amatsukazeGrammarParser.PatternContext?): Any {
        return visit(ctx?.getChild(0))
    }

    override fun visitWildcard_pattern(ctx: amatsukazeGrammarParser.Wildcard_patternContext?): Any {
        return "_"
    }

    override fun visitIdentifier_pattern(ctx: amatsukazeGrammarParser.Identifier_patternContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: throw Exception("Empty identifier encountered")
    }

    override fun visitNumberedExpMemberExpr(ctx: amatsukazeGrammarParser.NumberedExpMemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitNamedExpMemberExpr(ctx: amatsukazeGrammarParser.NamedExpMemberExprContext?): Any {
        // Internal functions such as foo.isOnGem
        try {
            val obj = visit(ctx?.variable_expression()) as Literal
            val name = ctx?.IDENTIFIER()!!.toString()
            when (obj) {
                is PlayerLiteral -> {
                    if (name == "collectedGem") {
                        val ans = manager.collectedGem(obj.id)
                        return IntegerLiteral(Variability.CST, ans, typeTable["Integer"]!!)
                    }
                    if (providedProperties.containsKey(name)) {
                        val ans = providedProperties.getValue(name)(obj.id)
                        return BooleanLiteral(Variability.CST, ans.invoke(), typeTable["Bool"]!!)
                    }
                }
                is StringLiteral -> {
                    if (name == "size") {
                        return IntegerLiteral(Variability.CST, obj.content.length, typeTable["Integer"]!!)
                    }
                }
                is CharacterLiteral -> {
                    if (name == "isDigit") {
                        return BooleanLiteral(Variability.CST, obj.content.isDigit(), typeTable["Bool"]!!)
                    }
                    if (name == "isAlpha") {
                        return BooleanLiteral(Variability.CST, obj.content.isLetter(), typeTable["Bool"]!!)
                    }
                    if (name == "isUpper") {
                        return BooleanLiteral(Variability.CST, obj.content.isUpperCase(), typeTable["Bool"]!!)
                    }
                    if (name == "isLower") {
                        return BooleanLiteral(Variability.CST, obj.content.isLowerCase(), typeTable["Bool"]!!)
                    }
                    if (name == "isBlank") {
                        return BooleanLiteral(Variability.CST, obj.content.isWhitespace(), typeTable["Bool"]!!)
                    }
                }
                is ArrayLiteral -> {
                    if (name == "size") {
                        return IntegerLiteral(Variability.CST, obj.content.size, typeTable["Integer"]!!)
                    }
                    if (name == "first") {
                        return obj.content.first()
                    }
                    if (name == "last") {
                        return obj.content.last()
                    }
                    if (name == "empty") {
                        return BooleanLiteral(Variability.CST, obj.content.isEmpty(), typeTable["Bool"]!!)
                    }
                }
                else -> return SpecialRetVal.NotDef
            }
            throw Exception("In explicit member expression: Not implemented#1")
        } catch(e: Exception) {
            throw Exception("Something went wrong while visiting explicit member expression\n    ${e.message}")
        }
    }

    override fun visitFuncExpMemberExpr(ctx: amatsukazeGrammarParser.FuncExpMemberExprContext?): Any {
        return handleFunctionCall(ctx?.function_call_expression()!!, ctx.variable_expression())
    }

    override fun visitMemberOfFuncCallExpr(ctx: amatsukazeGrammarParser.MemberOfFuncCallExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRecursiveExpMemberExpr(ctx: amatsukazeGrammarParser.RecursiveExpMemberExprContext?): Any {
        // Internal functions call such as foo.bar.isOnGem
        TODO("Not yet implemented")
    }

    override fun visitSimpleType(ctx: amatsukazeGrammarParser.SimpleTypeContext?): Any {
        return when (ctx?.IDENTIFIER().toString()) {
            "Int" -> _INT
            "Double" -> _DOUBLE
            "Character" -> _CHARACTER
            "String" -> _STRING
            "Bool" -> _BOOL
            "Void" -> _VOID
            "Function" -> _FUNCTION
            "Struct" -> _STRUCT
            "Enum" -> _ENUM
            "Array" -> _ARRAY(_SOME)

            "Player" -> _PLAYER
            "Specialist" -> _SPECIALIST
            else -> throw Exception("Unsupported type")
        }
    }

    override fun visitArrayType(ctx: amatsukazeGrammarParser.ArrayTypeContext?): Any {
        return _ARRAY(visit(ctx?.type()) as DataType)
    }

    override fun visitArrayTypeSub(ctx: amatsukazeGrammarParser.ArrayTypeSubContext?): Any {
        return _ARRAY(visit(ctx?.type()) as DataType)
    }

    override fun visitMember_pattern(ctx: amatsukazeGrammarParser.Member_patternContext?): Any {
        when {
            ctx?.identifier_pattern() != null -> {
                val variable = (queryVariableTable(ctx?.identifier_pattern()?.text!!) as Pair<*, *>).first
                    ?: throw Exception("Cannot find variable associated to identifier")
                return if (ctx?.IDENTIFIER()?.text == "prototype")
                    (variable as Proto).prototype ?: throw Exception("Encountered null while searching in prototype chain")
                else {
                    val attribute = ctx.IDENTIFIER()?.text!!
                    when (variable) {
                        is StructLiteral -> {
                            if (variable.body.containsKey(attribute)) variable.body[attribute]!!
                            else variable to attribute
                        }
                        else -> throw Exception("Unsupported member pattern call")
                    }
                }
            }
            ctx?.member_pattern() != null -> {
                when (val variable = (queryVariableTable(ctx?.identifier_pattern()?.text!!) as Pair<*, *>).first
                    ?: throw Exception("Cannot find variable associated to identifier")) {
                    is Proto -> {
                        if (ctx?.IDENTIFIER()?.text == "prototype")
                            return variable.prototype ?: throw Exception("Encountered null while searching in prototype chain")
                        else {
                            val attribute = ctx.IDENTIFIER()?.text!!
                            return when (variable) {
                                is StructLiteral -> {
                                    if (variable.body.containsKey(attribute)) variable.body[attribute]!!
                                    else variable to attribute
                                }
                                is Prototype -> {
                                    if (variable.members.containsKey(attribute)) variable.members[attribute]!!
                                    else variable to attribute
                                }
                                else -> throw Exception("Unsupported member pattern call")
                            }
                        }
                    }
                    is Pair<*, *> -> throw Exception("Unsupported operation on member pattern")
                    else -> throw Exception("This could not happen")
                }
            }
            else -> throw Exception("This could not happen")
        }
    }

    override fun visitSubscript_pattern(ctx: amatsukazeGrammarParser.Subscript_patternContext?): Any {
        if (ctx?.identifier_pattern() != null) {
            val left = queryVariableTable(visit(ctx?.getChild(0)) as String)
            val right = visit(ctx?.subscript())
            if (left.second != -2) {
                if (left.first is ArrayLiteral && right is Int)
                    return (left.first as ArrayLiteral).content[right]
                if (left.first is StructLiteral)
                    throw Exception("Unsupported subscript operation on structures.")
            }
            throw Exception("Unsupported lhs subscript operation.")
        }
        if (ctx?.subscript_pattern() != null) {
            val left = visit(ctx?.subscript_pattern())
            val right = visit(ctx?.subscript())
            if (left is ArrayLiteral && right is Int) return left.content[right]
            throw Exception("Unsupported lhs subscript operation.")
        }
        if (ctx?.member_expression() != null) {
            // TODO
        }
        throw Exception("This cannot happen")
    }

    override fun visitAssert_statement(ctx: amatsukazeGrammarParser.Assert_statementContext?): Any {
        when (val rhs = visit(ctx?.expression())) {
            is IntegerLiteral -> if (rhs.content == 0) throw Exception("AssertError\n")
            is DoubleLiteral -> if (rhs.content == 0.0) throw Exception("AssertError\n")
            is BooleanLiteral -> if (!rhs.content) throw Exception("AssertError\n")
            else -> throw Exception("AssertError\n")
        }
        return SpecialRetVal.Empty
    }

}
