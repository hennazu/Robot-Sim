package testjfx;
/**
 * Represents a paddle robot in the robot simulation.
 * The paddle robot is a stationary robot that can be controlled by the user.
 */
public class PaddleRobot extends Robot {

    /**
     * Draws the paddle robot on the canvas.
     * This method overrides the drawRobot method in the Robot class.
     * Only the body of the paddle robot is drawn, without any additional features like wheels.
     *
     * @param mc The MyCanvas object on which the robot is drawn.
     */
    @Override
    public void drawRobot(MyCanvas mc) {
        mc.showCircle(x, y, rad, col); // Draw the paddle robot's body as a circle
    }
    
    /**
     * Default constructor for creating a paddle robot.
     */
    public PaddleRobot() {
        // Constructor implementation (possibly empty if no additional setup is required)
    }

    /**
     * Constructs a paddle robot with specified position and size.
     *
     * @param ix The x-coordinate of the paddle robot.
     * @param iy The y-coordinate of the paddle robot.
     * @param ir The radius of the paddle robot.
     */
    public PaddleRobot(double ix, double iy, double ir) {
        super(ix, iy, ir); // Call the superclass constructor
        col = 'i';         // Set the colour of the paddle robot
    }

    /**
     * Checks and updates the paddle robot's status.
     * This method is currently not implemented as the paddle robot does not have any specific checks.
     *
     * @param r The RobotArena object that manages the robots.
     */
    @Override
    protected void checkRobot(RobotArena r) {
        // No specific checks required for the paddle robot
    }

    /**
     * Adjusts the paddle robot's position.
     * This method is currently not implemented as the paddle robot does not move.
     */
    @Override
    protected void adjustRobot() {
        // The paddle robot does not move
    }
    
    /**
     * Returns a string representation of the paddle robot type.
     *
     * @return A string indicating the type of robot.
     */
    protected String getStrType() {
        return "Paddle";
    }   
}