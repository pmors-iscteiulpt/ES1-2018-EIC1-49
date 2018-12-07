package mail_api;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import mail_api.MailAPI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
public class PresentationMailWindow {

	private JFrame frame;
	public String user;
	public String pass;
	private MailAPI mail = new MailAPI();
	private JTextField textMessageMail;
	private JTextField sendTo;
	private JTextField subject;
	private SentMailWindow smw;
	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresentationMailWindow window = new PresentationMailWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public PresentationMailWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
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
		
		JLabel lblSessoActiva = new JLabel("Sess\u00E3o activa");
		lblSessoActiva.setBounds(650, 123, 88, 16);
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
				smw = new SentMailWindow();
				smw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				AuthenticationMailWindow.class.getResource("icons8-administrator-male-24.png")));
		lblNewLabel_1.setBounds(677, 153, 31, 22);
		panel.add(lblNewLabel_1);
				
				JButton btnEnviarEmail = new JButton("Enviar");
				btnEnviarEmail.setBackground(Color.WHITE);
				btnEnviarEmail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//							textMessageMailm = new  MimeMessage(mail.getSession(), new ByteArrayInputStream(textMessageMail.getText().getBytes()));
						try {
							mail.sendEmail(sendTo.getText(), subject.getText(), textMessageMail.getText());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				btnEnviarEmail.setBounds(477, 427, 89, 25);
				panel.add(btnEnviarEmail);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.LIGHT_GRAY);
				panel_1.setBounds(53, 216, 513, 204);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblMensagem = new JLabel("Mensagem");
				lblMensagem.setBounds(12, 13, 74, 16);
				panel_1.add(lblMensagem);
				
				textMessageMail = new JTextField();
				textMessageMail.setBounds(86, 0, 427, 204);
				panel_1.add(textMessageMail);
				textMessageMail.setColumns(10);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(53, 134, 226, 81);
				panel.add(panel_2);
				panel_2.setLayout(null);
				
				sendTo = new JTextField();
				sendTo.setBounds(84, 13, 116, 22);
				panel_2.add(sendTo);
				sendTo.setColumns(10);
				
				subject = new JTextField();
				subject.setBounds(84, 48, 116, 22);
				panel_2.add(subject);
				subject.setColumns(10);
				
				JLabel lblEnviarPara = new JLabel("Enviar para");
				lblEnviarPara.setBounds(8, 19, 78, 16);
				panel_2.add(lblEnviarPara);
				
				JLabel lblAssunto = new JLabel("Assunto");
				lblAssunto.setBounds(23, 49, 78, 16);
				panel_2.add(lblAssunto);
				
				
				String mailUser = mail.getUsername();
				JLabel fancyShowUser = new JLabel(mailUser);
				fancyShowUser.setBounds(642, 188, 129, 27);
				panel.add(fancyShowUser);
	}
	
//	public void msgFiltradas(String sender) {
//		ArrayList<> mailsRecebidos = new ArrayList<>();
//		
//	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}


