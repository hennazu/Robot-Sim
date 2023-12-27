package testjfx;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;


/**
 * Represents the arena where the robots are placed and interact.
 */

public class RobotArena {	
	double xSize, ySize;						// size of arena
	ArrayList<Robot> allRobots;			// array list of all balls in arena
	 private AnimationTimer timer;
	 private boolean isGameOver = false;
	 private int whiskerRobotLives = 3;
	 
	/**
	 * construct an arena
	 */
	RobotArena() {
		this(500, 400);			// default size
	}
	
	/**
	 * construct arena of size xS by yS
	 * @param xS
	 * @param yS
	 */
	RobotArena(double xS, double yS){
		xSize = xS;
		ySize = yS;
		allRobots = new ArrayList<Robot>();					// list of all balls, initially empty
		allRobots.add(new TargetRobot(100, 50, 15));			// add target robot
		allRobots.add(new PaddleRobot(100, yS - 50, 20));   		// add paddle
		allRobots.add(new BlockerRobot(xS/4, yS/4, 15));     // add blocker robot in upper left
		allRobots.add(new BlockerRobot(50, 50, 15));   // add blocker robot in upper right
		allRobots.add(new BlockerRobot(xS - 50, 50, 15));    // add blocker robot in lower left
		allRobots.add(new BlockerRobot(xS - 50, yS - 50, 15)); // add blocker robot
		allRobots.add(new GameRobot(xS/2, yS/2, 10, 45, 10)); // add game robot
		allRobots.add(new WhiskerRobot(100, 200, 15, 45, 5)); 
		
	}
	
	public ArrayList<Robot> getAllRobots() {
        return allRobots;
    }



	/**
	 * return arena size in x direction
	 * @return
	 */
	public double getXSize() {
		return xSize;
	}
	/**
	 * return arena size in y direction
	 * @return
	 */
	public double getYSize() {
		return ySize;
	}
	/**
	 * draw all robots in the arena into interface bi
	 * @param bi
	 */
	public void drawArena(MyCanvas mc) {
		for (Robot b : allRobots) b.drawRobot(mc);		// draw all robots
	}
	/**
	 * check all robots .. see if need to change angle of moving robots, etc 
	 */
    // Check all robots and handle collisions
    public void checkRobot() {
        for (Robot robot : allRobots) {
            if (robot instanceof GameRobot) {
                for (Robot otherRobot : allRobots) {
                    if (otherRobot instanceof WhiskerRobot && robot.hitting(otherRobot)) {
                        ((WhiskerRobot) otherRobot).loseLife(this);
                    }
                }
            }
            robot.checkRobot(this);
        }
    }

    
    
    public Robot getRobotAtPosition(double x, double y) {
        for (Robot robot : allRobots) {
            if (Math.hypot(robot.getX() - x, robot.getY() - y) <= robot.getRad()) {
                return robot; // Found a robot at this position
            }
        }
        return null; // No robot at this position
    }

    public void removeRobot(Robot robot) {
        allRobots.remove(robot);
    }
    
    
 // Add a method to set the timer
    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }
    
    
    // Game over method
    public void gameOver() {
        if (isGameOver) {
            return; // Prevent repeated processing
        }
        isGameOver = true;

        if (timer != null) {
            timer.stop();
        }

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over! Whisker Robot lost all lives.");
            alert.showAndWait();
            // Consider adding Platform.exit() here if you want to close the entire application
        });
    }

    public boolean isGameOver() {
        return isGameOver;
    }
	
    /**
	 * adjust all robots .. move any moving ones
	 */
	public void adjustRobot() {
		for (Robot b : allRobots) b.adjustRobot();
	}
	
	/** 
	 * set the paddle robots at x,y
	 * @param x
	 * @param y
	 */
	public void setPaddle(double x, double y) {
		for (Robot b : allRobots)
			if (b instanceof PaddleRobot) b.setXY(x, y);
	}
	/**
	 * return list of strings defining each robot
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for(Robot b : allRobots) ans.add(b.toString());			// add string defining each robots
		return ans;												// return string list
	}
	/** 
	 * Check angle of ball ... if hitting wall, rebound; if hitting robots, change angle
	 * @param x				robots x position
	 * @param y				y
	 * @param rad			radius
	 * @param ang			current angle
	 * @param notID			identify of robots not to be checked
	 * @return				new angle 
	 */
	public double CheckRobotAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 180 - ans;
			// if robot hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = - ans;
			// if try to go off top or bottom, set mirror angle
		
		for (Robot b : allRobots) 
			if (b.getID() != notID && b.hitting(x, y, rad)) ans = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;
				// check all robots except one with given id
				// if hitting, return angle between the other robot and this one.
		
		return ans;		// return the angle
	}

	// check if the target robot has been hit by another robot
	
	
	public boolean checkHit(Robot target) {
		boolean ans = false;
		for (Robot b : allRobots)
			if (b instanceof GameRobot && b.hitting(target)) ans = true;
				// try all robots, if GameRobot, check if hitting the target
		return ans; // Return true if any game robot hits the target
	}
	
	  // Adds a new generic robot to the arena
	public void addRobot(BlockerRobot newObstacle) {
		allRobots.add(new GameRobot(xSize/2, ySize/2, 10, 60, 5));
		
	}
	
	// Adds a new WhiskerRobot to the arena
	public void addWhiskerRobot(double x, double y, double radius, double angle, double speed) {
	    WhiskerRobot newRobot = new WhiskerRobot(x, y, radius, angle, speed);
	    allRobots.add(newRobot);
	}

	public void whiskerRobotHit() {
        whiskerRobotLives--;
        if (whiskerRobotLives <= 0) {
            gameOver(); // Call gameOver if no lives left
        }
    }

    // Getter for WhiskerRobot lives
    public int getWhiskerRobotLives() {
        return whiskerRobotLives;
    }

}