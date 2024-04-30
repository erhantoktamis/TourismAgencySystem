package dao;

import core.Db;
import entity.*;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationManagementDao {
    private Connection connection;

    public ReservationManagementDao() {
        this.connection =  Db.getInstance();
    }

    public ArrayList<String> getAllHotelCities() {
        ArrayList<String> hotelCities = new ArrayList<String>();

        String queryHotels = "SELECT DISTINCT hotel_address FROM public.hotel ORDER BY hotel_address;";
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(queryHotels);

            ResultSet resultSetHotels = preparedStatementHotels.executeQuery();
            while(resultSetHotels.next()) {
                hotelCities.add(resultSetHotels.getString("hotel_address"));
            }
            return hotelCities;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return hotelCities;
    }
    public ArrayList<HotelModel> getSelectedHotelsByCityName(String cityName, ArrayList<FacilityModel> facilityModels, ArrayList<PensionModel> pensionModels) {

        String queryHotels = "SELECT * FROM public.hotel WHERE hotel_address = ? Order by hotel_address;";
        return getHotelsBy(queryHotels,cityName,facilityModels,pensionModels);
    }
    public ArrayList<HotelModel> getSelectedHotelsByHotelName(String hotelName, ArrayList<FacilityModel> facilityModels, ArrayList<PensionModel> pensionModels) {

        String queryHotels = "SELECT * FROM public.hotel WHERE hotel_name = ? Order by hotel_name;";
        return getHotelsBy(queryHotels,hotelName, facilityModels, pensionModels);
    }
    private ArrayList<HotelModel> getHotelsBy(String query, String param, ArrayList<FacilityModel> facilitiesModel, ArrayList<PensionModel> pensionsModel) {
        ArrayList<HotelModel> hotelModels = new ArrayList<HotelModel>();
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(query);
            preparedStatementHotels.setString(1, param);

            ResultSet resultSetHotels = preparedStatementHotels.executeQuery();
            while(resultSetHotels.next()) {
                HotelModel hotelModel = new HotelModel();
                hotelModel.setHotelId(resultSetHotels.getInt("hotel_id"));
                hotelModel.setHotelName(resultSetHotels.getString("hotel_name"));
                hotelModel.setHotelEmail(resultSetHotels.getString("hotel_email"));
                hotelModel.setHotelPhone(resultSetHotels.getString("hotel_phone"));
                hotelModel.setHotelStar(resultSetHotels.getInt("hotel_star"));
                hotelModel.setHotelAddress(resultSetHotels.getString("hotel_address"));


                // Veritabanından gelen id arrayleri tabloda gösterebilmek için kendi tablolarındaki verilerle eşliyorum.
                List<String> columnArray = Arrays.asList( resultSetHotels.getArray("hotel_facility_resources").toString());
                ArrayList<String> namesStringsArray = new ArrayList<String>();
                ArrayList<Integer> idsIntArray = new ArrayList<Integer>();
                for (String item : columnArray.get(0).replace("{", "").replace("}","").split(",")) {
                    namesStringsArray.add(facilitiesModel.get(Integer.valueOf(item)-1).getFacilityName());
                    idsIntArray.add(facilitiesModel.get(Integer.valueOf(item)-1).getFacilityId());
                }
                hotelModel.setHotelFacilityResources(namesStringsArray);
                hotelModel.setHotelFacilityResourcesIds(idsIntArray);


                columnArray = new ArrayList<>(Arrays.asList(resultSetHotels.getArray("hotel_hostel_pension_types").toString()));
                namesStringsArray = new ArrayList<String>();
                idsIntArray = new ArrayList<Integer>();
                for (String item : columnArray.get(0).replace("{", "").replace("}","").split(",")) {
                    namesStringsArray.add(pensionsModel.get(Integer.valueOf(item)-1).getPensionName());
                    idsIntArray.add(pensionsModel.get(Integer.valueOf(item)-1).getPensionId());
                }
                hotelModel.setHotelPensionTypes(namesStringsArray);
                hotelModel.setHotelPensionTypesIds(idsIntArray);


                hotelModels.add(hotelModel);
            }
            return hotelModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return hotelModels;
    }
    public boolean addNewReservation(ReservationModel reservationModel, Integer hotelId) {
        String queryReservationAdd = "INSERT INTO public.reservations(\n" +
                "\treservation_full_name, reservation_identification_number, reservation_phone, reservation_email," +
                " reservation_adult, reservation_child, reservation_check_in_date, reservation_check_out_date, reservation_hotel_id)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return reservationQuery(queryReservationAdd, hotelId, reservationModel);
    }
    public boolean updateReservation(ReservationModel reservationModel, Integer hotelId) {
        String queryReservationAdd = "UPDATE public.reservations\n" +
                "\tSET reservation_full_name=?, reservation_identification_number=?, reservation_phone=?, " +
                "reservation_email=?, reservation_adult=?, reservation_child=?, reservation_check_in_date=?, reservation_check_out_date=?, reservation_hotel_id = ? \n" +
                "\tWHERE \"reservation_Id\" = ?;";

        return reservationQuery(queryReservationAdd, hotelId, reservationModel);
    }


    public ArrayList<HotelsSeasonsAndRoomsModel> getAllReservationHotelsAndRooms(ArrayList<FacilityModel> facilitiesModel, ArrayList<PensionModel> pensionsModel) {
        String queryReservation = "SELECT DISTINCT public.hotel.hotel_id,public.\"hotelRoom\".room_id,public.season.season_id, hotel_name, hotel_address,\n" +
                "hotel_facility_resources, hotel_hostel_pension_types,\n" +
                "public.\"season\".season_start_date,public.\"season\".season_end_date,\n" +
                "public.\"hotelRoom\".room_stock, public.\"hotelRoom\".room_adult_price,public.\"hotelRoom\".room_child_price\n" +
                "\n ,public.\"hotelRoomTypes\".room_name " +
                "FROM public.hotel\n" +
                "INNER JOIN public.\"hotelRoom\" on public.hotel.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"season\" on public.season.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"hotelRoomTypes\" on public.\"hotelRoomTypes\".room_type_id = public.\"hotelRoom\".room_type_id " +
                "ORDER BY public.hotel.hotel_id;";

        return getAllReservationHotelsAndRoomExecuteSQL(queryReservation,facilitiesModel,pensionsModel);
    }

    public ArrayList<HotelsSeasonsAndRoomsModel> getSelectedReservationHotelsAndRoomsByCityName(String cityName,ArrayList<FacilityModel> facilitiesModel, ArrayList<PensionModel> pensionsModel) {
        String queryReservation = "SELECT DISTINCT public.hotel.hotel_id,public.\"hotelRoom\".room_id,public.season.season_id, hotel_name, hotel_address,\n" +
                "hotel_facility_resources, hotel_hostel_pension_types,\n" +
                "public.\"season\".season_start_date,public.\"season\".season_end_date,\n" +
                "public.\"hotelRoom\".room_stock, public.\"hotelRoom\".room_adult_price,public.\"hotelRoom\".room_child_price\n" +
                "\n ,public.\"hotelRoomTypes\".room_name " +
                "FROM public.hotel\n" +
                "INNER JOIN public.\"hotelRoom\" on public.hotel.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"season\" on public.season.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"hotelRoomTypes\" on public.\"hotelRoomTypes\".room_type_id = public.\"hotelRoom\".room_type_id " +
                "WHERE public.hotel.hotel_address = '"+cityName+"' " +
                "ORDER BY public.hotel.hotel_id ;";

        return getAllReservationHotelsAndRoomExecuteSQL(queryReservation, facilitiesModel, pensionsModel);
    }
    public ArrayList<HotelsSeasonsAndRoomsModel> getSelectedReservationHotelsAndRoomsByHotelName(String hotelName,ArrayList<FacilityModel> facilitiesModel, ArrayList<PensionModel> pensionsModel) {
        String queryReservation = "SELECT DISTINCT public.hotel.hotel_id,public.\"hotelRoom\".room_id,public.season.season_id, hotel_name, hotel_address,\n" +
                "hotel_facility_resources, hotel_hostel_pension_types,\n" +
                "public.\"season\".season_start_date,public.\"season\".season_end_date,\n" +
                "public.\"hotelRoom\".room_stock, public.\"hotelRoom\".room_adult_price,public.\"hotelRoom\".room_child_price\n" +
                "\n ,public.\"hotelRoomTypes\".room_name " +
                "FROM public.hotel\n" +
                "INNER JOIN public.\"hotelRoom\" on public.hotel.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"season\" on public.season.hotel_id = public.\"hotelRoom\".hotel_id \n" +
                "INNER JOIN public.\"hotelRoomTypes\" on public.\"hotelRoomTypes\".room_type_id = public.\"hotelRoom\".room_type_id " +
                "WHERE public.hotel.hotel_name = '"+hotelName+"' " +
                "ORDER BY public.hotel.hotel_id ;";

        return getAllReservationHotelsAndRoomExecuteSQL(queryReservation, facilitiesModel, pensionsModel);
    }
    public ArrayList<HotelsSeasonsAndRoomsModel> getAllReservationHotelsAndRoomExecuteSQL(String queryString, ArrayList<FacilityModel> facilitiesModel, ArrayList<PensionModel> pensionsModel) {
        ArrayList<HotelsSeasonsAndRoomsModel> hotelsSeasonsAndRoomsModelArrayList = new ArrayList<HotelsSeasonsAndRoomsModel>();
        try{
            PreparedStatement preparedStatementReservation = this.connection.prepareStatement(queryString);

            ResultSet resultSetReservations = preparedStatementReservation.executeQuery();
            while(resultSetReservations.next()) {
                HotelsSeasonsAndRoomsModel model = new HotelsSeasonsAndRoomsModel();
                model.setHotelId(resultSetReservations.getInt("hotel_id"));
                model.setRoomId(resultSetReservations.getInt("room_id"));
                model.setSeasonId(resultSetReservations.getInt("season_id"));
                model.setRoomName(resultSetReservations.getString("room_name"));
                model.setHotelName(resultSetReservations.getString("hotel_name"));
                model.setHotelAddress(resultSetReservations.getString("hotel_address"));
                model.setSeasonStartDate(resultSetReservations.getString("season_start_date"));
                model.setSeasonEndDate(resultSetReservations.getString("season_end_date"));
                model.setRoomStock(resultSetReservations.getString("room_stock"));
                model.setRoomAdultPrice(resultSetReservations.getString("room_adult_price"));
                model.setRoomChildPrice(resultSetReservations.getString("room_child_price"));


                // Veritabanından gelen id arrayleri tabloda gösterebilmek için kendi tablolarındaki verilerle eşliyorum.
                List<String> columnArray = Arrays.asList( resultSetReservations.getArray("hotel_facility_resources").toString());
                ArrayList<String> namesStringsArray = new ArrayList<String>();
                ArrayList<Integer> idsIntArray = new ArrayList<Integer>();
                for (String item : columnArray.get(0).replace("{", "").replace("}","").split(",")) {
                    namesStringsArray.add(facilitiesModel.get(Integer.valueOf(item)-1).getFacilityName());
                    idsIntArray.add(facilitiesModel.get(Integer.valueOf(item)-1).getFacilityId());
                }
                model.setHotelFacilityResources(namesStringsArray);
                model.setHotelFacilityResourcesIds(idsIntArray);


                columnArray = new ArrayList<>(Arrays.asList(resultSetReservations.getArray("hotel_hostel_pension_types").toString()));
                namesStringsArray = new ArrayList<String>();
                idsIntArray = new ArrayList<Integer>();
                for (String item : columnArray.get(0).replace("{", "").replace("}","").split(",")) {
                    namesStringsArray.add(pensionsModel.get(Integer.valueOf(item)-1).getPensionName());
                    idsIntArray.add(pensionsModel.get(Integer.valueOf(item)-1).getPensionId());
                }
                model.setHotelPensionTypes(namesStringsArray);
                model.setHotelPensionTypesIds(idsIntArray);

                hotelsSeasonsAndRoomsModelArrayList.add(model);
            }
            return hotelsSeasonsAndRoomsModelArrayList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return hotelsSeasonsAndRoomsModelArrayList;
    }

    public ArrayList<ReservationModel> getAllReservations() {
        ArrayList<ReservationModel> reservationModels = new ArrayList<ReservationModel>();

        String queryReservation = "SELECT * FROM public.reservations ORDER BY \"reservation_Id\";";
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(queryReservation);

            ResultSet resultSetReservations = preparedStatementHotels.executeQuery();
            while(resultSetReservations.next()) {
                ReservationModel model = new ReservationModel();
                model.setReservationId(resultSetReservations.getInt("reservation_Id"));
                model.setReservationFullName(resultSetReservations.getString("reservation_full_name"));
                model.setReservationIdentificationNumber(resultSetReservations.getString("reservation_identification_number"));
                model.setReservationPhoneNumber(resultSetReservations.getString("reservation_phone"));
                model.setReservationEmail(resultSetReservations.getString("reservation_email"));
                model.setReservationAdultCount(resultSetReservations.getInt("reservation_adult"));
                model.setReservationChildCount(resultSetReservations.getInt("reservation_child"));
                model.setReservationCheckInDate(resultSetReservations.getString("reservation_check_in_date"));
                model.setReservationCheckOutDate(resultSetReservations.getString("reservation_check_out_date"));
                model.setReservationHotelId(resultSetReservations.getInt("reservation_hotel_id"));

                reservationModels.add(model);
            }
            return reservationModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return reservationModels;
    }
    public Boolean deleteReservation(ReservationModel reservationModel) {
        String queryReservationDelete = "DELETE FROM public.\"reservations\" WHERE \"reservation_Id\" = ?";
        try{
            PreparedStatement preparedStatementReservationDelete = this.connection.prepareStatement(queryReservationDelete);
            preparedStatementReservationDelete.setInt(1,reservationModel.getReservationId());

            if(preparedStatementReservationDelete.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    private boolean reservationQuery(String queryReservation, Integer hotelId, ReservationModel reservationModel) {
        try{
            PreparedStatement preparedStatementReservation = this.connection.prepareStatement(queryReservation);
            preparedStatementReservation.setString(1,reservationModel.getReservationFullName());
            preparedStatementReservation.setString(2,reservationModel.getReservationIdentificationNumber());
            preparedStatementReservation.setString(3,reservationModel.getReservationPhoneNumber());
            preparedStatementReservation.setString(4,reservationModel.getReservationEmail());
            preparedStatementReservation.setInt(5,reservationModel.getReservationAdultCount());
            preparedStatementReservation.setInt(6,reservationModel.getReservationChildCount());
            preparedStatementReservation.setDate(7,reservationModel.getReservationCheckInDate());
            preparedStatementReservation.setDate(8,reservationModel.getReservationCheckOutDate());
            preparedStatementReservation.setInt(9,hotelId);
            if(reservationModel.getReservationId() != null)
                preparedStatementReservation.setInt(10,reservationModel.getReservationId());

            if(preparedStatementReservation.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
