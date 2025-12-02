package com.algaworks.algashop.billing.domain.model.creditcard;

import java.util.UUID;

import jdk.jfr.internal.Options;

public interface CreditCardProviderService {
    LimitedCreditCard register(UUID customerId, String tokenizedCard);
    Options<LimitedCreditCard> findById(String providerCreditCardCode);
    void delete(String gatewayCode)
}
