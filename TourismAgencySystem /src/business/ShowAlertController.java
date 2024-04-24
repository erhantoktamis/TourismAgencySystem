package business;

import view.UserManagementScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowAlertController {

    public static ShowAlertController instance = new ShowAlertController();

    public ShowAlertController() {

    }

    public void showAlertWithText(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public Boolean showAlertWithQuestionButton(String title, String message) {
        return JOptionPane.showConfirmDialog(null,message, title, JOptionPane.YES_NO_OPTION) == 0;
    }

}
