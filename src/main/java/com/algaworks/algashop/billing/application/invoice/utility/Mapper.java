package com.algaworks.algashop.billing.application.invoice.utility;

public interface Mapper {
    <T> T convert(Object o, Class<T> destinationClass);
}