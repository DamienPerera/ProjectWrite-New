import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 748, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setText("");
				
			}
		});
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtUsername.setForeground(new Color(169, 169, 169));
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setText("Username");
		txtUsername.setBounds(233, 158, 264, 38);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setText("");
				
			}
		});
		txtPassword.setText("Password");
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setForeground(new Color(169, 169, 169));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtPassword.setColumns(10);
		txtPassword.setBounds(233, 207, 264, 38);
		frame.getContentPane().add(txtPassword);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBackground(new Color(0, 191, 255));
		btnSignIn.setBounds(287, 265, 164, 38);
		frame.getContentPane().add(btnSignIn);
	}
}
