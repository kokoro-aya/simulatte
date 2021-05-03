import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

val iterator_next = """
{
    if self.hasNext() {
        cst data = curr.data
        curr = curr.next
        return data
    }
    return nil
}
"""

fun main() {
    val lexer = amatsukazeGrammarLexer(CharStreams.fromString(iterator_next))
    val tokens = CommonTokenStream(lexer)
    val parser = amatsukazeGrammarParser(tokens)
    val tree = parser.function_body()
    print(1)
}