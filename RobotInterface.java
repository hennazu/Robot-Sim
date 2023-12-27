package testjfx;


import java.util.ArrayList;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;



public class RobotInterface extends Application {
	private MyCanvas mc;										// Custom canvas for drawing
	private AnimationTimer timer;								// timer used for animation
	private VBox rtPane;										// vertical box for putting info
	private RobotArena arena;
	private Robot selectedRobot = null; 

	/**
	 * function to show in a box ABout the program
	 */
	private void showAbout() {
	    Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
	    alert.setTitle("About");									// say is About
	    alert.setHeaderText(null);
	    alert.setContentText("Henna's JavaFX Demonstrator");			// give text
	    alert.showAndWait();										// show box and wait for user to close
	}

	 /**
	  * set up the mouse event - when mouse pressed, put robot there
	  * @param canvas
	  */
	void setMouseEvents(Canvas canvas) {
	    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent e) {
	            if (selectedRobot != null) {
	                // Move the selected robot to the clicked position
	                selectedRobot.setXY(e.getX(), e.getY());
	                selectedRobot = null; // Deselect after moving
	                drawWorld(); // Redraw world
	            } else {
	                // Check if a PaddleRobot is clicked
	                selectedRobot = arena.getRobotAtPosition(e.getX(), e.getY());
	                if (!(selectedRobot instanceof PaddleRobot)) {
	                    // If not a PaddleRobot, deselect
	                    selectedRobot = null;
	                }
	            }
	        }
	    });
	}
	/**
	 * set up the menu of commands for the GUI
	 * @return the menu bar
	 */
	private MenuBar setMenu() {
	    MenuBar menuBar = new MenuBar();

	    // File Menu
	    Menu fileMenu = new Menu("File");
	    
	    // Creating MenuItems
	    MenuItem exitItem = new MenuItem("Exit");
	    MenuItem loadConfigItem = new MenuItem("Load Configuration");
	    MenuItem saveConfigItem = new MenuItem("Save Configuration");
	    MenuItem configureArenaItem = new MenuItem("Configure Arena");
	    MenuItem editArenaItem = new MenuItem("Edit Arena");

	    // Setting actions for MenuItems
	    loadConfigItem.setOnAction(e -> loadConfiguration());
	    saveConfigItem.setOnAction(e -> saveConfiguration());
	    configureArenaItem.setOnAction(e -> configureArena());
	    editArenaItem.setOnAction(e -> editArena());
	    exitItem.setOnAction(e -> {
	        timer.stop(); // Assuming 'timer' is an AnimationTimer or similar
	        System.exit(0);
	    });

	    // Adding MenuItems to File Menu
	    fileMenu.getItems().addAll(loadConfigItem, saveConfigItem, configureArenaItem, editArenaItem, exitItem);

	    // Help Menu
	    Menu helpMenu = new Menu("Help");
	    MenuItem aboutItem = new MenuItem("About");
	    MenuItem instructionsItem = new MenuItem("Instructions");

	    // Setting actions for Help MenuItems
	    aboutItem.setOnAction(e -> showAbout());
	    instructionsItem.setOnAction(e -> showInstructions());

	    // Adding MenuItems to Help Menu
	    helpMenu.getItems().addAll(aboutItem, instructionsItem);

	    // Adding Menus to MenuBar
	    menuBar.getMenus().addAll(fileMenu, helpMenu);

	    return menuBar;
	}

	

	
	private void loadConfiguration() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Configuration File");
	    File file = fileChooser.showOpenDialog(null); // Replace 'null' with a Stage if available
	    if (file != null) {
	        try {
	            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }
	    }
	}
	private void saveConfiguration() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Save Configuration File");
	    File file = fileChooser.showSaveDialog(null); // Replace 'null' with a Stage if available
	    if (file != null) {
	        try {
	            String content = ""; // Replace this with the actual configuration content
	            Files.write(file.toPath(), content.getBytes(StandardCharsets.UTF_8));
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }
	    }
	}

	private void showInstructions() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Instructions");
	    alert.setHeaderText("How to Use the Robot Simulation");
	    alert.setContentText("Game Robots move autonomously; avoid collisions.\n"
	                         + "Whisker Robots detect obstacles; all share 3 lives, lost on collisions with Game Robots.\n"
	                         + "Blocker Robots act as dynamic obstacles in the arena.\n"
	                         + "Control Paddle Robots by clicking to select and move them.\n"
	                         + "Game ends if Whisker Robots lose all lives; use 'Start' and 'Pause' to control simulation.\n");
	    alert.showAndWait();
	}
	
	private void configureArena() {
	    // Create the custom dialog.
	    Dialog<String> dialog = new Dialog<>();
	    dialog.setTitle("Configure Arena");

	    // Set the button types.
	    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	    // Create a grid pane and set its properties
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(20, 150, 10, 10));

	    TextField arenaWidth = new TextField();
	    arenaWidth.setPromptText("Width");
	    TextField arenaHeight = new TextField();
	    arenaHeight.setPromptText("Height");

	    grid.add(arenaWidth, 0, 0);
	    grid.add(arenaHeight, 1, 0);

	    dialog.getDialogPane().setContent(grid);

	    // Convert the result to a width-height pair when the OK button is clicked.
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ButtonType.OK) {
	            return arenaWidth.getText() + "," + arenaHeight.getText();
	        }
	        return null;
	    });

	    dialog.showAndWait().ifPresent(arenaSize -> {
	        // Handle the results here. Example:
	        System.out.println("Arena size: " + arenaSize); // Replace with actual configuration logic
	    });
	}
	
	private void editArena() {
	    Dialog<String> dialog = new Dialog<>();
	    dialog.setTitle("Edit Arena");

	    // Set the button types.
	    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(20, 150, 10, 10));

	    // Assuming you want to edit properties like robot position, size, etc.
	    // The following is a simple example. You will need to adapt this based on how your robots are structured.
	    TextField robotID = new TextField();
	    robotID.setPromptText("Robot ID");
	    TextField newPositionX = new TextField();
	    newPositionX.setPromptText("New X Position");
	    TextField newPositionY = new TextField();
	    newPositionY.setPromptText("New Y Position");

	    grid.add(new Label("Robot ID:"), 0, 0);
	    grid.add(robotID, 1, 0);
	    grid.add(new Label("New X Position:"), 0, 1);
	    grid.add(newPositionX, 1, 1);
	    grid.add(new Label("New Y Position:"), 0, 2);
	    grid.add(newPositionY, 1, 2);

	    dialog.getDialogPane().setContent(grid);

	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ButtonType.OK) {
	            return robotID.getText() + "," + newPositionX.getText() + "," + newPositionY.getText();
	        }
	        return null;
	    });

	    dialog.showAndWait().ifPresent(result -> {
	        // Handle the results here. Example:
	        String[] parts = result.split(",");
	        if (parts.length == 3) {
	            int id = Integer.parseInt(parts[0]);
	            double x = Double.parseDouble(parts[1]);
	            double y = Double.parseDouble(parts[2]);
	            
	            
	        }
	    });
	}

	private void updateRobotPosition(int robotID, double newX, double newY) {
	    // Iterate through all robots in the arena
	    for (Robot robot : arena.allRobots) {
	        // Check if the current robot's ID matches the given ID
	        if (robot.getID() == robotID) {
	            // Update the robot's position
	            robot.setXY(newX, newY);
	            break; // Stop the loop once the matching robot is found and updated
	        }
	    }
	}

	
	
	private HBox setButtons() {
	    Button btnStart = new Button("Start");					// create button for starting
	    btnStart.setOnAction(new EventHandler<ActionEvent>() {	// now define event when it is pressed
	        @Override
	        public void handle(ActionEvent event) {
	        	timer.start();									// its action is to start the timer
	       }
	    });

	    Button btnStop = new Button("Pause");					// now button for stop
	    btnStop.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	timer.stop();									// and its action to stop the timer
	       }
	    });

	    Button btnAdd = new Button("Another Robot");				// now button for stop
	    btnAdd.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	           	arena.addRobot(null);								// and its action to stop the timer
	           	drawWorld();
	       }
	    });
	    
	    Button btnAddWhisker = new Button("Add Whisker Robot");
	    btnAddWhisker.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	  double newX = Math.random() * mc.getXCanvasSize(); // Random X within canvas size
	        	    double newY = Math.random() * mc.getYCanvasSize(); // Random Y within canvas size
	        	    arena.addWhiskerRobot(newX, newY, 15, 45, 5); // Modify as needed
	        }
	    });

	    Button btnDelete = new Button("Delete Selected Robot");
	    btnDelete.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            if (selectedRobot != null) {
	                arena.removeRobot(selectedRobot);
	                selectedRobot = null; // Clear the selection
	                drawWorld(); // Redraw to show changes
	            } else {
	                System.out.println("No robot selected to delete.");
	            }
	        }
	    });
	    
	    // adding buttons + labels to a HBox
	    return new HBox(new Label("Run: "), btnStart, btnStop, new Label("Add: "), btnAdd, btnAddWhisker,btnDelete);
	}

	/**
	 * Show the score .. by writing it at position x,y
	 * @param x
	 * @param y
	 * @param score
	 */
	public void showScore (double x, double y, int score) {
		mc.showText(x, y, Integer.toString(score));
	}
	/** 
	 * draw the world with robot in it
	 */
	public void drawWorld() {
		mc.clearCanvas(); // set beige colour

		arena.drawArena(mc);
	}
	
	/**
	 * show where robot is, in pane on right
	 */
	public void drawStatus() {
	    rtPane.getChildren().clear();
	    ArrayList<String> allRs = arena.describeAll();
	    for (String s : allRs) {
	        Label l = new Label(s);
	        rtPane.getChildren().add(l);
	    }

	    // Display the collective number of lives for all WhiskerRobots
	    Label livesLabel = new Label("Whisker Robots Lives: " + arena.getWhiskerRobotLives());
	    rtPane.getChildren().add(livesLabel);
	}

	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Hennas attempt at Moving Robot");
	    BorderPane bp = new BorderPane();
	    bp.setPadding(new Insets(10, 20, 10, 20));

	    bp.setTop(setMenu());											// put menu at the top

	    Group root = new Group();										// create group with canvas
	    Canvas canvas = new Canvas( 400, 500 );
	    root.getChildren().add( canvas );
	    bp.setLeft(root);												// load canvas to left area
	
	    mc = new MyCanvas(canvas.getGraphicsContext2D(), 400, 500);

	    setMouseEvents(canvas);											// set up mouse events

	    arena = new RobotArena(400, 500);								// set up arena
	    drawWorld();
	    
	    timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (arena.isGameOver()) {
                    this.stop(); // Stop updating if the game is over
                    return;
                }

                // Existing game loop logic
                arena.checkRobot();
                arena.adjustRobot();
                drawWorld();
                drawStatus();
	        }
	    };

	    rtPane = new VBox();											// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5));					// padding
 		bp.setRight(rtPane);											// add rtPane to borderpane right
		  
	    bp.setBottom(setButtons());										// set bottom pane with buttons

	    Scene scene = new Scene(bp, 700, 600);							// set overall scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setScene(scene);
        primaryStage.show();


	}
	public static void main(String[] args) {
	    Application.launch(args);			// launch the GUI

	}

}