package twitter_api;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import ES1_2018_EIC1_49.EIC1_49.App;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterWindow {
	JFrame frame;
	JFrame frame2;
	private JTextField txtFechar;
	private JList<String> list_1;
	private JScrollPane scrollPane;
	twitterAPI twitterAPI = new twitterAPI();
	private JTextField textField;
	public static List<Status> status;
	private long statusId;
	private twitterAPI signin;
	private App app;
	private JTextField textField_1;
	private PopUp popUp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwitterWindow window = new TwitterWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public TwitterWindow() {
		try {
			initialize();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws TwitterException {
		frame = new JFrame();
		frame.setBounds(500, 500, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		txtFechar = new JTextField();
		txtFechar.setText("Fechar");
		txtFechar.setColumns(10);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 466);
		panel.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(panel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(116, 107, 588, 196);
		panel.add(scrollPane);

		list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setBounds(344, 0, 277, 288);
		label.setSize(100, 100);
		panel.add(label);
		label.setIcon(new ImageIcon(TwitterWindow.class.getResource("twitter.png")));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(116, 17, 164, 83);
		panel.add(panel_3);
		panel_3.setVisible(false);
		panel_3.setLayout(null);

		JToggleButton tglbtnTwitter = new JToggleButton("[ISCTE] Tweets");
		tglbtnTwitter.setBackground(Color.WHITE);
		tglbtnTwitter.setBounds(575, 82, 129, 25);
		tglbtnTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitterAPI.ISCTETimeLine(signin);
				list_1.setModel(twitterAPI.dlm);
				panel_3.setVisible(true);
			}
		});

		panel.setLayout(null);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(12, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app = new App();
				app.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 255));
		panel_1.setBounds(148, 355, 504, 73);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JToggleButton tweetButton = new JToggleButton("Twittar");
		tweetButton.setBounds(408, 35, 84, 25);
		panel_1.add(tweetButton);
		tweetButton.setBackground(Color.WHITE);

		textField = new JTextField();
		textField.setBounds(12, 6, 480, 22);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblOQueEst = new JLabel("O que está acontecendo?");
		lblOQueEst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOQueEst.setBounds(136, 39, 244, 21);
		panel_1.add(lblOQueEst);

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
				twitterAPI.searchForTagInISCTETimeLine(textField_1.getText());
				list_1.clearSelection();
				list_1.setModel(twitterAPI.searchTagList);
			}
		});

		tweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					twitterAPI.tweet(textField.getText(), signin);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				}
			}
		});

		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(487, 301, 217, 35);
		panel.add(panel_2);
		panel_2.setVisible(false);
		JButton btnRetweetar = new JButton("Retweetar");
		btnRetweetar.setBackground(new Color(135, 206, 235));
		panel_2.add(btnRetweetar);
		btnRetweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitterAPI.retweetIt(statusId, signin);
				System.out.println("Retweetado");
				panel_2.setVisible(false);
			}
		});

		JLabel lblTweetSelecioando = new JLabel("tweet selecionado");
		panel_2.add(lblTweetSelecioando);

		panel.add(tglbtnTwitter);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(506, 13, 196, 59);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JButton btnFollowers = new JButton("Followers");
		btnFollowers.setBounds(0, 0, 97, 33);
		panel_4.add(btnFollowers);

		JButton btnFollowing = new JButton("Following");
		btnFollowing.setBounds(92, 0, 104, 33);
		panel_4.add(btnFollowing);

		JLabel number_followers = new JLabel(twitterAPI.getNumberFollowers());
		number_followers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		number_followers.setBounds(124, 30, 85, 29);
		panel_4.add(number_followers);

		JLabel numero_following = new JLabel(twitterAPI.getNumberFollowing());
		numero_following.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numero_following.setBounds(34, 30, 78, 29);
		panel_4.add(numero_following);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(116, 304, 164, 43);
		panel.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(true);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(121, 0, 43, 43);
		panel_5.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Utilizador\\Desktop\\283982_thm.png"));

		JLabel lblOrganizarPorTempo = new JLabel("\u00DAltimas 24h");
		lblOrganizarPorTempo.setBounds(33, 0, 76, 16);
		panel_5.add(lblOrganizarPorTempo);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBackground(new Color(135, 206, 235));
		btnFiltrar.setBounds(35, 18, 67, 22);
		panel_5.add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				twitterAPI.filtrarUltimas24horas();
				list_1.setModel(twitterAPI.post_24h);
			}
		});
		btnFollowing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_1.clearSelection();
				list_1.setModel(twitterAPI.followingList);
				panel_3.setVisible(false);
				panel_2.setVisible(false);
			}
		});
		btnFollowers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_1.clearSelection();
				list_1.setModel(twitterAPI.followersList);
				panel_3.setVisible(false);
				panel_2.setVisible(false);
			}
		});
		
		
		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				status = twitterAPI.getStatus();
				int index = list_1.getSelectedIndex();
				if (evt.getClickCount() == 1) {
					panel_2.setVisible(true);
					for (int i = 0; i < status.size(); i++) {
						if (i == index)
							statusId = status.get(i).getId();
					}
				}
				if (evt.getClickCount() == 3) {
					for (int i = 0; i < status.size(); i++) {
						if (index == i) {
							String title= "Tweet de " + status.get(i).getUser().getName() + " | " + status.get(i).getUser().getCreatedAt();
							String message = status.get(i).getText(); 
							JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
	}
}
