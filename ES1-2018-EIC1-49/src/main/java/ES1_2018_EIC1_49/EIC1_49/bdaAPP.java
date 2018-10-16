package ES1_2018_EIC1_49.EIC1_49;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import org.w3c.dom.Node;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Dimension;

public class bdaAPP {

	private JFrame frame;
	private static bdaAPP window1;
	private JTextField textField;
	private JPasswordField passwordField;
	private CreateXML file = new CreateXML();
	private App window;

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

	public bdaAPP() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setFont(new Font("Monotype Corsiva", Font.BOLD, 16));
		frame.setBounds(250, 250, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("iscte2.png")).getImage();

		JButton btnNewButton = new JButton("Aceder");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Node master = file.read(
						"C:\\Users\\Utilizador\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\ES1_2018_EIC1_49\\config.xml");
				Node UserItem = master.getChildNodes().item(3);
				if (UserItem.getAttributes().item(2).getFirstChild().getTextContent().equals(textField.getText())
						|| UserItem.getAttributes().item(1).getFirstChild().getTextContent()
								.equals(passwordField.getPassword())) {
					try {
						window = new App();
						window.getFrame().setVisible(true);
						frame.setVisible(false);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username e/ou password incorrectas!", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(317, 335, 75, 24);
		frame.getContentPane().add(btnNewButton);

		JLabel lblBomDiaAcademia = new JLabel("Bom Dia Academia");
		lblBomDiaAcademia.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		lblBomDiaAcademia.setBounds(104, 69, 306, 37);
		frame.getContentPane().add(lblBomDiaAcademia);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(140, 261, 252, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(140, 297, 252, 23);
		frame.getContentPane().add(passwordField);

		JButton btnNewButton_1 = new JButton("Criar conta");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				newAccountWindow window = new newAccountWindow();
				window.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(290, 372, 102, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(bdaAPP.class.getResource("/ES1_2018_EIC1_49/EIC1_49/iscte-iul_s.png")));
		lblNewLabel_2.setBounds(200, 119, 94, 104);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3
				.setIcon(new ImageIcon(bdaAPP.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		lblNewLabel_3.setBounds(104, 256, 24, 37);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(bdaAPP.class.getResource("/mail_api/icons8-lock-24.png")));
		lblNewLabel.setBounds(104, 293, 33, 30);
		frame.getContentPane().add(lblNewLabel);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
