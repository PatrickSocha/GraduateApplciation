import static spark.Spark.*;

import java.util.ArrayList;
//import com.google.gson.Gson;

public class Main {
	
    public static void main(String[] args) {
    	
    	// Here we could use sessions/cookies to make carts specific to a user (not in spec)
    	ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
    	Store store = new Store();
    	Cart cart = new Cart(store, userCart);
    	
    	// For all API end-points allow Cross Origin Resource Sharing (CORS)
    	before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
    	
    	// End-points
    	get("/user/cart", (req, res) -> cart.getUserCart());
    	get("/user/toPay", (req, res) -> cart.getUserToPay());
    	post("/user/addToCart", (req, res) -> cart.addItem(req.queryParams("itemID")));
    	get("/item/info/:itemID", (req, res) -> store.printItem(req.params(":itemID")));
    	get("/item/list", (req, res) -> store.getItemList());
    }
    
}
