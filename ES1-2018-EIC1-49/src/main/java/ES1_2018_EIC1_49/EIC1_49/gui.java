package ES1_2018_EIC1_49.EIC1_49;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class gui {

	private JFrame frame;
	private JTextField txtFechar;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		JTextArea txtrEscrevaParaProcurar = new JTextArea();
		txtrEscrevaParaProcurar.setBounds(45, 13, 346, 50);
		txtrEscrevaParaProcurar.setForeground(Color.LIGHT_GRAY);
		txtrEscrevaParaProcurar.setText("Escreva para procurar...");
		panel.add(txtrEscrevaParaProcurar);
		
		JToggleButton tglbtnFacebook = new JToggleButton("Facebook");
		tglbtnFacebook.setBounds(150, 87, 114, 25);
		panel.add(tglbtnFacebook);
		
		JToggleButton tglbtnTwitter = new JToggleButton("Twitter");
		tglbtnTwitter.setBounds(150, 139, 114, 25);
		panel.add(tglbtnTwitter);
		
		JToggleButton toggleButton_1 = new JToggleButton("Facebook");
		toggleButton_1.setBounds(150, 189, 114, 25);
		panel.add(toggleButton_1);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBackground(SystemColor.info);
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnProcurar.setBounds(294, 65, 97, 25);
		panel.add(btnProcurar);
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
