package business;

import dao.UserManagementDao;
import entity.UserModel;
import entity.UserTitle;

import java.util.ArrayList;

public class UserManagementBusinessController {
    public static UserManagementBusinessController instance = new UserManagementBusinessController();
    private UserManagementDao userManagementDao;

    public UserManagementBusinessController() {
        {
            this.userManagementDao = new UserManagementDao();
        }
    }

    public ArrayList<UserModel> getAllUsers() {
        return userManagementDao.getAllUsers();

    }

    public ArrayList<UserModel> getUsersBySelectedTitle(UserTitle title) {
        return userManagementDao.getUsersBySelectedTitle(title);
    }

    public Boolean deleteSelectedUser(UserModel activeUser, UserModel deletingUser) {
        if(activeUser.getUserid() != deletingUser.getUserid()) {
            if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Customer Will be deleted. Are you sure?")) {
                userManagementDao.deleteUser(deletingUser);
            }
        } else {
            ShowAlertController.instance.showAlertWithText("Cannot delete while you are using the same user");
        }
        return false;
    }

    public Boolean addUser(UserModel user) {
        return userManagementDao.addUser(user);
    }
}
