package btn05;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class Mypanel extends JPanel implements ActionListener{
	
	Random ran;
	JButton[][] btns = new JButton[5][5];;
	int[][] data;
	int[] num = new int[25];
	
	int yy; int xx;
	
	public Mypanel() {
		this.setLayout(null);
		data_set();
		button_setting();
	}
	
	void data_set() {
		ran = new Random();
		for(int i=0; i<25; i++) {
			num[i] = i+1;
		}
		int k=1;
		while(k<1000) {
			int r = ran.nextInt(25);
			int temp = num[r];
			num[r] = num[0];
			num[0] = temp;
			k = k + 1;
		}
		
		data = new int[5][5];
		k = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				data[i][j] = num[k];
				k = k + 1;
			}
		}
	}
		
	void button_setting() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				btns[i][j] = new JButton();
				btns[i][j].setBounds(j*100, i*100, 100, 100);
				btns[i][j].addActionListener(this);
				add(btns[i][j]);
			}
		}
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(data[i][j]!=25) {
					btns[i][j].setText(data[i][j]+"");
				}else if(data[i][j]==25) {
					btns[i][j].setText("");
					yy = i; xx = j;
				}
				
			}
		}
	}
	
	void button_reset() {
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(data[i][j]!=25) {
					btns[i][j].setText(data[i][j]+"");
				}else if(data[i][j]==25) {
					btns[i][j].setText("");
					yy = i; xx = j;
				}
				
			}
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(e.getSource()==btns[i][j]) {
					if(i==yy && j==xx+1) {
						int temp = data[i][j];
						data[i][j] = data[yy][xx];
						data[yy][xx] = temp;
						xx = j ;
					}else if(i==yy && j==xx-1) {
						int temp = data[i][j];
						data[i][j] = data[yy][xx];
						data[yy][xx] = temp;
						xx = j ;
					}else if(i==yy+1 && j==xx) {
						int temp = data[i][j];
						data[i][j] = data[yy][xx];
						data[yy][xx] = temp;
						yy = i ;
					}else if(i==yy-1 && j==xx) {
						int temp = data[i][j];
						data[i][j] = data[yy][xx];
						data[yy][xx] = temp;
						yy = i ;
					}
					button_reset();
				}
			}
		}
		
		
	}
	
}

public class Btn05 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Slide Game");
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();

	}

}
