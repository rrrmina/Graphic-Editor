package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GFooterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel mLabel;
	private JLabel fLabel;

	public GFooterPanel() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		this.setBackground(Color.WHITE);
		
		JPanel infoPane = new JPanel();
		infoPane.setSize(new Dimension(10, 50));
		infoPane.setBackground(Color.white);
		
		this.fLabel = new JLabel();
		infoPane.add(fLabel);
		
		this.mLabel = new JLabel("현재 마우스 좌표 : "+0+" , "+0);
		infoPane.add(mLabel);

		this.add(infoPane);

	}
	public void initialize() {	

	}
	public void setFileName(String fileName) {
		if(fileName==null) {
			this.fLabel.setText("현재 파일 명 : 빈 파일               ");
		}else {
			this.fLabel.setText("현재 파일 명 : "+fileName+"               ");
		}
	}

	public void setMousePoint(int x, int y) {
		this.mLabel.setText("현재 마우스 좌표 : "+x+ " , "+y);
	}

	public void setMouseExited() {
		this.mLabel.setText("현재 마우스 좌표 : 그림판 밖");
	}

}
