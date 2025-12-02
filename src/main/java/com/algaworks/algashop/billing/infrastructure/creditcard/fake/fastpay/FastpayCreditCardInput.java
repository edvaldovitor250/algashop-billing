package com.algaworks.algashop.billing.infrastructure.creditcard.fake.fastpay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FastpayCreditCardInput {
    private String tokenizedCard;
    private String customerCode;
}
