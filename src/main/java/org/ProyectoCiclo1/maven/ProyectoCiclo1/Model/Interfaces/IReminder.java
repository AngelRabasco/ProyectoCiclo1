package org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Interfaces;

import java.time.LocalDateTime;

public interface IReminder {
	
	public LocalDateTime getDate();
	public Boolean getStatus();				//Returns false (Pending) or true (Done)
	public void setDate(LocalDateTime date);
	public void setStatus(Boolean Status);
	
}
