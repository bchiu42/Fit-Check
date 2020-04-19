
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	// store any command-line arguments that were enteread.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;
	private CustomerTable table = new CustomerTable();
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 640;
	private static final String APP_TITLE = "CS400 My First JavaFX Program";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// used for testing
		 table.insert(new Customer("Ben", "bchiu", "password"));

		Scene curScene = loginScreen(primaryStage);
		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(curScene);
		primaryStage.show();
	}

	/**
	 * Generates the userPage
	 * 
	 * @param customer
	 * @return
	 */
	private Scene userPage(Customer customer, Stage primaryStage) {
		BorderPane root = new BorderPane();

		root.setTop(new Label(customer.getCustomerID()));
		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

	}

	/**
	 * Generates homeScreen
	 * 
	 * @return
	 */
	private Scene homeScreen(Stage primaryStage) {
		// Create a horizontal box with Hello labels for each args
		HBox hbox = new HBox();

		// creates a label
		Label top = new Label("CS 400 MY First JavaFX Program");
		// Creates the ComboBox for Left Panel
		ComboBox box = new ComboBox();
		Label boxLabel = new Label("What's my \n favorite food?  ");
		box.getItems().addAll("Chicken", "Pizza", "Play-doh", "Pie");
		hbox.getChildren().addAll(boxLabel, box);

		// Loads Image for Middle
//		Image me = new Image("headshot.jpeg");
//		ImageView headshot = new ImageView(me);
		// Button for the bottom
		Button exit = new Button("Done");
		
		MyHandler handler = new MyHandler(exit);
		exit.setOnAction(handler);
		// lambda function to avoid creating myHandler class
//		exit.setOnAction((ActionEvent e) -> {
//			primaryStage.close();
//		});

		// Checkbox for right
		VBox checklist = new VBox();
		checklist.getChildren().add(new Label("Grocery List:"));
		String[] groceryList = new String[] { "Apples", "Pineapples", "Apple Sauce", "Apple Butter" };
		CheckBox[] prep = new CheckBox[groceryList.length];
		for (int k = 0; k < groceryList.length; k++) {
			prep[k] = new CheckBox(groceryList[k]);
			checklist.getChildren().add(prep[k]);
		}

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		// Add the vertical box to the center of the root pane
		root.setTop(top);
		root.setLeft(hbox);
		root.setCenter(new Label("hi"));
		root.setRight(checklist);
		root.setBottom(exit);
		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private Scene loginScreen(Stage primaryStage) {
		// Creates all scenes
		Scene homeScreen = homeScreen(primaryStage);
		// creates a label
		Label top = new Label("Returning User:");
		top.setFont(new Font(30));
		// Back for the bottom
		Button backButton = new Button("back");
		backButton.setMinSize(100, 50);
		// lambda function to avoid creating myHandler class
		backButton.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(homeScreen);
		});
		//Creates the VBox for the functionality of the page
		VBox vBox = new VBox();
		// empty space
		Region spacer1 = new Region();
		spacer1.setPrefHeight(30);
		Label userID = new Label("UserID:");
		TextField userIDEnter = new TextField();
		Label password = new Label("Password:");
		PasswordField passwordEnter = new PasswordField();
		Button loginButton = new Button("Login");
		Region spacer2 = new Region();
		spacer2.setPrefHeight(20);
		Label loginFailed = new Label("The UserID and Password combination was not found.");
		loginFailed.setOpacity(0);
		loginFailed.setTextFill(Color.RED);
		MyHandler handler = new MyHandler(loginButton);
		
		loginButton.setOnAction((ActionEvent e) -> {
			String user = userIDEnter.getText();
			String pass = passwordEnter.getText();
			if (table.login(user, pass)) {
				// Will need a way to give user page the correct user
				Scene userPage = userPage(table.getCustomer(user), primaryStage);
				primaryStage.setScene(userPage);
			}
			loginFailed.setOpacity(100);
		});
		//TODO
//		if(((KeyEvent) userIDEnter.getOnKeyPressed()).getText().equals("ENTER"));
//		userIDEnter.setOnKeyPressed((new KeyEvent()) -> {String user = userIDEnter.getText();
//		String pass = passwordEnter.getText();
//		if (table.login(user, pass)) {
//			// Will need a way to give user page the correct user
//			Scene userPage = userPage(table.getCustomer(user), primaryStage);
//			primaryStage.setScene(userPage);
//		}
//		loginFailed.setOpacity(100);
//	});
		vBox.getChildren().addAll(spacer1, userID, userIDEnter, password, passwordEnter, loginButton, spacer2,
				loginFailed);
		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		root.setTop(top);
		root.setCenter(vBox);
		root.setBottom(backButton);
		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

