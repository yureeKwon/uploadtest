package snake;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements ActionListener{

	final int SIZE = 9;
	JButton[][] map;
	JButton[] dir;
	int[][] data;
	int snakeSize;
	int[] x;
	int[] y;
	int[] snake;
	
	public MyPanel() {
		
		setLayout(null);
		
		map = new JButton[SIZE][SIZE];
		dir = new JButton[4];
		data = new int[SIZE][SIZE];
		
		snakeSize = 4;
		
		x = new int[snakeSize];
		y = new int[snakeSize];
		snake = new int[snakeSize];
		
		setNumber();
		setMap();
		setSnake();
		setButton();
	}
	
	void setNumber() {
		for(int i=0; i<snakeSize; i++) {
			x[i] = i;
			snake[i] = i + 1;
			data[y[i]][x[i]] = snake[i];
		}
	}
	
	void setSnake() {
		// 화면 초기화
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				map[i][j].setBackground(Color.WHITE);
			}
		}
		
		// 뱀
		map[y[0]][x[0]].setBackground(Color.BLUE);
		for(int i=1; i<snakeSize; i++) {
			map[y[i]][x[i]].setBackground(Color.RED);
		}		
	}
	
	void setButton() {
		// 이동버튼
		for(int i=0; i<4; i++) {
			dir[i] = new JButton();
			dir[i].setSize(100, 100);
			if(i < 3) {
				dir[i].setLocation(550 + 100*(i+1), 400);
			}else {
				dir[i].setLocation(550 + 100*2, 300);
			}
			dir[i].addActionListener(this);
			
			add(dir[i]);
		}
		
		dir[0].setText("←");
		dir[1].setText("↓");
		dir[2].setText("→");
		dir[3].setText("↑");		
	}
	
	void setMap() {
		// 맵 작업
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				map[i][j] = new JButton();
				map[i][j].setSize(50, 50);
				map[i][j].setLocation(50*(j+1), 50*(i+1));
				
				map[i][j].addActionListener(this);
				
				add(map[i][j]);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int idx = 0;
		for(int i=0; i<snakeSize; i++) {
			if(e.getSource() == dir[i]) {
				idx = i;
			}
		}
		
		int yy = 0;
		int xx = 0;
		
		if(idx == 0) {
			xx = x[0] - 1;
			yy = y[0];
		}
		else if(idx == 2) {
			xx = x[0] + 1;
			yy = y[0];
		}
		else if(idx == 1) {
			xx = x[0];
			yy = y[0] + 1;
		}
		else if(idx == 3) {
			xx = x[0];
			yy = y[0] - 1;
		}
		
		if(SIZE <= xx || xx < 0 || SIZE <= yy || yy < 0) return;
		if(data[yy][xx] != 0) return;
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				data[i][j] = 0;
			}
		}
		
		for(int i=3; i>0; i--) {
			y[i] = y[i - 1];
			x[i] = x[i - 1];
		}
		
		x[0] = xx;
		y[0] = yy;
		
		for(int i=0; i<snakeSize; i++) {
			data[y[i]][x[i]] = snake[i];
		}
		setSnake();
	}
	
}


public class Snake {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		
		frame.setSize(1000, 800);
		frame.setLocation(100, 100);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		MyPanel mp = new MyPanel();
		frame.add(mp);
		
		frame.revalidate();


	}

}
