package org.unibl.etf.virtualbank.models;

import org.unibl.etf.virtualbank.models.enums.CreditCardType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Account {
    private String firstname;
    private String lastname;
    private String creditCardNumber;
    private CreditCardType creditCardType;
    private String pin;
    private LocalDate expirationDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return firstname.equals(account.firstname) && lastname.equals(account.lastname) && creditCardNumber.equals(account.creditCardNumber) && creditCardType == account.creditCardType && pin.equals(account.pin) && expirationDate.equals(account.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, creditCardNumber, creditCardType, pin, expirationDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardType=" + creditCardType +
                ", pin='" + pin + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
