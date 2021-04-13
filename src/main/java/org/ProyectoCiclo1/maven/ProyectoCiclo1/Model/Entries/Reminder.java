package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces.IReminder;
import java.time.LocalDateTime;

public class Reminder extends Entry implements IReminder {
	private LocalDateTime date;
	private Integer status;
	
	public Reminder() {
		super();
	}
	public Reminder(Integer ID, String name, LocalDateTime date) {
		super(ID,name);
		this.date=date;
		this.status=0;
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setDate(LocalDateTime date) {
		this.date=date;
	}
	public void setStatus(Integer Status) {
		this.status=Status;
	}
}
