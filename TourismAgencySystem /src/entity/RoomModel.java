package entity;

public class RoomModel {
    private Integer roomId;
    private Integer roomTypeId;
    private Integer roomPensionTypeId;

    private Integer roomSeasonTypeId;
    private Integer roomStockCount;
    private String roomMeterSquare;
    private Boolean roomHaveTV;
    private Boolean roomHaveMinibar;
    private Boolean roomHavePS5;
    private Boolean roomHaveVault;
    private Boolean roomHaveProjection;
    private Integer roomBedNumber;
    private String roomTypeName;
    private String roomAdultPrice;
    private String roomChildPrice;
    private Integer roomHotelId;
    private String roomHotelName;
    private String roomHotelPlace;
    private String roomSeasonName;
    private String roomPensionTypeName;
    private String roomDoorNumbers;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomStockCount() {
        return roomStockCount;
    }

    public void setRoomStockCount(Integer roomStockCount) {
        this.roomStockCount = roomStockCount;
    }

    public String getRoomMeterSquare() {
        return roomMeterSquare;
    }

    public void setRoomMeterSquare(String roomMeterSquare) {
        this.roomMeterSquare = roomMeterSquare;
    }

    public String getRoomHaveTV() {
        return roomHaveTV == true ? "Yes": "No";
    }
    public Boolean getRoomHaveTVBool() {
        return roomHaveTV;
    }
    public void setRoomHaveTV(Boolean roomHaveTV) {
        this.roomHaveTV = roomHaveTV;
    }

    public Boolean getRoomHaveMinibarBool() {
        return roomHaveMinibar;
    }
    public String getRoomHaveMinibar() {
        return roomHaveMinibar == true ? "Yes": "No";
    }

    public void setRoomHaveMinibar(Boolean roomHaveMinibar) {
        this.roomHaveMinibar = roomHaveMinibar;
    }

    public Boolean getRoomHavePS5Bool() {
        return roomHavePS5;
    }
    public String getRoomHavePS5() {
        return roomHavePS5 == true ? "Yes" : "No";
    }

    public void setRoomHavePS5(Boolean roomHavePS5) {
        this.roomHavePS5 = roomHavePS5;
    }

    public Boolean getRoomHaveVaultBool() {
        return roomHaveVault;
    }
    public String getRoomHaveVault() {
        return roomHaveVault == true ? "Yes" : "No";
    }

    public void setRoomHaveVault(Boolean roomHaveVault) {
        this.roomHaveVault = roomHaveVault;
    }

    public Boolean getRoomHaveProjectionBool() {
        return roomHaveProjection;
    }
    public String getRoomHaveProjection() {
        return roomHaveProjection == true ? "Yes" : "No";
    }

    public void setRoomHaveProjection(Boolean roomHaveProjection) {
        this.roomHaveProjection = roomHaveProjection;
    }

    public Integer getRoomBedNumber() {
        return roomBedNumber;
    }

    public void setRoomBedNumber(Integer roomBedNumber) {
        this.roomBedNumber = roomBedNumber;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomAdultPrice() {
        return roomAdultPrice;
    }

    public void setRoomAdultPrice(String roomAdultPrice) {
        this.roomAdultPrice = roomAdultPrice;
    }

    public String getRoomChildPrice() {
        return roomChildPrice;
    }

    public void setRoomChildPrice(String roomChildPrice) {
        this.roomChildPrice = roomChildPrice;
    }

    public Integer getRoomHotelId() {
        return roomHotelId;
    }

    public void setRoomHotelId(Integer roomHotelId) {
        this.roomHotelId = roomHotelId;
    }

    public String getRoomSeasonName() {
        return roomSeasonName;
    }

    public void setRoomSeasonName(String roomSeasonName) {
        this.roomSeasonName = roomSeasonName;
    }

    public String getRoomPensionTypeName() {
        return roomPensionTypeName;
    }

    public void setRoomPensionTypeName(String roomPensionTypeName) {
        this.roomPensionTypeName = roomPensionTypeName;
    }

    public String getRoomDoorNumbers() {
        return roomDoorNumbers;
    }

    public void setRoomDoorNumbers(String roomDoorNumbers) {
        this.roomDoorNumbers = roomDoorNumbers;
    }

    public String getRoomHotelName() {
        return roomHotelName;
    }

    public void setRoomHotelName(String roomHotelName) {
        this.roomHotelName = roomHotelName;
    }

    public String getRoomHotelPlace() {
        return roomHotelPlace;
    }

    public void setRoomHotelPlace(String roomHotelPlace) {
        this.roomHotelPlace = roomHotelPlace;
    }


    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getRoomPensionTypeId() {
        return roomPensionTypeId;
    }

    public void setRoomPensionTypeId(Integer roomPensionTypeId) {
        this.roomPensionTypeId = roomPensionTypeId;
    }

    public Integer getRoomSeasonTypeId() {
        return roomSeasonTypeId;
    }

    public void setRoomSeasonTypeId(Integer roomSeasonTypeId) {
        this.roomSeasonTypeId = roomSeasonTypeId;
    }
}
