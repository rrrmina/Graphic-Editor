package shapeTool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import main.GConstants;
import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;

public abstract class GShapeTool implements Serializable, Cloneable{ 
	private static final long serialVersionUID = 1L;
	
	public enum EAnchors{ 
		x0y0, 
		x0y1,
		x0y2,
		x1y0,
		x1y2,
		x2y0,
		x2y1,
		x2y2,
		RR;
	}
		
	private EDrawingStyle eDrawingStyle; 
	public Shape shape;
	private Ellipse2D[] anchors;
	private EAnchors selectedAnchor;
	private boolean isSelected;
	private EAction eAction;
	private AffineTransform affineTransform;
	private Color lineColor, fillColor;
	private float thickness;
	private Point p ;
	 
	
	//constructors
	public GShapeTool(EDrawingStyle eDrawingStyle) {
		this.anchors = new Ellipse2D.Double[EAnchors.values().length];
		for(EAnchors eAnchor: EAnchors.values()) {
			this.anchors[eAnchor.ordinal()]=new Ellipse2D.Double();
		}
		this.isSelected = false;
		this.thickness = 1;
		this.eDrawingStyle = eDrawingStyle;
		this.selectedAnchor=null;
		this.lineColor = Color.black;
		this.fillColor = Color.white;
		
		this.eAction=null;
		this.affineTransform = new AffineTransform();
		this.p= new Point();
	}
	
	//getters & setters
	public EDrawingStyle getDrawingStyle() {
		return this.eDrawingStyle;
	}
	public EAction getAction() {
		return this.eAction;
	}
	
	//methods
	public GShapeTool clone() {
		GShapeTool cloned = null;
		try {
			cloned = (GShapeTool) super.clone();
			cloned.anchors = this.anchors.clone();
			for(int i=0; i<this.anchors.length; i++) {
				cloned.anchors[i] = (Ellipse2D) this.anchors[i].clone();
			}
			cloned.affineTransform=(AffineTransform) this.affineTransform.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cloned;	
	}

	public  EAction contains(int x, int y) {
		Shape transformedShape;
		this.eAction=null;
		if(this.isSelected) {
			for(int i=0; i<EAnchors.values().length-1; i++) {
				transformedShape = this.affineTransform.createTransformedShape(this.anchors[i]);
				if(transformedShape.contains(x, y)) {
					this.selectedAnchor = EAnchors.values()[i]; 
					this.eAction = EAction.eResize;
				}
			} 
			transformedShape = this.affineTransform.createTransformedShape(this.anchors[EAnchors.RR.ordinal()]);
			if(transformedShape.contains(x,y)) { 
				this.eAction= EAction.eRotate;
			}
		}
		transformedShape = this.affineTransform.createTransformedShape(this.shape);
		if(transformedShape.contains(x,y)) {
			this.eAction= EAction.eMove; 
		}
		return this.eAction;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public boolean getSelected( ) {
		return isSelected;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
		}
	public Color getLineColor() {
		return lineColor;
		}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		}
	public Color getFillColor() {
		return fillColor;
		}

	public void setThickness(float strokeThickness) {
		if (0 < strokeThickness) {
			this.thickness = strokeThickness;
		}
	}
	public float getThickness() {
		return thickness;
	}

	public void prepareToTransform(Graphics2D graphics2D,int x, int y) {
		this.draw(graphics2D);
	}
	public void move(Graphics2D graphics2d, int dx, int dy) {
		this.draw(graphics2d);
		this.affineTransform.translate(dx, dy);
		this.draw(graphics2d);
	}
	public void resize(Graphics2D graphics2d, int x, int y, int px, int py) {
		this.draw(graphics2d);
		Object transformedShape;
		double width = this.shape.getBounds().getWidth();
		double height = this.shape.getBounds().getHeight();
		double dw = 0;
		double dh = 0;
		
		switch (this.selectedAnchor) {
		case x0y0:  
			dw =-(x-px);	
			dh=-(y-py);
			p.setLocation(this.anchors[EAnchors.x2y2.ordinal()].getCenterX(), this.anchors[EAnchors.x2y2.ordinal()].getCenterY());
			break;
		case x0y1:  
			dw =-(x-px);	
			dh=0; 	 
			p.setLocation(this.anchors[EAnchors.x2y1.ordinal()].getCenterX(), 0);
			break;
		case x0y2:  
			dw =-(x-px);	
			dh=y-py; 	
			p.setLocation(this.anchors[EAnchors.x2y0.ordinal()].getCenterX(), this.anchors[EAnchors.x2y0.ordinal()].getCenterY());
			break;
		case x1y0:  
			dw =0;	
			dh=-(y-py); 
			p.setLocation(0, this.anchors[EAnchors.x1y2.ordinal()].getCenterY());
			break;
		case x1y2:  
			dw =0;	
			dh=y-py; 
			p.setLocation(0, this.anchors[EAnchors.x1y0.ordinal()].getCenterY());
			break;
		case x2y0:  
			dw =x-px;	
			dh=-(y-py); 
			p.setLocation(this.anchors[EAnchors.x0y2.ordinal()].getCenterX(), this.anchors[EAnchors.x0y2.ordinal()].getCenterY());
			break;
		case x2y1:  
			dw =x-px;	
			dh=0; 	
			p.setLocation(this.anchors[EAnchors.x0y1.ordinal()].getCenterX(), 0);
			break;
		case x2y2:  
			dw =x-px;	
			dh=y-py; 
			p.setLocation(this.anchors[EAnchors.x0y0.ordinal()].getCenterX(), this.anchors[EAnchors.x0y0.ordinal()].getCenterY());
			break;

		default: break;
		}
		double rX = 1.0;
		double rY = 1.0;
		if(height > 0.0) {
			rY = dh / height + rY;
		}	
		if(width > 0.0) {
			rX = dw / width + rX;
		}
		//this.affineTransform.setToTranslation(dw, dh);
		this.affineTransform.scale(rX, rY);
		this.affineTransform.translate(-dw, -dh);

		//this.shape=this.affineTransform.createTransformedShape(shape);
		this.draw(graphics2d);
	}
	public void rotate(Graphics2D graphics2d, Point pStart, Point pEnd) {
		this.draw(graphics2d);
		double centerX = this.shape.getBounds().getCenterX();
		double centerY = this.shape.getBounds().getCenterY();
		double startAngle = Math.toDegrees(Math.atan2(centerX-pStart.x, centerY-pStart.y));
		double endAngle = Math.toDegrees(Math.atan2(centerX-pEnd.x, centerY-pEnd.y));
		double rotationAngle = startAngle-endAngle;
		if(rotationAngle<0) {
			rotationAngle += 360;
		}
		this.affineTransform.rotate(Math.toRadians(rotationAngle), centerX, centerY);
		this.draw(graphics2d);
	}
	
	private void drawAnchors(Graphics2D graphics2D) {
		int wAnchor = GConstants.wAnchor;
		int hAnchor = GConstants.hAnchor;
		
		Rectangle rectangle = this.shape.getBounds();		
		int x0 = rectangle.x-wAnchor/2;
		int x1 = rectangle.x + (rectangle.width)/2-wAnchor/2;
		int x2 = rectangle.x + rectangle.width-wAnchor/2;
		int y0 = rectangle.y-hAnchor/2;
		int y1 = rectangle.y + (rectangle.height)/2-hAnchor/2;
		int y2 = rectangle.y + rectangle.height-hAnchor/2;
		
		this.anchors[EAnchors.x0y0.ordinal()].setFrame(x0,y0,wAnchor,hAnchor);
		this.anchors[EAnchors.x0y1.ordinal()].setFrame(x0,y1,wAnchor,hAnchor);
		this.anchors[EAnchors.x0y2.ordinal()].setFrame(x0,y2,wAnchor,hAnchor);
		this.anchors[EAnchors.x1y0.ordinal()].setFrame(x1,y0,wAnchor,hAnchor);
		this.anchors[EAnchors.x1y2.ordinal()].setFrame(x1,y2,wAnchor,hAnchor);
		this.anchors[EAnchors.x2y0.ordinal()].setFrame(x2,y0,wAnchor,hAnchor);
		this.anchors[EAnchors.x2y1.ordinal()].setFrame(x2,y1,wAnchor,hAnchor);
		this.anchors[EAnchors.x2y2.ordinal()].setFrame(x2,y2,wAnchor,hAnchor);
		this.anchors[EAnchors.RR.ordinal()].setFrame(x1,y0-50,wAnchor,hAnchor);
	
		for(EAnchors eAnchor: EAnchors.values()) {
			graphics2D.setColor(Color.white);
			graphics2D.setStroke(new BasicStroke(1));
			Shape transformAnchor = this.affineTransform.createTransformedShape(this.anchors[eAnchor.ordinal()]);
			graphics2D.fill(transformAnchor);
			graphics2D.setColor(Color.black);
			graphics2D.draw(transformAnchor);
			
		}
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(this.fillColor); //fill Color
		graphics2D.fill(this.affineTransform.createTransformedShape(this.shape));
		graphics2D.setColor(this.lineColor); //LINE Color
		graphics2D.setStroke(new BasicStroke(this.thickness,BasicStroke.CAP_ROUND,0));
		graphics2D.draw(this.affineTransform.createTransformedShape(this.shape));

		if(this.isSelected) {
			this.drawAnchors(graphics2D);
		}
	}
	
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.movePoint(x,y);
		this.draw(graphics2d);
	}
	//interface
	public abstract void setInitPoint(int x, int y);
	public void setIntermediatePoint(int x, int y) {}
	public abstract void setFinalPoint(int x, int y);
	protected abstract void movePoint(int x, int y);
	
	
	


	
	
	

}
