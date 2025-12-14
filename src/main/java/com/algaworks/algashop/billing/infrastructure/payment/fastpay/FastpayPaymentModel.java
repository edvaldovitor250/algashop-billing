package com.algaworks.algashop.billing.infrastructure.payment.fastpay;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FastpayPaymentModel {
    private String id;
    private String referenceCode;
    private String status;
    private String method;
    private BigDecimal totalAmount;

}
