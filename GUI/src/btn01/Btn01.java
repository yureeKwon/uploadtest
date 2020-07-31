package btn01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Mypanel extends JPanel implements ActionListener{
	
	JButton btn;
	int height = 10;
	
	public Mypanel() {

	setLayout(null);
	setBackground(Color.BLUE);
	
	btn = new JButton("¹öÆ°");
	btn.setBounds(10, 10 , 300, 300);
	
	btn.addActionListener(this);
	add(btn);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			height += 10;
			btn.setLocation(10, height);
			System.out.println("?!");
			setBackground(Color.GREEN);
		}
		
	}
}

public class Btn01 {
	public static void main(String[] args) {

		JFrame frame = new JFrame("BTN");
		frame.setSize(500, 500);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension size = tk.getScreenSize();
		
		frame.setLocation(size.width/2-250, size.height/2-250);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new Mypanel());
		frame.revalidate();
		
		
		
		
	}
}