package shapeTool;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingStyle;

public class GHexagon extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private int x0, y0;
	
	public GHexagon() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Polygon();
	}

	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		Polygon polygon = ((Polygon)new Polygon());
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
		x0 = x;
		y0 = y;
		//hexagon.npoints = 6
		Polygon hexagon = (Polygon) this.shape;
		for(int i=0; i<6; i++) {
			hexagon.addPoint(x, y);
		}
	}
	public void setIntermediatePoint(int x, int y) {
		
	}
	@Override
	public void setFinalPoint(int x, int y) {
		
	}
	@Override
	public void movePoint(int x, int y) { 
		Polygon hexagon = (Polygon) this.shape;
		int y1 = (this.y0 + y) / 2;
		int x1 = (3 * this.x0 + x) / 4;
		int x2 = (this.x0 + 3 * x) / 4;
		
		hexagon.xpoints[0] = x1;
		hexagon.ypoints[0] = this.y0;

		hexagon.xpoints[1] = x2;
		hexagon.ypoints[1] = this.y0;

		hexagon.xpoints[2] = x;
		hexagon.ypoints[2] = y1;

		hexagon.xpoints[3] = x2;
		hexagon.ypoints[3] = y;

		hexagon.xpoints[4] = x1;
		hexagon.ypoints[4] = y;

		hexagon.xpoints[5] = this.x0;
		hexagon.ypoints[5] = y1;
	}



}
