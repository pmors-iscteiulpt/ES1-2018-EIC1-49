package facebook_api;

import java.awt.Color;
import ES1_2018_EIC1_49.EIC1_49.*;
import mail_api.MailAPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import twitter4j.TwitterException;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import ES1_2018_EIC1_49.EIC1_49.*;
public class AuthenticationFacebookWindow {
	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public String user;
	public String pass;
	private MailAPI mail = new MailAPI();
	private App smw = new App();
	private App app;
	private facebookWindow fbw;
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

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationFacebookWindow window = new AuthenticationFacebookWindow();
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
	public AuthenticationFacebookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		panel.setBackground(new Color(135, 206, 235));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(197, 34, 100, 100);
		lblNewLabel.setIcon(
				new ImageIcon(AuthenticationFacebookWindow.class.getResource("/ES1_2018_EIC1_49/EIC1_49/Facebook-Logo-100.png")));
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
		lblNewLabel_1.setIcon(new ImageIcon(AuthenticationFacebookWindow.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(115, 241, 31, 22);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(
				new ImageIcon(AuthenticationFacebookWindow.class.getResource("/mail_api/icons8-lock-24.png")));
		lblNewLabel_2.setBounds(115, 299, 24, 22);
		panel.add(lblNewLabel_2);

		JButton btnIniciarSesso = new JButton("Iniciar ");
		btnIniciarSesso.setBackground(Color.WHITE);
		btnIniciarSesso.setBounds(285, 341, 81, 25);
		panel.add(btnIniciarSesso);
		btnIniciarSesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				String pass = passwordField.getText();
				fbw = new facebookWindow();
				fbw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});


		JLabel lblInicieASesso = new JLabel("Inicie a sua conta para continuar");
		lblInicieASesso.setBounds(151, 159, 187, 16);
		panel.add(lblInicieASesso);
	}

		
	public MailAPI getMail() {
		return mail;
	}

	public void setMail(MailAPI mail) {
		this.mail = mail;
	}

	public App getSmw() {
		return smw;
	}

	public void setSmw(App smw) {
		this.smw = smw;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}