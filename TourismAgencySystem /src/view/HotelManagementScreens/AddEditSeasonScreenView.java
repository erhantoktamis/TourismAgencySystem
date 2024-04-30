package view.HotelManagementScreens;

import business.HotelManagementBusinessController;
import entity.HotelModel;
import entity.SeasonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEditSeasonScreenView extends JFrame {
    private JPanel mainContainer;
    private JTextField seasonNameTextField;
    private JButton saveButton;
    private JButton backButton;
    private JComboBox hotelNameComboBox1;
    private JLabel titleLabel;
    private JFormattedTextField startDateFormattedTextField;
    private JFormattedTextField endDateFormattedTextField;
    private SeasonModel seasonModel;
    private ArrayList<HotelModel> hotelModels;

    public AddEditSeasonScreenView(String hotelName, SeasonModel seasonModel) {
        this.seasonModel = seasonModel;

        setUIAttributes("Edit Season");
        setEditScreenAttributes(hotelName);
        setAddScreenAttributes(this.hotelModels);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeasonModel model = new SeasonModel();
                model.setSeasonId(seasonModel.getSeasonId());
                model.setSeasonName(seasonNameTextField.getText().trim());
                model.setSeasonStartDate(startDateFormattedTextField.getText().trim());
                model.setSeasonEndDate(endDateFormattedTextField.getText().trim());
                model.setSeasonHotelId(seasonModel.getSeasonHotelId());

                if(HotelManagementBusinessController.instance.updateSeason(seasonModel))
                    dispose();
            }
        });
    }
    public AddEditSeasonScreenView(ArrayList<HotelModel> hotelModels) {
        this.hotelModels = hotelModels;
        setUIAttributes("Add New Season");
        setAddScreenAttributes(this.hotelModels);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeasonModel model = new SeasonModel();
                model.setSeasonName(seasonNameTextField.getText().trim());
                model.setSeasonStartDate(startDateFormattedTextField.getText().trim());
                model.setSeasonEndDate(endDateFormattedTextField.getText().trim());
                model.setSeasonHotelId(hotelModels.get(hotelNameComboBox1.getSelectedIndex()).getHotelId());

                if(HotelManagementBusinessController.instance.addNewSeason(model))
                    dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
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
    private void setAddScreenAttributes(ArrayList<HotelModel> hotelModels) {
        for(HotelModel hotel : hotelModels) {
            hotelNameComboBox1.addItem(hotel.getHotelName());
        }
        saveButton.setText("Save");
    }
    private void setEditScreenAttributes(String hotelName) {
        hotelNameComboBox1.addItem(hotelName);
        hotelNameComboBox1.setSelectedItem(hotelName);
        seasonNameTextField.setText(this.seasonModel.getSeasonName());
        endDateFormattedTextField.setText(this.seasonModel.getSeasonEndDate());
        startDateFormattedTextField.setText(this.seasonModel.getSeasonStartDate());
        seasonNameTextField.setText(this.seasonModel.getSeasonName());

        saveButton.setText("Update");
    }
}
