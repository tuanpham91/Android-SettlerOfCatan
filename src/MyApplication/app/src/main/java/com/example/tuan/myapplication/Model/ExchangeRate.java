package com.example.tuan.myapplication.model;

public class ExchangeRate {
	public Cost.ResourceType fromMaterial;
	public int rate;
	
	public ExchangeRate(Cost.ResourceType from, int rate) {
		this.fromMaterial = from;
		this.rate = rate;
	}
}
