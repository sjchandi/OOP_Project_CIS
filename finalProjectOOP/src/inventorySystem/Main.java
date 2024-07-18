package inventorySystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setResizable(false);
		setTitle("Construction Inventory System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon imgIcon = new ImageIcon("img\\frameicon.png"); // Image for Frameicon
        Image frameicon = imgIcon.getImage();
        setIconImage(frameicon);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcomelabel = new JLabel("CONSTRUCTION");
		welcomelabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		welcomelabel.setBounds(0, 117, 389, 56);
		contentPane.add(welcomelabel);
		
		JLabel lblInventory = new JLabel("INVENTORY SYSTEM");
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setForeground(Color.WHITE);
		lblInventory.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblInventory.setBounds(0, 153, 389, 56);
		contentPane.add(lblInventory);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setIcon(new ImageIcon("img\\cislogo.png"));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(74, 207, 250, 208);
		contentPane.add(btnNewButton);
		
		JLabel signinlabel = new JLabel("LOGIN AS");
		signinlabel.setHorizontalAlignment(SwingConstants.CENTER);
		signinlabel.setForeground(new Color(242, 169, 31));
		signinlabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		signinlabel.setBounds(399, 168, 296, 56);
		contentPane.add(signinlabel);
		
		JButton emploginicon = new JButton("");
		emploginicon.setFocusPainted(false);
		emploginicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login em = new Login(); //Object of Employee file
				em.setLocationRelativeTo(null); //Center of page
				em.setVisible(true); // Shows archive page
				setVisible(false); // Hides Current page
			}
		});
		emploginicon.setIcon(new ImageIcon("img\\employeeloginicon.png"));
		emploginicon.setContentAreaFilled(false);
		emploginicon.setBorderPainted(false);
		emploginicon.setBounds(456, 235, 78, 70);
		contentPane.add(emploginicon);
		
		JButton admloginicon = new JButton("");
		admloginicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin adm = new AdminLogin(); //Object of Employee file
				adm.setLocationRelativeTo(null); //Center of page
				adm.setVisible(true); // Shows archive page
				setVisible(false); // Hides Current page
			}
		});

		admloginicon.setIcon(new ImageIcon("img\\adminloginicon.png"));
		admloginicon.setFocusPainted(false);
		admloginicon.setContentAreaFilled(false);
		admloginicon.setBorderPainted(false);
		admloginicon.setBounds(561, 235, 78, 69);
		contentPane.add(admloginicon);
		
		JLabel employeelabel = new JLabel("Employee");
		employeelabel.setHorizontalAlignment(SwingConstants.CENTER);
		employeelabel.setForeground(new Color(242, 169, 31));
		employeelabel.setFont(new Font("Georgia", Font.BOLD, 12));
		employeelabel.setBounds(443, 304, 104, 24);
		contentPane.add(employeelabel);
		
		JLabel adminlabel = new JLabel("Administrator");
		adminlabel.setHorizontalAlignment(SwingConstants.CENTER);
		adminlabel.setForeground(new Color(242, 169, 31));
		adminlabel.setFont(new Font("Georgia", Font.BOLD, 12));
		adminlabel.setBounds(535, 304, 126, 24);
		contentPane.add(adminlabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(242, 169, 31));
		separator.setBackground(new Color(242, 169, 31));
		separator.setBounds(389, 117, 2, 309);
		contentPane.add(separator);
		
		JButton btnExit = new JButton("Exit");
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
		
		btnExit.setIcon(new ImageIcon("img\\exit.png"));
		btnExit.setRequestFocusEnabled(false);
		btnExit.setForeground(new Color(31, 37, 47));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBorder(new LineBorder(new Color(31, 37, 47)));
		btnExit.setBackground(new Color(242, 169, 31));
		btnExit.setBounds(630, 418, 69, 29);
		contentPane.add(btnExit);
		
		JLabel mainbglabel = new JLabel("");
		mainbglabel.setIcon(new ImageIcon("img\\mainbg.png"));
		mainbglabel.setBounds(0, 0, 726, 470);
		contentPane.add(mainbglabel);
	}
}
