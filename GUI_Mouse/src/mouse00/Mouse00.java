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
		
		//1.���� ȭ�� �����
		super.paintComponent(g); 
		//2.�׸��� (�ʱ⿡ 1ȸ ������ �����)
		g.drawRect(rc.x, rc.y, rc.width, rc.height);
		//3.��ǥ����
		rc.x += 40;
		//4.�ð�����
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
		System.out.println(e.getX()); //���콺��ġ�� X��ǥ
		System.out.println(e.getY()); //���콺��ġ�� Y��ǥ
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}



public class Mouse00 {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("�����̵�");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLocation(200, 200);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
	}

}
