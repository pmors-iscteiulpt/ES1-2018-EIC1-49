package twitter_api;

import java.awt.Color;
<<<<<<< HEAD
=======

import XML.CreateXML;
import menu.*;

>>>>>>> refs/remotes/origin/master
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import menu.*;

import javax.swing.JPasswordField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

public class AuthenticationTwitterWindow {
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public String user;
	public String pass;
	private TwitterWindow smw;
	private App app;
	private CreateXML createxml = new CreateXML();

	/**
	 * 
	 * Launch the application.
	 * 
	 * @author Daniel Leal
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationTwitterWindow window = new AuthenticationTwitterWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Construtor da janela de login do Twitter na App.
	 * 
	 * @author Daniel Leal
	 * 
	 * 
	 */

	public AuthenticationTwitterWindow() {
		initialize();
	}

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
		lblNewLabel.setIcon(new ImageIcon(AuthenticationTwitterWindow.class.getResource("/twitter_api/twitter.png")));
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
		lblNewLabel_1.setIcon(new ImageIcon(
				AuthenticationTwitterWindow.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(115, 241, 31, 22);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(AuthenticationTwitterWindow.class.getResource("/mail_api/icons8-lock-24.png")));
		lblNewLabel_2.setBounds(115, 299, 24, 22);
		panel.add(lblNewLabel_2);

		JButton btnIniciarSesso = new JButton("Iniciar ");
		btnIniciarSesso.setBackground(Color.WHITE);
		btnIniciarSesso.setBounds(285, 341, 81, 25);
		panel.add(btnIniciarSesso);
		btnIniciarSesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				smw.getFrame().setVisible(true);
				frame.setVisible(false);
=======
				try {
					smw= new TwitterWindow();
					smw.getFrame().setVisible(true);
					frame.setVisible(false);
				} catch (IllegalStateException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

>>>>>>> refs/remotes/origin/master
				try {
					createxml.complementUserTwitter(user);
				} catch (JAXBException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblInicieASesso = new JLabel("Inicie a sua conta para continuar");
		lblInicieASesso.setBounds(151, 159, 187, 16);
		panel.add(lblInicieASesso);
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
