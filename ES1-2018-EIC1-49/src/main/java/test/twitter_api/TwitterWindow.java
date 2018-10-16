package test.twitter_api;

/**
 * Hello world!
 *
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class TwitterWindow {
	JFrame frame;
	private JTextField txtFechar;
	private JList<String> list_1;
	private JScrollPane scrollPane;
	twitterISCTETimeLine iscteTL = new twitterISCTETimeLine();
	twittar tweet = new twittar();
	private JTextField textField;
	retweet ret = new retweet();
	twitterSignIn logIn = new twitterSignIn();
	public static List<Status> status;
	private long statusId;
	private twitterSignIn signin ;

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
		panel.setBounds(0, 0, 782, 453);
		panel.setBackground(new Color(135, 206, 250));
		frame.getContentPane().add(panel);

		JToggleButton tglbtnTwitter = new JToggleButton("[ISCTE] Tweets");
		tglbtnTwitter.setBounds(405, 301, 129, 25);
		tglbtnTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iscteTL.ISCTETimeLine(signin);
				list_1.setModel(iscteTL.dlm);
			}
		});

		panel.setLayout(null);
		panel.add(tglbtnTwitter);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 107, 429, 181);
		panel.add(scrollPane);

		list_1 = new JList<String>();
		scrollPane.setViewportView(list_1);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		Button tweetButton = new Button("Twittar");
		tweetButton.setBounds(461, 392, 73, 24);
		panel.add(tweetButton);
		tweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tweet.tweet(textField.getText(), signin);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				}
			}
		});

		textField = new JTextField();
		textField.setBounds(105, 356, 429, 22);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(634, 107, 97, 25);
		panel.add(btnSignIn);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logIn.logIn();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setBounds(634, 24, 277, 288);
		label.setSize(100, 100);
		panel.add(label);
		label.setIcon(new ImageIcon(TwitterWindow.class.getResource("twitter.png")));

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					int index = list_1.getSelectedIndex();
					status = twitterISCTETimeLine.getStatus();
					for (int i = 0; i < status.size(); i++) {
						if (i == index)
						statusId = status.get(i).getId();
					}

				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(115, 301, 217, 35);
		panel.add(panel_2);
		JButton btnRetweetar = new JButton("Retweetar");
		panel_2.add(btnRetweetar);
		btnRetweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ret.retweetIt(statusId, signin);
				System.out.println("Retweetado");
			}
		});
		

		JLabel lblTweetSelecioando = new JLabel("tweet selecionado");
		panel_2.add(lblTweetSelecioando);
	}

}
