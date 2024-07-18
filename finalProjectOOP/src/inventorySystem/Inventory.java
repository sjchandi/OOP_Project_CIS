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
import java.text.MessageFormat;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;





public class Inventory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtType;
	private JTextField txtBrand;
	private JTextField txtQty;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory("Default User");
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
	
	
	//Mirror of table in database to jtable
	public void table_load() {
		try {
			pst =  con.prepareStatement("SELECT * from inventory");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Colummn names
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
	
	
	
	//GUI Display 
	public Inventory(String username) {
		
		Connect(); // Connect to Database
	
		setTitle("Construction Inventory"); // Title Frame
		setResizable(false); // will restrict resizing frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 602);
		
        ImageIcon imgIcon = new ImageIcon("img\\frameicon.png"); // Image for Frameicon
        Image frameicon = imgIcon.getImage();
        setIconImage(frameicon);
        
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(43, 53, 64));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblList = new JLabel("No data yet!"); //Detects if there is no data in the table
		lblList.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblList.setBounds(268, 357, 199, 43);
		contentPane.add(lblList);
		lblList.setVisible(false);
		
		JPanel slatebluepanel = new JPanel();
		slatebluepanel.setBorder(null);
		slatebluepanel.setBounds(0, 0, 93, 563);
		contentPane.add(slatebluepanel);
		slatebluepanel.setBackground(new Color(31, 37, 47));
		slatebluepanel.setLayout(null);
		
		JPanel yellowpanel = new JPanel();
		yellowpanel.setBackground(new Color(242, 169, 31));
		yellowpanel.setBounds(91, -11, 747, 81);
		contentPane.add(yellowpanel);
		yellowpanel.setLayout(null);
		
		JLabel welcomelabel = new JLabel("Welcome, " + username.toUpperCase() + "!"); //Username Welcome Message 
		welcomelabel.setForeground(new Color(43, 53, 64));
		welcomelabel.setFont(new Font("Gill Sans MT", Font.BOLD, 28));
		welcomelabel.setBounds(23, 11, 444, 70);
		yellowpanel.add(welcomelabel);
		
		JLabel gradientbg = new JLabel("");
		gradientbg.setIcon(new ImageIcon("img\\gradientbg.png")); //Background
		gradientbg.setBounds(0, -11, 93, 574);
		slatebluepanel.add(gradientbg);
		
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
		
		
		
		JButton archiveiconbutton = new JButton("");
		archiveiconbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Archive as = new Archive(username); //Object of Archive file
				as.setLocationRelativeTo(null); //Center of page
				as.setVisible(true); // Shows archive page
				setVisible(false); // Hides Current page
			}
		});
		
		archiveiconbutton.setContentAreaFilled(false);
		archiveiconbutton.setBorderPainted(false);
		archiveiconbutton.setIcon(new ImageIcon("img\\archiveicon.png")); // Archive icon
		archiveiconbutton.setSize(new Dimension(40, 40));
		archiveiconbutton.setMinimumSize(new Dimension(40, 40));
		archiveiconbutton.setMaximumSize(new Dimension(40, 40));
		archiveiconbutton.setPreferredSize(new Dimension(40, 40));
		archiveiconbutton.setBounds(24, 107, 40, 40);
		slatebluepanel.add(archiveiconbutton);
		
		JButton logoutbutton = new JButton(""); //Logout button
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", 
						"Logging Out Confirmation",JOptionPane.YES_NO_OPTION); //Confirmation with yes or no option
				
				if(result == JOptionPane.YES_OPTION) {
					Login lgn = new Login(); // Login object
					lgn.setLocationRelativeTo(null); 
					lgn.setVisible(true); //Shows login page
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
		
		
		JLabel lblNewLabel = new JLabel("TYPE"); 
		lblNewLabel.setForeground(new Color(242, 169, 31));
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblNewLabel.setBounds(131, 99, 93, 24);
		contentPane.add(lblNewLabel);
		
		txtType = new JTextField();
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
		
		
		
		
		
		txtType.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtType.setFont(new Font("Arial", Font.PLAIN, 15));
		txtType.setBounds(123, 129, 135, 30);
		contentPane.add(txtType);
		txtType.setColumns(10);
		
		JLabel brandlabel = new JLabel("BRAND");
		brandlabel.setForeground(new Color(242, 169, 31));
		brandlabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		brandlabel.setBounds(302, 99, 93, 24);
		contentPane.add(brandlabel);
		
		txtBrand = new JTextField();
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
		txtBrand.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtBrand.setBounds(294, 129, 135, 30);
		contentPane.add(txtBrand);
		
		JLabel qtylabel = new JLabel("QTY");
		qtylabel.setForeground(new Color(242, 169, 31));
		qtylabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		qtylabel.setBounds(477, 99, 93, 24);
		contentPane.add(qtylabel);
		
		txtQty = new JTextField();
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
		txtQty.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtQty.setBounds(469, 129, 135, 30);
		contentPane.add(txtQty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		scrollPane.setBounds(125, 242, 502, 302);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton searchbutton = new JButton("");
		searchbutton.setContentAreaFilled(false);
		searchbutton.setBorderPainted(false);
		searchbutton.setIcon(new ImageIcon("img\\search.png"));
		searchbutton.setBounds(432, 207, 50, 24);
		contentPane.add(searchbutton);
		
		txtSearch = new JTextField();
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
		txtSearch.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtSearch.setBounds(478, 207, 149, 24);
		contentPane.add(txtSearch);
		
		JButton addbutton = new JButton("ADD");
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
							lblList.setVisible(true); //Show of no data label text 
						}
						else {
							lblList.setVisible(false); 
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
		addbutton.setIcon(new ImageIcon("img\\add.png")); // add icon
		addbutton.setFocusPainted(false);
		addbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		addbutton.setForeground(new Color(31, 37, 47));
		addbutton.setBackground(new Color(242, 169, 31));
		addbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		addbutton.setBounds(675, 255, 105, 23);
		contentPane.add(addbutton);
		
		JButton editbutton = new JButton("EDIT");
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
						
						if(table.getRowCount() == 0) {
							lblList.setVisible(true);
						}
						else {
							lblList.setVisible(false);
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
		editbutton.setIcon(new ImageIcon("img\\edit.png"));
		editbutton.setForeground(new Color(31, 37, 47));
		editbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editbutton.setFocusPainted(false);
		editbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		editbutton.setBackground(new Color(242, 169, 31));
		editbutton.setBounds(675, 411, 105, 23);
		contentPane.add(editbutton);
		
		
		
		JButton archivebutton = new JButton("ARCHIVE"); 
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
			            
			            if(table.getRowCount() == 0) {
							lblList.setVisible(true);
						}
						else {
							lblList.setVisible(false);
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
							"Confirmation",JOptionPane.YES_NO_OPTION);
					
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
						            
						            if (rowsDeleted > 0) {
						                tblModel.removeRow(selectedRow); 
						            } else {
						                JOptionPane.showMessageDialog(null, "Failed to archive" + idToDelete);
						            }
						        }
						        
						        JOptionPane.showMessageDialog(null, "Selected data row has been moved to archive.");
						        
						        if(table.getRowCount() == 0) {
									lblList.setVisible(true);
								}
								else {
									lblList.setVisible(false);
								}
						        
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
		archivebutton.setIcon(new ImageIcon("img\\archive.png"));
		archivebutton.setForeground(new Color(31, 37, 47));
		archivebutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		archivebutton.setFocusPainted(false);
		archivebutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		archivebutton.setBackground(new Color(242, 169, 31));
		archivebutton.setBounds(675, 445, 105, 23);
		contentPane.add(archivebutton);
		
		JButton clearbutton = new JButton("CLEAR");
		clearbutton.setIcon(new ImageIcon("img\\clear.png"));
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
		clearbutton.setForeground(new Color(31, 37, 47));
		clearbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		clearbutton.setFocusPainted(false);
		clearbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		clearbutton.setBackground(new Color(242, 169, 31));
		clearbutton.setBounds(675, 289, 105, 23);
		contentPane.add(clearbutton);
		
		JSeparator seperator1 = new JSeparator();
		seperator1.setForeground(new Color(242, 169, 31));
		seperator1.setBackground(new Color(242, 169, 31));
		seperator1.setBounds(663, 242, 129, 2);
		contentPane.add(seperator1);
		
		JSeparator seperator2 = new JSeparator();
		seperator2.setForeground(new Color(242, 169, 31));
		seperator2.setBackground(new Color(242, 169, 31));
		seperator2.setBounds(663, 357, 129, 2);
		contentPane.add(seperator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(new Color(242, 169, 31));
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBackground(new Color(242, 169, 31));
		separator3.setBounds(790, 242, 2, 117);
		contentPane.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setForeground(new Color(242, 169, 31));
		separator4.setBackground(new Color(242, 169, 31));
		separator4.setBounds(663, 242, 2, 117);
		contentPane.add(separator4);
		
		txtID = new JTextField();
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
		txtID.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtID.setBounds(647, 128, 81, 30);
		contentPane.add(txtID);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(242, 169, 31));
		lblId.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblId.setBounds(656, 98, 62, 24);
		contentPane.add(lblId);
		
		LocalDate date = LocalDate.now(); 
		String dateString = date.toString(); // convert
		
		JButton printbutton = new JButton("PRINT"); // Print function
		printbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat (dateString);
				MessageFormat footer = new MessageFormat ("Total Items: " + table.getRowCount());
				
				if(table.getRowCount() > 0) { // check if table data count is greater than 0
					
				try {
					table.print(JTable.PrintMode.FIT_WIDTH,header,footer); // Show print frame 
					
					
					
				}catch (Exception r) {
					JOptionPane.showInternalMessageDialog(null, "Unable to print"); 
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no data to print.");//will not print blank data table
				}
			}
		});
		printbutton.setForeground(new Color(31, 37, 47));
		printbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		printbutton.setIcon(new ImageIcon("img\\print.png"));
		printbutton.setFocusPainted(false);
		printbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		printbutton.setBackground(new Color(242, 169, 31));
		printbutton.setBounds(675, 323, 105, 23);
		contentPane.add(printbutton);
		
		JButton findbutton = new JButton("FIND");
		findbutton.setIcon(new ImageIcon("img\\find.png"));
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
		findbutton.setForeground(new Color(31, 37, 47));
		findbutton.setFont(new Font("Tahoma", Font.BOLD, 11));
		findbutton.setFocusPainted(false);
		findbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		findbutton.setBackground(new Color(242, 169, 31));
		findbutton.setBounds(738, 128, 67, 31);
		contentPane.add(findbutton);
		
		JSeparator separator5 = new JSeparator();
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.setForeground(new Color(242, 169, 31));
		separator5.setBackground(new Color(242, 169, 31));
		separator5.setBounds(625, 99, 2, 81);
		contentPane.add(separator5);
		
		JSeparator seperator6 = new JSeparator();
		seperator6.setForeground(new Color(242, 169, 31));
		seperator6.setBackground(new Color(242, 169, 31));
		seperator6.setBounds(663, 398, 129, 2);
		contentPane.add(seperator6);
		
		JSeparator seperator7 = new JSeparator();
		seperator7.setForeground(new Color(242, 169, 31));
		seperator7.setBackground(new Color(242, 169, 31));
		seperator7.setBounds(663, 479, 129, 2);
		contentPane.add(seperator7);
		
		JSeparator separator8 = new JSeparator();
		separator8.setOrientation(SwingConstants.VERTICAL);
		separator8.setForeground(new Color(242, 169, 31));
		separator8.setBackground(new Color(242, 169, 31));
		separator8.setBounds(790, 398, 2, 83);
		contentPane.add(separator8);
		
		JSeparator separator9 = new JSeparator();
		separator9.setOrientation(SwingConstants.VERTICAL);
		separator9.setForeground(new Color(242, 169, 31));
		separator9.setBackground(new Color(242, 169, 31));
		separator9.setBounds(663, 398, 2, 83);
		contentPane.add(separator9);
		
		JLabel forIdlabel = new JLabel("FOR ID");
		forIdlabel.setHorizontalAlignment(SwingConstants.CENTER);
		forIdlabel.setForeground(new Color(242, 169, 31));
		forIdlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		forIdlabel.setBounds(663, 380, 129, 14);
		contentPane.add(forIdlabel);
		
		txtTypeMirror = new JTextField();
		txtTypeMirror.setBounds(701, 169, 15, 19);
		contentPane.add(txtTypeMirror);
		txtTypeMirror.setColumns(10);
		
		txtQtyMirror = new JTextField();
		txtQtyMirror.setBounds(706, 202, 15, 19);
		contentPane.add(txtQtyMirror);
		txtQtyMirror.setColumns(10);
		
		txtBrandMirror = new JTextField();
		txtBrandMirror.setColumns(10);
		txtBrandMirror.setBounds(731, 169, 9, 19);
		contentPane.add(txtBrandMirror);
		
		txtIDMirror = new JTextField();
		txtIDMirror.setColumns(10);
		txtIDMirror.setBounds(731, 202, 9, 19);
		contentPane.add(txtIDMirror);
		
		txtTypeMirror.setVisible(false);
		txtBrandMirror.setVisible(false);
		txtQtyMirror.setVisible(false);
		txtIDMirror.setVisible(false);
		
		table_load(); //reload
		if(table.getRowCount() == 0) {
			lblList.setVisible(true);
		}
	}
}
