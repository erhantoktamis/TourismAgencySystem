package view.HotelManagementScreens;

import business.ShowAlertController;
import business.UserManagementBusinessController;
import entity.UserModel;
import entity.UserTitle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddEditUserScreenView extends JFrame {
    private JPanel mainContainer;
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveButton;
    private JComboBox comboBox1;
    private JLabel titleLabel;
    private UserTitle selectedUserTitle;

    public AddEditUserScreenView(UserModel model) {
        setUpdateScreenUIAttributes(model);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserModel userModel = new UserModel();
                userModel.setUserTitle(selectedUserTitle == null ? model.getUserTitle() : selectedUserTitle);
                userModel.setUsername(textField1.getText());
                userModel.setUserPassword(textField2.getText());
                userModel.setUserid(model.getUserid());

                if(UserManagementBusinessController.instance.updateUser(userModel)) {
                    dispose();
                }

            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedUserTitle = e.getItem().toString() == UserTitle.Admin.toString() ? UserTitle.Admin : UserTitle.Employee;
            }
        });
    }

    public AddEditUserScreenView() {
        setAddUIAttributes();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserModel userModel = new UserModel();

                userModel.setUserTitle(selectedUserTitle == null ? UserTitle.Admin : selectedUserTitle);
                userModel.setUsername(textField1.getText());
                userModel.setUserPassword(textField2.getText());

                if(UserManagementBusinessController.instance.addUser(userModel)) {
                    dispose();
                }

            }
        });
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedUserTitle = e.getItem().toString() == UserTitle.Admin.toString() ? UserTitle.Admin : UserTitle.Employee;
            }
        });
    }

    private void setAddUIAttributes() {
        this.add(mainContainer);
        this.setVisible(true);
        this.setSize(300,400);
        this.setTitle("Tourism Agency Management System - Add");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        titleLabel.setText("Add New Employee");
    }

    private void setUpdateScreenUIAttributes(UserModel user) {
        this.add(mainContainer);
        this.setVisible(true);
        this.setSize(300,400);
        this.setTitle("Tourism Agency Management System - Update");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);

        titleLabel.setText("Update Employee");
        textField1.setText(user.getUsername());
        textField2.setText(user.getUserPassword());
        comboBox1.setSelectedIndex((user.getUserTitle().name() == "Admin") ? 0 : 1);
    }
}
