package mouse02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Rect{
	int x; int y; int size; int num; Color color; int back;
}


class Mypanel extends JPanel implements MouseListener, MouseMotionListener{

//<2> JFrame 셋팅------------------------------------------------	
	Rect[] rect = new Rect[25];
	int a = 0;
//<3> JFrame 셋팅------------------------------------------------		
	public Mypanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		int k = 0;
		for(int i = 0; i<5; i++) {
			for(int j=0; j<5; j++) {
				rect[k] = new Rect();
				rect[k].size = 50;
				rect[k].x = j*rect[k].size;
				rect[k].y = i*rect[k].size;
				rect[k].num = k;
				rect[k].color = Color.BLUE;
				rect[k].back = k+25;
				k = k + 1;
			}
		}
	}
//<4> paintComponent 셋팅 : 이게 반복되는거------------------------------------------------
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {}
		
		g.drawString("다음숫자:"+a, 270, 30);
		
		for(int i=0; i<25; i++) {
			g.setColor(rect[i].color);
			g.fillRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			g.setColor(Color.BLACK);
			g.drawRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			g.drawString(rect[i].num+"", rect[i].x+18, rect[i].y+22);
		}
		
	} 
//<5> 마우스클릭하는순간 셋팅----------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
		System.out.println(x + "," + y);
		for(int i=0; i<25; i++) {
			if(rect[i].x < x && x < rect[i].x+rect[i].size) {
				if(rect[i].y < y && y < rect[i].y+rect[i].size) {
					rect[i].color = Color.RED; 
					//눌렀을 때 red됐다가 살짝움직이면 밑에 moved적용돼서 cyan됐다가 범위밖으로나가면 blue됨
					rect[i].num = rect[i].back;
				}
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
		System.out.println(x + "," + y);
		for(int i=0; i<25; i++) {
			//1)마우스가 사각형안쪽이면 CYAN
			if(rect[i].x < x 
					&& x < rect[i].x+rect[i].size
			 &&rect[i].y < y 
					&& y < rect[i].y+rect[i].size) {
				rect[i].color = Color.CYAN;
			}
			//2)마우스가 범위 바깥으로 움직이면 원래색 BLUE 로 되돌리기
			else {
				rect[i].color = Color.BLUE;
			}
		}
	}
	
}

public class Mouse02 {
	public static void main(String[] args) {

//<1> JFrame 셋팅-------------------------------------------------
		JFrame frame = new JFrame();
		frame.setTitle("마우스연습");
		frame.setBounds(300, 300, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
	}
}
