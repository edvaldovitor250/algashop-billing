package com.algaworks.algashop.billing.domain.model;

public class DomainEntityNotFoundException extends DomainException {

    public DomainEntityNotFoundException() {
    }

    public DomainEntityNotFoundException(String message) {
        super(message);
    }

    public DomainEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
