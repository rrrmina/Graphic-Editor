package transformer;

import java.awt.Graphics2D;

import shapeTool.GShapeTool;

public class GMover extends GTransformer {
	private static final long serialVersionUID = 1L;

	public GMover(GShapeTool selectedShape) {
		super(selectedShape);
	}

	@Override
	protected void transform(Graphics2D graphics2d, int x, int y, int px, int py) {
		this.selectedShape.move(graphics2d, x-px, y-py);
		
	}

}
