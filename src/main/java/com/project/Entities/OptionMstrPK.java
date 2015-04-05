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
public class OptionMstrPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Question_ID", nullable = false)
    private int questionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Option_ID", nullable = false)
    private int optionID;

    public OptionMstrPK() {
    }

    public OptionMstrPK(int questionID, int optionID) {
        this.questionID = questionID;
        this.optionID = optionID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) questionID;
        hash += (int) optionID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionMstrPK)) {
            return false;
        }
        OptionMstrPK other = (OptionMstrPK) object;
        if (this.questionID != other.questionID) {
            return false;
        }
        if (this.optionID != other.optionID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.OptionMstrPK[ questionID=" + questionID + ", optionID=" + optionID + " ]";
    }
    
}
