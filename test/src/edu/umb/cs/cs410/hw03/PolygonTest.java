package edu.umb.cs.cs410.hw03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;

public class PolygonTest {

	@Test
	public void Triangle() {
		Polygon p = new Polygon(new ArrayList<Point>());
		p.setAreaCalculator(new TriangleAreaCalc());
		p.addPoint(new Point(0, 0));
		p.addPoint(new Point(0, 1));
		p.addPoint(new Point(1, 1));
		float actual = p.getArea();
		float expected = 0.5000001f;
		assertThat(actual, is(expected));
	}

	@Test//(expected=IllegalArgumentException.class)
	public void Rectangle() {
		Polygon p = new Polygon(new ArrayList<Point>());
		p.setAreaCalculator(new RectangleAreaCalc());
		p.addPoint(new Point(0, 0));
		p.addPoint(new Point(0, 2));
		p.addPoint(new Point(2, 2));
		p.addPoint(new Point(2, 0));
		float actual = p.getArea();
		float expected = 4f;
		assertThat(actual, is(expected));
	}

	@Test(expected=IllegalArgumentException.class)
	public void RectangleFail() {
		Polygon p = new Polygon(new ArrayList<Point>());
		p.setAreaCalculator(new RectangleAreaCalc());
		p.addPoint(new Point(0, 0));
		p.addPoint(new Point(0, 2));
		p.addPoint(new Point(2, 2));
		float actual = p.getArea();
	}

	@Test(expected=IllegalArgumentException.class)
	public void TriangleFail() {
		Polygon p = new Polygon(new ArrayList<Point>());
		p.setAreaCalculator(new TriangleAreaCalc());
		p.addPoint(new Point(0, 54));
		p.addPoint(new Point(0, 29));
		p.addPoint(new Point(2, 2));
		p.addPoint(new Point(30, 87));

		float actual = p.getArea();
	}


	@Test(expected=IllegalArgumentException.class)
	public void validRectangle() {
		Polygon p = new Polygon(new ArrayList<Point>());
		p.setAreaCalculator(new RectangleAreaCalc());
		p.addPoint(new Point(0, 5));
		p.addPoint(new Point(0, 0));
		p.addPoint(new Point(5, 5));
		p.addPoint(new Point(6, 0));

		float actual = p.getArea();
	}
}