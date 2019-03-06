package ajb.geometry;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import ajb.random.RandomInt;

public class GeometryUtils {

	public static Area randomCircular() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 400); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 50)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 50)));

			area.add(new Area(new Ellipse2D.Double(x, y, RandomInt.anyRandomIntRange(20, 34),
					RandomInt.anyRandomIntRange(20, 34))));
		}

		return area;

	}

	public static Area randomBlocks() {

		Area area = new Area();

		for (int i = 0; i < RandomInt.anyRandomIntRange(100, 200); i++) {

			int angle = RandomInt.anyRandomIntRange(0, 360);

			int x = (int) (area.getBounds2D().getCenterX()
					+ (int) (Math.cos(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 10)));
			int y = (int) (area.getBounds2D().getCenterY()
					+ (int) (Math.sin(Math.toRadians(angle)) * RandomInt.anyRandomIntRange(0, 10)));

			area.add(new Area(new Rectangle2D.Double(x, y, RandomInt.anyRandomIntRange(1, 10),
					RandomInt.anyRandomIntRange(1, 20))));
		}

		return area;

	}

	public static void addRandomBlock(Area area) {

		Point2D.Double point = GeometryUtils.findRandomPointWithinArea(area);

		area.add(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(10, 30))));

	}

	public static void addRandomBlockAlongMinX(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMinX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(1, 10), RandomInt.anyRandomIntRange(2, 10))));

	}

	public static void addRandomBlockAlongMaxX(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMaxX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMinY(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY() - 5, (int) area.getBounds2D().getMinY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void addRandomBlockAlongMaxY(Area area) {

		area.add(new Area(new Rectangle2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMaxY(), (int) area.getBounds2D().getMaxY()),
				RandomInt.anyRandomIntRange(2, 6), RandomInt.anyRandomIntRange(6, 10))));

	}

	public static void subtractRandomBlock(Area area) {

		Point2D.Double point = GeometryUtils.findRandomPointWithinArea(area);

		area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 10),
				RandomInt.anyRandomIntRange(1, 10))));

	}

	public static void subtractRandomCircle(Area area) {

		Point2D.Double point = GeometryUtils.findRandomPointWithinArea(area);

		area.subtract(new Area(new Ellipse2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 6),
				RandomInt.anyRandomIntRange(1, 6))));

	}

	public static void subtractRandomLine(Area area) {

		Point2D.Double point = GeometryUtils.findRandomPointWithinArea(area);

		int vertical = RandomInt.anyRandomIntRange(0, 4);

		if (vertical < 3) {

			area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 1),
					RandomInt.anyRandomIntRange(1, 20))));

		} else {

			area.subtract(new Area(new Rectangle2D.Double(point.x, point.y, RandomInt.anyRandomIntRange(1, 20),
					RandomInt.anyRandomIntRange(1, 1))));

		}
	}

	public static Point2D.Double findRandomPointWithinArea(Area area) {

		Point2D.Double point = new Point2D.Double(
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
				RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(), (int) area.getBounds2D().getMaxY()));

		int count = 0;

		while (!area.contains(point)) {

			point = new Point2D.Double(
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinX(), (int) area.getBounds2D().getMaxX()),
					RandomInt.anyRandomIntRange((int) area.getBounds2D().getMinY(),
							(int) area.getBounds2D().getMaxY()));

			count++;

			// Safeguard against infinite loops
			if (count == 1000) {
				break;
			}
		}

		return point;

	}
}
