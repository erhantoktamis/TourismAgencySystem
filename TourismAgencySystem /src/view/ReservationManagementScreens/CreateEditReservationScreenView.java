package view.ReservationManagementScreens;

import business.ReservationManagementBussinessController;
import business.ShowAlertController;
import entity.HotelModel;
import entity.ReservationModel;
import entity.RoomModel;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateEditReservationScreenView extends JFrame {
    private JPanel mainContainer;
    private JTextField textFieldName;
    private JTextField textFieldPhone;
    private JTextField textFieldCheckInDate;
    private JTextField textFieldIdentification;
    private JTextField textFieldEmail;
    private JTextField textFieldCheckOutDate;
    private JButton backButton;
    private JButton saveButton;
    private JLabel titleLabel;
    private JTextField textFieldAdult;
    private JTextField textFieldChild;
    private JLabel priceLabel;
    private ReservationModel selectedReservation;
    private HotelModel selectedHotel;
    private RoomModel selectedRoom;

    private Integer adultPrice = 0;
    private Integer childPrice = 0;

    public CreateEditReservationScreenView(HotelModel hotelModel, RoomModel roomModel) {
        this.selectedHotel = hotelModel;
        this.selectedRoom = roomModel;
        setUIAttributes("Create Reservation");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationModel reservationModel = new ReservationModel();
                reservationModel.setReservationPhoneNumber(textFieldPhone.getText());
                reservationModel.setReservationIdentificationNumber(textFieldIdentification.getText());
                reservationModel.setReservationEmail(textFieldEmail.getText());
                reservationModel.setReservationFullName(textFieldName.getText());
                reservationModel.setReservationCheckInDate(textFieldCheckInDate.getText());
                reservationModel.setReservationCheckOutDate(textFieldCheckOutDate.getText());
                reservationModel.setReservationAdultCount(Integer.valueOf(textFieldAdult.getText()));
                reservationModel.setReservationChildCount(Integer.valueOf(textFieldChild.getText()));
                reservationModel.setReservationTotalPrice(adultPrice+childPrice);

                if(ReservationManagementBussinessController.instance.addNewReservation(reservationModel, selectedHotel, selectedRoom)) {
                    dispose();
                }
            }
        });
        textFieldAdult.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculateTotalPrice();
            }
        });
        textFieldChild.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculateTotalPrice();
            }

        });
        textFieldCheckOutDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculateTotalPrice();
            }

        });
        textFieldCheckInDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculateTotalPrice();
            }
        });
    }
    public CreateEditReservationScreenView(ReservationModel reservationModel ,HotelModel hotelModel, RoomModel roomModel) {
        this.selectedReservation = reservationModel;
        this.selectedHotel = hotelModel;
        this.selectedRoom = roomModel;
        setEditUIAttributes();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationModel reservationModel = new ReservationModel();
                reservationModel.setReservationId(selectedReservation.getReservationId());
                reservationModel.setReservationPhoneNumber(textFieldPhone.getText());
                reservationModel.setReservationIdentificationNumber(textFieldIdentification.getText());
                reservationModel.setReservationEmail(textFieldEmail.getText());
                reservationModel.setReservationFullName(textFieldName.getText());
                reservationModel.setReservationCheckInDate(textFieldCheckInDate.getText());
                reservationModel.setReservationCheckOutDate(textFieldCheckOutDate.getText());
                reservationModel.setReservationAdultCount(Integer.valueOf(textFieldAdult.getText()));
                reservationModel.setReservationChildCount(Integer.valueOf(textFieldChild.getText()));

                if(ReservationManagementBussinessController.instance.updateReservation(reservationModel, selectedHotel, selectedRoom)) {
                    dispose();
                }
            }
        });

        textFieldAdult.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculatePrice();
            }

            public void calculatePrice() {
                if(!textFieldAdult.getText().trim().isEmpty() && !textFieldAdult.getText().isEmpty()) {
                    Integer price = Integer.valueOf(textFieldAdult.getText()) * Integer.valueOf(roomModel.getRoomAdultPrice());
                    adultPrice = price;
                }
                else {
                    adultPrice = 0;
                }
                priceLabel.setText("Total Price:"+String.valueOf(adultPrice+childPrice));
            }
        });
        textFieldChild.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                calculatePrice();
            }

            public void calculatePrice() {
                if(!textFieldChild.getText().isEmpty() && !textFieldChild.getText().trim().isEmpty()) {
                    Integer price = Integer.valueOf(textFieldChild.getText()) * Integer.valueOf(roomModel.getRoomChildPrice());
                    childPrice = price;
                }
                else {
                    childPrice = 0;
                }
                priceLabel.setText("Total Price:"+String.valueOf(adultPrice+childPrice));
            }
        });
    }

    public void calculateTotalPrice() {
        Integer totalPrice = 0;
        if(!textFieldAdult.getText().trim().isEmpty() && !textFieldAdult.getText().isEmpty()) {
            Integer price = Integer.valueOf(textFieldAdult.getText()) * Integer.valueOf(this.selectedRoom.getRoomAdultPrice());
            adultPrice = price;
            totalPrice = adultPrice;
        }else {
            adultPrice = 0;
        }

        if(!textFieldChild.getText().isEmpty() && !textFieldChild.getText().trim().isEmpty()) {
            Integer price = Integer.valueOf(textFieldChild.getText()) * Integer.valueOf(this.selectedRoom.getRoomChildPrice());
            childPrice = price;
            totalPrice += childPrice;
        }else {
            childPrice = 0;
        }

        if(!textFieldCheckInDate.getText().isEmpty() && !textFieldCheckOutDate.getText().isEmpty()) {
            Integer dayCount = 0;
            Integer monthCount = 0;
            Integer yearCount = 0;
            String inString = textFieldCheckInDate.getText();
            String outString = textFieldCheckOutDate.getText();
            String[] inDate = inString.split("-");
            String[] outDate = outString.split("-");

            if(inDate.length > 2 && outDate.length > 2 ){
                Integer inYear = Integer.valueOf(inDate[0]);
                Integer inMonth = Integer.valueOf(inDate[1]);
                Integer inDay = Integer.valueOf(inDate[2]);

                Integer outYear = Integer.valueOf(outDate[0]);
                Integer outMonth = Integer.valueOf(outDate[1]);
                Integer outDay = Integer.valueOf(outDate[2]);
                if(outYear-inYear > 0) {
                    yearCount = outYear-inYear;
                }
                if (outMonth-inMonth > 0) {
                    monthCount = outMonth-inMonth;
                }
                if (outDay-inDay>0) {
                    dayCount = outDay-inDay;
                }

                if(yearCount > 0 || monthCount > 1 || (monthCount == 1 && dayCount > 0)){
                    ShowAlertController.instance.showAlertWithText("Please dont enter bigger than 1 month ");
                } else{
                    totalPrice = (adultPrice+childPrice)*dayCount;
                }
            }
        }

        priceLabel.setText("Total Price:"+String.valueOf(totalPrice));
    }
    private void setEditUIAttributes() {
        setUIAttributes("Edit Reservation");

        textFieldName.setText(this.selectedReservation.getReservationFullName());
        textFieldEmail.setText(this.selectedReservation.getReservationEmail());
        textFieldPhone.setText(this.selectedReservation.getReservationPhoneNumber());
        textFieldIdentification.setText(this.selectedReservation.getReservationIdentificationNumber());
        textFieldChild.setText(this.selectedReservation.getReservationChildCount().toString());
        textFieldAdult.setText(this.selectedReservation.getReservationAdultCount().toString());
        textFieldCheckInDate.setText(this.selectedReservation.getReservationCheckInDateString());
        textFieldCheckOutDate.setText(this.selectedReservation.getReservationCheckOutDateString());
    }
    private void setUIAttributes(String title) {
        this.add(mainContainer);
        this.setSize(400,400);
        this.setTitle(title);
        titleLabel.setText(title);
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
    }
}
