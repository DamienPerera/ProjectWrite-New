import java.awt.BorderLayout;
import java.awt.*;
import java.awt.EventQueue;


import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(184, 86, 136, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(184, 120, 136, 20);
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(96, 89, 78, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(96, 123, 78, 14);
		contentPane.add(lblPassword);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = txtUsername.getText();
				String pass =  txtPassword.getText();
				
				if (uname.equals("name") && pass.equals("pass"))
				{
					Login.this.setVisible(false);
					JOptionPane.showMessageDialog(null, "Sign In Sucessfull");
					
					MainMenu mainmenu = new MainMenu();
			
					mainmenu.setVisible(true);
				
						
					
				
				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "Sign In Unsucessfull");
				}
			}
		});
		btnSignIn.setBounds(96, 166, 89, 23);
		contentPane.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(231, 166, 89, 23);
		contentPane.add(btnSignUp);
	}
}
