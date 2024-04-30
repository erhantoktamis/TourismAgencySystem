package entity;

public class HotelsSeasonsAndRoomsModel extends HotelModel {
    private Integer roomId;
    private Integer seasonId;
    private String roomName;
    private String seasonStartDate;
    private String seasonEndDate;
    private String roomStock;
    private String roomAdultPrice;
    private String roomChildPrice;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(String seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public String getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(String seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public String getRoomStock() {
        return roomStock;
    }

    public void setRoomStock(String roomStock) {
        this.roomStock = roomStock;
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
}

