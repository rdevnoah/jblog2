package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	
	@NotEmpty
	@Length(min=2, max=12)
	private String id;
	
	@Length(min=6, max=12)
	private String password;
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	private String regDate;

	public UserVo() {}
	
	public UserVo(String id, String name, String password) {
		
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public UserVo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", password=" + password + ", name=" + name + ", regDate=" + regDate + "]";
	}

}
