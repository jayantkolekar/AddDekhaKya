/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jayant Administrator
 */
@Entity
@Table(name = "user_mstr", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserMstr.findAll", query = "SELECT u FROM UserMstr u"),
    @NamedQuery(name = "UserMstr.findByUserID", query = "SELECT u FROM UserMstr u WHERE u.userID = :userID"),
    @NamedQuery(name = "UserMstr.findByUserPassword", query = "SELECT u FROM UserMstr u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "UserMstr.findByFirstName", query = "SELECT u FROM UserMstr u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserMstr.findByLastName", query = "SELECT u FROM UserMstr u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserMstr.findByEmailID", query = "SELECT u FROM UserMstr u WHERE u.emailID = :emailID"),
    @NamedQuery(name = "UserMstr.findByExperienceLevel", query = "SELECT u FROM UserMstr u WHERE u.experienceLevel = :experienceLevel"),
    @NamedQuery(name = "UserMstr.findByDateOfBirth", query = "SELECT u FROM UserMstr u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "UserMstr.findByPhoneNumber", query = "SELECT u FROM UserMstr u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "UserMstr.findByCreatedDate", query = "SELECT u FROM UserMstr u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "UserMstr.findByModifiedDate", query = "SELECT u FROM UserMstr u WHERE u.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "UserMstr.findByMarksObtained", query = "SELECT u FROM UserMstr u WHERE u.marksObtained = :marksObtained"),
    @NamedQuery(name = "UserMstr.findByPassResult", query = "SELECT u FROM UserMstr u WHERE u.passResult = :passResult"),
    @NamedQuery(name = "UserMstr.findByEnabled", query = "SELECT u FROM UserMstr u WHERE u.enabled = :enabled")})
public class UserMstr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "User_ID", nullable = false)
    private Integer userID;
    @Size(max = 100)
    @Column(name = "User_Password", length = 100)
    private String userPassword;
    @Size(max = 200)
    @Column(name = "First_Name", length = 200)
    private String firstName;
    @Size(max = 200)
    @Column(name = "Last_Name", length = 200)
    private String lastName;
    @Size(max = 300)
    @Column(name = "Email_ID", length = 300)
    private String emailID;
    @Column(name = "Experience_Level")
    private Integer experienceLevel;
    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 20)
    @Column(name = "Phone_Number", length = 20)
    private String phoneNumber;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Marks_Obtained", precision = 22)
    private Double marksObtained;
    @Column(name = "Pass_Result")
    private Character passResult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    @JoinColumn(name = "Role_ID", referencedColumnName = "Role_ID")
    @ManyToOne
    private RoleMstr roleID;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private Set<RoleMstr> roles = new HashSet<RoleMstr>();
    
    private String login;

    public UserMstr() {
    }

    public UserMstr(Integer userID) {
        this.userID = userID;
    }

    public UserMstr(Integer userID, boolean enabled) {
        this.userID = userID;
        this.enabled = enabled;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Character getPassResult() {
        return passResult;
    }

    public void setPassResult(Character passResult) {
        this.passResult = passResult;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RoleMstr getRoleID() {
        return roleID;
    }

    public void setRoleID(RoleMstr roleID) {
        this.roleID = roleID;
    }

    public Set<RoleMstr> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleMstr> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserMstr)) {
            return false;
        }
        UserMstr other = (UserMstr) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.UserMstr[ userID=" + userID + " ]";
    }
    
}
