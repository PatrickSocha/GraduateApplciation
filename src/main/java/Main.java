import static spark.Spark.*;

import java.util.ArrayList;
//import com.google.gson.Gson;

public class Main {
	
    public static void main(String[] args) {
    	
    	// Here we could use sessions/cookies to make carts specific to a user (not in spec)
    	ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
    	Store store = new Store();
    	Cart cart = new Cart(store, userCart);
    	
    	get("/user/cart", (req, res) -> cart.getUserCart());
    	get("/user/toPay", (req, res) -> cart.getUserToPay());
    	get("/item/add/:itemID", (req, res) -> cart.addItem(req.params(":itemID")));
    	get("/item/info/:itemID", (req, res) -> store.printItem(req.params(":itemID")));
    }
    
}
