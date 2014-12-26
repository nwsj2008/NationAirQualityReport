package com.huya.air.quality.model;

import java.util.List;

public class CityData {
	private String title;
	private String advice;
	private String rank;
	private String face;
	private String result;
	private String color;
	private List<PointerData> pointerList;

	/**
	 * TODO
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityData [title=" + title + ", color=" + color + ", advice="
				+ advice + ",  rank=" + rank + ",result=" + result
				+ ", pointerList=" + pointerList + "]";
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	/**
	 * title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(final String rank) {
		this.rank = rank;
	}

	/**
	 * title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * advice.
	 * 
	 * @return the advice
	 */
	public String getAdvice() {
		return advice;
	}

	/**
	 * advice.
	 * 
	 * @param advice
	 *            the advice to set
	 */
	public void setAdvice(final String advice) {
		this.advice = advice;
	}

	/**
	 * result.
	 * 
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * result.
	 * 
	 * @param result
	 *            the result to set
	 */
	public void setResult(final String result) {
		this.result = result;
	}

	/**
	 * pointerList.
	 * 
	 * @return the pointerList
	 */
	public List<PointerData> getPointerList() {
		return pointerList;
	}

	/**
	 * pointerList.
	 * 
	 * @param pointerList
	 *            the pointerList to set
	 */
	public void setPointerList(final List<PointerData> pointerList) {
		this.pointerList = pointerList;
	}

	public String getFace() {
		return face;
	}

	public void setFace(final String face) {
		this.face = face;
	}

}
