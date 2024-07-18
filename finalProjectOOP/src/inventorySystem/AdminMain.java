package inventorySystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class AdminMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtType;
	private JTextField txtBrand;
	private JTextField txtQty;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain("Default User");
					frame.setLocationRelativeTo(null);  
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// SQL Variable for connection to SQL database
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtID;
	private JTextField txtTypeMirror;
	private JTextField txtQtyMirror;
	private JTextField txtBrandMirror;
	private JTextField txtIDMirror;
	private JTextField textSearchArchive;
	private JTextField txtTypeMirrorArch;
	private JTextField txtBrandMirrorArch;
	private JTextField txtQtyMirrorArch;
	private JTextField textField_4;
	private JTextField txtIDArch;
	private JTable table;
	private JTable table_1;
	/**
	 * Create the frame.
	 */
	
	
	// Main try and catch method to connect to database
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
	
	
	//Mirror of table in database to jtable in inventory
	public void table_load() {
		try {
			pst =  con.prepareStatement("SELECT * from inventory");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("ID Number"); 
		th.repaint();
		
		JTableHeader th1 = table.getTableHeader();
		TableColumnModel tcm1 = th1.getColumnModel();
		TableColumn tc1 = tcm1.getColumn(1);
		tc1.setHeaderValue("Type");
		th1.repaint();
		
		JTableHeader th2 = table.getTableHeader();
		TableColumnModel tcm2 = th2.getColumnModel();
		TableColumn tc2 = tcm2.getColumn(2);
		tc2.setHeaderValue("Brand");
		th2.repaint();
		
		JTableHeader th3 = table.getTableHeader();
		TableColumnModel tcm3 = th3.getColumnModel();
		TableColumn tc3 = tcm3.getColumn(3);
		tc3.setHeaderValue("Quantity");
		th3.repaint();
		
	}
	
	//Mirror of table in database to jtable in archive
	public void table_loadArchive() {
		try {
			pst =  con.prepareStatement("SELECT * from archive");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			
		}
		JTableHeader th = table_1.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("ID Number"); 
		th.repaint();
		
		JTableHeader th1 = table_1.getTableHeader();
		TableColumnModel tcm1 = th1.getColumnModel();
		TableColumn tc1 = tcm1.getColumn(1);
		tc1.setHeaderValue("Type");
		th1.repaint();
		
		JTableHeader th2 = table_1.getTableHeader();
		TableColumnModel tcm2 = th2.getColumnModel();
		TableColumn tc2 = tcm2.getColumn(2);
		tc2.setHeaderValue("Brand");
		th2.repaint();
		
		JTableHeader th3 = table_1.getTableHeader();
		TableColumnModel tcm3 = th3.getColumnModel();
		TableColumn tc3 = tcm3.getColumn(3);
		tc3.setHeaderValue("Quantity");
		th3.repaint();
		
	}
	
	
	
	//GUI Display 
	public AdminMain(String user) {
		
		Connect(); // Connect to Database
	
		setTitle("Construction Inventory System"); // Title Frame
		setResizable(false); // will restrict resizing frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 691);
		
        ImageIcon imgIcon = new ImageIcon("img\\frameicon.png"); // Image for Frameicon
        Image frameicon = imgIcon.getImage();
        setIconImage(frameicon);
        
        
		contentPane = new JPanel();
		contentPane.setForeground(new Color(31, 37, 47));
		contentPane.setBackground(new Color(242, 169, 31));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListArchive = new JLabel("No data yet!");
		lblListArchive.setHorizontalAlignment(SwingConstants.CENTER);
		lblListArchive.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblListArchive.setBounds(545, 275, 356, 41);
		contentPane.add(lblListArchive);
		lblListArchive.setVisible(false);
		
		JLabel lblListInventory = new JLabel("No data yet!");
		lblListInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblListInventory.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblListInventory.setBounds(123, 271, 349, 45);
		contentPane.add(lblListInventory);
		lblListInventory.setVisible(false);
		
		
		
		JPanel slatebluepanel = new JPanel();
		slatebluepanel.setBounds(0, 0, 93, 652);
		slatebluepanel.setBorder(null);
		contentPane.add(slatebluepanel);
		slatebluepanel.setBackground(new Color(31, 37, 47));
		slatebluepanel.setLayout(null);
		
		JPanel yellowpanel = new JPanel();
		yellowpanel.setBounds(91, -11, 843, 81);
		yellowpanel.setBackground(new Color(18, 30, 40));
		contentPane.add(yellowpanel);
		yellowpanel.setLayout(null);
		
		JLabel welcomelabel = new JLabel("Welcome, " + user.toUpperCase() + "!"); //Username Welcome Message 
		welcomelabel.setForeground(new Color(242, 169, 31));
		welcomelabel.setFont(new Font("Gill Sans MT", Font.BOLD, 28));
		welcomelabel.setBounds(23, 11, 444, 70);
		yellowpanel.add(welcomelabel);
		
		JLabel gradientbgorange = new JLabel("");
		gradientbgorange.setIcon(new ImageIcon("C:\\Users\\Mark\\eclipse-workspace\\finalProjectOOP Local\\finalProjectOOP Local\\finalProjectOOP\\img\\gradientbgorange.png"));
		gradientbgorange.setBounds(150, 11, 693, 70);
		yellowpanel.add(gradientbgorange);
		
		JButton logoutbutton = new JButton(""); //Logout button
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", 
						"Logging Out Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no option
				
				if(result == JOptionPane.YES_OPTION) {
					AdminLogin Algn = new AdminLogin(); // Login object
					Algn.setLocationRelativeTo(null); 
					Algn.setVisible(true); //Shows login page
					dispose(); //Close current page
				}
				else if (result == JOptionPane.NO_OPTION) { //Close message
					
				}
			}
		});
		
		
		
		logoutbutton.setBorderPainted(false);
		logoutbutton.setContentAreaFilled(false);
		logoutbutton.setIcon(new ImageIcon("img\\logout.png"));
		logoutbutton.setBounds(24, 183, 40, 40);
		slatebluepanel.add(logoutbutton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(242, 169, 31));
		separator.setBackground(new Color(242, 169, 31));
		separator.setBounds(10, 68, 73, 2);
		slatebluepanel.add(separator);
		
		JButton logoicon = new JButton("");
		logoicon.setFocusPainted(false);
		logoicon.setBorderPainted(false);
		logoicon.setContentAreaFilled(false);
		logoicon.setIcon(new ImageIcon("img\\logo.png")); // Logo icon
		logoicon.setBounds(0, 0, 93, 70);
		slatebluepanel.add(logoicon);
		
		
		
		JButton ManageUserIcon = new JButton("");
		ManageUserIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageUser mu = new ManageUser(user); //Object of Archive file
				mu.setLocationRelativeTo(null); //Center of page
				mu.setVisible(true); // Shows archive page
				setVisible(false); // Hides Current page
			
			}
		});
		
		ManageUserIcon.setContentAreaFilled(false);
		ManageUserIcon.setBorderPainted(false);
		ManageUserIcon.setIcon(new ImageIcon("img\\manageemployee.png")); // Archive icon
		ManageUserIcon.setSize(new Dimension(40, 40));
		ManageUserIcon.setMinimumSize(new Dimension(40, 40));
		ManageUserIcon.setMaximumSize(new Dimension(40, 40));
		ManageUserIcon.setPreferredSize(new Dimension(40, 40));
		ManageUserIcon.setBounds(24, 107, 40, 40);
		slatebluepanel.add(ManageUserIcon);
		
		JLabel gradientbg = new JLabel("");
		gradientbg.setIcon(new ImageIcon("img\\gradientbg.png"));
		gradientbg.setBounds(0, 0, 93, 652);
		slatebluepanel.add(gradientbg);
		
		
		JLabel lblNewLabel = new JLabel("TYPE"); 
		lblNewLabel.setBounds(123, 479, 109, 24);
		lblNewLabel.setForeground(new Color(31, 37, 47));
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		txtType = new JTextField();
		txtType.setBounds(123, 504, 109, 30);
		txtType.setText("Type");
		txtType.setForeground(Color.GRAY);
		txtType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) { //Clear the placeholder text when there is a input
				if (txtType.getText().equals("Type")) {  
					txtType.setText("");
					txtType.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) { //Set the placeholder text ("Type") when type field is empty
				if (txtType.getText().isEmpty()) {
					txtType.setText("Type");
					txtType.setForeground(Color.GRAY);
				}
			}
		});
		
		
		
		
		
		txtType.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		txtType.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(txtType);
		txtType.setColumns(10);
		
		JLabel brandlabel = new JLabel("BRAND");
		brandlabel.setBounds(242, 479, 110, 24);
		brandlabel.setForeground(new Color(31, 37, 47));
		brandlabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		contentPane.add(brandlabel);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(243, 504, 109, 30);
		txtBrand.setText("Brand");
		txtBrand.setForeground(Color.GRAY);
		txtBrand.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) { //Clear the placeholder text when there is a input
				if (txtBrand.getText().equals("Brand")) {
					txtBrand.setText("");
					txtBrand.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) { //Set the placeholder text ("Brand") when brand field is empty
				if (txtBrand.getText().isEmpty()) {
					txtBrand.setText("Brand");
					txtBrand.setForeground(Color.GRAY);
				}
			}
		});
		
		txtBrand.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBrand.setColumns(10);
		txtBrand.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		contentPane.add(txtBrand);
		
		JLabel qtylabel = new JLabel("QTY");
		qtylabel.setBounds(362, 479, 110, 24);
		qtylabel.setForeground(new Color(31, 37, 47));
		qtylabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		contentPane.add(qtylabel);
		
		txtQty = new JTextField();
		txtQty.setBounds(362, 504, 110, 30);
		txtQty.setText("Quantity");
		txtQty.setForeground(Color.GRAY);
		txtQty.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) { //Clear the placeholder text when there is a input
				if (txtQty.getText().equals("Quantity")) {
					txtQty.setText("");
					txtQty.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) { //Set the placeholder text ("Quantity") when Quantity field is empty
				if (txtQty.getText().isEmpty()) {
					txtQty.setText("Quantity");
					txtQty.setForeground(Color.GRAY);
				}
			}
		});
		
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) { //To avoid typing any letters
				char c = e.getKeyChar(); 
				
				if(!Character.isDigit(c)) {
					e.consume();
				}
				
				String qty = txtQty.getText();
				if (qty.equals("0") || qty.equals("00") || qty.equals("000")) { //Restricting 0 from having multiple digit alone
					txtQty.setText("0");
					e.consume();
				}
				
				String newQty = qty + c;  
				try { //To ensure that 2000 is the max quantity
					int quantity = Integer.parseInt(newQty);
					if(quantity == 0) {			
						if(qty.length() > 1) {
							e.consume();
						}
					}
					else if (quantity < 1 || quantity > 2000) { 
						txtQty.setText("2000");
						e.consume();
					}
				}
				catch (NumberFormatException ex){
					e.consume();
				}
			}
		});
		
		txtQty.setFont(new Font("Arial", Font.PLAIN, 15));
		txtQty.setColumns(10);
		txtQty.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		contentPane.add(txtQty);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(355, 152, 129, 24);
		txtSearch.addKeyListener(new KeyAdapter() { // Activate search in jtable (function will operate after key released)
			@Override
			public void keyReleased(KeyEvent e) {
				
				String type;
				
				type = txtSearch.getText();
				
				
				try { // SQL code for search textfield  (Finding data in table and mirror in jtable)
					pst = con.prepareStatement("SELECT * from inventory WHERE (type = ? OR brand = ?) OR (CONCAT(type, ' ', brand)=?) OR (CONCAT(brand, ' ', type)=?)");
					pst.setString(1, type);
					pst.setString(2, type);
					pst.setString(3, type);
					pst.setString(4, type);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTableHeader th = table.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc = tcm.getColumn(0);
					tc.setHeaderValue("ID Number");
					th.repaint();
					
					JTableHeader th1 = table.getTableHeader();
					TableColumnModel tcm1 = th1.getColumnModel();
					TableColumn tc1 = tcm1.getColumn(1);
					tc1.setHeaderValue("Type");
					th1.repaint();
					
					JTableHeader th2 = table.getTableHeader();
					TableColumnModel tcm2 = th2.getColumnModel();
					TableColumn tc2 = tcm2.getColumn(2);
					tc2.setHeaderValue("Brand");
					th2.repaint();
					
					JTableHeader th3 = table.getTableHeader();
					TableColumnModel tcm3 = th3.getColumnModel();
					TableColumn tc3 = tcm3.getColumn(3);
					tc3.setHeaderValue("Quantity");
					th3.repaint();
					
					if(type.equals("")) { // Blank search bar will just load table as is
						table_load();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		textSearchArchive = new JTextField();
		textSearchArchive.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String type;
				
				type = textSearchArchive.getText();
				
				
				try { // SQL code for search textfield  (Finding data in table and mirror in jtable)
					pst = con.prepareStatement("SELECT * from archive WHERE (type = ? OR brand = ?) OR (CONCAT(type, ' ', brand)=?) OR (CONCAT(brand, ' ', type)=?)");
					pst.setString(1, type);
					pst.setString(2, type);
					pst.setString(3, type);
					pst.setString(4, type);
					rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
					JTableHeader th = table_1.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc = tcm.getColumn(0);
					tc.setHeaderValue("ID Number");
					th.repaint();
					
					JTableHeader th1 = table_1.getTableHeader();
					TableColumnModel tcm1 = th1.getColumnModel();
					TableColumn tc1 = tcm1.getColumn(1);
					tc1.setHeaderValue("Type");
					th1.repaint();
					
					JTableHeader th2 = table_1.getTableHeader();
					TableColumnModel tcm2 = th2.getColumnModel();
					TableColumn tc2 = tcm2.getColumn(2);
					tc2.setHeaderValue("Brand");
					th2.repaint();
					
					JTableHeader th3 = table_1.getTableHeader();
					TableColumnModel tcm3 = th3.getColumnModel();
					TableColumn tc3 = tcm3.getColumn(3);
					tc3.setHeaderValue("Quantity");
					th3.repaint();
					
					if(type.equals("")) { // Blank search bar will just load table as is
						table_loadArchive();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
			}
		});
		textSearchArchive.setFont(new Font("Arial", Font.PLAIN, 15));
		textSearchArchive.setColumns(10);
		textSearchArchive.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		textSearchArchive.setBounds(781, 151, 129, 24);
		contentPane.add(textSearchArchive);
		
		
		
		
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search")) { //Clear the placeholder text when there is a input
					txtSearch.setText("");
					txtSearch.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().isEmpty()) { //Set the placeholder text ("Quantity") when Quantity field is empty
					txtSearch.setText("Search");
					txtSearch.setForeground(Color.GRAY);
				}
			}
		});
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		contentPane.add(txtSearch);
		
		JButton addbutton = new JButton("ADD");
		addbutton.setBounds(123, 581, 109, 23);
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Function of Add
				
				String type, brand, qty;
				
				type = txtType.getText();
				brand = txtBrand.getText();
				qty = txtQty.getText();		
				
				if(!type.equals("") && !brand.equals("") && !qty.equals("")) {
					if (!type.equals("Type") && !brand.equals("Brand") && !qty.equals("Quantity")) {
						if(!qty.equals("0")) { //Checking for none inputs
							
				int result = JOptionPane.showConfirmDialog(null, "Are the data you entered are correct?", 
						"Confirmation",JOptionPane.YES_NO_OPTION); //Yes or no message box for confirmation
				if(result == JOptionPane.YES_OPTION) {
					try { // SQL code for data insertion 
						pst = con.prepareStatement("INSERT INTO inventory(type, brand, qty) VALUES(?, ?, ?)");
						pst.setString(1, type);
						pst.setString(2, brand);
						pst.setString(3, qty);
						pst.executeUpdate();
						table_load(); //Reload table
						if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true); //Show of no data label text 
						}
						else {
							lblListInventory.setVisible(false); 
						}
						
						JOptionPane.showMessageDialog(null, "The record has been added on the list!" , "Added Successfully", JOptionPane.INFORMATION_MESSAGE);
						txtType.setText(null);
						txtBrand.setText(null);
						txtQty.setText(null);
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if (result == JOptionPane.NO_OPTION) { 
					
				}
				}//Restriction of 0 in qty input and blank inputs
						else {
							JOptionPane.showMessageDialog(null, "Please don't insert 0 on the quantity!" , "Added Failed", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please complete filling up first the Type, Brand, and Quantity!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please complete filling up first the Type, Brand, and Quantity!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		addbutton.setIcon(new ImageIcon("img\\addsmallicon.png")); // add icon
		addbutton.setFocusPainted(false);
		addbutton.setBorder(new LineBorder(new Color(0, 0, 0)));
		addbutton.setForeground(new Color(242, 169, 31));
		addbutton.setBackground(new Color(31, 37, 47));
		addbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(addbutton);
		
		JButton editbutton = new JButton("EDIT");
		editbutton.setBounds(242, 581, 110, 23);
		editbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String type, brand, qty, id;
				type = txtType.getText();
				brand = txtBrand.getText();
				qty = txtQty.getText();
				id = txtID.getText();
				//Restriction of blank input
				if(!type.equals("") && !brand.equals("") && !qty.equals("") && !id.equals("") && !type.equals("Type") && !brand.equals("Brand") && !qty.equals("Quantity")) {
					
						
				int result = JOptionPane.showConfirmDialog(null, "Are these changes on this data are correct?", 
						"Confirmation",JOptionPane.YES_NO_OPTION); //yes or no confirmation 
				
				if (result == JOptionPane.YES_OPTION) {
					try { //Update selected data 
						pst = con.prepareStatement("UPDATE inventory SET type = ?, brand = ?, qty = ? WHERE id = ?");
						pst.setString(1, type);
						pst.setString(2, brand);
						pst.setString(3, qty);
						pst.setString(4, id);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "The record has been updated!" , "Success", JOptionPane.INFORMATION_MESSAGE);
						
						int updateQty = Integer.parseInt(qty);
						if(updateQty == 0) { // Check if qty is 0 and will insert data in archive section
							 pst = con.prepareStatement("INSERT INTO archive (id, type, brand, qty) VALUES (?, ?, ?, ?)");
		                        pst.setString(1, id);
		                        pst.setString(2, type);
		                        pst.setString(3, brand);
		                        pst.setString(4, qty);
		                        pst.executeUpdate();
		                        
		                        pst = con.prepareStatement("DELETE FROM inventory where id = ?"); // delete the data that in the inventory 
					            pst.setString(1, id);
					            pst.executeUpdate();
					            JOptionPane.showMessageDialog(null, "The record has been archived!" , "Archiving Successful", JOptionPane.INFORMATION_MESSAGE);
						}
						table_load();
						table_loadArchive();
						
						if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true);
						}
						else {
							lblListInventory.setVisible(false);
						}
						
						if(table_1.getRowCount() == 0) {
							lblListArchive.setVisible(true);
						}
						else {
							lblListArchive.setVisible(false);
						}
						
						txtType.setText(null);
						txtBrand.setText(null);
						txtQty.setText(null);
						
						txtID.setText(null);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if (result == JOptionPane.NO_OPTION) {
					
				}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please complete filling up everything first!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
				}	
				
			}
		});
		editbutton.setIcon(new ImageIcon("img\\editmallicon.png"));
		editbutton.setForeground(new Color(242, 169, 31));
		editbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editbutton.setFocusPainted(false);
		editbutton.setBorder(new LineBorder(new Color(0, 0, 0)));
		editbutton.setBackground(new Color(31, 37, 47));
		contentPane.add(editbutton);
		
		
		
		JButton archivebutton = new JButton("ARCHIVE"); 
		archivebutton.setBounds(362, 581, 110, 23);
		archivebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			    
				 int[] selectedRows = table.getSelectedRows();
				
				String type, brand, qty, id, thetype, thebrand, theqty, theid;
				type = txtType.getText();
				brand = txtBrand.getText();
				qty = txtQty.getText();
				id = txtID.getText();
				
				thetype = txtTypeMirror.getText();
				thebrand = txtBrandMirror.getText();
				theqty = txtQtyMirror.getText();
				theid = txtIDMirror.getText();
				
				if(!type.equals("") && !brand.equals("") && !qty.equals("") && !id.equals("")) {
					if(!type.equals("Type") && !brand.equals("Brand") && !qty.equals("Quantity")) { // Restriction of no inputs
						
					
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to archive this data?", 
						"Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no
				
				if(result == JOptionPane.YES_OPTION) {
					try {
			            pst = con.prepareStatement("INSERT INTO archive(id, type , brand, qty) VALUES(?, ?, ?, ?)");
			            pst.setString(1, theid);
			            pst.setString(2, thetype);
						pst.setString(3, thebrand);
						pst.setString(4, theqty);
						// Insert the selected data into archive section

			            pst.executeUpdate();
			            
			            pst = con.prepareStatement("DELETE FROM inventory where id = ?"); // delete the data that was selected in inventory section
			            pst.setString(1, theid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "The record has been archived!" , "Archiving Successful", JOptionPane.INFORMATION_MESSAGE);
			            table_load();
			            table_loadArchive();
			            
			           if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true);
						}
						else {
							lblListInventory.setVisible(false);
						}
			           
			           if(table_1.getRowCount() == 0) {
							lblListArchive.setVisible(true);
						}
						else {
							lblListArchive.setVisible(false);
						}
			            
			            txtType.setText(null);
						txtBrand.setText(null);
						txtQty.setText(null);
						txtID.setText(null);
						
						txtTypeMirror.setText(null);
						txtBrandMirror.setText(null);
						txtQtyMirror.setText(null);
						txtIDMirror.setText(null);
			         

			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
				}
				
				else if (result == JOptionPane.NO_OPTION) {
					
				}
				}
					else {
						JOptionPane.showMessageDialog(null, "Please select a data first through typing the ID number or the ID number is not on the list!!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				else if (selectedRows.length != 0) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to archive the selected data in the table?", 
							"Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no
				if(result == JOptionPane.YES_OPTION) {
				 try {
				      
				        for (int i = selectedRows.length - 1; i >= 0; i--) {
				            int selectedRow = selectedRows[i];
				            int idToDelete = (int) tblModel.getValueAt(selectedRow, 0); 
				            PreparedStatement pstArchive = con.prepareStatement("INSERT INTO archive(id, type, brand, qty) SELECT id, type, brand, qty FROM inventory WHERE id = ?");
				            pstArchive.setInt(1, idToDelete);
				            pstArchive.executeUpdate();
				            
				            
				            pst = con.prepareStatement("DELETE FROM inventory WHERE id = ?");
				            pst.setInt(1, idToDelete);
				            
				            int rowsDeleted = pst.executeUpdate();
				            table_load();
				            table_loadArchive();
				            
				            if (rowsDeleted > 0) {
				                tblModel.removeRow(selectedRow); 
				            } else {
				                JOptionPane.showMessageDialog(null, "Failed to archive" + idToDelete);
				            }
				        }
				        
				        
				        
				        if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true); //Show of no data label text 
						}
						else {
							lblListInventory.setVisible(false); 
						}
			            
			            if(table_1.getRowCount() == 0) {
							lblListArchive.setVisible(true);
						}
						else {
							lblListArchive.setVisible(false);
						}
			            
			            JOptionPane.showMessageDialog(null, "Selected data row has been moved to archive.");
				        
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				    }
				}
					else if (result == JOptionPane.NO_OPTION) {
					
				}	
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Please select a data first through typing the ID number or the ID number is not on the list!!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		archivebutton.setIcon(new ImageIcon("img\\archivesmallicon.png"));
		archivebutton.setForeground(new Color(242, 169, 31));
		archivebutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		archivebutton.setFocusPainted(false);
		archivebutton.setBorder(new LineBorder(new Color(0, 0, 0)));
		archivebutton.setBackground(new Color(31, 37, 47));
		contentPane.add(archivebutton);
		
		JButton clearbutton = new JButton("CLEAR");
		clearbutton.setBounds(123, 615, 109, 23);
		clearbutton.setIcon(new ImageIcon("img\\clearsmallicon.png"));
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtType.setText(null);
				txtBrand.setText(null);
				txtQty.setText(null);
				txtSearch.setText(null);
				txtID.setText(null);
				table_load();
				editbutton.setEnabled(true);
				archivebutton.setEnabled(true);
				addbutton.setEnabled(true);
				txtType.setForeground(Color.BLACK);
				txtBrand.setForeground(Color.BLACK);
				txtQty.setForeground(Color.BLACK);
				txtSearch.setForeground(Color.BLACK);
			}
		});
		clearbutton.setForeground(new Color(242, 169, 31));
		clearbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		clearbutton.setFocusPainted(false);
		clearbutton.setBorder(new LineBorder(new Color(0, 0, 0)));
		clearbutton.setBackground(new Color(31, 37, 47));
		contentPane.add(clearbutton);
		
		txtID = new JTextField();
		txtID.setBounds(203, 430, 55, 24);
		txtID.addKeyListener(new KeyAdapter() { // ID FIELD key released
	
			@Override
			public void keyTyped(KeyEvent e) {// Only numerical inputs
				char c = e.getKeyChar(); 
				
				if(!Character.isDigit(c)) {
					e.consume();
				}
				String id = txtID.getText();
				if(id.equals("0") || id.equals("00") || id.equals("000") || id.equals("0000") || id.equals("00000")) { //if any 0 digit, will conver to 1
					txtID.setText("1");
					e.consume();
				}
				String newID = id + c;
				
				try {
					int idn = Integer.parseInt(newID);
					if(idn == 0) {
						e.consume();
					}
					else if(idn < 1 || idn > 500000) {
						txtID.setText("500000");
						e.consume();
					}
				}
				catch (NumberFormatException ex) {
					e.consume();
				}
			}
		});
		txtID.setFont(new Font("Arial", Font.PLAIN, 15));
		txtID.setColumns(10);
		txtID.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		contentPane.add(txtID);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(113, 492, 62, 24);
		lblId.setForeground(new Color(242, 169, 31));
		lblId.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		contentPane.add(lblId);
		
		JButton findbutton = new JButton("FIND");
		findbutton.setBounds(124, 430, 69, 24);
		findbutton.setIcon(new ImageIcon("img\\findsmallicon.png"));
		findbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String id = txtID.getText();
					if (!id.equals("")) {
						pst = con.prepareStatement("SELECT type, brand, qty FROM inventory WHERE id = ?"); // SQL code for showing the data in ID field 
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
						
						if(rs.next() == true) {
							String type = rs.getString(1);
							String brand = rs.getString(2);
							String qty = rs.getString(3);
							String thetype = rs.getString(1);
							String thebrand = rs.getString(2);
							String theqty = rs.getString(3);
							
							txtType.setText(type);
							txtBrand.setText(brand);
							txtQty.setText(qty);
							
							txtTypeMirror.setText(thetype);
							txtBrandMirror.setText(thebrand);
							txtQtyMirror.setText(theqty);
							txtIDMirror.setText(id);
							
							editbutton.setEnabled(true);
							archivebutton.setEnabled(true);
							addbutton.setEnabled(true);
							txtType.setForeground(Color.BLACK);
							txtBrand.setForeground(Color.BLACK);
							txtQty.setForeground(Color.BLACK);
					
						}
						
						else { //Show blank in no id was found 
		
							txtType.setText("");
							txtBrand.setText("");
							txtQty.setText("");
							
							txtTypeMirror.setText("");
							txtBrandMirror.setText("");
							txtQtyMirror.setText("");
							
							// disable buttons 
							editbutton.setEnabled(false); 
							archivebutton.setEnabled(false);
							addbutton.setEnabled(false);
						}
					}
					
					else {
						txtType.setText("");
						txtBrand.setText("");
						txtQty.setText("");
						
						txtTypeMirror.setText("");
						txtBrandMirror.setText("");
						txtQtyMirror.setText("");
						
						editbutton.setEnabled(true);
						archivebutton.setEnabled(true);
						addbutton.setEnabled(true);
						txtType.setForeground(Color.BLACK);
						txtBrand.setForeground(Color.BLACK);
						txtQty.setForeground(Color.BLACK);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
			}
		});
		
		findbutton.setForeground(new Color(242, 169, 31));
		findbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		findbutton.setFocusPainted(false);
		findbutton.setBorder(new LineBorder(new Color(0, 0, 0)));
		findbutton.setBackground(new Color(31, 37, 47));
		contentPane.add(findbutton);
		
		txtTypeMirror = new JTextField();
		txtTypeMirror.setBounds(385, 127, 15, 19);
		contentPane.add(txtTypeMirror);
		txtTypeMirror.setColumns(10);
		
		txtQtyMirror = new JTextField();
		txtQtyMirror.setBounds(430, 127, 15, 19);
		contentPane.add(txtQtyMirror);
		txtQtyMirror.setColumns(10);
		
		txtBrandMirror = new JTextField();
		txtBrandMirror.setBounds(412, 127, 9, 19);
		txtBrandMirror.setColumns(10);
		contentPane.add(txtBrandMirror);
		
		txtIDMirror = new JTextField();
		txtIDMirror.setBounds(459, 127, 9, 19);
		txtIDMirror.setColumns(10);
		contentPane.add(txtIDMirror);
		
		JLabel lblInventory = new JLabel("INVENTORY");
		lblInventory.setForeground(new Color(31, 37, 47));
		lblInventory.setFont(new Font("Cambria Math", Font.BOLD, 20));
		lblInventory.setBounds(122, 102, 129, 24);
		contentPane.add(lblInventory);
		
		JLabel lblArchive = new JLabel("ARCHIVE");
		lblArchive.setForeground(new Color(31, 37, 47));
		lblArchive.setFont(new Font("Cambria Math", Font.BOLD, 20));
		lblArchive.setBounds(546, 102, 129, 24);
		contentPane.add(lblArchive);
		
		txtTypeMirrorArch = new JTextField();
		txtTypeMirrorArch.setBounds(851, 125, 15, 20);
		contentPane.add(txtTypeMirrorArch);
		txtTypeMirrorArch.setColumns(10);
		
		txtBrandMirrorArch = new JTextField();
		txtBrandMirrorArch.setColumns(10);
		txtBrandMirrorArch.setBounds(872, 125, 15, 20);
		contentPane.add(txtBrandMirrorArch);
		
		txtQtyMirrorArch = new JTextField();
		txtQtyMirrorArch.setColumns(10);
		txtQtyMirrorArch.setBounds(895, 125, 15, 20);
		contentPane.add(txtQtyMirrorArch);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(1133, 399, 15, 20);
		contentPane.add(textField_4);
		
		txtIDArch = new JTextField();
		txtIDArch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = txtIDArch.getText();
					if (!id.equals("")) {
						pst = con.prepareStatement("SELECT type, brand, qty FROM archive WHERE id = ?"); // SQL code for showing the data in ID field 
						pst.setString(1, id);
						ResultSet rs = pst.executeQuery();
						
						if(rs.next() == true) {
							
							String thetype = rs.getString(1);
							String thebrand = rs.getString(2);
							String theqty = rs.getString(3);
							
						
							txtTypeMirrorArch.setText(thetype);
							txtBrandMirrorArch.setText(thebrand);
							txtQtyMirrorArch.setText(theqty);
					
						
						
					
						}
						
						else { //Show blank in no id was found 
		
						
							
							txtTypeMirrorArch.setText("");
							txtBrandMirrorArch.setText("");
							txtQtyMirrorArch.setText("");
							
							// disable buttons 
							//editbutton.setEnabled(false); 
							//archivebutton.setEnabled(false);
							//addbutton.setEnabled(false);
						}
					}
					
					else {
					
						txtTypeMirror.setText("");
						txtBrandMirror.setText("");
						txtQtyMirror.setText("");
						
						editbutton.setEnabled(true);
						archivebutton.setEnabled(true);
						addbutton.setEnabled(true);
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		txtIDArch.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIDArch.setColumns(10);
		txtIDArch.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		txtIDArch.setBounds(658, 430, 55, 25);
		contentPane.add(txtIDArch);
		
		JLabel archiveidlabel = new JLabel("ID");
		archiveidlabel.setForeground(new Color(31, 37, 47));
		archiveidlabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		archiveidlabel.setBounds(717, 432, 38, 23);
		contentPane.add(archiveidlabel);
		
		JButton btnUnarchive = new JButton("UNARCHIVE");
		btnUnarchive.setIcon(new ImageIcon("img\\unarchivesmallicon.png"));
		btnUnarchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tblModel = (DefaultTableModel) table_1.getModel();
			    
				 int[] selectedRows = table_1.getSelectedRows();
					
				String id, type, brand, qty;
				id = txtIDArch.getText();
				type = txtTypeMirrorArch.getText();
				brand = txtBrandMirrorArch.getText();
				qty = txtQtyMirrorArch.getText();
		
				if(!type.equals("") && !brand.equals("") && !qty.equals("") && !id.equals("")) { // Restriction of no inputs
		
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to unarchive this data and return it on the active list?", 
						"Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no
				
				if(result == JOptionPane.YES_OPTION) {
					try {
			            pst = con.prepareStatement("INSERT INTO inventory(id, type, brand, qty) VALUES(?, ?, ?, ?)");
			            pst.setString(1, id);
			            pst.setString(2, type);
						pst.setString(3, brand);
						pst.setString(4, qty);
						// Insert the selected data into inventory section

			            pst.executeUpdate();
			            
			            pst = con.prepareStatement("DELETE FROM archive where id = ?");
			            pst.setString(1, id);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "The record has been placed back!", "Unarchiving Successful", JOptionPane.INFORMATION_MESSAGE);
			            table_loadArchive();
			            table_load();
			            
			            if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true); //Show of no data label text 
						}
						else {
							lblListInventory.setVisible(false); 
						}
			            
			            if(table_1.getRowCount() == 0) {
							lblListArchive.setVisible(true);
						}
						else {
							lblListArchive.setVisible(false);
						}
			            
			           
			            
			            txtTypeMirrorArch.setText(null);
			            txtBrandMirrorArch.setText(null);
			            txtQtyMirrorArch.setText(null);
			            txtIDArch.setText(null);
			         

			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
				}
				
				else if (result == JOptionPane.NO_OPTION) {
					
				}		
				}
				
				else if (selectedRows.length != 0) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to unarchive the selected data in the table?", 
							"Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no
				if(result == JOptionPane.YES_OPTION) {
				 try {
				      
				        for (int i = selectedRows.length - 1; i >= 0; i--) {
				            int selectedRow = selectedRows[i];
				            int idToDelete = (int) tblModel.getValueAt(selectedRow, 0); 
				            PreparedStatement pstArchive = con.prepareStatement("INSERT INTO inventory(id, type, brand, qty) SELECT id, type, brand, qty FROM archive WHERE id = ?");
				            pstArchive.setInt(1, idToDelete);
				            pstArchive.executeUpdate();
				            
				            
				            pst = con.prepareStatement("DELETE FROM archive WHERE id = ?");
				            pst.setInt(1, idToDelete);
				            
				            int rowsDeleted = pst.executeUpdate();
				            table_load();
				            table_loadArchive();
				            
				            if (rowsDeleted > 0) {
				                tblModel.removeRow(selectedRow); 
				            } else {
				                JOptionPane.showMessageDialog(null, "Failed to unarchive" + idToDelete);
				            }
				        }
				        
				        
				        
				        if(table.getRowCount() == 0) {
							lblListInventory.setVisible(true); //Show of no data label text 
						}
						else {
							lblListInventory.setVisible(false); 
						}
			            
			            if(table_1.getRowCount() == 0) {
							lblListArchive.setVisible(true);
						}
						else {
							lblListArchive.setVisible(false);
						}
			            
			            JOptionPane.showMessageDialog(null, "Selected data row has been moved to active list.");
				        
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				    }
				}
					else if (result == JOptionPane.NO_OPTION) {
					
				}	
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Please select a data first through typing the ID number or the ID number is not on the list!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		btnUnarchive.setForeground(new Color(242, 169, 31));
		btnUnarchive.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUnarchive.setFocusPainted(false);
		btnUnarchive.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnUnarchive.setBackground(new Color(31, 37, 47));
		btnUnarchive.setBounds(550, 430, 99, 25);
		contentPane.add(btnUnarchive);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		scrollPane.setBounds(114, 187, 370, 233);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(31, 37, 47), 2));
		scrollPane_1.setBounds(540, 187, 370, 233);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(new Color(31, 37, 47));
		separator1.setBackground(new Color(31, 37, 47));
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(513, 102, 9, 536);
		contentPane.add(separator1);
		
		JLabel findidlabel = new JLabel("ID");
		findidlabel.setForeground(new Color(31, 37, 47));
		findidlabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		findidlabel.setBounds(264, 431, 38, 24);
		contentPane.add(findidlabel);
		
		JLabel lblActions = new JLabel("ACTIONS");
		lblActions.setForeground(new Color(31, 37, 47));
		lblActions.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblActions.setBounds(123, 550, 109, 30);
		contentPane.add(lblActions);
		

		
		txtTypeMirror.setVisible(false);
		txtBrandMirror.setVisible(false);
		txtQtyMirror.setVisible(false);
		txtIDMirror.setVisible(false);
		
		txtTypeMirrorArch.setVisible(false);
		txtBrandMirrorArch.setVisible(false);
		txtQtyMirrorArch.setVisible(false);
		
		table_load();
		table_loadArchive();
		//reload
		if(table.getRowCount() == 0) {
			lblListInventory.setVisible(true);
		}
		else {
			lblListInventory.setVisible(false);
		}
		
		if(table_1.getRowCount() == 0) {
			lblListArchive.setVisible(true);
		}
		else {
			lblListArchive.setVisible(false);
		}
	}
}
