package shapeTool;

import java.awt.Point;
import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GQuastars extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private int x0, y0;
	
	public GQuastars() {
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
		Polygon star = (Polygon) this.shape;
		//Quastars.npoints=8
		for(int i=0; i<8; i++) {
			star.addPoint(x, y);
		}
	}

	@Override
	public void setFinalPoint(int x, int y) {
	}

	@Override
	protected void movePoint(int x, int y) {
		int h = (int) Math.abs(y - this.y0);
		int w = (int) Math.abs(x - this.x0);
		
		int x1 = x0+(int)w*3/8;
		int x2 = x0+(int)w/2;
		int x3 = x0+(int)w*5/8;

		int y1 = y0+(int)h*3/8;
		int y2 = y0+(int)h/2;
		int y3 = y0+(int)h*5/8;
		
		
		Polygon star = (Polygon) this.shape;
		
		star.xpoints[0] = this.x0;
		star.ypoints[0] = y2;
		
		star.xpoints[1] = x1;
		star.ypoints[1] = y1;
		
		star.xpoints[2] = x2;
		star.ypoints[2] = this.y0;
		
		star.xpoints[3] = x3;
		star.ypoints[3] = y1;
		
		star.xpoints[4] = x;
		star.ypoints[4] = y2;
		
		star.xpoints[5] = x3;
		star.ypoints[5] = y3;
		
		star.xpoints[6] = x2;
		star.ypoints[6] = y;
		
		star.xpoints[7] = x1;
		star.ypoints[7] = y3;
		
	}

}
