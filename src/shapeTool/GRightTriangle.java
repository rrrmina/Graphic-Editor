package shapeTool;

import java.awt.Point;
import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GRightTriangle extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private int x0, y0;

	public GRightTriangle() {
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
		//hexagon.npoints = 3
		Polygon rightTriangle = (Polygon) this.shape;
		for(int i=0; i<3; i++) {
			rightTriangle.addPoint(x, y);
		}

	}

	@Override
	public void setFinalPoint(int x, int y) {

	}

	@Override
	protected void movePoint(int x, int y) {
		Polygon rightTriangle = (Polygon) this.shape;
		rightTriangle.xpoints[0] = this.x0;
		rightTriangle.ypoints[0] = this.y0;
		
		rightTriangle.xpoints[1] = this.x0;
		rightTriangle.ypoints[1] = y;
		
		rightTriangle.xpoints[2] = x ;
		rightTriangle.ypoints[2] = y;
	}

}
