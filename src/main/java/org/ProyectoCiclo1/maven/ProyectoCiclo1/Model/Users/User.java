package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IUser;

public class User implements IUser {
	protected Integer ID;
	protected String name;
	protected String password;
	
	public User() {
		this(-1,"","");
	}
	public User(Integer ID, String name, String password) {
		this.ID=ID;
		this.name=name;
		this.password=password;
	}
	public User(String name) {
		this.name=name;
	}
	
	public Integer getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + "]";
	}
}
