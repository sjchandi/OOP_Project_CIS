package inventorySystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class Archive extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Archive frame = new Archive("Default User");
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

	// SQL Variable for connection to SQL database
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtID;
	private JTextField textType;
	private JTextField textBrand;
	private JTextField textQty;
	
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
			pst =  con.prepareStatement("SELECT * from archive");
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
	
	
	//GUI Display
	public Archive(String username) {

		
		Connect();
		setTitle("Archive Inventory");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 579);
		
		ImageIcon imgIcon = new ImageIcon("img\\frameicon.png"); //frameicon
        Image frameicon = imgIcon.getImage();
        setIconImage(frameicon);
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(43, 53, 64));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblList = new JLabel("No data yet!"); //No data text if no data is detected
		lblList.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblList.setBounds(328, 268, 194, 43);
		contentPane.add(lblList);
		lblList.setVisible(false);
		
		JPanel slatebluepanel = new JPanel();
		slatebluepanel.setBorder(null);
		slatebluepanel.setBounds(0, 0, 93, 540);
		contentPane.add(slatebluepanel);
		slatebluepanel.setBackground(new Color(31, 37, 47));
		slatebluepanel.setLayout(null);
		
		JLabel gradientbg = new JLabel("");
		gradientbg.setIcon(new ImageIcon("img\\gradientbg.png")); //Background
		gradientbg.setBounds(0, -34, 93, 574);
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
		logoicon.setIcon(new ImageIcon("img\\logo.png")); //Logo
		logoicon.setBounds(0, 0, 93, 70);
		slatebluepanel.add(logoicon);
		
		JButton inventoryiconbutton = new JButton("");
		inventoryiconbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inventory inv = new Inventory(username); //Object of Inventory file
				inv.setLocationRelativeTo(null); //Center of page
				inv.setVisible(true); // Shows inventory page
				setVisible(false); // Hides Current page
				
				
			}
		});
		
		inventoryiconbutton.setContentAreaFilled(false);
		inventoryiconbutton.setBorderPainted(false);
		inventoryiconbutton.setIcon(new ImageIcon("img\\inventory.png")); //archive icon
		inventoryiconbutton.setSize(new Dimension(40, 40));
		inventoryiconbutton.setMinimumSize(new Dimension(40, 40));
		inventoryiconbutton.setMaximumSize(new Dimension(40, 40));
		inventoryiconbutton.setPreferredSize(new Dimension(40, 40));
		inventoryiconbutton.setBounds(24, 107, 40, 40);
		slatebluepanel.add(inventoryiconbutton);
		
		JButton logoutbutton = new JButton("");  //Logout button
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
				else if (result == JOptionPane.NO_OPTION) {
					
				}
			}
		});
		logoutbutton.setBorderPainted(false);
		logoutbutton.setContentAreaFilled(false);
		logoutbutton.setIcon(new ImageIcon("img\\logout.png"));
		logoutbutton.setBounds(24, 183, 40, 40);
		slatebluepanel.add(logoutbutton);
		
		JButton searchbutton = new JButton(""); //Search textfield
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchbutton.setContentAreaFilled(false);
		searchbutton.setBorderPainted(false);
		searchbutton.setIcon(new ImageIcon("img\\search.png"));
		searchbutton.setBounds(137, 100, 46, 30);
		contentPane.add(searchbutton);
		
		JPanel yellowpanel = new JPanel();
		yellowpanel.setBackground(new Color(242, 169, 31));
		yellowpanel.setBounds(91, -11, 692, 81);
		contentPane.add(yellowpanel);
		yellowpanel.setLayout(null);
		
		JLabel welcomelabel = new JLabel("Archive Inventory");
		welcomelabel.setForeground(new Color(43, 53, 64));
		welcomelabel.setFont(new Font("Gill Sans MT", Font.BOLD, 28));
		welcomelabel.setBounds(23, 11, 444, 70);
		yellowpanel.add(welcomelabel);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() { // Activate search in jtable (function will operate after key released)
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				
				String type;
				
				type = txtSearch.getText();
				
				
				try { // SQL code for search textfield  (Finding data in table and mirror in jtable)
					pst = con.prepareStatement("SELECT * from archive WHERE (type = ? OR brand = ?) OR (CONCAT(type, ' ', brand)=?) OR (CONCAT(brand, ' ', type)=?)");
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
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 15));
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
		txtSearch.setBorder(new LineBorder(new Color(246, 169, 31), 2));
		txtSearch.setBounds(183, 100, 315, 30);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(242, 169, 31), 2));
		scrollPane.setBounds(137, 148, 578, 322);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		JButton unarchivebutton;
		unarchivebutton = new JButton("Unarchive");
		unarchivebutton.setFocusPainted(false);
		unarchivebutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		unarchivebutton.setIcon(new ImageIcon("img\\unarchive.png"));
		unarchivebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			    
				 int[] selectedRows = table.getSelectedRows();
				
				String id, type, brand, qty;
				id = txtID.getText();
				type = textType.getText();
				brand = textBrand.getText();
				qty = textQty.getText();
		
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
			            table_load();
			            
			            if(table.getRowCount() == 0) {
							lblList.setVisible(true);
						}
						else {
							lblList.setVisible(false);
						}
			            
			            textType.setText(null);
			            textBrand.setText(null);
			            textQty.setText(null);
						txtID.setText(null);
			         

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
				            
				            if (rowsDeleted > 0) {
				                tblModel.removeRow(selectedRow); 
				            } else {
				                JOptionPane.showMessageDialog(null, "Failed to unarchive" + idToDelete);
				            }
				        }
				        
				        JOptionPane.showMessageDialog(null, "Selected data row has been moved to active list.");
				        
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
					JOptionPane.showMessageDialog(null, "Please select a data first through typing the ID number or the ID number is not on the list!" , "Failed", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		

		JButton refreshbutton = new JButton("Refresh"); 
		refreshbutton.setFocusPainted(false);
		refreshbutton.setIcon(new ImageIcon("img\\refresh.png"));
		refreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load(); //Reload Table
			}
		});
		refreshbutton.setFont(new Font("Tahoma", Font.BOLD, 14));
		refreshbutton.setForeground(new Color(31, 37, 47));
		refreshbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		refreshbutton.setBackground(new Color(242, 169, 31));
		refreshbutton.setBounds(207, 481, 129, 35);
		contentPane.add(refreshbutton);
		unarchivebutton.setFont(new Font("Tahoma", Font.BOLD, 14));
		unarchivebutton.setForeground(new Color(31, 37, 47));
		unarchivebutton.setBackground(new Color(242, 169, 31));
		unarchivebutton.setBounds(357, 481, 132, 35);
		contentPane.add(unarchivebutton);
		
		LocalDate date = LocalDate.now(); 
		String dateString = date.toString(); // convert
		
		
		JButton printbutton = new JButton("Print\r\n");
		printbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat (dateString);
				MessageFormat footer = new MessageFormat ("Total Items: " + table.getRowCount());
				
				
				
				if(table.getRowCount() > 0) { //Check data count in table and show print if greater than 0
				try {
					table.print(JTable.PrintMode.FIT_WIDTH,header ,footer); // Show print frame 
				}catch (Exception r) {
					JOptionPane.showInternalMessageDialog(null, "Unable to print"); 
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no data to print."); // show no data box if no data was detected
				}
				
				
			}
		});
		printbutton.setForeground(new Color(31, 37, 47));
		printbutton.setFocusPainted(false);
		printbutton.setBorder(new LineBorder(new Color(31, 37, 47)));
		printbutton.setIcon(new ImageIcon("img\\print.png"));
		printbutton.setFont(new Font("Tahoma", Font.BOLD, 14));
		printbutton.setBackground(new Color(242, 169, 31));
		printbutton.setBounds(510, 481, 111, 35);
		contentPane.add(printbutton);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() { // ID FIELD key released
			@Override
			public void keyReleased(KeyEvent e) { 
				
				try {
					String id = txtID.getText();
					pst = con.prepareStatement("SELECT type, brand, qty FROM archive WHERE id = ?"); //SQL code for showing data depending on ID
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						String type = rs.getString(1);
						String brand = rs.getString(2);
						String qty = rs.getString(3);
						
						textType.setText(type);
						textBrand.setText(brand);
						textQty.setText(qty);
						
					}
					
					else {
						textType.setText("");
						textBrand.setText("");
						textQty.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); // Only numerical inputs
				
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
		txtID.setBounds(633, 100, 82, 30);
		contentPane.add(txtID);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(242, 169, 31));
		lblId.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblId.setBounds(601, 106, 32, 24);
		contentPane.add(lblId);
		
		textType = new JTextField();
		textType.setBounds(748, 81, 7, 20);
		contentPane.add(textType);
		textType.setColumns(10);
		
		textBrand = new JTextField();
		textBrand.setBounds(748, 106, 7, 20);
		contentPane.add(textBrand);
		textBrand.setColumns(10);
		
		textQty = new JTextField();
		textQty.setBounds(748, 138, 7, 20);
		contentPane.add(textQty);
		textQty.setColumns(10);
		
		table_load(); //Reload table
		textType.setVisible(false);
		textBrand.setVisible(false);
		textQty.setVisible(false);

		if(table.getRowCount() == 0) {
			lblList.setVisible(true);
		}
	}
}
