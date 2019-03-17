package com.foodtym.admin.daomodels;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.foodtym.admin.beans.FoodItem;
import com.foodtym.admin.beans.PriceBasis;
import com.foodtym.admin.beans.PriceType;
import com.foodtym.admin.beans.Type;
import com.foodtym.admin.beans.TypePrice;

public class FoodItemDaoImpl {
	private BasicDataSource dataSource;
	private Properties sql;
	
	public static FoodItem getFoodItemFromJson(String json) throws ParseException {
		FoodItem foodItem = new FoodItem();
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(json);
		foodItem.setCategory((String) obj.get("food_category"));
		foodItem.setSubCategory((String) obj.get("food_subcategory"));
		foodItem.setTitle((String) obj.get("food_title"));
		foodItem.setDescription((String) obj.get("food_description"));
		foodItem.setPreparingTime( Integer.parseInt((String) obj.get("food_prepare_time")));
		switch(((String)obj.get("food_price_basis")).toUpperCase()) {
			case "HALF_FULL":
				foodItem.setPriceBasis(PriceBasis.HALF_FULL);
				break;
			case "PCS":
				foodItem.setPriceBasis(PriceBasis.PCS);
				break;
			case "KG":
				foodItem.setPriceBasis(PriceBasis.KG);
				break;
		}
		
		foodItem.setRestaurantId(Integer.parseInt((String) obj.get("restaurant_id")));
		switch(((String) obj.get("food_type")).toUpperCase()) {
			case "NONVEG":
				foodItem.setType(Type.NONVEG);
			case "VEG":
				foodItem.setType(Type.VEG);
		}
		
		JSONArray jsonArray = (JSONArray) obj.get("priceTypes");
		PriceType[] priceTypes = new PriceType[jsonArray.size()];
		for (int i = 0 ; i < jsonArray.size() ; i++) {
			PriceType priceType = new PriceType();
			JSONObject object = (JSONObject) jsonArray.get(i);
			switch(((String)object.get("type")).toUpperCase()) {
				case "HALF":
					priceType.setType(TypePrice.HALF);
					break;
				case "QTR":
					priceType.setType(TypePrice.QTR);
					break;
				case "FULL":
					priceType.setType(TypePrice.FULL);
					break;
				case "PCS":
					priceType.setType(TypePrice.PCS);
					priceType.setQuantity(Integer.parseInt((String) object.get("quantity")));
					break;
				case "HALF_KG":
					priceType.setType(TypePrice.HALF_KG);
					break;
				case "FULL_KG":
					priceType.setType(TypePrice.FULL_KG);
					break;
			}
			priceType.setFoodtymPrice(Double.parseDouble((String) object.get("foodtymPrice")));
			priceType.setRestaurantPrice(Double.parseDouble((String) object.get("restaurantPrice")));
			priceTypes[i] = priceType;
		}
		foodItem.setPriceTypes(priceTypes);
		return foodItem;
	}
	
	public FoodItemDaoImpl(BasicDataSource dataSource , Properties sql) {
		this.dataSource = dataSource;
		this.sql = sql;
	}
	
	private PriceType[] getFoodItemPrice(int foodItemId , Connection connection) throws SQLException {
		List<PriceType> list = new ArrayList<>();
		PreparedStatement price_statement = connection.prepareStatement(sql.getProperty("GET_FOOD_ITEM_PRICE_DETAILS"));
		price_statement.setInt(1, foodItemId);
		ResultSet price_result_set = price_statement.executeQuery();
		while(price_result_set.next()) {
			PriceType priceType = new PriceType();
			switch(price_result_set.getString(1)) {
				case "HALF":
					priceType.setType(TypePrice.HALF);
					break;
				case "FULL":
					priceType.setType(TypePrice.FULL);
					break;
				case "QTR":
					priceType.setType(TypePrice.QTR);
					break;
				case "PCS":
					priceType.setType(TypePrice.PCS);
					break;
				case "HALF_KG":
					priceType.setType(TypePrice.HALF_KG);
					break;
				case "FULL_KG":
					priceType.setType(TypePrice.FULL_KG);						
			} // end of switch
			priceType.setRestaurantPrice(price_result_set.getDouble(2));
			priceType.setFoodtymPrice(price_result_set.getDouble(3));
			priceType.setQuantity(price_result_set.getInt(4));
			list.add(priceType);
			
		} // end of while
		return list.toArray(new PriceType[list.size()]);
	}
	
	public int insertFoodItem(FoodItem foodItem) throws SQLException {
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		int categoryId = -1,subCategoryId = -1,foodItemId = -1;
		
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		preparedStatement = connection.prepareStatement(sql.getProperty("GET_FOOD_CATEGORY_ID"));
		preparedStatement.setString(1, foodItem.getCategory());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			categoryId = resultSet.getInt(1);
		}
		else {
			preparedStatement.close();
			resultSet.close();
			preparedStatement = connection.prepareStatement(sql.getProperty("INSERT_FOOD_CATEGORY"));
			preparedStatement.setString(1, foodItem.getCategory());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(sql.getProperty("LAST_INSERT_ID"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				categoryId = resultSet.getInt(1);
			preparedStatement.close();
			resultSet.close();
		}
		preparedStatement = connection.prepareStatement(sql.getProperty("GET_FOOD_SUB_CATEGORY_ID"));
		preparedStatement.setInt(1, categoryId);
		preparedStatement.setString(2, foodItem.getSubCategory());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next())
			subCategoryId = resultSet.getInt(1);
		else {
			preparedStatement.close();
			resultSet.close();
			preparedStatement = connection.prepareStatement(sql.getProperty("INSERT_FOOD_SUB_CATEGORY"));
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setString(2, foodItem.getSubCategory());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(sql.getProperty("LAST_INSERT_ID"));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				subCategoryId = resultSet.getInt(1);
			resultSet.close();
			preparedStatement.close();
		}
		preparedStatement = connection.prepareStatement(sql.getProperty("INSERT_FOOD_ITEM"));
		preparedStatement.setInt(1, subCategoryId);
		preparedStatement.setString(2, foodItem.getTitle());
		preparedStatement.setString(3, foodItem.getDescription());
		preparedStatement.setInt(4, foodItem.getRestaurantId());
		preparedStatement.setBinaryStream(5, new ByteArrayInputStream(foodItem.getImg()));
		preparedStatement.setString(6, foodItem.getPriceBasis().toString());
		preparedStatement.setInt(7, foodItem.getPreparingTime());
		preparedStatement.setString(8, foodItem.getType().toString());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		preparedStatement = connection.prepareStatement(sql.getProperty("LAST_INSERT_ID"));
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next())
			foodItemId = resultSet.getInt(1);
		resultSet.close();
		preparedStatement.close();
		
		PriceType[] types = foodItem.getPriceTypes();
		preparedStatement = connection.prepareStatement(sql.getProperty("INSERT_FOOD_ITEM_PRICE"));
		for (int i = 0 ; i < types.length ; i++) {
			preparedStatement.setInt(1, foodItemId);
			preparedStatement.setString(2, types[i].getType().toString());
			preparedStatement.setDouble(3, types[i].getRestaurantPrice());
			preparedStatement.setDouble(4, types[i].getFoodtymPrice());
			preparedStatement.setDouble(5, types[i].getQuantity());
			preparedStatement.executeUpdate();
		}
		connection.commit();
		connection.setAutoCommit(true);
		connection.close();
		return foodItemId;
	}
	
	public List<FoodItem> getRestaurantFoodItems(int restaurantId) throws SQLException {
		Connection connection = dataSource.getConnection();
		List<FoodItem> list = new ArrayList<>();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.getProperty("GET_FOOD_ITEMS_OF_RESTAURANT"));
		preparedStatement.setInt(1, restaurantId);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			FoodItem foodItem = new FoodItem();
			foodItem.setFoodItemId(resultSet.getInt(1));
			foodItem.setTitle(resultSet.getString(2));
			foodItem.setCategory(resultSet.getString(3));
			foodItem.setSubCategory(resultSet.getString(4));
			foodItem.setDescription(resultSet.getString(5));
			
			switch(resultSet.getString(6)) {
				case "HALF_FULL":
					foodItem.setPriceBasis(PriceBasis.HALF_FULL);
					break;
				case "PCS":
					foodItem.setPriceBasis(PriceBasis.PCS);
					break;
				case "KG":
					foodItem.setPriceBasis(PriceBasis.KG);
			}
			foodItem.setPreparingTime(resultSet.getInt(7));
			
			if (resultSet.getString(8).equals("VEG"))
				foodItem.setType(Type.VEG);
			else
				foodItem.setType(Type.NONVEG);
			
			foodItem.setPriceTypes(getFoodItemPrice(foodItem.getFoodItemId(), connection));
			list.add(foodItem);
		}
		return list;
	}
	
	public byte[] getFoodItemThumb(int foodItemId) throws SQLException, IOException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.getProperty("GET_FOOD_ITEM_THUMB"));
		preparedStatement.setInt(1, foodItemId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getBinaryStream(1).readAllBytes();
		}
		return new byte[1];
	}
	
}
