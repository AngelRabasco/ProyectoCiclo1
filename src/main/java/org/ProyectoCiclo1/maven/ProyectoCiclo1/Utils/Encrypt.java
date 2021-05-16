package org.ProyectoCiclo1.maven.ProyectoCiclo1.Utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt {
	public static String enrypt(String password) {
		//Encripta el string introducido
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}
