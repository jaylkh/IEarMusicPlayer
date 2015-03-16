package com.ldw.music.model;


public class RemoteWifiInfo {
    public String ssid;
    private String connectStatus = Boolean.FALSE.toString();
    public RemoteWifiInfo(String ssid)
    {
    	this.ssid = ssid;
    }
	public Boolean isConnected()
	{
		return Boolean.parseBoolean(connectStatus);
	}
	public String getConnectStatus()
	{
		return connectStatus;
	}
	public void setConnectStatus(Boolean status)
	{
		connectStatus = status.toString();
	}
	public void setConnectStatus(String status)
	{
		connectStatus = status;
	}
}
