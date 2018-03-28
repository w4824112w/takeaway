package com.takeaway.modular.dao.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户dto
 * 
 * @author hk
 *
 */
public class UsersDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String salt;
	private String hashedPassword;
	private String md5Password;
	private String role;
	private String jailId;
	private String jailName;
	private String createdAt;
	private String updatedAt;

	private String token;
	private String prison;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJailId() {
		return jailId;
	}

	public void setJailId(String jailId) {
		this.jailId = jailId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPrison() {
		return prison;
	}

	public void setPrison(String prison) {
		this.prison = prison;
	}

	public String getJailName() {
		return jailName;
	}

	public void setJailName(String jailName) {
		this.jailName = jailName;
	}

}
