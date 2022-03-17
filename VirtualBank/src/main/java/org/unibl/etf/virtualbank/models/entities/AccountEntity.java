package org.unibl.etf.virtualbank.models.entities;

import org.unibl.etf.virtualbank.models.enums.CreditCardType;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer accoutId;
    private String firstname;
    private String lastname;
    private String creditCardNumber;
    private CreditCardType creditCardType;
    private Double amount;
    private String pin;
    private LocalDate expirationDate;
    private Boolean canPay;

    public Integer getAccoutId() {
        return accoutId;
    }

    public void setAccoutId(Integer accoutId) {
        this.accoutId = accoutId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "credit_card_number", nullable = false)
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "credit_card_type", nullable = false)
    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Basic
    @Column(name = "expiration_date", nullable = false)
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Basic
    @Column(name = "can_pay", nullable = false)
    public Boolean getCanPay() {
        return canPay;
    }

    public void setCanPay(Boolean canPay) {
        this.canPay = canPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return firstname.equals(that.firstname) && lastname.equals(that.lastname) && creditCardNumber.equals(that.creditCardNumber) && creditCardType == that.creditCardType && pin.equals(that.pin) && expirationDate.equals(that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, creditCardNumber, creditCardType, pin, expirationDate);
    }
}
