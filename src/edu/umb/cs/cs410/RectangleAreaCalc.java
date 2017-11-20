package edu.umb.cs.cs410.hw03;
import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

public class RectangleAreaCalc implements AreaCalculator {

	public RectangleAreaCalc() {}

	public float getArea(ArrayList<Point> points) {

		if (points.size() != 4) 
			throw new IllegalArgumentException("The given polygon is not a quadrillateral.");

		points = heuristicShuffle(points);

		if (!validRectangle(points)) 
			throw new IllegalArgumentException("The given quadrillateral is not a rectangle.!!");

		float s1 = (float)(Point.distance(points.get(0).getX(), points.get(0).getY(),
			                              points.get(1).getX(), points.get(1).getY()));
		float s2 = (float)(Point.distance(points.get(1).getX(), points.get(1).getY(),
			                              points.get(2).getX(), points.get(2).getY()));
	  return s1 * s2;
	}

	private static ArrayList<Point> heuristicShuffle(ArrayList<Point> points) {
	/* If I can make a perindicular angle out of the given points, it is more then
   * likely that this algorithm will find it. */
	
		ArrayList<Point> properOrientation = new ArrayList<>();
		Double angle;
		Point p0, p1, p2;

		for (int i = 0; i < 500; i++) {  
	
			p0 = points.get((int)Math.floor(4 * Math.random()));
			p1 = points.get((int)Math.floor(4 * Math.random()));
			while (p0.equals(p1))
				p1 = points.get((int)Math.floor(4 * Math.random()));
			p2 = points.get((int)Math.floor(4 * Math.random()));
			while (p2.equals(p0) || p2.equals(p1))
				p2 = points.get((int)Math.floor(4 * Math.random()));

			angle = Math.abs(angleBetween(p0, p1, p2));

			if (angle.equals(90.0)) {
				properOrientation.add(p2);
				properOrientation.add(p0);
				properOrientation.add(p1);
				break;
			}

		}

		for (Point p: points) {
			if (!properOrientation.contains(p))
				properOrientation.add(p);
		}

		return properOrientation;
	}

	private static boolean validRectangle(ArrayList<Point> points) { 

		Point p0, p1, p2, p3;
		Double angle0, angle1, angle2, angle3;

		p0 = points.get(0);
		p1 = points.get(1);
		p2 = points.get(2);
		p3 = points.get(3);

		angle0 = Math.abs(angleBetween(p0, p3, p1));
		angle1 = Math.abs(angleBetween(p1, p0, p2));
		angle2 = Math.abs(angleBetween(p2, p1, p3));
		angle3 = Math.abs(angleBetween(p3, p2, p0));
	
		if (angle0 != 90.0 && angle0 != 270.0)	return false;
		if (angle1 != 90.0 && angle1 != 270.0) 	return false;
		if (angle2 != 90.0 && angle2 != 270.0) 	return false;
		if (angle3 != 90.0 && angle3 != 270.0) 	return false;
			
		return true;
	}

	private static double angleBetween(Point center, Point current, Point previous) {
	/* https://stackoverflow.com/questions/7066792/angle-between-3-points-signed-bad-results */
  	return Math.toDegrees(Math.atan2(current.x - center.x,current.y - center.y)-
                        	Math.atan2(previous.x- center.x,previous.y- center.y));
	}
}