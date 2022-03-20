package org.unibl.etf.virtualbank.models.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Basic
    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;
    @Basic
    @Column(name = "cash_amount", nullable = false)
    private Double cashAmount;
    @Basic
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

}
