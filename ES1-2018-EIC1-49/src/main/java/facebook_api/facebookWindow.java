package facebook_api;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;

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
	private AuthenticationFacebookWindow afw;
	private facebookAPI fapi = new facebookAPI();
	private JList<String> list_1;
	private JTextField textField_1;
	public PopUp_Facebook popup;
	private String message_show;

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
		lblNewLabel.setIcon(new ImageIcon(facebookWindow.class.getResource("/facebook_api/Facebook-Logo-100.png")));
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
		panel_1.setBounds(77, 157, 546, 269);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 546, 269);
		panel_1.add(scrollPane);

		list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(676, 380, 64, 32);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(650, 66, 56, 16);
		panel.add(lblNewLabel_3);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(facebookWindow.class.getResource("/mail_api/icons8-administrator-male-24.png")));
		label.setBounds(676, 162, 31, 22);
		panel.add(label);

		JLabel lblNewLabel_4 = new JLabel("Publica\u00E7\u00F5es");

		lblNewLabel_4.setBackground(new Color(135, 206, 250));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(77, 128, 113, 25);
		panel.add(lblNewLabel_4);

		JButton btnVerPublicaes = new JButton("Ver publica\u00E7\u00F5es");
		btnVerPublicaes.setBackground(Color.ORANGE);
		btnVerPublicaes.setBounds(167, 125, 154, 25);
		panel.add(btnVerPublicaes);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(167, 27, 154, 83);
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
				fapi.getListaForSearchPostsFB().clear();
				try {
					fapi.searchForUserPosts(textField_1.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				list_1.clearSelection();
				list_1.setModel(fapi.getListaForSearchPostsFB());
			}
		});

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(495, 106, 127, 43);
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
				list_1.setModel(fapi.getPost_24h());
			}
		});

		btnVerPublicaes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fapi.getListaPostsFB().clear();
				try {
					fapi.AuthUser();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list_1.setModel(fapi.getListaPostsFB());
				panel_5.setVisible(true);
			}
		});

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int index = list_1.getSelectedIndex();
				if (evt.getClickCount() == 2) {
					if (!fapi.connectedToInternet()) {
						JOptionPane.showMessageDialog(null, "N�o � poss�vel ver conte�do do post offline");
					} else {
						for (int i = 0; i < fapi.getPosts().size(); i++) {
							if (index == i) {
								popup = new PopUp_Facebook();
								popup.getLblTweetDe().setText(fapi.getPosts().get(i).getName());
								popup.getLblData().setText(fapi.getPosts().get(i).getCreatedTime().toString());
								message_show = fapi.getPosts().get(i).getMessage();
								popup.getTextArea().setText(message_show);
								popup.getFrame().setVisible(true);
								popup.getTextArea().setLineWrap(true);
								popup.getTextArea().setWrapStyleWord(true);
							}
						}
					}
				}
			}

		});
	}
}
