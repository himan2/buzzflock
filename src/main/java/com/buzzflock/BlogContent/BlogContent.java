package com.buzzflock.BlogContent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class BlogContent 
{	
	public BlogContent()
	{
		
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long ContentID;
	
	private String BlogID;
	private String TimeStamp;
	@Lob
	private String Value;
	@Lob
	private String LikeList;
	
	
	

	
	public long getContentID() {
		return ContentID;
	}
	public void setContentID(long contentID) {
		ContentID = contentID;
	}
	public String getBlogID() {
		return BlogID;
	}
	public void setBlogID(String blogID) {
		BlogID = blogID;
	}
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getLikeList() {
		return LikeList;
	}
	public void setLikeList(String likeList) {
		LikeList = likeList;
	}
	
	
	
}