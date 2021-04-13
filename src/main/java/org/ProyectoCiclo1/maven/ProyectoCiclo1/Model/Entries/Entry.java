package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IEntry;
import java.time.LocalDateTime;

public class Entry implements IEntry {
	private Integer ID;
	private String name;
	private LocalDateTime creationDate;
	
	public Entry() {
		
	}
	public Entry(Integer ID, String name) {
		this.ID=ID;
		this.name=name;
		this.creationDate=LocalDateTime.now();
	}
	
	public Integer getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}
	public void setName(String name) {
		this.name=name;
	}
}