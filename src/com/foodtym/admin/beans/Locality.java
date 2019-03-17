package com.foodtym.admin.beans;

import org.json.simple.JSONObject;

public class Locality {
	private int ncrRegionId;
	private int localityId;
	private String ncrRegionName;
	private String localityName;
	
	public int getNcrRegionId() {
		return ncrRegionId;
	}
	public void setNcrRegionId(int ncrRegionId) {
		this.ncrRegionId = ncrRegionId;
	}
	public int getLocalityId() {
		return localityId;
	}
	public void setLocalityId(int localityId) {
		this.localityId = localityId;
	}
	
	public String getNcrRegionName() {
		return ncrRegionName;
	}
	public void setNcrRegionName(String ncrRegionName) {
		this.ncrRegionName = ncrRegionName;
	}
	public String getLocalityName() {
		return localityName;
	}
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
	
	@Override
	public String toString() {
		JSONObject jObject = new JSONObject();
		jObject.put("localityId", localityId);
		jObject.put("ncrRegionId", ncrRegionId);
		jObject.put("localityName", localityName);
		jObject.put("ncrRegionName", ncrRegionName);
		return jObject.toJSONString();
	}
}	
