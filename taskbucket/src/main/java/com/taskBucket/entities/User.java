package com.taskBucket.entities;

public class User {

private String id, name, email, password, role;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public User(String id, String name, String email, String password, String role) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.role = role;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + "]";
}

public User(String id, String name, String email, String role) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.role = role;
}



}
