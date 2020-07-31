package mouse03;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


//작동 순서 다시 생각해보기  지금 이렇게 저렇게 바꿔봤는데 
//오른쪽방향키 누를때 왜 사각형두개다 빨간색이 되는걸까?
//-> 아하 오른쪽방향키만드는 명령어(이때색깔 빨강) 
//다음에 사각형두개 그리는 명령어가 온다. 그래서 지정색빨 설정때문에 사각형도 빨간색으로 보여졌던 것. 
//89번줄 추가해서 사각형 그리기전에 검정색으로 지정해줘서 해결.

class Rect{
	int x; int y; int size; String dir;
	Color color;
	Rect(){}
	public Rect(int x, int y, int size) {
		this.x = x; 
		this.y = y;
		this.size = size;
	}
	
}
class My_Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	Rect[] rect;
	Rect rc; 
	Rect rc2;
	
	int state = -1;
	int speed = 1;
	boolean press = false;
	int pressX; int pressY;
	
	public My_Panel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		rect = new Rect[4];
		for(int i=0; i<4; i++) {
			rect[i] = new Rect();
			rect[i].size = 50;
			rect[i].color = Color.BLACK;
		}
		rect[0].x = 175; rect[0].y = 250; rect[0].dir = "▲";
		rect[1].x = 125; rect[1].y = 300; rect[1].dir = "◀";
		rect[2].x = 175; rect[2].y = 300; rect[2].dir = "▼";
		rect[3].x = 225; rect[3].y = 300; rect[3].dir = "▶";

		rc = new Rect(75, 75, 100);
		rc2 = new Rect(250, 75, 100);
	}
	
	void update() {
		if(state==0) {
			rc.y -= speed;
		}else if(state==1) {
			rc.x -= speed;
		}else if(state==2) {
			rc.y += speed;
		}else if(state==3) {
			rc.x += speed;
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try {
			Thread.sleep(100);
			repaint();
		} catch (Exception e) {}
		
		update();
		
		for(int i=0; i<4; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
								
			g.setColor(rect[i].color);
			g.drawRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			g.drawString(rect[i].dir, rect[i].x+20, rect[i].y+30);
		}
		g.setColor(Color.BLACK); //line
		g.drawRect(rc.x, rc.y, rc.size, rc.size);
		g.drawRect(rc2.x, rc2.y, rc2.size, rc2.size);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
		for(int i=0; i<4; i++) {
			if(rect[i].x<x
					&&x<rect[i].x+rect[i].size
					&&rect[i].y<y
					&&y<rect[i].y+rect[i].size) {
				rect[i].color = Color.RED;
				state = i;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX(); int y = e.getY();
		for(int i=0; i<4; i++) {
			rect[i].color = Color.BLACK;		
		}
		state = -1;
	}
}


public class Mouse03 {
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("rec move");
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new My_Panel());
		frame.revalidate();
		
		
		

	}
}
