package inventorySystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Color;
import javax.swing.SwingConstants;
//import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
//import javax.swing.SwingConstants;

public class ManageUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPwd;

	/**
	 * Launch the application.
	 */
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/oopfinals", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to hash the password
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
	
	public void table_load() {
		try {
			pst =  con.prepareStatement("SELECT username from accounts");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Username of the Employees");
		th.repaint();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUser frame = new ManageUser("Default User");
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
	public ManageUser(String username) {
		setResizable(false);
		setTitle("User Management");
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon imgIcon = new ImageIcon("img\\frameicon.png"); // Image for Frameicon
        Image frameicon = imgIcon.getImage();
        setIconImage(frameicon);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxShowPass = new JCheckBox("Show Password");
		chckbxShowPass.setBorderPainted(true);
		chckbxShowPass.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxShowPass.setBorder(new LineBorder(new Color(31, 37, 47)));	
		chckbxShowPass.setForeground(new Color(31, 37, 47));	
		chckbxShowPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxShowPass.setBackground(new Color(242, 169, 31));
		chckbxShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxShowPass.isSelected()) {
					txtPwd.setEchoChar((char) 0);
				} else {
					txtPwd.setEchoChar('●'); 
					txtPwd.setFont(new Font("Arial", Font.PLAIN, 15));
					
				}
			}
		});
		
		JLabel lblPassNotice = new JLabel("(8 characters only)");
		lblPassNotice.setForeground(new Color(242, 169, 31));
		lblPassNotice.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassNotice.setBounds(566, 286, 129, 31);
		contentPane.add(lblPassNotice);
		lblPassNotice.setVisible(false);
		
		chckbxShowPass.setBounds(461, 356, 116, 21);
		contentPane.add(chckbxShowPass);
		chckbxShowPass.setVisible(false);
		
		txtUser = new JTextField();
		txtUser.setBorder(new LineBorder(new Color(242, 169, 31), 2));
		txtUser.setBounds(390, 247, 249, 29);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBorder(new LineBorder(new Color(242, 169, 31), 2));
		txtPwd.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				chckbxShowPass.setVisible(true);
				if(chckbxShowPass.isSelected() == true) {
					txtPwd.setEchoChar((char) 0);
				}
				else {
					txtPwd.setEchoChar('●'); 
					txtPwd.setFont(new Font("Arial", Font.PLAIN, 15));
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				chckbxShowPass.setVisible(false);
			}
		});
		txtPwd.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				
				 
				 if(txtPwd.getText().length() >= 8) {
					 e.consume();
					 lblPassNotice.setVisible(true);
				 }
				 else {
					 lblPassNotice.setVisible(false);
				 }
			}
		});
		txtPwd.setBounds(390, 317, 249, 28);
		contentPane.add(txtPwd);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new LineBorder(new Color(31, 37, 47)));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnAdd.setForeground(new Color(31, 37, 47));
		btnAdd.setBackground(new Color(242, 169, 31));
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String password = new String(txtPwd.getPassword());
				
				// Hash the password
				String hashedPassword = hashPassword(password);
				
				if(!user.equals("") && !password.equals("")) {
					
					int result = JOptionPane.showConfirmDialog(null, "Are the data you entered are correct?", 
							"Confirmation",JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						try {
							// Prepare SQL statement
							pst = con.prepareStatement("INSERT INTO adminacc (username, password) VALUES (?, ?)");
							pst.setString(1, user);
							pst.setString(2, hashedPassword);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Account Has Been Added Successfully");
							table_load();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					else if (result == JOptionPane.NO_OPTION) {
						
					}
				
			}
				else {
					JOptionPane.showMessageDialog(null, "Please fill up everything first!");
				}
			}
		});
		btnAdd.setBounds(471, 388, 94, 35);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("");
		btnBack.setFocusable(false);
		btnBack.setIcon(new ImageIcon("img\\back.png"));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminMain am = new AdminMain(username);
			am.setLocationRelativeTo(null); 
			am.setVisible(true);
			setVisible(false);
			}
		});
		btnBack.setBounds(660, 433, 85, 52);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.BOLD, 15));
		scrollPane.setBorder(new LineBorder(new Color(242, 169, 31), 3));
		scrollPane.setBounds(63, 159, 225, 262);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblManageUser = new JLabel("MANAGE USER");
		lblManageUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageUser.setForeground(Color.WHITE);
		lblManageUser.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblManageUser.setBounds(310, 141, 412, 65);
		contentPane.add(lblManageUser);
		
		JLabel usernamelabel = new JLabel("Username");
		usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernamelabel.setForeground(new Color(242, 169, 31));
		usernamelabel.setFont(new Font("Arial", Font.PLAIN, 20));
		usernamelabel.setBounds(423, 217, 186, 29);
		contentPane.add(usernamelabel);
		
		JLabel passwordlabel = new JLabel("Password");
		passwordlabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordlabel.setForeground(new Color(242, 169, 31));
		passwordlabel.setFont(new Font("Arial", Font.PLAIN, 20));
		passwordlabel.setBounds(423, 287, 186, 29);
		contentPane.add(passwordlabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(242, 169, 31));
		separator.setBackground(new Color(242, 169, 31));
		separator.setBounds(298, 146, 2, 288);
		contentPane.add(separator);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(new Color(242, 169, 31));
		separator2.setBackground(new Color(242, 169, 31));
		separator2.setBounds(51, 146, 2, 288);
		contentPane.add(separator2);
		
		JSeparator seperator4 = new JSeparator();
		seperator4.setForeground(new Color(242, 169, 31));
		seperator4.setBackground(new Color(242, 169, 31));
		seperator4.setBounds(51, 146, 249, 2);
		contentPane.add(seperator4);
		
		JSeparator seperator7_1_1 = new JSeparator();
		seperator7_1_1.setForeground(new Color(242, 169, 31));
		seperator7_1_1.setBackground(new Color(242, 169, 31));
		seperator7_1_1.setBounds(51, 432, 249, 2);
		contentPane.add(seperator7_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\manageuserbg.png"));
		lblNewLabel.setBounds(0, 0, 755, 496);
		contentPane.add(lblNewLabel);
		
			
		table_load();
	}
}
