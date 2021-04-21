package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;

public interface ISubject {
	
	public Integer getID();
	public String getName();
	public User getOwner();
	public void setID(Integer ID);
	public void setName(String name);
	public void setOwner(User owner);
	
}
