/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jayant Administrator
 */
@Embeddable
public class UserAnswersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "User_ID", nullable = false)
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Question_ID", nullable = false)
    private int questionID;

    public UserAnswersPK() {
    }

    public UserAnswersPK(int userID, int questionID) {
        this.userID = userID;
        this.questionID = questionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (int) questionID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswersPK)) {
            return false;
        }
        UserAnswersPK other = (UserAnswersPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.questionID != other.questionID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.UserAnswersPK[ userID=" + userID + ", questionID=" + questionID + " ]";
    }
    
}
