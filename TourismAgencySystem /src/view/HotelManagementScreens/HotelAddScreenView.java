package view.HotelManagementScreens;

import business.HotelManagementBusinessController;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class HotelAddScreenView extends JFrame {
    private JPanel mainContainer;
    private JComboBox hotelNamesCombobox;
    private JComboBox seasonsCombobox;
    private JComboBox roomTypeCombox;
    private JTextField roomNumberTextField;
    private JTextField stockTextField;
    private JButton saveButton;
    private JTextField adultPriceTextfield;
    private JTextField childPriceTextfield;
    private JTextField bedTextField;
    private JTextField m2TextField;
    private JComboBox tvCombobox;
    private JComboBox miniBar;
    private JComboBox ps5;
    private JComboBox safeVault;
    private JComboBox projector;
    private JComboBox pensionTypesCombo;
    private JButton backButton;
    private JLabel titleLabel;
    private ArrayList<HotelModel> hotelModels;
    private ArrayList<SeasonModel> seasonModels;
    private ArrayList<RoomModel> roomModels;
    private ArrayList<PensionModel> pensionModels;
    private ArrayList<HotelRoomTypes> hotelRoomTypes;

    private HotelModel selectedHotelModel;
    private ArrayList<SeasonModel> selectedSeasonModel;
    private ArrayList<RoomModel> selectedRoomModel;
    private ArrayList<PensionModel> selectedPensionModel;
    private ArrayList<HotelRoomTypes> selectedHotelRoomType;


    private RoomModel editScreensSelectedRoomModel;
    public HotelAddScreenView(HotelModel selectedHotelModel, RoomModel selectedRoomModel, ArrayList<SeasonModel> seasonModels, ArrayList<PensionModel> pensionModels) {
        this.editScreensSelectedRoomModel = selectedRoomModel;
        this.selectedHotelModel = selectedHotelModel;
        this.seasonModels = seasonModels;
        this.pensionModels = pensionModels;
        setEditUIAttributes();


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomModel updateRoomScreenModel = getScreenPropertiesDatas();
                updateRoomScreenModel.setRoomId(editScreensSelectedRoomModel.getRoomId());
                if (HotelManagementBusinessController.instance.updateHotelRoom(updateRoomScreenModel))
                    dispose();
            }
        });
    }

    public HotelAddScreenView(ArrayList<HotelModel> hotelModels, ArrayList<SeasonModel> seasonModels, ArrayList<RoomModel> roomModels, ArrayList<PensionModel> pensionModels ) {
        this.hotelModels = hotelModels;
        this.seasonModels = seasonModels;
        this.roomModels = roomModels;
        this.pensionModels = pensionModels;
        setAddNewUIAttributes();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RoomModel addRoomScreenModel = getScreenPropertiesDatas();

                if(HotelManagementBusinessController.instance.addHotelRoom(addRoomScreenModel)) {
                    dispose();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        hotelNamesCombobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(hotelNamesCombobox.getSelectedIndex() != -1) {
                    selectedHotelModel = hotelModels.get(hotelNamesCombobox.getSelectedIndex());
                    seasonsCombobox.removeAllItems();
                    pensionTypesCombo.removeAllItems();

                    selectedSeasonModel = new ArrayList<>();
                    for(SeasonModel season: seasonModels) {
                        if(season.getSeasonHotelId() == selectedHotelModel.getHotelId()){
                            seasonsCombobox.addItem(season.getSeasonName());
                            selectedSeasonModel.add(season);
                        }
                    }
                    selectedPensionModel = new ArrayList<>();
                    for(PensionModel pension: pensionModels) {
                        for (String hotelPensionName:selectedHotelModel.getHotelPensionTypes()) {
                            if(pension.getPensionName().equals(hotelPensionName)) {
                                pensionTypesCombo.addItem(pension.getPensionName());
                                selectedPensionModel.add(pension);
                            }
                        }
                    }
                }



            }
        });
    }

    private void setAddNewUIAttributes() {
        selectedSeasonModel = new ArrayList<>();
        selectedPensionModel = new ArrayList<>();
        selectedHotelRoomType = new ArrayList<>();
        selectedRoomModel = new ArrayList<>();
        this.add(mainContainer);
        this.setSize(900,600);
        this.setTitle("Add New Hotel");
        this.titleLabel.setText("Add New Hotel");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);

        this.hotelRoomTypes = HotelManagementBusinessController.instance.getAllHotelRoomTypesDatas();

        for (HotelModel hotel : this.hotelModels) {
            hotelNamesCombobox.addItem(hotel.getHotelName());
        }
        for(SeasonModel season: seasonModels) {
            if(season.getSeasonHotelId() == hotelModels.get(hotelNamesCombobox.getSelectedIndex()).getHotelId()){
                seasonsCombobox.addItem(season.getSeasonName());
                selectedSeasonModel.add(season);
            }
        }
        for(HotelRoomTypes room: this.hotelRoomTypes) {
            roomTypeCombox.addItem(room.getRoomName());
            selectedHotelRoomType.add(room);
        }
        this.selectedHotelModel = this.hotelModels.get(0);

        for(PensionModel pension: pensionModels) {
            for (String hotelPensionName:selectedHotelModel.getHotelPensionTypes()) {
                if(pension.getPensionName().equals(hotelPensionName)) {
                    pensionTypesCombo.addItem(pension.getPensionName());
                    selectedPensionModel.add(pension);
                }
            }
        }
    }

    private void setEditUIAttributes() {
        selectedSeasonModel = new ArrayList<>();
        selectedPensionModel = new ArrayList<>();
        selectedHotelRoomType = new ArrayList<>();
        selectedRoomModel = new ArrayList<>();
        this.add(mainContainer);
        this.setSize(900,600);
        this.setTitle("Edit Hotel");
        this.titleLabel.setText("Edit Hotel");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);

        this.hotelRoomTypes = HotelManagementBusinessController.instance.getAllHotelRoomTypesDatas();

        hotelNamesCombobox.addItem(selectedHotelModel.getHotelName());

        for(SeasonModel season: seasonModels) {
            if(season.getSeasonHotelId() == selectedHotelModel.getHotelId()){
                seasonsCombobox.addItem(season.getSeasonName());
                selectedSeasonModel.add(season);
                if(editScreensSelectedRoomModel.getRoomSeasonTypeId() == season.getSeasonId()) {
                    seasonsCombobox.setSelectedItem(season.getSeasonName());
                }
            }
        }

        for(HotelRoomTypes room: this.hotelRoomTypes) {
            roomTypeCombox.addItem(room.getRoomName());
            selectedHotelRoomType.add(room);
            if(editScreensSelectedRoomModel.getRoomTypeId() == room.getRoomTypeId()) {
                roomTypeCombox.setSelectedItem(room.getRoomName());
            }
        }

        for(PensionModel pension: pensionModels) {
            for (String hotelPensionName : selectedHotelModel.getHotelPensionTypes()) {
                if(pension.getPensionName().equals(hotelPensionName)) {
                    pensionTypesCombo.addItem(pension.getPensionName());
                    selectedPensionModel.add(pension);
                }
            }
            if(editScreensSelectedRoomModel.getRoomPensionTypeId() == pension.getPensionId()) {
                pensionTypesCombo.setSelectedItem(pension.getPensionName());
            }
        }

        roomNumberTextField.setText(editScreensSelectedRoomModel.getRoomDoorNumbers());
        adultPriceTextfield.setText(editScreensSelectedRoomModel.getRoomAdultPrice());
        childPriceTextfield.setText(editScreensSelectedRoomModel.getRoomChildPrice());
        bedTextField.setText(editScreensSelectedRoomModel.getRoomBedNumber().toString());
        stockTextField.setText(editScreensSelectedRoomModel.getRoomStockCount().toString());
        m2TextField.setText(editScreensSelectedRoomModel.getRoomMeterSquare());

        tvCombobox.setSelectedIndex(editScreensSelectedRoomModel.getRoomHaveTVBool() == true ? 0 : 1);
        miniBar.setSelectedIndex(editScreensSelectedRoomModel.getRoomHaveMinibarBool() == true ? 0 : 1);
        ps5.setSelectedIndex(editScreensSelectedRoomModel.getRoomHavePS5Bool() == true ? 0 : 1);
        safeVault.setSelectedIndex(editScreensSelectedRoomModel.getRoomHaveVaultBool() == true ? 0 : 1);
        projector.setSelectedIndex(editScreensSelectedRoomModel.getRoomHaveProjectionBool() == true ? 0 : 1);

        saveButton.setText("Update");
    }

    private RoomModel getScreenPropertiesDatas() {
        RoomModel addRoomScreenModel = new RoomModel();
        addRoomScreenModel.setRoomSeasonName(seasonsCombobox.getSelectedItem().toString());
        addRoomScreenModel.setRoomTypeName(roomTypeCombox.getSelectedItem().toString());
        addRoomScreenModel.setRoomDoorNumbers(roomNumberTextField.getText());
        addRoomScreenModel.setRoomAdultPrice(adultPriceTextfield.getText());
        addRoomScreenModel.setRoomChildPrice(childPriceTextfield.getText());
        addRoomScreenModel.setRoomStockCount(Integer.valueOf(stockTextField.getText().trim()));
        addRoomScreenModel.setRoomBedNumber(Integer.valueOf(bedTextField.getText()));
        addRoomScreenModel.setRoomMeterSquare(m2TextField.getText());
        addRoomScreenModel.setRoomHaveTV(tvCombobox.getSelectedIndex() == 0 ? true : false);
        addRoomScreenModel.setRoomHaveMinibar(miniBar.getSelectedIndex() == 0 ? true : false);
        addRoomScreenModel.setRoomHavePS5(ps5.getSelectedIndex() == 0 ? true : false);
        addRoomScreenModel.setRoomHaveVault(safeVault.getSelectedIndex() == 0 ? true : false);
        addRoomScreenModel.setRoomHaveProjection(projector.getSelectedIndex() == 0 ? true : false);
        addRoomScreenModel.setRoomHotelId(editScreensSelectedRoomModel != null ? editScreensSelectedRoomModel.getRoomHotelId() : hotelModels.get(hotelNamesCombobox.getSelectedIndex()).getHotelId());
        addRoomScreenModel.setRoomTypeId(selectedHotelRoomType.get(roomTypeCombox.getSelectedIndex()).getRoomTypeId());
        addRoomScreenModel.setRoomSeasonTypeId(selectedSeasonModel.get(seasonsCombobox.getSelectedIndex()).getSeasonId());
        addRoomScreenModel.setRoomPensionTypeId(selectedPensionModel.get(pensionTypesCombo.getSelectedIndex()).getPensionId());
        addRoomScreenModel.setRoomPensionTypeName(pensionTypesCombo.getSelectedItem().toString());
        return addRoomScreenModel;
    }
}
