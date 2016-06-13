package com.heartmusic;

public class singger {
       private String singger;
       private int num;
    public singger(String s,int i){
    	this.singger=s;
    	this.num=i;
    }
	public String getSingger() {
		return singger;
	}
	public void setSingger(String singger) {
		this.singger = singger;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int addNum(){
		return this.num+=1;
	}
       
}
