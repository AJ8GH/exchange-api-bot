package io.github.aj8gh.bot.console.util.formatting;

public enum ShellColour {
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    MAGENTA(5),
    CYAN(6),
    WHITE(7),
    BRIGHT(8);

    private final int value;

    ShellColour(int value) {
        this.value = value;
    }

    public int toStyle() {
        return this.value;
    }
}
