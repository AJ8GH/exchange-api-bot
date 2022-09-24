package io.github.aj8gh.bot.console.util;

import static io.github.aj8gh.bot.console.util.constants.Messages.ERROR_PREFIX;
import static io.github.aj8gh.bot.console.util.constants.Messages.INFO_PREFIX;
import static io.github.aj8gh.bot.console.util.constants.Messages.SUCCESS_PREFIX;
import static io.github.aj8gh.bot.console.util.constants.Messages.WARN_PREFIX;

import io.github.aj8gh.bot.console.util.formatting.ColourFormatter;
import io.github.aj8gh.bot.console.util.formatting.TableFormatter;
import java.util.List;
import org.jline.terminal.Terminal;

public class ShellPrinter {

  private final Terminal terminal;
  private final ColourFormatter colourFormatter;
  private final TableFormatter tableFormatter;

  public ShellPrinter(Terminal terminal,
      ColourFormatter colourFormatter,
      TableFormatter tableFormatter) {
    this.terminal = terminal;
    this.colourFormatter = colourFormatter;
    this.tableFormatter = tableFormatter;
  }

  public void print(String message) {
    terminal.writer().println(message);
    terminal.flush();
  }

  public <T> void print(List<T> list) {
    print(tableFormatter.format(list));
  }

  public void info(String message) {
    print(colourFormatter.toInfo(INFO_PREFIX + message));
  }

  public void success(String message) {
    print(colourFormatter.toSuccess(SUCCESS_PREFIX + message));
  }

  public void warn(String message) {
    print(colourFormatter.toWarning(WARN_PREFIX + message));
  }

  public void error(String message) {
    print(colourFormatter.toError(ERROR_PREFIX + message));
  }
}
