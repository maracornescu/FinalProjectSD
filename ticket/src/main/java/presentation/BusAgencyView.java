package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BusAgencyModel;
import model.BusModel;
import model.BusRouteModel;
import model.ReservationModel;
import model.SellTicketModel;
import model.StopsModel;
import model.TravellerModel;
import requests.BusAgencyRequest;
import requests.BusRequest;
import requests.BusRouteRequest;
import requests.PossibleRoutesRequest;
import requests.SellTicketRequest;
import requests.StopsRequest;
import requests.TicketRequest;
import requests.TravellerRequest;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class BusAgencyView extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JTextField seatNrTextField;
	private JTextField dateTextField;
	private JTable travellersTable;
	private JTable busTable;
	private JTable bookingTable;
	private JScrollPane busScrollPane, travellersScrollPane, bookingScrollPane, addBusScrollPane, addStationScrollPane, addRouteScrollPane;
	private JTextField addFromTextField;
	private JTextField addToTextField;
	private JTextField addDateTextField;
	private JTextField addSeatNrTextField;
	private JTextField textField;
	private JTextField textField_1;
	private JTable addBusTable;
	private JTable addStationTable;
	private JTable addRouteTable;
	private JTextField accountCompanyNameTextField;
	private JTextField accountEmailTextField;
	private JPasswordField accountPasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusAgencyRequest agencyRequest = new BusAgencyRequest();
					BusAgencyModel s = agencyRequest.getBusAgencyById((long) 2);
					BusAgencyView frame = new BusAgencyView(s);
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
	public BusAgencyView(final BusAgencyModel busAgency) {
		setTitle("Bus Agency");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1064, 575);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("View All Bookings", null, panel, null);
		panel.setLayout(null);
		
		travellersScrollPane = new JScrollPane();
		travellersScrollPane.setBounds(30, 48, 957, 112);
		panel.add(travellersScrollPane);
		
		travellersTable = new JTable();
		travellersScrollPane.setViewportView(travellersTable);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(94, 13, 168, 22);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 16, 56, 16);
		panel.add(lblName);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 176, 56, 16);
		panel.add(lblFrom);
		
		fromTextField = new JTextField();
		fromTextField.setBounds(54, 173, 137, 22);
		panel.add(fromTextField);
		fromTextField.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(234, 176, 28, 16);
		panel.add(lblTo);
		
		toTextField = new JTextField();
		toTextField.setBounds(254, 173, 145, 22);
		panel.add(toTextField);
		toTextField.setColumns(10);
		
		JLabel lblNumberOfSeats = new JLabel("Number Of Seats");
		lblNumberOfSeats.setBounds(448, 176, 103, 16);
		panel.add(lblNumberOfSeats);
		
		seatNrTextField = new JTextField();
		seatNrTextField.setBounds(551, 173, 179, 22);
		panel.add(seatNrTextField);
		seatNrTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(791, 176, 42, 16);
		panel.add(lblDate);
		
		dateTextField = new JTextField();
		dateTextField.setBounds(826, 173, 161, 22);
		panel.add(dateTextField);
		dateTextField.setColumns(10);
		
		busScrollPane = new JScrollPane();
		busScrollPane.setBounds(30, 246, 957, 112);
		panel.add(busScrollPane);
		
		busTable = new JTable();
		busScrollPane.setViewportView(busTable);
		
		bookingScrollPane = new JScrollPane();
		bookingScrollPane.setBounds(30, 420, 957, 112);
		panel.add(bookingScrollPane);
		
		bookingTable = new JTable();
		bookingScrollPane.setViewportView(bookingTable);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TravellerRequest travellerRequst = new TravellerRequest();
					
					String keyword = nameTextField.getText();
					List<TravellerModel> travellers = travellerRequst.getAllTravellers(keyword);
					
					if(travellers.size() > 0) {
						travellersTable = Table.createTable(travellers);
						travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
						travellersScrollPane.setViewportView(travellersTable);
					}
					else {
						travellersTable = new JTable();
						travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
						travellersScrollPane.setViewportView(travellersTable);
					}
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnFilter.setBounds(286, 10, 97, 25);
		panel.add(btnFilter);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					TicketRequest ticketRequest = new TicketRequest();
					SellTicketRequest sellTicket = new SellTicketRequest();
					
					String date = dateTextField.getText();
					int numberOfSeats = Integer.parseInt(seatNrTextField.getText());
					String travellerEmail = (String) travellersTable.getModel().getValueAt(travellersTable.getSelectedRow(), 3);
					
					SellTicketModel ticket = new SellTicketModel();
					ticket.setFromCity((String) busTable.getModel().getValueAt(busTable.getSelectedRow(), 0));
					ticket.setToCity((String) busTable.getModel().getValueAt(busTable.getSelectedRow(), 1));
					ticket.setDate(date);
					ticket.setNumberOfTickets(numberOfSeats);
					ticket.setTravellerEmail(travellerEmail);
					
					String agency = busAgency.getName();
					ticket.setBusAgency(agency);
					ticketRequest.saveTicket(ticket);
					sellTicket.sellTicket(ticket);
					
					JOptionPane.showMessageDialog(null, "The booking has been successfully made!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
					err.printStackTrace();
				}
				
			}
		});
		btnBook.setBounds(30, 382, 97, 25);
		panel.add(btnBook);
		
		JButton btnFindBooking = new JButton("Find Booking");
		btnFindBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TicketRequest ticketRequst = new TicketRequest();
					Long travellerId = (Long) travellersTable.getModel().getValueAt(travellersTable.getSelectedRow(), 0);
					Long busId = (Long) busTable.getModel().getValueAt(travellersTable.getSelectedRow(), 0);
					List<SellTicketModel> tickets = ticketRequst.getAllTickets(null, busId, travellerId);
					if(tickets.size() > 0) {
						bookingTable = Table.createTable(tickets);
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(0));
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(3));
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(4));
					}
					else
						bookingTable = new JTable();

					bookingScrollPane.setViewportView(bookingTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
				}
			}
		});
		btnFindBooking.setBounds(161, 382, 126, 25);
		panel.add(btnFindBooking);
		
		JButton btnFindBus = new JButton("Find Bus");
		btnFindBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PossibleRoutesRequest routesRequst = new PossibleRoutesRequest();
					
					String fromCity = fromTextField.getText();
					String toCity = toTextField.getText();
					String date = dateTextField.getText();
					int numberOfSeats = Integer.parseInt(seatNrTextField.getText());
				
					List<ReservationModel> routes = routesRequst.getAllPossibleRoutes(fromCity, toCity, date, numberOfSeats, busAgency.getBusAgencyId());
					busTable = Table.createTable(routes);
					busScrollPane.setViewportView(busTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnFindBus.setBounds(30, 208, 97, 25);
		panel.add(btnFindBus);
		
		JButton btnRefresh = new JButton("View All");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TravellerRequest travellerRequst = new TravellerRequest();

					List<TravellerModel> travellers = travellerRequst.getAllTravellers(null);
					
					if(travellers.size() > 0) {
						travellersTable = Table.createTable(travellers);
						travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
						travellersScrollPane.setViewportView(travellersTable);
					}
					else {
						travellersTable = new JTable();
						travellersTable.removeColumn(travellersTable.getColumnModel().getColumn(0));
						travellersScrollPane.setViewportView(travellersTable);
					}
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnRefresh.setBounds(416, 10, 97, 25);
		panel.add(btnRefresh);
		
		JButton btnFindAll = new JButton("Find All");
		btnFindAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusRequest busRequest = new BusRequest();
					TravellerRequest travellerRequst = new TravellerRequest();
					
					List<BusModel> busses = busRequest.getAllBuses(busAgency.getBusAgencyId());
					
					List<TravellerModel> travellers = travellerRequst.getAllTravellers(null);
					
					if(travellers.size() > 0) {
						busTable = Table.createTable(busses);
						busTable.removeColumn(busTable.getColumnModel().getColumn(0));
						busTable.removeColumn(busTable.getColumnModel().getColumn(4));
					}
					else {
						busTable = new JTable();
						busTable.removeColumn(busTable.getColumnModel().getColumn(0));
						busTable.removeColumn(busTable.getColumnModel().getColumn(4));
					}
					busScrollPane.setViewportView(busTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnFindAll.setBounds(165, 208, 97, 25);
		panel.add(btnFindAll);
		
		JButton btnBookingsForCustomer = new JButton("View All Bookings For Customer");
		btnBookingsForCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TicketRequest ticketRequst = new TicketRequest();
					Long travellerId = (Long) travellersTable.getModel().getValueAt(travellersTable.getSelectedRow(), 0);
					List<SellTicketModel> tickets = ticketRequst.getAllTickets(null, null, travellerId);
					if(tickets.size() > 0) {
						bookingTable = Table.createTable(tickets);
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(0));
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(3));
						bookingTable.removeColumn(bookingTable.getColumnModel().getColumn(4));
					}
					else
						bookingTable = new JTable();

					bookingScrollPane.setViewportView(bookingTable);

				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "Something went wrong with your booking! Please try again!");
				}
			}
		});
		btnBookingsForCustomer.setBounds(333, 382, 234, 25);
		panel.add(btnBookingsForCustomer);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Add Bus", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblFrom_1 = new JLabel("From");
		lblFrom_1.setBounds(12, 13, 56, 16);
		panel_1.add(lblFrom_1);
		
		addFromTextField = new JTextField();
		addFromTextField.setBounds(48, 10, 116, 22);
		panel_1.add(addFromTextField);
		addFromTextField.setColumns(10);
		
		JLabel lblTo_1 = new JLabel("To");
		lblTo_1.setBounds(176, 13, 56, 16);
		panel_1.add(lblTo_1);
		
		addToTextField = new JTextField();
		addToTextField.setBounds(200, 10, 116, 22);
		panel_1.add(addToTextField);
		addToTextField.setColumns(10);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setBounds(328, 13, 56, 16);
		panel_1.add(lblDate_1);
		
		addDateTextField = new JTextField();
		addDateTextField.setBounds(364, 10, 116, 22);
		panel_1.add(addDateTextField);
		addDateTextField.setColumns(10);
		
		JLabel lblNumberOfSeats_1 = new JLabel("Number Of Seats");
		lblNumberOfSeats_1.setBounds(492, 13, 116, 16);
		panel_1.add(lblNumberOfSeats_1);
		
		addSeatNrTextField = new JTextField();
		addSeatNrTextField.setBounds(600, 10, 116, 22);
		panel_1.add(addSeatNrTextField);
		addSeatNrTextField.setColumns(10);
		
		addBusScrollPane = new JScrollPane();
		addBusScrollPane.setBounds(23, 53, 957, 112);
		panel_1.add(addBusScrollPane);
		
		addBusTable = new JTable();
		addBusScrollPane.setViewportView(addBusTable);
		
		addStationScrollPane = new JScrollPane();
		addStationScrollPane.setBounds(23, 232, 957, 112);
		panel_1.add(addStationScrollPane);
		
		
		StopsRequest stopsRequest = new StopsRequest();
		List<StopsModel> stops = stopsRequest.getAllStops();
		addStationTable = Table.createTable(stops);
		addStationTable.removeColumn(addStationTable.getColumnModel().getColumn(0));
		addStationScrollPane.setViewportView(addStationTable);
		
		JLabel lblFare = new JLabel("Fare");
		lblFare.setBounds(26, 203, 56, 16);
		panel_1.add(lblFare);
		
		textField = new JTextField();
		textField.setBounds(63, 197, 116, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblHour = new JLabel("Hour");
		lblHour.setBounds(212, 200, 56, 16);
		panel_1.add(lblHour);
		
		textField_1 = new JTextField();
		textField_1.setBounds(250, 197, 116, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		addRouteScrollPane = new JScrollPane();
		addRouteScrollPane.setBounds(23, 407, 957, 112);
		panel_1.add(addRouteScrollPane);
		
		addRouteTable = new JTable();
		addRouteScrollPane.setViewportView(addRouteTable);
		
		JButton btnAddStation = new JButton("Add Station");
		btnAddStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusRouteRequest routeRequest = new BusRouteRequest();
					
					Double fare = Double.parseDouble(textField.getText());
					String hour = textField_1.getText();
					Long busId = (Long) addBusTable.getModel().getValueAt(addBusTable.getSelectedRow(), 0);
					Long stopId = (Long) addStationTable.getModel().getValueAt(addStationTable.getSelectedRow(), 0);
					
					BusRouteModel route = new BusRouteModel(busId, stopId, hour, fare);
					
					routeRequest.saveRoute(route);
					
					List<BusRouteModel> routes = routeRequest.getAllRoutes(busId, null);
				
					if(routes.size() > 0) {
						addRouteTable = Table.createTable(routes);
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteScrollPane.setViewportView(addRouteTable);
					}
					else {
						addRouteTable = new JTable();
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteScrollPane.setViewportView(addRouteTable);
					}
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnAddStation.setBounds(26, 357, 138, 25);
		panel_1.add(btnAddStation);
		
		JButton btnAddBus = new JButton("Add Bus");
		btnAddBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusRequest busRequest = new BusRequest();
					
					String date = addDateTextField.getText();
					String fromCity = addFromTextField.getText();
					String toCity = addToTextField.getText();
					int numberOfSeats = Integer.parseInt(addSeatNrTextField.getText());
					Long agencyId = busAgency.getBusAgencyId();
					
					BusModel busModel = new BusModel(fromCity, toCity, date, numberOfSeats, agencyId);
					
					busRequest.saveBus(busModel);
					
					List<BusModel> busses = busRequest.getAllBuses(agencyId);
						
					if(busses.size() > 0) {
						addBusTable = Table.createTable(busses);
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(0));
						addBusScrollPane.setViewportView(addBusTable);
					}
					else {
						addBusTable = new JTable();
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(0));
						addBusScrollPane.setViewportView(addBusTable);
					}
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnAddBus.setBounds(740, 9, 97, 25);
		panel_1.add(btnAddBus);
		
		JButton btnViewAllBuses = new JButton("View All Buses");
		btnViewAllBuses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BusRequest busRequest = new BusRequest();
					TravellerRequest travellerRequst = new TravellerRequest();
					
					List<BusModel> busses = busRequest.getAllBuses(busAgency.getBusAgencyId());
					
					List<TravellerModel> travellers = travellerRequst.getAllTravellers(null);
					
					if(travellers.size() > 0) {
						addBusTable = Table.createTable(busses);
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(0));
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(4));
					}
					else {
						addBusTable = new JTable();
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(0));
						addBusTable.removeColumn(addBusTable.getColumnModel().getColumn(4));
					}
					addBusScrollPane.setViewportView(addBusTable);
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnViewAllBuses.setBounds(849, 9, 116, 25);
		panel_1.add(btnViewAllBuses);
		
		JButton btnViewAllStations = new JButton("View All Stations");
		btnViewAllStations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BusRouteRequest routeRequest = new BusRouteRequest();
			
					Long busId = (Long) addBusTable.getModel().getValueAt(addBusTable.getSelectedRow(), 0);

					List<BusRouteModel> routes = routeRequest.getAllRoutes(busId, null);
				
					if(routes.size() > 0) {
						addRouteTable = Table.createTable(routes);
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteScrollPane.setViewportView(addRouteTable);
					}
					else {
						addRouteTable = new JTable();
						addRouteTable.removeColumn(addRouteTable.getColumnModel().getColumn(0));
						addRouteScrollPane.setViewportView(addRouteTable);
					}
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "No busses available!");
				}
			}
		});
		btnViewAllStations.setBounds(200, 357, 138, 25);
		panel_1.add(btnViewAllStations);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Account", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setBounds(44, 47, 111, 16);
		panel_2.add(lblCompanyName);
		
		accountCompanyNameTextField = new JTextField();
		accountCompanyNameTextField.setBounds(160, 44, 209, 22);
		panel_2.add(accountCompanyNameTextField);
		accountCompanyNameTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(44, 156, 85, 16);
		panel_2.add(lblEmail);
		
		accountEmailTextField = new JTextField();
		accountEmailTextField.setBounds(160, 153, 209, 22);
		panel_2.add(accountEmailTextField);
		accountEmailTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(44, 274, 85, 16);
		panel_2.add(lblPassword);
		
		JButton btnUpdateAccountInformation = new JButton("Update Account");
		btnUpdateAccountInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String companyName = null;
				String email = null;
				String password = null;
				try {
					BusAgencyRequest request = new BusAgencyRequest();
					
					if(!accountCompanyNameTextField.getText().isEmpty()) {
						companyName = accountCompanyNameTextField.getText();
					}
					
					if(!accountEmailTextField.getText().isEmpty()) {
						email = accountEmailTextField.getText();
					}
				
					if(!String.valueOf(accountPasswordTextField.getPassword()).isEmpty()) {
						password = String.valueOf(accountPasswordTextField.getPassword());
					}
					
					BusAgencyModel newAgency = new BusAgencyModel(companyName, email, password);
					request.updateAgency(busAgency.getBusAgencyId(), newAgency);
					
					JOptionPane.showMessageDialog(null, "Your information has been successfully updated!");
				} catch(Exception err) {
					JOptionPane.showMessageDialog(null, "There are no assignments for this laboratory!");
				}
			}
		});
		btnUpdateAccountInformation.setBounds(83, 410, 151, 25);
		panel_2.add(btnUpdateAccountInformation);
		
		accountPasswordTextField = new JPasswordField();
		accountPasswordTextField.setBounds(160, 271, 209, 22);
		panel_2.add(accountPasswordTextField);
	}
}
