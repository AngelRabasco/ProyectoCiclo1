package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainMenuController implements Initializable {
	
	@FXML
	private JFXHamburger ham1;
	
	public void initialize(URL url, ResourceBundle rb) {
		HamburgerBasicCloseTransition basicClose=new HamburgerBasicCloseTransition(ham1);
		basicClose.setRate(-1);
		ham1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->{
			basicClose.setRate(basicClose.getRate()*-1);
			basicClose.play();
		});
	}
}
