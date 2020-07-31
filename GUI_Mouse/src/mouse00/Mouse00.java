package mouse00;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Rect{
	int x; int y; int width; int height;
}
class Mypanel extends JPanel implements MouseListener{
	
	Rect rc = new Rect();
	
	public Mypanel() {
		rc.x = 10;
		rc.y = 10;
		rc.width = 100;
		rc.height = 100;
		addMouseListener(this);
	}
	
	protected void paintComponent(Graphics g) {
		
		//1.전의 화면 지우기
		super.paintComponent(g); 
		//2.그리기 (초기에 1회 저절로 실행됨)
		g.drawRect(rc.x, rc.y, rc.width, rc.height);
		//3.좌표변경
		rc.x += 40;
		//4.시간간격
		try {
			Thread.sleep(1000);
			repaint();
		} catch (Exception e) {}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX()); //마우스위치의 X좌표
		System.out.println(e.getY()); //마우스위치의 Y좌표
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}



public class Mouse00 {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("슬라이드");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLocation(200, 200);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
	}

}
