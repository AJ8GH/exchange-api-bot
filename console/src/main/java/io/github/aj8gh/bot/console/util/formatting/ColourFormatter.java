package io.github.aj8gh.bot.console.util.formatting;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class ColourFormatter {
    private String infoColour;
    private String successColour;
    private String warningColour;
    private String errorColour;

    public String toInfo(String message) {
        return toColour(message, ShellColour.valueOf(infoColour));
    }

    public String toSuccess(String message) {
        return toColour(message, ShellColour.valueOf(successColour));
    }

    public String toWarning(String message) {
        return toColour(message, ShellColour.valueOf(warningColour));
    }

    public String toError(String message) {
        return toColour(message, ShellColour.valueOf(errorColour));
    }

    public void setInfoColour(String infoColour) {
        this.infoColour = infoColour;
    }

    public void setSuccessColour(String successColour) {
        this.successColour = successColour;
    }

    public void setWarningColour(String warningColour) {
        this.warningColour = warningColour;
    }

    public void setErrorColour(String errorColour) {
        this.errorColour = errorColour;
    }

    private String toColour(String message, ShellColour color) {
        return (new AttributedStringBuilder())
                .append(message, AttributedStyle.DEFAULT.foreground(
                        color.toStyle())).toAnsi();
    }
}
