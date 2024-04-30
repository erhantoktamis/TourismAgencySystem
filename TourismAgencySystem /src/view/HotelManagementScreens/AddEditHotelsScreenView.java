package view.HotelManagementScreens;

import business.HotelManagementBusinessController;
import entity.FacilityModel;
import entity.HotelModel;
import entity.PensionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;

public class AddEditHotelsScreenView extends JFrame{
    private JPanel mainContainer;
    private JTextField nameTextfield;
    private JComboBox facilityComboBox;
    private JButton saveButton;
    private JButton backButton;
    private JTextField addressTextfield;
    private JTextField phoneTextfield;
    private JTextField emailTextfield;
    private JButton pensionAddButton;
    private JButton facilityAddButton;
    private JList facilityList;
    private JList pensionTypeList;
    private JComboBox pensionComboBox;
    private JLabel titleLabel;
    private JTextField starCountTextfield;
    private JButton resetListFacilityButton;
    private JButton resetListPensionButton;
    private HotelModel selectedHotelModel;

    private ArrayList<FacilityModel> facilityErasableListArray;
    private ArrayList<PensionModel> pensionErasableListArray;
    private ArrayList<String> facilityNamesListArray;
    private ArrayList<String> pensionNamesListArray;

    private ArrayList<Integer> facilityIdsListArray;
    private ArrayList<Integer> pensionIdsListArray;

    public AddEditHotelsScreenView() {
        setUIAttributes("Add New Hotel");


        facilityAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(facilityComboBox.getSelectedIndex() != -1) {
                    facilityNamesListArray.add(facilityComboBox.getSelectedItem().toString());
                    facilityIdsListArray.add(facilityErasableListArray.get(facilityComboBox.getSelectedIndex()).getFacilityId());
                    facilityList.setListData(facilityNamesListArray.toArray());
                    facilityErasableListArray.remove(facilityComboBox.getSelectedIndex());
                    facilityComboBox.removeAllItems();
                    updateComboBoxes();

                }
            }
        });
        pensionAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pensionComboBox.getSelectedIndex() != -1) {
                    pensionNamesListArray.add(pensionComboBox.getSelectedItem().toString());
                    pensionIdsListArray.add(pensionErasableListArray.get(pensionComboBox.getSelectedIndex()).getPensionId());
                    pensionTypeList.setListData(pensionNamesListArray.toArray());
                    pensionErasableListArray.remove(pensionComboBox.getSelectedIndex());
                    pensionComboBox.removeAllItems();
                    updateComboBoxes();
                }
            }
        });

        resetListFacilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer size = facilityNamesListArray.size();
                for (Integer i = 0; i < size; i++) {
                    facilityNamesListArray.remove(0);
                }
                DefaultListModel model = new DefaultListModel();
                model.clear();
                facilityList.setModel(model);

                facilityErasableListArray = HotelManagementBusinessController.instance.getAllFacilities();
                updateComboBoxes();
            }
        });
        resetListPensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer size = pensionNamesListArray.size();
                for (Integer i = 0; i < size; i++) {
                    pensionNamesListArray.remove(0);
                }
                DefaultListModel model = new DefaultListModel();
                model.clear();
                pensionTypeList.setModel(model);
                pensionErasableListArray = HotelManagementBusinessController.instance.getAllPensionDatas();
                updateComboBoxes();
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelModel model = new HotelModel();
                model.setHotelName(nameTextfield.getText());
                model.setHotelAddress(addressTextfield.getText());
                model.setHotelStar(Integer.valueOf(starCountTextfield.getText().trim().toString()));
                model.setHotelPhone(phoneTextfield.getText());
                model.setHotelEmail(emailTextfield.getText());
                model.setHotelName(nameTextfield.getText());
                model.setHotelFacilityResourcesIds(facilityIdsListArray);
                model.setHotelPensionTypesIds(pensionIdsListArray);

                if(HotelManagementBusinessController.instance.addNewHotel(model))
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
    public AddEditHotelsScreenView(HotelModel hotelModel) {
        this.selectedHotelModel = hotelModel;
        setEditScreenAttributes();


        facilityAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(facilityComboBox.getSelectedIndex() != -1) {
                    facilityNamesListArray.add(facilityComboBox.getSelectedItem().toString());
                    facilityIdsListArray.add(facilityErasableListArray.get(facilityComboBox.getSelectedIndex()).getFacilityId());
                    facilityList.setListData(facilityNamesListArray.toArray());
                    facilityErasableListArray.remove(facilityComboBox.getSelectedIndex());
                    facilityComboBox.removeAllItems();
                    updateComboBoxes();

                }
            }
        });
        pensionAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pensionComboBox.getSelectedIndex() != -1) {
                    pensionNamesListArray.add(pensionComboBox.getSelectedItem().toString());
                    pensionIdsListArray.add(pensionErasableListArray.get(pensionComboBox.getSelectedIndex()).getPensionId());
                    pensionTypeList.setListData(pensionNamesListArray.toArray());
                    pensionErasableListArray.remove(pensionComboBox.getSelectedIndex());
                    pensionComboBox.removeAllItems();
                    updateComboBoxes();
                }
            }
        });

        resetListFacilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer size = facilityNamesListArray.size();
                for (Integer i = 0; i < size; i++) {
                    facilityNamesListArray.remove(0);
                }
                DefaultListModel model = new DefaultListModel();
                model.clear();
                facilityList.setModel(model);

                facilityErasableListArray = HotelManagementBusinessController.instance.getAllFacilities();
                updateComboBoxes();
            }
        });
        resetListPensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer size = pensionNamesListArray.size();
                for (Integer i = 0; i < size; i++) {
                    pensionNamesListArray.remove(0);
                }
                DefaultListModel model = new DefaultListModel();
                model.clear();
                pensionTypeList.setModel(model);
                pensionErasableListArray = HotelManagementBusinessController.instance.getAllPensionDatas();
                updateComboBoxes();
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelModel model = new HotelModel();
                model.setHotelId(selectedHotelModel.getHotelId());
                model.setHotelName(nameTextfield.getText());
                model.setHotelAddress(addressTextfield.getText());
                model.setHotelStar(Integer.valueOf(starCountTextfield.getText().trim().toString()));
                model.setHotelPhone(phoneTextfield.getText());
                model.setHotelEmail(emailTextfield.getText());
                model.setHotelName(nameTextfield.getText());
                model.setHotelFacilityResourcesIds(facilityIdsListArray);
                model.setHotelPensionTypesIds(pensionIdsListArray);

                if(HotelManagementBusinessController.instance.updateHotel(model))
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

    private void updateComboBoxes() {
        pensionComboBox.removeAllItems();
        facilityComboBox.removeAllItems();
        for (PensionModel pensionModel : this.pensionErasableListArray) {
            pensionComboBox.addItem(pensionModel.getPensionName());
        }
        for (FacilityModel facility : this.facilityErasableListArray) {
            facilityComboBox.addItem(facility.getFacilityName());
        }
    }
    private void setUIAttributes(String title) {
        this.add(mainContainer);
        this.setSize(600,600);
        this.setTitle(title);
        titleLabel.setText(title);
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
        facilityNamesListArray = new ArrayList<>();
        pensionNamesListArray = new ArrayList<>();
        facilityIdsListArray = new ArrayList<>();
        pensionIdsListArray = new ArrayList<>();

        this.pensionErasableListArray = HotelManagementBusinessController.instance.getAllPensionDatas();
        this.facilityErasableListArray = HotelManagementBusinessController.instance.getAllFacilities();
        updateComboBoxes();
    }
    private void setEditScreenAttributes() {
        setUIAttributes("Edit Hotel");

        saveButton.setText("Update");
        nameTextfield.setText(this.selectedHotelModel.getHotelName());
        addressTextfield.setText(this.selectedHotelModel.getHotelAddress());
        emailTextfield.setText(this.selectedHotelModel.getHotelEmail());
        phoneTextfield.setText(this.selectedHotelModel.getHotelPhone());
        starCountTextfield.setText(this.selectedHotelModel.getHotelStar().toString());

        ArrayList<FacilityModel> temp = facilityErasableListArray;
        ArrayList<String> datas = new ArrayList<>();
        for (Integer idItem: selectedHotelModel.getHotelFacilityResourcesIds()) {
            int index = 0;
            for (int i = 0; i < temp.size(); i++) {
                if(temp.get(i).getFacilityId() == idItem) {
                    datas.add(temp.get(i).getFacilityName());
                    facilityErasableListArray.remove(i);
                    break;
                }
            }
        }
        facilityList.setListData(datas.toArray());

        datas = new ArrayList<>();
        ArrayList<PensionModel> tempPension = pensionErasableListArray;
        for (Integer idItem: selectedHotelModel.getHotelPensionTypesIds()) {
            int index = 0;
            for (PensionModel pensionModel: tempPension) {
                if(pensionModel.getPensionId() == idItem) {
                    datas.add(pensionModel.getPensionName());
                    pensionErasableListArray.remove(index);
                    break;
                }
                index++;
            }
        }
        pensionTypeList.setListData(datas.toArray());
        updateComboBoxes();
    }

}
