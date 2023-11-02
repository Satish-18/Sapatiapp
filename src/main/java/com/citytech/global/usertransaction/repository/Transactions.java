package com.citytech.global.usertransaction.repository;
import com.citytech.global.common.enumeration.TransactionStatus;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Introspected
@Serdeable.Serializable
@Table(name="transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int borrowerId;

    private int lenderId;
    private int amount;
    private int timeInDays;
    private int interestRate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private LocalDateTime timestamp;

    public Transactions(int id, int borrowerId, int lenderId, int amount, int timeInDays, int interestRate, TransactionStatus status, LocalDateTime timestamp) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.amount = amount;
        this.timeInDays = timeInDays;
        this.interestRate = interestRate;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTimeInDays() {
        return timeInDays;
    }

    public void setTimeInDays(int timeInDays) {
        this.timeInDays = timeInDays;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", borrowerId=" + borrowerId +
                ", lenderId=" + lenderId +
                ", amount=" + amount +
                ", timeInDays=" + timeInDays +
                ", interestRate=" + interestRate +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }

    public Transactions() {
    }
}
