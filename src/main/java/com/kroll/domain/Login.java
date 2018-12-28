/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kroll.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.kroll.constants.AppEnum;

/**
 * 
 * @author Ramesh
 */
@Entity
@XmlRootElement
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue
	private long id;
	private String loginId;
	private String password;
	private boolean active;
	private AppEnum.UserType userType;
	@OneToOne(cascade = CascadeType.ALL)
	private Person person;

	@ManyToOne
	private Company company;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public AppEnum.UserType getUserType() {
		return userType;
	}

	public void setUserType(AppEnum.UserType userType) {
		this.userType = userType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", loginId=" + loginId + ", password=" + password + ", active=" + active
				+ ", userType=" + userType + ", person=" + person + ", company=" + company + "]";
	}

}
