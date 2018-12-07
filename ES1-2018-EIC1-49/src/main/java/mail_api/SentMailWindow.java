package mail_api;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class SentMailWindow {

	private JFrame frame;
	private PresentationMailWindow pmw;
	private AuthenticationMailWindow amw;
	private MailAPI mail = new MailAPI();
	private JRadioButton rdbtnEmailsEnviadosPelo;
	private JList<String> list_1;
	private JTextField textField_1;
	private String toRespond;
	private Message[] messages = mail.getMessages();
	private PopUp_Mail popup;

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

		JRadioButton rdbtnEmailsDirector = new JRadioButton("E-mails enviados pela reitora");
		rdbtnEmailsDirector.setBackground(Color.ORANGE);
		rdbtnEmailsDirector.setBounds(345, 87, 236, 25);
		panel.add(rdbtnEmailsDirector);

		JButton btnNewButton = new JButton("Enviar e-mail");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(640, 407, 107, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!mail.connectedToInternet()) {
					JOptionPane.showMessageDialog(null, "Precisa de estar online para enviar e-mails");
				} else {

					pmw = new PresentationMailWindow(null);
					pmw.getFrame().setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		panel.add(btnNewButton);

		rdbtnEmailsEnviadosPelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEmailsEnviadosPelo.isSelected()) {
					rdbtnEmailsDirector.setSelected(false);
				}
			}
		});

		rdbtnEmailsDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEmailsDirector.isSelected()) {
					rdbtnEmailsEnviadosPelo.setSelected(false);
				}
			}
		});

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
						list_1.setModel(mail.getListaDeEmails());

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				if (rdbtnEmailsDirector.isSelected()) {
					try {
						mail.getEmailfromReitora();
						list_1.setModel(mail.getEmailsReitor());
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
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
		panel_2.setVisible(false);

		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(-1, 5, 103, 25);
		panel_2.add(btnResponder);
		btnProcurar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				list_1.clearSelection();

				mail.searchForTagInMailBox(textField_1.getText());
				list_1.setModel(mail.getListaDeProcuraDeEmails());
			}
		});

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int index = list_1.getSelectedIndex();
				if (evt.getClickCount() == 1) {
					panel_2.setVisible(true);
					btnResponder.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							if (rdbtnEmailsEnviadosPelo.isSelected())
								if (!mail.connectedToInternet()) {
									JOptionPane.showMessageDialog(null, "N�o � poss�vel enviar e-mails offline");
								} else {
									for (int i = 0; i < mail.getListaDeEmails().size(); i++) {
										if (i == index) {
											toRespond = mail.getlistaDeEmails().getElementAt(i);
											System.out.println(toRespond);
											pmw = new PresentationMailWindow(toRespond);
											pmw.getFrame().setVisible(true);
											frame.setVisible(false);
										}
									}
								}
							if (rdbtnEmailsDirector.isSelected()) {
								if (!mail.connectedToInternet()) {
									JOptionPane.showMessageDialog(null, "N�o � poss�vel enviar e-mails offline");
								} else {
									for (int i = 0; i < mail.getEmailsReitor().size(); i++) {
										if (i == index) {
											toRespond = mail.getEmailsReitor().getElementAt(i);
											System.out.println(toRespond);
											pmw = new PresentationMailWindow(toRespond);
											pmw.getFrame().setVisible(true);
											frame.setVisible(false);
										}
									}
								}
							}
						}
					});
				}
			}
		});

		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(455, 9, 164, 43);
		panel.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(false);

		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setBounds(121, 0, 43, 43);
		panel_5.add(lblNewLabel1);
		lblNewLabel1.setIcon(new ImageIcon("C:\\Users\\Utilizador\\Desktop\\283982_thm.png"));

		JLabel lblOrganizarPorTempo = new JLabel("\u00DAltimas 24h");
		lblOrganizarPorTempo.setBounds(33, 0, 76, 16);
		panel_5.add(lblOrganizarPorTempo);

		JButton btnFiltrard = new JButton("Filtrar");
		btnFiltrard.setBackground(new Color(135, 206, 235));
		btnFiltrard.setBounds(35, 18, 67, 22);
		panel_5.add(btnFiltrard);
		btnFiltrard.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					mail.filtrarUltimas24horas();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				list_1.setModel(mail.getPost_24h());
			}
		});

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int index = list_1.getSelectedIndex();
				if (evt.getClickCount() == 2) {
					if (!mail.connectedToInternet()) {
						JOptionPane.showMessageDialog(null, "Precisa de estar online para ver conte�do do e-mail");
					} else {
						String from = mail.getUsername();
						String password = mail.getPass();
						try {
							Properties properties = new Properties();
							properties.put("mail.pop3.host", "outlook.office365.com");
							properties.put("mail.pop3.port", "995");
							properties.put("mail.pop3s.ssl.trust", "*"); // This is the most IMP property
							Session emailSession = Session.getDefaultInstance(properties);
							Store store = emailSession.getStore("pop3s"); // try imap or impas
							store.connect("outlook.office365.com", from, password);
							Folder emailFolder = store.getFolder("INBOX");
							emailFolder.open(Folder.READ_ONLY);
							messages = emailFolder.getMessages();
							for (int i = 0; i < mail.getlistaDeEmails().size(); i++) {
									if (index == i) {
										popup = new PopUp_Mail();
										popup.getTextArea().setText(mail.getlistaDeEmails().get(i));
										popup.getFrame().setVisible(true);
										popup.getTextArea().setLineWrap(true);
										popup.getTextArea().setWrapStyleWord(true);
									}
							}
							for (int j = 0; j < mail.getEmailsReitor().size(); j++) {
									if(index == j) {
										popup = new PopUp_Mail();
										popup.getTextArea().setText(mail.getEmailsReitor().get(j));
										popup.getFrame().setVisible(true);
										popup.getTextArea().setLineWrap(true);
										popup.getTextArea().setWrapStyleWord(true);
								}
							}
							emailFolder.close(false);
							store.close();
						} catch (NoSuchProviderException nspe) {
							nspe.printStackTrace();
						} catch (MessagingException me) {
							me.printStackTrace();
						}
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

	JPanel panel_5 = new JPanel();

}
