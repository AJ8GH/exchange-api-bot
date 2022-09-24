package io.github.aj8gh.bot.domain.betting.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class APINGException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String errorDetails;
  private final String requestUUID;

  public APINGException(ErrorCode errorCode, String errorDetails, String requestUUID) {
    this.errorCode = errorCode;
    this.errorDetails = errorDetails;
    this.requestUUID = requestUUID;
  }

  public APINGException(String message, ErrorCode errorCode, String errorDetails,
      String requestUUID) {
    super(message);
    this.errorCode = errorCode;
    this.errorDetails = errorDetails;
    this.requestUUID = requestUUID;
  }

  @Getter
  @RequiredArgsConstructor
  public enum ErrorCode {
    TOO_MUCH_DATA(
        "The operation requested too much data, exceeding the Market Data Request Limits. You must adjust your request parameters to stay with the documented limits."),
    INVALID_INPUT_DATA(
        "The data input is invalid. A specific description is returned via errorDetails as shown below."),
    INVALID_SESSION_INFORMATION(
        "The session token hasn't been provided, is invalid or has expired. Login again to create a new session"),
    NO_APP_KEY("An application key header ('X-Application') has not been provided in the request."),
    NO_SESSION("A session token header ('X-Authentication') has not been provided in the request"),
    UNEXPECTED_ERROR(
        "An unexpected internal error occurred that prevented successful request processing."),
    INVALID_APP_KEY("The application key passed is invalid or is not present"),
    TOO_MANY_REQUESTS(
        "There are too many pending (in-flght) requests e.g. a listMarketBook with Order/Match projections is limited to 3 concurrent requests. The error also applies to: listCurrentOrders, listMarketProfitAndLoss and listClearedOrders if you have 3 or more requests currently in execution. placeOrders, cancelOrders. updateOrders, replaceOrders if the number of transactions submitted exceeds 1000 in a single second."),
    SERVICE_BUSY("The service is currently too busy to service this request."),
    TIMEOUT_ERROR(
        "The Internal call to downstream service timed out. Please note: If a TIMEOUT error occurs on a placeOrders/replaceOrders request, you should check listCurrentOrders to verify the status of your bets before placing further orders. Please Note: Timeouts will occur after 5 seconds of attempting to process the bet but please allow up to 15 seconds for a timed out order to appear. After this time any unprocessed bets will automatically be Lapsed and no longer be available on the Exchange."),
    REQUEST_SIZE_EXCEEDS_LIMIT(
        "The request exceeds the request size limit. Requests are limited to a total of 250 betId’s/marketId’s (or a combination of both)."),
    ACCESS_DENIED(
        "The calling client is not permitted to perform the specific action e.g. they have an App Key restriction in place or attempting to place a bet from a restricted jurisdiction.");

    private final String message;
  }

  @Getter
  @RequiredArgsConstructor
  public enum ErrorDetails {
    INVALID_MARKET_ID("market id passed is invalid"),
    INVALID_LOCALE("locale must use valid iso-639 locale names"),
    INVALID_CURRENCY("currency must use valid iso2 currency code name"),
    INVALID_COUNTRY_CODE("country code must use valid iso2 country code name"),
    INVALID_TEXT_QUERY("text query has invalid content"),
    INVALID_LANGUAGE("language must use valid iso language name");

    private final String value;
  }
}
