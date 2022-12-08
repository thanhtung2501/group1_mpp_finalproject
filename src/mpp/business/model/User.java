package mpp.business.model;

import java.io.Serializable;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	
	private String password;
	private Role role;
	public User(String id, String pass, Role role) {
		this.id = id;
		this.password = pass;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Role getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + role.toString() + "]";
	}
	
}
