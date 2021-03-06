package application;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import java.io.FileInputStream;
import java.util.List;
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
import javafx.scene.control.TextField;
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


public class A_Main extends Application {
  private List<String> args;
  //prevScene is only used for scenes 2 and 3 as those 2 scenes end up going to the same next scene
  //and so the previous Scene has to be saved to return to
  private Scene prevScene;
  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 640;
  private static final String APP_TITLE = "Ateam Scene 5";
 
  private Scene baseScreen(Stage primaryStage) {
    VBox v = new VBox();
    
    Label l = new Label("Welcome!");
    Button retb = new Button("Returning");
    Button newb = new Button("New");
    Button fileb = new Button("File");
    
    l.getTransforms().add(new Scale(3,3,-155, -20));
    retb.getTransforms().add(new Scale(2,2,-320, -100));
    newb.getTransforms().add(new Scale(2,2,-348, -150));
    fileb.getTransforms().add(new Scale(2,2,-353, -200));
    
    retb.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(loginScreen(primaryStage));
    });
    
    newb.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(newScene(primaryStage));
    });
    
    fileb.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(FileScene(primaryStage, null));
    });
    
    v.getChildren().addAll(l, retb, newb, fileb);
    v.setSpacing(50);

    BorderPane bp = new BorderPane();

    bp.setCenter(v);
    
    return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  
  
  //Scene2
  private Scene newScene(Stage primaryStage) {
    
    Scale scale = new Scale(1.5, 1.5, 0, 25);
    Scale scale3 = new Scale(1.5, 1.5, 40, 25);

    TextField user = new TextField();
    
    user.setPrefSize(300, 40);
    
    HBox h = new HBox();
   
   
    Button back = new Button("Back");
    Button save = new Button("Save");
    
    back.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(baseScreen(primaryStage));
    });
    
    save.setOnAction((ActionEvent e) -> {
      prevScene = newScene(primaryStage);
      primaryStage.setScene(profileScene(primaryStage));
    });
    Label customer = new Label("New? Enter Name: ");

    h.getChildren().addAll(customer, user);
    h.setSpacing(125);


    back.getTransforms().add(scale);
    save.getTransforms().add(scale3);

    customer.getTransforms().add(new Scale(2, 2, -10, 0));
    
    HBox h2 = new HBox();
    h2.getChildren().addAll(back, save);
    h2.setSpacing(719);

    BorderPane bp = new BorderPane();

    bp.setTop(h);
    bp.setBottom(h2);

    return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  
  //Scene 6
  private Scene shareScene(Stage primaryStage) {
    Scene profile = profileScene(primaryStage);
    //StackPane root = new StackPane();
    BorderPane bp = new BorderPane();
    
    Label customer = new Label("Share with: ");
    customer.getTransforms().add(new Scale(2, 2, -10, -10));
    
    TextField user = new TextField();
    
    user.setPrefSize(500, 50);
    
    HBox h = new HBox();
    h.getChildren().addAll(customer, user);
    h.setSpacing(75);
    
    Button back = new Button("Back");
    back.getTransforms().add(new Scale(1.5, 1.5, 0, 25));
    back.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(profile);
    });

    bp.setTop(h);
    bp.setBottom(back);
    
    return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  
  //Scene 5
  private Scene profileScene(Stage primaryStage) {
    
    Scale scale = new Scale(1.5, 1.5, 0, 25);
    Scale scale2 = new Scale(2, 2, 50, -150);
    Scale scale3 = new Scale(1.5, 1.5, 40, 25);
    Scale scale4 = new Scale(1.5, 1.5, 500, 25);

    Button yourProfiles = new Button("Your Profiles");
    Button edit = new Button("Edit");
    Button back = new Button("Back");
    Button save = new Button("Save");
    Button share = new Button("Share");
    
    share.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(shareScene(primaryStage));
    });
    
    yourProfiles.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(setScene7(primaryStage));
    });
    
    back.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(prevScene);
    });
    Label customer = new Label("Customer: David Breiten");
    Label shirt = new Label("Shirt");
    Label pants = new Label("Pants");
    Label sleeve = new Label("Sleeve: 32in");
    Label collar = new Label("Collar: 16in");
    Label fit = new Label("Fit: Normal");
    Label waist = new Label("Waist: 32in");
    Label inseem = new Label("Inseem: 34in");

    shirt.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    pants.setFont(Font.font("Arial", FontWeight.BOLD, 10));

    back.getTransforms().add(scale);
    save.getTransforms().add(scale3);
    edit.getTransforms().add(scale4);
    share.getTransforms().add(new Scale(1.5, 1.5, -500, 25));
    yourProfiles.getTransforms().add(scale2);
    customer.getTransforms().add(new Scale(2, 2, -10, 0));
    shirt.getTransforms().add(new Scale(1.5, 1.5, 0, 400));
    sleeve.getTransforms().add(new Scale(1.3, 1.3, -20, 600));
    collar.getTransforms().add(new Scale(1.3, 1.3, -20, 530));
    fit.getTransforms().add(new Scale(1.3, 1.3, -20, 460));
    pants.getTransforms().add(new Scale(1.5, 1.5, 0, 0));
    waist.getTransforms().add(new Scale(1.3, 1.3, -20, -60));
    inseem.getTransforms().add(new Scale(1.3, 1.3, -20, -120));

    StackPane bp = new StackPane();
    bp.getChildren().addAll(back, save, edit, yourProfiles, customer, shirt, pants, sleeve, collar,
        fit, waist, share, inseem);


    StackPane.setAlignment(back, Pos.BOTTOM_LEFT);
    StackPane.setAlignment(edit, Pos.BOTTOM_RIGHT);
    StackPane.setAlignment(save, Pos.BOTTOM_RIGHT);
    StackPane.setAlignment(share, Pos.BOTTOM_LEFT);
    StackPane.setAlignment(yourProfiles, Pos.CENTER);
    StackPane.setAlignment(customer, Pos.TOP_LEFT);
    StackPane.setAlignment(shirt, Pos.CENTER_LEFT);
    StackPane.setAlignment(pants, Pos.CENTER_LEFT);
    StackPane.setAlignment(collar, Pos.CENTER_LEFT);
    StackPane.setAlignment(fit, Pos.CENTER_LEFT);
    StackPane.setAlignment(sleeve, Pos.CENTER_LEFT);
    StackPane.setAlignment(waist, Pos.CENTER_LEFT);
    StackPane.setAlignment(inseem, Pos.CENTER_LEFT);
    
    return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  
  
  
//  /**
//   * Generates the userPage
//   * 
//   * @param customer
//   * @return
//   */
//  private Scene userPage(Customer customer, Stage primaryStage) {
//      BorderPane root = new BorderPane();
//
//      root.setTop(new Label(customer.getCustomerID()));
//      return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//  }

  
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

      loginButton.setOnAction((ActionEvent e) -> {
          String user = userIDEnter.getText();
          String pass = passwordEnter.getText();
          prevScene = loginScreen(primaryStage);
          primaryStage.setScene(profileScene(primaryStage));
//          if (table.login(user, pass)) {
//              // Will need a way to give user page the correct user
//              //Scene userPage = userPage(table.getCustomer(user), primaryStage);
//              //primaryStage.setScene(userPage);
//          }
          loginFailed.setOpacity(100);
      });
      // TODO
//    if(((KeyEvent) userIDEnter.getOnKeyPressed()).getText().equals("ENTER"));
//    userIDEnter.setOnKeyPressed((new KeyEvent()) -> {String user = userIDEnter.getText();
//    String pass = passwordEnter.getText();
//    if (table.login(user, pass)) {
//        // Will need a way to give user page the correct user
//        Scene userPage = userPage(table.getCustomer(user), primaryStage);
//        primaryStage.setScene(userPage);
//    }
//    loginFailed.setOpacity(100);
//});
      vBox.getChildren().addAll(spacer1, userID, userIDEnter, password, passwordEnter, loginButton, spacer2,
              loginFailed);
      // Main layout is Border Pane example (top,left,center,right,bottom)
      BorderPane root = new BorderPane();

      root.setTop(top);
      root.setCenter(vBox);
      root.setBottom(backButton);
      return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

  }

  //Scene 4
  private Scene FileScene(Stage primaryStage, Customer customer) {
      // Creates all scenes
      
      // creates a label
      Label top = new Label("File Upload");
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
      Label subtitle = new Label("Please Enter a JSON File name without the file type:");
      TextField JSONEnter = new TextField();
      Button loginButton = new Button("Login");
      Region spacer2 = new Region();
      spacer2.setPrefHeight(20);
      Label loginFailed = new Label("The file was not found.");
      loginFailed.setOpacity(0);
      loginFailed.setTextFill(Color.RED);
      loginButton.setOnAction((ActionEvent e) -> {
          // opens JSON file and creates a new Customer from the data
          String path = JSONEnter + ".JSON";
          loginFailed.setOpacity(100);
      });
      // TODO
//    if(((KeyEvent) userIDEnter.getOnKeyPressed()).getText().equals("ENTER"));
//    userIDEnter.setOnKeyPressed((new KeyEvent()) -> {String user = userIDEnter.getText();
//    String pass = passwordEnter.getText();
//    if (table.login(user, pass)) {
//        // Will need a way to give user page the correct user
//        Scene userPage = userPage(table.getCustomer(user), primaryStage);
//        primaryStage.setScene(userPage);
//    }
//    loginFailed.setOpacity(100);
//});
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
   * @param primaryStage
   */
  private Scene setScene7 (Stage primaryStage) {
      Text title = new Text("\tProfiles shared with you : \n");
      VBox vBox = new VBox();
      Button b = new Button("Return");
      
      b.setOnAction(actionEvent ->  {
        primaryStage.setScene(profileScene(primaryStage));
      });
      // a list of string where the customers' names are stored
      // TODO implement with data structure later.
      String[] customers = { "Ben" , "Ethan" , "Stan", "David"};
      
      // Iterate through each customer
      for (int i = 0; i < customers.length; i++) {
          String customerName = customers[i];
          CheckBox customer = new CheckBox(customers[i]);
          vBox.getChildren().add(customer);
          // handle the event when the check box is being selected
          EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
              public void handle(ActionEvent e)  
                  // if this check box is being selected
                  { if (customer.isSelected()) {
                      // IMPORTANT
                      // call setScene8 to change the scene to 8 with this customerName passed.
                      setScene8(primaryStage, customerName);
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
   * @param primaryStage
   * @param customer customer name
   */
  private void setScene8 (Stage primaryStage, String customer) {
      Text title = new Text("\t"+customer+"'s profile : \n");
      VBox vBox = new VBox();
      
      // display this customer's sizes
      // TODO implement with data structure later
      Text shirt = new Text("Shirts\n-sleeve : 32 in\n-fit : normal\n");
      Text pants = new Text("Pants\n-waist : 32 in\n");
      vBox.getChildren().add(shirt);
      vBox.getChildren().add(pants);
      
      // Set stage basics
      Button button = new Button("Return");
      button.setOnAction(actionEvent ->  {
          primaryStage.setScene(setScene7(primaryStage));
      });
      BorderPane root = new BorderPane(vBox, title, null, button, null);
      Scene scene8 = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
      primaryStage.setScene(scene8);
      primaryStage.show();
  }

  
  @Override
  public void start(Stage primaryStage) throws Exception {
    args = this.getParameters().getRaw();
    

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

