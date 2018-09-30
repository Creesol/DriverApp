package com.delivery.startup.creesol.driversapp;

public class OrderData {
    String itemName;
    String Price;
    String quantity;
    String Item_id;

        String Order_Items;
        String Delivery_before;

    public String getItem_id() {
        return Item_id;
    }

    public void setItem_id(String item_id) {
        Item_id = item_id;
    }

    public String getOrder_Items() {
        return Order_Items;
    }

    public void setOrder_Items(String orderItems) {
        Order_Items = orderItems;
    }

    public String getDelivery_before() {
        return Delivery_before;
    }

    public void setDelivery_before(String deliverybefore) {
        Delivery_before = deliverybefore;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String total_Price) {
        Total_Price = total_Price;
    }

    String Total_Price;

    public OrderData(String item_id, String orderItems, String deliverybefore, String total_Price) {
        Item_id = item_id;
        Order_Items = orderItems;
        Delivery_before = deliverybefore;
        Total_Price = total_Price;
    }

    public OrderData(String itemName, String price, String quantity) {
        this.itemName = itemName;
        Price = price;
        this.quantity = quantity;

    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }





}
