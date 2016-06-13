package com.heartmusic;

import java.io.Serializable;

public class User implements Serializable {
    private String Username;
    private String Password;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "User [ username=" + Username + ", password="
				+ Password +  "]";
	}

}
