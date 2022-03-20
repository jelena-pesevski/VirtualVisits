package org.unibl.etf.virtualbank.models.requests;

import lombok.*;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String creditCardNumber;
    @NotNull
    private CreditCardType creditCardType;
    @NotBlank
    private String pin;
    @NotNull
    private LocalDate expirationDate;
    @NotNull
    private Double cashAmount;

}
