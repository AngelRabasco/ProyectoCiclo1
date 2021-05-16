package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IReminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import java.time.LocalDateTime;

public class Reminder extends Entry implements IReminder {
	protected LocalDateTime remindDate;
	protected Boolean status;
	
	public Reminder() {
		//Crea un recordatorio por defecto
		super();
		this.remindDate=LocalDateTime.now();
		this.status=false;
	}
	public Reminder(Integer ID, String name, String description, Subject subject, LocalDateTime creation, LocalDateTime edited, LocalDateTime date, Boolean status) {
		//Crea un recordatorio con todos los calores
		super(ID, name, description, subject, creation, edited);
		this.remindDate=date;
		this.status=status;
	}
	
	public LocalDateTime getRemindDate() {
		return this.remindDate;
	}
	public Boolean getStatus() {
		return this.status;
	}
	public void setRemindDate(LocalDateTime date) {
		this.remindDate=date;
	}
	public void setStatus(Boolean Status) {
		this.status=Status;
	}
	
	public String toString() {
		return "Reminder ["+super.toString()+", remindDate=" + remindDate + ", status=" + status + "]";
	}
}
