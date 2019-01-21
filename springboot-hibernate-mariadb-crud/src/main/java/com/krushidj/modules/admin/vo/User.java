package com.krushidj.modules.admin.vo;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)

    private long id;
    @Type(type = "string")
    @Column(name = "fname", nullable = true)
    private String fName;
    @Type(type = "string")
    @Column(name = "lname", nullable = true)
    private String lName;
    @Type(type = "string")
    @Column(name = "email", nullable = true)
    private String email;
    @Type(type = "string")
    @Column(name = "mobileNumber", nullable = true)
    private String mobileNumber;
    @Type(type = "int")
    @Column(name = "active", nullable = true)
    private int active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", active=" + active +
                '}';
    }
}
