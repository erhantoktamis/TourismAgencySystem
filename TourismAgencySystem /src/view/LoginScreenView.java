package view;

import business.LoginUserBusinessController;
import entity.UserModel;
import entity.QueryStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreenView extends JFrame {
    private JPanel container;
    private JTextField userNameTextField;
    private JPanel userNameTextFieldContainer;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JPanel passwordTextFieldContainer;
    private JButton exitButton;
    public UserModel userModel;

    public LoginScreenView() throws HeadlessException {
        setUIAttributes();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameTextField.getText();
                String password = passwordTextField.getText();
                userModel = LoginUserBusinessController.instance.getUserDatas(username, password);

                goToDashboardScreen(userModel);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public LoginScreenView(String title, JPanel container) throws HeadlessException {
        super(title);
        this.container = container;
    }

    private void goToDashboardScreen(UserModel userModel) {
        if(userModel.getQueryStatus() == QueryStatus.Success){
            this.setVisible(false);
            DashboardScreenView dashboardScreenView = new DashboardScreenView(userModel);
        }

    }

    private void setUIAttributes() {
        this.add(container);
        this.setVisible(true);
        this.setSize(300,400);
        this.setTitle("Tourism Agency Management System");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
