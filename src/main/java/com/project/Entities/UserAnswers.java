/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "user_answers", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAnswers.findAll", query = "SELECT u FROM UserAnswers u"),
    @NamedQuery(name = "UserAnswers.findByUserID", query = "SELECT u FROM UserAnswers u WHERE u.userAnswersPK.userID = :userID"),
    @NamedQuery(name = "UserAnswers.findByQuestionID", query = "SELECT u FROM UserAnswers u WHERE u.userAnswersPK.questionID = :questionID"),
    @NamedQuery(name = "UserAnswers.findByAnsweredOption", query = "SELECT u FROM UserAnswers u WHERE u.answeredOption = :answeredOption"),
    @NamedQuery(name = "UserAnswers.findByCreatedDate", query = "SELECT u FROM UserAnswers u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "UserAnswers.findByModifiedDate", query = "SELECT u FROM UserAnswers u WHERE u.modifiedDate = :modifiedDate")})
public class UserAnswers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAnswersPK userAnswersPK;
    @Size(max = 20)
    @Column(name = "Answered_Option", length = 20)
    private String answeredOption;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Created_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Modified_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public UserAnswers() {
    }

    public UserAnswers(UserAnswersPK userAnswersPK) {
        this.userAnswersPK = userAnswersPK;
    }

    public UserAnswers(UserAnswersPK userAnswersPK, Date createdDate, Date modifiedDate) {
        this.userAnswersPK = userAnswersPK;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public UserAnswers(int userID, int questionID) {
        this.userAnswersPK = new UserAnswersPK(userID, questionID);
    }

    public UserAnswersPK getUserAnswersPK() {
        return userAnswersPK;
    }

    public void setUserAnswersPK(UserAnswersPK userAnswersPK) {
        this.userAnswersPK = userAnswersPK;
    }

    public String getAnsweredOption() {
        return answeredOption;
    }

    public void setAnsweredOption(String answeredOption) {
        this.answeredOption = answeredOption;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAnswersPK != null ? userAnswersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswers)) {
            return false;
        }
        UserAnswers other = (UserAnswers) object;
        if ((this.userAnswersPK == null && other.userAnswersPK != null) || (this.userAnswersPK != null && !this.userAnswersPK.equals(other.userAnswersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.UserAnswers[ userAnswersPK=" + userAnswersPK + " ]";
    }
    
}
