package com.algaworks.algashop.billing.domain.model.creditcard;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardProviderService {
    LimitedCreditCard register(UUID customerId, String tokenizedCard);
    Optional<LimitedCreditCard> findById(String providerCreditCardCode);
    void delete(String gatewayCode);
}
