package menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.OptionPaneUI;

import org.apache.commons.io.FileUtils;

import XML.CreateXML;

/**
 * @author Antonio
 *
 */
public class bdaAPP extends JFrame {

	private JFrame frame;
	private static bdaAPP window1;
	private JTextField textField;
	private JPasswordField passwordField;
	private CreateXML file = new CreateXML();
	private App window;
	private String userGranted;
	public int index;
	private java.util.List<Utilizador> users = file.getusersList();
	private String filepath = file.getFilepath();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window1 = new bdaAPP();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public bdaAPP() {
		initialize();
	}

	/**
	 * Construtor da frame
	 * 
	 * @author Pedro Almeida
	 * 
	 */

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setFont(new Font("Monotype Corsiva", Font.BOLD, 16));
		frame.setBounds(250, 250, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		new ImageIcon(this.getClass().getResource("iscte2.png")).getImage();

		JButton btnNewButton = new JButton("Aceder");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(/**
		 * @author Pedro
		 *
		 */
		new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (acessGranted() == true) {
					try {
						frame.dispose();

						App app = new App();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
				}
			}

			/**
			 * verifica acesso
			 * @return
			 */
			private boolean acessGranted() {
				String username = String.valueOf(textField.getText());

				String pw = String.valueOf(passwordField.getPassword());

				for (int i = 0; i < users.size(); i++) {
					String usernameData = users.get(i).getUsername();
					String pwData = users.get(i).getPw();
					if (username.equals(usernameData) && pwData.equals(pw)) {

						index = i;
						saveInfile(index);

						return true;

					}
				}

				return false;
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
				frame.dispose();
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
		lblNewLabel_3.setIcon(new ImageIcon(bdaAPP.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		lblNewLabel_3.setBounds(104, 256, 24, 37);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(bdaAPP.class.getResource("/mail_api/icons8-lock-24.png")));
		lblNewLabel.setBounds(104, 293, 33, 30);
		frame.getContentPane().add(lblNewLabel);

	}

	/**
	 * Funcao auxiliar que ajuda ao funcionamento da associacao de utilizadores a
	 * contas das redes sociais
	 * 
	 * @author Pedro Almeida
	 * 
	 */
	
	public void saveInfile(int i) {
		try {
			File fac = new File(filepath + "acessos");
			if (!fac.exists()) {
				fac.createNewFile();
			}
			FileWriter write = new FileWriter(fac);
			write.write(Integer.toString(i));
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


}
