package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

public interface IContent{
	
	public Integer getID();
	public Entries getEntry();
	public Integer getType();	//Returns 0 (Image) or 1 (Link)
	public String getValue();	//Returns an image stored on the database as Base64 or the URL
	
}