package com.sugar.leke.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FTPInfo {

	private String ip;
	private Integer port;
	private String userName;
	private String password;
	private String uploadPath4code;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {return password;}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUploadPath4code() {
		return uploadPath4code;
	}
	public void setUploadPath4code(String uploadPath4code) {
		this.uploadPath4code = uploadPath4code;
	}
	
	
}
