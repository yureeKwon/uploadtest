package btn03;
/*
 * #[πˆ∆∞]∆Ω≈√≈‰
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class MyPanel extends JPanel implements ActionListener{

	JButton btn[][];
	JButton winbtn;
	JButton restartbtn;
	
	int data[][];
	boolean turn;
	int winner = -1;
	int check = -1;
	
	MyPanel() {
		this.setLayout(null);
		basic_set();
		
	}	
	
	void basic_set(){
		turn = true;
		btn = new JButton[3][3];
		data = new int[3][3];
				
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {			
				btn[i][j] = new JButton();			
				btn[i][j].setSize(120, 120);
				btn[i][j].setLocation(j*120, i*120);
				btn[i][j].addActionListener(this);
				add(btn[i][j]);
			}
		}
		
		winbtn = new JButton("¥©±∏?");
		winbtn.setSize(120, 40);
		winbtn.setLocation(30, 400);
		winbtn.addActionListener(this);
		add(winbtn);
		
		restartbtn = new JButton("reset");
		restartbtn.setBounds(200, 400, 120, 40);
		restartbtn.addActionListener(this);
		add(restartbtn);
	}
	
	
		
	void winnercheck() {
		
		for(int i=0; i<3; i++) {
			if(data[i][0]==data[i][1]&&data[i][1]==data[i][2]) {
				if(data[i][0]==1) {
					winner = 1;
				}else if(data[i][0]==2) {
					winner = 2;
				}
			}
		}
		
		for(int j=0; j<3; j++) {
			if(data[0][j]==data[1][j]&&data[1][j]==data[2][j]) {
				if(data[0][j]==1) {
					winner = 1;
				}else if(data[0][j]==2) {
					winner = 2;
				}
			}
		}
		
		if(data[0][0]==data[1][1]&&data[1][1]==data[2][2]) {
			if(data[0][0]==1) {
				winner = 1;
			}else if(data[0][0]==2) {
				winner = 2;
			}
		}
		
		if(data[0][2]==data[1][1]&&data[1][1]==data[2][0]) {
			if(data[0][2]==1) {
				winner = 1;
			}else if(data[0][2]==2) {
				winner = 2;
			}
		}
		
		if(winner != -1) {
			winbtn.setText("player"+winner+"Ω¬∏Æ");
			check = 1;
		}
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==restartbtn) {
			System.out.println("!");
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {	
					data[i][j] = 0;
					btn[i][j].setText("");	
				}
			}
			winbtn.setText("§§§°?");
			check = -1;
		}
		if(check==-1) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(e.getSource()==btn[i][j]) {
						if(turn==true&&data[i][j]==0) {
							data[i][j] = 1;
							btn[i][j].setText("O");
							turn = !turn;
						}else if(turn==false&&data[i][j]==0) {
							data[i][j] = 2;
							btn[i][j].setText("X");
							turn = !turn;
						}					
					}
					
				}
			}
			winnercheck();	
		}
	}
	
}
public class Btn03 {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new MyPanel());
		frame.revalidate();
		
		
		
	}

}
