
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	private List<String> args;
	// prevScene is only used for scenes 2 and 3 as those 2 scenes end up going to
	// the same next scene
	// and so the previous Scene has to be saved to return to
	private Scene prevScene;
	private Scene curNewUserScene;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 640;
	private static final String APP_TITLE = "Ateam Scene 5";
	private CustomerTable table = new CustomerTable();
	private String[] measurements = new String[7];

	private Scene baseScreen(Stage primaryStage) {

		VBox v = new VBox();

		Label l = new Label("Welcome!");
		Button retb = new Button("Returning");
		Button newb = new Button("New");
//		Button fileb = new Button("File");

		l.getTransforms().add(new Scale(3, 3, -155, -20));
		retb.getTransforms().add(new Scale(2, 2, -320, -100));
		newb.getTransforms().add(new Scale(2, 2, -348, -150));
//		fileb.getTransforms().add(new Scale(2, 2, -353, -200));

		retb.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(loginScreen(primaryStage));
		});

		newb.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(newUserScene(primaryStage));
		});

//		fileb.setOnAction((ActionEvent e) -> {
//			primaryStage.setScene(fileScene(primaryStage, null));
//		});

		v.getChildren().addAll(l, retb, newb);
		v.setSpacing(50);

		BorderPane bp = new BorderPane();

		bp.setCenter(v);

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	// Scene2
	private Scene newUserScene(Stage primaryStage) {
		Scale scale = new Scale(1.5, 1.5, 0, 25);
		Scale scale3 = new Scale(1.5, 1.5, 40, 25);

		HBox h = new HBox();

		Button back = new Button("Back");
		Button save = new Button("Save");

		back.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(baseScreen(primaryStage));
		});

		// Initializes all all fields
		Label title = new Label("New?");
		title.setPrefSize(300, 40);
		Region spacer1 = new Region();
		spacer1.setPrefHeight(30);
		Label name = new Label("What's your name?");
		TextField nameField = new TextField();
		Label userID = new Label("Create a unique UserID to be used to login and share!");
		TextField userIDField = new TextField();
		Label pass = new Label("Enter a secure password: ");
		PasswordField passField1 = new PasswordField();
		Label pass2 = new Label("Re-enter password: ");
		PasswordField passField2 = new PasswordField();
		Region spacer2 = new Region();
		spacer2.setPrefHeight(30);
		Label errorLabel = new Label();
		errorLabel.setTextFill(Color.RED);
		Region spacer3 = new Region();
		spacer3.setPrefHeight(30);
		Label JSONinfo = new Label("If you have a JSON file containing size information, click yes. "
				+ " Otherwise you must manually enter size information.");
		final ToggleGroup group = new ToggleGroup();

		RadioButton yes = new RadioButton("yes");
		RadioButton no = new RadioButton("no");
		yes.setToggleGroup(group);
		no.setToggleGroup(group);
		VBox fields = new VBox();
		fields.getChildren().addAll(name, nameField, userID, userIDField, pass, passField1, pass2, passField2, JSONinfo,
				yes, no, spacer2, errorLabel);

		h.getChildren().addAll(title, spacer1);
		h.setSpacing(25);

		back.getTransforms().add(scale);
		save.getTransforms().add(scale3);
		title.setFont(new Font(30));

//    title.getTransforms().add(new Scale(2, 2, -10, 0));

		HBox h2 = new HBox();
		h2.getChildren().addAll(back, save);
		h2.setSpacing(719);

		BorderPane bp = new BorderPane();
		bp.setTop(h);
		bp.setCenter(fields);
		bp.setBottom(h2);

		save.setOnAction((ActionEvent e) -> {

			// Checks if entries are valid
			String[] check = new String[3];
			check[0] = nameField.getText();
			check[1] = userIDField.getText();
			check[2] = passField1.getText();
			for (String field : check) {
				if (field.length() == 0) {
					errorLabel.setText("Please do not leave any fields Blank!");
					return;
				}
				if (field.trim().length() == 0) {
					errorLabel.setText("Please fill the fields with more than spaces!");
					return;
				}
			}
			// checks if JSON button has been clicked
			if (group.getSelectedToggle() == null) {
				errorLabel.setText("Please answer the file passing question!");
				return;
			}
			if (!passField1.getText().equals(passField2.getText())) {
				errorLabel.setText(
						"Your second entry of your password did not match your first, please have them match!");
				return;
			}
			if (table.contains(userIDField.getText())) {
				errorLabel.setText("Sorry, that UserID has been taken, please chose another!");
				return;
			}
			// if customer creation is valid
			else {
				Customer newCustomer = new Customer(nameField.getText(), userIDField.getText(), passField1.getText());
				// case if the user has a JSONfile
				if (group.getSelectedToggle().toString().contains("yes")) {
					errorLabel.setText("");
					yes.setSelected(false);
					prevScene = primaryStage.getScene();
//					prevScene = new Scene(backup, WINDOW_WIDTH, WINDOW_HEIGHT);
					primaryStage.setScene(fileScene(primaryStage, newCustomer));
				}
				// case if the user does not have a JSONfile
				else {
					table.insert(newCustomer);
					try {
						table.setCurrentCustomer(table.getCustomer(userIDField.getText()));
					} catch (NoSuchObjectException e1) {
						System.out.println("Setting Customer not in table, must Debug");
					}
					prevScene = newUserScene(primaryStage);
					primaryStage.setScene(profileScene(primaryStage));
				}
			}
		});

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	// Scene 6
	private Scene shareScene(Stage primaryStage) {
		Scene profile = profileScene(primaryStage);
		// StackPane root = new StackPane();
		BorderPane bp = new BorderPane();

		Label customer = new Label("Share with: ");
		Label sucess = new Label("      if a user with that customerID exists, your sizes have been shared with them!");
		sucess.setVisible(false);
		customer.getTransforms().add(new Scale(2, 2, -10, -10));

		TextField userID = new TextField();

		userID.setPrefSize(500, 50);

		HBox h = new HBox();
		h.getChildren().addAll(customer, userID);
		h.setSpacing(75);
		Button share = new Button("Share");
		share.setOnAction((ActionEvent e) -> {
			sucess.setVisible(true);
			if (table.contains(userID.getText())) {
				table.replace(userID.getText(),
						table.getCustomer(userID.getText()).addReceived(table.getCurrentCustomer().getCustomerID()));
				table.replace(table.getCurrentCustomer().getCustomerID(),
						table.getCurrentCustomer().addShared(userID.getText()));

			}
		});
		Button back = new Button("Back");
		back.getTransforms().add(new Scale(1.5, 1.5, 0, 25));
		back.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(profile);
		});
		Region spacer2 = new Region();
		spacer2.setPrefHeight(300);
		VBox center = new VBox();
		HBox shareHelper = new HBox();
		Region spacer3 = new Region();
		spacer3.setPrefWidth(200);
		shareHelper.getChildren().addAll(spacer3, share);
		center.getChildren().addAll(spacer2, shareHelper, sucess);
		Region spacer1 = new Region();
		spacer1.setPrefWidth(150);
		bp.setLeft(spacer1);
		bp.setTop(h);
		bp.setCenter(center);
		bp.setBottom(back);

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	/**
	 * Delete Scene
	 * 
	 * @param primaryStage
	 * @return
	 */
	private Scene deleteProfileScene(Stage primaryStage) {
		StackPane bp = new StackPane();
		Button cancel = new Button("Cancel");
		Button yes = new Button("Yes");
		Label deleteNext = new Label("Are you sure you want to delete your profile? " + "This cannot be undone.");
		deleteNext.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		yes.setFont(Font.font("Arial", 20));
		cancel.setFont(Font.font("Arial", 20));

		yes.setOnAction((ActionEvent e) -> {
			table.remove(table.getCurrentCustomer());
			table.clearCurrentCustomer();
			primaryStage.setScene(baseScreen(primaryStage));
		});

		cancel.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(profileScene(primaryStage));
		});

		bp.getChildren().addAll(deleteNext, cancel, yes);
		StackPane.setAlignment(deleteNext, Pos.TOP_CENTER);
		StackPane.setAlignment(yes, Pos.CENTER_LEFT);
		StackPane.setAlignment(cancel, Pos.CENTER_RIGHT);

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	// Scene 5
	private Scene profileScene(Stage primaryStage) {
		Scale scale = new Scale(1.5, 1.5, 0, 25);
		Scale scale2 = new Scale(2, 2, 50, -150);
		Scale scale3 = new Scale(1.5, 1.5, 40, 25);
		Scale scale4 = new Scale(1.5, 1.5, 500, 25);

		Button yourProfiles = new Button("Your Profiles");
		Button logOut = new Button("Log Out");
		Button share = new Button("Share");
		Button edit = new Button("Edit");
		Button delete = new Button("Delete");

		share.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(shareScene(primaryStage));
		});

		yourProfiles.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(sharedProfilesScene(primaryStage));
		});

		logOut.setOnAction((ActionEvent e) -> {
			table.clearCurrentCustomer();
			primaryStage.setScene(baseScreen(primaryStage));
		});
		delete.setOnAction((ActionEvent e) -> {

			primaryStage.setScene(deleteProfileScene(primaryStage));
		});

		measurements = table.getCurrentCustomer().getMeasurements();

		Label customer = new Label("Customer: " + table.getCurrentCustomer().getName());
		Label shirt = new Label("Shirt");
		Label pants = new Label("Pants");
		Label sleeve = new Label("Sleeve: ");
		Label collar = new Label("Collar: ");
		Label fit = new Label("Fit: ");
		Label waist = new Label("Waist: ");
		Label inseem = new Label("Inseem: ");
		Label dress = new Label("Dress");
		Label dsize = new Label("Size: ");
		Label shoe = new Label("Shoe");
		Label ssize = new Label("Size: ");
		Label empty = new Label("Add some sizes so you can share!");
		empty.setVisible(false);

		edit.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(editScene(primaryStage));
		});
		boolean displayShirt = false;
		boolean displayPants = false;
		boolean displayDress = false;
		boolean displayShoes = false;

		if (!measurements[0].equals("0")) {
			sleeve.setText("Sleeve: " + measurements[0] + " in");
			sleeve.setVisible(true);
			displayShirt = true;
		} else {
			sleeve.setVisible(false);
		}
		if (!measurements[1].equals("0")) {
			collar.setText("Collar: " + measurements[1] + " in");
			collar.setVisible(true);
			displayShirt = true;
		} else {
			collar.setVisible(false);
		}
		if (!measurements[2].equals("-1")) {
			fit.setText("Fit: " + measurements[2]);
			fit.setVisible(true);
			displayShirt = true;
		} else {
			fit.setVisible(false);
		}
		if (!measurements[3].equals("0")) {
			waist.setText("Waist: " + measurements[3] + " in");
			waist.setVisible(true);
			displayPants = true;

		} else {
			waist.setVisible(false);
		}
		if (!measurements[4].equals("0")) {
			inseem.setText("Inseem: " + measurements[4] + " in");
			inseem.setVisible(true);
			displayShirt = true;

		} else {
			inseem.setVisible(false);
		}
		if (!measurements[5].equals("0")) {
			dsize.setText("Size: " + measurements[5]);
			dsize.setVisible(true);
			displayDress = true;

		} else {
			dsize.setVisible(false);
		}
		if (!measurements[6].strip().equals("0")) {
			ssize.setText("Size: " + measurements[6]);
			ssize.setVisible(true);
			displayShoes = true;

		} else {
			ssize.setVisible(false);
		}

		if (!displayShirt) {
			shirt.setVisible(false);
		}
		if (!displayPants) {
			pants.setVisible(false);
		}
		if (!displayDress) {
			dress.setVisible(false);
		}
		if (!displayShoes) {
			shoe.setVisible(false);
		}
		if (!displayShirt && !displayPants && !displayDress && !displayShoes) {
			System.out.println("yeet");
			empty.setVisible(true);
		}

		shirt.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		pants.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		dress.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		shoe.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		empty.setFont(Font.font("Arial", FontWeight.BOLD, 10));

		logOut.getTransforms().add(scale);

		delete.getTransforms().add(scale3);
		edit.getTransforms().add(scale4);

		share.getTransforms().add(new Scale(1.5, 1.5, -500, 25));
		yourProfiles.getTransforms().add(scale2);
		customer.getTransforms().add(new Scale(2, 2, -10, 0));

		shirt.getTransforms().add(new Scale(1.5, 1.5, 0, 400));
		dress.getTransforms().add(new Scale(1.5, 1.5, 200, 400));
		empty.getTransforms().add(new Scale(1.5, 1.5, 100, 500));
		shoe.getTransforms().add(new Scale(1.5, 1.5, 200, 0));
		ssize.getTransforms().add(new Scale(1.3, 1.3, 250, -60));
		dsize.getTransforms().add(new Scale(1.3, 1.3, 250, 600));

		sleeve.getTransforms().add(new Scale(1.3, 1.3, -20, 600));
		collar.getTransforms().add(new Scale(1.3, 1.3, -20, 530));
		fit.getTransforms().add(new Scale(1.3, 1.3, -20, 460));
		pants.getTransforms().add(new Scale(1.5, 1.5, 0, 0));
		waist.getTransforms().add(new Scale(1.3, 1.3, -20, -60));
		inseem.getTransforms().add(new Scale(1.3, 1.3, -20, -120));

		StackPane bp = new StackPane();
		bp.getChildren().addAll(logOut, share, yourProfiles, edit, delete, customer, shirt, pants, sleeve, collar, fit,
				waist, inseem, dress, dsize, ssize, shoe, empty);
		StackPane.setAlignment(share, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(logOut, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(edit, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(delete, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(yourProfiles, Pos.CENTER);
		StackPane.setAlignment(customer, Pos.TOP_LEFT);
		StackPane.setAlignment(shirt, Pos.CENTER_LEFT);
		StackPane.setAlignment(pants, Pos.CENTER_LEFT);
		StackPane.setAlignment(collar, Pos.CENTER_LEFT);
		StackPane.setAlignment(fit, Pos.CENTER_LEFT);
		StackPane.setAlignment(sleeve, Pos.CENTER_LEFT);
		StackPane.setAlignment(waist, Pos.CENTER_LEFT);
		StackPane.setAlignment(inseem, Pos.CENTER_LEFT);
		StackPane.setAlignment(dress, Pos.CENTER_RIGHT);
		StackPane.setAlignment(dsize, Pos.CENTER_RIGHT);
		StackPane.setAlignment(shoe, Pos.CENTER_RIGHT);
		StackPane.setAlignment(ssize, Pos.CENTER_RIGHT);

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	// Scene that pops up when someone is editing their data
	private Scene editScene(Stage primaryStage) {
		TextField[] fieldArr = new TextField[6];
		for (int y = 0; y < 5; y++) {
			fieldArr[y] = new TextField();
			fieldArr[y].setMaxSize(75, 25);
		}
		TextField dresscb = new TextField();
		dresscb.setMaxSize(75, 25);
		ComboBox fitcb = new ComboBox();
		fitcb.getItems().addAll("Classic", "Modern", "Slim");
		fitcb.setMaxSize(100, 25);
		Tooltip sleevett = new Tooltip("Enter sleeve length in inches");
		fieldArr[0].setTooltip(sleevett);
		Tooltip collartt = new Tooltip("Enter collar circumference in inches");
		fieldArr[1].setTooltip(collartt);
		Tooltip waisttt = new Tooltip("Enter waist circumference in inches");
		fieldArr[2].setTooltip(waisttt);
		Tooltip inseemtt = new Tooltip("Enter inseem length in inches");
		fieldArr[3].setTooltip(inseemtt);
		Tooltip shoett = new Tooltip("Enter shoe size (American)");
		fieldArr[4].setTooltip(sleevett);
		Tooltip fittt = new Tooltip("Choose favorite fit");
		fitcb.setTooltip(fittt);
		Tooltip dresstt = new Tooltip("Choose dress size");
		dresscb.setTooltip(dresstt);

		BorderPane bp = new BorderPane();
		Button save = new Button("Save");
		Button back = new Button("Back");
		back.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(profileScene(primaryStage));
		});

		Label customer = new Label("Customer: David Breiten");
		Label shirt = new Label("Shirt");
		Label pants = new Label("Pants");
		Label sleeve = new Label("Sleeve: ");
		Label collar = new Label("Collar: ");
		Label fit = new Label("Fit: ");
		Label waist = new Label("Waist: ");
		Label inseem = new Label("Inseem: ");
		Label dress = new Label("Dress");
		Label dsize = new Label("Size: ");
		Label shoe = new Label("Shoe");
		Label ssize = new Label("Size: ");
		Label error = new Label("Please enter valid sizes only!");
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		shirt.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		pants.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		dress.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		shoe.setFont(Font.font("Arial", FontWeight.BOLD, 10));

		error.getTransforms().add(new Scale(1.5, 1.5, 300, -450));
		back.getTransforms().add(new Scale(1.5, 1.5, 0, 25));
		save.getTransforms().add(new Scale(1.5, 1.5, 0, 25));
		customer.getTransforms().add(new Scale(2, 2, -10, 0));
		shirt.getTransforms().add(new Scale(1.5, 1.5, 0, -200));
		pants.getTransforms().add(new Scale(1.5, 1.5, 0, -200));
		dress.getTransforms().add(new Scale(1.5, 1.5, 300, -200));
		shoe.getTransforms().add(new Scale(1.5, 1.5, 300, -385));
		dsize.getTransforms().add(new Scale(1.3, 1.3, 420, -250));
		ssize.getTransforms().add(new Scale(1.3, 1.3, 420, -560));

		sleeve.getTransforms().add(new Scale(1.3, 1.3, -50, -250));
		collar.getTransforms().add(new Scale(1.3, 1.3, -50, -250));
		fit.getTransforms().add(new Scale(1.3, 1.3, -50, -250));

		waist.getTransforms().add(new Scale(1.3, 1.3, -50, -250));
		inseem.getTransforms().add(new Scale(1.3, 1.3, -50, -250));

		fieldArr[1].setTranslateX(-5);
		fitcb.setTranslateX(-25);
		fieldArr[2].setTranslateY(45);
		fieldArr[3].setTranslateY(45);
		fieldArr[2].setTranslateX(-10);
		fieldArr[3].setTranslateX(5);
		dresscb.setTranslateX(-85);
		dresscb.setTranslateY(-65);
		fieldArr[4].setTranslateY(24);
		fieldArr[4].setTranslateX(-85);

		VBox v1 = new VBox();
		v1.setSpacing(30);
		VBox v2 = new VBox();
		v2.setSpacing(30);
		VBox v3 = new VBox();
		v3.setSpacing(21.5);
		HBox h = new HBox();
		v1.getChildren().addAll(shirt, sleeve, collar, fit, pants, waist, inseem);
		for (int i = 0; i < 4; i++) {
			if (i == 2) {
				v3.getChildren().add(fitcb);
			}
			v3.getChildren().add(fieldArr[i]);
		}

		v2.getChildren().addAll(dress, dsize, shoe, ssize, dresscb, error);

		for (int j = 4; j < 5; j++) {
			v2.getChildren().add(fieldArr[j]);
		}
		h.getChildren().addAll(back, save);
		h.setSpacing(698);
		v3.setTranslateX(25);
		v3.setTranslateY(117);
		bp.setTop(customer);
		bp.setLeft(v1);
		bp.setRight(v2);
		bp.setBottom(h);
		bp.setCenter(v3);
		save.setOnAction((ActionEvent e) -> {
			boolean nextScene = true;

			// updates dress
			if (!dresscb.getText().equals("")) {
				measurements[5] = dresscb.getText().strip();
				try {
					Integer.parseInt(dresscb.getText().strip());
				} catch (NumberFormatException ed) {
					nextScene = false;
				}

			}
			// updates Shirt Fit
			if (fitcb.getValue() != null && !fitcb.getValue().equals("")) {
				measurements[2] = (String) fitcb.getValue();

			}
			if (!fieldArr[0].getText().equals("")) {
				measurements[0] = fieldArr[0].getText().strip();
				try {
					Integer.parseInt(fieldArr[0].getText().strip());
				} catch (NumberFormatException ed) {
					nextScene = false;
				}
			}
			if (!fieldArr[1].getText().equals("")) {
				measurements[1] = fieldArr[1].getText().strip();
				try {
					Integer.parseInt(fieldArr[1].getText().strip());
				} catch (NumberFormatException ed) {
					nextScene = false;
				}
			}
			if (!fieldArr[2].getText().equals("")) {
				measurements[3] = fieldArr[2].getText().strip();
				try {
					Integer.parseInt(fieldArr[2].getText().strip());
				} catch (NumberFormatException ed) {
					nextScene = false;
				}
			}
			if (!fieldArr[3].getText().equals("")) {
				measurements[4] = fieldArr[3].getText().strip();
				try {
					Integer.parseInt(fieldArr[3].getText().strip());
				} catch (NumberFormatException ed) {
					nextScene = false;
				}
			}
			if (!fieldArr[4].getText().equals("")) {
				measurements[6] = fieldArr[4].getText().strip();
				try {
					Integer.parseInt(fieldArr[4].getText().strip());
					measurements[6] = Integer
							.toString((int) (Math.round(Double.parseDouble(measurements[6]) * 2) / 2.0));
				} catch (NumberFormatException ed) {
					nextScene = false;
				}
			}
			if (nextScene) {
				table.getCurrentCustomer().update(measurements);
				primaryStage.setScene(profileScene(primaryStage));
			} else {
				error.setVisible(true);
			}
		});

		return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

//Scene 3
	private Scene loginScreen(Stage primaryStage) {
		// Creates all scenes

		// creates a label
		Label top = new Label("Returning User");
		top.setFont(new Font(30));
		// Back for the bottom
		Button backButton = new Button("back");
		backButton.setMinSize(100, 50);
		// lambda function to avoid creating myHandler class
		backButton.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(baseScreen(primaryStage));
		});
		// Creates the VBox for the functionality of the page
		VBox vBox = new VBox();
		// empty space
		Region spacer1 = new Region();
		spacer1.setPrefHeight(30);
		Label hello = new Label("Welcome back! please sign in!");
		hello.setFont(new Font(20));
		Region spacer2 = new Region();
		spacer2.setPrefHeight(20);
		Label userID = new Label("UserID:");
		TextField userIDEnter = new TextField();
		Label password = new Label("Password:");
		PasswordField passwordEnter = new PasswordField();
		Button loginButton = new Button("Login");
//		Region spacer2 = new Region();
//		spacer2.setPrefHeight(20);
		Label loginFailed = new Label("The UserID and Password combination was not found.");
		loginFailed.setOpacity(0);
		loginFailed.setTextFill(Color.RED);

		loginButton.setOnAction((ActionEvent e) -> {
			String user = userIDEnter.getText();
			String pass = passwordEnter.getText();

			if (table.login(user, pass)) {
//              // Will need a way to give user page the correct user
				prevScene = loginScreen(primaryStage);
				primaryStage.setScene(profileScene(primaryStage));
			}
			loginFailed.setOpacity(100);
		});
		// TODO make hitting enter on textfield same as hitting button

		vBox.getChildren().addAll(spacer1, userID, userIDEnter, password, passwordEnter, loginButton, spacer2,
				loginFailed);
		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		root.setTop(top);
		root.setCenter(vBox);
		root.setBottom(backButton);
		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

	}

	// Scene 4
	private Scene fileScene(Stage primaryStage, Customer customer) {
		Label top = new Label("File Upload");
		top.setFont(new Font(30));
		// Back for the bottom
		Button backButton = new Button("back");
		backButton.setMinSize(100, 50);
		// lambda function to avoid creating myHandler class
		backButton.setOnAction((ActionEvent e) -> {
			primaryStage.setScene(prevScene);
		});
		// Creates the VBox for the functionality of the page
		VBox vBox = new VBox();
		// empty space
		Region spacer1 = new Region();
		spacer1.setPrefHeight(30);
		Label subtitle = new Label("Please Enter a JSON File name without the file type:");
		TextField JSONEnter = new TextField();
		Button loginButton = new Button("Create");
		Region spacer2 = new Region();
		spacer2.setPrefHeight(20);
		Label loginFailed = new Label();
		loginFailed.setTextFill(Color.RED);

		loginButton.setOnAction((ActionEvent e) -> {
			String path = JSONEnter + ".JSON";
			try {
				Object obj = new JSONParser().parse(new FileReader(path));
				JSONObject jo = (JSONObject) obj;
				customer.parseJSON(jo);
				table.setCurrentCustomer(customer);
				primaryStage.setScene(profileScene(primaryStage));

			} catch (FileNotFoundException e1) {
				loginFailed.setText("The file was not found.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		// TODO add enter functionality
		vBox.getChildren().addAll(spacer1, subtitle, JSONEnter, loginButton, spacer2, loginFailed);
		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		root.setTop(top);
		root.setCenter(vBox);
		root.setBottom(backButton);
		return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

	}

	/**
	 * Once this function is called, a scene 7 is created on set on the stage.
	 * 
	 * @param primaryStage
	 */
	private Scene sharedProfilesScene(Stage primaryStage) {
		Text title = new Text("\tProfiles shared with you : \n");
		title.setFont(new Font(30));
		VBox vBox = new VBox();
		Button b = new Button("Return");
		b.setFont(new Font(20));
		b.setOnAction(actionEvent -> {
			primaryStage.setScene(profileScene(primaryStage));
		});
		// a list of string where the customers' names are stored
		// TODO implement with data structure later.
		;
		ArrayList<String> customers = table.getCurrentCustomer().getReceived();

		// Iterate through each customer
		for (int i = 0; i < customers.size(); i++) {
			String customerID = customers.get(i);
			CheckBox customer = new CheckBox(table.getCustomer(customerID).getAnalytics());
			customer.setFont(new Font(20));
			vBox.getChildren().add(customer);
			// handle the event when the check box is being selected
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e)
				// if this check box is being selected
				{
					if (customer.isSelected()) {
						// IMPORTANT
						// call sharedProfileScene to change the scene to 8 with this customerName
						// passed.
						sharedProfileScene(primaryStage, customerID);
					}
				}
			};
			customer.setOnAction(event);
		}

		// Set stage basics
		BorderPane root = new BorderPane(vBox, title, null, null, null);
		root.setBottom(b);
		Scene scene7 = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		return scene7;

	}

	/**
	 * Once this function is called, a scene 8 is created and set on the stage
	 * 
	 * @param primaryStage
	 * @param customer     customer name
	 */
	private void sharedProfileScene(Stage primaryStage, String customerID) {
		Customer curProfile = table.getCustomer(customerID);
		Text title = new Text("\t" + curProfile.getName() + "'s profile : \n");
		Text noData = new Text("This user does not have any data filled out!");
		noData.setFont(new Font(30));
		VBox vBox = new VBox();
		title.setFont(new Font(30));

		boolean shirtShow = false;
		String shirtText = "Shirts\n";
		try {
			int sleeve = curProfile.getShirt().getSleeve();
			if(sleeve == 0) throw new NullPointerException();

			shirtText = shirtText + "-sleeve : " + sleeve + " in";
			shirtShow = true;
		} catch (NullPointerException e) {}
		
		try {
			int collor = curProfile.getShirt().getCollar();
			if(collor == 0) throw new NullPointerException();

			shirtText = shirtText + "\n-collor : " + collor + " in";
			shirtShow = true;

		} catch (NullPointerException e) {}
		
		try {
			String fit = curProfile.getShirt().getFit();
			shirtText = shirtText + "\n-fit : " + fit + "\n";
			shirtShow = true;

		} catch (NullPointerException e) {}
		if(!shirtShow) {
			shirtText = "";
		}
		Text shirt = new Text(shirtText);
		
		boolean pantsShow = false;
		String pantsText = "Pants\n";
		
		try{
			int waist = curProfile.getPants().getWaist();
			if(waist == 0) throw new NullPointerException();
			pantsText = "-waist : " + waist + " in";
			pantsShow = true;
		}catch (NullPointerException e) {}
		
		try{
			int inseem = curProfile.getPants().getInseem();
			if(inseem == 0) throw new NullPointerException();

			pantsShow = true;
			 pantsText = "\n-inseem : " + inseem + " in\n";
		}catch (NullPointerException e) {}
		if(!pantsShow) {
			pantsText = "";
		}

		Text pants = new Text(pantsText);
		String dressText = "";
		boolean dressShow = false;
		try{
			int dress_size = curProfile.getDress().getSize();
			if(dress_size == 0) throw new NullPointerException();
			dressShow = true;
			 dressText = "Dress\n-size : " + dress_size + " in\n";
		}catch (NullPointerException e) {}
		Text dress = new Text(dressText);
		boolean shoeShow = false;
		String shoeText = "";
		try{
			int shoe_size = curProfile.getShoe().getSize();
			if(shoe_size == 0) throw new NullPointerException();
			shoeShow = true;
			shoeText = "Shoe\n-size : " + shoe_size + " in\n";

		}catch (NullPointerException e) {}
		Text shoe = new Text(shoeText);
		

		

		shirt.setFont(new Font(20));
		pants.setFont(new Font(20));
		dress.setFont(new Font(20));
		shoe.setFont(new Font(20));
		vBox.getChildren().add(shirt);
		vBox.getChildren().add(pants);
		vBox.getChildren().add(dress);
		vBox.getChildren().add(shoe);

		// Set stage basics
		Button button = new Button("Return");
		button.setFont(new Font(20));
		button.setOnAction(actionEvent -> {
			primaryStage.setScene(sharedProfilesScene(primaryStage));
		});
		
		BorderPane root = new BorderPane();
		if(!shirtShow && !pantsShow && !dressShow && !shoeShow) {
			root = new BorderPane(vBox, title, null, button, noData);
		}
		else{
			root = new BorderPane(vBox, title, null, button, null);
		}
		root.getChildren().add(new Label("yeet"));
		Scene scene8 = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene8);
		primaryStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		args = this.getParameters().getRaw();
		// Adds some users
		Customer ben = new Customer("ben", "b1", "password");
		Customer david = new Customer("david", "d1", "password");
		Customer stan = new Customer("stan", "s1", "password");
		Customer ethan = new Customer("ethan", "e1", "password");
		ben.addReceived("d1");
		ben.addReceived("s1");
		ben.addReceived("e1");
		ben.setShirt(new Shirt(1, 1, "CLASSIC"));
		table.insert(ben);
		table.insert(david);
		table.insert(stan);
		table.insert(ethan);
		Scene currScene = baseScreen(primaryStage);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(currScene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
