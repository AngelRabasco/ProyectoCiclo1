package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.ISubject;

public class Subject implements ISubject {
	private Integer ID;
	private String name;
	
	public Subject() {
		this(-1,"");
	}
	public Subject(Integer ID, String name) {
		this.ID=ID;
		this.name=name;
	}

	public Integer getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public void setName(String name) {
		this.name=name;
	}
}
