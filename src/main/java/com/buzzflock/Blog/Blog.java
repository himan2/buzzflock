package com.buzzflock.Blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity 
public class Blog 
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long BlogID;
	private String OwnerID;
	private String Timestamp;
	private String Status;
	private String Description;
	private String Image;
	private String Topicname;
	@Transient
	private MultipartFile ProductFile;

	public MultipartFile getProductFile() {
		return ProductFile;
	}

	public void setProductFile(MultipartFile productFile) {
		ProductFile = productFile;
	}

	public String getTopicname() {
		return Topicname;
	}

	public void setTopicname(String topicname) {
		Topicname = topicname;
	}

	public Blog()
	{
		
	}
	
	public Long getBlogID() {
		return BlogID;
	}
	public void setBlogID(Long blogID) {
		BlogID = blogID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Long getID() {
		return BlogID;
	}
	public void setID(Long iD) {
		BlogID = iD;
	}
	public String getOwnerID() {
		return OwnerID;
	}
	public void setOwnerID(String ownerID) {
		OwnerID = ownerID;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}