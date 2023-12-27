package testjfx;

/**
 * Represents a robot with whiskers used for detecting obstacles or other entities.
 */
public class WhiskerRobot extends Robot {

    private double rSpeed; // Speed of the robot
    private double rAngle; // Angle of movement in degrees
    private int lives = 3; // Start with 3 lives

    /**
     * Constructor for creating a WhiskerRobot.
     * 
     * @param ix The initial x-coordinate of the robot.
     * @param iy The initial y-coordinate of the robot.
     * @param ir The radius of the robot.
     * @param ia The initial angle of movement in degrees.
     * @param is The speed of the robot.
     */
    public WhiskerRobot(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir); // Initialise the super class (Robot)
        rAngle = ia; // Set the angle
        rSpeed = is; // Set the speed
        col = 'g'; // Set the colour for the WhiskerRobot
    }

    /**
     * Draws the WhiskerRobot on the canvas, including its whiskers.
     * 
     * @param mc The canvas where the robot is drawn.
     */
    @Override
    public void drawRobot(MyCanvas mc) {
        super.drawRobot(mc); // Call the basic robot drawing method from the Robot class

        // Define the length and angle of the whiskers
        double whiskerLength = rad * 1.5; // Length of whiskers
        double angleRadians = Math.toRadians(rAngle); // Convert angle to radians for calculations

        // Calculate and draw whiskers at slight angles from the front
        double offsetAngle = Math.toRadians(10); // Offset angle for whiskers
        double[] whiskerAngles = {-offsetAngle, offsetAngle}; // Angles for front whiskers

        for (double angle : whiskerAngles) {
            double whiskerAngle = angleRadians + angle;
            double whiskerEndX = x + (rad + whiskerLength) * Math.cos(whiskerAngle);
            double whiskerEndY = y + (rad + whiskerLength) * Math.sin(whiskerAngle);

            // Draw whiskers from the robot's edge
            mc.showLines(x + rad * Math.cos(whiskerAngle), y + rad * Math.sin(whiskerAngle), whiskerEndX, whiskerEndY);
        }
    }

    /**
     * Adjusts the position of the WhiskerRobot based on its speed and angle.
     */
    protected void adjustRobot() {
        double radAngle = Math.toRadians(rAngle); // Convert angle to radians
        x += rSpeed * Math.cos(radAngle); // Update X position based on speed and angle
        y += rSpeed * Math.sin(radAngle); // Update Y position based on speed and angle
        // Boundary checks can be implemented here if needed
    }

    /**
     * Check and update the angle of the WhiskerRobot based on collisions or arena boundaries.
     * 
     * @param r The RobotArena in which this robot resides.
     */
    
    
    @Override
    protected void checkRobot(RobotArena r) {
        // Existing angle checking logic
        rAngle = r.CheckRobotAngle(x, y, rad, rAngle, robotID);

        // Check if this WhiskerRobot has collided with a GameRobot or any other condition for being hit
        for (Robot otherRobot : r.getAllRobots()) {
            if (otherRobot != this && otherRobot instanceof GameRobot && this.hitting(otherRobot)) {
                r.whiskerRobotHit(); // Notify the arena that this WhiskerRobot was hit
                break; // Exit loop after processing collision to avoid multiple hits in one check
            }
        }       
    }  
    
    
    public void loseLife(RobotArena arena) {
        lives--;
        if (lives <= 0) {
            arena.gameOver(); // Call the gameOver method in RobotArena
        }
    }

    public int getLives() {
        return lives;
    } 
    
    @Override
    protected String getStrType() {
        return "Whisker Robot"; // Return a string that identifies this as a Whisker Robot
    }

}