package mail_api;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import mail_api.MailAPI;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SentMailWindow {

	private JFrame frame;
	private PresentationMailWindow pmw;
	private AuthenticationMailWindow amw;
	private MailAPI mail = new MailAPI();
	public String user;
	public String pass;
	private JRadioButton rdbtnEmailsEnviadosPelo;
	private JList<String> list_1;
	private JTextField textField_1;
	public String toRespond;
	
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

	public SentMailWindow() {
		initialize();
	}

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

		String mailUser = mail.getUsername();
		JLabel fancyShowUser = new JLabel(mailUser);
		fancyShowUser.setBounds(642, 188, 129, 27);
		panel.add(fancyShowUser);

		JLabel lblSessoActiva = new JLabel("Sess\u00E3o activa");
		lblSessoActiva.setBounds(650, 128, 90, 16);
		panel.add(lblSessoActiva);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(640, 27, 100, 100);
		lblNewLabel.setIcon(new ImageIcon(AuthenticationMailWindow.class.getResource("1275392-t100.png")));
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
		lblNewLabel_1
				.setIcon(new ImageIcon(AuthenticationMailWindow.class.getResource("icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(677, 147, 31, 22);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(61, 157, 520, 275);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 500, 253);
		panel_1.add(scrollPane);
		list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);

		rdbtnEmailsEnviadosPelo = new JRadioButton("E-mails enviados pelo Iscte ");
		rdbtnEmailsEnviadosPelo.setBackground(Color.ORANGE);
		rdbtnEmailsEnviadosPelo.setBounds(345, 62, 236, 25);
		panel.add(rdbtnEmailsEnviadosPelo);

		JRadioButton rdbtnEmailsDirector = new JRadioButton("E-mails enviados pelo director");
		rdbtnEmailsDirector.setBackground(Color.ORANGE);
		rdbtnEmailsDirector.setBounds(345, 87, 236, 25);
		panel.add(rdbtnEmailsDirector);

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
		btnFiltrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEmailsEnviadosPelo.isSelected()) {
					try {
						mail.getEmail();
						list_1.setModel(mail.listaDeEmails);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SentMailWindow.class.getResource("/mail_api/if_list_384887 (2).png")));
		lblNewLabel_2.setBounds(676, 380, 64, 32);
		panel.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(136, 32, 164, 83);
		panel.add(panel_3);
		panel_3.setVisible(true);
		panel_3.setLayout(null);

		JLabel lblProcurarNoTimeline = new JLabel("Procurar na MailBox");
		lblProcurarNoTimeline.setBounds(21, 0, 143, 22);
		panel_3.add(lblProcurarNoTimeline);

		textField_1 = new JTextField();
		textField_1.setBounds(21, 24, 116, 22);
		panel_3.add(textField_1);
		textField_1.setColumns(10);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBackground(new Color(135, 206, 235));
		btnProcurar.setBounds(21, 45, 116, 25);
		panel_3.add(btnProcurar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(61, 433, 100, 32);
		panel.add(panel_2);
		panel_2.setLayout(null);
//		panel_2.setVisible(false);
		
		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(-1, 5, 103, 25);
		panel_2.add(btnResponder);
		btnProcurar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				list_1.clearSelection();
				if (rdbtnEmailsDirector.isEnabled()) {
					try {
						mail.showListMailsDirector();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					list_1.setModel(mail.listaDeProcuraDeEmails);
				if(rdbtnEmailsEnviadosPelo.isEnabled()) {
					try {
						mail.showListMailsISCTE();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					list_1.setModel(mail.listaDeProcuraDeEmails);
				}
				} else {
					mail.searchForTagInMailBox(textField_1.getText());
					list_1.setModel(mail.listaDeProcuraDeEmails);
				}
			}
		});

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					panel_2.setVisible(true);
					int index = list_1.getSelectedIndex();
					for (int i = 0; i < mail.getMessages().length; i++) {
						if (i == index)
							try {
								toRespond = mail.getMessages()[i].getFrom().toString();
							} catch (MessagingException e) {
								e.printStackTrace();
							}
						
						pmw = new PresentationMailWindow();
						
						pmw.getFrame().setVisible(true);
						frame.setVisible(false);
						
					}
				}
			}
		});
	
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
