package io.github.aj8gh.bot.domain.account.requests;

import io.github.aj8gh.bot.domain.account.enums.Wallet;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TransferFundsRequest {
    Wallet from;
    Wallet to;
    Double amount;
}
