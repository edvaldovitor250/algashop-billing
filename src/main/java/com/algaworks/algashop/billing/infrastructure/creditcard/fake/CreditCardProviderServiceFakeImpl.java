package com.algaworks.algashop.billing.infrastructure.creditcard.fake;

import java.io.Serial;
import java.util.UUID;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import com.algaworks.algashop.billing.domain.model.creditcard.CreditCardProviderService;
import com.algaworks.algashop.billing.domain.model.creditcard.LimitedCreditCard;
import com.algaworks.algashop.billing.domain.model.creditcard.Options;

@Service
@ConditionalOnProperties(name = "algashop.integrations.payment.provider", havingValue = "FAKE")
public class CreditCardProviderServiceFakeImpl implements  CreditCardProviderService {

    @Override
    public LimitedCreditCard register(UUID customerId, String tokenizedCard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Options<LimitedCreditCard> findById(String providerCreditCardCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(String gatewayCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
