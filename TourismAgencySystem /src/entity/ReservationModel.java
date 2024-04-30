package entity;

import java.sql.Date;

public class ReservationModel {
    private Integer reservationId;
    private Integer reservationHotelId;
    private String reservationFullName;
    private String reservationIdentificationNumber;
    private String reservationEmail;
    private String reservationPhoneNumber;
    private Integer reservationAdultCount;
    private Integer reservationChildCount;
    private String reservationCheckInDate;
    private String reservationCheckOutDate;
    private Integer reservationTotalPrice;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationHotelId() {
        return reservationHotelId;
    }

    public void setReservationHotelId(Integer reservationHotelId) {
        this.reservationHotelId = reservationHotelId;
    }

    public String getReservationFullName() {
        return reservationFullName;
    }

    public void setReservationFullName(String reservationFullName) {
        this.reservationFullName = reservationFullName;
    }

    public String getReservationIdentificationNumber() {
        return reservationIdentificationNumber;
    }

    public void setReservationIdentificationNumber(String reservationIdentificationNumber) {
        this.reservationIdentificationNumber = reservationIdentificationNumber;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public String getReservationPhoneNumber() {
        return reservationPhoneNumber;
    }

    public void setReservationPhoneNumber(String reservationPhoneNumber) {
        this.reservationPhoneNumber = reservationPhoneNumber;
    }

    public Integer getReservationAdultCount() {
        return reservationAdultCount;
    }

    public void setReservationAdultCount(Integer reservationAdultCount) {
        this.reservationAdultCount = reservationAdultCount;
    }

    public Integer getReservationChildCount() {
        return reservationChildCount;
    }

    public void setReservationChildCount(Integer reservationChildCount) {
        this.reservationChildCount = reservationChildCount;
    }

    public String getReservationCheckInDateString() {
        return reservationCheckInDate;
    }

    public Date getReservationCheckInDate() {
        return Date.valueOf(reservationCheckInDate);
    }

    public void setReservationCheckInDate(String reservationCheckInDate) {
        this.reservationCheckInDate = reservationCheckInDate;
    }

    public String getReservationCheckOutDateString() {
        return reservationCheckOutDate;
    }

    public Date getReservationCheckOutDate() {
        return Date.valueOf(reservationCheckOutDate);
    }

    public void setReservationCheckOutDate(String reservationCheckOutDate) {
        this.reservationCheckOutDate = reservationCheckOutDate;
    }

    public Integer getReservationTotalPrice() {
        return reservationTotalPrice;
    }

    public void setReservationTotalPrice(Integer reservationTotalPrice) {
        this.reservationTotalPrice = reservationTotalPrice;
    }
}
