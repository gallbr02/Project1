/**
 * Disk.java
 * @author Brandon Gallagher
 * hw0
 *
 *Representation of a circular drawable disk for the game PackingBin
 */

import java.awt.*;



public class Disk {
	private static final Color BLUE = null;
	/**
	 * Creates and returns a random color
	 * @return random color
	 */
	public static Color randomColor() {
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		Color color = new Color(red, green, blue);
		return color;
	}
	/**
	 * Initialize your member variables in the constructor
	 * create a random color and assign it as the color of the disk
	 * Assign an integer ID to the disk
	 * @param cx
	 * @param cy
	 * @param radius
	 */
	public Disk (int cx, int cy, int radius) {
		this.cx = cx;
		this.cy = cy;
		this.originalX = cx;
		this.originalY = cy;
		this.radius = radius;
		this.color = Disk.randomColor();
		id = id + count;
		count++;

	} 
	/**
	 * Draws this disk as a filled circle
	 * @param g the variable of drawing the disk inside the rectangle
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(cx - radius, cy - radius, radius*2, radius *2);
		g.setColor(color);
		g.drawString(Integer.toString(id), cx - radius, cy - radius);
	}
	/**
	 * returns the x coordinate of the center
	 * @return the cx coordinate
	 */
	public int getCX() {
		return cx;
	}
	/**
	 * returns the y coordinate of the center
	 * @return the cy coordinate
	 */
	public int getCY() {
		return cy;
	}
	/**
	 * returns the radius of the circle
	 * @return the radius
	 */
	public int getRadius() {
		return radius;	
	}
	/**
	 * returns the area of the disk in double
	 * @return the area of the disk
	 */
	public double area() {
		return area;
	}
	/**
	 * set the center of the disk to x,y
	 * @param x location in which the disk can be labeled
	 * @param y location in which the disk can be labeled
	 */
	public void setCenter(int x, int y) {
		this.cx = x;
		this.cy = y;
	}
	/**
	 * reset the center location of the disk to the original center
	 */
	public void reset() {
		this.cx = originalX;
		this.cy = originalY;
	}
	/**
	 * returns true if x,y is in the disk
	 * @param x that is inside the disk
	 * @param y that is inside the disk
	 * @return true if the parameters x, y are in the disk
	 */
	public boolean inside(int x, int y) {
		if(x < (cx + radius) && x > (cx - radius) && y < (cy + radius) && y > (cy - radius)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * returns true if the disk is within the rectangle
	 * @param x upper left corner at x
	 * @param y upper left corner at y
	 * @param w width of the disk
	 * @param h height of the disk
	 * @return returns true if the disk is within the rectangle represented by the parameters
	 */
	public boolean within(int x, int y, int w, int h) {
		if((x + w ) < (cx + radius) || x > cx - radius || (y + h) < (cy + radius) || y > cy - radius){
			return false;
		}else {
			return true;
		}
	}
	/**
	 * returns the distance between the center of this disk and xp and yp
	 * @param xp center of disk at xp 
	 * @param yp center of disk at yp
	 * @return the distance between the center of this disk and xp and yp
	 */
	public double distance(int x, int y) {
		return Math.sqrt(Math.pow(cx - x, 2.0)+ Math.pow(cy - y, 2.0));
	}
	/**
	 * returns the distance between the center of this disk and the center of d
	 * @param d the distance that the center of the disk is from
	 * @return the distance between center of disk and d
	 */
	public double distance(Disk d) {
		return Math.sqrt(Math.pow(cx - d.getCX(), 2.0)+ Math.pow(cy - d.getCY(), 2.0));
	}
	/**
	 * true if d overlaps with the disk
	 * @param d determines the overlap between the disk and itself
	 * @return true if d overlaps with the disk
	 */
	public boolean overlap(Disk d) {
		if(distance(d)< (radius + d.getRadius())) {
			return true;
		}else {
			return false;
		}
	}
	private int cx, cy;
	private int radius;
	private int originalX;
	private int originalY;
	private Color color;
	private int id;
	private double area;
	private static int count;
}
