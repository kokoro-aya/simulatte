@file:DependsOn("org.antlr:antlr4:4.8")

import org.antlr.v4.Tool
import java.io.File

job("Generate parser and recognizer and then build the project") {
    container("openjdk:11") {
        kotlinScript { api ->
            Tool.main(arrayOf("-o", "gen", "-visitor", "-no-listener", "src/main/amatsukazeGrammar.g4"))
            println("Grammar Recognizer generated.")
            api.gradlew("build")
            println("Build succeeded.")
        }
    }
}