package mail_api;

import java.awt.Color;
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
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import mail_api.MailAPI;

public class SentMailWindow {

	private JFrame frame;
	private PresentationMailWindow pmw;
	private AuthenticationMailWindow amw;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String user;
	public String pass;
	private MailAPI mail = new MailAPI();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentMailWindow window = new SentMailWindow();
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
	public SentMailWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 250, 777, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 771, 465);
		panel.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSessoActiva = new JLabel("Sess\u00E3o activa");
		lblSessoActiva.setBounds(650, 128, 78, 16);
		panel.add(lblSessoActiva);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(640, 27, 100, 100);
		lblNewLabel.setIcon(
				new ImageIcon(AuthenticationMailWindow.class.getResource("1275392-t100.png")));
		panel.add(lblNewLabel);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				amw = new AuthenticationMailWindow();
				amw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				AuthenticationMailWindow.class.getResource("icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(677, 147, 31, 22);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(61, 157, 520, 275);
		panel.add(panel_1);
		
		JList list = new JList();
		panel_1.add(list);
		
		JRadioButton rdbtnEmailsEnviadosPelo = new JRadioButton("E-mails enviados pelo diretor ");
		rdbtnEmailsEnviadosPelo.setBackground(Color.ORANGE);
		rdbtnEmailsEnviadosPelo.setBounds(345, 62, 236, 25);
		panel.add(rdbtnEmailsEnviadosPelo);
		
		JRadioButton rdbtnEmailsEnviadosPelo_1 = new JRadioButton("E-mails enviados pelo E-Learning");
		rdbtnEmailsEnviadosPelo_1.setBackground(Color.ORANGE);
		rdbtnEmailsEnviadosPelo_1.setBounds(345, 87, 236, 25);
		panel.add(rdbtnEmailsEnviadosPelo_1);
		
		JButton btnNewButton = new JButton("Enviar e-mail");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(640, 407, 107, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pmw = new PresentationMailWindow();
				pmw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBackground(Color.ORANGE);
		btnFiltrar.setBounds(486, 124, 97, 25);
		panel.add(btnFiltrar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SentMailWindow.class.getResource("/mail_api/if_list_384887 (2).png")));
		lblNewLabel_2.setBounds(676, 380, 64, 32);
		panel.add(lblNewLabel_2);
		
		String mailUser = mail.getUsername();
		JLabel fancyShowUser = new JLabel(mailUser);
		fancyShowUser.setBounds(630, 188, 129, 27);
		panel.add(fancyShowUser);
	}
}
