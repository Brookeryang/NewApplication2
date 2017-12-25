package com.example.yyh.newapplication.entity;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/11/6 15:18
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class HotSellVo {
    private String unit;
    private String Model;
    private String NAME;
    private String ShowPicture;
    private double Price;
    private long ID;
    private String Storename;
    private double Sales;//销量

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getShowPicture() {
        return ShowPicture;
    }

    public void setShowPicture(String showPicture) {
        ShowPicture = showPicture;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getStorename() {
        return Storename;
    }

    public void setStorename(String storename) {
        Storename = storename;
    }

    public double getSales() {
        return Sales;
    }

    public void setSales(double sales) {
        Sales = sales;
    }
}
