package testjfx;

/**
 * Represents a target robot in the robot simulation.
 * This robot keeps track of the score based on how many times it has been hit.
 */
public class TargetRobot extends Robot {
    private int score; // Score for the target robot

    /**
     * Default constructor for TargetRobot.
     */
    public TargetRobot() {
        super();
    }

    /**
     * Constructs a TargetRobot with specified position and radius.
     * @param ix The initial x-position of the robot.
     * @param iy The initial y-position of the robot.
     * @param ir The radius of the robot.
     */
    public TargetRobot(double ix, double iy, double ir) {
        super(ix, iy, ir); // Calls the constructor of the parent Robot class
        score = 0; // Initialise score
        col = 'b'; // Set colour of the target robot (can be adjusted)
    }

    /**
     * Checks if the TargetRobot has been hit and increments the score if so.
     * @param b The RobotArena in which this robot resides.
     */
    @Override
    protected void checkRobot(RobotArena b) {
        if (b.checkHit(this)) {
            score++; // Increment score if hit
        }
    }

    /**
     * Draws the TargetRobot on the canvas.
     * @param mc The MyCanvas instance used for drawing.
     */
    @Override
    public void drawRobot(MyCanvas mc) {
        // Drawing logic can be added here
    }

    /**
     * Adjusts the TargetRobot's position. This method is intended for future use.
     */
    @Override
    protected void adjustRobot() {
        // Adjustment logic can be added if the robot needs to move
    }

    /**
     * Returns the string representation of the TargetRobot's type.
     * @return A string indicating the type of robot.
     */
    @Override
    protected String getStrType() {
        return "Target";
    }
}