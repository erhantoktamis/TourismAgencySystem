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
                if(userManagementDao.deleteUser(deletingUser)) {
                    ShowAlertController.instance.showAlertWithText("User Successfully Deleted.");
                }
            }
        } else {
            ShowAlertController.instance.showAlertWithText("Cannot delete while you are using the same user");
        }
        return false;
    }

    public Boolean addUser(UserModel user) {
        if(user.getUsername() != null && !user.getUsername().isEmpty() && !user.getUsername().trim().isEmpty()
                && user.getUserPassword() != null && !user.getUserPassword().isEmpty() && !user.getUserPassword().trim().isEmpty()
                && user.getUserTitle() != null) {
            if(userManagementDao.addUser(user)) {
                ShowAlertController.instance.showAlertWithText("User Successfully Added.");
                return true;
            }
        } else {
            ShowAlertController.instance.showAlertWithText("Cannot save empty data.");
        }
        return false;
    }

    public Boolean updateUser(UserModel user) {
        if(user.getUserid() != null
                && user.getUsername() != null
                && user.getUserPassword() != null
                && user.getUserTitle() != null ) {
            if(userManagementDao.updateUser(user)){
                ShowAlertController.instance.showAlertWithText("User Successfully Updated.");
                return true;
            }
        } else {
            ShowAlertController.instance.showAlertWithText("Cannot update empty data.");
        }
        return false;

    }
}
