package shapeTool;

import java.awt.Point;
import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GIsosceles extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private int x0, y0;

	public GIsosceles() {
		super(EDrawingStyle.e2PointDrawing);
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
		x0 = x;
		y0 = y;
		//hexagon.npoints = 3
		Polygon triangle = (Polygon) this.shape;
		for(int i=0; i<3; i++) {
			triangle.addPoint(x, y);
		}

	}

	@Override
	public void setFinalPoint(int x, int y) {

	}

	@Override
	protected void movePoint(int x, int y) {
		int width = Math.abs(x-this.x0);
		
		if(x<this.x0) {
			width = -width;
		} 
		Polygon triangle = (Polygon) this.shape;
		triangle.xpoints[0] = this.x0;
		triangle.ypoints[0] = y;
		
		triangle.xpoints[1] = x0+width/2;
		triangle.ypoints[1] = y0;
		
		triangle.xpoints[2] = x ;
		triangle.ypoints[2] = y;
	}

}
