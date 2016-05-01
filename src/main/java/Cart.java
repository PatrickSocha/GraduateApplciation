//package eurostarApplication;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;


public class Cart {
	
	// JSON Responses
	class Response {
		Boolean success;
	}
	
	class ToPay {
		int price;
		int discount;
		int priceWithDiscount;
	}
	
	
	Response response;
	private Gson gson = new Gson();
	private Store store;

	
	// User related data
	private ArrayList<Store.Item> cart;
	private int price = 0;
	private int discount = 0;
	
	
	public Cart(Store storeInstance, ArrayList<Store.Item> userCart){		

		store = storeInstance;
		cart = userCart;

	}
	
	public String getUserCart(){
		return gson.toJson(cart);
	}
	
	
	public String addItem(String itemID){
		
		System.out.println("Posted Item ID: " + itemID);
		
		try {
			Store.Item product = store.getProduct(Integer.parseInt(itemID));
	    	cart.add(product);
	    	
			price = price + store.getProductPrice(Integer.parseInt(itemID));

		} catch (NumberFormatException e) {
	    	System.out.println(e);		
		}

		Response response = new Response();
		response.success = true;
    	return gson.toJson(response);
	}
	
	public String getUserToPay(){
		
		checkDiscount();
		
		ToPay toPay = new ToPay();
		toPay.price = price;
		toPay.discount = discount;
		toPay.priceWithDiscount = price - discount;
		
		return gson.toJson(toPay);
	}
	
	private void checkDiscount(){

		// Prevent people getting discounts by refreshing the check discount function
		discount = 0;

		// Apply as many discounts as possible, until exhausted options.
		boolean canCheckDiscount = true;
		
		ArrayList<Store.Item> tmp = new ArrayList<Store.Item>(cart);
		
		while(canCheckDiscount){
			
			canCheckDiscount = false;
			
			int freqButter = Collections.frequency(tmp, store.getProduct(0));
			int freqMilk = Collections.frequency(tmp, store.getProduct(1));
			int freqBread = Collections.frequency(tmp, store.getProduct(2));
			
			if (freqButter >= 2 && freqBread >= 1){
				tmp.remove(tmp.indexOf(store.getProduct(0)));
				tmp.remove(tmp.indexOf(store.getProduct(0)));
				tmp.remove(tmp.indexOf(store.getProduct(2)));
				discount = discount + 50;
				canCheckDiscount = true;
			}
			
			if (freqMilk >= 4){
				for (int i =0; i < 4; i++){
					tmp.remove(tmp.indexOf(store.getProduct(1)));
				}
				discount = discount + 115;
				canCheckDiscount = true;
			}
		}
		
	}
	

} 