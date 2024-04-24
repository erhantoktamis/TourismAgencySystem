package dao;

import core.Db;
import entity.UserModel;
import entity.QueryStatus;
import entity.UserTitle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUserDao {
    private  Connection connection;

    public LoginUserDao() {
        this.connection = Db.getInstance();
    }


    public UserModel getLoginUserData(String username, String password) {
        UserModel model = new UserModel();
        String queryUsername = "SELECT user_name, user_password, user_title FROM public.user WHERE user_name = ?";
        try{
            model.setQueryStatus(QueryStatus.AllFailed);
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryUsername);
            preparedStatementUsername.setString(1,username);

            ResultSet resultSetuserName = preparedStatementUsername.executeQuery();
            if(resultSetuserName.next()){
                String querynamepassword = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
                PreparedStatement preparedStatementNamePassword = this.connection.prepareStatement(querynamepassword);
                preparedStatementNamePassword.setString(1,username);
                preparedStatementNamePassword.setString(2,password);

                ResultSet resultSetUserNamePassword = preparedStatementNamePassword.executeQuery();
                while(resultSetUserNamePassword.next()) {
                    UserModel userModel = new UserModel();
                    userModel.setUserid(resultSetUserNamePassword.getInt("user_id"));
                    userModel.setUsername(resultSetUserNamePassword.getString("user_name"));
                    userModel.setUserTitle( resultSetUserNamePassword.getString("user_title").equals("Admin") ? UserTitle.Admin : UserTitle.Employee);
                    userModel.setUserPassword(resultSetUserNamePassword.getString("user_password"));
                    userModel.setQueryStatus(QueryStatus.Success);
                    return userModel;
                }
                model.setQueryStatus(QueryStatus.PasswordFailed);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return model;
    }
}
