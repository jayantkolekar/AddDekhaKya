/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jayant Administrator
 */
@Entity
@Table(name = "option_mstr", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionMstr.findAll", query = "SELECT o FROM OptionMstr o"),
    @NamedQuery(name = "OptionMstr.findByQuestionID", query = "SELECT o FROM OptionMstr o WHERE o.optionMstrPK.questionID = :questionID"),
    @NamedQuery(name = "OptionMstr.findByOptionID", query = "SELECT o FROM OptionMstr o WHERE o.optionMstrPK.optionID = :optionID"),
    @NamedQuery(name = "OptionMstr.findByQuestionOptions", query = "SELECT o FROM OptionMstr o WHERE o.questionOptions = :questionOptions"),
    @NamedQuery(name = "OptionMstr.findByCreatedDate", query = "SELECT o FROM OptionMstr o WHERE o.createdDate = :createdDate"),
    @NamedQuery(name = "OptionMstr.findByModifiedDate", query = "SELECT o FROM OptionMstr o WHERE o.modifiedDate = :modifiedDate")})
public class OptionMstr implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OptionMstrPK optionMstrPK;
    @Size(max = 1000)
    @Column(name = "Question_Options", length = 1000)
    private String questionOptions;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    public OptionMstr() {
    }

    public OptionMstr(OptionMstrPK optionMstrPK) {
        this.optionMstrPK = optionMstrPK;
    }

    public OptionMstr(int questionID, int optionID) {
        this.optionMstrPK = new OptionMstrPK(questionID, optionID);
    }

    public OptionMstrPK getOptionMstrPK() {
        return optionMstrPK;
    }

    public void setOptionMstrPK(OptionMstrPK optionMstrPK) {
        this.optionMstrPK = optionMstrPK;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = questionOptions;
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
        hash += (optionMstrPK != null ? optionMstrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionMstr)) {
            return false;
        }
        OptionMstr other = (OptionMstr) object;
        if ((this.optionMstrPK == null && other.optionMstrPK != null) || (this.optionMstrPK != null && !this.optionMstrPK.equals(other.optionMstrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.OptionMstr[ optionMstrPK=" + optionMstrPK + " ]";
    }
    
}
