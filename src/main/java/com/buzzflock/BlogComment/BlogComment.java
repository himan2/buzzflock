package com.buzzflock.BlogComment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Generated;

@Entity
public class BlogComment 
{
	@Id@GeneratedValue(strategy=GenerationType.AUTO)	
	private long CommentID;
	private String ContentID;
	private String OwnerID;
	private String TimeStamp;
	
	@Lob
	private String CommentValue;
	@Lob
	private String LikeList;
	
	
	public long getCommentID() {
		return CommentID;
	}
	public void setCommentID(long commentID) {
		CommentID = commentID;
	}

	public String getContentID() {
		return ContentID;
	}
	public void setContentID(String contentID) {
		ContentID = contentID;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getCommentValue() {
		return CommentValue;
	}
	public void setCommentValue(String commentValue) {
		CommentValue = commentValue;
	}
	public String getLikeList() {
		return LikeList;
	}
	public void setLikeList(String likeList) {
		LikeList = likeList;
	}
	public String getOwnerID() {
		return OwnerID;
	}
	public void setOwnerID(String ownerID) {
		OwnerID = ownerID;
	}
	
}