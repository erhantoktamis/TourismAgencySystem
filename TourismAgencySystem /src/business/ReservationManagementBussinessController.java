package business;

import dao.HotelManagementDao;
import dao.ReservationManagementDao;
import entity.*;

import java.util.ArrayList;

public class ReservationManagementBussinessController {

    public static ReservationManagementBussinessController instance = new ReservationManagementBussinessController();
    private ReservationManagementDao reservationManagementDao;
    private HotelManagementDao hotelManagementDao;

    public ReservationManagementBussinessController() {
        this.reservationManagementDao = new ReservationManagementDao();
        this.hotelManagementDao = new HotelManagementDao();
    }

    public ArrayList<HotelsSeasonsAndRoomsModel> getAllReservationHotelsAndRooms() {

        ArrayList<FacilityModel> facilityModels = hotelManagementDao.getAllFacilities();
        ArrayList<PensionModel> pensionModels = hotelManagementDao.getAllPensionsDatas();
        return reservationManagementDao.getAllReservationHotelsAndRooms(facilityModels, pensionModels);
    }
    public ArrayList<HotelsSeasonsAndRoomsModel> getSelectedHotelsRoomsAndSeasonByCityName(String cityName) {
        ArrayList<FacilityModel> facilityModels = hotelManagementDao.getAllFacilities();
        ArrayList<PensionModel> pensionModels = hotelManagementDao.getAllPensionsDatas();
        return reservationManagementDao.getSelectedReservationHotelsAndRoomsByCityName(cityName,facilityModels, pensionModels);
    }
    public ArrayList<HotelsSeasonsAndRoomsModel> getSelectedHotelsRoomsAndSeasonByHotelName(String hotelName) {
        if(hotelName != null && !hotelName.isEmpty()) {
            ArrayList<FacilityModel> facilityModels = hotelManagementDao.getAllFacilities();
            ArrayList<PensionModel> pensionModels = hotelManagementDao.getAllPensionsDatas();
            return reservationManagementDao.getSelectedReservationHotelsAndRoomsByHotelName(hotelName,facilityModels, pensionModels);
        }
        return new ArrayList<>();
    }



    public ArrayList<RoomModel> getAllRooms() {
        return hotelManagementDao.getAllRoomsDatas();
    }
    public ArrayList<SeasonModel> getAllSeasons() {
        return hotelManagementDao.getAllSeasonsDatas();
    }

    public ArrayList<HotelModel> getAllHotels() {
        return hotelManagementDao.getAllHotelsDatas();
    }
    public ArrayList<HotelModel> getSelectedHotelsByHotelName(String hotelName) {
        ArrayList<FacilityModel> facilityModels = hotelManagementDao.getAllFacilities();
        ArrayList<PensionModel> pensionModels = hotelManagementDao.getAllPensionsDatas();
        return reservationManagementDao.getSelectedHotelsByHotelName(hotelName, facilityModels, pensionModels);
    }
    public ArrayList<HotelModel> getSelectedHotelsByCityName(String cityName) {
        ArrayList<FacilityModel> facilityModels = hotelManagementDao.getAllFacilities();
        ArrayList<PensionModel> pensionModels = hotelManagementDao.getAllPensionsDatas();
        return reservationManagementDao.getSelectedHotelsByCityName(cityName, facilityModels, pensionModels);
    }
    public ArrayList<String> getAllHotelsCities() {
        return reservationManagementDao.getAllHotelCities();
    }


    // Reservation Service Methods
    public ArrayList<ReservationModel> getAllReservations() {
        return reservationManagementDao.getAllReservations();
    }
    public Boolean addNewReservation(ReservationModel reservationModel, HotelModel hotel, RoomModel roomModel) {
        if(reservationModel != null && hotel != null && hotel.getHotelId() > 0
                && reservationModel.getReservationEmail() != null && !reservationModel.getReservationEmail().trim().isEmpty()
                && reservationModel.getReservationPhoneNumber() != null && !reservationModel.getReservationPhoneNumber().trim().isEmpty()
                && reservationModel.getReservationFullName() != null && !reservationModel.getReservationFullName().trim().isEmpty()
                && reservationModel.getReservationCheckInDateString() != null && !reservationModel.getReservationCheckInDateString().trim().isEmpty()
                && reservationModel.getReservationCheckOutDateString() != null && !reservationModel.getReservationCheckOutDateString().trim().isEmpty()
                && reservationModel.getReservationIdentificationNumber() != null && !reservationModel.getReservationIdentificationNumber().trim().isEmpty()
                && reservationModel.getReservationAdultCount() != null && reservationModel.getReservationAdultCount() > 0
                && reservationModel.getReservationChildCount() != null) {
            if(reservationManagementDao.addNewReservation(reservationModel,hotel.getHotelId())) {

                roomModel.setRoomStockCount((roomModel.getRoomStockCount()-1));
                hotelManagementDao.updateHotelRoom(roomModel);

                ShowAlertController.instance.showAlertWithText("Reservation Successfully Created.");
                return true;
            }
            ShowAlertController.instance.showAlertWithText("Something Went Wrong.");
            return false;
        }
        ShowAlertController.instance.showAlertWithText("Please enter correct infos.");
        return false;
    }
    public Boolean updateReservation(ReservationModel reservationModel, HotelModel hotel, RoomModel roomModel) {
        if(reservationModel != null && hotel != null && hotel.getHotelId() > 0
                && reservationModel.getReservationEmail() != null && !reservationModel.getReservationEmail().trim().isEmpty()
                && reservationModel.getReservationPhoneNumber() != null && !reservationModel.getReservationPhoneNumber().trim().isEmpty()
                && reservationModel.getReservationFullName() != null && !reservationModel.getReservationFullName().trim().isEmpty()
                && reservationModel.getReservationCheckInDateString() != null && !reservationModel.getReservationCheckInDateString().trim().isEmpty()
                && reservationModel.getReservationCheckOutDateString() != null && !reservationModel.getReservationCheckOutDateString().trim().isEmpty()
                && reservationModel.getReservationIdentificationNumber() != null && !reservationModel.getReservationIdentificationNumber().trim().isEmpty()
                && reservationModel.getReservationAdultCount() != null && reservationModel.getReservationAdultCount() > 0
                && reservationModel.getReservationChildCount() != null) {

            if(reservationManagementDao.updateReservation(reservationModel,hotel.getHotelId())) {

                roomModel.setRoomStockCount((roomModel.getRoomStockCount()-1));
                hotelManagementDao.updateHotelRoom(roomModel);

                ShowAlertController.instance.showAlertWithText("Reservation Successfully Updated.");
                return true;
            }
            ShowAlertController.instance.showAlertWithText("Something Went Wrong.");
            return false;
        }
        ShowAlertController.instance.showAlertWithText("Please enter correct infos.");
        return false;
    }
    public Boolean deleteReservation(RoomModel roomModel, ReservationModel reservationModel) {
        if(ShowAlertController.instance.showAlertWithQuestionButton("Are You Sure?","This Season Will be deleted. Are you sure?")) {
            roomModel.setRoomStockCount((roomModel.getRoomStockCount()+1));
            hotelManagementDao.updateHotelRoom(roomModel);
            return reservationManagementDao.deleteReservation(reservationModel);
        }
        return false;
    }
}
