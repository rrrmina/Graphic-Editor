package main;

import java.awt.Dimension;
import java.awt.Point;

import shapeTool.GFreeLine;
import shapeTool.GHexagon;
import shapeTool.GIsosceles;
import shapeTool.GLine;
import shapeTool.GOval;
import shapeTool.GPentagon;
import shapeTool.GPolygon;
import shapeTool.GQuastars;
import shapeTool.GRectangle;
import shapeTool.GRightTriangle;
import shapeTool.GShapeTool;

public class GConstants {
	public final static class CFrame {
		public final static Point point = new Point(100,100);
		public final static Dimension dimension = new Dimension(1000,800);
	}
	public enum EDrawingStyle{
		e2PointDrawing,
		eNPointDrawing
	}
	
	public enum EAction{
		eDraw,
		eMove,
		eResize,
		eRotate,
		eShear;
	}
	
	public final static int wAnchor = 10;
	public final static int hAnchor = 10;
	
	public enum EShapeTool{ 
		eRectangle(new GRectangle(),"�簢��","img/rect.jpg","img/rect_c.jpg"), 
		eOval(new GOval(),"��","img/oval.jpg","img/oval_c.jpg"),
		eLine(new GLine(),"����","img/line.jpg","img/line_c.jpg"),
		eFreeLine(new GFreeLine(),"������","img/fl.jpg","img/fl_c.jpg"),
		ePolygon(new GPolygon(),"�ٰ���","img/poly.jpg","img/poly_c.jpg"),
		eHexagon(new GHexagon(),"������","img/hexa.jpg","img/hexa_c.jpg"),
		ePentagon(new GPentagon(),"������","img/penta.jpg","img/penta_c.jpg"),
		eIsosceles(new GIsosceles(),"�̵�ﰢ��","img/iso.jpg","img/iso_c.jpg"),
		eRightTriangle(new GRightTriangle(),"�����ﰢ��","img/rt.jpg","img/rt_c.jpg"),
		eQuastars(new GQuastars(),"�簢��","img/qs.jpg","img/qs_c.jpg"); //Quadrilateralstars
		
		private GShapeTool shapeTool;
		private String text;
		private String img;
		private String img_c;
		
		private EShapeTool(GShapeTool shapeTool, String text, String img, String img_c) {
			this.shapeTool=shapeTool;
			this.text=text;
			this.img=img;
			this.img_c=img_c;
		}
		
		public GShapeTool getShapeTool() {
			return this.shapeTool;
		}
		public String getText() {
			return this.text;
		}
		public String getImg() {
			return this.img;
		}
		public String getImg_c() {
			return this.img_c;
			
		}
	}
	public enum EMenu{
		eFile("����"),
		eEdit("����"),
		eGraphics("�׷���");
		
		private String text;
		
		private EMenu(String text) {
			this.text=text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EFileMenuItem{
		eNew("���� �����","���ο� ������ �����Ѵ�."),
		eOpen("����","���� ������ ����."),
		eSave("����","���� ������ �����Ѵ�."),
		eSaveAs("�ٸ��̸����� ����","���� ������ �ٸ� �̸����� �����Ѵ�."),
		ePrint("����Ʈ","���� ������ ����Ʈ�Ѵ�."),
		eExit("������","�ش� ���α׷��� �����Ѵ�.");
		
		private String text;
		private String tip;
		
		private EFileMenuItem(String text, String tip) {
			this.text=text;
			this.tip = tip;
		}
		public String getText() {
			return this.text;
		}
		
		public String getTip() {
			return this.tip;
		}
	}
	public enum EEditMenuItem{
		eUndo("�ǵ�����","���� ������ ����Ѵ�."), 
		eRedo("�ٽý���","��ҵ� ���� ������ �ٽ� �ǵ�����."),
		
		eCut("�߶󳻱�", "���õ� ������ �߶󳽴�.(�����ֱ� ����)"),
		eCopy("����", "���õ� ������ �����Ѵ�."),
		ePaste("�ٿ��ֱ�", "���õ� ������ ���� �ڸ��� �����Ѵ�."),
		
		eDelete("����","���õ� ������ �����Ѵ�.(�����ֱ� �Ұ���)"),
		eDeleteAll("��ü ����","��ü ������ �����Ѵ�."),
		
		eFront("�� ������","���õ� ������ �� ������ ������."),
		eBack("�� �ڷ�","���õ� ������ �� �ڷ� ������.");
		
		private String text;
		private String tip;
		
		private EEditMenuItem(String text, String tip) {
			this.text=text;
			this.tip = tip;
		}
		public String getText() {
			return this.text;
		}
		
		public String getTip() {
			return this.tip;
		}
	}
	public enum EGraphicsMenuItem{
		eLineColor("�� ��","������ �� ���� �����Ѵ�."),
		eFillColor("ä��� ��","������ ä��� ���� �����Ѵ�."),
		eThickness("�� ����","������ �� ���⸦ �����Ѵ�.");
		
		private String text;
		private String tip;
		
		private EGraphicsMenuItem(String text, String tip) {
			this.text=text;
			this.tip = tip;
		}
		public String getText() {
			return this.text;
		}
		
		public String getTip() {
			return this.tip;
		}
	}

}
