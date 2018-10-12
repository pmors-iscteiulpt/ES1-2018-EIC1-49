package ES1_2018_EIC1_49.EIC1_49;

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
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JToggleButton;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class App {

	private JFrame frame;
	private JTextField txtFechar;
	private JTextField textField;
	private TwitterAPI twitterapi;
	private JList<String> list_1;
	private JScrollPane scrollPane;
	private static List<Status> status;
	private JTextField textField_1;
    private String OAuthConsumerKey ="kVRX8HYyuuGfREHU52O7AUrWQ";
	private String OAuthConsumerSecret ="XwGpzZUWsnTXwXQgSeCqAgDLBvelcOCkkX1RmYf4UwXZ60uoY9";
	private String AccessToken = "1050057518121148419-d704OUJA2VWxqFhBHI2j1wkS0e4cpZ";
	private String AccessTokenSecret="JSlIzbw0hP0t1tsM7RhUABb0q1yD3ZVh96LYhw766CIn4";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFicheiro = new JMenu("Ficheiro");
		menuBar.add(mnFicheiro);

		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		menuBar.add(mntmAjuda);

		textField = new JTextField();
		menuBar.add(textField);
		textField.setColumns(10);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);

		txtFechar = new JTextField();
		txtFechar.setText("Fechar");
		popupMenu.add(txtFechar);
		txtFechar.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JToggleButton tglbtnFacebook = new JToggleButton("Facebook");
		tglbtnFacebook.setBounds(10, 93, 114, 25);
		tglbtnFacebook.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						javafx.application.Application.launch(AuthenticationWindow.class);
					}
				}.start();
				System.out.println("sddsdd");

			}
		});
		panel.add(tglbtnFacebook);

		JToggleButton tglbtnTwitter = new JToggleButton("Twitter");
		tglbtnTwitter.setBounds(10, 129, 114, 25);
		tglbtnTwitter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
				configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey(OAuthConsumerKey)
						.setOAuthConsumerSecret(OAuthConsumerSecret)
						.setOAuthAccessToken(AccessToken)
						.setOAuthAccessTokenSecret(AccessTokenSecret);

				TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
				twitter4j.Twitter twitter = tf.getInstance();
				try {
					status = twitter.getUserTimeline("ISCTEIUL");
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultListModel<String> dlm = new DefaultListModel<String>();
				for (Status s : status) {
					dlm.addElement(
							"Time:" + s.getCreatedAt() + "   " + s.getUser().getName() + "-------->" + s.getText());

				}
				list_1.setModel(dlm);

			}
		});
		panel.add(tglbtnTwitter);

		JToggleButton toggleButton_1 = new JToggleButton("Facebook");
		toggleButton_1.setBounds(10, 165, 114, 25);
		panel.add(toggleButton_1);

		JButton btnProcurar = new JButton("Procurar");

		btnProcurar.setBounds(304, 34, 97, 25);
		btnProcurar.setBackground(SystemColor.info);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel<String> dlm = new DefaultListModel<String>();
				for (Status s : status) {
					if (s.getText().contains(textField_1.getText())) {
						dlm.addElement(s.getUser().getName() + "-------->" + s.getText());
					}
					list_1.setModel(dlm);
				}

			}
		});
		panel.add(btnProcurar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 102, 431, 269);
		panel.add(scrollPane);

		list_1 = new JList();
		scrollPane.setViewportView(list_1);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 11, 274, 71);
		panel.add(textField_1);
		textField_1.setColumns(10);

	}

	/**
	 * @return the oAuthConsumerKey
	 */
	public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}

	/**
	 * @param oAuthConsumerKey the oAuthConsumerKey to set
	 */
	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		OAuthConsumerKey = oAuthConsumerKey;
	}

	/**
	 * @return the oAuthConsumerSecret
	 */
	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}

	/**
	 * @param oAuthConsumerSecret the oAuthConsumerSecret to set
	 */
	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		OAuthConsumerSecret = oAuthConsumerSecret;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return AccessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}

	/**
	 * @return the accessTokenSecret
	 */
	public String getAccessTokenSecret() {
		return AccessTokenSecret;
	}

	/**
	 * @param accessTokenSecret the accessTokenSecret to set
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		AccessTokenSecret = accessTokenSecret;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
