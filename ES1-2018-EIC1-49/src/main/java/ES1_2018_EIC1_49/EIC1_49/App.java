package ES1_2018_EIC1_49.EIC1_49;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;

import facebook_api.AuthenticationFacebookWindow;
import mail_api.AuthenticationMailWindow;
import twitter4j.Status;
import twitter_api.AuthenticationTwitterWindow;

public class App extends JFrame {

	private JFrame frame;
	private static List<Status> status;
	private String OAuthConsumerKey = "kVRX8HYyuuGfREHU52O7AUrWQ";
	private String OAuthConsumerSecret = "XwGpzZUWsnTXwXQgSeCqAgDLBvelcOCkkX1RmYf4UwXZ60uoY9";
	private String AccessToken = "1050057518121148419-d704OUJA2VWxqFhBHI2j1wkS0e4cpZ";
	private String AccessTokenSecret = "JSlIzbw0hP0t1tsM7RhUABb0q1yD3ZVh96LYhw766CIn4";
	private bdaAPP bdaAPP;
	private AuthenticationMailWindow authenticationMailWindow;
	private AuthenticationTwitterWindow twitterwindow;
	private AuthenticationFacebookWindow afw;
	private App app;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
//					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		System.out.println(frame.getSize());

		JToggleButton tglbtnFacebook = new JToggleButton("Facebook");
		tglbtnFacebook.setBackground(Color.WHITE);
		tglbtnFacebook.setBounds(117, 110, 120, 49);
		tglbtnFacebook.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				afw = new AuthenticationFacebookWindow();
				afw.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(tglbtnFacebook);

		JToggleButton tglbtnTwitter = new JToggleButton("Twitter");
		tglbtnTwitter.setBackground(Color.WHITE);
		tglbtnTwitter.setBounds(117, 223, 120, 49);
		tglbtnTwitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				twitterwindow = new AuthenticationTwitterWindow();
				twitterwindow.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(tglbtnTwitter);

		JToggleButton tglbtnEmail = new JToggleButton("E-mail");
		tglbtnEmail.setBackground(Color.WHITE);
		tglbtnEmail.setBounds(117, 340, 120, 49);
		tglbtnEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				authenticationMailWindow = new AuthenticationMailWindow();
				authenticationMailWindow.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(tglbtnEmail);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(App.class.getResource("/mail_api/1275392-t100.png")));
		lblNewLabel.setBounds(249, 302, 126, 124);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(App.class.getResource("/twitter_api/twitter.png")));
		lblNewLabel_1.setBounds(249, 180, 107, 134);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(App.class.getResource("/ES1_2018_EIC1_49/EIC1_49/Facebook-Logo-100.png")));
		lblNewLabel_2.setBounds(249, 77, 117, 117);
		panel.add(lblNewLabel_2);

		JButton btnVoltar = new JButton("Log-out");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bdaAPP = new bdaAPP();
				bdaAPP.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnVoltar);

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
