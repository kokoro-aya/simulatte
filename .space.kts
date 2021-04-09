@file:DependsOn("org.antlr:antlr4:4.8")

import org.antlr.v4.Tool

job("Generate parser and recognizer and then build the project") {
    /* container(displayName = "generate files", "openjdk:11") {
        kotlinScript {
            Tool.main(arrayOf("-o", "gen", "-visitor", "-no-listener", "src/main/amatsukazeGrammar.g4"))
            println("Grammar Recognizer generated.")
        }
    } */
    gradlew("openjdk:11", "build")
}