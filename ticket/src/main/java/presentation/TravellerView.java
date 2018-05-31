package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import model.ReservationModel;
import model.SellTicketModel;
import model.TravellerModel;
import requests.PossibleRoutesRequest;
import requests.SellTicketRequest;
import requests.TicketRequest;
import requests.TravellerRequest;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;  


public class TravellerView extends JFrame {

	private JPanel contentPane;
	private JTextField fromCityTextField;
	private JTextField toCityTextField;
	private JTextField seatNrTextField;
	private JTextField dateTextField;
	private JTable bookingsTable;
	private JScrollPane bookingsScrollPane;
	private JTable allBookingsTable;
	private JScrollPane allBookingsScrollPane;
	private JTextField accountFirstNameTextField;
	private JTextField accountLastNameTextField;
	private JTextField accountEmailTextField;
	private JPasswordField accountPasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TravellerRequest t = new TravellerRequest();
					TravellerModel s = t.getTravellerById((long) 30);
					TravellerView frame = new TravellerView(s);
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
	public TravellerView(final TravellerModel traveller) {
		setTitle("Traveller");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1145, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(27, 44, 1062, 495);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search ", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(92, 36, 56, 16);
		panel.add(lblFrom);
		
		fromCityTextField = new JTextField();
		fromCityTextField.setBounds(160, 33, 187, 22);
		panel.add(fromCityTextField);
		fromCityTextField.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(92, 88, 32, 16);
		panel.add(lblTo);
		
		toCityTextField = new JTextField();
		toCityTextField.setBounds(160, 85, 188, 22);
		panel.add(toCityTextField);
		toCityTextField.setColumns(10);
		
		JLabel lblNumberOfSeats = new JLabel("Number Of Seats");
		lblNumberOfSeats.setBounds(371, 88, 112, 16);
		panel.add(lblNumberOfSeats);
		
		seatNrTextField = new JTextField();
		seatNrTextField.setBounds(480, 85, 187, 22);
		panel.add(seatNrTextField);
		seatNrTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(427, 36, 56, 16);
		panel.add(lblDate);
		
	
		dateTextField = new JTextField();
		dateTextField.setBounds(479, 33, 188, 22);
		panel.add(dateTextField);
		dateTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(712, 84, 97, 25);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PossibleRoutesRequest routesRequst = new PossibleRoutesRequest();
					
					String fromCity = fromCityTextField.getText();
					//System.out.println(fromCity);
					String toCity = toCityTextField.getText();
					//System.out.println(toCity);
					String date = dateTextField.getText();
					//System.out.println(date);
					int numberOfSeats = Integer.parseInt(seatNrTextField.getText());
					//System.out.println(numberOfSeats);
				
					List<ReservationModel> routes = routesRequst.getAllPossibleRoutes(fromCity, toCity, date, numberOfSeats, null);
					for(ReservationModel r: routes)
						System.out.println(r.getDepartureCity());
					bookingsTable = Table.createTable(routes);
					bookingsScrollPane.setViewportView(bookingsTable);
				} catch(Exception err) {
					//err.printStackTrace();
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		panel.add(btnSearch);
		
		bookingsScrollPane = new JScrollPane();
		bookingsScrollPane.setBounds(32, 117, 957, 326);
		panel.add(bookingsScrollPane);
		
		bookingsTable = new JTable();
		bookingsScrollPane.setViewportView(bookingsTable);
		
		JButton btnBookTickets = new JButton("Book Tickets");
		btnBookTickets.setBounds(834, 84, 155, 25);
		btnBookTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					TicketRequest ticketRequest = new TicketRequest();
					SellTicketRequest sellTicket = new SellTicketRequest();
					
					String date = dateTextField.getText();
					int numberOfSeats = Integer.parseInt(seatNrTextField.getText());
					String travellerEmail = traveller.getEmail();
					
					SellTicketModel ticket = new SellTicketModel();
					ticket.setFromCity((String) bookingsTable.getModel().getValueAt(bookingsTable.getSelectedRow(), 0));
					ticket.setToCity((String) bookingsTable.getModel().getValueAt(bookingsTable.getSelectedRow(), 1));
					ticket.setDate(date);
					ticket.setNumberOfTickets(numberOfSeats);
					ticket.setTravellerEmail(travellerEmail);
					
					System.out.println(travellerEmail);
					
					String busAgency = (String) bookingsTable.getModel().getValueAt(bookingsTable.getSelectedRow(), 2);
					ticket.setBusAgency(busAgency);
					ticketRequest.saveTicket(ticket);
					sellTicket.sellTicket(ticket);
					
					JOptionPane.showMessageDialog(null, "You have successfully booked the tickets!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
				}
				
			}
		});
		panel.add(btnBookTickets);
		
		JButton btnSelectDate = new JButton("Select Date");
		btnSelectDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date 
				dateTextField.setText(new DatePicker(f).setPickedDate());
			}
		});
		btnSelectDate.setBounds(709, 32, 140, 25);
		panel.add(btnSelectDate);
		
		JPanel ViewAll = new JPanel();
		tabbedPane.addTab("View All Bookings", null, ViewAll, null);
		ViewAll.setLayout(null);
		
		allBookingsScrollPane = new JScrollPane();
		allBookingsScrollPane.setBounds(41, 112, 957, 326);
		ViewAll.add(allBookingsScrollPane);
		
		allBookingsTable = new JTable();
		allBookingsScrollPane.setViewportView(allBookingsTable);
		
		JButton btnViewAllBookings = new JButton("View All Bookings");
		btnViewAllBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TicketRequest ticketRequst = new TicketRequest();
					
					List<SellTicketModel> tickets = ticketRequst.getAllTickets(null, null, traveller.getTravellerId());
					if(tickets.size() > 0) {
						allBookingsTable = Table.createTable(tickets);
						allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(0));
						allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(3));
						allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(4));
					}
					else
						allBookingsTable = new JTable();

					allBookingsScrollPane.setViewportView(allBookingsTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
				}
			}
		});
		btnViewAllBookings.setBounds(41, 48, 161, 25);
		ViewAll.add(btnViewAllBookings);
		
		JButton btnDeleteBooking = new JButton("Delete Booking");
		btnDeleteBooking.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						TicketRequest ticketRequst = new TicketRequest();
						
						ticketRequst.deleteTicketById((Long) allBookingsTable.getModel().getValueAt(allBookingsTable.getSelectedRow(), 0));
						List<SellTicketModel> tickets = ticketRequst.getAllTickets(null, null, traveller.getTravellerId());
						if(tickets.size() > 0) {
							allBookingsTable = Table.createTable(tickets);
							allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(0));
							allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(3));
							allBookingsTable.removeColumn(allBookingsTable.getColumnModel().getColumn(4));
						}
						else
							allBookingsTable = new JTable();
						allBookingsScrollPane.setViewportView(allBookingsTable);

					} catch(Exception err) {
						JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
					}
			}
		});
		btnDeleteBooking.setBounds(249, 48, 154, 25);
		ViewAll.add(btnDeleteBooking);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Account", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(40, 71, 90, 16);
		panel_1.add(lblFirstName);
		
		accountFirstNameTextField = new JTextField();
		accountFirstNameTextField.setBounds(152, 68, 239, 22);
		panel_1.add(accountFirstNameTextField);
		accountFirstNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(40, 152, 90, 16);
		panel_1.add(lblLastName);
		
		accountLastNameTextField = new JTextField();
		accountLastNameTextField.setBounds(152, 149, 239, 22);
		panel_1.add(accountLastNameTextField);
		accountLastNameTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(40, 230, 90, 16);
		panel_1.add(lblEmail);
		
		accountEmailTextField = new JTextField();
		accountEmailTextField.setBounds(152, 227, 239, 22);
		panel_1.add(accountEmailTextField);
		accountEmailTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(40, 309, 90, 16);
		panel_1.add(lblPassword);
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstName = null;
				String lastName = null;
				String email = null;
				String password = null;
				try {
					TravellerRequest request = new TravellerRequest();
					
					if(!accountFirstNameTextField.getText().isEmpty()) {
						firstName = accountFirstNameTextField.getText();
					}
					
					if(!accountLastNameTextField.getText().isEmpty()) {
						lastName = accountLastNameTextField.getText();
					}
					
					if(!accountEmailTextField.getText().isEmpty()) {
						email = accountEmailTextField.getText();
					}
				
					if(!String.valueOf(accountPasswordTextField.getPassword()).isEmpty()) {
						password = String.valueOf(accountPasswordTextField.getPassword());
					}
					
					TravellerModel newTraveller = new TravellerModel(firstName, lastName, email, password);
					request.updateTraveller(traveller.getTravellerId(), newTraveller);
					
					JOptionPane.showMessageDialog(null, "Your information has been successfully updated!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnUpdateInfo.setBounds(88, 383, 126, 25);
		panel_1.add(btnUpdateInfo);
		
		accountPasswordTextField = new JPasswordField();
		accountPasswordTextField.setBounds(152, 306, 239, 22);
		panel_1.add(accountPasswordTextField);
	}
}
