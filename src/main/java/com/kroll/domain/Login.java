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

@NamedQueries({
		@NamedQuery(name = "Login.verifyUser", query = "select l from Login l where l.loginId = ?1 and l.password = ?2"),
		@NamedQuery(name = "Login.findAllByUserType", query = "select l from Login l where l.company.id = ?1 and l.userType = ?2"),
		@NamedQuery(name = "Login.search", query = "Select l from Login l where l.person.fullName like ?1 or "
				+ "l.person.email like ?2 or l.person.address.city like ?3 or "
				+ "l.person.mobileNumber like ?4"),
		@NamedQuery(name = "Login.findByLoginId", query = "select l from Login l where l.loginId = ?1")
})
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2037554227962645752L;
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
