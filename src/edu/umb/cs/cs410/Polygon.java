package edu.umb.cs.cs410.hw03;

import java.awt.Point;
import java.util.ArrayList;

public class Polygon {

	private ArrayList<Point> points;
	private AreaCalculator areaCalc;


	public Polygon(ArrayList<Point> points, AreaCalculator areaCalc) {
		this.points = points;
		this.areaCalc = areaCalc;
	}

	public Polygon(ArrayList<Point> points) { 
		this.points = points;
		this.points = new ArrayList<Point>();        
	}

  public void setAreaCalc(AreaCalculator areaCalc) {
		this.areaCalc = areaCalc;
	}                                                    

	public void addPoint(Point point) {
		if (this.points.size() == 4) {
			System.out.println("Assignment only supports area calculations " +
				                 "for triangles and squares.");
			return;
		}
		if (this.points.contains(point)) {
			System.out.println("Point: " + point.toString() + " is already in polygon");
			return;
		}
		this.points.add(point);
	}

	public void setAreaCalculator(AreaCalculator areaCalc) {
		this.areaCalc = areaCalc;
	}

	public float getArea() {
		return this.areaCalc.getArea(this.points);
	}

	public static void main(String[] args) {
		ArrayList<Point> al = new ArrayList<>();
		al.add(new Point(0, 0));
		al.add(new Point(50, 50));
		al.add(new Point(0, 50));
		Polygon p = new Polygon(al, new TriangleAreaCalc());
		System.out.println(p.getArea()); //triangle's area
		p.addPoint(new Point(50, 0));
		p.setAreaCalc(new RectangleAreaCalc());
		System.out.println(p.getArea()); //rectangle's area
  }

}