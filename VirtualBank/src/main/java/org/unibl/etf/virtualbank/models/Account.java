package org.unibl.etf.virtualbank.models;

import lombok.*;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String firstname;
    private String lastname;
    private String creditCardNumber;
    private CreditCardType creditCardType;
    private String pin;
    private LocalDate expirationDate;
}
