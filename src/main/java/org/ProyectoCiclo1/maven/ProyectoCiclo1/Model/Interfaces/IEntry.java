package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import java.time.LocalDateTime;

public interface IEntry {
	
	public Integer getID();
	public String getName();
	public String getDescription();
	public ISubject getSubject();
	public LocalDateTime getCreationDate();
	public LocalDateTime getLastEdited();
	public void setID(Integer ID);
	public void setName(String name);
	public void setDescription(String description);
	public void setSubject(Subject subject);
	public void setCreationDate(LocalDateTime date);
	public void setLastEdited(LocalDateTime date);
}
