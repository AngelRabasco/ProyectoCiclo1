package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IUser;

public class User implements IUser{
	private Integer ID;
	private String name;
	
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