package com.RSMS.model;

public enum UserType {
	CUSTOMER("�ͻ�",1),WORKER("����ְҵ��",2),MANAGER("����Ա",3);
	private String name;
	private int index;
	private UserType(String name,int index){
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString(){
		return this.name;
	}
}
