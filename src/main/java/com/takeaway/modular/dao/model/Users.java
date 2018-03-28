package com.takeaway.modular.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户信息
 * 
 * @author hk
 *
 */
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String salt;
	private String hashedPassword;
	private String md5Password;
	private Integer role;
	private Integer jailId;
	private Date createdAt;
	private Date updatedAt;
	private Integer sysFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getMd5Password() {
		return md5Password;
	}

	public void setMd5Password(String md5Password) {
		this.md5Password = md5Password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getJailId() {
		return jailId;
	}

	public void setJailId(Integer jailId) {
		this.jailId = jailId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(Integer sysFlag) {
		this.sysFlag = sysFlag;
	}
}
