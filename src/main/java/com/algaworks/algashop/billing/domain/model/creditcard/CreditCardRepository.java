package com.algaworks.algashop.billing.domain.model.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import jdk.jfr.internal.Options;
import java.util.List;


public interface CreditCardRepository  extends JpaRepository<CreditCard, UUID> {

    Opitinal<CreditCard> findByCustomerIdAndId(UUID customerId);
}
