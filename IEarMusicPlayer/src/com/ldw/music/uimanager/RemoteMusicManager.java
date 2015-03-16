package com.ldw.music.uimanager;

import java.util.ArrayList;
import java.util.List;

import com.ldw.music.R;
import com.ldw.music.activity.IConstants;
import com.ldw.music.activity.MainContentActivity;
import com.ldw.music.model.DeviceInfo;
import com.ldw.music.model.MusicInfo;
import com.ldw.music.model.RemoteSongInfo;
import com.ldw.music.storage.SPStorage;
import com.ldw.music.utils.MusicUtils;
import com.ldw.music.view.AlwaysMarqueeTextView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 远程音乐控制
 * @author jaylkh
 *
 */
public class RemoteMusicManager extends RemoteManager implements  IConstants, OnClickListener, OnItemClickListener {
	
	private ImageButton mBackBtn;//返回按钮
	private ImageButton mRefreshBtn;//刷新按钮
	private ImageButton mPrevBtn;//刷新按钮
	private ImageButton mNextBtn;//刷新按钮
	private ImageButton mPlayBtn;//刷新按钮
	private ImageButton mPauseBtn;//刷新按钮
	private ImageButton mMenuBtn;//刷新按钮
	private AlwaysMarqueeTextView artistTv;//歌手
	private AlwaysMarqueeTextView musicNameTv;//音乐名称
	private TextView positionTv;//当前进度
	private TextView durationTv;//总进度
	private ProgressBar mPlaybackProgress;//进度条
	
	private ListView musicList;
	private List<RemoteSongInfo> list = new ArrayList<RemoteSongInfo>();
	private MyAdapter mAdapter;
	
	private int curtMusicIndex = -1;//当前选中的音乐
	private int playState = MPS_INVALID;
	

	public RemoteMusicManager(Activity mActivity, UIManager uiManager) {
		super(mActivity, uiManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		View musicView = mInflater.inflate(R.layout.remotemusic, null);
		initBg(musicView);
		initView(musicView);
		return musicView;
	}
	

	private void initView(View musicView) {
		// TODO Auto-generated method stub
		mBackBtn = (ImageButton) musicView.findViewById(R.id.backBtn);
		mRefreshBtn = (ImageButton) musicView.findViewById(R.id.refreshBtn);
		mPauseBtn = (ImageButton) musicView.findViewById(R.id.btn_pause2);
		mPlayBtn = (ImageButton) musicView.findViewById(R.id.btn_play2);
		mPrevBtn = (ImageButton) musicView.findViewById(R.id.btn_playPrev2);
		mNextBtn = (ImageButton) musicView.findViewById(R.id.btn_playNext2);
		mMenuBtn = (ImageButton) musicView.findViewById(R.id.btn_menu2);
		mBackBtn.setOnClickListener(this);
		mRefreshBtn.setOnClickListener(this);
		mPauseBtn.setOnClickListener(this);
		mPrevBtn.setOnClickListener(this);
		mPlayBtn.setOnClickListener(this);
		mNextBtn.setOnClickListener(this);
		mMenuBtn.setOnClickListener(this);
		
		//底部组件实例
		artistTv = (AlwaysMarqueeTextView) musicView.findViewById(R.id.artist_tv2);
		musicNameTv = (AlwaysMarqueeTextView) musicView.findViewById(R.id.musicname_tv2);
		positionTv = (TextView) musicView.findViewById(R.id.position_tv2);
		durationTv = (TextView) musicView.findViewById(R.id.duration_tv2);
		mPlaybackProgress = (ProgressBar) musicView.findViewById(R.id.playback_seekbar2);
	
		//音乐列表listview
		mAdapter = new MyAdapter(mActivity);
		musicList = (ListView) musicView.findViewById(R.id.music_listview);
		musicList.setAdapter(mAdapter);
		musicList.setOnItemClickListener(this);
		
		initData();//模拟数据		
	}

	private void initData()
	{
		RemoteSongInfo songInfo =new RemoteSongInfo("love story","Taylor",3*60*1000+30*1000+5000);//
		RemoteSongInfo songInfo1 =new RemoteSongInfo("新不了情","萧敬腾",3500);
		RemoteSongInfo songInfo2 =new RemoteSongInfo("空白格","杨宗纬",3500);
		RemoteSongInfo songInfo3 =new RemoteSongInfo("当你孤单你会想起谁","张栋梁",3500);
		RemoteSongInfo songInfo4 =new RemoteSongInfo("唯一","王力宏",3500);
		
		list.add(songInfo);
		list.add(songInfo1);
		list.add(songInfo2);
		list.add(songInfo3);
		list.add(songInfo4);
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 更新播放进度条
	 * @param curTime
	 * @param totalTime
	 */
	public void refreshSeekProgress(int curTime, int totalTime) {

		curTime /= 1000;
		totalTime /= 1000;
		int curminute = curTime / 60;
		int cursecond = curTime % 60;

		String curTimeString = String.format("%02d:%02d", curminute, cursecond);
		positionTv.setText(curTimeString);//设置当期进度

		int rate = 0;
		if (totalTime != 0) {
			rate = (int) ((float) curTime / totalTime * 100);
		}
		mPlaybackProgress.setProgress(rate);
	}
	/**
	 * 刷新UI
	 * @param curTime
	 * @param totalTime
	 * @param music
	 */
	public void refreshUI(int curTime, int totalTime, RemoteSongInfo music) {

		int tempCurTime = curTime;
		int tempTotalTime = totalTime;

		totalTime /= 1000;
		int totalminute = totalTime / 60;
		int totalsecond = totalTime % 60;
		String totalTimeString = String.format("%02d:%02d", totalminute,
				totalsecond);

		durationTv.setText(totalTimeString);
		musicNameTv.setText(music.musicName);
		artistTv.setText(music.artist);
        if(music.isPlaying())
        {
        	showPlay(true);
        }else
        {
        	showPlay(false);
        }
        
		refreshSeekProgress(tempCurTime, tempTotalTime);
	}
    @Override
    protected void setBgByPath(String path) {
    	// TODO Auto-generated method stub
    	super.setBgByPath(path);
    }

	protected void initBg(View musicView) {
		// TODO Auto-generated method stub
		super.initBg(musicView,R.id.main_remotemusic_layout);
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
	
   /****************************************************************/	
	class  MyAdapter extends BaseAdapter{

		Context mContext;
		LayoutInflater mInflater;
		public MyAdapter (Context context)
		{
			this.mContext = context;
			this.mInflater = LayoutInflater.from(this.mContext);
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
			ViewHolder  hodler;
			RemoteSongInfo songInfo = list.get(position);
			if( null == convertView)
			{
				hodler = new ViewHolder();
				convertView = mInflater.inflate(R.layout.remotemusic_item, null);
				//获得控件实例
				hodler.artistTv = (TextView) convertView.findViewById(R.id.artist_tv);
				hodler.durationTv = (TextView) convertView.findViewById(R.id.duration_tv);
				hodler.musicNameTv = (TextView) convertView.findViewById(R.id.musicname_tv);
				hodler.playStateIconIv = (ImageView) convertView.findViewById(R.id.playstate_iv);
				
				convertView.setTag(hodler);
			}else{
				hodler = (ViewHolder) convertView.getTag();
			}
			
			if(position!=curtMusicIndex)//当期项没有被选中,播放图标
			{
				//不显示播放状态图标
				hodler.playStateIconIv.setVisibility(View.GONE);
				songInfo.pause();
			}else{
				//显示播放状态图标
				hodler.playStateIconIv.setVisibility(View.VISIBLE);
				//判断播放状态
				if(songInfo.isPlaying())
				{
					hodler.playStateIconIv.setBackgroundResource(R.drawable.list_play_state);
				}else
				{
					hodler.playStateIconIv.setBackgroundResource(R.drawable.list_pause_state);
				}				
			}//if else
			
			hodler.artistTv.setText(songInfo.artist);
			hodler.durationTv.setText(MusicUtils.makeTimeString(songInfo.duration));
			hodler.musicNameTv.setText(songInfo.musicName);
			
			return convertView;
		}
		
		class ViewHolder {
			TextView musicNameTv, artistTv, durationTv;
			ImageView playStateIconIv;
		} 
		
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId())
	{
	case R.id.backBtn:
		//返回按钮
		mUIManager.setCurrentItem();
		break;
	case R.id.refreshBtn:
		//刷新列表
		break;
	case R.id.btn_playNext2://下一首
		break;
	case R.id.btn_menu2://菜单
		if(mActivity instanceof MainContentActivity)
		{
			((MainContentActivity)mActivity).mSlidingMenu.showMenu(true);
		}
		break;
	case R.id.btn_playPrev2://上一首
		
		break;
	case R.id.btn_pause2://暂停
		
		showPlay(false);
		setSongListState(curtMusicIndex, false);
		break;
	case R.id.btn_play2://播放
		showPlay(true);
		setSongListState(curtMusicIndex, true);
		break;
	}
}
/**
 * 切换播放、暂停按键可见性
 * @param flag
 */
public void showPlay(boolean flag) {
	if (!flag) {
		mPlayBtn.setVisibility(View.VISIBLE);
		mPauseBtn.setVisibility(View.GONE);
	} else {
		mPlayBtn.setVisibility(View.GONE);
		mPauseBtn.setVisibility(View.VISIBLE);
	}
}
private void setSongListState(int musicIndex,Boolean isPlaying)
{
	if(musicIndex!=-1 && list.size()>musicIndex)
	{
		RemoteSongInfo songInfo = list.get(musicIndex);
		if(isPlaying)
		{
			songInfo.play();
		}else
		{
			songInfo.pause();
		}
		list.remove(musicIndex);
		list.add(musicIndex, songInfo);
		mAdapter.notifyDataSetChanged();
	}
}

/**
 * 播放列表单击事件
 */
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	RemoteSongInfo songInfo = list.get(position);
	if(songInfo.isPlaying())
	{
		songInfo.pause();//暂停
	}else{
		songInfo.play();//播放
	}
	curtMusicIndex = position;
	list.remove(curtMusicIndex);
	list.add(curtMusicIndex, songInfo);
	//刷新底部状态
	refreshUI(songInfo.curtPositon, songInfo.duration, songInfo);
	//通知到远程耳机中
	mAdapter.notifyDataSetChanged();
}
}
