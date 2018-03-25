package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FB301 {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	private JPasswordField passwordField;
	String username=null;
	String password=null;
	String osName="windows";
	String filename=null;
	private JTextField txtCprogramFilesxgooglechromeapplicationchromeexe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FB301 window = new FB301();
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
	public FB301() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "facebook login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 77, 408, 85);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 25, 100, 16);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 54, 100, 16);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(124, 22, 272, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(124, 51, 272, 22);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Select OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 176, 408, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			osName="windows";
			txtCprogramFilesxgooglechromeapplicationchromeexe.setText("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			}
		});
		rdbtnWindows.setSelected(true);
		rdbtnWindows.setBounds(8, 18, 110, 25);
		panel_1.add(rdbtnWindows);
		buttonGroup.add(rdbtnWindows);
		
		JRadioButton rdbtnLinux = new JRadioButton("linux");
		rdbtnLinux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			osName="linux";
			txtCprogramFilesxgooglechromeapplicationchromeexe.setText("/usr/bin/google-chrome");
			}
		});
		rdbtnLinux.setBounds(122, 18, 110, 25);
		panel_1.add(rdbtnLinux);
		buttonGroup.add(rdbtnLinux);
		
		JRadioButton rdbtnMac = new JRadioButton("mac");
		rdbtnMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			osName="mac";
			txtCprogramFilesxgooglechromeapplicationchromeexe.setText("/Applications/Chrome.app/Contents/MacOS/chrome");
			}
		});
		rdbtnMac.setBounds(236, 18, 110, 25);
		panel_1.add(rdbtnMac);
		buttonGroup.add(rdbtnMac);
		
		JLabel lblSaveFileAs = new JLabel("Save file as:");
		lblSaveFileAs.setBounds(12, 247, 90, 16);
		frame.getContentPane().add(lblSaveFileAs);
		
		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setBounds(114, 244, 254, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnExecute = new JButton("execute >>");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 username=textField.getText().trim();
				 password =new String(((JPasswordField) passwordField).getPassword());
				 filename=textField_2.getText();
				 if(username.length()>0&&password.length()>0&&filename.length()>0){
					 execute obj = new execute(username,password,filename,osName);
					 frame.dispose();
					 obj.setVisible(true);
				 }else
					 JOptionPane.showMessageDialog(null,"please input all fields!"); 			
			}
		});
		btnExecute.setBounds(159, 315, 120, 25);
		frame.getContentPane().add(btnExecute);
		
		JLabel lbltxt = new JLabel(".txt");
		lbltxt.setBounds(380, 244, 40, 16);
		frame.getContentPane().add(lbltxt);
		
		JLabel lblFbv = new JLabel("FB301-v0.3");
		lblFbv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFbv.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblFbv.setBounds(12, 13, 408, 51);
		frame.getContentPane().add(lblFbv);
		
		JLabel lblPathToChrome = new JLabel("path to chrome");
		lblPathToChrome.setBounds(12, 285, 100, 16);
		frame.getContentPane().add(lblPathToChrome);
		
		txtCprogramFilesxgooglechromeapplicationchromeexe = new JTextField();
		txtCprogramFilesxgooglechromeapplicationchromeexe.setHorizontalAlignment(SwingConstants.LEFT);
		txtCprogramFilesxgooglechromeapplicationchromeexe.setText("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		txtCprogramFilesxgooglechromeapplicationchromeexe.setBounds(114, 280, 306, 22);
		frame.getContentPane().add(txtCprogramFilesxgooglechromeapplicationchromeexe);
		txtCprogramFilesxgooglechromeapplicationchromeexe.setColumns(10);
		
		JLabel lblSaveFriendList = new JLabel("Friend list scraper bot");
		lblSaveFriendList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveFriendList.setBounds(12, 55, 408, 16);
		frame.getContentPane().add(lblSaveFriendList);
		
	}
}
