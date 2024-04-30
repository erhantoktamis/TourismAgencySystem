package entity;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelModel {
    private Integer hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelEmail;
    private String hotelPhone;
    private Integer hotelStar;
    private ArrayList<String> hotelFacilityResources;
    private ArrayList<String> hotelPensionTypes;

    private ArrayList<Integer> hotelFacilityResourcesIds;
    private ArrayList<Integer> hotelPensionTypesIds;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public Integer getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(Integer hotelStar) {
        this.hotelStar = hotelStar;
    }

    public ArrayList<String> getHotelFacilityResources() {
        return hotelFacilityResources;
    }
    public void setHotelFacilityResources(ArrayList<String> hotelFacilityResources) {
        this.hotelFacilityResources = hotelFacilityResources;
    }

    public ArrayList<String> getHotelPensionTypes() {
        return hotelPensionTypes;
    }


    public void setHotelPensionTypes(ArrayList<String> hotelPensionTypes) {
        this.hotelPensionTypes = hotelPensionTypes;
    }

    public ArrayList<Integer> getHotelFacilityResourcesIds() {
        return hotelFacilityResourcesIds;
    }

    public void setHotelFacilityResourcesIds(ArrayList<Integer> hotelFacilityResourcesIds) {
        this.hotelFacilityResourcesIds = hotelFacilityResourcesIds;
    }

    public ArrayList<Integer> getHotelPensionTypesIds() {
        return hotelPensionTypesIds;
    }

    public void setHotelPensionTypesIds(ArrayList<Integer> hotelPensionTypesIds) {
        this.hotelPensionTypesIds = hotelPensionTypesIds;
    }
}
