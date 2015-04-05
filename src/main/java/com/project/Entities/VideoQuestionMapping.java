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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jayant Administrator
 */
@Entity
@Table(name = "video_question_mapping", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoQuestionMapping.findAll", query = "SELECT v FROM VideoQuestionMapping v"),
    @NamedQuery(name = "VideoQuestionMapping.findByVideoID", query = "SELECT v FROM VideoQuestionMapping v WHERE v.videoQuestionMappingPK.videoID = :videoID"),
    @NamedQuery(name = "VideoQuestionMapping.findByQuestionID", query = "SELECT v FROM VideoQuestionMapping v WHERE v.videoQuestionMappingPK.questionID = :questionID"),
    @NamedQuery(name = "VideoQuestionMapping.findByUserID", query = "SELECT v FROM VideoQuestionMapping v WHERE v.videoQuestionMappingPK.userID = :userID"),
    @NamedQuery(name = "VideoQuestionMapping.findByCreatedDate", query = "SELECT v FROM VideoQuestionMapping v WHERE v.createdDate = :createdDate"),
    @NamedQuery(name = "VideoQuestionMapping.findByModifiedDate", query = "SELECT v FROM VideoQuestionMapping v WHERE v.modifiedDate = :modifiedDate")})
public class VideoQuestionMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VideoQuestionMappingPK videoQuestionMappingPK;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    public VideoQuestionMapping() {
    }

    public VideoQuestionMapping(VideoQuestionMappingPK videoQuestionMappingPK) {
        this.videoQuestionMappingPK = videoQuestionMappingPK;
    }

    public VideoQuestionMapping(int videoID, int questionID, int userID) {
        this.videoQuestionMappingPK = new VideoQuestionMappingPK(videoID, questionID, userID);
    }

    public VideoQuestionMappingPK getVideoQuestionMappingPK() {
        return videoQuestionMappingPK;
    }

    public void setVideoQuestionMappingPK(VideoQuestionMappingPK videoQuestionMappingPK) {
        this.videoQuestionMappingPK = videoQuestionMappingPK;
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
        hash += (videoQuestionMappingPK != null ? videoQuestionMappingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoQuestionMapping)) {
            return false;
        }
        VideoQuestionMapping other = (VideoQuestionMapping) object;
        if ((this.videoQuestionMappingPK == null && other.videoQuestionMappingPK != null) || (this.videoQuestionMappingPK != null && !this.videoQuestionMappingPK.equals(other.videoQuestionMappingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.VideoQuestionMapping[ videoQuestionMappingPK=" + videoQuestionMappingPK + " ]";
    }
    
}
