package testjfx;
/**
 * Represents a BlockerRobot in the robot simulation.
 * BlockerRobot serves as an obstacle within the arena.
 */
public class BlockerRobot extends Robot {

    /**
     * Default constructor for BlockerRobot.
     */
    public BlockerRobot() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructs a BlockerRobot with specified coordinates and size.
     *
     * @param ix The initial x-coordinate.
     * @param iy The initial y-coordinate.
     * @param ir The radius of the robot.
     */
    public BlockerRobot(double ix, double iy, double ir) {
        super(ix, iy, ir);
        col = 'p'; // Sets the colour of the BlockerRobot
    }

    /**
     * Draws the BlockerRobot on the canvas.
     * This robot is represented only as a circle without wheels.
     *
     * @param mc The canvas where the robot is drawn.
     */
    @Override
    public void drawRobot(MyCanvas mc) {
        mc.showCircle(x, y, rad, col);
    }

    /**
     * Checks the state of the BlockerRobot in the arena.
     * Currently, this method does not implement any specific behaviour.
     *
     * @param b The RobotArena in which the robot exists.
     */
    @Override
    protected void checkRobot(RobotArena b) {
        // Currently no specific check implemented for BlockerRobot
    }

    /**
     * Adjusts the BlockerRobot's position or state.
     * Currently, this method does not implement any specific behaviour.
     */
    @Override
    protected void adjustRobot() {
        // Currently no adjustment needed for BlockerRobot
    }

    /**
     * Returns a string representation of the robot's type.
     *
     * @return A string indicating this is a "Blocker" type robot.
     */
    @Override
    protected String getStrType() {
        return "Blocker";
    }
}