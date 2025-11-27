package geometry;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Point tacka1 = new Point();
		// tacka1.xCoordinate = 5;
		tacka1.setXCoordinate(2);
		tacka1.setYCoordinate(6);
		System.out.println("X kooridinata je: " + tacka1.getXCoordinate());
		System.out.println("Y koordinata je: " + tacka1.getYCoordinate());

		Point tacka2 = new Point();
		tacka2.setXCoordinate(7);
		tacka2.setYCoordinate(1);
		System.out.println("X kooridinata je: " + tacka2.getXCoordinate());
		System.out.println("Y koordinata je: " + tacka2.getYCoordinate());
		// distance

		double result = tacka1.distance(tacka2);
		System.out.println("Udaljenost dve tacke je:" + result);

		Line line1 = new Line();

		// zadatak 3

		tacka1.setXCoordinate(tacka2.getYCoordinate());

		// zadatak 5
		line1.setStartPoint(tacka1);
		line1.setEndPoint(tacka2);

		// zadatak 4
		// pristupamo x i y kordinati start tacke i ispisujemo
		System.out.println("Start x:" + line1.getStartPoint().getXCoordinate());
		System.out.println("Start y:" + line1.getStartPoint().getYCoordinate());

		System.out.println("End x:" + line1.getEndPoint().getXCoordinate());
		System.out.println("End y:" + line1.getEndPoint().getYCoordinate());

		// 6.
		line1.getEndPoint().setYCoordinate(23);
		// ne moze set na set vec prvo moramo get da dobijemo end point i onda menjamo
		// njenu y koord

		System.out.println("End y:" + line1.getEndPoint().getYCoordinate());
		System.out.println("Point2 y:" + tacka2.getYCoordinate());
		// prenos po referenci (referenca je pokaz u memoriji na vrednost)

		// 7
		line1.getStartPoint().setXCoordinate(line1.getEndPoint().getYCoordinate());
		// 8

		System.out.println("point1 x:" + tacka1.getXCoordinate());
		System.out.println("point2 y:" + tacka2.getYCoordinate());
		// 9
		// math abs je apsolutna vrednost
		line1.getEndPoint().setXCoordinate(Math.abs((int) line1.length()
				- (line1.getStartPoint().getXCoordinate() + line1.getStartPoint().getYCoordinate())));
		System.out.println(line1.getEndPoint().getXCoordinate());
		// 10
		Rectangle rect1 = new Rectangle();
		Circle circle1 = new Circle();

		// 11
		Point upperLeftPoint = new Point();
		upperLeftPoint.setXCoordinate(10);
		upperLeftPoint.setYCoordinate(15);
		rect1.setUpperLeftPoint(upperLeftPoint);

		// 12
		circle1.setCenter(upperLeftPoint);

		// 13
		circle1.getCenter().setXCoordinate(rect1.area() - line1.getStartPoint().getYCoordinate());

		// zadatak vezbe 4
		circle1.setCenter(tacka1);
		circle1.getCenter()
				.setXCoordinate(circle1.getRadius() + (int) line1.getStartPoint().distance(line1.getEndPoint()));
		System.out.println(circle1.getCenter().getXCoordinate());

		Point p3 = new Point(2, 4, true);
		System.out.println("Point p3:" + p3);
		System.out.println("Point p3 x:" + p3.getXCoordinate());
		System.out.println("Point p3 y:" + p3.getYCoordinate());
		System.out.println("Point p3 selected:" + p3.isSelected());

		System.out.println(line1);
		System.out.println(new Rectangle(tacka1, 50, 60));

		Point p4 = new Point(2, 4, false);
		System.out.println(p3 == p4);
		System.out.println(p3.equals(p4));

		// sedme vezbe
		Point p10 = new Point(50, 50);
		Rectangle r10 = new Rectangle(p10, 10, 10);
		Rectangle r20 = new Rectangle(p10, 20, 20);
		Rectangle r30 = new Rectangle(p10, 30, 30);
		Shape[] rectangles = { r30, r10, r20 };
		System.out.println("\nNesortiran niz: ");
		for (int i = 0; i < rectangles.length; i++) {
			System.out.println(rectangles[i]);
		}
		// u pozadini sortira po compareTo
		Arrays.sort(rectangles);
		System.out.println("Sortiran niz: ");
		for (int i = 0; i < rectangles.length; i++) {
			System.out.println(rectangles[i]);
		}

	}
}
