package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

public interface IContent{
	
	public Integer getID();
	public IEntry getEntry();
	public Integer getType();	//Returns 0 (Image) or 1 (Link)
	public String getURL();		//Returns the URL if the content is a Link
	
}
