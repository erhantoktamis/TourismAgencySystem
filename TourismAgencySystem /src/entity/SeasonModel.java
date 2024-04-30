package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class SeasonModel {
    private Integer seasonId;
    private String seasonName;
    private String seasonStartDate;
    private String seasonEndDate;
    private String seasonHotelName;
    private Integer seasonHotelId;


    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonStartDate() {
        return seasonStartDate;
    }
    public Date getSeasonStartDateType() {
        return Date.valueOf(seasonStartDate);
    }

    public void setSeasonStartDate(String seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public String getSeasonEndDate() {
        return seasonEndDate;
    }
    public Date getSeasonEndDateType() {
        return Date.valueOf(seasonEndDate);
    }

    public void setSeasonEndDate(String seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public String getSeasonHotelName() {
        return seasonHotelName;
    }

    public void setSeasonHotelName(String seasonHotelName) {
        this.seasonHotelName = seasonHotelName;
    }

    public Integer getSeasonHotelId() {
        return seasonHotelId;
    }

    public void setSeasonHotelId(Integer seasonHotelId) {
        this.seasonHotelId = seasonHotelId;
    }
}
