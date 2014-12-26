/**
 * ��Ŀ����ϵͳ��Ŀ����
 * ������com.huya.nationairquality.report  
 * �ļ�����CityAirQualityInfoFragment.java  
 * �����ˣ�xiaohu 
 * ���ڣ�2014-3-9-����11:00:02  
 * Copyright (c) 2014hu��˾-��Ȩ����<br/>  
 */
package com.huya.air.quality.view;

import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huya.air.quality.R;
import com.huya.air.quality.model.CityData;
import com.huya.air.quality.model.PointerData;
import com.huya.air.quality.util.ReportDataSourceUtil;

/**
 * @author xiaohu
 * 
 */
public class CityAirQualityInfoFragment extends Fragment {

	private String url;

	private GridView gv;

	private TextView cv;

	private TextView av;

	private TextView rv;

	private CityData city;

	private ProgressBar pb;

	private View main;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(final Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		url = getTag();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, final Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.cityairqualityinfo, container,
				false);

		if (null == url) {
			return view;
		}
		pb = (ProgressBar) view.findViewById(R.id.pb1);
		main = view.findViewById(R.id.main);
		new AirQualityFromCityTask().execute(url);

		gv = (GridView) view.findViewById(R.id.gridView);

		cv = (TextView) view.findViewById(R.id.city);
		av = (TextView) view.findViewById(R.id.advice);
		rv = (TextView) view.findViewById(R.id.result);

		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent,
					final View view, final int position, final long id) {

				if (null == city) {
					return;
				}
				if (!"".equals(city.getPointerList().get(position).getUrl())) {
					new AirQualityFromCityTask().execute(city.getPointerList()
							.get(position).getUrl());
				} else {
					PointerData pointer = city.getPointerList().get(position);
					String info = "���վ��:" + pointer.getName() + " " + "\nAQI:"
							+ pointer.getAqi25() + "\n����ʱ��:"
							+ ReportDataSourceUtil.time4Aqi() + "\n��Ⱦ�ȼ�:"
							+ pointer.getAdvice().split("-")[0] + "\n"
							+ pointer.getAdvice().split("-")[2].trim()
							+ "\n���鼰��ʩ:" + pointer.getAdvice().split("-")[1];
					new AlertDialog.Builder(getActivity()).setTitle("��ϸ��Ϣ")
							.setMessage(info).show();
				}

			}
		});

		return view;
	}

	class CityInfoAdapter extends BaseAdapter {

		private CityData cityData;

		public CityInfoAdapter(final CityData c) {
			cityData = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cityData.getPointerList().size();
		}

		@Override
		public Object getItem(final int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(final int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, final View convertView,
				final ViewGroup parent) {
			// TODO Auto-generated method stub
			View tv = getActivity().getLayoutInflater().inflate(
					R.layout.pointerairqualityinfo, null);
			TextView nameView = (TextView) tv.findViewById(R.id.cityname);
			TextView aqiView = (TextView) tv.findViewById(R.id.aqi);
			TextView adviceView = (TextView) tv.findViewById(R.id.advice);
			PointerData pointer = cityData.getPointerList().get(position);
			nameView.setText(pointer.getName());
			aqiView.setText(pointer.getNumber());
			aqiView.setTextColor(Color.parseColor(pointer.getColor()));
			adviceView.setText(pointer.getAdvice().split("-")[0]);
			return tv;
		}
	}

	class AirQualityFromCityTask extends AsyncTask<String, Void, Document> {

		@Override
		protected Document doInBackground(final String... params) {
			// TODO Auto-generated method stub
			return ReportDataSourceUtil.parseHtml2Doc(params[0]);
		}

		@Override
		protected void onPostExecute(final Document doc) {
			// TODO Auto-generated method stub
			if (null == doc) {
				new AlertDialog.Builder(getActivity()).setTitle("������Ϣ")
						.setMessage("�޷���ȡ���ݣ�����������������").show();
				return;
			}
			city = ReportDataSourceUtil.getDataFromCity(doc);
			gv.setAdapter(new CityInfoAdapter(city));
			cv.setText(city.getTitle());
			av.setText("����ʱ��:"
					+ ReportDataSourceUtil.time4Aqi()
					+ "\n"
					+ city.getAdvice()
					+ (city.getRank().equals(city.getAdvice()) ? "" : "\r\n"
							+ city.getRank()));
			rv.setText(city.getFace() + "\r\n" + city.getResult());
			View topView = getActivity().findViewById(R.id.top);
			topView.setBackgroundColor(Color.parseColor(city.getColor()));
			pb.setVisibility(View.GONE);
			main.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			pb.setVisibility(View.VISIBLE);
			main.setVisibility(View.GONE);
			super.onPreExecute();
		}

	}

}
