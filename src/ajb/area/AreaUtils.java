package ajb.area;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

public class AreaUtils {

	public static Area mirrorAlongX(double x, Area area) {

		AffineTransform at = new AffineTransform();
		at.translate(x, 0);
		at.scale(-1, 1);
		at.translate(-x, 0);
		return new Area(at.createTransformedShape(area));

	}

	public static Area flipVertically(Area area) {

		AffineTransform at = new AffineTransform();
		at.scale(1, -1);
		return new Area(at.createTransformedShape(area));

	}

	public static Area translateToTopLeft(Area area) {

		AffineTransform at = new AffineTransform();
		at.translate(area.getBounds2D().getMinX() * -1, area.getBounds2D().getMinY() * -1);
		return area.createTransformedArea(at);

	}

	public static Area translateToPoint(Area area, Point2D.Double point) {

		AffineTransform at = new AffineTransform();
		at.translate(point.x, point.y);
		return area.createTransformedArea(at);

	}

	public static Area getOutline(Area area) {
		Area ret = new Area();
		double[] coords = new double[6];
		GeneralPath tmpPath = new GeneralPath();

		PathIterator pathIterator = area.getPathIterator(null);
		for (; !pathIterator.isDone(); pathIterator.next()) {
			int type = pathIterator.currentSegment(coords);
			switch (type) {
			case PathIterator.WIND_EVEN_ODD:
				tmpPath.reset();
				tmpPath.moveTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_LINETO:
				tmpPath.lineTo(coords[0], coords[1]);
				break;
			case PathIterator.SEG_QUADTO:
				tmpPath.quadTo(coords[0], coords[1], coords[2], coords[3]);
				break;
			case PathIterator.SEG_CUBICTO:
				tmpPath.curveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
				break;
			case PathIterator.SEG_CLOSE:
				ret.add(new Area(tmpPath));
				break;
			default:
				System.err.println("Unhandled type " + type);
				break;
			}
		}

		return ret;
	}

}
