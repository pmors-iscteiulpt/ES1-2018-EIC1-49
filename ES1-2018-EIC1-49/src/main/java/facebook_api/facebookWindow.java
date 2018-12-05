package facebook_api;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;

import mail_api.AuthenticationMailWindow;
import mail_api.MailAPI;
import mail_api.PopUp_Mail;
import mail_api.PresentationMailWindow;
import mail_api.SentMailWindow;
import twitter4j.Status;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;

public class facebookWindow {
	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String user;
	public String pass;
	private JTextField textField;
	private AuthenticationFacebookWindow afw;
	private facebookAPI fapi = new facebookAPI();
	private JList<String> list_1;
	private printWriter printwriter;
	private JTextField textField_1;
	public PopUp_Facebook popup;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				facebookWindow afw = new facebookWindow();
				afw.frame.setVisible(true);
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public facebookWindow() {
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
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 771, 465);
		panel.setBackground(new Color(135, 206, 235));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSessoActiva = new JLabel("Sess\u00E3o activa");
		lblSessoActiva.setBounds(650, 133, 90, 16);
		panel.add(lblSessoActiva);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(facebookWindow.class.getResource("/ES1_2018_EIC1_49/EIC1_49/Facebook-Logo-100.png")));
		lblNewLabel.setBounds(640, 27, 100, 100);
		panel.add(lblNewLabel);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afw = new AuthenticationFacebookWindow();
				afw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(77, 157, 546, 209);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 546, 209);
		panel_1.add(scrollPane);

		list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);

		JRadioButton rdbtnEmailsEnviadosPelo = new JRadioButton("ISCTE");
		rdbtnEmailsEnviadosPelo.setBackground(Color.ORANGE);
		rdbtnEmailsEnviadosPelo.setBounds(321, 53, 300, 25);
		panel.add(rdbtnEmailsEnviadosPelo);

		JRadioButton rdbtnEmailsEnviadosPelo_1 = new JRadioButton("Engenharia de Software I");
		rdbtnEmailsEnviadosPelo_1.setBackground(Color.ORANGE);
		rdbtnEmailsEnviadosPelo_1.setBounds(321, 78, 300, 25);
		panel.add(rdbtnEmailsEnviadosPelo_1);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(650, 66, 56, 16);
		panel.add(lblNewLabel_3);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(facebookWindow.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		label.setBounds(676, 162, 31, 22);
		panel.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(30, 144, 255));
		panel_2.setBounds(77, 379, 546, 73);
		panel.add(panel_2);

		JToggleButton tglbtnPostar = new JToggleButton("Postar");
		tglbtnPostar.setBackground(Color.WHITE);
		tglbtnPostar.setBounds(450, 35, 84, 25);
		panel_2.add(tglbtnPostar);
		tglbtnPostar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				fapi.post(textField.getText());
				JOptionPane.showMessageDialog(null, "Mensagem postada no seu perfil com sucesso!");
			}
		});

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 6, 522, 22);
		panel_2.add(textField);

		JLabel lblEmQueEsts = new JLabel("Em que estás a pensar?");
		lblEmQueEsts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmQueEsts.setBounds(179, 36, 179, 21);
		panel_2.add(lblEmQueEsts);

		JLabel lblNewLabel_4 = new JLabel("Publica\u00E7\u00F5es");
		lblNewLabel_4.setBackground(new Color(135, 206, 250));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(77, 128, 113, 25);
		panel.add(lblNewLabel_4);

		JButton btnVerPublicaes = new JButton("Ver publicações");
		btnVerPublicaes.setBackground(Color.ORANGE);
		btnVerPublicaes.setBounds(167, 125, 136, 25);
		panel.add(btnVerPublicaes);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(136, 32, 164, 83);
		panel.add(panel_3);
		panel_3.setVisible(true);
		panel_3.setLayout(null);

		JLabel lblProcurarNoTimeline = new JLabel("Procurar no Timeline");
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
		btnProcurar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fapi.searchForUserPosts(textField_1.getText());
				list_1.clearSelection();
				list_1.setModel(fapi.listaForSearchPostsFB);
			}
		});

		JPanel panel_5 = new JPanel();
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
				fapi.filtrarUltimas24horas();
				list_1.setModel(fapi.post_24h);
			}
		});

		btnVerPublicaes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list_1.clearSelection();
				fapi.AuthUser();
				list_1.setModel(fapi.listaPostsFB);
				printwriter = new printWriter();
				panel_5.setVisible(true);
				try {
					printwriter.printOnFile(fapi.listaPostsFB, new File(
							"C:\\Users\\Asus\\git\\ES1-2018-EIC1-49\\ES1-2018-EIC1-49\\src\\main\\java\\DataBase\\facebookDataBase.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int index = list_1.getSelectedIndex();
				if (evt.getClickCount() == 3) {
					for (int i = 0; i < fapi.listaPostsFB.size(); i++) {
						if (index == i) {
							String domain = "http://radixcode.com/";
							String appID = "1115442835290294";
							String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="
									+ appID + "&redirect_uri=" + domain + "&scope=user_about_me,"
									+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
									+ "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
									+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
									+ "manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

							FacebookClient fbClient = new DefaultFacebookClient(fapi.getAccessToken());
							Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

							for (List<Post> page : result) {
								for (Post aPost : page) {
									if (aPost.getMessage() != null) {

										String message_show = aPost.getMessage().toString();
										popup = new PopUp_Facebook();
										popup.getLblTweetDe().setText("Postado por: " + aPost.getName().toString());
										popup.getTextArea().setText(message_show);
										popup.getLblData().setText(aPost.getCreatedTime().toString());
										popup.getFrame().setVisible(true);
										popup.getTextArea().setLineWrap(true);
										popup.getTextArea().setWrapStyleWord(true);
									}
								}
							}
						}
					}
				}
			}
		});
	}
}
