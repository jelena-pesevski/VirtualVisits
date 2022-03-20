package org.unibl.etf.virtualbank.models.entities;

import lombok.*;
import org.unibl.etf.virtualbank.models.enums.CreditCardType;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer accoutId;
    private String firstname;
    private String lastname;
    @Basic
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "credit_card_type", nullable = false)
    private CreditCardType creditCardType;
    private Double amount;
    private String pin;
    @Basic
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Basic
    @Column(name = "can_pay", nullable = false)
    private Boolean canPay;

}
