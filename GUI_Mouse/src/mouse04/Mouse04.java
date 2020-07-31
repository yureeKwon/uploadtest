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

		//���� Rect(100,100,100)�ߴµ� �� �ؿ��� �� rc.x rc.y ������?
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
			//1)Ŭ����ġ�� �簢���ٿ���� �ȿ� ������ true�� �ǰ� 
			//2)�������ϸ� true -> false �ǰ�
			press = true;
			//pressX �� �����簢���������� 0,0���� �� X��ǥ��
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
		frame.setTitle("���콺����");
		frame.setBounds(200, 200, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new MyPanel());
		frame.revalidate();
		
	}
}
