package edu.umb.cs.cs410.hw03;

import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

public class TriangleAreaCalc  implements AreaCalculator {

	public TriangleAreaCalc() {}

	public float getArea(ArrayList<Point> points) {

		if (points.size() != 3) 
			throw new IllegalArgumentException("The given polygon is not a triangle.");

	  float side1, side2, side3, s, area;

	  side1 = (float)points.get(0).distance(points.get(1));
	  side2 = (float)points.get(1).distance(points.get(2));
	  side3 = (float)points.get(2).distance(points.get(0));

	  s = (side1 + side2 + side3)/2;

	  area = (float)Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));

	  if (area == 0)
	  	throw new IllegalArgumentException("Area of traingle cannot be 0. " +
	  																     "Make sure that the points are not Collinear");
	   return area;
	}

}