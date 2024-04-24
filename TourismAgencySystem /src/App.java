import core.Db;
import view.LoginScreenView;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection con = Db.getInstance();

        LoginScreenView loginFrame = new LoginScreenView();
        loginFrame.setSize(400,400);

    }
}