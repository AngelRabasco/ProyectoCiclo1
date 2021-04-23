package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IReminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;

import java.time.LocalDateTime;

public class Reminder extends Entry implements IReminder {
	private LocalDateTime remindDate;
	private Integer status;
	
	public Reminder() {
		super();
	}
	public Reminder(Integer ID, String name, String description, Subject subject, LocalDateTime creation, LocalDateTime edited, LocalDateTime date) {
		super(ID, name, description, subject, creation, edited);
		this.remindDate=date;
		this.status=0;
	}
	
	public LocalDateTime getDate() {
		return this.remindDate;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setDate(LocalDateTime date) {
		this.remindDate=date;
	}
	public void setStatus(Integer Status) {
		this.status=Status;
	}
}
