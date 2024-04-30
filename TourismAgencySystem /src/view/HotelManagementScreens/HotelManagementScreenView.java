package view.HotelManagementScreens;

import business.HotelManagementBusinessController;
import entity.*;
import view.DashboardScreenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HotelManagementScreenView extends JFrame {
    private JPanel mainContainer;
    private JButton backButton;
    private JTable hotelsTable;
    private JButton newAddButtonRoom;
    private JButton editButtonRoom;
    private JButton deleteButton;
    private JTable seasonsTable;
    private JTable roomsTable;
    private JTable pensionsTable;
    private JTabbedPane tabbedPane1;
    private JPanel HotelsTab;
    private JPanel SeasonsTab;
    private JPanel RoomsTab;
    private JPanel PansionsTab;
    private JScrollPane scrollPanel;
    private JButton listAllButtonRoom;
    private JComboBox comboboxRoomHotelNames;
    private JButton editButtonPension;
    private JButton addNewButtonPension;
    private JButton listAllButtonPension;
    private JButton editButtonSeason;
    private JButton newAddButtonSeason;
    private JButton listAllButtonSeason;
    private JButton listAllButtonHotels;
    private JButton editButtonHotels;
    private JButton addNewButtonHotels;
    private JComboBox comboBoxHotelStars;
    private JComboBox comboBoxSeasonsNames;
    private JComboBox comboBoxSeasonHotelNames;
    private JButton backButtonPension;
    private UserModel controlActiveUser;
    private ArrayList<Integer> hotelStarCountArray;
    private ArrayList<HotelModel> hotelModels;
    private ArrayList<SeasonModel> seasonModels;
    private ArrayList<String> seasonNamesArray;
    private ArrayList<RoomModel> roomModels;
    private ArrayList<PensionModel> pensionModels;
    private Boolean comboboxSelectable = false;

    public HotelManagementScreenView(UserModel userModel) throws HeadlessException {
        controlActiveUser = userModel;
        setUIAttributes();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardScreenView(controlActiveUser).setVisible(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hotelsTable.getSelectedRow() != -1) {
                    HotelManagementBusinessController.instance.deleteHotel(hotelModels.get(hotelsTable.getSelectedRow()));
                    updateHotelsDataWithTable();
                } else if (seasonsTable.getSelectedRow() != -1) {
                    HotelManagementBusinessController.instance.deleteSeason(seasonModels.get(seasonsTable.getSelectedRow()));
                    updateSeasonsDataWithTable();
                } else if (roomsTable.getSelectedRow() != -1) {
                    HotelManagementBusinessController.instance.deleteRoom(roomModels.get(roomsTable.getSelectedRow()));
                    updateRoomDataWithTable();
                } else if (pensionsTable.getSelectedRow() != -1) {
                    HotelManagementBusinessController.instance.deletePension(pensionModels.get(pensionsTable.getSelectedRow()));
                    updatePensionDataWithTable();
                }
                updateSeasonComboBox();
            }
        });


        listAllButtonHotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboboxSelectable = false;
                updateHotelsDataWithTable();
                updateHotelsComboBox();
                updateSeasonComboBox();
                comboboxSelectable = true;
            }
        });
        addNewButtonHotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditHotelsScreenView().setVisible(true);
            }
        });
        editButtonHotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditHotelsScreenView(hotelModels.get(hotelsTable.getSelectedRow())).setVisible(true);
            }
        });
        comboBoxHotelStars.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBoxHotelStars.getSelectedIndex() != -1 && comboboxSelectable) {
                    comboboxSelectable = false;
                    hotelModels = HotelManagementBusinessController.instance.getHotelsByStarsCount(Integer.valueOf(comboBoxHotelStars.getSelectedItem().toString()));
                    updateDataHotelsTable(hotelModels);
                    comboboxSelectable = true;
                }
            }
        });


        comboboxRoomHotelNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboboxRoomHotelNames.getSelectedIndex() != -1 && comboboxSelectable) {
                    comboboxSelectable = false;
                    roomModels = HotelManagementBusinessController.instance.getSelectedHotelRooms(hotelModels.get(comboboxRoomHotelNames.getSelectedIndex()).getHotelId());
                    updateDataRoomsTable(roomModels);
                    comboboxSelectable = true;
                }
            }
        });
        newAddButtonRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HotelAddScreenView(hotelModels, seasonModels, roomModels, pensionModels).setVisible(true);
            }
        });
        editButtonRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelModel selectedHotel = new HotelModel();
                for (HotelModel hotel : hotelModels) {
                    if (hotel.getHotelId() == roomModels.get(roomsTable.getSelectedRow()).getRoomHotelId()) {
                        selectedHotel = hotel;
                    }
                }
                new HotelAddScreenView(selectedHotel, roomModels.get(roomsTable.getSelectedRow()), seasonModels, pensionModels).setVisible(true);
            }
        });
        listAllButtonRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomDataWithTable();
            }
        });


        addNewButtonPension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditPensionScreenView().setVisible(true);
            }
        });
        editButtonPension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditPensionScreenView(pensionModels.get(pensionsTable.getSelectedRow())).setVisible(true);
            }
        });
        listAllButtonPension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePensionDataWithTable();
            }
        });


        listAllButtonSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSeasonsDataWithTable();
                updateSeasonComboBox();
            }
        });
        newAddButtonSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEditSeasonScreenView(hotelModels).setVisible(true);
            }
        });
        editButtonSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelModel selectedHotel = new HotelModel();
                SeasonModel selectedSeason = seasonModels.get(seasonsTable.getSelectedRow());
                for (HotelModel hotel: hotelModels) {
                    if(hotel.getHotelId() == selectedSeason.getSeasonHotelId()) {
                        selectedHotel = hotel;
                    }
                }
                new AddEditSeasonScreenView(selectedHotel.getHotelName(), selectedSeason).setVisible(true);
            }
        });
        comboBoxSeasonsNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBoxSeasonHotelNames.getSelectedIndex() != -1 && comboboxSelectable) {
                    comboboxSelectable = false;
                    seasonModels = HotelManagementBusinessController.instance.getSeasonsBySeasonName(seasonNamesArray.get(comboBoxSeasonsNames.getSelectedIndex()));
                    updateDataSeasonsTable(seasonModels);
                }
            }
        });
        comboBoxSeasonHotelNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBoxSeasonHotelNames.getSelectedIndex() != -1 && comboboxSelectable) {
                    comboboxSelectable = false;
                    seasonModels = HotelManagementBusinessController.instance.getSeasonsByHotelId(hotelModels.get(comboBoxSeasonHotelNames.getSelectedIndex()).getHotelId());
                    updateDataSeasonsTable(seasonModels);
                }
            }
        });

    }

    private void getTableDatas() {
        comboboxSelectable = false;
        updateHotelsDataWithTable();
        updateSeasonsDataWithTable();
        updateRoomDataWithTable();
        updatePensionDataWithTable();
        comboboxSelectable = true;
    }


    private void updateHotelsDataWithTable() {
        this.hotelModels = HotelManagementBusinessController.instance.getAllHotelsDatas();
        updateDataHotelsTable(this.hotelModels);
    }
    private void updateDataHotelsTable(ArrayList<HotelModel> hotelModels) {
        DefaultTableModel dmHotels = (DefaultTableModel) this.hotelsTable.getModel();
        int rowCount = dmHotels.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmHotels.removeRow(i);
        }

        Object[] columnUser = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Email", "Hotel Phone", "Hotel Star", "Hotel Facility Resources", "Hotel Pension Types"};

        DefaultTableModel mdlTableHotelsTemp = new DefaultTableModel();
        mdlTableHotelsTemp.setColumnIdentifiers(columnUser);

        for (HotelModel userModel : hotelModels){
            Object[] row = {
                    userModel.getHotelId(),
                    userModel.getHotelName(),
                    userModel.getHotelAddress(),
                    userModel.getHotelEmail(),
                    userModel.getHotelPhone(),
                    userModel.getHotelStar(),
                    userModel.getHotelFacilityResources(),
                    userModel.getHotelPensionTypes()
            };
            mdlTableHotelsTemp.addRow(row);

        }

        this.hotelsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.hotelsTable.setAutoscrolls(false);

        this.hotelsTable.setModel(mdlTableHotelsTemp);
        this.hotelsTable.setEnabled(true);
        this.hotelsTable.setVisible(true);
        this.hotelsTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = this.hotelsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(60);
        columnModel.getColumn(6).setPreferredWidth(600);
        columnModel.getColumn(7).setPreferredWidth(550);
        this.hotelsTable.setColumnModel(columnModel);

    }
    private void updateHotelsComboBox() {
        comboboxSelectable = false;
        comboBoxHotelStars.removeAllItems();
        for (Integer star: hotelStarCountArray) {
            comboBoxHotelStars.addItem(star);
        }
        comboboxSelectable = true;
    }


    private void updateSeasonsDataWithTable() {
        this.seasonModels = HotelManagementBusinessController.instance.getAllSeasonsDatas();
        updateDataSeasonsTable(this.seasonModels);
    }
    private void updateDataSeasonsTable(ArrayList<SeasonModel> seasonModels) {
        DefaultTableModel dmSeasons = (DefaultTableModel) this.seasonsTable.getModel();
        int rowCount = dmSeasons.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmSeasons.removeRow(i);
        }

        Object[] columnUser = {"Seasons ID", "Seasons Name", "Seasons Start Date", "Seasons End Date", "Hotel Name", "Hotel ID"};

        DefaultTableModel mdlTableSeasons = new DefaultTableModel();
        mdlTableSeasons.setColumnIdentifiers(columnUser);

        for (SeasonModel seasonModel : seasonModels){
            Object[] row = {
                    seasonModel.getSeasonId(),
                    seasonModel.getSeasonName(),
                    seasonModel.getSeasonStartDate(),
                    seasonModel.getSeasonEndDate(),
                    seasonModel.getSeasonHotelName(),
                    seasonModel.getSeasonHotelId(),
            };
            mdlTableSeasons.addRow(row);

        }

        this.seasonsTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.seasonsTable.setAutoscrolls(true);

        this.seasonsTable.setModel(mdlTableSeasons);
        this.seasonsTable.setEnabled(true);
        this.seasonsTable.setVisible(true);
        this.seasonsTable.getTableHeader().setReorderingAllowed(true);
        comboboxSelectable = true;
    }
    private void updateSeasonComboBox() {
        comboboxSelectable = false;
        this.seasonNamesArray = HotelManagementBusinessController.instance.getAllSeasonNames();
        comboBoxSeasonHotelNames.removeAllItems();
        comboBoxSeasonsNames.removeAllItems();
        for (String name: seasonNamesArray) {
            comboBoxSeasonsNames.addItem(name);
        }

        for (HotelModel hotel: hotelModels) {
            comboBoxSeasonHotelNames.addItem(hotel.getHotelName());
        }
        comboboxSelectable = true;
    }


    private void updateRoomDataWithTable() {
        roomModels = HotelManagementBusinessController.instance.getAllRoomDatas();
        updateDataRoomsTable(roomModels);
    }
    private void updateDataRoomsTable(ArrayList<RoomModel> roomModels) {
        DefaultTableModel dmRooms = (DefaultTableModel) this.roomsTable.getModel();
        int rowCount = dmRooms.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmRooms.removeRow(i);
        }

        Object[] columnRooms = {"Room ID", "Room Stock", "Room Door Numbers", "Room Name", "Pension Name", "Room M2",
                "Room TV", "Room Minibar", "Room Ps", "Room Vault", "Room Projection", "Bed Number", "Adult Price",
                "Child Price", "Hotel Name", "Hotel Location"};

        DefaultTableModel mdlTableRoomsTemp = new DefaultTableModel();
        mdlTableRoomsTemp.setColumnIdentifiers(columnRooms);

        for (RoomModel roomModel : roomModels){
            Object[] row = {
                    roomModel.getRoomId(),
                    roomModel.getRoomStockCount(),
                    roomModel.getRoomDoorNumbers(),
                    roomModel.getRoomTypeName(),
                    roomModel.getRoomPensionTypeName(),
                    roomModel.getRoomMeterSquare(),
                    roomModel.getRoomHaveTV(),
                    roomModel.getRoomHaveMinibar(),
                    roomModel.getRoomHavePS5(),
                    roomModel.getRoomHaveVault(),
                    roomModel.getRoomHaveProjection(),
                    roomModel.getRoomBedNumber(),
                    roomModel.getRoomAdultPrice(),
                    roomModel.getRoomChildPrice(),
                    roomModel.getRoomHotelName(),
                    roomModel.getRoomHotelPlace()
            };
            mdlTableRoomsTemp.addRow(row);

        }

        this.roomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.roomsTable.setAutoscrolls(true);

        this.roomsTable.setModel(mdlTableRoomsTemp);
        this.roomsTable.setEnabled(true);
        this.roomsTable.setVisible(true);
        this.roomsTable.getTableHeader().setReorderingAllowed(true);

        TableColumnModel columnModel = this.roomsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(1).setMaxWidth(80);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(2).setMaxWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(3).setMaxWidth(200);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(4).setMaxWidth(200);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(5).setMaxWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(6).setMaxWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(7).setMaxWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        columnModel.getColumn(8).setMaxWidth(80);
        columnModel.getColumn(9).setPreferredWidth(80);
        columnModel.getColumn(9).setMaxWidth(80);
        columnModel.getColumn(10).setPreferredWidth(100);
        columnModel.getColumn(10).setMaxWidth(120);
        columnModel.getColumn(11).setPreferredWidth(100);
        columnModel.getColumn(11).setMaxWidth(150);
        columnModel.getColumn(12).setPreferredWidth(100);
        columnModel.getColumn(12).setMaxWidth(150);
        columnModel.getColumn(13).setPreferredWidth(100);
        columnModel.getColumn(13).setMaxWidth(150);
        columnModel.getColumn(14).setPreferredWidth(150);
        columnModel.getColumn(14).setMaxWidth(400);
        columnModel.getColumn(15).setPreferredWidth(300);
        columnModel.getColumn(15).setMaxWidth(400);
        this.roomsTable.setColumnModel(columnModel);

    }


    private void updatePensionDataWithTable() {
        this.pensionModels = HotelManagementBusinessController.instance.getAllPensionDatas();
        updateDataPensionsTable(this.pensionModels);
    }
    private void updateDataPensionsTable(ArrayList<PensionModel> pensionModels) {
        DefaultTableModel dmPension = (DefaultTableModel) this.pensionsTable.getModel();
        int rowCount = dmPension.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dmPension.removeRow(i);
        }

        Object[] columnUser = {"Pension ID", "Pension Name"};

        DefaultTableModel mdlTablePensionTemp = new DefaultTableModel();
        mdlTablePensionTemp.setColumnIdentifiers(columnUser);

        for (PensionModel pensionModel : pensionModels){
            Object[] row = {
                    pensionModel.getPensionId(),
                    pensionModel.getPensionName()
            };
            mdlTablePensionTemp.addRow(row);

        }

        this.pensionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.pensionsTable.setAutoscrolls(false);

        this.pensionsTable.setModel(mdlTablePensionTemp);
        this.pensionsTable.setEnabled(true);
        this.pensionsTable.setVisible(true);
        this.pensionsTable.getTableHeader().setReorderingAllowed(true);

        TableColumnModel columnModel = this.pensionsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(0).setMaxWidth(150);
        columnModel.getColumn(1).setPreferredWidth(125);
        columnModel.getColumn(1).setMaxWidth(250);

        this.pensionsTable.setColumnModel(columnModel);

    }


    private void  setUIAttributes() {
        this.add(mainContainer);
        this.setSize(900,600);
        this.setTitle("Hotel Management");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
        this.hotelStarCountArray = new ArrayList<Integer>();
        getTableDatas();
        updateSeasonComboBox();
        updateHotelsComboBox();
        for (HotelModel model :hotelModels) {
            comboboxRoomHotelNames.addItem(model.getHotelName());
        }
        this.hotelStarCountArray = HotelManagementBusinessController.instance.getHotelsStars();
        for (Integer star : hotelStarCountArray) {
            comboBoxHotelStars.addItem(star);
        }

    }

}

