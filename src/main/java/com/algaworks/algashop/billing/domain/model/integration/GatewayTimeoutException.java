package com.algaworks.algashop.billing.domain.model.integration;

import com.algaworks.algashop.billing.domain.model.DomainException;

public class GatewayTimeoutException extends DomainException {

    public GatewayTimeoutException() {
    }

    public GatewayTimeoutException(String message) {
        super(message);
    }

    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
