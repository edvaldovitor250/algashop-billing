package com.algaworks.algashop.billing.domain.model.integration;

import com.algaworks.algashop.billing.domain.model.DomainException;

public class BadGatewayException extends DomainException {

    public BadGatewayException() {
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
