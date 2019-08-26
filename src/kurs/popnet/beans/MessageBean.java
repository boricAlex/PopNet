package kurs.popnet.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class MessageBean {
	
	private Integer messageId;
	private Integer userId;
	private String senderName;
	private String text;
	private Date timePosted;
	private Integer likes;
	private Integer ownerId;
	
	@Override
	public String toString() {
		return "MessageBean [messageId=" + messageId + ", userId=" + userId + ", senderName=" + senderName + ", text="
				+ text + ", timePosted=" + timePosted + ", likes=" + likes + ", ownerId=" + ownerId + "]";
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(Date date) {
		this.timePosted = date;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
