package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;
import menu.GFileMenu;
import menu.GUndoStack;
import shapeTool.GShapeTool;
import transformer.GMover;
import transformer.GResize;
import transformer.GRotator;
import transformer.GTransformer;

public class GPanel extends JPanel {
	//attributes
	private static final long serialVersionUID = 1L;
	
	//components
	public Vector<GShapeTool> shapes;
	private GUndoStack undoStack;
	
	public Color lineColor;
	public Color fillColor;
	private int thickness;

	//working objects
	private GShapeTool shapeTool;
	private GShapeTool selectedShape;
	private GShapeTool copiedShape;
	
	private GTransformer transformer;
	private GFooterPanel footerPanel;
	private GMenuBar menuBar;
	private GFileMenu fileMenu;

	private boolean bModified;

	//getters & setters
	public Vector<GShapeTool> getShapes() {
		return this.shapes;
	}
	public void setShapes(Vector<GShapeTool> shapes) {
		 this.shapes=shapes;
		 this.repaint();
	}
	public void setShapeTool(GShapeTool shapeTool) { //setSelection
		this.shapeTool = shapeTool;
	}
	public boolean isModified() {
		return this.bModified;
	}
	public void setModified(boolean bModified) {
		this.bModified=bModified;
	}
	
	public Color getLinecolor() {
		return lineColor;
	}
	public void setLineColor(Color color) {
	      this.lineColor=new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	      shapeTool.setLineColor(this.lineColor);
	      if(this.selectedShape!=null) {
	    	  selectedShape.setLineColor(lineColor);
	         }
	      repaint();
	   }
	public Color getFillColor() {
		return fillColor;
	}
	 public void setFillColor(Color color) {
		 this.fillColor=new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	      shapeTool.setFillColor(fillColor); 
		 if(this.selectedShape!=null) {
	    	  selectedShape.setFillColor(fillColor);      
		 }
	      repaint();
	 }
	 public float getThickness() {
		 return this.thickness;
	 }

	 public void setThickness(int i) {
		 this.thickness = i;
		 shapeTool.setThickness(this.thickness);
		 if(this.selectedShape!=null) {
	    	  selectedShape.setThickness(this.thickness);     
	      }
		 repaint();
	 }
	 

	 private void setSelected(GShapeTool selectedShape) {
		 for(GShapeTool shape : this.shapes) {
			 shape.setSelected(false); 
		 }
		 this.selectedShape = selectedShape;
		 this.selectedShape.setSelected(true);
		 this.repaint(); 
	 }

	  
	public Vector<GShapeTool> deepCopy(Vector<GShapeTool> originals){
		Vector<GShapeTool> clones = (Vector<GShapeTool>) originals.clone();
		for(int i=0; i<originals.size(); i++) {
			clones.set(i, originals.get(i).clone());
		}
		return clones;
	}
	public void setAssociation(GMenuBar menuBar, GFooterPanel footerPanel) {
		this.footerPanel = footerPanel;
		this.menuBar =menuBar;

	}
	//constructors
	public GPanel() {
		this.shapes = new Vector<GShapeTool>();
		
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
		
		this.undoStack = new GUndoStack();
		this.bModified=false;
		this.copiedShape = null;
	}
	
	public void initialize() { 
		this.setBackground(Color.WHITE);
		this.shapes.clear();
		this.repaint();
        this.undoStack.clear();
		}

	// (editMenu)
	public void undo() {
		 Vector<GShapeTool> snapShot = this.undoStack.undo();
		 if(snapShot==null) {
			 return;
		 }
		 this.shapes = this.deepCopy(snapShot);
		 this.repaint();
	}
	public void redo() {
		Vector<GShapeTool> snapShot = this.undoStack.redo();
		if(snapShot==null) {
			 return;
		 }
		this.shapes = this.deepCopy(snapShot);
		this.repaint();
	}
	public void undoPush() {
		this.undoStack.push(this.deepCopy(this.shapes));
	}
	public void cut() {
		if(this.selectedShape!=null) {
			this.copiedShape = this.selectedShape.clone();
			this.shapes.remove(this.selectedShape);
			undoPush(); 
			this.repaint();
		}
	}
	public void copy() {
		if(this.selectedShape!=null) {
			this.copiedShape = this.selectedShape.clone();
		}
	}
	public void paste() { 
		if(this.copiedShape!=null) {
			Shape cshape = this.copiedShape.shape;
			this.shapes.add(this.copiedShape.clone());
			undoPush(); 
			this.repaint();
		}
	}
	public void delete() {
		if(this.selectedShape!=null) {
			this.copiedShape = null;
			this.shapes.remove(this.selectedShape);
			undoPush();
			this.repaint();
		}
	}
	public void deleteAll() {
		this.shapes.clear();
		undoPush();	
		this.repaint();
		}
	public void front() {
		Vector<GShapeTool> shapeTempVector = new Vector<GShapeTool>();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).getSelected()) {
				shapeTempVector.add(shapes.get(i));
				this.shapes.remove(i);
			}
		}
		for (int i = shapeTempVector.size() - 1; i >= 0; i--) {
			this.shapes.add(shapeTempVector.get(i));
		}
		undoPush();
		repaint();
	}
	public void back() {
		Vector<GShapeTool> shapeTempVector = new Vector<GShapeTool>();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).getSelected()) {
				shapeTempVector.add(shapes.get(i));
				this.shapes.remove(i);
			}
		}
		Vector<GShapeTool> shapeTempVector2 = new Vector<GShapeTool>();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			shapeTempVector2.add(shapes.get(i));
		}
		this.shapes.clear();
		for (int i = shapeTempVector.size() - 1; i >= 0; i--) {
			this.shapes.add(shapeTempVector.get(i));
		}
		for (int i = shapeTempVector2.size() - 1; i >= 0; i--) {
			this.shapes.add(shapeTempVector2.get(i));
		}
		undoPush();	
		repaint();
	}
	
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		//redraw shapes
		for(GShapeTool shape: this.shapes) {
			shape.draw((Graphics2D) graphics);
		}
	}

	private GShapeTool onShape(int x, int y) { 
		for(GShapeTool shape : this.shapes) {
			EAction eAction = shape.contains(x, y);
			if(eAction!=null) {
				return shape;
			}
		}
		return null;
	}

	// (Drawing)
	private void initDrawing(int x, int y) {
		this.selectedShape = this.shapeTool.clone(); 
		this.selectedShape.setInitPoint(x,y);
	}
	private void keepDrawing(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		graphics2d.setXORMode(getBackground());
		this.selectedShape.animate(graphics2d, x, y);
	}
	private void finishDrawing(int x, int y) {
		//this.selectedShape.setSelected(true);
		this.selectedShape.setFinalPoint(x,y);
		this.shapes.add(this.selectedShape); 
		this.bModified=true;
		this.repaint();
		undoPush();
	}
	private void setIntermediatePoint(int x, int y) {	
		this.selectedShape.setIntermediatePoint(x,y);
	}

	// (Transforming)
	private void initTransforming(GShapeTool selectedShape, int x, int y) {
		this.selectedShape = selectedShape;
		EAction eAction = this.selectedShape.getAction();
		switch(eAction){
			case eMove: 
				this.transformer = new GMover(this.selectedShape);
				break;
			case eResize:
				this.transformer = new GResize(this.selectedShape);
				break;
			case eRotate:
				this.transformer = new GRotator(this.selectedShape);
				break;
			default:
				break;
		}
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transformer.initTransforming(graphics2d,x,y);
	}
	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transformer.keepTransforming(graphics2d,x,y);
	}
	private void finishTransforming(int x, int y) {
		this.setSelected(this.selectedShape);	
		this.repaint();
		this.bModified=true;
		undoPush();
	}
	private void setMousePoint(int x, int y) {
		this.footerPanel.setMousePoint(x, y);
		menuBar.setFileName();
		this.footerPanel.setFileName(menuBar.getFileName());

	}
	private void setMouseExited() {
		this.footerPanel.setMouseExited();
	}
	// inner classes
	private class MouseHandler
	implements MouseListener,MouseMotionListener, MouseWheelListener{

		private boolean isDrawing;
		private boolean isTransforming;
		public MouseHandler(){
			this.isDrawing=false;
			this.isTransforming=false;
		}
		@Override
		public void mousePressed(MouseEvent e) { 
			if(e.getButton()==MouseEvent.BUTTON1) {
				if(!this.isDrawing) {
					GShapeTool selectedShape = onShape(e.getX(), e.getY());
					if(selectedShape == null) { 
						if(shapeTool.getDrawingStyle()==EDrawingStyle.e2PointDrawing) { 
							initDrawing(e.getX(), e.getY());
							this.isDrawing=true;
						}
					}else {
						initTransforming(selectedShape,e.getX(), e.getY());
						this.isTransforming=true;
					}
				}
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(this.isDrawing) {
				if(shapeTool.getDrawingStyle()==EDrawingStyle.e2PointDrawing) {
					keepDrawing(e.getX(), e.getY());
				}
			}else if(this.isTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) { 
			if(this.isDrawing) {
				if(shapeTool.getDrawingStyle()==EDrawingStyle.e2PointDrawing) {
					finishDrawing(e.getX(), e.getY());
					this.isDrawing=false;
				}
			}else if(this.isTransforming) {
				finishTransforming(e.getX(), e.getY());
				this.isTransforming=false;

			}
		}
		
		
		@Override
		public void mouseMoved(MouseEvent e) {
			setMousePoint(e.getX(), e.getY());
			if(this.isDrawing) {
				if(shapeTool.getDrawingStyle()==EDrawingStyle.eNPointDrawing) { 
					keepDrawing(e.getX(), e.getY());
				}
			}
		 
		
		}
		
		
		private void mouseLButton1Clicked(MouseEvent e) {
			if(!this.isDrawing) {
				GShapeTool selectedShape = onShape(e.getX(), e.getY());
				if(selectedShape==null) { 
					if(shapeTool.getDrawingStyle()==EDrawingStyle.eNPointDrawing) {
						initDrawing(e.getX(), e.getY());
						this.isDrawing=true;
						}
						else {
							for(GShapeTool shape : shapes) {
								 shape.setSelected(false); 
							 }
						}
				}else {
					setSelected(selectedShape); 
				}
				
			}else {
				if(shapeTool.getDrawingStyle()==EDrawingStyle.eNPointDrawing) {
					setIntermediatePoint(e.getX(), e.getY());
				}
			}
		}
		
		private void mouseLButton2Clicked(MouseEvent e) {
			if(this.isDrawing) {
				if(shapeTool.getDrawingStyle()==EDrawingStyle.eNPointDrawing) {
					finishDrawing(e.getX(), e.getY());
					this.isDrawing=false;
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) { 
			if(e.getButton()==MouseEvent.BUTTON1) {
				if(e.getClickCount()==1) {
					this.mouseLButton1Clicked(e);
				} else if(e.getClickCount()==2) {
					this.mouseLButton2Clicked(e);
				}
			}else if(e.getButton()==MouseEvent.BUTTON2) {
				if(e.getClickCount()==1) {
					this.mouseRButton1Clicked(e);
				}
			}
		}
	
		private void mouseRButton1Clicked(MouseEvent e) {
		}
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) { 	
			setMouseExited();
		}
		
	}
	
}

