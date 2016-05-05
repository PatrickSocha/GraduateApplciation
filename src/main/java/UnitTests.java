import static org.junit.Assert.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.junit.Test;

public class UnitTests {
	
	Gson gson = new Gson();
	Store store = new Store();

	@Test
	public void testButterBreadOffer(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("0");
		cart.addItem("0");
		cart.addItem("2");
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 210);	
	}
	

	@Test
	public void testFourMilkOffer(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("1");
		cart.addItem("1");
		cart.addItem("1");
		cart.addItem("1");
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 345);	
	}	

	// Specification Scenarios/Test Cases:
	// TC1
	@Test
	public void testScenarioOne(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("0");
		cart.addItem("1");
		cart.addItem("2");
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 295);	
	}
	
//	// TC2
	@Test
	public void testScenarioTwo(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("0");
		cart.addItem("0");
		cart.addItem("2");
		cart.addItem("2");
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 310);	
	}
	
	// TC3
	@Test
	public void testScenarioThree(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("1");
		cart.addItem("1");
		cart.addItem("1");
		cart.addItem("1");
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 345);	
	}
	
	// TC4
	@Test
	public void testScenarioFour(){

		ArrayList<Store.Item> userCart = new ArrayList<Store.Item>();
		Cart cart = new Cart(store, userCart);
		
		cart.addItem("0");
		cart.addItem("0");
		cart.addItem("2");
		for (int i = 0; i < 8; i++){
			cart.addItem("1");
		}
		
		Cart.ToPay json = gson.fromJson(cart.getUserToPay(), Cart.ToPay.class);
		assertEquals(json.priceWithDiscount, 900);	
	}


}
