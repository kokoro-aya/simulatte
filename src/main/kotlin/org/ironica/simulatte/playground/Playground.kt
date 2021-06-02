/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.playground

import org.ironica.simulatte.bridge.rules.GamingCondition
import org.ironica.simulatte.internal.*
import org.ironica.simulatte.payloads.statusStorage
import org.ironica.simulatte.playground.datas.*
import org.ironica.simulatte.playground.Direction.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import java.lang.reflect.AccessibleObject
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberProperties

class Playground(val squares: List<List<Square>>,
                 val portals: MutableMap<PortalItem, Coordinate>,
                 val locks: MutableMap<Coordinate, LockBlock>,
                 val characters: MutableMap<AbstractCharacter, Coordinate>,

                 val gamingCondition: GamingCondition?,
                 val userCollision: Boolean = true,

                 ) {

    val initialGem: Int
    val initialSwitch: Int
    val initialBeeper: Int

    private var currentTurn: Int = 0

    private var condToSatisfy: Int

    init {

        characters.forEach { it.value.asSquare.players.add(it.key) }

        characters.keys.forEach { it.playground = this }

        initialGem = squares.flatten().filter { it.gem != null }.size
        initialSwitch = squares.flatten().filter { it.switch != null }.size
        initialBeeper = squares.flatten().filter { it.beeper != null }.size

        condToSatisfy = if (gamingCondition == null) 0
            else (gamingCondition::class as KClass<Any>).declaredMemberProperties
            .filter { it.get(gamingCondition) != null }
            .filterNot { it.name == "stringRepresentation" }.size
    }

    val allGemCollected: Int
        get() = characters.keys.sumOf { it.collectedGem }
    val allGemLeft: Int
        get() = squares.flatten().filter { it.gem != null }.size
    val allBeeperCollected: Int
        get() = characters.keys.sumOf { it.beeperInBag }
    val allBeeperLeft: Int
        get() = squares.flatten().filter { it.beeper != null }.size
    val allOpenedSwitch: Int
        get() = squares.flatten().filter { it.switch?.on == true }.size
    val allClosedSwitch: Int
        get() = squares.flatten().filter { it.switch?.on == false }.size

    fun win(): Boolean {
        statusStorage.set(GameStatus.WIN)
        return true
    } // TODO implement win conditions
    fun lose(): Boolean {
        statusStorage.set(GameStatus.LOST)
        return true
    } // TODO implement lost conditions

    private val AbstractCharacter.getCoo: Coordinate
        get() = characters[this] ?: throw Exception("Playground:: Find no coordinate for specified character")

    private val Coordinate.asSquare: Square
        get() {
            check(y in squares.indices && x in squares[0].indices) {
                "Playground:: attempt to convert a coordinate to a square but it's out of bounds"
            }
            return squares[y][x]
        }

    private fun prePrintTile(c: Coordinate): String {
        val sq = c.asSquare
        if (sq.players.isNotEmpty()) return when (sq.players[0].dir) {
            UP -> "上"
            DOWN -> "下"
            LEFT -> "左"
            RIGHT -> "右"
        }
        if (sq.gem != null) return "钻"
        if (sq.switch != null) return if (sq.switch!!.on) "开" else "关"
        if (sq.beeper != null) return "机"
        if (sq.portal != null) return "门"
        if (sq.platform != null) return "台"
        return when (sq.block) {
            Void -> "无"
            Open -> "空"
            Blocked -> "障"
            is LockBlock -> "锁"
            is StairBlock -> when ((sq.block as StairBlock).dir) {
                UP -> "⬆️"
                DOWN -> "⬇️"
                LEFT -> "⬅️"
                RIGHT -> "➡️"
            }
        }
    }

    fun printGrid() {
        squares.forEachIndexed { i, row -> row.forEachIndexed { j, _ ->
                print(prePrintTile(Coordinate(j, i)))
            }
            println()
        }
        println()
    }

    fun incrementATurn() {

        characters.forEach {
            if (it.value.asSquare.block is Void) it.key.kill()
        }

        if (currentTurn > gamingCondition?.endGameAfter ?: Int.MAX_VALUE / 2) statusStorage.set(GameStatus.LOST)
        var satisfiedCond = 0
        if (allGemCollected >= gamingCondition?.collectGemsBy ?: Int.MAX_VALUE / 2) satisfiedCond += 1
        if (allBeeperCollected >= gamingCondition?.collectBeepersBy ?: Int.MAX_VALUE / 2) satisfiedCond += 1
        if (allOpenedSwitch >= gamingCondition?.openedSwitchBy ?: Int.MAX_VALUE / 2) satisfiedCond += 1
        if (gamingCondition?.putBeepersAt?.map { squares[it.y][it.x].beeper != null }?.all { it } == true) satisfiedCond += 1
        if (gamingCondition?.arriveAt?.map { squares[it.y][it.x].players.isNotEmpty()
                    || squares[it.y][it.x].platform?.players?.isNotEmpty() == true }?.all { it } == true) satisfiedCond += 1
        if (satisfiedCond == condToSatisfy) statusStorage.set(GameStatus.WIN)
        currentTurn += 1
    }

    // ---- Begin of Move Helper Functions ---- //

    private fun isTileAccessible(tile: BlockObject): Boolean {
        return when (tile) {
            is LockBlock, Blocked, Void -> false
            else -> true
        }
    }

    private fun isAdjacent(from: Coordinate, to: Coordinate): Boolean {
        return from.x == to.x && from.y == to.y - 1
                || from.x == to.x && from.y == to.y + 1
                || from.y == to.y && from.x == to.x - 1
                || from.y == to.y && from.x == to.x + 1
    }

    // Test if two adjacent blocks are connected by a stair
    /* The direction of stair is the direction of lower edge of this block
     * Will also check the difference of height
     */
    private fun hasStairToward(from: Coordinate, to: Coordinate): Boolean {
        check (isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square" }
        to.asSquare.block.let {
            if (it !is StairBlock) return false
            if (from.y + 1 == to.y) return it.dir == UP
            if (from.y - 1 == to.y) return it.dir == DOWN
            if (from.x - 1 == to.x) return it.dir == RIGHT
            if (from.x + 1 == to.x) return it.dir == LEFT
            return false
        }
    }

    private fun isMoveBlocked(from: Coordinate, to: Coordinate): Boolean {
        check(isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square [1]" }
        val f = from.asSquare; val t = to.asSquare
        if (!isTileAccessible(t.block)) return true
        if (userCollision && t.players.isNotEmpty()) return true
        return (t.level - f.level).let {
            it < -1 || it > 1 || (it == 1 && !hasStairToward(from, to))
                    || (it == -1 && !hasStairToward(to, from))
        }
    }

    private fun isMoveFromPlatformToPlatformBlocked(from: Coordinate, to: Coordinate): Boolean {
        check(isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square [2]" }
        val f = from.asSquare.platform; val t = to.asSquare.platform
        if (!(f != null && t != null)) return true
        if (userCollision && t.players.isNotEmpty()) return true
        return (t.level != f.level)
    }

    private fun isMoveFromPlatformBlocked(from: Coordinate, to: Coordinate): Boolean {
        check(isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square [3]" }
        val f = from.asSquare.platform; val t = to.asSquare
        if (f == null) return true
        if (userCollision && t.players.isNotEmpty()) return true
        return (t.level != f.level)
    }

    private fun isMoveToPlatformBlocked(from: Coordinate, to: Coordinate): Boolean {
        check(isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square [4]" }
        val f = from.asSquare; val t = to.asSquare.platform
        if (t == null) return true
        if (userCollision && t.players.isNotEmpty()) return true
        return (t.level != f.level)
    }

    // Must be public in order to use reflect on this method
    fun findAPath(from: Coordinate, to: Coordinate): Pair<PlayerReceiver?, PlayerReceiver?> {
        if (!to.isInPlayground) return null to null
        check(isAdjacent(from, to)) { "Playground:: Must move from a square to an adjacent square [A]" }
        if (!isMoveBlocked(from, to)) return PlayerReceiver.TILE to PlayerReceiver.TILE
        if (!isMoveFromPlatformToPlatformBlocked(from, to)) return PlayerReceiver.PLATFORM to PlayerReceiver.PLATFORM
        if (!isMoveFromPlatformBlocked(from, to)) return PlayerReceiver.PLATFORM to PlayerReceiver.TILE
        if (!isMoveToPlatformBlocked(from, to)) return PlayerReceiver.TILE to PlayerReceiver.PLATFORM
        return null to null
    }

    private val Coordinate.isInPlayground: Boolean
        get() = this.y in this@Playground.squares.indices && this.x in this@Playground.squares[0].indices

    private val Coordinate.up: Coordinate
        get() = Coordinate(this.x, this.y - 1)
    private fun<T> Coordinate.up(func: KFunction<T>, vararg others: Any?): T {
        // Need to set accessible the function to invoke, otherwise the reflect library will refuse to check safety
        AccessibleObject.setAccessible(this.javaClass.declaredFields.filter { it.name == func.name }.toTypedArray(), true)
        return func.call(this, this.up, *others)
    }

    private val Coordinate.down: Coordinate
        get() = Coordinate(this.x, this.y + 1)
    private fun<T> Coordinate.down(func: KFunction<T>, vararg others: Any?): T {
        AccessibleObject.setAccessible(this.javaClass.declaredFields.filter { it.name == func.name }.toTypedArray(), true)
        return func.call(this, this.down, *others)
    }

    private val Coordinate.left: Coordinate
        get() = Coordinate(this.x - 1, this.y)
    private fun<T> Coordinate.left(func: KFunction<T>, vararg others: Any?): T {
        AccessibleObject.setAccessible(this.javaClass.declaredFields.filter { it.name == func.name }.toTypedArray(), true)
        return func.call(this, this.left, *others)
    }

    val Coordinate.right: Coordinate
        get() = Coordinate(this.x + 1, this.y)
    private fun<T> Coordinate.right(func: KFunction<T>, vararg others: Any?): T {
        AccessibleObject.setAccessible(this.javaClass.declaredFields.filter { it.name == func.name }.toTypedArray(), true)
        return func.call(this, this.right, *others)
    }


    private fun findPathTowardYPlus(char: AbstractCharacter): Pair<PlayerReceiver?, PlayerReceiver?> = char.getCoo.up(::findAPath)
    private fun findPathTowardYMinus(char: AbstractCharacter): Pair<PlayerReceiver?, PlayerReceiver?> = char.getCoo.down(::findAPath)
    private fun findPathTowardXMinus(char: AbstractCharacter): Pair<PlayerReceiver?, PlayerReceiver?> = char.getCoo.left(::findAPath)
    private fun findPathTowardXPlus(char: AbstractCharacter): Pair<PlayerReceiver?, PlayerReceiver?> = char.getCoo.right(::findAPath)

    // Must be public in order to use reflect on this method
    fun move(from: Coordinate, to: Coordinate, char: AbstractCharacter, path: Pair<PlayerReceiver?, PlayerReceiver?>, action: () -> Unit) {
        when (path.first!!) {
            PlayerReceiver.TILE -> when (path.second!!) {
                PlayerReceiver.TILE -> {
                    from.asSquare.players.remove(char); to.asSquare.players.add(char)
                }
                PlayerReceiver.PLATFORM -> {
                    from.asSquare.players.remove(char); to.asSquare.platform?.players?.add(char)
                }
            }
            PlayerReceiver.PLATFORM -> when (path.second!!) {
                PlayerReceiver.TILE -> {
                    from.asSquare.platform?.players?.remove(char); to.asSquare.players.add(char)
                }
                PlayerReceiver.PLATFORM -> {
                    from.asSquare.platform?.players?.remove(char); to.asSquare.platform?.players?.remove(char)
                }
            }
        }
        action.invoke()
    }

    // Need to update reference of this player in the characters list, also entry point for movement
    private fun movePlayerUp(char: AbstractCharacter, path: Pair<PlayerReceiver?, PlayerReceiver?>) = char.getCoo.up(::move, char, path, {
        characters[char] = characters[char]!!.up
    })
    private fun movePlayerDown(char: AbstractCharacter, path: Pair<PlayerReceiver?, PlayerReceiver?>) = char.getCoo.down(::move, char, path, {
        characters[char] = characters[char]!!.down
    })
    private fun movePlayerLeft(char: AbstractCharacter, path: Pair<PlayerReceiver?, PlayerReceiver?>) = char.getCoo.left(::move, char, path, {
        characters[char] = characters[char]!!.left
    })
    private fun movePlayerRight(char: AbstractCharacter, path: Pair<PlayerReceiver?, PlayerReceiver?>) = char.getCoo.right(::move, char, path, {
        characters[char] = characters[char]!!.right
    })

    private fun getPathForward(char: AbstractCharacter): Pair<PlayerReceiver?, PlayerReceiver?> {
        return when (char.dir) {
            UP -> findPathTowardYPlus(char)
            DOWN -> findPathTowardYMinus(char)
            LEFT -> findPathTowardXMinus(char)
            RIGHT -> findPathTowardXPlus(char)
        }
    }

    // ---- End of Move Helper Functions ---- //

    // ---- Start of Player Properties ---- //

    fun playerIsOnGem(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.gem != null
    }

    fun playerIsOnOpenedSwitch(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.switch?.on == true
    }

    fun playerIsOnClosedSwitch(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.switch?.on == false
    }

    fun playerIsOnBeeper(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.beeper != null
    }

    fun playerIsOnPortal(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.portal != null && char.getCoo.asSquare.players.contains(char)
    }

    fun playerIsOnPlatform(char: AbstractCharacter): Boolean {
        return char.isAlive && char.getCoo.asSquare.platform?.players?.contains(char) ?: false
    }

    fun playerIsBlocked(char: AbstractCharacter): Boolean {
        return when (char.dir) {
            UP -> findPathTowardYPlus(char) == null to null
            DOWN -> findPathTowardYMinus(char) == null to null
            LEFT -> findPathTowardXMinus(char) == null to null
            RIGHT -> findPathTowardXPlus(char) == null to null
        }
    }

    fun playerIsBlockedLeft(char: AbstractCharacter): Boolean {
        return when (char.dir) {
            RIGHT -> findPathTowardYPlus(char) == null to null
            LEFT -> findPathTowardYMinus(char) == null to null
            UP -> findPathTowardXMinus(char) == null to null
            DOWN -> findPathTowardXPlus(char) == null to null
        }
    }

    fun playerIsBlockedRight(char: AbstractCharacter): Boolean {
        return when (char.dir) {
            LEFT -> findPathTowardYPlus(char) == null to null
            RIGHT -> findPathTowardYMinus(char) == null to null
            DOWN -> findPathTowardXMinus(char) == null to null
            UP -> findPathTowardXPlus(char) == null to null
        }
    }

    // ---- End of Player Properties ---- //

    // ---- Start of Player Methods ---- //

    fun playerTurnLeft(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        char.dir = when (char.dir) {
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            RIGHT -> UP
        }
        // TODO insert stamina rule here
        incrementATurn()
        return true
    }

    fun playerTurnRight(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        char.dir = when (char.dir) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
        // TODO insert stamina rule here
        incrementATurn()
        return true
    }

    fun playerMoveForward(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        val oldTile = char.getCoo.asSquare
        val path = getPathForward(char)
        if (path != null to null) {
            when (char.dir) {
                UP -> movePlayerUp(char, path)
                DOWN -> movePlayerDown(char, path)
                LEFT -> movePlayerLeft(char, path)
                RIGHT -> movePlayerRight(char, path)
            }
            val newTile = char.getCoo.asSquare
            // TODO insert after-move rules here
            // TODO insert stamina rule here
            incrementATurn()
            return true
        }
        incrementATurn()
        return false
    }

    fun playerStepIntoPortal(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        if (playerIsOnPortal(char)) {
            val p = portals.entries.first { it.value == char.getCoo }.key
            if (p.isActive) {
                p.coo.asSquare.players.remove(char)
                p.dest.asSquare.players.add(char)
                characters[char] = p.dest
                // TODO insert stamina rule here
                p.energy -= 1 // TODO insert portal energy change here
                if (p.energy <= 0) p.isActive = false
                return true
            }
        }
        return false
    }

    fun playerCollectGem(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        return if (playerIsOnGem(char)) {
            char.collectedGem += 1
            char.getCoo.asSquare.gem = null
            // TODO add additional gems rule here
            // TODO insert stamina rule here
            incrementATurn()
            true
        } else {
            incrementATurn()
            true
        }
    }

    fun playerToggleSwitch(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        return when {
            playerIsOnOpenedSwitch(char) -> {
                characters[char]?.asSquare?.switch?.on = false
                // TODO insert stamina rule here
                incrementATurn()
                true
            }
            playerIsOnClosedSwitch(char) -> {
                characters[char]?.asSquare?.switch?.on = true
                // TODO insert stamina rule here
                incrementATurn()
                true
            }
            else -> {
                incrementATurn()
                false
            }
        }
    }

    fun playerTakeBeeper(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        return if (playerIsOnBeeper(char)) {
            char.beeperInBag += 1
            characters[char]?.asSquare?.beeper = null
            // TODO insert stamina rule here
            incrementATurn()
            true
        } else {
            incrementATurn()
            false
        }
    }

    fun playerDropBeeper(char: AbstractCharacter): Boolean {
        if (char.isDead || char.beeperInBag <= 0) return false
        with (characters[char]!!.asSquare) {
            return if (this.beeper == null) {
                char.beeperInBag -= 1
                this.beeper = BeeperItem()
                // TODO insert stamina rule here
                incrementATurn()
                true
            } else {
                incrementATurn()
                false
            }
        }
    }

    private fun killACharacter(char: AbstractCharacter) {
        with (characters[char]!!.asSquare) {
            this.players.remove(char)
            this.platform?.players?.remove(char)
            char.stamina = Int.MIN_VALUE / 2
            // We won't remove the character from the list
        }
    }

    fun playerKill(char: AbstractCharacter): Boolean {
        if (char.isDead) return false
        killACharacter(char)
        incrementATurn()
        return true
    }

    // ---- End of Player Methods ---- //

    // ---- Start of Specialist Properties & Methods ---- //

    fun specialistIsBeforeLock(char: InstantializedSpecialist): Boolean {
        if (char.isDead) return false
        val coo = char.getCoo
        val (x, y) = coo
        return when (char.dir) {
            UP -> y >= 1 && coo.up.asSquare.block is LockBlock
            DOWN -> y <= squares.size - 2 && coo.down.asSquare.block is LockBlock
            LEFT -> x >= 1 && coo.left.asSquare.block is LockBlock
            RIGHT -> x <= squares[0].size - 2 && coo.right.asSquare.block is LockBlock
        }
    }

    private fun turnLock(coo: Coordinate, up: Boolean) {
        with (locks[coo]
            ?: throw Exception("Playground:: No lock of the coordinate found")) {
            this.controlled.forEach {
                with (it.asSquare) {
                    this.platform?.let {
                        if (up && it.level < 15) it.level += 1
                        else if (it.level > this.level + 1) it.level -= 1
                    } ?: throw Exception("Playground:: Attempt to change level of a platform that doesn't exist")
                }
            }
        }
    }

    private fun lockPos(char: InstantializedSpecialist): Coordinate {
        check (specialistIsBeforeLock(char))
        return when (char.dir) {
            UP -> char.getCoo.up
            DOWN -> char.getCoo.down
            LEFT -> char.getCoo.left
            RIGHT -> char.getCoo.right
        }
    }

    fun specialistTurnLockUp(char: InstantializedSpecialist): Boolean {
        if (char.isDead) return false
        if (specialistIsBeforeLock(char)) {
            turnLock(lockPos(char), up = true)
            // TODO insert stamina rule here
            incrementATurn()
            return true
        }
        incrementATurn()
        return false
    }

    fun specialistTurnLockDown(char: InstantializedSpecialist): Boolean {
        if (char.isDead) return true
        if (specialistIsBeforeLock(char)) {
            turnLock(lockPos(char), up = false)
            // TODO insert stamina rule here
            incrementATurn()
            return true
        }
        incrementATurn()
        return false
    }


    // World level functions

    fun worldPlace(item: Item, at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: Coordinate out of bounds" }
        at.asSquare.let {
            check(it.block !is LockBlock) { "Playground:: Could not place items on Lock tile" }
            check(it.block != Void) { "Playground:: Could not place items on Void tile" }
            when (item) {
                Beeper -> if (it.beeper == null) it.beeper = BeeperItem() else return false
                Gem -> if (it.gem == null) it.gem = GemItem() else return false
                is SwitchP -> if (it.switch == null) it.switch = SwitchItem(item.on) else return false
            }
        }
        return true
    }

    fun worldPlace(block: BlockP, at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: Coordinate out of bounds" }
        at.asSquare.let {
            check(it.block !is LockBlock) { "Playground:: Could not change tile on Lock tile" }
            check(it.block !is StairBlock) { "Playground:: Could not change tile on Stair tile" }
            when (block.blocked) {
                true -> it.block = Blocked
                false -> it.block = Open
            }
        }
        return true
    }

    fun worldPlace(portal: PortalP, atStart: Coordinate, atEnd: Coordinate): Boolean {
        check(atStart.isInPlayground && atEnd.isInPlayground) { "Playground:: coordinate out of bounds" }
        check(atStart.asSquare.block !is LockBlock && atEnd.asSquare.block !is LockBlock) { "Playground:: Could not set up portal on Lock tile" }
        check(atStart.asSquare.block != Void && atEnd.asSquare.block != Void) { "Playground:: could not set up portal on Void tile" }
        return if (atStart.asSquare.portal == null) {
            val newId = portals.size
            val newPortalItem = PortalItem(newId, atStart, atEnd, portal.color, false) // TODO add portal energy rule
            atStart.asSquare.portal = newPortalItem
            portals[newPortalItem] = atStart
            portal.id = newId
            true
        } else {
            false
        }
    }

    fun worldPlace(stair: Stair, facing: Direction, at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: coordinate out of bounds" }
        check(at.asSquare.block !is LockBlock) { "Playground:: Could not put stair on Lock tile" }
        check(at.asSquare.block !is Void) { "Playground:: Could not put stair on Void tile" }
        at.asSquare.block = StairBlock(facing)
        return true
    }

    fun worldPlaceCharacter(char: Character, facing: Direction, at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: coordinate out of bounds" }
        check(at.asSquare.block !is LockBlock) { "Playground:: Could not put character on Lock tile" }
        check(at.asSquare.block !is Void) { "Playground:: Could not put character on Void tile" }
        var p: AbstractCharacter? = null
        when (char) {
            is Player -> p = InstantializedPlayer(char.id, facing, 500) // TODO add stamina default rule
            is Specialist -> p = InstantializedSpecialist(char.id, facing, 500)
        }
        at.asSquare.players.add(p ?: throw NullPointerException("Playground:: this should not happen"))
        characters[p] = at
        return true
    }

    val worldAllPossibleCoordinates: Array<Coordinate>
        get() = squares.mapIndexed { i, line ->
            line.mapIndexed { j, _ ->
                Coordinate(j, i)
            }
        }.flatten().toTypedArray()

    fun worldRemoveAllBlocks(at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: coordinate out of bounds" }
        check(at.asSquare.block !is LockBlock) { "Playground:: Could not remove blocks on Lock tile" }
        check(at.asSquare.block !is Void) { "Playground:: Could not remove blocks on Void tile" }
        at.asSquare.let {
            it.block = Void
            it.level = 0
            it.platform?.players?.forEach { it.kill() }
            it.platform = null
            it.players.forEach { it.kill() }
            it.gem = null; it.beeper = null; it.switch = null
            if (it.portal != null) {
                portals.remove(it.portal)
                it.portal = null
            }
        }
        return true
    }

    private fun convertInstantializedCharToPresentativeChar(char: AbstractCharacter): Character {
        return when (char) {
            is InstantializedPlayer -> Player(char.id)
            is InstantializedSpecialist -> Specialist(char.id)
            else -> throw IllegalArgumentException("Playground:: unknown char type")
        }
    }

    fun worldExistingCharacters(at: Array<Coordinate>): Array<Character> {
        return (at.map { it.asSquare.players }.flatten().map { convertInstantializedCharToPresentativeChar(it) }
                + at.map { it.asSquare.platform?.players ?: listOf() }.flatten().map { convertInstantializedCharToPresentativeChar(it) }
                ).toTypedArray()
    }

    fun worldDigDown(at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: coordinate out of bounds" }
        at.asSquare.let {
            if (it.level > 1) {
                it.level -= 1
                return true
            }
            return false
        }
    }

    fun worldPileUp(at: Coordinate): Boolean {
        check(at.isInPlayground) { "Playground:: coordinate out of bounds" }
        at.asSquare.let {
            if (it.level < 15) { // TODO set height max/min rule
                it.level += 1
                return true
            }
            return false
        }
    }
}

