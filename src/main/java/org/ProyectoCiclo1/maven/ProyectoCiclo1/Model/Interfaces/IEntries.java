package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import java.time.LocalDateTime;

public interface IEntries {
	
	public Integer getID();
	public String getName();
	public LocalDateTime getCreationDate();
	public void setName(String name);
	
}