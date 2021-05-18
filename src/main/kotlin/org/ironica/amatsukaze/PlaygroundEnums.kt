package org.ironica.amatsukaze

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class Block {
    OPEN, BLOCKED, WATER, TREE, DESERT, HOME, MOUNTAIN, STONE, LOCK, STAIR,
}

enum class Item {
    NONE, GEM, CLOSEDSWITCH, OPENEDSWITCH, BEEPER, PORTAL, PLATFORM
}

enum class Color {
    BLACK, SILVER, GREY, WHITE, RED, ORANGE, GOLD, PINK, YELLOW, BEIGE, BROWN, GREEN, AZURE, CYAN, ALICEBLUE, PURPLE
}

enum class Role {
    PLAYER, SPECIALIST,
}
