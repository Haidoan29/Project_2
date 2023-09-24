/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.cafemangement;

/**
 *
 * @author Admin
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class product {
    private final StringProperty maMon = new SimpleStringProperty();
    private final StringProperty tenMon = new SimpleStringProperty();
    private final DoubleProperty giaBan = new SimpleDoubleProperty();
    private final StringProperty trangThaiBan = new SimpleStringProperty("Đang bán"); // Set default value
    private final StringProperty id = new SimpleStringProperty(); // Define the id property

    public product(String maMon, String tenMon, double giaBan) {
        setMaMon(maMon);
        setTenMon(tenMon);
        setGiaBan(giaBan);
    }

    // Getter and Setter methods for each property
    public String getMaMon() {
        return maMon.get();
    }

    public void setMaMon(String value) {
        maMon.set(value);
    }

    public StringProperty maMonProperty() {
        return maMon;
    }

    public String getTenMon() {
        return tenMon.get();
    }

    public void setTenMon(String value) {
        tenMon.set(value);
    }

    public StringProperty tenMonProperty() {
        return tenMon;
    }

    public double getGiaBan() {
        return giaBan.get();
    }

    public void setGiaBan(double value) {
        giaBan.set(value);
    }

    public DoubleProperty giaBanProperty() {
        return giaBan;
    }

    public String getTrangThaiBan() {
        return trangThaiBan.get();
    }

    public void setTrangThaiBan(String value) {
        trangThaiBan.set(value);
    }

    public StringProperty trangThaiBanProperty() {
        return trangThaiBan;
    }

    // Getter and Setter for the id property
    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }
}
