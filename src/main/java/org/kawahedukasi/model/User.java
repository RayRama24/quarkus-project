package org.kawahedukasi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;
import org.kawahedukasi.model.base.UpdateBase;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "user")
public class User extends UpdateBase {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    @Column(name = "email" , nullable = false, length = 100)
    private String email;

    @Column(name = "work_phone_number", nullable = false, length = 20)
    private String workPhoneNumber;

    @Column(name = "mobile_phone_number", nullable = false, length = 20)
    private String mobilePhoneNumber;

    @Column(name = "login_name" , nullable = false)
    private String loginName;

    @Column(name = "password" , nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "address" , columnDefinition = "text")
    private String address;

    public User() {
        super();
    }

    public static Boolean isEmptyByLoginName(String loginName){
        return User.find("login_name = ?1", loginName).firstResultOptional().isEmpty();
    }
    public static Optional<User> findbyLoginName(String loginName){
        return User.find("login_name = ?1", loginName).firstResultOptional();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
