package io.github.aj8gh.bot.http.operations;

public enum AuthOperations {
  LOGIN("login"),
  LOGOUT("logout"),
  KEEP_ALIVE("keepAlive");

  private static final String PATH_DELIMITER = "/";

  private final String operation;

  AuthOperations(String operation) {
    this.operation = operation;
  }

  public String path() {
    return PATH_DELIMITER + operation;
  }

  public String operation() {
    return operation;
  }
}
