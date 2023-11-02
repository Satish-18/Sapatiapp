package com.citytech.global.user.repository.dto;

public class BorrowerDto {
    private int uid;
    private String firstName;
    private String lastName;
    private String userType;

    public BorrowerDto(int uid, String firstName, String lastName, String userType) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "BorrowerDto{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public BorrowerDto() {
    }
}
