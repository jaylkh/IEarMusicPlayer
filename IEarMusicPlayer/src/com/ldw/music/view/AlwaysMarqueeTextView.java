/**
 * Copyright (c) www.longdw.com
 */
package com.ldw.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

//一直滚动的textview
public class AlwaysMarqueeTextView extends TextView {

	public AlwaysMarqueeTextView(Context context) {
		super(context);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean isFocused() {
		return true;
	}
	

}
