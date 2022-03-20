package org.unibl.etf.virtualvisits.models.requests;

import lombok.*;
import org.unibl.etf.virtualvisits.models.enums.CreditCardType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyTicketRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer virtualVisitId;
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
