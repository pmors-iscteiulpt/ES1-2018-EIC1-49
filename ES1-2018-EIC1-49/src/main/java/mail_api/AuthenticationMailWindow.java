package mail_api;

import java.awt.Color;
import ES1_2018_EIC1_49.EIC1_49.*;
import mail_api.MailAPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import com.restfb.types.User;

import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AuthenticationMailWindow {

	/**
	 * 
	 * Janela de autenticaçao de email
	 * 
	 * @author Antonio Correia
	 * 
	 * 
	 */
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public String user;
	public String pass;
	private MailAPI mail = new MailAPI();
	private SentMailWindow smw = new SentMailWindow();
	private App app;
	private CreateXML createxml = new CreateXML();

	/**
	 * 
	 * Launch the application.
	 * 
	 * @author Antonio Correia
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationMailWindow window = new AuthenticationMailWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuthenticationMailWindow() {
		initialize();
	}

	/**
	 * 
	 * 
	 * Initialize the contents of the frame.
	 * 
	 * @author Antonio Correia
	 *
	 */

	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 250, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 494, 465);
		panel.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(197, 34, 100, 100);
		lblNewLabel.setIcon(new ImageIcon(AuthenticationMailWindow.class.getResource("1275392-t100.png")));
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(163, 241, 203, 22);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(163, 299, 203, 22);
		panel.add(passwordField);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app = new App();
				app.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(AuthenticationMailWindow.class.getResource("icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(115, 241, 31, 22);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AuthenticationMailWindow.class.getResource("icons8-lock-24.png")));
		lblNewLabel_2.setBounds(115, 299, 24, 22);
		panel.add(lblNewLabel_2);

		JButton btnIniciarSesso = new JButton("Iniciar ");
		btnIniciarSesso.setBackground(Color.WHITE);
		btnIniciarSesso.setBounds(285, 341, 81, 25);
		panel.add(btnIniciarSesso);
		btnIniciarSesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = textField.getText();
				pass = passwordField.getText();
				smw.getFrame().setVisible(true);
				frame.setVisible(false);
				mail.getMailCredentials(user, pass);

				try {
					createxml.complementUserEmail(user);
				} catch (JAXBException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblInicieASesso = new JLabel("Inicie a sua conta para continuar");
		lblInicieASesso.setBounds(151, 159, 187, 16);
		panel.add(lblInicieASesso);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
