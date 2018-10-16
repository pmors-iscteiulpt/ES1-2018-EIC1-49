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
import javax.swing.JTextField;

import org.w3c.dom.Node;


import javax.swing.JPasswordField;

public class bdaAPP {

	private JFrame frame;
	
	private JLabel icon;
	private static bdaAPP window1;
	private JTextField textField;
	private JPasswordField passwordField;
	private CreateXML file = new CreateXML();

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
		 icon.setBounds(242, 86, 182, 164);
		 Image img = new ImageIcon(this.getClass().getResource("iscte2.png")).getImage();
		 
		 Image img2 = img.getScaledInstance( icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);
		 icon.setIcon(new ImageIcon(img2));
		
		
		frame.getContentPane().add(icon);
		
		
		JButton btnNewButton = new JButton("Aceder");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Node master = file.read("C:\\Users\\Utilizador\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\ES1_2018_EIC1_49\\config.xml");
				Node UserItem = master.getChildNodes().item(3);
				if(UserItem.getAttributes().item(2).getFirstChild().getTextContent().equals(textField.getText())|| UserItem.getAttributes().item(1).getFirstChild().getTextContent().equals(passwordField.getPassword())) {
				try {
					App window = new App();
					window.getFrame().setVisible(true);
					window1.frame.setVisible(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}else {
				System.out.println("Credenciais ERRADAS");
			}
		}});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		btnNewButton.setBounds(65, 206, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblBomDiaAcademia = new JLabel("Bom Dia Academia");
		lblBomDiaAcademia.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		lblBomDiaAcademia.setBounds(109, 33, 246, 14);
		frame.getContentPane().add(lblBomDiaAcademia);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 86, 55, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(10, 126, 55, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(75, 83, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(75, 124, 89, 17);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton_1 = new JButton("New account");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				newAccountWindow window = new newAccountWindow();
				window.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(65, 158, 96, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
