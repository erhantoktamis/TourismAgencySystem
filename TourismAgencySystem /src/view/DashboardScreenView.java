package view;

import entity.UserModel;
import entity.UserTitle;
import view.HotelManagementScreens.HotelManagementScreenView;
import view.ReservationManagementScreens.ReservationManagementScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardScreenView extends JFrame {
    private JPanel mainContainer;
    private JButton UserManagementButton;
    private JButton HotelManagementButton;
    private JButton ReservationManagementButton;
    private JButton logoutButton;

    public DashboardScreenView(UserModel userModel) {
        setUIAttributes();

        if (userModel.getUserTitle() == UserTitle.Admin) {
            UserManagementButton.setVisible(true);
            UserManagementButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new UserManagementScreenView(userModel).setVisible(true);
                }
            });
        }

        HotelManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HotelManagementScreenView(userModel).setVisible(true);
            }
        });
        ReservationManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ReservationManagementScreenView(userModel).setVisible(true);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginScreenView().setVisible(true);

            }
        });
    }

    private void setUIAttributes() {
        this.add(mainContainer);
        this.setSize(300,400);
        this.setTitle("Dashboard");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
        UserManagementButton.setVisible(false);

    }
}
