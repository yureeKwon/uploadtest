package btn04;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * #[버튼]1-50(1-10 ver.)
 */
class Mypanel extends JPanel implements ActionListener{
	
	Random ran;
	
	JButton[] btns;
	JButton numbtn;
	int[] front;
	int[] back;
	int num = 1;
	
	public Mypanel() {
		this.setLayout(null);
		basic_set();
		shownumbtn();	
	}
	
	void basic_set() {
		btns = new JButton[5];
		front = new int[5];
		back = new int[5];
		
		for(int i=0; i<front.length; i++) {
			front[i] = i+1;
			back[i] = i+6;		
		}
		numshuffle();
		
		for(int i=0; i<5; i++) {	
			btns[i] = new JButton(front[i]+"");
			btns[i].setBounds(50+100*i, 120, 100, 100);
			btns[i].addActionListener(this);
			add(btns[i]);			
		}
				
	}
	
	void numshuffle() {
		ran = new Random();
		int n = 0;
		while(n<100) {
			int r = ran.nextInt(5);
			int temp = front[r];
			front[r] = front[0];
			front[0] = temp;
			
			r = ran.nextInt(5);
			temp = back[r];
			back[r] = back[0];
			back[0] = temp;
			
			n = n + 1;
		}
	}

	void shownumbtn() {
		
		numbtn = new JButton("next num : "+num);
		numbtn.setBounds(350,40,200,50);
		numbtn.addActionListener(this);
		add(numbtn);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(num<12) {
			for(int i=0; i<5; i++) {
				if(e.getSource()==btns[i]) {
					if(btns[i].getText().equals(num+"")) {
						num = num + 1;
						if(num<=6) {
							numbtn.setText("next num : "+num);
							btns[i].setText(back[i]+"");
						}else if(num<11){ //6,7,8,9을 맞췄을때
							numbtn.setText("next num : "+num);
							btns[i].setText("");
						}else if(num==11) {//10을 맞췄을때
							numbtn.setText("끝");
							btns[i].setText("");
						}
						
					}
				}
			}
		}
			
			
	}
	
}


public class Btn04 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("1 to 10");
		frame.setBounds(300, 300, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		

	}

}
