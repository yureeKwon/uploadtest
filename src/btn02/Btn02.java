package btn02;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class Mypanel extends JFrame implements ActionListener{
	
	JButton[] btns;
	
	public Mypanel(){
		setLayout(null);
		btns = new JButton[5];
		for(int i=0; i<btns.length; i++) {
			btns[i] = new JButton(i+"");
			btns[i].setBounds(i*60, 50, 50, 50);
			
			btns[i].addActionListener(this);
			add(btns[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<btns.length; i++) {
			if(e.getSource()==btns[i]) {
				btns[i].setText("!!!");
			}
		}
		
		
	}
}


public class Btn02 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("버튼배열");
		frame.setSize(500, 500);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension size = tk.getScreenSize();
		frame.setLocation(size.width/2-250, size.height/2-250);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		

	}

}
