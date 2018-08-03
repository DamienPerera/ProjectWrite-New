import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mnist.Mnist;
import trainset.TrainSet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainingPlatform extends JFrame {

	private JPanel contentPane;
	private JTextField txtEpoch;
	private JLabel lblBatchSize;
	private JLabel lblHiddenLayer_1;
	private JLabel lblNumberOfLoops;
	private JLabel lblHiddenLayer;
	private JTextField txtBatchSize;
	private JTextField txtLoops;
	private JTextField txtHidden1;
	private JTextField txtHidden2;
	private JLabel lblNetworkTraining;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainingPlatform frame = new TrainingPlatform();
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
	public TrainingPlatform() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEpoch = new JTextField();
		txtEpoch.setBounds(221, 81, 363, 32);
		contentPane.add(txtEpoch);
		txtEpoch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Number of Epochs:");
		lblNewLabel.setBounds(93, 90, 182, 14);
		contentPane.add(lblNewLabel);
		
		lblBatchSize = new JLabel("Batch Size:");
		lblBatchSize.setBounds(93, 139, 182, 14);
		contentPane.add(lblBatchSize);
		
		lblHiddenLayer_1 = new JLabel("Hidden Layer 2 Size:");
		lblHiddenLayer_1.setBounds(93, 284, 182, 14);
		contentPane.add(lblHiddenLayer_1);
		
		lblNumberOfLoops = new JLabel("Number of Loops:");
		lblNumberOfLoops.setBounds(93, 189, 182, 14);
		contentPane.add(lblNumberOfLoops);
		
		lblHiddenLayer = new JLabel("Hidden Layer 1 Size:");
		lblHiddenLayer.setBounds(92, 236, 182, 14);
		contentPane.add(lblHiddenLayer);
		
		txtBatchSize = new JTextField();
		txtBatchSize.setColumns(10);
		txtBatchSize.setBounds(221, 136, 363, 32);
		contentPane.add(txtBatchSize);
		
		txtLoops = new JTextField();
		txtLoops.setColumns(10);
		txtLoops.setBounds(221, 186, 363, 32);
		contentPane.add(txtLoops);
		
		txtHidden1 = new JTextField();
		txtHidden1.setColumns(10);
		txtHidden1.setBounds(220, 233, 363, 32);
		contentPane.add(txtHidden1);
		
		txtHidden2 = new JTextField();
		txtHidden2.setColumns(10);
		txtHidden2.setBounds(221, 281, 363, 32);
		contentPane.add(txtHidden2);
		
		lblNetworkTraining = new JLabel("NETWORK TRAINING");
		lblNetworkTraining.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNetworkTraining.setBounds(23, 10, 294, 39);
		contentPane.add(lblNetworkTraining);
		
		
		JButton btnTrainNetwork = new JButton("Train Network");
		btnTrainNetwork.addActionListener(new ActionListener() {
			int epoch =0;
			int batch =0;
			int loop=0;
			int hidden1=0;
			int hidden2=0;
			
			public void actionPerformed(ActionEvent arg0) {
				 
			
				
			}
		});
		btnTrainNetwork.setBounds(402, 344, 182, 39);
		contentPane.add(btnTrainNetwork);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mnist.main(null);
			}
		});
		btnClear.setBounds(168, 344, 182, 39);
		contentPane.add(btnClear);
	}
}
