package org.kawahedukasi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.kawahedukasi.model.base.UpdateBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee extends UpdateBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "pob", nullable = false, length = 50)
    private String pob;

    @Column(name = "email" , nullable = false, length = 100)
    private String email;

    @Column(name = "mobile_phone_number", nullable = false, length = 20)
    private String mobilePhoneNumber;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(targetEntity = JobPosition.class)
    @JsonIgnore
    @JoinColumn(name = "job_position", nullable = false)
    private JobPosition jobPosition;

    @ManyToOne(targetEntity = LastEducation.class)
    @JsonIgnore
    @JoinColumn(name = "last_education", nullable = false)
    private LastEducation lastEducation;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    @JoinColumn(name = "update_by", nullable = false)
    private User updateBy;

    public Employee() {
        super();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public LastEducation getLastEducation() {
        return lastEducation;
    }

    public void setLastEducation(LastEducation lastEducation) {
        this.lastEducation = lastEducation;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }
}
