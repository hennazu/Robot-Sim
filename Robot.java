package testjfx;

/**
 * Represents an abstract base class for all robot types in the robot
 * simulation.
 */
public abstract class Robot {
	protected double x, y, rad; // X and Y positions, and radius of the robot
	protected char col; // Colour of the robot
	static int robotCounter = 0; // Static counter to assign unique IDs to robots
	protected int robotID; // Unique identifier for the robot
	protected double rAngle; // Angle of rotation for the robot

	/**
	 * Default constructor which initialises the robot with default values.
	 */

	Robot() {
		this(100, 100, 10);
	}

	/**
	 * Constructor to initialise a robot with specific position and size.
	 *
	 * @param ix Initial x-coordinate of the robot.
	 * @param iy Initial y-coordinate of the robot.
	 * @param ir Radius of the robot.
	 */

	Robot(double ix, double iy, double ir) {
		x = ix;
		y = iy;
		rad = ir;
		robotID = robotCounter++; // set the identifier and increment class static
		col = 'r';
	}

	// Getter methods
	/**
	 * return x position
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * return y position
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * return radius of ball
	 * 
	 * @return
	 */
	public double getRad() {
		return rad;
	}

	/**
	 * set the ball at position nx,ny
	 * 
	 * @param nx
	 * @param ny
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}

	/**
	 * return the identity of ball
	 * 
	 * @return
	 */
	public int getID() {
		return robotID;
	}

	/**
	 * Draws the robot on a canvas. This method must be overridden by subclasses to
	 * define specific robot appearance.
	 *
	 * @param mc The canvas on which the robot is to be drawn.
	 */

	public void drawRobot(MyCanvas mc) {
		mc.showCircle(x, y, rad, col); // Draw the robot's body as a circle

		mc.showCircle(x, y, rad, col);

		// Wheel radius, smaller than the robot's radius
		double wheelRad = rad * 0.2;

		// Calculate positions for wheels
		double[] wheelAngles = new double[] { 45, 135, 225, 315 }; // Angles for wheel positions
		for (double angle : wheelAngles) {
			double wheelX = x + rad * Math.cos(Math.toRadians(angle));
			double wheelY = y + rad * Math.sin(Math.toRadians(angle));
			mc.drawSmallCircle(wheelX, wheelY, wheelRad);
			double ang = Math.toRadians(rAngle); // Convert angle to radians for trigonometric calculations

		}
	}

	/**
	 * Returns a string representation of the robot.
	 *
	 * @return A string describing the type and position of the robot.
	 */

	protected String getStrType() {
		return "Robot";
	}

	@Override
	public String toString() {
		return getStrType() + " at " + Math.round(x) + " , " + Math.round(y);
	}

	/**
	 * Adjusts the robot's position or state. This method must be implemented by
	 * subclasses to define specific robot behaviour.
	 */

	protected void adjustRobot() {

		// Regular movement logic
	}

	// abstract method for checking a ball in arena b

	/**
	 * Checks the robot's interaction with the arena or other robots. This method
	 * must be implemented by subclasses for specific robot interactions.
	 *
	 * @param r The RobotArena in which the robot is located.
	 */

	protected abstract void checkRobot(RobotArena r);

	/**
	 * Determines if the robot is hitting another object at given coordinates with a
	 * specified radius.
	 *
	 * @param ox X-coordinate of the other object.
	 * @param oy Y-coordinate of the other object.
	 * @param or Radius of the other object.
	 * @return True if the robot is hitting the other object, otherwise False.
	 */

	public boolean hitting(double ox, double oy, double or) {
		return (ox - x) * (ox - x) + (oy - y) * (oy - y) < (or + rad) * (or + rad);
	}

	public boolean hitting(Robot oRobot) {
		return hitting(oRobot.getX(), oRobot.getY(), oRobot.getRad());
	}

}