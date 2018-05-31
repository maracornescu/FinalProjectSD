package presentation;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import encryption.Encryption;
import model.BusAgencyModel;
import model.TravellerModel;
import model.UserModel;
import requests.BusAgencyRequest;
import requests.LoginRequest;
import requests.TravellerRequest;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frmLogin;
	private JTextField emailLoginTextField;
	private JTextField firstNameTravellerTextField;
	private JTextField lastNameTravellerTextField;
	private JTextField emailTravellerTextField;
	private JTextField nameBusAgencyTextField;
	private JTextField emailBusAgencyTextField;
	private JPasswordField passwordLoginTextField;
	private JPasswordField passwordTravellerTextField;
	private JPasswordField passwordBusAgencyTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 1179, 652);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 222, 56, 16);
		frmLogin.getContentPane().add(lblEmail);
		//frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Mara\\Desktop\\bookbus.png")));
		
		/*
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Mara\\Desktop\\bookbus.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1170, 650, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		frame.setContentPane(new JLabel(imageIcon));*/
		
		emailLoginTextField = new JTextField();
		emailLoginTextField.setBounds(83, 219, 215, 22);
		frmLogin.getContentPane().add(emailLoginTextField);
		emailLoginTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 292, 72, 16);
		frmLogin.getContentPane().add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(357, 155, 79, 16);
		frmLogin.getContentPane().add(lblFirstName);
		
		firstNameTravellerTextField = new JTextField();
		firstNameTravellerTextField.setBounds(448, 152, 201, 22);
		frmLogin.getContentPane().add(firstNameTravellerTextField);
		firstNameTravellerTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(357, 222, 79, 16);
		frmLogin.getContentPane().add(lblLastName);
		
		lastNameTravellerTextField = new JTextField();
		lastNameTravellerTextField.setBounds(448, 219, 201, 22);
		frmLogin.getContentPane().add(lastNameTravellerTextField);
		lastNameTravellerTextField.setColumns(10);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(380, 292, 56, 16);
		frmLogin.getContentPane().add(lblEmail_1);
		
		emailTravellerTextField = new JTextField();
		emailTravellerTextField.setBounds(448, 289, 201, 22);
		frmLogin.getContentPane().add(emailTravellerTextField);
		emailTravellerTextField.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(368, 364, 79, 16);
		frmLogin.getContentPane().add(lblPassword_1);
		
		JLabel lblBusAgencyName = new JLabel("Bus Agency Name");
		lblBusAgencyName.setBounds(757, 222, 116, 16);
		frmLogin.getContentPane().add(lblBusAgencyName);
		
		nameBusAgencyTextField = new JTextField();
		nameBusAgencyTextField.setBounds(874, 219, 192, 22);
		frmLogin.getContentPane().add(nameBusAgencyTextField);
		nameBusAgencyTextField.setColumns(10);
		
		JLabel lblEmail_2 = new JLabel("Email");
		lblEmail_2.setBounds(789, 292, 56, 16);
		frmLogin.getContentPane().add(lblEmail_2);
		
		emailBusAgencyTextField = new JTextField();
		emailBusAgencyTextField.setBounds(874, 289, 192, 22);
		frmLogin.getContentPane().add(emailBusAgencyTextField);
		emailBusAgencyTextField.setColumns(10);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setBounds(776, 364, 56, 16);
		frmLogin.getContentPane().add(lblPassword_2);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginRequest request = new LoginRequest();
					String email = emailLoginTextField.getText();
					String password = String.valueOf(passwordLoginTextField.getPassword());
										
					
					String securePassword = Encryption.get_SHA_1_SecurePassword(password);
			
					UserModel userModel = request.login(email, securePassword);
					
					if(userModel instanceof TravellerModel) {
						TravellerModel s = (TravellerModel) userModel;
						TravellerView frame = new TravellerView(s);
						frame.setVisible(true);
					}
					else if(userModel instanceof BusAgencyModel) {
						BusAgencyModel s = (BusAgencyModel) userModel;
						BusAgencyView frame = new BusAgencyView(s);
						frame.setVisible(true);
					}
					else {
						AdminView frame = new AdminView();
						frame.setVisible(true);
					}
					
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Wrong email or password!");
				}
			}
		});
		btnLogin.setBounds(78, 435, 97, 25);
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnRegisterTraveller = new JButton("Register Traveller");
		btnRegisterTraveller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String firstName = firstNameTravellerTextField.getText();
					String lastName = lastNameTravellerTextField.getText();
					String email = emailTravellerTextField.getText();
					String password = String.valueOf(passwordTravellerTextField.getPassword());
					String securePassword = Encryption.get_SHA_1_SecurePassword(password);
					
					TravellerRequest request = new TravellerRequest();
										
					TravellerModel newTraveller = new TravellerModel(firstName, lastName, email, securePassword);
					request.saveTraveller(newTraveller);
					
					JOptionPane.showMessageDialog(null, "You have been successfully registered!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong! Please try again!");
				}
			}
		});
		btnRegisterTraveller.setBounds(399, 435, 174, 25);
		frmLogin.getContentPane().add(btnRegisterTraveller);
		
		JButton btnRegisterBusAgency = new JButton("Register Bus Agency");
		btnRegisterBusAgency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String companyName = nameBusAgencyTextField.getText();
					String email = emailBusAgencyTextField.getText();
					String password = String.valueOf(passwordBusAgencyTextField.getPassword());
					String securePassword = Encryption.get_SHA_1_SecurePassword(password);
					
					BusAgencyRequest request = new BusAgencyRequest();
										
					BusAgencyModel newAgency = new BusAgencyModel(companyName, email, securePassword);
					request.saveAgency(newAgency);
					
					JOptionPane.showMessageDialog(null, "You have been successfully registered!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong! Please try again!");
				}
			}
		});
		btnRegisterBusAgency.setBounds(819, 435, 169, 25);
		frmLogin.getContentPane().add(btnRegisterBusAgency);
		
		passwordLoginTextField = new JPasswordField();
		passwordLoginTextField.setBounds(83, 289, 215, 22);
		frmLogin.getContentPane().add(passwordLoginTextField);
		
		passwordTravellerTextField = new JPasswordField();
		passwordTravellerTextField.setBounds(448, 364, 201, 22);
		frmLogin.getContentPane().add(passwordTravellerTextField);
		
		passwordBusAgencyTextField = new JPasswordField();
		passwordBusAgencyTextField.setBounds(874, 361, 192, 22);
		frmLogin.getContentPane().add(passwordBusAgencyTextField);
	}
}
