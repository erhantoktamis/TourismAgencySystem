package business;

import core.Db;
import dao.LoginUserDao;
import entity.UserModel;

import java.sql.SQLException;

public class LoginUserBusinessController {
    public static LoginUserBusinessController instance = new LoginUserBusinessController();
    private LoginUserDao loginUserDao;

    public LoginUserBusinessController() {
        this.loginUserDao = new LoginUserDao();
    }

    public UserModel getUserDatas(String name, String password) {
        UserModel userModel = new UserModel();
        if(name.isBlank()
                || name.trim() == ""
                || password.isBlank()
                || password.trim() == "" ) {
            ShowAlertController.instance.showAlertWithText( "Boş Giriş Yaptınız.");
        } else {
            try {

                if(!Db.getInstance().isClosed()){
                    userModel = loginUserDao.getLoginUserData(name,password);
                    isLoginDatasCorrect(userModel);

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return userModel;
    }

    public Boolean isLoginDatasCorrect(UserModel userModel) {
       switch (userModel.getQueryStatus()) {
           case Success -> {
               return true;
           }
           case PasswordFailed -> {
               ShowAlertController.instance.showAlertWithText("Girdiğiniz şifre hatalı ile eşleşen kullanıcı bulunamadı!");
           }
           case AllFailed -> {
               ShowAlertController.instance.showAlertWithText("Giridiğiniz kullanıcı bulunamadı.!");
           }
       }
        return false;
    }

}
