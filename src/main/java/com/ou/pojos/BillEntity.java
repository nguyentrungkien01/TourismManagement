package com.ou.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Bill", schema = "TourismManagement")
public class BillEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bill_id")
    private int billId;
    @Basic
    @Column(name = "bill_created_date")
    private Timestamp billCreatedDate;
    @Basic
    @Column(name = "bill_total_money")
    private Integer billTotalMoney;
    @Basic
    @Column(name = "bill_total_sale_money")
    private Integer billTotalSaleMoney;
    @Basic
    @Column(name = "bill_ship_date")
    private Timestamp billShipDate;
    @Basic
    @Column(name = "bill_ship_address")
    private String billShipAddress;
    @Basic
    @Column(name = "bill_ship_district")
    private String billShipDistrict;
    @Basic
    @Column(name = "bill_ship_city")
    private String billShipCity;
    @Basic
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "tour_id")
    private int tourId;
    @Basic
    @Column(name = "payt_id")
    private int paytId;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Timestamp getBillCreatedDate() {
        return billCreatedDate;
    }

    public void setBillCreatedDate(Timestamp billCreatedDate) {
        this.billCreatedDate = billCreatedDate;
    }

    public Integer getBillTotalMoney() {
        return billTotalMoney;
    }

    public void setBillTotalMoney(Integer billTotalMoney) {
        this.billTotalMoney = billTotalMoney;
    }

    public Integer getBillTotalSaleMoney() {
        return billTotalSaleMoney;
    }

    public void setBillTotalSaleMoney(Integer billTotalSaleMoney) {
        this.billTotalSaleMoney = billTotalSaleMoney;
    }

    public Timestamp getBillShipDate() {
        return billShipDate;
    }

    public void setBillShipDate(Timestamp billShipDate) {
        this.billShipDate = billShipDate;
    }

    public String getBillShipAddress() {
        return billShipAddress;
    }

    public void setBillShipAddress(String billShipAddress) {
        this.billShipAddress = billShipAddress;
    }

    public String getBillShipDistrict() {
        return billShipDistrict;
    }

    public void setBillShipDistrict(String billShipDistrict) {
        this.billShipDistrict = billShipDistrict;
    }

    public String getBillShipCity() {
        return billShipCity;
    }

    public void setBillShipCity(String billShipCity) {
        this.billShipCity = billShipCity;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getPaytId() {
        return paytId;
    }

    public void setPaytId(int paytId) {
        this.paytId = paytId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillEntity that = (BillEntity) o;
        return billId == that.billId && accId == that.accId && tourId == that.tourId && paytId == that.paytId && Objects.equals(billCreatedDate, that.billCreatedDate) && Objects.equals(billTotalMoney, that.billTotalMoney) && Objects.equals(billTotalSaleMoney, that.billTotalSaleMoney) && Objects.equals(billShipDate, that.billShipDate) && Objects.equals(billShipAddress, that.billShipAddress) && Objects.equals(billShipDistrict, that.billShipDistrict) && Objects.equals(billShipCity, that.billShipCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, billCreatedDate, billTotalMoney, billTotalSaleMoney, billShipDate, billShipAddress, billShipDistrict, billShipCity, accId, tourId, paytId);
    }
}