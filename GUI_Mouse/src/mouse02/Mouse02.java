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

//<2> JFrame ����------------------------------------------------	
	Rect[] rect = new Rect[25];
	int a = 0;
//<3> JFrame ����------------------------------------------------		
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
//<4> paintComponent ���� : �̰� �ݺ��Ǵ°�------------------------------------------------
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) {}
		
		g.drawString("��������:"+a, 270, 30);
		
		for(int i=0; i<25; i++) {
			g.setColor(rect[i].color);
			g.fillRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			g.setColor(Color.BLACK);
			g.drawRect(rect[i].x, rect[i].y, rect[i].size, rect[i].size);
			g.drawString(rect[i].num+"", rect[i].x+18, rect[i].y+22);
		}
		
	} 
//<5> ���콺Ŭ���ϴ¼��� ����----------------------------------------------
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
					//������ �� red�ƴٰ� ��¦�����̸� �ؿ� moved����ż� cyan�ƴٰ� ���������γ����� blue��
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
			//1)���콺�� �簢�������̸� CYAN
			if(rect[i].x < x 
					&& x < rect[i].x+rect[i].size
			 &&rect[i].y < y 
					&& y < rect[i].y+rect[i].size) {
				rect[i].color = Color.CYAN;
			}
			//2)���콺�� ���� �ٱ����� �����̸� ������ BLUE �� �ǵ�����
			else {
				rect[i].color = Color.BLUE;
			}
		}
	}
	
}

public class Mouse02 {
	public static void main(String[] args) {

//<1> JFrame ����-------------------------------------------------
		JFrame frame = new JFrame();
		frame.setTitle("���콺����");
		frame.setBounds(300, 300, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
	}
}
