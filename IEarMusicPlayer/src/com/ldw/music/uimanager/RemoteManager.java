package com.ldw.music.uimanager;

import java.util.zip.Inflater;

import com.ldw.music.R;
import com.ldw.music.storage.SPStorage;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public abstract class RemoteManager extends MainUIManager {
	private final String Tag="RemoteManager";
	protected Activity mActivity;
	protected UIManager mUIManager;
	protected LayoutInflater mInflater;
	protected RelativeLayout mFolderLayout;
	
     public RemoteManager(Activity mActivity, UIManager uiManager) {
		// TODO Auto-generated constructor stub
    	 this.mActivity = mActivity;
    	 this.mUIManager = uiManager;
    	 this.mInflater = LayoutInflater.from(mActivity);
    	 Log.e(Tag, "RemoteManager init1");
	}
     @Override
 	protected void setBgByPath(String path) {
 		// TODO Auto-generated method stub
 		Bitmap bitmap = mUIManager.getBitmapByPath(path);
 		if (bitmap != null) {
 			mFolderLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
 					.getResources(), bitmap));
 		}
 	}
     /**
      * 初始化背景
      * @param view
      * @param rId 界面主体布局
      */
     protected void initBg(View view,int rId) {
 		mFolderLayout = (RelativeLayout) view
 				.findViewById(rId);//获得文件夹主界面的主体布局
 		SPStorage mSp = new SPStorage(mActivity);
 		String mDefaultBgPath = mSp.getPath();
 		Bitmap bitmap = mUIManager.getBitmapByPath(mDefaultBgPath);
 		Log.e(Tag, "RemoteManager initBg ---》 mFolderLayout:"+(mFolderLayout!=null)+" mActivity:"+(mActivity!=null));
 		if (bitmap != null) {//设置当前布局的背景
 			mFolderLayout.setBackgroundDrawable(new BitmapDrawable(mActivity
 					.getResources(), bitmap));
 		}
 	}
}
