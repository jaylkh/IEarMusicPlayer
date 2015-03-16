package com.ldw.music.model;
//��������Ϣ  Battery@[int:�����ٷֱȣ�]@[int:���״̬  0����   1�����ڳ��  2��δ���  3��������] 
public class RemoteBatteryInfo {
	public int currentBatLevel = 75;
	CHARGE_STATUS status = CHARGE_STATUS.NONE;
	 
	public void  setBatChargingStatus(CHARGE_STATUS status)
	{
		this.status = status;
	}
	public void  setBatChargingStatus(String status)
	{
		if(status!=null)
		{
			if(CHARGE_STATUS.CHARGCOMPLETED.toString().equals(status)){
				this.status = CHARGE_STATUS.CHARGCOMPLETED;
			}else
			if(CHARGE_STATUS.CHARGING.toString().equals(status)){
				this.status = CHARGE_STATUS.CHARGING;
			}else
			if(CHARGE_STATUS.UNCHARGE.toString().equals(status)){
				this.status = CHARGE_STATUS.UNCHARGE;
			}
		}
	}
	
	public CHARGE_STATUS getBatChargingStatus()
	{
		return this.status;
	}
}
enum CHARGE_STATUS{
	NONE,//��
	CHARGING,//���ڳ��
	UNCHARGE,//δ���
	CHARGCOMPLETED//������
}
