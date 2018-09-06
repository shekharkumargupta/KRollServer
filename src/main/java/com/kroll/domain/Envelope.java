package com.kroll.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Envelope implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senderLoginId;
	private String receiverLoginId;
	private Date sentAt;
	private int status;

	private int messageType;

	private CustomerOrder customerOrder;

	public String getSenderLoginId() {
		return senderLoginId;
	}

	public void setSenderLoginId(String senderLoginId) {
		this.senderLoginId = senderLoginId;
	}

	public String getReceiverLoginId() {
		return receiverLoginId;
	}

	public void setReceiverLoginId(String receiverLoginId) {
		this.receiverLoginId = receiverLoginId;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Envelope [senderLoginId=" + senderLoginId + ", sentAt=" + sentAt + ", status=" + status
				+ ", messageType=" + messageType + ", customerOrder=" + customerOrder + "]";
	}


	
	
}
