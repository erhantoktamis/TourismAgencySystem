package view.HotelManagementScreens;

import business.HotelManagementBusinessController;
import entity.PensionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditPensionScreenView extends JFrame {
    private JPanel mainContainer;
    private JTextField pensionNameTextField;
    private JButton saveButton;
    private JButton backButton;
    private JLabel titleLabel;
    private PensionModel selectedEditPensionModel;

    public AddEditPensionScreenView() {
        setScreenUIAttributes();
        titleLabel.setText("Add Pension");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(HotelManagementBusinessController.instance.addNewPension(pensionNameTextField.getText())) {
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
    }

    public AddEditPensionScreenView(PensionModel selectedEditPensionModel) throws HeadlessException {
        this.selectedEditPensionModel = selectedEditPensionModel;
        setScreenUIAttributes();
        setEditScreenDatas(selectedEditPensionModel.getPensionName());


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pensionNameTextField.getText().trim().isEmpty())
                    selectedEditPensionModel.setPensionName(pensionNameTextField.getText().trim());
                    if(HotelManagementBusinessController.instance.updatePension(selectedEditPensionModel))
                        dispose();
            }
        });
    }

    private void setScreenUIAttributes() {
        this.add(mainContainer);
        this.setSize(250,350);
        this.setTitle("Add Pension");
        int x = ((Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()) / 2);
        int y = ((Toolkit.getDefaultToolkit().getScreenSize().height-this.getHeight()) / 2);
        this.setLocation(x,y);
        this.setVisible(true);
    }

    private void setEditScreenDatas(String pensionName) {
        titleLabel.setText("Edit Pension");
        pensionNameTextField.setText(pensionName);
    }

}
