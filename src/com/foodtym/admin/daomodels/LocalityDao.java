package com.foodtym.admin.daomodels;

import java.sql.SQLException;
import java.util.List;

import com.foodtym.admin.beans.Locality;

public interface LocalityDao {
	public int insertNcrRegion(String ncrRegionName) throws SQLException;
	public int getLocalityId(String ncr , String locality) throws SQLException;
	public int insertLocality(String localityName, int ncrRegionId) throws SQLException;
	public List<Locality> searchLocalityInNcrRegion(String ncrRegion ,String text) throws SQLException;
	public List<Locality> searchLocality(String text) throws SQLException;
	public List<Locality> searchNcrRegion(String text) throws SQLException;
	public Locality getLocality(int localityId) throws SQLException;
}
