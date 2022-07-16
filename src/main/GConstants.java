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
		eRectangle(new GRectangle(),"사각형","img/rect.jpg","img/rect_c.jpg"), 
		eOval(new GOval(),"원","img/oval.jpg","img/oval_c.jpg"),
		eLine(new GLine(),"직선","img/line.jpg","img/line_c.jpg"),
		eFreeLine(new GFreeLine(),"자유선","img/fl.jpg","img/fl_c.jpg"),
		ePolygon(new GPolygon(),"다각형","img/poly.jpg","img/poly_c.jpg"),
		eHexagon(new GHexagon(),"육각형","img/hexa.jpg","img/hexa_c.jpg"),
		ePentagon(new GPentagon(),"오각형","img/penta.jpg","img/penta_c.jpg"),
		eIsosceles(new GIsosceles(),"이등변삼각형","img/iso.jpg","img/iso_c.jpg"),
		eRightTriangle(new GRightTriangle(),"직각삼각형","img/rt.jpg","img/rt_c.jpg"),
		eQuastars(new GQuastars(),"사각별","img/qs.jpg","img/qs_c.jpg"); //Quadrilateralstars
		
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
		eFile("파일"),
		eEdit("편집"),
		eGraphics("그래픽");
		
		private String text;
		
		private EMenu(String text) {
			this.text=text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EFileMenuItem{
		eNew("새로 만들기","새로운 파일을 생성한다."),
		eOpen("열기","기존 파일을 연다."),
		eSave("저장","현재 파일을 저장한다."),
		eSaveAs("다른이름으로 저장","현재 파일을 다른 이름으로 저장한다."),
		ePrint("프린트","현재 파일을 프린트한다."),
		eExit("나가기","해당 프로그램을 종료한다.");
		
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
		eUndo("되돌리기","직전 수행을 취소한다."), 
		eRedo("다시실행","취소된 직전 수행을 다시 되돌린다."),
		
		eCut("잘라내기", "선택된 도형을 잘라낸다.(븥여넣기 가능)"),
		eCopy("복사", "선택된 도형을 복사한다."),
		ePaste("붙여넣기", "선택된 도형을 같은 자리에 복사한다."),
		
		eDelete("삭제","선택된 도형을 삭제한다.(븥여넣기 불가능)"),
		eDeleteAll("전체 삭제","전체 도형을 삭제한다."),
		
		eFront("맨 앞으로","선택된 도형을 맨 앞으로 보낸다."),
		eBack("멘 뒤로","선택된 도형을 맨 뒤로 보낸다.");
		
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
		eLineColor("선 색","도형의 선 색을 변경한다."),
		eFillColor("채우기 색","도형의 채우기 색을 변경한다."),
		eThickness("선 굵기","도형의 선 굵기를 변경한다.");
		
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
