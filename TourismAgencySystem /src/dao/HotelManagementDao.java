package dao;

import core.Db;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelManagementDao {
    private Connection connection;

    public HotelManagementDao() {
        this.connection = Db.getInstance();
    }

    // Hotel Service Methods
    public ArrayList<HotelModel> getAllHotelsDatas() {
        ArrayList<HotelModel> hotelModels = new ArrayList<HotelModel>();

        ArrayList<FacilityModel> facilitiesModel = getAllFacilities();
        ArrayList<PensionModel> pensionsModel = getAllPensionsDatas();

        String queryHotels = "SELECT * FROM public.hotel Order by hotel_id ASC;";
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(queryHotels);

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
    public ArrayList<Integer> getHotelsStars() {
        ArrayList<Integer> hotelModels = new ArrayList<Integer>();

        String queryHotels = "SELECT DISTINCT hotel_star FROM public.hotel Order by hotel_star DESC;";
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(queryHotels);

            ResultSet resultSetHotels = preparedStatementHotels.executeQuery();
            while(resultSetHotels.next()) {
                hotelModels.add(resultSetHotels.getInt("hotel_star"));
            }
            return hotelModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return hotelModels;
    }
    public ArrayList<HotelModel> getHotelsByStarsCount(Integer starCount) {
        ArrayList<HotelModel> hotelModels = new ArrayList<HotelModel>();

        ArrayList<FacilityModel> facilitiesModel = getAllFacilities();
        ArrayList<PensionModel> pensionsModel = getAllPensionsDatas();

        String queryHotels = "SELECT * FROM public.hotel WHERE hotel_star = ? Order by hotel_star DESC;";
        try{
            PreparedStatement preparedStatementHotels = this.connection.prepareStatement(queryHotels);
            preparedStatementHotels.setInt(1, starCount);

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
    public Boolean addNewHotel(HotelModel hotelModel) {
        String queryHotelAdd = "INSERT INTO public.hotel(\n" +
                "\thotel_name, hotel_address, hotel_email, hotel_phone, hotel_star, hotel_facility_resources, hotel_hostel_pension_types)\n" +
                "\tVALUES (?, ?, ?, ?, ?, Array[?], Array[?]);";
        try{
            PreparedStatement preparedStatementHotelAdd = this.connection.prepareStatement(queryHotelAdd);
            preparedStatementHotelAdd.setString(1,hotelModel.getHotelName());
            preparedStatementHotelAdd.setString(2,hotelModel.getHotelAddress());
            preparedStatementHotelAdd.setString(3,hotelModel.getHotelEmail());
            preparedStatementHotelAdd.setString(4,hotelModel.getHotelPhone());
            preparedStatementHotelAdd.setInt(5,hotelModel.getHotelStar());
            preparedStatementHotelAdd.setArray(6,connection.createArrayOf("BigInt", hotelModel.getHotelFacilityResourcesIds().toArray()));
            preparedStatementHotelAdd.setArray(7,connection.createArrayOf("BigInt", hotelModel.getHotelPensionTypesIds().toArray()));

            if(preparedStatementHotelAdd.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean updateHotel(HotelModel hotelModel) {
        String queryHotelUpdate = "UPDATE public.hotel\n" +
                "\tSET hotel_name=?, hotel_address=?, hotel_email=?, hotel_phone=?, hotel_star=?, hotel_facility_resources=?, hotel_hostel_pension_types=?\n" +
                "\tWHERE hotel_id = ?;";
        try{
            PreparedStatement preparedStatementHotelUpdate = this.connection.prepareStatement(queryHotelUpdate);
            preparedStatementHotelUpdate.setString(1,hotelModel.getHotelName());
            preparedStatementHotelUpdate.setString(2,hotelModel.getHotelAddress());
            preparedStatementHotelUpdate.setString(3,hotelModel.getHotelEmail());
            preparedStatementHotelUpdate.setString(4,hotelModel.getHotelPhone());
            preparedStatementHotelUpdate.setInt(5,hotelModel.getHotelStar());
            preparedStatementHotelUpdate.setArray(6,connection.createArrayOf("BigInt", hotelModel.getHotelFacilityResourcesIds().toArray()));
            preparedStatementHotelUpdate.setArray(7,connection.createArrayOf("BigInt", hotelModel.getHotelPensionTypesIds().toArray()));
            preparedStatementHotelUpdate.setInt(8,hotelModel.getHotelId());

            if(preparedStatementHotelUpdate.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean deleteHotel(HotelModel hotelModel) {

        String queryHotelDelete = "DELETE FROM public.hotel where hotel_id = ?";
        try{
            PreparedStatement preparedStatementHotelDelete = this.connection.prepareStatement(queryHotelDelete);
            preparedStatementHotelDelete.setInt(1,hotelModel.getHotelId().intValue());

            if(preparedStatementHotelDelete.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }



    // Seasons Service Methods
    public ArrayList<SeasonModel> getAllSeasonsDatas() {
        ArrayList<SeasonModel> seasonModels = new ArrayList<SeasonModel>();

        String querySeasons = "SELECT season_id, season_name, season_start_date, season_end_date, hotel_name, public.\"hotel\".hotel_id FROM public.season \n" +
                "INNER JOIN public.\"hotel\"\n" +
                "on public.season.hotel_id = public.hotel.hotel_id;\n";
        try{
            PreparedStatement preparedStatementSeasons = this.connection.prepareStatement(querySeasons);

            ResultSet resultSetSeasons = preparedStatementSeasons.executeQuery();
            while(resultSetSeasons.next()) {
                SeasonModel seasonModel = new SeasonModel();
                seasonModel.setSeasonId(resultSetSeasons.getInt("season_id"));
                seasonModel.setSeasonName(resultSetSeasons.getString("season_name"));
                seasonModel.setSeasonStartDate(resultSetSeasons.getString("season_start_date"));
                seasonModel.setSeasonEndDate(resultSetSeasons.getString("season_end_date"));
                seasonModel.setSeasonHotelId(resultSetSeasons.getInt("hotel_id"));
                seasonModel.setSeasonHotelName(resultSetSeasons.getString("hotel_name"));

                seasonModels.add(seasonModel);
            }
            return seasonModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return seasonModels;
    }
    public ArrayList<String> getAllSeasonNames() {
        ArrayList<String> seasonNamesModels = new ArrayList<String>();

        String querySeasons = "SELECT DISTINCT season_name FROM public.season;";
        try{
            PreparedStatement preparedStatementSeason = this.connection.prepareStatement(querySeasons);

            ResultSet resultSetSeasons = preparedStatementSeason.executeQuery();
            while(resultSetSeasons.next()) {
                seasonNamesModels.add(resultSetSeasons.getString("season_name"));
            }
            return seasonNamesModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return seasonNamesModels;
    }
    public ArrayList<SeasonModel> getSeasonsByHotelId(Integer selectedHotelId) {
        ArrayList<SeasonModel> seasonModels = new ArrayList<SeasonModel>();

        String querySeasons = "SELECT season_id, season_name, season_start_date, season_end_date, hotel_name, public.\"hotel\".hotel_id FROM public.season \n" +
                "INNER JOIN public.\"hotel\"\n" +
                "on public.season.hotel_id = public.hotel.hotel_id " +
                "where public.hotel.hotel_id = ?;";
        try{
            PreparedStatement preparedStatementSeason = this.connection.prepareStatement(querySeasons);
            preparedStatementSeason.setInt(1, selectedHotelId);

            ResultSet resultSetSeasons = preparedStatementSeason.executeQuery();
            while(resultSetSeasons.next()) {
                SeasonModel seasonModel = new SeasonModel();
                seasonModel.setSeasonId(resultSetSeasons.getInt("season_id"));
                seasonModel.setSeasonName(resultSetSeasons.getString("season_name"));
                seasonModel.setSeasonStartDate(resultSetSeasons.getString("season_start_date"));
                seasonModel.setSeasonEndDate(resultSetSeasons.getString("season_end_date"));
                seasonModel.setSeasonHotelId(resultSetSeasons.getInt("hotel_id"));
                seasonModel.setSeasonHotelName(resultSetSeasons.getString("hotel_name"));

                seasonModels.add(seasonModel);
            }
            return seasonModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return seasonModels;
    }
    public ArrayList<SeasonModel> getSeasonsBySeasonName(String selectedSeasonName) {
        ArrayList<SeasonModel> seasonModels = new ArrayList<SeasonModel>();

        String querySeasons = "SELECT season_id, season_name, season_start_date, season_end_date, hotel_name, public.\"hotel\".hotel_id FROM public.season \n" +
                "INNER JOIN public.\"hotel\"\n" +
                "on public.season.hotel_id = public.hotel.hotel_id " +
                "where season_name = ?;";
        try{
            PreparedStatement preparedStatementSeason = this.connection.prepareStatement(querySeasons);
            preparedStatementSeason.setString(1, selectedSeasonName);

            ResultSet resultSetSeasons = preparedStatementSeason.executeQuery();
            while(resultSetSeasons.next()) {
                SeasonModel seasonModel = new SeasonModel();
                seasonModel.setSeasonId(resultSetSeasons.getInt("season_id"));
                seasonModel.setSeasonName(resultSetSeasons.getString("season_name"));
                seasonModel.setSeasonStartDate(resultSetSeasons.getString("season_start_date"));
                seasonModel.setSeasonEndDate(resultSetSeasons.getString("season_end_date"));
                seasonModel.setSeasonHotelId(resultSetSeasons.getInt("hotel_id"));
                seasonModel.setSeasonHotelName(resultSetSeasons.getString("hotel_name"));

                seasonModels.add(seasonModel);
            }
            return seasonModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return seasonModels;
    }
    public Boolean addNewSeason(SeasonModel seasonModel) {

        String querySeasonAdd = "INSERT INTO public.season(\n" +
                "\tseason_name, season_start_date, season_end_date, hotel_id)\n" +
                "\tVALUES (?, ?, ?, ?);";
        try{
            PreparedStatement preparedStatementSeasonAdd = this.connection.prepareStatement(querySeasonAdd);
            preparedStatementSeasonAdd.setString(1,seasonModel.getSeasonName());
            preparedStatementSeasonAdd.setDate(2,seasonModel.getSeasonStartDateType());
            preparedStatementSeasonAdd.setDate(3,seasonModel.getSeasonEndDateType());
            preparedStatementSeasonAdd.setInt(4,seasonModel.getSeasonHotelId());

            if(preparedStatementSeasonAdd.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean updateSeason(SeasonModel seasonModel) {

        String querySeasonUpdate = "UPDATE public.season\n" +
                "\tSET season_name=?, season_start_date=?, season_end_date=?, hotel_id=?\n" +
                "\tWHERE season_id = ?;";
        try{
            PreparedStatement preparedStatementSeasonUpdate = this.connection.prepareStatement(querySeasonUpdate);
            preparedStatementSeasonUpdate.setString(1,seasonModel.getSeasonName());
            preparedStatementSeasonUpdate.setDate(2,seasonModel.getSeasonStartDateType());
            preparedStatementSeasonUpdate.setDate(3,seasonModel.getSeasonEndDateType());
            preparedStatementSeasonUpdate.setInt(4,seasonModel.getSeasonHotelId());

            if(preparedStatementSeasonUpdate.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean deleteSeason(SeasonModel seasonModel) {
        String querySeasonDelete = "DELETE FROM public.season where season_id = ?";
        try{
            PreparedStatement preparedStatementSeasonDelete = this.connection.prepareStatement(querySeasonDelete);
            preparedStatementSeasonDelete.setInt(1,seasonModel.getSeasonId());

            if(preparedStatementSeasonDelete.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }



    // Pensions Service Methods
    public ArrayList<PensionModel> getAllPensionsDatas() {
        ArrayList<PensionModel> pensionModels = new ArrayList<PensionModel>();

        String queryPensions = "SELECT * FROM public.\"hotelPensionType\";";
        try{
            PreparedStatement preparedStatementPensions = this.connection.prepareStatement(queryPensions);

            ResultSet resultSetPensionTypes = preparedStatementPensions.executeQuery();
            while(resultSetPensionTypes.next()) {
                PensionModel pensionModel = new PensionModel();
                pensionModel.setPensionId(resultSetPensionTypes.getInt("pension_id"));
                pensionModel.setPensionName(resultSetPensionTypes.getString("pension_name"));

                pensionModels.add(pensionModel);
            }
            return pensionModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pensionModels;
    }
    private ArrayList<String> getPensionsResources() {
        ArrayList<String> pensionsStrings = new ArrayList<String>();
        String queryFacilityResources = "SELECT pension_name FROM public.\"hotelPensionType\"";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryFacilityResources);

            ResultSet resultSetPension = preparedStatementUsername.executeQuery();
            while(resultSetPension.next()) {
                pensionsStrings.add(resultSetPension.getString("pension_name"));
            }
            return pensionsStrings;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pensionsStrings;
    }
    public Boolean deletePension(PensionModel pensionModel) {

        String queryPensionDelete = "DELETE FROM public.\"hotelPensionType\" WHERE pension_id = ?";
        try{
            PreparedStatement preparedStatementPensionDelete = this.connection.prepareStatement(queryPensionDelete);
            preparedStatementPensionDelete.setInt(1,pensionModel.getPensionId());

            if(preparedStatementPensionDelete.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean addNewPension(String pensionName) {

        String queryPensionAdd = "INSERT INTO public.\"hotelPensionType\" (pension_name) VALUES (?);";
        try{
            PreparedStatement preparedStatementPensionAdd = this.connection.prepareStatement(queryPensionAdd);
            preparedStatementPensionAdd.setString(1,pensionName);

            if(preparedStatementPensionAdd.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean updatePension(PensionModel pensionModel) {

        String queryPensionUpdate = "UPDATE public.\"hotelPensionType\" SET pension_name=? WHERE pension_id =?;";
        try{
            PreparedStatement preparedStatementPensionUpdate = this.connection.prepareStatement(queryPensionUpdate);
            preparedStatementPensionUpdate.setString(1,pensionModel.getPensionName());
            preparedStatementPensionUpdate.setInt(2,pensionModel.getPensionId());

            if(preparedStatementPensionUpdate.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }



    // Hotel Room Service Methods
    public ArrayList<RoomModel> getAllRoomsDatas() {
        ArrayList<RoomModel> roomModels = new ArrayList<RoomModel>();

        String queryRooms = "SELECT room_id,room_stock, room_metersquare,\n" +
                "room_tv, room_minibar, room_ps,room_vault,room_projection, room_bed_number,\n" +
                "room_door_number, public.\"hotelRoomTypes\".room_name , room_adult_price, room_child_price, \n" +
                "public.\"hotel\".hotel_name,public.\"hotel\".hotel_address,\n" +
                "public.\"season\".season_name, public.\"hotelPensionType\".pension_name,public.\"hotelRoom\".hotel_id " +
                " ,public.\"hotelRoom\".room_type_id, public.\"hotelRoom\".pension_type_id, public.\"hotelRoom\".season_id "+
                "FROM public.\"hotelRoom\"\n" +
                "Inner join public.\"hotel\" \n" +
                "on public.\"hotelRoom\".hotel_id = public.\"hotel\".hotel_id\n" +
                "Inner join public.\"hotelRoomTypes\" \n" +
                "on public.\"hotelRoom\".room_type_id = public.\"hotelRoomTypes\".room_type_id\n" +
                "Inner join public.\"season\" \n" +
                "on public.\"hotelRoom\".season_id = public.\"season\".season_id\n" +
                "Inner join public.\"hotelPensionType\" \n" +
                "on public.\"hotelRoom\".pension_type_id = public.\"hotelPensionType\".pension_id\n Order by public.\"hotel\".hotel_id, room_id asc";
        try{
            PreparedStatement preparedStatementRooms = this.connection.prepareStatement(queryRooms);

            ResultSet resultSetRooms = preparedStatementRooms.executeQuery();
            while(resultSetRooms.next()) {
                RoomModel roomModel = new RoomModel();
                roomModel.setRoomId(resultSetRooms.getInt("room_id"));
                roomModel.setRoomStockCount(resultSetRooms.getInt("room_stock"));
                roomModel.setRoomMeterSquare(resultSetRooms.getString("room_metersquare"));
                roomModel.setRoomHaveTV(resultSetRooms.getBoolean("room_tv"));
                roomModel.setRoomHaveMinibar(resultSetRooms.getBoolean("room_minibar"));
                roomModel.setRoomHavePS5(resultSetRooms.getBoolean("room_ps"));
                roomModel.setRoomHaveVault(resultSetRooms.getBoolean("room_vault"));
                roomModel.setRoomHaveProjection(resultSetRooms.getBoolean("room_projection"));
                roomModel.setRoomBedNumber(resultSetRooms.getInt("room_bed_number"));
                roomModel.setRoomDoorNumbers(resultSetRooms.getString("room_door_number"));
                roomModel.setRoomTypeName(resultSetRooms.getString("room_name"));
                roomModel.setRoomAdultPrice(resultSetRooms.getString("room_adult_price"));
                roomModel.setRoomChildPrice(resultSetRooms.getString("room_child_price"));
                roomModel.setRoomHotelName(resultSetRooms.getString("hotel_name"));
                roomModel.setRoomHotelPlace(resultSetRooms.getString("hotel_address"));
                roomModel.setRoomSeasonName(resultSetRooms.getString("season_name"));
                roomModel.setRoomPensionTypeName(resultSetRooms.getString("pension_name"));
                roomModel.setRoomTypeId(resultSetRooms.getInt("room_type_id"));
                roomModel.setRoomPensionTypeId(resultSetRooms.getInt("pension_type_id"));
                roomModel.setRoomSeasonTypeId(resultSetRooms.getInt("season_id"));
                roomModel.setRoomHotelId(resultSetRooms.getInt("hotel_id"));

                roomModels.add(roomModel);
            }
            return roomModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return roomModels;
    }
    public ArrayList<RoomModel> getSelectedHotelRooms(Integer selectedHotelId) {
        ArrayList<RoomModel> roomModels = new ArrayList<RoomModel>();

        String queryRooms = "SELECT room_id,room_stock, room_metersquare,\n" +
                "room_tv, room_minibar, room_ps,room_vault,room_projection, room_bed_number,\n" +
                "room_door_number, public.\"hotelRoomTypes\".room_name , room_adult_price, room_child_price, \n" +
                "public.\"hotel\".hotel_name,public.\"hotel\".hotel_address,public.\"hotelRoom\".hotel_id,\n" +
                "public.\"season\".season_name, public.\"hotelPensionType\".pension_name\n " +
                "\n ,public.\"hotelRoom\".room_type_id, public.\"hotelRoom\".pension_type_id, public.\"hotelRoom\".season_id " +
                "\n" +
                "FROM public.\"hotelRoom\"\n" +
                "Inner join public.\"hotel\" \n" +
                "on public.\"hotelRoom\".hotel_id = public.\"hotel\".hotel_id\n" +
                "Inner join public.\"hotelRoomTypes\" \n" +
                "on public.\"hotelRoom\".room_type_id = public.\"hotelRoomTypes\".room_type_id\n" +
                "Inner join public.\"season\" \n" +
                "on public.\"hotelRoom\".season_id = public.\"season\".season_id\n" +
                "Inner join public.\"hotelPensionType\" \n" +
                "on public.\"hotelRoom\".pension_type_id = public.\"hotelPensionType\".pension_id\n" +
                " Where public.\"hotelRoom\".hotel_id = ?";
        try{
            PreparedStatement preparedStatementRooms = this.connection.prepareStatement(queryRooms);
            preparedStatementRooms.setInt(1, selectedHotelId);

            ResultSet resultSetRooms = preparedStatementRooms.executeQuery();
            while(resultSetRooms.next()) {
                RoomModel roomModel = new RoomModel();
                roomModel.setRoomId(resultSetRooms.getInt("room_id"));
                roomModel.setRoomStockCount(resultSetRooms.getInt("room_stock"));
                roomModel.setRoomMeterSquare(resultSetRooms.getString("room_metersquare"));
                roomModel.setRoomHaveTV(resultSetRooms.getBoolean("room_tv"));
                roomModel.setRoomHaveMinibar(resultSetRooms.getBoolean("room_minibar"));
                roomModel.setRoomHavePS5(resultSetRooms.getBoolean("room_ps"));
                roomModel.setRoomHaveVault(resultSetRooms.getBoolean("room_vault"));
                roomModel.setRoomHaveProjection(resultSetRooms.getBoolean("room_projection"));
                roomModel.setRoomBedNumber(resultSetRooms.getInt("room_bed_number"));
                roomModel.setRoomDoorNumbers(resultSetRooms.getString("room_door_number"));
                roomModel.setRoomTypeName(resultSetRooms.getString("room_name"));
                roomModel.setRoomAdultPrice(resultSetRooms.getString("room_adult_price"));
                roomModel.setRoomChildPrice(resultSetRooms.getString("room_child_price"));
                roomModel.setRoomHotelName(resultSetRooms.getString("hotel_name"));
                roomModel.setRoomHotelPlace(resultSetRooms.getString("hotel_address"));
                roomModel.setRoomSeasonName(resultSetRooms.getString("season_name"));
                roomModel.setRoomPensionTypeName(resultSetRooms.getString("pension_name"));
                roomModel.setRoomTypeId(resultSetRooms.getInt("room_type_id"));
                roomModel.setRoomPensionTypeId(resultSetRooms.getInt("pension_type_id"));
                roomModel.setRoomSeasonTypeId(resultSetRooms.getInt("season_id"));
                roomModel.setRoomHotelId(resultSetRooms.getInt("hotel_id"));

                roomModels.add(roomModel);
            }
            return roomModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return roomModels;
    }
    public Boolean addHotelRoom(RoomModel roomModel) {
        String queryRoomAdd = "INSERT INTO public.\"hotelRoom\"(\n" +
                "\t room_stock, room_metersquare, room_tv, room_minibar, room_ps, room_vault, room_projection, room_bed_number, room_type_id, room_adult_price, room_child_price, hotel_id, season_id, pension_type_id, room_door_number)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement preparedStatementRoomAdd = this.connection.prepareStatement(queryRoomAdd);
            preparedStatementRoomAdd.setInt(1,roomModel.getRoomStockCount());
            preparedStatementRoomAdd.setString(2,roomModel.getRoomMeterSquare());
            preparedStatementRoomAdd.setBoolean(3,roomModel.getRoomHaveTVBool());
            preparedStatementRoomAdd.setBoolean(4,roomModel.getRoomHaveMinibarBool());
            preparedStatementRoomAdd.setBoolean(5,roomModel.getRoomHavePS5Bool());
            preparedStatementRoomAdd.setBoolean(6,roomModel.getRoomHaveVaultBool());
            preparedStatementRoomAdd.setBoolean(7,roomModel.getRoomHaveProjectionBool());
            preparedStatementRoomAdd.setInt(8,roomModel.getRoomBedNumber());
            preparedStatementRoomAdd.setInt(9,roomModel.getRoomTypeId());
            preparedStatementRoomAdd.setString(10,roomModel.getRoomAdultPrice());
            preparedStatementRoomAdd.setString(11,roomModel.getRoomChildPrice());
            preparedStatementRoomAdd.setInt(12,roomModel.getRoomHotelId());
            preparedStatementRoomAdd.setInt(13,roomModel.getRoomSeasonTypeId());
            preparedStatementRoomAdd.setInt(14,roomModel.getRoomPensionTypeId());
            preparedStatementRoomAdd.setString(15,roomModel.getRoomDoorNumbers());


            if(preparedStatementRoomAdd.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean updateHotelRoom(RoomModel roomModel) {
        String queryRoomUpdate = "Update public.\"hotelRoom\" \n" +
                "SET room_stock=?, room_metersquare=?, room_tv=?, room_minibar=?, room_ps=?, room_vault=?, " +
                "room_projection=?, room_bed_number=?, room_type_id=?, room_adult_price=?, room_child_price=?, " +
                "hotel_id=?, season_id=?, pension_type_id=?, room_door_number=? WHERE room_id = ?";
        try{
            PreparedStatement preparedStatementRoomUpdate = this.connection.prepareStatement(queryRoomUpdate);
            preparedStatementRoomUpdate.setInt(1,roomModel.getRoomStockCount());
            preparedStatementRoomUpdate.setString(2,roomModel.getRoomMeterSquare());
            preparedStatementRoomUpdate.setBoolean(3,roomModel.getRoomHaveTVBool());
            preparedStatementRoomUpdate.setBoolean(4,roomModel.getRoomHaveMinibarBool());
            preparedStatementRoomUpdate.setBoolean(5,roomModel.getRoomHavePS5Bool());
            preparedStatementRoomUpdate.setBoolean(6,roomModel.getRoomHaveVaultBool());
            preparedStatementRoomUpdate.setBoolean(7,roomModel.getRoomHaveProjectionBool());
            preparedStatementRoomUpdate.setInt(8,roomModel.getRoomBedNumber());
            preparedStatementRoomUpdate.setInt(9,roomModel.getRoomTypeId());
            preparedStatementRoomUpdate.setString(10,roomModel.getRoomAdultPrice());
            preparedStatementRoomUpdate.setString(11,roomModel.getRoomChildPrice());
            preparedStatementRoomUpdate.setInt(12,roomModel.getRoomHotelId());
            preparedStatementRoomUpdate.setInt(13,roomModel.getRoomSeasonTypeId());
            preparedStatementRoomUpdate.setInt(14,roomModel.getRoomPensionTypeId());
            preparedStatementRoomUpdate.setString(15,roomModel.getRoomDoorNumbers());
            preparedStatementRoomUpdate.setInt(16,roomModel.getRoomId());


            if(preparedStatementRoomUpdate.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Boolean deleteRoom(RoomModel roomModel) {
        String queryRoomDelete = "DELETE FROM public.\"hotelRoom\" WHERE room_id = ?";
        try{
            PreparedStatement preparedStatementRoomDelete = this.connection.prepareStatement(queryRoomDelete);
            preparedStatementRoomDelete.setInt(1,roomModel.getRoomId());

            if(preparedStatementRoomDelete.executeUpdate()>0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ArrayList<HotelRoomTypes> getAllHotelRoomTypesDatas() {
        ArrayList<HotelRoomTypes> hotelRoomTypes = new ArrayList<HotelRoomTypes>();

        String queryHotelRoomTypes = "SELECT * FROM public.\"hotelRoomTypes\";";
        try{
            PreparedStatement preparedStatementHotelRoomTypes = this.connection.prepareStatement(queryHotelRoomTypes);

            ResultSet resultSetHotelRoomTypes = preparedStatementHotelRoomTypes.executeQuery();
            while(resultSetHotelRoomTypes.next()) {
                HotelRoomTypes hotelRoomType = new HotelRoomTypes();
                hotelRoomType.setRoomTypeId(resultSetHotelRoomTypes.getInt("room_type_id"));
                hotelRoomType.setRoomName(resultSetHotelRoomTypes.getString("room_name"));

                hotelRoomTypes.add(hotelRoomType);
            }
            return hotelRoomTypes;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return hotelRoomTypes;
    }



    // Facilities Service Methods
    public ArrayList<FacilityModel> getAllFacilities() {
        ArrayList<FacilityModel> facilityModels = new ArrayList<FacilityModel>();
        String queryFacilityResources = "Select * from public.\"hotelFacilityResource\"";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryFacilityResources);

            ResultSet resultSetFacility = preparedStatementUsername.executeQuery();
            while(resultSetFacility.next()) {
                FacilityModel facilityModel = new FacilityModel();
                facilityModel.setFacilityId(resultSetFacility.getInt("facility_Id"));
                facilityModel.setFacilityName(resultSetFacility.getString("facility_resource_name"));

                facilityModels.add(facilityModel);
            }
            return facilityModels;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return facilityModels;
    }
    public ArrayList<String> getFacilityResources() {
        ArrayList<String> facilityStrings = new ArrayList<String>();
        String queryFacilityResources = "Select facility_resource_name from public.\"hotelFacilityResource\"";
        try{
            PreparedStatement preparedStatementUsername = this.connection.prepareStatement(queryFacilityResources);

            ResultSet resultSetFacility = preparedStatementUsername.executeQuery();
            while(resultSetFacility.next()) {
                facilityStrings.add(resultSetFacility.getString("facility_resource_name"));
            }
            return facilityStrings;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return facilityStrings;
    }
}
