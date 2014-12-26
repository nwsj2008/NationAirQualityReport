package com.huya.air.quality.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.text.format.Time;

import com.huya.air.quality.model.CityData;
import com.huya.air.quality.model.PointerData;

public class ReportDataSourceUtil {

	/**
	 * @param url
	 * @return
	 */
	public static Document parseHtml2Doc(final String url) {
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties()
				.setProperty("http.proxyHost", "proxy.xilinx.com");
		System.getProperties().setProperty("http.proxyPort", "8080");
		Connection conn = Jsoup.connect(url);
		conn.timeout(3000);
		Document doc = null;
		try {
			doc = conn.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return doc;
	}

	public static CityData getDataFromCity(final Document doc) {
		// 读取城市信息
		Element averageLevel = doc.select("div.span11>div.hero-unit").first();

		String color = averageLevel.attr("style");
		String title = averageLevel.select("div.span8>h1").first().text();
		String adviceForcity = averageLevel.select("div.span8>h2").first()
				.text();
		String face = averageLevel.select("div.span4>h1").first().text();
		String result = averageLevel.select("div.span4>h1.review").first()
				.text();
		String rank = averageLevel.select("div.span8>h2").last().text();

		List<PointerData> pointerList = new ArrayList<PointerData>();

		// 读取各监测站信息
		Elements pointers = doc.select("div.span11>div.row-fluid div.content");

		// 遍历监测站信息
		for (Element pointer : pointers) {

			Elements uselessPointers = pointer.parent().select("div.staname");
			if (null == uselessPointers || 0 == uselessPointers.size()
					|| "所有监测站列表".equals(uselessPointers.first().text())
					|| null == pointer.parent().select("div.staaqi")) {
				continue;
			}

			Element cityElement = pointer.parent().select("div.staaqi").first()
					.getElementsByTag("span").first();
			String adviceForPointer = cityElement.attr("title");

			String numColor = cityElement.attr("style");

			PointerData cityPointer = new PointerData();

			Elements pms = pointer.select("tr");

			for (Element pmTitle : pms) {
				if (null == pmTitle.select("td")
						|| 0 == pmTitle.select("td").size()) {
					continue;
				}

				if ("PM2.5浓度".equals(pmTitle.select("td").first().text())) {
					cityPointer.setPm25(pmTitle.select("td").get(1).text());
				} else if ("PM2.5指数"
						.equals(pmTitle.select("td").first().text())) {
					cityPointer.setAqi25(pmTitle.select("td").get(1).text());
				} else if ("PM10浓度".equals(pmTitle.select("td").first().text())) {
					cityPointer.setPm10(pmTitle.select("td").get(1).text());
				} else {
					cityPointer.setAqi10(pmTitle.select("td").get(1).text());
				}
			}

			cityPointer.setName(uselessPointers.first().text());
			cityPointer.setNumber(pointer.parent().select("div.staaqi").first()
					.text());
			cityPointer.setColor(numColor.substring(numColor.lastIndexOf("#"),
					numColor.length() - 1));
			cityPointer.setUrl(uselessPointers.first().select("a").first()
					.attr("abs:href"));
			cityPointer.setAdvice(adviceForPointer);

			pointerList.add(cityPointer);
		}
		CityData data = new CityData();
		data.setAdvice(adviceForcity);
		data.setPointerList(pointerList);
		data.setRank(rank);
		data.setFace(face.replaceAll("#", ""));
		data.setResult(result);
		data.setTitle(title);
		data.setColor(color.substring(color.lastIndexOf("#"),
				color.length() - 1));
		return data;
	}

	/**
	 * @param doc
	 * @return
	 */
	public static Map<String, String> getSourceMap(final Document doc) {

		Map<String, String> proMap = new LinkedHashMap<String, String>();
		Element navProvince = doc.select("div.well").first();
		Elements provinceList = navProvince.select("li.nav-header");
		for (Element province : provinceList) {
			String url = province.getElementsByTag("a").first().absUrl("href");
			proMap.put(province.text(), url);
		}
		return proMap;
	}

	public static String time4Aqi() {

		Time t = new Time();
		t.setToNow();
		String year = "" + t.year;
		String month = "" + (t.month + 1);
		String day = "" + t.monthDay;
		String hour = "" + t.hour;

		return year + "年" + month + "月" + day + "日" + hour + "时";
	}
}
