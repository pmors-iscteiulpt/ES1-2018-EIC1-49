package twitter_api;

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
import java.io.File;
import java.io.FileNotFoundException;
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

import ES1_2018_EIC1_49.EIC1_49.App;
import ES1_2018_EIC1_49.EIC1_49.bdaAPP;
import facebook_api.printWriter;
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
import java.awt.Font;

public class TwitterWindow {
	JFrame frame;
	private JTextField txtFechar;
	private JList<String> list_1;
	private JScrollPane scrollPane;
	twitterAPI twitterAPI = new twitterAPI();
	private JTextField textField;
	public static List<Status> status;
	private long statusId;
	private twitterAPI signin ;
	private App app;
	private printWriter printwriter;
	

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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 107, 599, 196);
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

		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(487, 301, 217, 35);
		panel.add(panel_2);
		panel_2.setVisible(false);
		JButton btnRetweetar = new JButton("Retweetar");
		btnRetweetar.setBackground(new Color(135, 206, 235));
		panel_2.add(btnRetweetar);
		btnRetweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitterAPI.retweetIt(statusId, signin );
				System.out.println("Retweetado");
			}
		});
		
		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					panel_2.setVisible(true);
					int index = list_1.getSelectedIndex();
					status = twitterAPI.getStatus();
					for (int i = 0; i < status.size(); i++) {
						if (i == index)
						statusId = status.get(i).getId();
					}

				}
			}
		});
		JToggleButton tglbtnTwitter = new JToggleButton("[ISCTE] Tweets");
		tglbtnTwitter.setBackground(Color.WHITE);
		tglbtnTwitter.setBounds(575, 69, 129, 25);
		tglbtnTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitterAPI.ISCTETimeLine(signin);
				list_1.setModel(twitterAPI.dlm);
			
			}
		});

		panel.setLayout(null);
		panel.add(tglbtnTwitter);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app = new App();
				app.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

		JLabel lblTweetSelecioando = new JLabel("tweet selecionado");
		panel_2.add(lblTweetSelecioando);
		
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
				tweetButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							twitterAPI.tweet(textField.getText(), signin);
						} catch (TwitterException e1) {
							e1.printStackTrace();
						}
					}
				});
	}
}
