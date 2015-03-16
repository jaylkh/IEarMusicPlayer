package com.ldw.music.uimanager;

import java.util.ArrayList;
import java.util.List;

import com.ldw.music.R;
import com.ldw.music.model.RemoteWifiInfo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 远程设备wifi配置
 * @author jaylkh
 *
 */
public class RemoteWifiManager extends RemoteManager implements OnClickListener, OnItemClickListener, OnItemLongClickListener {

	ImageButton backBtn;
	ImageButton refreshBtn;
	ListView mWifiList;
	MyAdapter mAdapter;
	List<RemoteWifiInfo> list = new ArrayList<RemoteWifiInfo>();
	public RemoteWifiManager(Activity mActivity, UIManager uiManager) {
		super(mActivity, uiManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setBgByPath(String path) {
		// TODO Auto-generated method stub
		super.setBgByPath(path);
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		View wifiView = mInflater.inflate(R.layout.remotewifi, null);
		initBg(wifiView);
		initView(wifiView);
		return wifiView;
	}
	

	@Override
	public View getView(int from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getView(int from, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void initBg(View view)
	{
		super.initBg(view, R.id.main_remotewifi_layout);
	}
	private void initView(View view)
	{
		backBtn = (ImageButton) view.findViewById(R.id.backBtn);
		refreshBtn = (ImageButton) view.findViewById(R.id.refreshBtn);
		backBtn.setOnClickListener(this);
		refreshBtn.setOnClickListener(this);
		
		mAdapter = new MyAdapter(mActivity);
		mWifiList = (ListView) view.findViewById(R.id.wifi_listview);
		mWifiList.setAdapter(mAdapter);
		mWifiList.setOnItemClickListener(this);
		mWifiList.setOnItemLongClickListener(this);
		
		
		initData();
	}
	private void initData()
	{
		RemoteWifiInfo wifiInfo = new RemoteWifiInfo("Unisound");
		RemoteWifiInfo wifiInfo1 = new RemoteWifiInfo("Jaylkh");
		RemoteWifiInfo wifiInfo2 = new RemoteWifiInfo("Lenovo-PC");
		RemoteWifiInfo wifiInfo3 = new RemoteWifiInfo("Jaylkh-PC");
		RemoteWifiInfo wifiInfo4 = new RemoteWifiInfo("Wifi-360");
		RemoteWifiInfo wifiInfo5 = new RemoteWifiInfo("CMCC-100");
		
		wifiInfo3.setConnectStatus(true);
		list.add(wifiInfo);
		list.add(wifiInfo1);
		list.add(wifiInfo2);
		list.add(wifiInfo3);
		list.add(wifiInfo4);
		list.add(wifiInfo5);
		
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.backBtn:
			mUIManager.setCurrentItem();
			break;
		case R.id.refreshBtn:
			//发送指令到耳机中，更新wifi连接状态
			break;
		}
	}
	
	class MyAdapter extends BaseAdapter{
        Context mContext;
        LayoutInflater mInflater;
		public MyAdapter(Context context)
		{
			this.mContext = context;
			this.mInflater = LayoutInflater.from(mContext);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			RemoteWifiInfo wifiInfo = list.get(position);
			if(null == convertView)
			{
				convertView = mInflater.inflate(R.layout.remotewifi_item, null);
				holder = new ViewHolder();
				holder.ssid_tv = (TextView) convertView.findViewById(R.id.ssid_tv);
				holder.state_tv = (TextView) convertView.findViewById(R.id.state_tv);
				holder.state_iv = (ImageView) convertView.findViewById(R.id.state_iv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.ssid_tv.setText(wifiInfo.ssid);
			if(wifiInfo.isConnected())
			{
				holder.state_iv.setVisibility(View.VISIBLE);
				holder.state_tv.setText("已连接");
			}else
			{
				holder.state_iv.setVisibility(View.INVISIBLE);
				holder.state_tv.setText("断开连接");
			}
			return convertView;
		}
		class ViewHolder{
		   public TextView ssid_tv,state_tv;
		   public ImageView state_iv; 
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//单击了wifilist中的一项
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		Log.e("jaylk","长按了listview中的项！");
		return false;
	}
}
