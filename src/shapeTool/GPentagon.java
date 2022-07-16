package shapeTool;

import java.awt.Point;
import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GPentagon extends GShapeTool {
	private static final long serialVersionUID = 1L;
	private int x0, y0;
	
	public GPentagon() {
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
		Polygon pentagon = (Polygon) this.shape;
		//pentagon.npoints=5
		for(int i=0; i<5; i++) {
			pentagon.addPoint(x, y);
		}
	}

	@Override
	public void setFinalPoint(int x, int y) {
	}

	@Override
	protected void movePoint(int x, int y) {
		Polygon pentagon = (Polygon) this.shape;
		
		int height = (int) Math.abs(y - this.y0);
		int width = (int) Math.abs(x - this.x0);
		
		int x1 = this.x0 + width / 5;
		int x2 = this.x0 + width / 2;
		int x3 = this.x0 + 4 * width / 5;
		int y1 = this.y0+height/3;

		//오각형이니까 거꾸로도
		if(x<this.x0) {
			width = -width;
		} 
		if(y<this.y0) {
			height= -height;
		}
		pentagon.xpoints[0] = x2;
		pentagon.ypoints[0] = this.y0;
		
		pentagon.xpoints[1] = this.x0+width;
		pentagon.ypoints[1] = y1;
		
		pentagon.xpoints[2] = x3;
		pentagon.ypoints[2] = this.y0+height;
		
		pentagon.xpoints[3] = x1;
		pentagon.ypoints[3] = this.y0+height;
		
		pentagon.xpoints[4] = this.x0;
		pentagon.ypoints[4] = y1;

	}
}


