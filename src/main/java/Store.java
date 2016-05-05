import java.util.ArrayList;

import com.google.gson.Gson;

public class Store {

	/// Using pennies (int) to avoid rounding errors (floats and double)
	class Item{
		int itemID;
		int price;
		String name;
	}
	
	private Gson gson = new Gson();
	private ArrayList<Item> products = new ArrayList<Item>();
	private Item butter = new Item();
	private Item milk = new Item();
	private Item bread = new Item();
	
	public Store(){

		createProducts();

	}
	
	/// Creates products and adds them to products ArrayList
	private void createProducts(){
		// Reminder: Price is in pence!
		butter.price = 80;
		butter.name = "Butter";
		
		milk.price = 115;
		milk.name = "Milk";
    	
		bread.price = 100;
		bread.name = "Bread";
		
		products.add(butter);
		products.add(milk);
		products.add(bread);
		
	}
	
	/**
	Returns a singular Item based on Item ID.
	On non-existent Item, returns an empty one.
	@param int itemID
	@returns Item
	*/
	public Item getProduct(int itemID){
		try {
			return products.get(itemID);
		} catch (IndexOutOfBoundsException e){
			return new Item();
		}
	}	
	
	/**
	@returns String JSON object of list of Products
	*/
	public String getItemList(){
		return gson.toJson(products);
	}
	
	/**
	@param int ItemID
	@returns int price in pence of item
	*/
	public int getProductPrice(int itemID){
		return products.get(itemID).price;
	}
	
	/**
	@param String getItem
	@returns String JSON object of Item
	*/
	public String printItem(String getItem){
		Item response;
		try {
			response = getProduct(Integer.parseInt(getItem));
		} catch (NumberFormatException e) {
	    	System.out.println(e);	
	    	response = new Item(); /// Return blank item if we cannot find the product
		}
		
		return gson.toJson(response);
		
	}	
	
}
