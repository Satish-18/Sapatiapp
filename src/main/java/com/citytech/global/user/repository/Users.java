package com.citytech.global.user.repository;
import com.citytech.global.common.enumeration.TransactionStatus;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.serde.annotation.Serdeable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Introspected
@Serdeable.Serializable
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private int uid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;
    @Enumerated(EnumType.STRING)
    private TransactionStatus userStatus;
    @Nullable
    private int borrowerAmount;
    private int lenderAmount;
    private LocalDateTime createdOn;

    public Users(int uid, String firstName, String lastName, String email, String password, String userType,TransactionStatus userStatus, @Nullable int borrowerAmount, int lenderAmount, LocalDateTime createdOn) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
        this.borrowerAmount = borrowerAmount;
        this.lenderAmount = lenderAmount;
        this.createdOn = createdOn;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public TransactionStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(TransactionStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Nullable
    public int getBorrowerAmount() {
        return borrowerAmount;
    }
    @Nullable
    public void setBorrowerAmount(int borrowerAmount) {
        this.borrowerAmount = borrowerAmount;
    }

    public int getLenderAmount() {
        return lenderAmount;
    }

    public void setLenderAmount(int lenderAmount) {
        this.lenderAmount = lenderAmount;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\''+
                ", borrowerAmount=" + borrowerAmount +
                ", lenderAmount=" + lenderAmount +
                ", createdOn=" + createdOn +
                '}';
    }

    public Users() {
    }
}
