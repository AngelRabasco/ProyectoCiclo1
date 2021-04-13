package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IContent;

public class Content implements IContent {
	private Integer ID;
	private Entry entry;
	private Integer type;
	private String value;
	
	public Content() {
		
	}
	public Content(Integer ID, Entry entry, Integer type, String value) {
		this.ID=ID;
		this.entry=entry;
		this.type=type;
		this.value=value;
	}
	
	public Integer getID() {
		return this.ID;
	}
	public Entry getEntry() {
		return this.entry;
	}
	public Integer getType() {
		return this.type;
	}
	public String getValue() {
		return this.value;
	}
}