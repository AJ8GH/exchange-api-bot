package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.auth.session.Session;
import io.github.aj8gh.bot.auth.session.SessionSupplier;
import io.github.aj8gh.bot.console.util.constants.Descriptions;
import java.util.function.Supplier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AuthConsole {

  private final Supplier<Session> sessionSupplier;

  public AuthConsole(SessionSupplier sessionSupplier) {
    this.sessionSupplier = sessionSupplier;
  }

  @ShellMethod(Descriptions.LOGIN)
  public Session login() {
    return sessionSupplier.get();
  }
}
