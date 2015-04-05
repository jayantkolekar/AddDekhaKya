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
public class VideoQuestionMappingPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Video_ID", nullable = false)
    private int videoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Question_ID", nullable = false)
    private int questionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "User_ID", nullable = false)
    private int userID;

    public VideoQuestionMappingPK() {
    }

    public VideoQuestionMappingPK(int videoID, int questionID, int userID) {
        this.videoID = videoID;
        this.questionID = questionID;
        this.userID = userID;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) videoID;
        hash += (int) questionID;
        hash += (int) userID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoQuestionMappingPK)) {
            return false;
        }
        VideoQuestionMappingPK other = (VideoQuestionMappingPK) object;
        if (this.videoID != other.videoID) {
            return false;
        }
        if (this.questionID != other.questionID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.VideoQuestionMappingPK[ videoID=" + videoID + ", questionID=" + questionID + ", userID=" + userID + " ]";
    }
    
}
