package jonasa.exchangeapibot.auth.util;

public enum AuthOperations {
    LOGIN("login"),
    LOGOUT("logout"),
    KEEP_ALIVE("keepAlive");

    private static final String PATH_DELIMITER = "/";

    private final String value;

    AuthOperations(String value) {
        this.value = value;
    }

    public String path() {
        return PATH_DELIMITER + value;
    }
}
