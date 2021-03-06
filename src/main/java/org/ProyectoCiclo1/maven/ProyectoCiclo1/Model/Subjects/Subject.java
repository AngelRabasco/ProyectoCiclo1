package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.ISubject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;

public class Subject implements ISubject {
	protected Integer ID;
	protected String name;
	protected User owner;
	
	public Subject() {
		//Crea una asignatura por defecto
		this(-1,"",new User());
	}
	public Subject(Integer ID, String name, User owner) {
		//Crea una asignatura con todos los valores
		this.ID=ID;
		this.name=name;
		this.owner=owner;
	}
	public Subject(Integer ID, String name) {
		//Crea una asignatura con todos los valores menos el usuario
		this.ID=ID;
		this.name=name;
	}
	public Subject(String name, User owner) {
		//Crea una asignatura con todos los valores menos la ID
		this.name=name;
		this.owner=owner;
	}

	public Integer getID() {
		return this.ID;
	}
	public String getName() {
		return this.name;
	}
	public User getOwner() {
		return this.owner;
	}
	public void setID(Integer ID) {
		this.ID=ID;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setOwner(User owner) {
		this.owner=owner;
	}
	
	public String toString() {
		return name;
	}
}
