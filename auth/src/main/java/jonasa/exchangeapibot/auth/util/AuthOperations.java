package jonasa.exchangeapibot.auth.util;

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
}
