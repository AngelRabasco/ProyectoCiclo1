package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import java.util.List;

public interface ISubject {
	
	public Integer getID();
	public String getName();
	public List<IEntry> getEntries();
	public void setName(String name);
	
}
