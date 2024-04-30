package business;

import dao.HotelManagementDao;
import entity.*;

import java.util.ArrayList;

public class HotelManagementBusinessController {
    public static HotelManagementBusinessController instance = new HotelManagementBusinessController();
    private HotelManagementDao hotelManagementDao;

    public HotelManagementBusinessController() {
        this.hotelManagementDao = new HotelManagementDao();
    }

    // Hotels Screen Bussiness
    public ArrayList<HotelModel> getAllHotelsDatas() {
       return hotelManagementDao.getAllHotelsDatas();
    }
    public ArrayList<HotelModel> getHotelsByStarsCount(Integer starCount) {
        return hotelManagementDao.getHotelsByStarsCount(starCount);
    }
    public ArrayList<Integer> getHotelsStars() {
        return hotelManagementDao.getHotelsStars();
    }
    public Boolean addNewHotel(HotelModel hotelModel) {
        if(hotelModel != null && hotelModel.getHotelName() != null && !hotelModel.getHotelName().trim().isEmpty()
                && hotelModel.getHotelPhone() != null && !hotelModel.getHotelPhone().trim().isEmpty()
                && hotelModel.getHotelEmail() != null && !hotelModel.getHotelEmail().trim().isEmpty()
                && hotelModel.getHotelAddress() != null && !hotelModel.getHotelAddress().trim().isEmpty()
                && hotelModel.getHotelStar() != null && hotelModel.getHotelStar() > 0
                && hotelModel.getHotelFacilityResourcesIds() != null && !hotelModel.getHotelFacilityResourcesIds().isEmpty()
                && hotelModel.getHotelPensionTypesIds() != null && !hotelModel.getHotelPensionTypesIds().isEmpty())  {

            if (hotelManagementDao.addNewHotel(hotelModel))
            {
                ShowAlertController.instance.showAlertWithText("New Hotel Successfully Added.");
                return true;
            }
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later.");
            return false;
        }
        ShowAlertController.instance.showAlertWithText("Please enter correct datas!");
        return false;
    }
    public Boolean updateHotel(HotelModel hotelModel) {
        if(hotelModel != null && hotelModel.getHotelName() != null && !hotelModel.getHotelName().trim().isEmpty()
                && hotelModel.getHotelPhone() != null && !hotelModel.getHotelPhone().trim().isEmpty()
                && hotelModel.getHotelEmail() != null && !hotelModel.getHotelEmail().trim().isEmpty()
                && hotelModel.getHotelAddress() != null && !hotelModel.getHotelAddress().trim().isEmpty()
                && hotelModel.getHotelStar() != null && hotelModel.getHotelStar() > 0
                && hotelModel.getHotelFacilityResourcesIds() != null && !hotelModel.getHotelFacilityResourcesIds().isEmpty()
                && hotelModel.getHotelPensionTypesIds() != null && !hotelModel.getHotelPensionTypesIds().isEmpty())  {

            if (hotelManagementDao.updateHotel(hotelModel))
            {
                ShowAlertController.instance.showAlertWithText("Hotel Successfully Updated.");
                return true;
            }
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later.");
            return false;
        }
        ShowAlertController.instance.showAlertWithText("Please enter correct datas!");
        return false;
    }
    public void deleteHotel(HotelModel hotelModel) {
        if (hotelModel.getHotelId() != null && hotelModel.getHotelId() != -1) {
            if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Hotel Will be deleted. Are you sure?")) {
                if(hotelManagementDao.deleteHotel(hotelModel))
                    ShowAlertController.instance.showAlertWithText("Hotel Successfully Deleted.");
            }
        }
        else {
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later!!");
        }

    }


    // Seasons Screen Bussiness
    public ArrayList<SeasonModel> getAllSeasonsDatas() {
        return hotelManagementDao.getAllSeasonsDatas();
    }
    public ArrayList<String> getAllSeasonNames() {
        return hotelManagementDao.getAllSeasonNames();
    }
    public ArrayList<SeasonModel> getSeasonsBySeasonName(String seasonName) {
        return hotelManagementDao.getSeasonsBySeasonName(seasonName);
    }
    public ArrayList<SeasonModel> getSeasonsByHotelId(Integer hotelId) {
        return hotelManagementDao.getSeasonsByHotelId(hotelId);
    }
    public Boolean addNewSeason(SeasonModel seasonModel) {
        if (!seasonModel.getSeasonStartDate().trim().isEmpty()
                && !seasonModel.getSeasonEndDate().trim().isEmpty()
                && !seasonModel.getSeasonName().trim().isEmpty()){
            if(hotelManagementDao.addNewSeason(seasonModel)) {
                ShowAlertController.instance.showAlertWithText("Season Successfully Added.");
                return true;
            }
        }
        return false;
    }
    public Boolean updateSeason(SeasonModel seasonModel) {
        if (!seasonModel.getSeasonStartDate().trim().isEmpty()
                && !seasonModel.getSeasonEndDate().trim().isEmpty()
                && !seasonModel.getSeasonName().trim().isEmpty()){
            if (hotelManagementDao.updateSeason(seasonModel)) {
                ShowAlertController.instance.showAlertWithText("Season Successfully Updated.");
                return true;
            }
        }
        return false;
    }
    public void deleteSeason(SeasonModel seasonModel) {
        if (seasonModel.getSeasonId() != null && seasonModel.getSeasonId() != -1) {
            if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Season Will be deleted. Are you sure?")) {
                if(hotelManagementDao.deleteSeason(seasonModel))
                    ShowAlertController.instance.showAlertWithText("Season Successfully Deleted.");
            }
        }
        else {
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later!!");
        }

    }



    // Pensions Screen Bussiness
    public ArrayList<PensionModel> getAllPensionDatas() {

        return hotelManagementDao.getAllPensionsDatas();
    }
    public void deletePension(PensionModel pensionModel) {
        if (pensionModel.getPensionId() != null && pensionModel.getPensionId() != -1) {
            if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Pension Will be deleted. Are you sure?")) {
                if(hotelManagementDao.deletePension(pensionModel))

                    ShowAlertController.instance.showAlertWithText("Pension Successfully Deleted.");
            }
        }
        else {
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later!!");
        }

    }
    public Boolean addNewPension(String pensionName) {
        if(hotelManagementDao.addNewPension(pensionName)) {
            ShowAlertController.instance.showAlertWithText("Pension Successfully Added.");
            return true;
        }
        return false;
    }
    public Boolean updatePension(PensionModel pensionModel) {
        if (pensionModel.getPensionId() != null && pensionModel.getPensionId() != -1) {
            if(hotelManagementDao.updatePension(pensionModel)) {
                ShowAlertController.instance.showAlertWithText("Pension Successfully Updated.");
                return true;
            }

        }
        return false;
    }



    // Rooms Screen Bussiness
    public ArrayList<RoomModel> getAllRoomDatas() {
        return hotelManagementDao.getAllRoomsDatas();
    }
    public ArrayList<RoomModel> getSelectedHotelRooms(Integer hotelId) {
        return hotelManagementDao.getSelectedHotelRooms(hotelId);
    }
    public ArrayList<HotelRoomTypes> getAllHotelRoomTypesDatas() {
        return hotelManagementDao.getAllHotelRoomTypesDatas();
    }
    public void deleteRoom(RoomModel roomModel) {
        if (roomModel.getRoomId() != null && roomModel.getRoomId() != -1) {
            if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Room Will be deleted. Are you sure?")) {
                if(hotelManagementDao.deleteRoom(roomModel))
                    ShowAlertController.instance.showAlertWithText("Room Successfully Deleted.");
            }
        }
        else {
            ShowAlertController.instance.showAlertWithText("Something Went Wrong! Try Again Later!!");
        }
    }
    public Boolean addHotelRoom(RoomModel roomModel) {
        if(roomModel != null && roomModel.getRoomHotelId() != null &&
                roomModel.getRoomDoorNumbers() != null && !roomModel.getRoomDoorNumbers().trim().isEmpty() &&
                roomModel.getRoomAdultPrice() != null && !roomModel.getRoomAdultPrice().trim().isEmpty() &&
                roomModel.getRoomChildPrice() != null && !roomModel.getRoomChildPrice().trim().isEmpty() &&
                roomModel.getRoomMeterSquare() != null && !roomModel.getRoomMeterSquare().trim().isEmpty() &&
                roomModel.getRoomStockCount() != null && roomModel.getRoomBedNumber() != null ) {

            if(hotelManagementDao.addHotelRoom(roomModel)) {
                ShowAlertController.instance.showAlertWithText("Room Successfully Added.");
                return true;
            }
            else
                ShowAlertController.instance.showAlertWithText("Something Went Wrong!");
        }
        return false;
    }
    public Boolean updateHotelRoom(RoomModel roomModel) {
        if(roomModel != null && roomModel.getRoomHotelId() != null &&
                roomModel.getRoomDoorNumbers() != null && !roomModel.getRoomDoorNumbers().trim().isEmpty() &&
                roomModel.getRoomAdultPrice() != null && !roomModel.getRoomAdultPrice().trim().isEmpty() &&
                roomModel.getRoomChildPrice() != null && !roomModel.getRoomChildPrice().trim().isEmpty() &&
                roomModel.getRoomMeterSquare() != null && !roomModel.getRoomMeterSquare().trim().isEmpty() &&
                roomModel.getRoomStockCount() != null && roomModel.getRoomBedNumber() != null ) {

            if(hotelManagementDao.updateHotelRoom(roomModel)) {
                ShowAlertController.instance.showAlertWithText("Room Successfully Updated.");
                return true;
            }
            else
                ShowAlertController.instance.showAlertWithText("Something Went Wrong!");
        }
        return false;
    }



    // Facilities Service Methods
    public ArrayList<FacilityModel> getAllFacilities() {
        return hotelManagementDao.getAllFacilities();
    }
    public ArrayList<String> getFacilityResources(){
        return hotelManagementDao.getFacilityResources();
    }
}
