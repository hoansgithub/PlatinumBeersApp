package com.ikt.platinumbeers.model;

public class Prize {
 private int prize_id;
 private String desc;
 private int value;
public Prize(int prizeid, String desc,int value) {
	this.setPrize_id(prizeid);
	this.setDesc(desc);
	this.setValue(value);
}
public Prize() {
	// TODO Auto-generated constructor stub
}
public int getPrize_id() {
	return prize_id;
}
public void setPrize_id(int prize_id) {
	this.prize_id = prize_id;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}

}
