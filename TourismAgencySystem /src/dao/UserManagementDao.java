package dao;

import core.Db;
import entity.UserModel;
import entity.QueryStatus;
import entity.UserTitle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserManagementDao {
    private Connection connection;

    public UserManagementDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> model = new ArrayList<UserModel>();
        String queryUsername = "SELECT * FROM public.user";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryUsername);

            ResultSet resultSetuserName = preparedStatementUsername.executeQuery();
            while(resultSetuserName.next()) {
                UserModel userModel = new UserModel();
                userModel.setUserid(resultSetuserName.getInt("user_id"));
                userModel.setUsername(resultSetuserName.getString("user_name"));
                userModel.setUserTitle( resultSetuserName.getString("user_title").equals("Admin") ? UserTitle.Admin : UserTitle.Employee);
                userModel.setUserPassword(resultSetuserName.getString("user_password"));
                userModel.setQueryStatus(QueryStatus.Success);
                model.add(userModel);
            }
            return model;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return model;
    }

    public ArrayList<UserModel> getUsersBySelectedTitle(UserTitle title) {
        ArrayList<UserModel> model = new ArrayList<UserModel>();
        String queryUsername = "SELECT * FROM public.user where user_title = ?";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryUsername);
            preparedStatementUsername.setString(1,title.toString());

            ResultSet resultSetuserName = preparedStatementUsername.executeQuery();
            while(resultSetuserName.next()) {
                UserModel userModel = new UserModel();
                userModel.setUserid(resultSetuserName.getInt("user_id"));
                userModel.setUsername(resultSetuserName.getString("user_name"));
                userModel.setUserTitle( resultSetuserName.getString("user_title").equals("Admin") ? UserTitle.Admin : UserTitle.Employee);
                userModel.setUserPassword(resultSetuserName.getString("user_password"));
                userModel.setQueryStatus(QueryStatus.Success);
                model.add(userModel);
            }
            return model;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return model;
    }

    public Boolean deleteUser(UserModel userModel) {
        ArrayList<UserModel> model = new ArrayList<UserModel>();
        String queryUsername = "DELETE FROM public.user where user_id = ?";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryUsername);
            preparedStatementUsername.setInt(1,userModel.getUserid());

            ResultSet resultSetuserName = preparedStatementUsername.executeQuery();
            while(resultSetuserName.next()) {
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean addUser(UserModel userModel) {
        ArrayList<UserModel> model = new ArrayList<UserModel>();

        String queryUsername = "INSERT INTO public.user(user_name,user_password,user_title) VALUES(?,?,?)";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryUsername);
            preparedStatementUsername.setString(1,userModel.getUsername());
            preparedStatementUsername.setString(2,userModel.getUserPassword());
            preparedStatementUsername.setString(3,userModel.getUserTitle().toString());

            if(preparedStatementUsername.executeUpdate()>0) {
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
