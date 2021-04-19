package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IUser;

public class User implements IUser {
	protected Integer ID;
	protected String name;
	
	public User() {
		this(-1,"");
	}
	public User(Integer ID, String name) {
		this.ID=ID;
		this.name=name;
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
	public void setName(String name) {
		this.name=name;
	}
}
