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
	//1.stone클래스
	class Stone{
		int xx; int yy; int type; int size;
	}
	//2. 필드
	boolean isShow = false;
	Stone[][] grid = new Stone[11][11];
	boolean stoneType = false; // 게임 턴
	
	Point mousePos = new Point();
	
	
	static final int START_POS = 400;
	
	//3.마이패널클래스
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
		
		JButton m_button = new JButton("그리드 보이기");
		this.add(m_button);
		
	//m_button.addActionListener(this);
		m_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isShow = !isShow;
				repaint();//repaint하면 paintComponent로 바로?
			}
		});

		//addMouseListener(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//1)마우스 클릭좌표 얻기 mousePos.x mousePos.y
				mousePos = e.getPoint();
				//Point rectPos = new Point();
				
				//2) 마우스클릭에 따라 돌 놓기
				for(int i=0; i<11; i++) {
					for(int j=0; j<11; j++) {
						int minX = grid[i][j].xx;
						int maxX = grid[i][j].xx + grid[i][j].size;
						int minY = grid[i][j].yy;
						int maxY = grid[i][j].yy + grid[i][j].size;
						
						//2.1)마우스클릭좌표가 사각형안에 들어오면 
						if(minX <= mousePos.x && mousePos.x <= maxX 
								&& minY <= mousePos.y && mousePos.y <= maxY) {
							//2.2)그리드타입이 0이고 스톤타입 true면 1로 바꾸기  
							//의미:돌이 안놓여져 있고 백돌차례면 백돌놓기
							//2.3)그리드타입이 0이고 스톤타입 false면 2로 바꾸기
							//의미:돌이 안놓여져 있고 흑돌차례면 흑돌놓기
							if(grid[i][j].type == 0) {
								if(stoneType) {
									grid[i][j].type = 1;
								}else {
									grid[i][j].type = 2;
								}
						     	//스톤타입 반대로하기
								stoneType = !stoneType;
							}
						}
					}
				}
				repaint();
				
			}
		});
	}//MyPanel()끝
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("", Font.BOLD, 30));
		//1.제목쓰기
		g.drawString("나만의오목", 50, 50);
		//2.순서에 따라 차례출력
		if(stoneType) {
			g.drawString("흰돌차례", 50, 100);
		}else {
			g.drawString("흑돌차례",50, 100);
		}
		//3.클릭좌표 출력
		g.setFont(new Font("", Font.PLAIN, 20));
		g.drawString("클릭X좌표"+mousePos.x, 50, 150);
		g.drawString("클릭Y좌표"+mousePos.y, 50, 170);
	
		//4.격자판 그리기
		for(int i=0; i<11; i++) {
			g.drawLine(START_POS+(i*40), 100, START_POS+(i*40), 100+(10*40));
			g.drawLine(START_POS, 100+(i*40), START_POS+(10*40), 100+(i*40));
		}
		//5.돌 놓기
		for(int i=0; i<11; i++) {
			for(int j=0; j<11; j++) {
				if(isShow) {
					g.drawRect(grid[i][j].xx, grid[i][j].yy, grid[i][j].size, grid[i][j].size);
				}
				if(grid[i][j].type==2) {
					g.setColor(Color.black);
				  //g.fillOval(x, y, width, height); 사각형 내부에 내접하게 원 채우는 명령어
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
		frame.setTitle("나만의 오목");
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
