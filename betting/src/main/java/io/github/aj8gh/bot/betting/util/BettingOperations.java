package io.github.aj8gh.bot.betting.util;

public enum BettingOperations {
    LIST_EVENT_TYPES("listEventTypes"),
    LIST_COMPETITIONS("listCompetitions"),
    LIST_TIME_RANGES("listTimeRanges"),
    LIST_EVENTS("listEvents"),
    LIST_MARKET_TYPES("listMarketTypes"),
    LIST_COUNTRIES("listCountries"),
    LIST_VENUES("listVenues"),
    LIST_MARKET_CATALOGUE("listMarketCatalogue"),
    LIST_MARKET_BOOK("listMarketBook"),
    LIST_RUNNER_BOOK("listRunnerBook"),
    LIST_MARKET_PROFIT_AND_LOSS("listMarketProfitAndLoss"),
    LIST_CURRENT_ORDERS("listCurrentOrders"),
    LIST_CLEARED_ORDERS("listClearedOrders"),
    PLACE_ORDERS("placeOrders"),
    CANCEL_ORDERS("cancelOrders"),
    REPLACE_ORDERS("replaceOrders"),
    UPDATE_ORDERS("updateOrders");

    private static final String PATH_DELIMITER = "/";
    private final String operation;

    BettingOperations(String operation) {
        this.operation = operation;
    }

    public String path() {
        return PATH_DELIMITER + operation + PATH_DELIMITER;
    }
}
