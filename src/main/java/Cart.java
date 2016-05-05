//package eurostarApplication;

import java.util.ArrayList;
import com.google.gson.Gson;


public class Cart {
	
	/// JSON success response on add item to cart
	class Response {
		Boolean success;
	}
	
	/// Holds what the user is to pay: int price, int discount, int priceWithDiscount (All in pence)	
	class ToPay {
		int price;
		int discount;
		int priceWithDiscount;
		String appliedDiscount;
	}
	
	/// Hold promotional data: int items[], String name, int discount (in pence)
	class Promotion {
		int items[];
		String name;
		int discount;
	}
	
	
	Response response;
	private Gson gson = new Gson();
	private Store store;

	
	/// User related data
	private ArrayList<Store.Item> cart;
	private int price = 0;
	private int discount = 0;
	private String appliedDiscount = "";
	
	/**
	@param Store storeInstance
	@param ArrayList<Store.Item> userCart
	@return String
	*/
	public Cart(Store storeInstance, ArrayList<Store.Item> userCart){		

		store = storeInstance;
		cart = userCart;

	}
	
	/**
	@return String
	*/
	public String getUserCart(){
		return gson.toJson(cart);
	}
	
	/**
	@param String itemID
	@return String
	*/
	public String addItem(String itemID){
		
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
	
	/**
	@return String of JSON of class ToPay
	*/
	public String getUserToPay(){
		
		checkDiscount();
		
		ToPay toPay = new ToPay();
		toPay.price = price;
		toPay.discount = discount;
		toPay.priceWithDiscount = price - discount;
		toPay.appliedDiscount = appliedDiscount;
		
		return gson.toJson(toPay);
	}
	
	// Read in user cart and run against list of existing discounts
	private void checkDiscount(){

		/// Prevent people getting discounts by refreshing the check discount function
		discount = 0;
		appliedDiscount = "";

		/// Create a copy of users cart for algorithm to work with
		ArrayList<Store.Item> tmp = new ArrayList<Store.Item>(cart);

		/// Create promotions
		Promotion BreadButter = new Promotion();
		BreadButter.items = new int[]{0,0,2}; /// Butter x 2, Bread x 1
		BreadButter.name = "Four Milk Discount";
		BreadButter.discount = 50;
		
		Promotion FourMilk = new Promotion();
		FourMilk.items = new int[]{1,1,1,1}; /// Milk x 4
		FourMilk.name = "Four Milk Discount";
		FourMilk.discount = 115;

		Promotion[] promotions = new Promotion[2];
		promotions[0] = BreadButter;
		promotions[1] = FourMilk;
		

		/// For the existing promotions
		for (int i = 0; i < promotions.length; i++){
			boolean inArray = true;
			
			/// While we can still check if the promotion applies
			while (inArray){
				
				/// Get an item from the promotion
				for (int item : promotions[i].items){
				///	
					try {
						/// Check if the item is in the shopping basket
					    int id = tmp.indexOf(store.getProduct(item)); 
					    tmp.remove(id);
					} catch ( IndexOutOfBoundsException e ) {
						/// If it isn't, don't allow the discount to be applied
					    inArray = false; 
					}
					
				}	
				if (inArray){
					discount = discount + promotions[i].discount;
					appliedDiscount = appliedDiscount + promotions[i].name + ", ";
					
				}
			}
		}	

	}
}