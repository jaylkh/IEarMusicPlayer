package com.ldw.music.uimanager;

import java.util.ArrayList;
import java.util.List;

import com.ldw.music.R;
import com.ldw.music.model.RemoteTransfeSongInfo;
import com.ldw.music.model.RemoteWifiInfo;
import com.ldw.music.uimanager.RemoteWifiManager.MyAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * wifi 文件推送
 * @author jaylkh
 *
 */
public class RemoteWifiTransferManager extends RemoteManager implements OnClickListener, OnItemClickListener {

	ImageButton backBtn;
	ImageButton selectedBtn;
	ImageButton transferBtn;
	ImageButton selectAllBtn;
	ImageButton selectAllBtn1;
	ListView mMusicList;
	MyAdapter mAdapter;
	List<RemoteTransfeSongInfo> list = new ArrayList<RemoteTransfeSongInfo>();
	
	public RemoteWifiTransferManager(Activity mActivity, UIManager uiManager) {
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
		View transferView = mInflater.inflate(R.layout.remotewifi_transfer, null);
		initBg(transferView);
		initView(transferView);
		return transferView;
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
		super.initBg(view, R.id.main_remotewifi_transfer_layout);
	}
	private void initView(View view)
	{
		backBtn = (ImageButton) view.findViewById(R.id.backBtn);
		selectedBtn = (ImageButton) view.findViewById(R.id.selected_imgbtn);
		transferBtn = (ImageButton) view.findViewById(R.id.transfer_imgbtn);
		selectAllBtn = (ImageButton) view.findViewById(R.id.checkbox_checked_ib);
		selectAllBtn1 = (ImageButton) view.findViewById(R.id.checkbox_normal_ib);
		
		backBtn.setOnClickListener(this);
		selectedBtn.setOnClickListener(this);
		transferBtn.setOnClickListener(this);
		selectAllBtn.setOnClickListener(this);
		selectAllBtn1.setOnClickListener(this);
		
		
		mMusicList = (ListView) view.findViewById(R.id.transfer_lv);
		mAdapter = new MyAdapter(mActivity);
		mMusicList.setAdapter(mAdapter);
		mMusicList.setOnItemClickListener(this);
		initdata();
	}

	private void initdata()
	{
		for(int i = 0;i<15;i++)
		{
			RemoteTransfeSongInfo info = new RemoteTransfeSongInfo("song "+i, "artist", "path",false);
			list.add(info);
		}
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
		case R.id.selected_imgbtn://弹出已选音乐列表
			
			break;
		case R.id.transfer_imgbtn://开始文件传输
			
			break;		
		case R.id.checkbox_checked_ib:
		case R.id.checkbox_normal_ib:
			if(selectAllBtn.getVisibility() != View.VISIBLE)//没有全选
			{
				selectAllBtn.setVisibility(View.VISIBLE);
				//全部选择
				isSelectAll(true);
			}else
			{
				selectAllBtn.setVisibility(View.INVISIBLE);
				//取消全选
				isSelectAll(false);
			}
			break;
		}
		
	}
	private void isSelectAll(Boolean flag)
	{
		for(RemoteTransfeSongInfo info : list)
		{
			info.isSelected = flag;
		}
		mAdapter.notifyDataSetChanged();
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
			RemoteTransfeSongInfo songInfo = list.get(position);
			if(null == convertView)
			{
				convertView = mInflater.inflate(R.layout.remotewifi_transfer_item, null);
				holder = new ViewHolder();
				holder.musicName_tv = (TextView) convertView.findViewById(R.id.musicName_tv);
				holder.artist_tv = (TextView) convertView.findViewById(R.id.artist_tv);
				holder.music_head_iv = (ImageView) convertView.findViewById(R.id.music_head_iv);
//				holder.music_head_normal_iv = (ImageView) convertView.findViewById(R.id.music_head_normal_iv);
				holder.selected_iv = (ImageView) convertView.findViewById(R.id.selected_iv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			if(songInfo.isSelected)
			{
				holder.selected_iv.setVisibility(View.VISIBLE);			
				holder.music_head_iv.setVisibility(View.VISIBLE);
			}else{
				holder.selected_iv.setVisibility(View.INVISIBLE);
				holder.music_head_iv.setVisibility(View.INVISIBLE);
			}
			
			holder.musicName_tv.setText(songInfo.musicName);
			holder.artist_tv.setText(songInfo.artist);
			
			
			
			return convertView;
		}
		class ViewHolder{
		   public TextView musicName_tv,artist_tv;
		   public ImageView music_head_iv,selected_iv;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		RemoteTransfeSongInfo info = list.get(position);
		info.isSelected = !info.isSelected;
		list.remove(position);
		list.add(position, info);
		mAdapter.notifyDataSetChanged();
		//添加当前音乐到发送列表中
	}

}
