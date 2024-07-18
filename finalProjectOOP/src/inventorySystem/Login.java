package inventorySystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;



public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernamefield;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/oopfinals", "root", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Login() {
		Connect();
		
		setTitle("Construction Inventory Employee Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon frameicon = new ImageIcon("img\\frameicon.png");
        setIconImage(frameicon.getImage());
		
		JCheckBox chckbxShowPass = new JCheckBox("Show Password");
		chckbxShowPass.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxShowPass.setBorderPainted(true);
		chckbxShowPass.setBorder(new LineBorder(new Color(31, 37, 47)));
		chckbxShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxShowPass.isSelected()) {
					passwordField.setEchoChar((char) 0); 
				} else {
					passwordField.setEchoChar('●'); 
					passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
				}
			}
		});
		chckbxShowPass.setVisible(false);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("img\\exit.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", 
						"Exit Confirmation",JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
				else if (result == JOptionPane.NO_OPTION) {
					
				}
			}
		});
		
		JButton btnAdmin = new JButton("");
		btnAdmin.setDefaultCapable(false);
		btnAdmin.setContentAreaFilled(false);
		btnAdmin.setBorderPainted(false);
		btnAdmin.setIcon(new ImageIcon("img\\switchadmin.png"));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AdminLogin adm = new AdminLogin();
				 adm.setLocationRelativeTo(null);
				 adm.setVisible(true);
				 setVisible(false);
			}
		});
		
		JLabel lblPassNotice = new JLabel("(8 Characters only)");
		lblPassNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassNotice.setForeground(new Color(242, 169, 31));
		lblPassNotice.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassNotice.setBounds(578, 260, 140, 29);
		contentPane.add(lblPassNotice);
		lblPassNotice.setVisible(false);
		
		btnAdmin.setRequestFocusEnabled(false);
		btnAdmin.setForeground(new Color(242, 169, 31));
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdmin.setBorder(new LineBorder(new Color(31, 37, 47)));
		btnAdmin.setBackground(new Color(246, 169, 31));
		btnAdmin.setBounds(10, 433, 59, 52);
		contentPane.add(btnAdmin);
		
		JLabel lblNoUser = new JLabel("*");
		lblNoUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoUser.setForeground(Color.RED);
		lblNoUser.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNoUser.setBounds(671, 220, 19, 29);
		contentPane.add(lblNoUser);
		lblNoUser.setVisible(false);
		
		JLabel lblNoPass = new JLabel("*");
		lblNoPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoPass.setForeground(new Color(255, 0, 0));
		lblNoPass.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNoPass.setBounds(671, 289, 19, 29);
		contentPane.add(lblNoPass);
		lblNoPass.setVisible(false);
		
		btnExit.setRequestFocusEnabled(false);
		btnExit.setForeground(new Color(31, 37, 47));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBorder(new LineBorder(new Color(31, 37, 47)));
		btnExit.setBackground(new Color(246, 169, 31));
		btnExit.setBounds(677, 443, 69, 29);
		contentPane.add(btnExit);
		chckbxShowPass.setBackground(new Color(242, 161, 31));
		chckbxShowPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxShowPass.setForeground(new Color(31, 37, 47));
		chckbxShowPass.setBounds(468, 325, 129, 23);
		contentPane.add(chckbxShowPass);

		
		JLabel loginlabel = new JLabel("EMPLOYEE LOGIN");
		loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginlabel.setForeground(new Color(255, 255, 255));
		loginlabel.setFont(new Font("Times New Roman", Font.BOLD, 42));
		loginlabel.setBounds(312, 114, 434, 65);
		contentPane.add(loginlabel);
		
		JLabel usernamelabel = new JLabel("Username");
		usernamelabel.setForeground(new Color(242, 169, 31));
		usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernamelabel.setFont(new Font("Arial", Font.PLAIN, 20));
		usernamelabel.setBounds(452, 191, 156, 29);
		contentPane.add(usernamelabel);
		
		usernamefield = new JTextField();
		usernamefield.setFont(new Font("Arial", Font.PLAIN, 15));
		usernamefield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (usernamefield.getText().equals("Username")) {
					usernamefield.setText("");
					usernamefield.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (usernamefield.getText().isEmpty()) {
					usernamefield.setText("Username");
					usernamefield.setForeground(Color.GRAY);
				}
			}
		});
		usernamefield.setBorder(new LineBorder(new Color(246, 169, 31)));
		usernamefield.setBounds(403, 220, 258, 29);
		contentPane.add(usernamefield);
		usernamefield.setColumns(10);
		
		JLabel Password = new JLabel("Password");
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setForeground(new Color(242, 169, 31));
		Password.setFont(new Font("Arial", Font.PLAIN, 20));
		Password.setBounds(452, 260, 156, 29);
		contentPane.add(Password);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				if(passwordField.getText().length() >= 8) {
					 e.consume();
					 lblPassNotice.setVisible(true);
				}
				else {
					lblPassNotice.setVisible(false);
				}
			}
		});
		passwordField.setText("Password");
		passwordField.setForeground(Color.GRAY);
		passwordField.setEchoChar((char) 0); 
		passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(passwordField.getPassword()).equals("Password")) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('\u2022'); 
					chckbxShowPass.setVisible(true);
					if(chckbxShowPass.isSelected() == true) {
						passwordField.setEchoChar((char) 0);
					}
					else {
						passwordField.setEchoChar('●'); 
						passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
					}
				}
				else {
					chckbxShowPass.setVisible(true);
					if(chckbxShowPass.isSelected() == true) {
						passwordField.setEchoChar((char) 0);
					}
					else {
						passwordField.setEchoChar('●'); 
						passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
					}
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(passwordField.getPassword()).isEmpty()) {
					passwordField.setText("Password");
					passwordField.setForeground(Color.GRAY);
					passwordField.setEchoChar((char) 0); 
					chckbxShowPass.setVisible(false);
				}
				else {
					chckbxShowPass.setVisible(false);
				}
			}
		});
		passwordField.setBorder(new LineBorder(new Color(246, 169, 31)));
		passwordField.setBounds(403, 289, 258, 29);
		contentPane.add(passwordField);
		
		JButton loginbutton = new JButton("Login");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = usernamefield.getText(); 
				String pwd = new String(passwordField.getPassword());
				
				//hash password
				String hashedPassword = hashPassword(pwd);
				//pwd = hashedPassword;
				
				if(!user.equals("") && !pwd.equals("")) {
					if(!user.equals("Username") && !pwd.equals("Password")) {
				
				try {
					
					pst = con.prepareStatement("SELECT * FROM accounts WHERE username = ? AND password = ?");
					pst.setString(1, user);
					pst.setString(2, hashedPassword);
					rs = pst.executeQuery();
					
					if (rs.next()) { 
							JOptionPane.showMessageDialog(null, "Login Successfully");
							 Inventory inv = new Inventory(user);
							 inv.setLocationRelativeTo(null);
							 inv.setVisible(true);
							 setVisible(false);
							 
							 
						} else {
							JOptionPane.showMessageDialog(null, "Login Failed: Invalid input of Username or Password");
							lblNoUser.setVisible(false);
							lblNoPass.setVisible(false);
						}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 		
				}
					else {
						if(user.equals("Username") || user.equals("")) {
							JOptionPane.showMessageDialog(null, "Please insert a username first!");
							lblNoUser.setVisible(true);
							lblNoPass.setVisible(false);
						}
						else if (pwd.equals("Password") || pwd.equals("")) {
							JOptionPane.showMessageDialog(null, "Please insert a password first!");
							lblNoUser.setVisible(false);
							lblNoPass.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please complete inserting username and password first!");
							lblNoUser.setVisible(true);
							lblNoPass.setVisible(true);
						}
					}
				}
				else {
					if(user.equals("Username") || user.equals("")) {
						if(pwd.equals("Password")) {
							JOptionPane.showMessageDialog(null, "Please complete inserting username and password first!");
							lblNoUser.setVisible(true);
							lblNoPass.setVisible(true);
						}
						else if (user.equals("Username")) {
							JOptionPane.showMessageDialog(null, "Please complete inserting username and password first!");
							lblNoUser.setVisible(true);
							lblNoPass.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Please insert a username first!");
							lblNoUser.setVisible(true);
							lblNoPass.setVisible(false);
						}	
					}
					else if (pwd.equals("Password") || pwd.equals("")) {
						JOptionPane.showMessageDialog(null, "Please insert a password first!");
						lblNoUser.setVisible(false);
						lblNoPass.setVisible(true);
					}
					
				}
			}
		});
		loginbutton.setRequestFocusEnabled(false);
		loginbutton.setFont(new Font("Arial", Font.BOLD, 15));
		loginbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		loginbutton.setBackground(new Color(242, 169, 31));
		loginbutton.setForeground(new Color(31, 37, 47));
		loginbutton.setBounds(482, 359, 98, 42);
		contentPane.add(loginbutton);
		
		JButton employeeicon = new JButton("");
		employeeicon.setContentAreaFilled(false);
		employeeicon.setBorderPainted(false);
		employeeicon.setRolloverEnabled(false);
		employeeicon.setFocusPainted(false);
		employeeicon.setFocusTraversalKeysEnabled(false);
		employeeicon.setVerifyInputWhenFocusTarget(false);
		employeeicon.setRequestFocusEnabled(false);
		employeeicon.setIcon(new ImageIcon("img\\employee.png"));
		employeeicon.setBounds(45, 144, 233, 226);
		contentPane.add(employeeicon);
		
		JLabel employeelabel = new JLabel("Employee");
		employeelabel.setHorizontalAlignment(SwingConstants.CENTER);
		employeelabel.setForeground(new Color(242, 169, 31));
		employeelabel.setFont(new Font("Georgia", Font.BOLD, 20));
		employeelabel.setBounds(81, 361, 156, 29);
		contentPane.add(employeelabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(242, 169, 31));
		separator.setBackground(new Color(242, 169, 31));
		separator.setBounds(300, 114, 2, 313);
		contentPane.add(separator);
		
		JButton buttonhome = new JButton("");
		buttonhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main mn = new Main();
				mn.setVisible(true);
				mn.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		buttonhome.setFocusPainted(false);
		buttonhome.setIcon(new ImageIcon("img\\homeyellow.png"));
		buttonhome.setRequestFocusEnabled(false);
		buttonhome.setForeground(new Color(242, 169, 31));
		buttonhome.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonhome.setDefaultCapable(false);
		buttonhome.setContentAreaFilled(false);
		buttonhome.setBorderPainted(false);
		buttonhome.setBorder(new LineBorder(new Color(31, 37, 47)));
		buttonhome.setBackground(new Color(246, 169, 31));
		buttonhome.setBounds(10, 11, 59, 52);
		contentPane.add(buttonhome);
		
		JLabel employeebglabel = new JLabel("");
		employeebglabel.setBackground(new Color(242, 169, 31));
		employeebglabel.setFocusTraversalKeysEnabled(false);
		employeebglabel.setIcon(new ImageIcon("img\\employeebg.png"));
		employeebglabel.setBounds(0, 0, 755, 496);
		contentPane.add(employeebglabel);
	}
}
