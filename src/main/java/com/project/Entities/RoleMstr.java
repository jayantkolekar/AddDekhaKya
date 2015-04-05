/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jayant Administrator
 */
@Entity
@Table(name = "role_mstr", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleMstr.findAll", query = "SELECT r FROM RoleMstr r"),
    @NamedQuery(name = "RoleMstr.findByRoleID", query = "SELECT r FROM RoleMstr r WHERE r.roleID = :roleID"),
    @NamedQuery(name = "RoleMstr.findByRoleName", query = "SELECT r FROM RoleMstr r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "RoleMstr.findByCreatedDate", query = "SELECT r FROM RoleMstr r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "RoleMstr.findByModifiedDate", query = "SELECT r FROM RoleMstr r WHERE r.modifiedDate = :modifiedDate")})
public class RoleMstr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Role_ID", nullable = false)
    private Integer roleID;
    @Size(max = 50)
    @Column(name = "Role_Name", length = 50)
    private String roleName;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @OneToMany(mappedBy = "roleID")
    private Collection<UserMstr> userMstrCollection;

    public RoleMstr() {
    }

    public RoleMstr(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    @XmlTransient
    public Collection<UserMstr> getUserMstrCollection() {
        return userMstrCollection;
    }

    public void setUserMstrCollection(Collection<UserMstr> userMstrCollection) {
        this.userMstrCollection = userMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleMstr)) {
            return false;
        }
        RoleMstr other = (RoleMstr) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.RoleMstr[ roleID=" + roleID + " ]";
    }
    
}
