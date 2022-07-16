package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import frame.GMenuBar;
import frame.GPanel;
import main.GConstants.EFileMenuItem;
import shapeTool.GShapeTool;

public class GFileMenu extends JMenu implements Printable {
	private static final long serialVersionUID = 1L;

	private File file;
	public String fileName;

	private GPanel panel;
	private double margin = 36; 
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public GFileMenu(String text) {
		super(text);

		ActionHandler actionHandler = new ActionHandler();
		for(EFileMenuItem eFileMenuItem: EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eFileMenuItem.getText());
			menuItem.setActionCommand(eFileMenuItem.name());
			menuItem.setToolTipText(eFileMenuItem.getTip());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		fileName="빈 파일";
	}
	public void setAssociation(GPanel panel) {
		this.panel = panel;		
	}
	private void openFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(this.file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
			Vector<GShapeTool> shapes = (Vector<GShapeTool>)objectInputStream.readObject();
			this.panel.setShapes(shapes);
			objectInputStream.close();
			setFileName(this.file.getName());
			this.panel.setModified(false);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private void saveFile() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(this.file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			objectOutputStream.writeObject(this.panel.getShapes());
			objectOutputStream.close();
			setFileName(this.file.getName());
			this.panel.setModified(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private boolean saveOrNot() {
		boolean bCanceled = false; 
		if(this.panel.isModified()) {
			int reply = JOptionPane.showConfirmDialog(this.panel, "저장하시겠습니까?");
			if(reply==JOptionPane.YES_OPTION) {
				save();
			}else if(reply==JOptionPane.NO_OPTION) {
				this.panel.setModified(false);
			}else if(reply==JOptionPane.CANCEL_OPTION) {
				bCanceled=true;
			}
		}
		return bCanceled;
	}
	private void nnew() {
		if(!this.saveOrNot()) {
			this.panel.initialize();
			this.file=null;
			this.fileName="빈 파일";
		}
	}
	private void open() {
		if(!this.saveOrNot()) {
			JFileChooser fileChooser = new JFileChooser();
			int reply = fileChooser.showOpenDialog(this.panel);
			if(reply == JFileChooser.APPROVE_OPTION) {
				this.file = fileChooser.getSelectedFile();
				if(this.file!=null) {
					this.openFile();
				}
			}
		}
	}
	private void save() {
		if(this.panel.isModified()) {
			if(this.file==null) {
				this.saveAs();
			}else {
				this.saveFile();
			}	
		}

	}
	private void saveAs() {
		JFileChooser fileChooser = new JFileChooser();
		int reply = fileChooser.showSaveDialog(this.panel);

		if(reply == JFileChooser.APPROVE_OPTION) {
			this.file = fileChooser.getSelectedFile();
			if(this.file!=null) {
				this.saveFile();
			}
		}

	}
	private void print() {
		PrinterJob prnJob = PrinterJob.getPrinterJob();
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		PageFormat prnFormat = prnJob.defaultPage();
		Paper paper = new Paper();

		paper.setImageableArea(margin, margin, paper.getWidth() - margin , paper.getHeight() - margin );
		prnFormat.setPaper(paper);
		try {
			prnJob.setPrintable(this, prnFormat);
			if (prnJob.printDialog(printRequestAttributeSet)) {
				prnJob.print();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public int print(Graphics graphics, PageFormat prnFormat, int pi) throws PrinterException { 
		if (pi != 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setFont(new Font("Serif", Font.BOLD, (int) margin));
		graphics2d.setPaint(Color.black);

		for (GShapeTool shapes : (Vector<GShapeTool>) this.panel.getShapes()) {
			shapes.draw(graphics2d);
		}
		Rectangle2D line = new Rectangle2D.Double(prnFormat.getImageableX(), prnFormat.getImageableY(), prnFormat.getImageableWidth(),prnFormat.getImageableHeight());
		graphics2d.draw(line);

		return PAGE_EXISTS;
	}

	private void eexit() {
		if(!this.saveOrNot()) {
			System.exit(0);
		}
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			EFileMenuItem eFileMenuItem = EFileMenuItem.valueOf(e.getActionCommand());
			switch(eFileMenuItem) {
			case eNew:
				nnew();
				break;
			case eOpen:
				open();
				break;
			case eSave:
				save();
				break;
			case eSaveAs:
				saveAs();
				break;
			case ePrint:
				print();
				break;
			case eExit:
				eexit();
				break;
			default:
				break;
			}

		}
	}

}
