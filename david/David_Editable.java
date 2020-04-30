//Scene that pops up when a user wants to share their info with another user
  private Scene shareScene(Stage primaryStage) {
    Scene profile = profileScene(primaryStage);
    
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
  
  //Scene that pops up when someone is editing their data
  private Scene editScene(Stage primaryStage) {
    TextField[] fieldArr = new TextField[6];
    for(int y = 0; y < 5; y++) {
      fieldArr[y] = new TextField();
      fieldArr[y].setMaxSize(75, 25);
    }
    String dsizes[] = {"2","4","6","8","10","12","14","16","18","20"};
    @SuppressWarnings("unchecked")
    ComboBox dresscb = new ComboBox(FXCollections.observableArrayList(dsizes));
    dresscb.setMaxSize(75, 25);
    String fits[] = {"Classic", "Modern", "Slim"};
    ComboBox fitcb = new ComboBox(FXCollections.observableArrayList(fits));
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
    
    Label customer = new Label("Customer: " + Table.currentCustomer.getName());
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
    
    shirt.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    pants.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    dress.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    shoe.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    
    back.getTransforms().add(new Scale(1.5, 1.5, 0, 25));
    save.getTransforms().add(new Scale (1.5, 1.5, 0, 25));

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
    fieldArr[4].setTranslateY(64);
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
      if(i == 2) {
        v3.getChildren().add(fitcb);
      }
      v3.getChildren().add(fieldArr[i]);
    }
    
    v2.getChildren().addAll(dress, dsize, shoe, ssize, dresscb);

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
      for (int m = 0; m < 7; m++) {
        if (m == 4) {
          if(dresscb.getValue() != null && !dresscb.getValue().equals("")) {
            measurements[m] = (String) dresscb.getValue();
          }
        }
        if (m == 2) {
          if(fitcb.getValue() != null && !fitcb.getValue().equals("")) {
            measurements[m] = (String) fitcb.getValue();
          }
        }
        else {
          if(m > 4 && fieldArr[m - 2].getText() != null && !fieldArr[m - 2].getText().equals("")) {
            measurements[m] = fieldArr[m-2].getText();
          }
          else if (m>2 && m<5 && fieldArr[m-1].getText() != null && !fieldArr[m-1].getText().equals("")) {
            measurements[m] = fieldArr[m-1].getText();
          }
          else if (m<=2 && fieldArr[m].getText() != null && !fieldArr[m].getText().equals("")){
            measurements[m] = fieldArr[m].getText();
          }
          else {
            measurements[m] = "-1";
          }
        }
        
      }
      measurements[6] = Double.toString(Math.round(Double.parseDouble(measurements[6])*2)/2.0);
      primaryStage.setScene(profileScene(primaryStage));
    });
    
    return new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
  }
  
  //Scene that pops up when a users information is being displayed
  private Scene profileScene(Stage primaryStage) {
    
    Scale scale = new Scale(1.5, 1.5, 0, 25);
    Scale scale2 = new Scale(2, 2, 50, -150);
    Scale scale3 = new Scale(1.5, 1.5, 40, 25);
    Scale scale4 = new Scale(1.5, 1.5, 800, 25);

    Button yourProfiles = new Button("Your Profiles");
    Button edit = new Button("Edit");
    Button logout = new Button("Logout");
    
    Button share = new Button("Share");
    
    share.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(shareScene(primaryStage));
    });
    
    
    
    yourProfiles.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(setScene7(primaryStage));
    });
    
    logout.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(prevScene);
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
    
    edit.setOnAction((ActionEvent e) -> {
      primaryStage.setScene(editScene(primaryStage));
    });
    if (!measurements[0].equals("-1")) {
      sleeve.setText("Sleeve: " + measurements[0] + " in");
      sleeve.setVisible(true);
    }
    else {
      sleeve.setVisible(false);
    }
    if (!measurements[1].equals("-1")) {
      collar.setText("Collar: " + measurements[1] + " in");
      collar.setVisible(true);
    }
    else {
     collar.setVisible(false);
    }
    if (!measurements[2].equals("-1")) {
      fit.setText("Fit: " + measurements[2]);
      fit.setVisible(true);
    }
    else {
      fit.setVisible(false);
    }
    if (!measurements[3].equals("-1")) {
      waist.setText("Waist: " + measurements[3] + " in");
      waist.setVisible(true);
    }
    else {
      waist.setVisible(false);
    }
    if (!measurements[4].equals("-1")) {
      inseem.setText("Inseem: " + measurements[4] + " in");
      inseem.setVisible(true);
    }
    else {
      inseem.setVisible(false);
    }
    if (!measurements[5].equals("-1")) {
      dsize.setText("Size: " + measurements[5]);
      dsize.setVisible(true);
    }
    else {
      dsize.setVisible(false);
    }
    if (!measurements[6].equals("-1")) {
      ssize.setText("Size: " + measurements[6]);
      ssize.setVisible(true);
    }
    else {
      ssize.setVisible(false);
    }
    
    shirt.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    pants.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    dress.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    shoe.setFont(Font.font("Arial", FontWeight.BOLD, 10));

    logout.getTransforms().add(scale);
    
    edit.getTransforms().add(scale4);
    share.getTransforms().add(new Scale(1.5, 1.5, -708, 25));
    yourProfiles.getTransforms().add(scale2);
    customer.getTransforms().add(new Scale(2, 2, -10, 0));
    
    shirt.getTransforms().add(new Scale(1.5, 1.5, 0, 400));
    dress.getTransforms().add(new Scale(1.5, 1.5, 200, 400));
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
    bp.getChildren().addAll(logout, edit, yourProfiles, customer, shirt, pants, sleeve, collar,
        fit, waist, share, inseem, dress, dsize, ssize, shoe);
    
    StackPane.setAlignment(logout, Pos.BOTTOM_LEFT);
    StackPane.setAlignment(edit, Pos.BOTTOM_RIGHT);
    StackPane.setAlignment(share, Pos.BOTTOM_CENTER);
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
