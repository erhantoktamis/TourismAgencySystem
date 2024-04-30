package view.ReservationManagementScreens;

import business.ReservationManagementBussinessController;
import business.ShowAlertController;
import entity.*;
import view.DashboardScreenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationManagementScreenView extends JFrame {
    private JPanel mainContainer;
    private JScrollPane scrollContainer;
    private JTabbedPane tabbedPane1;
    private JTable searchScreenTable;
    private JComboBox checkInYearCombo;
    private JComboBox cityComboBox;
    private JComboBox hotelNameComboBox;
    private JComboBox checkOutYearCombo;
    private JComboBox checkOutMonthCombo;
    private JComboBox checkInMonthCombo;
    private JComboBox checkInDayCombo;
    private JComboBox checkOutDayCombo;
    private JTable reservationsTable;
    private JButton searchButtonSearchScreen;
    private JButton listAllButtonSearchScreen;
    private JButton backButton;
    private JButton createReservationButton;
    private JButton editReservationButton;
    private JButton deleteReservationButton;
    private JButton listAllButtonReservationScreen;
    private UserModel activeUser;
    private ArrayList<HotelsSeasonsAndRoomsModel> hotelsRoomsSeasonsModel;
    private ArrayList<HotelModel> allHotels;
    private ArrayList<String> allHotelsCitiesStringArray;
    private ArrayList<ReservationModel> allReservations;
    private ArrayList<RoomModel> allRooms;
    private ArrayList<SeasonModel> allSeasons;

    public ReservationManagementScreenView(UserModel userModel) throws HeadlessException {
        this.activeUser = userModel;
        setUIAttributes();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardScreenView(activeUser).setVisible(true);
                dispose();

            }
        });
        searchButtonSearchScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        listAllButtonSearchScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelsRoomsSeasonsModel = ReservationManagementBussinessController.instance.getAllReservationHotelsAndRooms();
                updateSearchTable();
            }
        });
        createReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchScreenTable.getSelectedRow() >= 0) {
                    HotelsSeasonsAndRoomsModel selectedHotel = hotelsRoomsSeasonsModel.get(searchScreenTable.getSelectedRow());
                    RoomModel selectedRoom = new RoomModel();
                    for (RoomModel room : allRooms) {
                        if (room.getRoomHotelId() == selectedHotel.getHotelId()
                                && selectedHotel.getRoomId() == room.getRoomId()){
                            selectedRoom = room;
                            break;
                        }
                    }

                    new CreateEditReservationScreenView(selectedHotel,selectedRoom).setVisible(true);
                }
            }
        });
        cityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelsRoomsSeasonsModel = ReservationManagementBussinessController.instance.getSelectedHotelsRoomsAndSeasonByCityName(cityComboBox.getSelectedItem().toString());
                updateSearchTable();
            }
        });
        hotelNameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelsRoomsSeasonsModel = ReservationManagementBussinessController.instance.getSelectedHotelsRoomsAndSeasonByHotelName(hotelNameComboBox.getSelectedItem().toString());
                updateSearchTable();
            }
        });


        editReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(reservationsTable.getSelectedRow() >= 0){
                    ReservationModel selectedReservation = allReservations.get(reservationsTable.getSelectedRow());
                    RoomModel selectedRoom = new RoomModel();
                    HotelModel selectedHotel = new HotelModel();
                    for (RoomModel room : allRooms) {
                        for(HotelModel hotel: allHotels) {
                            if (selectedReservation.getReservationHotelId() == hotel.getHotelId()
                                    && room.getRoomHotelId() == hotel.getHotelId()){
                                selectedRoom = room;
                                selectedHotel = hotel;
                                break;
                            }
                        }

                    }
                    new CreateEditReservationScreenView(selectedReservation,selectedHotel,selectedRoom).setVisible(true);
                }

            }
        });
        deleteReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationModel selectedReservation = allReservations.get(reservationsTable.getSelectedRow());
                RoomModel selectedRoom = new RoomModel();
                if(reservationsTable.getSelectedRow() >= 0) {
                    for (RoomModel room : allRooms) {
                        for(HotelModel hotel: allHotels) {
                            if (selectedReservation.getReservationHotelId() == hotel.getHotelId()
                                    && room.getRoomHotelId() == hotel.getHotelId()){
                                selectedRoom = room;
                                break;
                            }
                        }

                    }
                    if(ReservationManagementBussinessController.instance.deleteReservation(selectedRoom, selectedReservation)) {
                        updateReservationTable();
                        ShowAlertController.instance.showAlertWithText("Reservation Successfully Deleted");
                    }
                }


            }
        });
        listAllButtonReservationScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allReservations = ReservationManagementBussinessController.instance.getAllReservations();
                updateReservationTable();
            }
        });
    }

    private void updateReservationTable() {
        DefaultTableModel dmHotels = (DefaultTableModel) this.reservationsTable.getModel();
        int rowCount = dmHotels.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmHotels.removeRow(i);
        }

        Object[] columnUser = {"Reservation Id", "Reservation Hotel Name", "Owners Full Name", "Owners Identication Number", "Owners Phone Number",
                "Owners E-mail", "Reservation Adult Count", "Reservation Child Count", "Reservation Check In Date", "Reservation Check Out Date" };

        DefaultTableModel mdlTableHotelsTemp = new DefaultTableModel();
        mdlTableHotelsTemp.setColumnIdentifiers(columnUser);


        for (ReservationModel reservationModel : this.allReservations){
            String hotelName ="";
            for (HotelModel hotel : allHotels) {
                if(hotel.getHotelId() == reservationModel.getReservationHotelId()) {
                    hotelName = hotel.getHotelName();
                    break;
                }
            }
            Object[] row = {
                    reservationModel.getReservationId(),
                    hotelName,
                    reservationModel.getReservationFullName(),
                    reservationModel.getReservationIdentificationNumber(),
                    reservationModel.getReservationPhoneNumber(),
                    reservationModel.getReservationEmail(),
                    reservationModel.getReservationAdultCount(),
                    reservationModel.getReservationChildCount(),
                    reservationModel.getReservationCheckInDateString(),
                    reservationModel.getReservationCheckOutDateString()
            };
            mdlTableHotelsTemp.addRow(row);

        }

        this.reservationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.reservationsTable.setAutoscrolls(false);

        this.reservationsTable.setModel(mdlTableHotelsTemp);
        this.reservationsTable.setEnabled(true);
        this.reservationsTable.setVisible(true);
        this.reservationsTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = this.reservationsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(60);
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setPreferredWidth(200);
        columnModel.getColumn(8).setPreferredWidth(600);
        columnModel.getColumn(9).setPreferredWidth(550);
        this.reservationsTable.setColumnModel(columnModel);
    }
    private void updateSearchTable() {
        DefaultTableModel dmHotels = (DefaultTableModel) this.searchScreenTable.getModel();
        int rowCount = dmHotels.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmHotels.removeRow(i);
        }

        Object[] columnUser = {"Hotel ID", "Room ID", "Season ID", "Hotel Name", "Hotel Address", "Season Start Date", "Season End Date", "Room Type", "Room Stock", "Adult Price", "Child Price","Hotel Facility Resources",
                "Hotel Pension Types" };

        DefaultTableModel mdlTableHotelsTemp = new DefaultTableModel();
        mdlTableHotelsTemp.setColumnIdentifiers(columnUser);

        for (HotelsSeasonsAndRoomsModel model : hotelsRoomsSeasonsModel){
            Object[] row = {
                    model.getHotelId(),
                    model.getRoomId(),
                    model.getSeasonId(),
                    model.getHotelName(),
                    model.getHotelAddress(),
                    model.getSeasonStartDate(),
                    model.getSeasonEndDate(),
                    model.getRoomName(),
                    model.getRoomStock(),
                    model.getRoomAdultPrice(),
                    model.getRoomChildPrice(),
                    model.getHotelFacilityResources(),
                    model.getHotelPensionTypes()
            };
            mdlTableHotelsTemp.addRow(row);

        }

        this.searchScreenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.searchScreenTable.setAutoscrolls(false);

        this.searchScreenTable.setModel(mdlTableHotelsTemp);
        this.searchScreenTable.setEnabled(true);
        this.searchScreenTable.setVisible(true);
        this.searchScreenTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = this.searchScreenTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(130);
        columnModel.getColumn(4).setPreferredWidth(130);
        columnModel.getColumn(5).setPreferredWidth(110);
        columnModel.getColumn(6).setPreferredWidth(110);
        columnModel.getColumn(7).setPreferredWidth(110);
        columnModel.getColumn(8).setPreferredWidth(60);
        columnModel.getColumn(8).setPreferredWidth(70);
        columnModel.getColumn(10).setPreferredWidth(70);
        columnModel.getColumn(11).setPreferredWidth(450);
        columnModel.getColumn(12).setPreferredWidth(450);
        this.searchScreenTable.setColumnModel(columnModel);
    }

    private void  setUIAttributes(){
        this.add(mainContainer);
        this.setSize(1200,800);
        this.setTitle("Reservation Management");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);

        this.hotelsRoomsSeasonsModel = ReservationManagementBussinessController.instance.getAllReservationHotelsAndRooms();
        this.allHotels = ReservationManagementBussinessController.instance.getAllHotels();
        this.allHotelsCitiesStringArray = ReservationManagementBussinessController.instance.getAllHotelsCities();
        this.allReservations = ReservationManagementBussinessController.instance.getAllReservations();
        this.allRooms = ReservationManagementBussinessController.instance.getAllRooms();
        this.allSeasons = ReservationManagementBussinessController.instance.getAllSeasons();

        for (HotelModel hotel: allHotels) {
            hotelNameComboBox.addItem(hotel.getHotelName());
        }

        for (String hotel: allHotelsCitiesStringArray) {
            cityComboBox.addItem(hotel);
        }
        updateSearchTable();
        updateReservationTable();
    }
}
