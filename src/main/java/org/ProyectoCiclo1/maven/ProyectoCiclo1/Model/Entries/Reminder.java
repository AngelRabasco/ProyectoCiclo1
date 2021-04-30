package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IReminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import java.time.LocalDateTime;

public class Reminder extends Entry implements IReminder {
	private LocalDateTime remindDate;
	private Boolean status;
	
	public Reminder() {
		super();
		this.remindDate=LocalDateTime.MIN;
		this.status=false;
	}
	public Reminder(Integer ID, String name, String description, Subject subject, LocalDateTime creation, LocalDateTime edited, LocalDateTime date) {
		super(ID, name, description, subject, creation, edited);
		this.remindDate=date;
		this.status=false;
	}
	
	public LocalDateTime getDate() {
		return this.remindDate;
	}
	public Boolean getStatus() {
		return this.status;
	}
	public void setDate(LocalDateTime date) {
		this.remindDate=date;
	}
	public void setStatus(Boolean Status) {
		this.status=Status;
	}
}
