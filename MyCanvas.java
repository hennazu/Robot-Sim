package testjfx;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

/**
 * MyCanvas is a utility class for drawing elements 
 * It provides methods to draw circles, images, lines, and text with specific properties.
 */
public class MyCanvas {
	int xCanvasSize; // Width of the canvas
	int yCanvasSize; // Height of the canvas
	GraphicsContext gc; // GraphicsContext for drawing on the canvas

	/**
	 * Constructor to create a MyCanvas object with specific dimensions and
	 * GraphicsContext.
	 *
	 * @param g   The GraphicsContext associated with the canvas.
	 * @param xcs Width of the canvas.
	 * @param ycs Height of the canvas.
	 */
	public MyCanvas(GraphicsContext g, int xcs, int ycs) {
		gc = g;
		xCanvasSize = xcs;
		yCanvasSize = ycs;
	}

	/**
	 * Constructor to create a MyCanvas object with the specified GraphicsContext.
	 *
	 * @param gc The GraphicsContext associated with the canvas.
	 */
	public MyCanvas(GraphicsContext gc) {
		this.gc = gc;
	}

	// Getter methods
	public GraphicsContext getGraphicsContext2D() {
		return gc;
	}

	public int getXCanvasSize() {
		return xCanvasSize;
	}

	public int getYCanvasSize() {
		return yCanvasSize;
	}

	/**
	 * Clears the entire canvas.
	 */
	public void clearCanvas() {
		gc.clearRect(0, 0, xCanvasSize, yCanvasSize);
	}

	/**
	 * Draws an image on the canvas.
	 *
	 * @param i  The image to be drawn.
	 * @param x  X-coordinate for the image.
	 * @param y  Y-coordinate for the image.
	 * @param sz Size of the image.
	 */
	public void drawIt(Image i, double x, double y, double sz) {
		gc.drawImage(i, xCanvasSize * (x - sz / 2), yCanvasSize * (y - sz / 2), xCanvasSize * sz, yCanvasSize * sz);
	}

	/**
	 * Converts a character representing a colour into the corresponding Colour
	 * object.
	 *
	 * @param c Character representing the colour.
	 * @return The Colour object corresponding to the character.
	 */

	Color colFromChar(char c) {
		Color ans = Color.BLACK;
		switch (c) {
		case 'y':
			ans = Color.GRAY;
			break;
		case 'w':
			ans = Color.WHITE;
			break;
		case 'r':
			ans = Color.RED;
			break;
		case 'g':
			ans = Color.GREEN;
			break;
		case 'b':
			ans = Color.BLUE;
			break;
		case 'o':
			ans = Color.ORANGE;
			break;
		case 'p':
			ans = Color.PINK;
			break;
		case 'i':
			ans = Color.PURPLE;
			break;

		}
		return ans;
	}

	public void setFillColour(Color c) {
		gc.setFill(c);
	}

	/**
	 * Displays a circle on the canvas at a specified location, size, and color.
	 *
	 * @param x   X-coordinate of the circle's center.
	 * @param y   Y-coordinate of the circle's center.
	 * @param rad Radius of the circle.
	 * @param col Character representing the colour of the circle.
	 */

	public void showCircle(double x, double y, double rad, char col) {
		Color color = colFromChar(col);
		setFillColour(color);
		gc.fillArc(x - rad, y - rad, rad * 2, rad * 2, 0, 360, ArcType.ROUND);
	}

	/**
	 * Draws a small circle on the canvas at the specified position with a given
	 * radius. The circle is always drawn in black.
	 *
	 * @param x   The x-coordinate of the center of the circle.
	 * @param y   The y-coordinate of the center of the circle.
	 * @param rad The radius of the circle.
	 */

	public void drawSmallCircle(double x, double y, double rad) {
		setFillColour(Color.BLACK);
		gc.fillArc(x - rad, y - rad, rad * 2, rad * 2, 0, 360, ArcType.ROUND);
	}

	/**
	 * Draws a line representing the wheels of a robot on the canvas. The line is
	 * drawn in black and is intended to visually represent wheels between two
	 * points.
	 *
	 * @param x1 The x-coordinate of the start of the line.
	 * @param y1 The y-coordinate of the start of the line.
	 * @param x2 The x-coordinate of the end of the line.
	 * @param y2 The y-coordinate of the end of the line.
	 */

	public void showWheels(double x1, double y1, double x2, double y2) {
		setFillColour(Color.BLACK);
		gc.setLineWidth(6);
		gc.strokeLine(x1, y1, x2, y2);
	}

	/**
	 * Displays text at a specified position on the canvas. The text is centered
	 * both horizontally and vertically at the specified coordinates.
	 *
	 * @param x The x-coordinate where the text is to be displayed.
	 * @param y The y-coordinate where the text is to be displayed.
	 * @param s The string of text to be displayed.
	 */

	public void showText(double x, double y, String s) {
		gc.setTextAlign(TextAlignment.CENTER); // set horizontal alignment
		gc.setTextBaseline(VPos.CENTER); // vertical
		gc.setFill(Color.WHITE); // colour in white
		gc.fillText(s, x, y); // print score as text
	}

	/**
	 * Displays an integer as text at a specified position on the canvas. This
	 * method is a convenience wrapper around showText.
	 *
	 * @param x The x-coordinate where the text is to be displayed.
	 * @param y The y-coordinate where the text is to be displayed.
	 * @param i The integer to be displayed.
	 */

	public void showInt(double x, double y, int i) {
		showText(x, y, Integer.toString(i));
	}

	/**
	 * Draws a line on the canvas between two specified points. The line is drawn in
	 * black with a default width.
	 *
	 * @param startX The x-coordinate of the start of the line.
	 * @param startY The y-coordinate of the start of the line.
	 * @param endX   The x-coordinate of the end of the line.
	 * @param endY   The y-coordinate of the end of the line.
	 */

	public void showLines(double startX, double startY, double endX, double endY) {
		gc.setStroke(Color.BLACK); // Set the colour of the line
		gc.setLineWidth(2); // Set the line width
		gc.strokeLine(startX, startY, endX, endY); // Draw the line
	}
	
}