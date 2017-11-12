package com.example.tuan.myapplication.model;

/**
 * Created by phamt on 24.07.2017.
 */

public class TradeOffer {
    public int offerId;
    public int id;
    public int takerId;
    public Cost supply;
    public Cost demand;

    public TradeOffer(int id , int offerId , Cost su , Cost dem  ) {
        this.id  =  id ;
        this.offerId = offerId;
        this.supply = su;
        this.demand = dem;
    }

    public  void setTakerId (int takerID) {
        this.takerId = takerID;
    }


}
