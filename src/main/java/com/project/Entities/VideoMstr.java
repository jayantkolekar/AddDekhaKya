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
@Table(name = "video_mstr", catalog = "add_dekha_kya", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoMstr.findAll", query = "SELECT v FROM VideoMstr v"),
    @NamedQuery(name = "VideoMstr.findByVideoID", query = "SELECT v FROM VideoMstr v WHERE v.videoID = :videoID"),
    @NamedQuery(name = "VideoMstr.findByVideoName", query = "SELECT v FROM VideoMstr v WHERE v.videoName = :videoName"),
    @NamedQuery(name = "VideoMstr.findByVideoPath", query = "SELECT v FROM VideoMstr v WHERE v.videoPath = :videoPath"),
    @NamedQuery(name = "VideoMstr.findByCreatedDate", query = "SELECT v FROM VideoMstr v WHERE v.createdDate = :createdDate"),
    @NamedQuery(name = "VideoMstr.findByModifiedDate", query = "SELECT v FROM VideoMstr v WHERE v.modifiedDate = :modifiedDate")})
public class VideoMstr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Video_ID", nullable = false)
    private Integer videoID;
    @Size(max = 500)
    @Column(name = "Video_Name", length = 500)
    private String videoName;
    @Size(max = 4000)
    @Column(name = "Video_Path", length = 4000)
    private String videoPath;
    @Column(name = "Created_Date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "Modified_Date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    public VideoMstr() {
    }

    public VideoMstr(Integer videoID) {
        this.videoID = videoID;
    }

    public Integer getVideoID() {
        return videoID;
    }

    public void setVideoID(Integer videoID) {
        this.videoID = videoID;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
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
        hash += (videoID != null ? videoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoMstr)) {
            return false;
        }
        VideoMstr other = (VideoMstr) object;
        if ((this.videoID == null && other.videoID != null) || (this.videoID != null && !this.videoID.equals(other.videoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.Entities.VideoMstr[ videoID=" + videoID + " ]";
    }
    
}
