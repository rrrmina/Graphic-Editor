package transformer;

import java.awt.Graphics2D;

import shapeTool.GShapeTool;

public class GRotator extends GTransformer {
	private static final long serialVersionUID = 1L;
	public GRotator(GShapeTool selectedShape) {
		super(selectedShape);
	}
	@Override
	protected void transform(Graphics2D graphics2d, int x, int y, int px, int py) {
		start.x=px;
		start.y=py;
		end.x=x;
		end.y=y;
		
		this.selectedShape.rotate(graphics2d, start, end);
		
	}

}
