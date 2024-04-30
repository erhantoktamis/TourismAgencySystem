package view;

import business.UserManagementBusinessController;
import entity.UserModel;
import entity.UserTitle;
import view.HotelManagementScreens.AddEditUserScreenView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserManagementScreenView extends JFrame {
    private JPanel mainContainer;
    private JComboBox titleComboBox;
    private JButton listAllButton;
    private JButton searchButton1;
    private JButton addNewEmployeeButton;
    private JButton editEmployeeButton;
    private JButton deleteEmployeeButton;
    private JTable table1;
    private JButton backButton;
    private DefaultTableModel mdlTableUser;
    private ArrayList<UserModel> userModels;
    private UserModel controllerActiveUser;
    private Integer tableSelectedItemIndex = -1;

    public UserManagementScreenView(UserModel activeUser) throws HeadlessException {
        setUIAttributes();
        controllerActiveUser = activeUser;

        listAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userModels = UserManagementBusinessController.instance.getAllUsers();
                updateTableInData(userModels);
            }
        });
        titleComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                UserTitle comboBoxSelectedTitle = e.getItem().toString() == "Admin" ? UserTitle.Admin : UserTitle.Employee;
                if(comboBoxSelectedTitle != null) {
                    userModels = UserManagementBusinessController.instance.getUsersBySelectedTitle(comboBoxSelectedTitle);
                    updateTableInData(userModels);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardScreenView(controllerActiveUser).setVisible(true);
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelectedItemIndex = table1.getSelectedRow();

                if(tableSelectedItemIndex != -1) {
                    UserManagementBusinessController.instance.deleteSelectedUser(controllerActiveUser, userModels.get(tableSelectedItemIndex));
                    userModels = UserManagementBusinessController.instance.getAllUsers();
                    updateTableInData(userModels);
                }
            }
        });

        addNewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddEditUserScreenView().setVisible(true);
            }
        });

        editEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSelectedItemIndex = table1.getSelectedRow();

                if(tableSelectedItemIndex != -1) {
                    new AddEditUserScreenView(userModels.get(tableSelectedItemIndex)).setVisible(true);
                }
            }
        });
    }

    private void setUIAttributes() {
        this.add(mainContainer);
        this.setSize(500,400);
        this.setTitle("User Management");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
        this.mdlTableUser = new DefaultTableModel();
        userModels = UserManagementBusinessController.instance.getAllUsers();
        updateTableInData(userModels);

    }

    private void updateTableInData(ArrayList<UserModel> userModels) {
        DefaultTableModel dm = (DefaultTableModel) this.table1.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }


        Object[] columnUser = {"id", "Name", "Password", "Title"};
        this.mdlTableUser.setColumnIdentifiers(columnUser);

        for (UserModel userModel : userModels){
            Object[] row = {
                    userModel.getUserid(),
                    userModel.getUsername(),
                    userModel.getUserPassword(),
                    userModel.getUserTitle()
            };
            this.mdlTableUser.addRow(row);

        }

        this.table1.setModel(this.mdlTableUser);
        this.table1.setEnabled(true);
        this.table1.setVisible(true);
        this.table1.getTableHeader().setReorderingAllowed(false);

    }
}
