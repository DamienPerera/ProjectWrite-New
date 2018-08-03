import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class LoginFrame {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

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
		txtUsername.setBounds(263, 156, 338, 38);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String uname = txtUsername.getText();
				String pass =  txtPassword.getText();
				
				if (uname.equals("name") && pass.equals("pass"))
				{
					JOptionPane.showMessageDialog(frame, "Sign In Sucessfull");
					MainMenu mainmenu = new MainMenu();
					mainmenu.setVisible(true);
				
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Sign in Failed");
				}
			}
		});
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBackground(new Color(0, 191, 255));
		btnSignIn.setBounds(263, 269, 164, 38);
		frame.getContentPane().add(btnSignIn);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(263, 212, 338, 34);
		frame.getContentPane().add(txtPassword);
		
		JButton btnRegister = new JButton("Sign Up");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 191, 255));
		btnRegister.setBounds(437, 269, 164, 38);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(81, 212, 172, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("Username :");
		label.setForeground(new Color(47, 79, 79));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(81, 156, 172, 38);
		frame.getContentPane().add(label);
	}
}
