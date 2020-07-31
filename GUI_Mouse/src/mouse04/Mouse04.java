package mouse04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Rect{
	int x; int y; int size;
	Color color;
	Rect(int x, int y, int size){
		this.x = x; 
		this.y = y;
		this.size = size;
		this.color = Color.black;
	}
}
class MyPanel extends JPanel implements MouseListener, MouseMotionListener{
	int x; int y;
	Rect rc = new Rect(100, 100, 100);
	boolean press = false;
	int pressX; int pressY;
	
	public MyPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);

		//위에 Rect(100,100,100)했는데 왜 밑에서 또 rc.x rc.y 지정하?
//		rc.x = 100;
//		rc.y = 100;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {}
		g.drawString(press+"", 100, 100);
		g.drawRect(rc.x, rc.y, rc.size, rc.size);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX(); y = e.getY();
		if(rc.x<x&&x<rc.x+rc.size&&rc.y<y&&y<rc.y+rc.size) {
			//1)클릭위치가 사각형바운더리 안에 들어오면 true가 되고 
			//2)릴리즈하면 true -> false 되게
			press = true;
			//pressX 는 기존사각형시작점을 0,0으로 본 X좌표값
			pressX = x - rc.x;
			pressY = y = rc.y;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		press = false;		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX(); y = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX(); y = e.getY();
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
	
}


public class Mouse04 {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("마우스연습");
		frame.setBounds(200, 200, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new MyPanel());
		frame.revalidate();
		
	}
}
