package jonasa.exchangeapibot.betting.enums;

public enum OrderBy {
    @Deprecated // Use BY_PLACE_TIME instead. Order by placed time, then bet id.
    BY_BET,
    BY_MARKET,
    BY_MATCH_TIME,
    BY_PLACE_TIME,
    BY_SETTLED_TIME,
    BY_VOID_TIME
}
