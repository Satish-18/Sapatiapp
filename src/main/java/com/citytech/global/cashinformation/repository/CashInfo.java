package com.citytech.global.cashinformation.repository;
import com.citytech.global.common.enumeration.TransactionStatus;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
//import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Entity
@Introspected
@Serdeable.Serializable
@Table(name="cash_info")
public class CashInfo {
    @Id
    @GeneratedValue
    private int cashId;
    private int borrowerId;
    private int lenderId;
    private int requestedAmount;
    @Nullable
    private int borrowerAmount;
    @Nullable
    private int lenderAmount;

    private int previousPurse;
    @Enumerated(EnumType.STRING)
    private TransactionStatus paymentStatus;


    public CashInfo(int cashId, int borrowerId, int lenderId, int requestedAmount, @Nullable int borrowerAmount, @Nullable int lenderAmount,TransactionStatus paymentStatus, int previousPurse) {
        this.cashId = cashId;
        this.borrowerId = borrowerId;
        this.lenderId = lenderId;
        this.requestedAmount = requestedAmount;
        this.borrowerAmount = borrowerAmount;
        this.lenderAmount = lenderAmount;
        this.paymentStatus = paymentStatus;
        this.previousPurse = previousPurse;
    }

    public int getPreviousPurse() {
        return previousPurse;
    }

    public void setPreviousPurse(int previousPurse) {
        this.previousPurse = previousPurse;
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
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

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
    @Nullable
    public int getBorrowerAmount() {
        return borrowerAmount;
    }
    @Nullable
    public void setBorrowerAmount(int borrowerAmount) {
        this.borrowerAmount = borrowerAmount;
    }
    @Nullable
    public int getLenderAmount() {
        return lenderAmount;
    }
    @Nullable
    public void setLenderAmount(int lenderAmount) {
        this.lenderAmount = lenderAmount;
    }

    public TransactionStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(TransactionStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    @Override
    public String toString() {
        return "CashInfo{" +
                "cashId=" + cashId +
                ", borrowerId=" + borrowerId +
                ", lenderId=" + lenderId +
                ", requestedAmount=" + requestedAmount +
                ", borrowerAmount=" + borrowerAmount +
                ", lenderAmount=" + lenderAmount +
                "paymentStatus=" + paymentStatus +
                ",previousPurse = " + previousPurse +
                '}';
    }

    public CashInfo() {
    }
}
