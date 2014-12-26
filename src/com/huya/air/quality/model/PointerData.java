package com.huya.air.quality.model;

public class PointerData {
	private String name;
	private String number;
	private String pm25;
	private String pm10;
	private String aqi25;
	private String aqi10;
	private String advice;
	private String url;
	private String color;

	/**
	 * @return the advice
	 */
	public String getAdvice() {
		return advice;
	}

	/**
	 * @param advice
	 *            the advice to set
	 */
	public void setAdvice(final String advice) {
		this.advice = advice;
	}

	/**
	 * aqi25.
	 * 
	 * @return the aqi25
	 */
	public String getAqi25() {
		return aqi25;
	}

	/**
	 * aqi25.
	 * 
	 * @param aqi25
	 *            the aqi25 to set
	 */
	public void setAqi25(final String aqi25) {
		this.aqi25 = aqi25;
	}

	/**
	 * aqi10.
	 * 
	 * @return the aqi10
	 */
	public String getAqi10() {
		return aqi10;
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	/**
	 * aqi10.
	 * 
	 * @param aqi10
	 *            the aqi10 to set
	 */
	public void setAqi10(final String aqi10) {
		this.aqi10 = aqi10;
	}

	/**
	 * name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * pm25.
	 * 
	 * @return the pm25
	 */
	public String getPm25() {
		return pm25;
	}

	/**
	 * pm25.
	 * 
	 * @param pm25
	 *            the pm25 to set
	 */
	public void setPm25(final String pm25) {
		this.pm25 = pm25;
	}

	/**
	 * pm10.
	 * 
	 * @return the pm10
	 */
	public String getPm10() {
		return pm10;
	}

	/**
	 * pm10.
	 * 
	 * @param pm10
	 *            the pm10 to set
	 */
	public void setPm10(final String pm10) {
		this.pm10 = pm10;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(final String number) {
		this.number = number;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointerData [name=" + name + ", number=" + number + ", color="
				+ color + ", pm25=" + pm25 + ", pm10=" + pm10 + ", aqi25="
				+ aqi25 + ", aqi10=" + aqi10 + ", advice=" + advice + ", url="
				+ url + "]";
	}
}
