/**
 * 项目名：系统项目名称
 * 包名：com.huya.air.quality.view  
 * 文件名：SplashActivity.java  
 * 创建人：xiaohu 
 * 日期：2014-5-4-下午7:52:55  
 * Copyright (c) 2014hu公司-版权所有<br/>  
 */
package com.huya.air.quality.view;

import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.huya.air.quality.R;
import com.huya.air.quality.util.ReportDataSourceUtil;

/**
 * @author xiaohu
 *
 */
public class SplashActivity extends Activity
	{

		/* (non-Javadoc)
		 * @see android.app.Activity#onCreate(android.os.Bundle)
		 */
		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.splash);
				new SplashTask().execute("http://www.soupm25.com/");
				
			}
		
		class SplashTask extends AsyncTask<String, Void, Document>{

			/* (non-Javadoc)
			 * @see android.os.AsyncTask#doInBackground(Params[])
			 */
			@Override
			protected Document doInBackground(String... params)
				{
					// TODO Auto-generated method stub
					return ReportDataSourceUtil.parseHtml2Doc(params[0]);
				}

			/* (non-Javadoc)
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(Document doc)
				{
					
					if (null == doc) {
						new AlertDialog.Builder((SplashActivity.this)).setTitle("错误信息")
								.setMessage("无法获取数据，请检查您的网络连接").show();
						return;
					}
					// TODO Auto-generated method stub
					Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
					Bundle bundle = new Bundle();
					bundle.putStringArray("cityarray", ReportDataSourceUtil.getSourceMap(doc).keySet()
							.toArray(new String[0]));
					bundle.putStringArray("urlarray", ReportDataSourceUtil.getSourceMap(doc)
							.values().toArray(new String[0]));
					mainIntent.putExtras(bundle);
					startActivity(mainIntent);
					SplashActivity.this.finish();
					overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
				}
			
		}
	}
