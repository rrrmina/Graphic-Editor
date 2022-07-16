package transformer;

import java.awt.Graphics2D;

import shapeTool.GShapeTool;

public class GResize extends GTransformer {
	private static final long serialVersionUID = 1L;

	public GResize(GShapeTool selectedShape) {
		super(selectedShape);
	}

	@Override
	protected void transform(Graphics2D graphics2d, int x, int y, int px, int py) {
		this.selectedShape.resize(graphics2d, x, y, px, py);
	}

}
