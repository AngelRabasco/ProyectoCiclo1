package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IEntry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;

import java.time.LocalDateTime;

public class Entry implements IEntry {
	protected Integer ID;
	protected String name;
	protected String description;
	protected Subject subject;
	protected LocalDateTime creationDate;
	protected LocalDateTime lastEdited;
	
	
	public Entry() {
		this(-1, "", "", new Subject(), LocalDateTime.now(), LocalDateTime.now());
	}
	public Entry(Integer ID, String name, String description, Subject subject, LocalDateTime creation, LocalDateTime edited) {
		this.ID=ID;
		this.name=name;
		this.description=description;
		this.subject=subject;
		this.creationDate=creation;
		this.lastEdited=edited;
	}
	public Entry(Integer ID, String name, String description, LocalDateTime creation, LocalDateTime edited) {
		this.ID=ID;
		this.name=name;
		this.description=description;
		this.creationDate=creation;
		this.lastEdited=edited;
	}
	public Entry(String name, String description, Subject subject,LocalDateTime creation, LocalDateTime edited) {
		this.name=name;
		this.description=description;
		this.subject=subject;
		this.creationDate=creation;
		this.lastEdited=edited;
	}
	
	public Integer getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public Subject getSubject() {
		return this.subject;
	}
	public LocalDateTime getCreationDate() {
		return this.creationDate;
	}
	public LocalDateTime getLastEdited() {
		return this.lastEdited;
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setSubject(Subject subject) {
		this.subject=subject;
	}
	public void setCreationDate(LocalDateTime date) {
		this.creationDate=date;
	}
	public void setLastEdited(LocalDateTime date) {
		this.lastEdited=date;
	}
	
	public String toString() {
		return "Entry [ID=" + ID + ", name=" + name + ", description=" + description + ", subject=" + subject + ", creationDate=" + creationDate + ", lastEdited=" + lastEdited + "]";
	}
}
