package io.github.aj8gh.bot.http.operations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountOperations {
  CREATE_DEVELOPER_APP_KEYS("createDeveloperAppKeys"),
  GET_DEVELOPER_APP_KEYS("getDeveloperAppKeys"),
  GET_ACCOUNT_FUNDS("getAccountFunds"),
  TRANSFER_FUNDS("transferFunds"),
  GET_ACCOUNT_DETAILS("getAccountDetails"),
  GET_VENDOR_CLIENT_ID("getVendorClientId"),
  GET_APPLICATION_SUBSCRIPTION_TOKEN("getApplicationSubscriptionToken"),
  ACTIVATE_APPLICATION_SUBSCRIPTION("activateApplicationSubscription"),
  CANCEL_APPLICATION_SUBSCRIPTION("cancelApplicationSubscription"),
  UPDATE_APPLICATION_SUBSCRIPTION("updateApplicationSubscription"),
  LIST_APPLICATION_SUBSCRIPTION("listApplicationSubscriptionTokens"),
  LIST_ACCOUNT_SUBSCRIPTION("listAccountSubscriptionTokens"),
  GET_APPLICATION_SUBSCRIPTION_HISTORY("getApplicationSubscriptionHistory"),
  GET_ACCOUNT_STATEMENT("getAccountStatement"),
  LIST_CURRENCY_RATES("listCurrencyRates"),
  TOKEN("token"),
  GET_VENDOR_DETAILS("getVendorDetails"),
  REVOKE_ACCESS_TO_WEB_APP("revokeAccessToWebApp"),
  LIST_AUTHORIZED_WEB_APPS("listAuthorizedWebApps"),
  IS_ACCOUNT_SUBSCRIBED_TO_WEB_APP("isAccountSubscribedToWebApp"),
  GET_AFFILIATE_RELATION("getAffiliateRelation");

  private final String value;
}
