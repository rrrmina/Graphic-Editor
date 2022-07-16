package shapeTool;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

import main.GConstants.EDrawingStyle;

public class GFreeLine extends GShapeTool {
	private static final long serialVersionUID = 1L;
	
	public GFreeLine( ) {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Path2D.Double();
	}
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		cloned.shape= (Shape) ((Path2D.Double)(this.shape)).clone();
		return cloned;
	}
	@Override
	public void setInitPoint(int x, int y) {
		Path2D path = (Path2D) this.shape;
		path.moveTo(x, y);

	}
	@Override
	public void setFinalPoint(int x, int y) {
	}
	@Override
	protected void movePoint(int x, int y) {
		Path2D path = (Path2D) this.shape;
		path.lineTo(x, y);
	}

}
