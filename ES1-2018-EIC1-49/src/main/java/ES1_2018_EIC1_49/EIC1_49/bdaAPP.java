package ES1_2018_EIC1_49.EIC1_49;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bdaAPP {

	private JFrame frame;
	
	private JLabel icon;
	private static bdaAPP window1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window1 = new bdaAPP();
					window1.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bdaAPP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Monotype Corsiva", Font.BOLD, 16));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		 icon = new JLabel("");
		 icon.setBounds(122, 97, 182, 164);
		 Image img = new ImageIcon(this.getClass().getResource("iscte2.png")).getImage();
		 
		 Image img2 = img.getScaledInstance( icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);
		 icon.setIcon(new ImageIcon(img2));
		
		
		frame.getContentPane().add(icon);
		
		
		JButton btnNewButton = new JButton("Aceder");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					App window = new App();
					window.getFrame().setVisible(true);
					window1.frame.setVisible(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		btnNewButton.setBounds(162, 75, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblBomDiaAcademia = new JLabel("Bom Dia Academia");
		lblBomDiaAcademia.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		lblBomDiaAcademia.setBounds(109, 33, 246, 14);
		frame.getContentPane().add(lblBomDiaAcademia);
		
	}
}
