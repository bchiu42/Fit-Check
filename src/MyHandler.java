import javafx.event.ActionEvent;
import javafx.scene.control.Button;

class MyHandler implements javafx.event.EventHandler<ActionEvent>{

	private Button Button;

	public MyHandler(Button loginButton) {
		this.Button = loginButton;
	}

	@Override
	public void handle(ActionEvent arg0) {
		System.out.println("handling button event");
	}

}
