package org.unibl.etf.virtualvisits.models.requests;


import org.unibl.etf.virtualvisits.models.enums.CreditCardType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardType=" + creditCardType +
                ", pin='" + pin + '\'' +
                ", expirationDate=" + expirationDate +
                ", cashAmount=" + cashAmount +
                '}';
    }
}
