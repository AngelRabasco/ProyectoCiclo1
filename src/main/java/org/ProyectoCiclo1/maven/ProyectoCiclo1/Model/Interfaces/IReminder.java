package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import java.time.LocalDateTime;

public interface IReminder {
	
	public LocalDateTime getDate();
	public Integer getStatus();				//Returns 0 (Pending) or 1 (Done)
	public void setDate(LocalDateTime date);
	public void setStatus(Integer Status);
	
}