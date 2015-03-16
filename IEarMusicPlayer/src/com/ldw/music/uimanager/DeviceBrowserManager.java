package com.ldw.music.uimanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ldw.music.R;
import com.ldw.music.activity.IConstants;
import com.ldw.music.model.AlbumInfo;
import com.ldw.music.model.DeviceInfo;
import com.ldw.music.storage.SPStorage;

public class DeviceBrowserManager extends MainUIManager implements IConstants, OnClickListener, OnItemClickListener {
	private LayoutInflater mInflater;
	private Activity mActivity;
	private UIManager mUIManager;
	private RelativeLayout mFolderLayout;
	private ImageButton mBackBtn;//返回按钮
	private ImageButton mSearchBtn;//搜索按钮
	private ListView deviceList;
	private List<DeviceInfo> list = new ArrayList<DeviceInfo>();
	private MyAdapter mAdapter;
	
	public DeviceBrowserManager(Activity mActivity, UIManager uiManager) {
		// TODO Auto-generated constructor stub
		this.mActivity = mActivity;
		this.mUIManager = uiManager;
		this.mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	protected void setBgByPath(String path) {//设置背景
		// TODO Auto-generated method stub
		Bitmap bitmap = mUIManager.getBitmapByPath(path);
		if (bitmap != null) {
			mFolderLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
					.getResources(), bitmap));
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		//填充界面到view中
		View deviceView = mInflater.inflate(R.layout.devicebrower, null);
		initBg(deviceView);
		initView(deviceView);
		return deviceView;
	}
	
	private void initView(View deviceView) {//初始化控件及相关数据
		// TODO Auto-generated method stub
		mBackBtn = (ImageButton) deviceView.findViewById(R.id.backBtn);
		mSearchBtn = (ImageButton) deviceView.findViewById(R.id.searchBtn);
		mBackBtn.setOnClickListener(this);
		mSearchBtn.setOnClickListener(this);
		
		deviceList = (ListView) deviceView.findViewById(R.id.device_listview);
		mAdapter =  new MyAdapter();
		deviceList.setAdapter(mAdapter);
		deviceList.setOnItemClickListener(this);
		DeviceInfo info = new DeviceInfo();
		info.name ="iEar";
		info.macAdr="ef6c::ba4c::4532::acef";
		
		DeviceInfo info1 = new DeviceInfo();
		info1.name ="iEarPhone";
		info1.macAdr="ef6c::ba4c::4532::acef";
		list.add(info1);
		mAdapter.notifyDataSetChanged();
		Log.e("jaylkh", "Device brower init done!");
	}

	private void initBg(View view) {
		mFolderLayout = (RelativeLayout) view
				.findViewById(R.id.main_device_layout);//获得文件夹主界面的主体布局
		SPStorage mSp = new SPStorage(mActivity);
		String mDefaultBgPath = mSp.getPath();
		Bitmap bitmap = mUIManager.getBitmapByPath(mDefaultBgPath);
		if (bitmap != null) {//设置当前布局的背景
			mFolderLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
					.getResources(), bitmap));
		}
	}

	/**
	 * 设置设备列表数据
	 * @param deviceList
	 */
	public void setDevicesList(List<DeviceInfo> deviceList)
	{
		this.list = deviceList;
		if(mAdapter!=null)
		{
			mAdapter.notifyDataSetChanged();
		}
	}
	/**
	 * 添加单个设备到列表中
	 * @param device
	 */
	public void addDeviceToList(DeviceInfo device)
	{
		this.list.add(device);
		if(mAdapter!=null)
		{
			mAdapter.notifyDataSetChanged();
		}
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.backBtn://返回按钮
			mUIManager.setCurrentItem();
			break;
		case R.id.searchBtn://查询按钮
			
			break;
		}
	}
	
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public DeviceInfo getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			DeviceInfo device = list.get(position);

			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = mActivity.getLayoutInflater().inflate(
						R.layout.devicebrower_listitem, null);
				viewHolder.deviceNameTv = (TextView) convertView
						.findViewById(R.id.device_name_tv);
				viewHolder.macadrTv = (TextView) convertView
						.findViewById(R.id.macadr_of_device_tv);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			if(device!=null)
			{
			 viewHolder.deviceNameTv.setText(device.name);
			 viewHolder.macadrTv.setText(device.macAdr);
			}

			return convertView;
		}

		private class ViewHolder {
			TextView deviceNameTv, macadrTv;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		mUIManager.setContentType(DEVICE_TO_REMOTCONTROL,list.get(position));//设置跳转页面
	}
	

}
