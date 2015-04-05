/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "question_mstr", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionMstr.findAll", query = "SELECT q FROM QuestionMstr q"),
    @NamedQuery(name = "QuestionMstr.findByQuestionID", query = "SELECT q FROM QuestionMstr q WHERE q.questionID = :questionID"),
    @NamedQuery(name = "QuestionMstr.findByCorrectOption", query = "SELECT q FROM QuestionMstr q WHERE q.correctOption = :correctOption"),
    @NamedQuery(name = "QuestionMstr.findByMarks", query = "SELECT q FROM QuestionMstr q WHERE q.marks = :marks"),
    @NamedQuery(name = "QuestionMstr.findByCreatedDate", query = "SELECT q FROM QuestionMstr q WHERE q.createdDate = :createdDate"),
    @NamedQuery(name = "QuestionMstr.findByModifiedDate", query = "SELECT q FROM QuestionMstr q WHERE q.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "QuestionMstr.findByDelFlag", query = "SELECT q FROM QuestionMstr q WHERE q.delFlag = :delFlag")})
public class QuestionMstr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Question_ID", nullable = false)
    private Integer questionID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "Question", nullable = false, length = 16777215)
    private String question;
    @Size(max = 50)
    @Column(name = "Correct_Option", length = 50)
    private String correctOption;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Marks", nullable = false)
    private int marks;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Column(name = "Del_Flag")
    private Character delFlag;

    public QuestionMstr() {
    }

    public QuestionMstr(Integer questionID) {
        this.questionID = questionID;
    }

    public QuestionMstr(Integer questionID, String question, int marks) {
        this.questionID = questionID;
        this.question = question;
        this.marks = marks;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
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

    public Character getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Character delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionMstr)) {
            return false;
        }
        QuestionMstr other = (QuestionMstr) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.QuestionMstr[ questionID=" + questionID + " ]";
    }
    
}
