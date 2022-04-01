package io.github.aj8gh.bot.console.util.constants;

public final class Descriptions {
    /**
     * Betting API
     * Operations
     */
    public static final String LIST_EVENT_TYPES = "Returns a list of Event Types (i.e. Sports) associated with the markets selected by the MarketFilter.";
    public static final String LIST_EVENTS = "Returns a list of Events (i.e, Reading vs. Man United) associated with the markets selected by the MarketFilter.";
    public static final String LIST_MARKET_TYPES = "Returns a list of market types (i.e. MATCH_ODDS, NEXT_GOAL) associated with the markets selected by the MarketFilter. The market types are always the same, regardless of locale.";
    public static final String LIST_COUNTRIES = "Returns a list of Countries associated with the markets selected by the MarketFilter.";
    public static final String LIST_VENUES = "Returns a list of Venues (i.e. Cheltenham, Ascot) associated with the markets selected by the MarketFilter. Please note: Only horse racing & greyhound markets are associated with a Venue.";
    public static final String LIST_TIME_RANGES = "Returns a list of time ranges in the granularity specified in the request (i.e. 3PM to 4PM, Aug 14th to Aug 15th) associated with the markets selected by the MarketFilter.";
    public static final String LIST_MARKET_CATALOGUE = "Returns a list of information about published (ACTIVE/SUSPENDED) markets that does not change (or changes very rarely).";
    public static final String LIST_RUNNER_CATALOG = "Displays the runner catalog for the given market ID.";
    public static final String LIST_MARKET_BOOK = "Returns a list of dynamic data about marketsDynamic data includes prices, the status of the market, the status of selections, the traded volume, and the status of any orders you have placed in the market.";
    public static final String LIST_RUNNER_BOOK = "Returns a list of dynamic data about a market and a specified runner Dynamic data includes prices, the status of the market, the status of selections, the traded volume, and the status of any orders you have placed in the market.";
    public static final String LIST_MARKET_PROFIT_AND_LOSS = "Retrieve profit and loss for a given list of OPEN markets. The values are calculated using matched bets and optionally settled bets.";
    public static final String LIST_CURRENT_ORDERS = "Returns a list of your current orders.";
    public static final String LIST_CLEARED_ORDERS = "Returns a list of settled bets based on the bet status, ordered by settled date.";
    public static final String PLACE_ORDERS = "Place new orders into market.";
    public static final String CANCEL_ORDERS = "Cancel all bets OR cancel all bets on a market OR fully or partially cancel particular orders on a market.";
    public static final String REPLACE_ORDERS = "This operation is logically a bulk cancel followed by a bulk place. The cancel is completed first then the new orders are placed.";
    public static final String UPDATE_ORDERS = "Update non-exposure changing fields.";
    public static final String LIST_COMPETITIONS = """
            Returns a list of Competitions (i.e., World Cup 2013) associated with the markets selected by the MarketFilter.
            Please note: Specific eventTypes (horse racing & greyhounds) are not associated with specific competitions.
            These eventTypes are only associated with Venues (see listVenues).""";
    /**
     * Parameters
     */
    public static final String MARKET_FILTER = "The filter to select desired markets. All markets that match the criteria in the filter are selected.";
    public static final String TIME_GRANULARITY = "The granularity of time periods that correspond to markets selected by the market filter.";
    public static final String TEXT_QUERY = "Restrict markets by any text associated with the Event name. You can include a wildcard (*) character as long as it is not the first character. Please note - the textQuery field doesn't evaluate market or selection names.";
    public static final String EVENT_TYPE_IDS = "Restrict markets by event type associated with the market. (i.e., Football, Hockey, etc).";
    public static final String EVENT_IDS = "Restrict markets by the event id associated with the market.";
    public static final String COMPETITION_IDS = "Restrict markets by the competitions associated with the market.";
    public static final String MARKET_IDS = "Restrict markets by the market id associated with the market.";
    public static final String MARKET_ID = "The market for which to view the runner catalog.";
    public static final String VENUES = "Restrict markets by the venue associated with the market. Currently only Horse Racing markets have venues.";
    public static final String BSP_ONLY = "Restrict to bsp markets only, if True or non-bsp markets if False. If not specified then returns both BSP and non-BSP markets.";
    public static final String TURN_IN_PLAY_ENABLED = "Restrict to markets that will turn in play if True or will not turn in play if false. If not specified, returns both.";
    public static final String IN_PLAY_ONLY = "Restrict to markets that are currently in play if True or are not currently in play if false. If not specified, returns both.";
    public static final String MARKET_BETTING_TYPES = "Restrict to markets that match the betting type of the market (i.e. Odds, Asian Handicap Singles, Asian Handicap Doubles or Line).";
    public static final String MARKET_COUNTRIES = "Restrict to markets that are in the specified country or countries.  Please note: default value is 'GB' when the correct country code cannot be determined.";
    public static final String MARKET_TYPE_CODES = "Restrict to markets that match the type of the market (i.e., MATCH_ODDS, HALF_TIME_SCORE). You should use this instead of relying on the market name as the market type codes are the same in all locales. Please note: All marketTypes are available via the listMarketTypes operations.";
    public static final String MARKET_START_TIME = "Restrict to markets with a market start time before or after the specified date.";
    public static final String WITH_ORDERS = "Restrict to markets that have one or more orders in these status.";
    public static final String RACE_TYPES = "Restrict by race type (i.e. Hurdle, Flat, Bumper, Harness, Chase, Steeple (AUS/NZ races only).";
    public static final String MARKET_PROJECTION = "The type and amount of data returned about the market.";
    public static final String MARKET_SORT = "The order of the results. Will default to RANK if not passed. RANK is an assigned priority that is determined by our Market Operations team in our back-end system. A result's overall rank is derived from the ranking given to the flowing attributes for the result. EventType, Competition, StartTime, MarketType, MarketId. For example, EventType is ranked by the most popular sports types and marketTypes are ranked in the following order: ODDS ASIAN LINE RANGE If all other dimensions of the result are equal, then the results are ranked in MarketId order.";
    public static final String MAX_RESULTS = "Limit on the total number of results returned, must be greater than 0 and less than or equal to 1000.";
    public static final String LOCALE = "The language used for the response. If not specified, the default is returned.";

    /**
     * Race Status API
     */
    public static final String LIST_RACE_DETAILS = """
            The listRaceDetails operation is provided to allow customers to establish the status of a horse or greyhound race market both prior to and after the start of the race.
            This information is available for UK, Ireland and South African races only and is provided for information purposes only.
            We cannot provide any guarantees regarding the timeliness of the data vs live /broadcasted pictures.""";

    /**
     * Heartbeat API
     */
    public static final String HEARTBEAT = """
            This heartbeat operation is provided to help customers have their positions managed automatically in the event of their API clients losing connectivity with the Betfair API.
            If a heartbeat request is not received within a prescribed time period, then Betfair will attempt to cancel all 'LIMIT' type bets for the given customer on the given exchange.
            There is no guarantee that this service will result in all bets being cancelled as there are a number of circumstances where bets are unable to be cancelled. 
            Manual intervention is strongly advised in the event of a loss of connectivity to ensure that positions are correctly managed. 
            If this service becomes unavailable for any reason, then your heartbeat will be unregistered automatically to avoid bets being inadvertently cancelled upon resumption of service.
            You should manage your position manually until the service is resumed. 
            Heartbeat data may also be lost in the unlikely event of nodes failing within the cluster, which may result in your position not being managed until a subsequent heartbeat request is received.""";

    /**
     * Authentication API
     */
    public static final String LOGIN = "Authenticates a new session";

    /**
     * Request
     */
    public static final String BUILD_MARKET_FILTER = "Creates a market-filter. " + MARKET_FILTER;
    public static final String CLEAR_MARKET_FILTER = "Clears the market-filter. " + MARKET_FILTER;

    private Descriptions() {}
}
