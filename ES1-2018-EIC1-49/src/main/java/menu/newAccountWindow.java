package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class newAccountWindow {

	/**
	 * Janela para criar um novo utilizador, para depois ser "gravado" no ficheiro
	 * xml.
	 */

	private bdaAPP bdaApp;

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

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnSave;
	private JLabel lblCriarNovoUsurio;

	/**
	 * Create the application.
	 */
	public newAccountWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(67, 238, 84, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(73, 271, 71, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurso.setBounds(97, 307, 46, 14);
		frame.getContentPane().add(lblCurso);

		textField = new JTextField();
		textField.setBounds(151, 236, 269, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(151, 269, 269, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(151, 305, 269, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		btnSave = new JButton("Guardar");
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(331, 376, 89, 23);
		frame.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Utilizador newUser = new Utilizador(textField.getText(), textField_1.getText(), textField_2.getText());
				CreateXML createxml = new CreateXML();
				createxml.addUser(newUser);
				bdaApp = new bdaAPP();
				bdaApp.getFrame().setVisible(true);
				frame.setVisible(false);

			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(27, 27, 97, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bdaApp = new bdaAPP();
				bdaApp.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnVoltar);

		lblCriarNovoUsurio = new JLabel("Criar novo usuário");
		lblCriarNovoUsurio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCriarNovoUsurio.setBounds(170, 151, 172, 20);
		frame.getContentPane().add(lblCriarNovoUsurio);
	}
}
