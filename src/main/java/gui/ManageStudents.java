package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ManageStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField textField;
	private JLabel lblSex;
	private JPanel panelsex;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JLabel lblBirthDate;
	private JTextField txtBirthDate;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblAddress;
	private JScrollPane scrollPane;
	private JTextArea txtAddress;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JTable table;
	private JScrollPane tableScrollPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStudents frame = new ManageStudents();
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
	public ManageStudents() {
		setTitle("Manage Students");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 792);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(108, 174, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0	};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		lblTitle = new JLabel("Manage Students");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(10, 0, 20, 0);
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		lblId = new JLabel("Id:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(10, 0, 20, 0);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(5, 5, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 1;
		contentPane.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 2;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(5 ,5 ,5 ,5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 2;
		contentPane.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		lblLastName = new JLabel("LastName");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 3;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5 ,5 ,5 ,5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblSex = new JLabel("Sex:");
		GridBagConstraints gbc_lblSex = new GridBagConstraints();
		gbc_lblSex.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblSex.gridx = 0;
		gbc_lblSex.gridy = 4;
		contentPane.add(lblSex, gbc_lblSex);
		
		panelsex = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5 ,5 ,5 ,5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		contentPane.add(panelsex, gbc_panel);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		buttonGroup.add(rdbtnMale);
		panelsex.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnFemale);
		panelsex.add(rdbtnFemale);
		
		lblBirthDate = new JLabel("BirthDate:");
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.anchor = GridBagConstraints.EAST;
		gbc_lblBirthDate.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 5;
		contentPane.add(lblBirthDate, gbc_lblBirthDate);
		
		txtBirthDate = new JTextField();
		GridBagConstraints gbc_txtBirthDate = new GridBagConstraints();
		gbc_txtBirthDate.insets = new Insets(5 ,5 ,5 ,5);
		gbc_txtBirthDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthDate.gridx = 1;
		gbc_txtBirthDate.gridy = 5;
		contentPane.add(txtBirthDate, gbc_txtBirthDate);
		txtBirthDate.setColumns(10);
		
		lblPhone = new JLabel("Phone:");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 6;
		contentPane.add(lblPhone, gbc_lblPhone);
		
		txtPhone = new JTextField();
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(5 ,5 ,5 ,5);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 6;
		contentPane.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);
		
		lblAddress = new JLabel("Address:");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(5 ,5 ,5 ,5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy =7;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(5 ,5 ,5 ,5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 7;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtAddress = new JTextArea(3,10);
		scrollPane.setColumnHeaderView(txtAddress);
		
//		
		buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(5 ,5 ,5 ,5);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 1;
		gbc_buttonPanel.gridy = 8;
		contentPane.add(buttonPanel, gbc_buttonPanel);
		
		btnAdd = new JButton("<html>\r\n\r\n<body>\r\n\r\n&#43;Add\r\n\r\n</body>\r\n</html>");
		buttonPanel.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		buttonPanel.add(btnEdit);
		
		btnRemove = new JButton("<html>\r\n\r\n<body>\r\n\r\n&#10060; Remove\r\n\r\n</body>\r\n</html>");
		buttonPanel.add(btnRemove);
		
		lblSearch = new JLabel("Enter Value To Search:");
		lblSearch.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(5, 5, 5, 5);
		gbc_lblSearch.gridx = 2;
		gbc_lblSearch.gridy = 1;
		contentPane.add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(255, 255, 255));
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.insets = new Insets(5, 5, 5, 5);
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 2;	
		contentPane.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		 String[] columnNames = {"Id", "First Name", "Last Name", "Sex", "BirthDate", "Phone", "Address"};
	        table = new JTable(new DefaultTableModel(columnNames, 8));
	        table.setBorder(new LineBorder(new Color(250, 250, 210), 2));
	        tableScrollPane = new JScrollPane(table);
	        tableScrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_table = new GridBagConstraints();
//		gbc_table.gridwidth = 8;
		gbc_table.anchor = GridBagConstraints.EAST;
		gbc_table.insets = new Insets(5, 5, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 2;
		gbc_table.gridy = 3;
		gbc_table.gridheight = 8;
		gbc_table.weightx = 1.0;
		gbc_table.weighty = 1.0;
		contentPane.add(tableScrollPane, gbc_table);
	}	

}
