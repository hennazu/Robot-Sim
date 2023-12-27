package testjfx;

/**
 * Represents a GameRobot in the robot simulation.
 * GameRobot is designed to move around the arena at a specified angle and speed.
 */
public class GameRobot extends Robot {

    double rAngle; // Angle of travel in degrees
    double rSpeed; // Speed of travel

    /**
     * Default constructor for GameRobot.
     */
    public GameRobot() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructs a GameRobot with specified position, size, angle, and speed.
     *
     * @param ix The initial x-coordinate.
     * @param iy The initial y-coordinate.
     * @param ir The radius of the robot.
     * @param ia The initial angle of travel in degrees.
     * @param is The speed of travel.
     */
    public GameRobot(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        rAngle = ia;
        rSpeed = is;
    }

    /**
     * Checks and updates the robot's angle of travel based on arena conditions.
     * Changes the angle if the robot hits a wall or another robot.
     *
     * @param r The RobotArena in which the robot exists.
     */
    @Override
    protected void checkRobot(RobotArena r) {
        rAngle = r.CheckRobotAngle(x, y, rad, rAngle, robotID);
    }

    /**
     * Adjusts the GameRobot's position based on its current angle and speed.
     */
    @Override
    protected void adjustRobot() {
        double radAngle = Math.toRadians(rAngle); // Convert angle to radians
        x += rSpeed * Math.cos(radAngle);         // Update X position
        y += rSpeed * Math.sin(radAngle);         // Update Y position
    }

    /**
     * Returns a string representation of the robot's type.
     *
     * @return A string indicating this is a "Game robot" type.
     */
    @Override
    protected String getStrType() {
        return "Game robot";
    }
}