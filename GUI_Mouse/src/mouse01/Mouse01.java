package mouse01;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Rect{
	int x; int y; int num; int size;
}


class Mypanel extends JPanel implements MouseListener{
	
	Rect[] rect = new Rect[25];
	int mx; int my;
	
	public Mypanel() {
		setLayout(null);
		addMouseListener(this);
		int k = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				rect[k] = new Rect();
				rect[k].size = 50;
				rect[k].num = k;
				rect[k].x = j*rect[k].size;
				rect[k].y = i*rect[k].size;
				k += 1;
				
			}
		}
	}
	
	//panel의 기능 : 그림그리기
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // 전의 화면 지우기
		try {
			//10/1000초 간격으로 paintComponent다시 실행
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {}
		
		//<1>사각형 그리고 안에 숫자넣기
		for(int i=0; i<rect.length; i++) {
			//1.사각형그리기 :시작x좌표     시작y좌표         가로길이                세로길이 
			g.drawRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			//2.글자넣기 :  내용                          x좌표                     y좌표
			g.drawString(rect[i].num+"", rect[i].x+18, rect[i].y+28);
		}
		
		//<2>마우스좌표 표시하기
		g.drawString("마우스X좌표"+mx, 270, 20);
		g.drawString("마우스Y좌표"+my, 270, 70);
	}
	
	Rect getClickedRect(int mx, int my) {
		Rect temp = null;
		for(int i=0; i<rect.length; i++) {
			if(rect[i].x<mx && mx<rect[i].x+rect[i].size) {
				if(rect[i].y<my && my<rect[i].y+rect[i].size) {
					temp = rect[i];
					return temp;
				}
			}
		}
		return temp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX(); my = e.getY();
		if(getClickedRect(mx, my) != null) {
			getClickedRect(mx, my).num = 100;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

public class Mouse01 {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("슬라이드");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 200, 500, 500);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
	}

}
