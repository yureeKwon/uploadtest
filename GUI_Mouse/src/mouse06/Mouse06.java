package mouse06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel {
	//1.stoneŬ����
	class Stone{
		int xx; int yy; int type; int size;
	}
	//2. �ʵ�
	boolean isShow = false;
	Stone[][] grid = new Stone[11][11];
	boolean stoneType = false; // ���� ��
	
	Point mousePos = new Point();
	
	
	static final int START_POS = 400;
	
	//3.�����г�Ŭ����
	public MyPanel() {
		
		this.setBackground(Color.ORANGE);
		
		for(int i=0; i<11; i++) {
			for(int j=0; j<11; j++) {
				grid[i][j] = new Stone();
				grid[i][j].xx = START_POS-15 + (j*40);
				grid[i][j].yy = 100-15 + (i*40);
				grid[i][j].size = 30;
				grid[i][j].type = 0;
			}
		}
		
		JButton m_button = new JButton("�׸��� ���̱�");
		this.add(m_button);
		
	//m_button.addActionListener(this);
		m_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isShow = !isShow;
				repaint();//repaint�ϸ� paintComponent�� �ٷ�?
			}
		});

		//addMouseListener(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//1)���콺 Ŭ����ǥ ��� mousePos.x mousePos.y
				mousePos = e.getPoint();
				//Point rectPos = new Point();
				
				//2) ���콺Ŭ���� ���� �� ����
				for(int i=0; i<11; i++) {
					for(int j=0; j<11; j++) {
						int minX = grid[i][j].xx;
						int maxX = grid[i][j].xx + grid[i][j].size;
						int minY = grid[i][j].yy;
						int maxY = grid[i][j].yy + grid[i][j].size;
						
						//2.1)���콺Ŭ����ǥ�� �簢���ȿ� ������ 
						if(minX <= mousePos.x && mousePos.x <= maxX 
								&& minY <= mousePos.y && mousePos.y <= maxY) {
							//2.2)�׸���Ÿ���� 0�̰� ����Ÿ�� true�� 1�� �ٲٱ�  
							//�ǹ�:���� �ȳ����� �ְ� �鵹���ʸ� �鵹����
							//2.3)�׸���Ÿ���� 0�̰� ����Ÿ�� false�� 2�� �ٲٱ�
							//�ǹ�:���� �ȳ����� �ְ� �浹���ʸ� �浹����
							if(grid[i][j].type == 0) {
								if(stoneType) {
									grid[i][j].type = 1;
								}else {
									grid[i][j].type = 2;
								}
						     	//����Ÿ�� �ݴ���ϱ�
								stoneType = !stoneType;
							}
						}
					}
				}
				repaint();
				
			}
		});
	}//MyPanel()��
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("", Font.BOLD, 30));
		//1.���񾲱�
		g.drawString("�����ǿ���", 50, 50);
		//2.������ ���� �������
		if(stoneType) {
			g.drawString("������", 50, 100);
		}else {
			g.drawString("�浹����",50, 100);
		}
		//3.Ŭ����ǥ ���
		g.setFont(new Font("", Font.PLAIN, 20));
		g.drawString("Ŭ��X��ǥ"+mousePos.x, 50, 150);
		g.drawString("Ŭ��Y��ǥ"+mousePos.y, 50, 170);
	
		//4.������ �׸���
		for(int i=0; i<11; i++) {
			g.drawLine(START_POS+(i*40), 100, START_POS+(i*40), 100+(10*40));
			g.drawLine(START_POS, 100+(i*40), START_POS+(10*40), 100+(i*40));
		}
		//5.�� ����
		for(int i=0; i<11; i++) {
			for(int j=0; j<11; j++) {
				if(isShow) {
					g.drawRect(grid[i][j].xx, grid[i][j].yy, grid[i][j].size, grid[i][j].size);
				}
				if(grid[i][j].type==2) {
					g.setColor(Color.black);
				  //g.fillOval(x, y, width, height); �簢�� ���ο� �����ϰ� �� ä��� ��ɾ�
					g.fillOval(grid[i][j].xx, grid[i][j].yy, grid[i][j].size, grid[i][j].size);
				}
				else if(grid[i][j].type==1) {
					g.setColor(Color.WHITE);
					g.fillOval(grid[i][j].xx, grid[i][j].yy, grid[i][j].size, grid[i][j].size);
				}
			}
		}
		
		
		
	}
}


public class Mouse06 {
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("������ ����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		frame.setLocation(screenSize.width/2-600, screenSize.height/2-400);
		
		frame.setContentPane(new MyPanel());
		frame.revalidate();

	}
}
