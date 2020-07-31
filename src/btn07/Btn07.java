package btn07;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Mypanel extends JPanel implements ActionListener{
	JButton[][] btns;
	JButton winbtn;
	JButton resetbtn;
	
	int[][] data = new int[9][9];
	boolean turn;
	int win = -1;
	
	public Mypanel()  {
		board_setting();		
	}
	
	void board_setting() {
		btns = new JButton[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				btns[i][j] = new JButton();
				btns[i][j].setBounds(j*100, i*100, 100, 100);
				btns[i][j].addActionListener(this);
				add(btns[i][j]);
			}
		}
		
		winbtn = new JButton("´©±¸?");
		winbtn.setBounds(400, 400, 120, 80);
		winbtn.addActionListener(this);
		
		resetbtn = new JButton("reset");
		resetbtn.setBounds(400, 400, 120, 80);
		resetbtn.addActionListener(this);
		
		
	}
	
	void board_printing() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(data[i][j]==1) {
					
				}
			}
		}
	}
	
	void winnercheck() {
		if(win!=-1) {
			
		}
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(e.getSource()==btns[i][j]) {
					if(turn==true&&data[i][j]==0) {
						data[i][j] = 1;
						turn = !turn;
					}
					else if(turn==false&&data[i][j]==0) {
						data[i][j] = 2;
						turn = !turn;
					}
				}
			}
		}
		
		winnercheck();
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}

public class Btn07 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(300, 300, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		

	}

}
