package mpp.business.model;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	private boolean credential;
	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean isCredential() {
		return credential;
	}

	public void setCredential(boolean credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return super.getFirstName() + " " + super.getLastName();
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
