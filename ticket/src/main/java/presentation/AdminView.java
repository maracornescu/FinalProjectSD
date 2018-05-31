package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BusAgencyModel;
import model.TravellerModel;
import requests.BusAgencyRequest;
import requests.TravellerRequest;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JTextField companyNameTextField;
	private JTextField busEmailTextField;
	private JPasswordField passwordField_1;
	private JTable travellersTable;
	private JTable busAgencyTable;
	private JScrollPane travellersScrollPane, busAgencyScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
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
	public AdminView() {
		setTitle("Admin");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(51, 58, 942, 489);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Travellers", null, panel, null);
		panel.setLayout(null);
		
		travellersScrollPane = new JScrollPane();
		travellersScrollPane.setBounds(12, 160, 900, 275);
		panel.add(travellersScrollPane);
		
		travellersTable = new JTable();
		travellersScrollPane.setViewportView(travellersTable);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(25, 25, 71, 16);
		panel.add(lblFirstName);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(94, 22, 116, 22);
		panel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(253, 25, 71, 16);
		panel.add(lblLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(322, 22, 116, 22);
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(489, 25, 46, 16);
		panel.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(532, 22, 116, 22);
		panel.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(682, 25, 73, 16);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(755, 22, 110, 22);
		panel.add(passwordField);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String firstName = firstNameTextField.getText();
					String lastName = lastNameTextField.getText();
					String email = emailTextField.getText();
					String password = String.valueOf(passwordField.getPassword());
					
					TravellerRequest request = new TravellerRequest();
										
					TravellerModel newTraveller = new TravellerModel(firstName, lastName, email, password);
					request.saveTraveller(newTraveller);
					
					List<TravellerModel> travellers = request.getAllTravellers(null);
					
					travellersTable = Table.createTable(travellers);
					travellersScrollPane.setViewportView(travellersTable);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAddCustomer.setBounds(61, 91, 130, 25);
		panel.add(btnAddCustomer);
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TravellerRequest request = new TravellerRequest();
					
					Long customerId = (Long) travellersTable.getModel().getValueAt(travellersTable.getSelectedRow(), 0);
					request.deleteTravellerById(customerId);
					
					List<TravellerModel> travellers = request.getAllTravellers(null);
					
					travellersTable = Table.createTable(travellers);
					travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
					travellersScrollPane.setViewportView(travellersTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnDeleteCustomer.setBounds(253, 91, 130, 25);
		panel.add(btnDeleteCustomer);
		
		JButton btnViewAllCustomers = new JButton("View All Customers");
		btnViewAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TravellerRequest request = new TravellerRequest();
					
					List<TravellerModel> travellers = request.getAllTravellers(null);
					
					travellersTable = Table.createTable(travellers);
					travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
					travellersScrollPane.setViewportView(travellersTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnViewAllCustomers.setBounds(447, 91, 153, 25);
		panel.add(btnViewAllCustomers);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Bus Agencies", null, panel_1, null);
		panel_1.setLayout(null);
		
		busAgencyScrollPane = new JScrollPane();
		busAgencyScrollPane.setBounds(12, 171, 900, 275);
		panel_1.add(busAgencyScrollPane);
		
		busAgencyTable = new JTable();
		busAgencyScrollPane.setViewportView(busAgencyTable);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setBounds(30, 42, 105, 16);
		panel_1.add(lblCompanyName);
		
		companyNameTextField = new JTextField();
		companyNameTextField.setBounds(135, 39, 169, 22);
		panel_1.add(companyNameTextField);
		companyNameTextField.setColumns(10);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(350, 42, 56, 16);
		panel_1.add(lblEmail_1);
		
		busEmailTextField = new JTextField();
		busEmailTextField.setBounds(406, 39, 169, 22);
		panel_1.add(busEmailTextField);
		busEmailTextField.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(635, 42, 80, 16);
		panel_1.add(lblPassword_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(712, 39, 169, 22);
		panel_1.add(passwordField_1);
		
		JButton btnAddCompany = new JButton("Add Company");
		btnAddCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String companyName = companyNameTextField.getText();
					String email = busEmailTextField.getText();
					String password = String.valueOf(passwordField_1.getPassword());
					
					BusAgencyRequest request = new BusAgencyRequest();
										
					BusAgencyModel newAgency = new BusAgencyModel(companyName, email, password);
					request.saveAgency(newAgency);
					
					List<BusAgencyModel> agencies = request.getAllBusAgencies();
					
					busAgencyTable = Table.createTable(agencies);
					busAgencyTable.removeColumn(busAgencyTable.getColumnModel().getColumn(0));
					busAgencyScrollPane.setViewportView(busAgencyTable);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnAddCompany.setBounds(66, 109, 146, 25);
		panel_1.add(btnAddCompany);
		
		JButton btnDeleteCompany = new JButton("Delete Company");
		btnDeleteCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusAgencyRequest request = new BusAgencyRequest();
					
					Long agencyId = (Long) busAgencyTable.getModel().getValueAt(travellersTable.getSelectedRow(), 0);
					request.deleteAgencyById(agencyId);
					
					List<BusAgencyModel> agencies = request.getAllBusAgencies();
					
					busAgencyTable = Table.createTable(agencies);
					busAgencyTable.removeColumn(busAgencyTable.getColumnModel().getColumn(0));
					busAgencyScrollPane.setViewportView(busAgencyTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnDeleteCompany.setBounds(285, 109, 156, 25);
		panel_1.add(btnDeleteCompany);
		
		JButton btnViewAllCompanies = new JButton("View All Companies");
		btnViewAllCompanies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusAgencyRequest request = new BusAgencyRequest();
					
					List<BusAgencyModel> agencies = request.getAllBusAgencies();
					
					busAgencyTable = Table.createTable(agencies);
					busAgencyTable.removeColumn(busAgencyTable.getColumnModel().getColumn(0));
					busAgencyScrollPane.setViewportView(busAgencyTable);
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnViewAllCompanies.setBounds(519, 109, 146, 25);
		panel_1.add(btnViewAllCompanies);
	}
}
