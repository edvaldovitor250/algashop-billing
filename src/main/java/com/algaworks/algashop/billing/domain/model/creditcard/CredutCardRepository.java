package com.algaworks.algashop.billing.domain.model.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CredutCardRepository  extends JpaRepository<CreditCard, UUID> {
}
