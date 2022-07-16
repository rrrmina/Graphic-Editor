package shapeTool;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingStyle;

public class GPolygon extends GShapeTool {
	private static final long serialVersionUID = 1L;

	public GPolygon() {
		super(EDrawingStyle.eNPointDrawing);
		this.shape = new Polygon();
	}

	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		Polygon polygon = ((Polygon)new Polygon());
		//copy
		Polygon pShape = (Polygon) this.shape;
		for(int i=0; i<pShape.npoints; i++) {
			Point np = new Point();
			np.x = pShape.xpoints[i];
			np.y = pShape.ypoints[i];
			polygon.addPoint(np.x, np.y);
		}
		cloned.shape= polygon;
		return cloned;
	}
	@Override
	public void setInitPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	public void setIntermediatePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}
	@Override
	public void setFinalPoint(int x, int y) {
	}
	@Override
	public void movePoint(int x, int y) { 
		Polygon polygon = (Polygon) this.shape;
		polygon.xpoints[polygon.npoints-1]=x;
		polygon.ypoints[polygon.npoints-1]=y;
	}



}
