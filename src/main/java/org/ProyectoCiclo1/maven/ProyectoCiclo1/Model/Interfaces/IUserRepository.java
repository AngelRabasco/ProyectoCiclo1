package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import java.util.List;

public interface IUserRepository {
	
	public boolean addUser(IUser user);		//Returns 0 if failed or 1 if successful
	public boolean removeUser(IUser user);	//Returns 0 if failed or 1 if successful
	
	public List<IUser> searchByName();
	
	public void save();
	public void load();
	
}