![Logo](simulatte.PNG)

![](https://img.shields.io/badge/version-3.3.3-brightgreen)

A Playground server implemented with Kotlin DSL.

Inspired by Playgrounds on iOS/macOS and Karel the Robot, the project was first implemented using ANTLR. Later, it was
migrated to the usage of Kotlin DSL.

For more info about the story of this project and some inspirations, see [the dedicated column of wiki](https://github.com/kokoro-aya/simulatte/wiki/About).

See docs for how to launch the application, some test cases and a short language reference on Kotlin DSL used (a subset
of Kotlin language) by the application. These docs might be helpful for you to implement your
own client to communicate with the server.

**Warning**: The project is still in development and has great tons of bugs.

**Remark**: For usage with the unity front-end developed last year, use the 3.3 version downloadable in release column. This project 
itself has been changed, so it's no longer usable with this front-end.

The currently updated version will be suitable to work with the [front-end written in React/UmiJS](https://github.com/kokoro-aya/shizuku-front-end), both projects are currently WIP.

Build with

- Kotlin
- Ktor
- Kotlin serialization
- Kotlin Poet
- Ki-Shell

For more information please review [the Wiki](https://github.com/kokoro-aya/amatsukaze/wiki) of this repo.

For download please go to the pages of [Release](https://github.com/kokoro-aya/amatsukaze/releases).

---

# Getting Started

### Prerequisite

Simulatte requires Java version of range 11 to 15. Java version newer than 16 is not supported as `Unable to initialize repl compiler: using old JVM backend` error will be raised when launching the shell.

According to your OS platform, you can use `choco`, `brew` or `apt-get` or other package manager to get the latest OpenJDK with `adoptopenjdk`.

Every release is a single fat jar file, bundled with a full Kotlin/Ktor runtime.

You could download the latest release of Simulatte in the [release](https://github.com/kokoro-aya/simulatte/releases) column of this repo.

To use the server you may need to implement and setup your own client. However, you can download the [Hoppscotch](https://github.com/hoppscotch/hoppscotch) to launch requests and receive responses from the Simulatte server, for the debug purpose. By the way, you have to implement a feature to quit the server.

### Start Simulatte

**Warning: Please ensure that only one instance of your simulatte.jar is running at a port otherwise you might need to force-quit some instances!!**

You cannot double-click the Simulatte program to launch it.

To launch it, open a terminal in the folder where you downloaded simulatte.jar and enter `java -jar simulatte.jar` where `simulatte.jar` should be replaced by your program's name.

It's recommended to run the program in a new terminal session, considering it's potential large outputs and by the way you cannot quit the program by clicking Ctrl + C so you have to close the terminal window directly in case you haven't implemented the quit feature.

#### Start Arguments

Prior to ver 3.3.0 (included), the default port was 9370 and the path was `/simulatte`. Newer versions have default port is 4729 and path has been changed to `/aqua`.

-   `port={PORT}` The server will listen at specified port number instead of default (4729).
-   `debug` The server will output the playground's grid after each turn.
-   `stdout` The server will output every user's output with `console.log(vararg)` method.
    -   Notice that the `console.log(vararg)` method is different to `println()` or `print()` method in Kotlin. If you call the latter, no output will be recorded.
-   `output` The server will pretty print the concatenated code generated by codegen, for your debug purpose.
-   You can combine these arguments. Any order is possible. Any other argument will be ignored.

### Quit Simulatte

You cannot quit the program directly.

If you haven't implemented the quit feature, you should close the terminal window or kill the process to quit the program. If you have the Postman installed, send a POST request to `127.0.0.1:4729/aqua/shutdown` (replace `4729` with your port launch parameter) to shutdown safely the server.

You can implement the quit feature in your client by using the same method.
