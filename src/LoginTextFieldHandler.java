import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

class LoginTextFieldHandler implements javafx.event.EventHandler<ActionEvent>{
	private TextField field;

	public LoginTextFieldHandler(TextField field) {
		this.field = field;
	}

	public void handle(KeyEvent event) {
		System.out.println("handling button event");
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
