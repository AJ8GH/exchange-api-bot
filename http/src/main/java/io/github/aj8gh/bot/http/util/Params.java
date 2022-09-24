package io.github.aj8gh.bot.http.util;

public enum Params {
  USERNAME("username"),
  PASSWORD("password");

  private final String param;

  Params(String param) {
    this.param = param;
  }

  public String param() {
    return param;
  }
}
