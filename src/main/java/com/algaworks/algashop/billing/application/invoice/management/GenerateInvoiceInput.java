package com.algaworks.algashop.billing.application.invoice.management;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateInvoiceInput{
    @NotBlank
    private String orderId;

    @NotNull
    private UUID customerId;

    @NotNull
    @Valid
    private PaymentSettingsInput paymentSettings;

    @NotNull
    @Valid
    private PayerData payer;

    @NotEmpty
    @Valid
    private Set<LineItemInput> items;
}