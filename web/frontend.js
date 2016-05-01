function getProducts(){
	
	$.ajax({
		url: "http://0.0.0.0:4567/item/list?nocache=" + (Math.random() * 100) ,
		dataType: "text",
		success: function(data) {

			var json = $.parseJSON(data);
			var $newdiv;
			for (var i = 0; i < json.length; i++) {
				$newdiv = $('<div class="small-4 columns text-center"><h4>'
					+ json[i].name +
					'<h4><p>&pound;'
					+ (json[i].price/100).toFixed(2) +
					'</p> <br /> <a href="#" class="button" onclick="addToCart('+ i +');">Add to cart!</a></div>');
				$('.showProducts').append($newdiv);
			}

			setPrices();

		}
	});

}

function addToCart(itemID){
	$.ajax({
		type: "POST",
		url: "http://0.0.0.0:4567/user/addToCart?add=" + (Math.random() * 100) ,
		data: {itemID: itemID},	
		// success: ,
		dataType: String
	});

	setPrices();
}

function setPrices(){

	$.ajax({
		url: "http://0.0.0.0:4567/user/toPay?nocache=" + (Math.random() * 100) ,
		dataType: "text",
		success: function(data) {

			var json = $.parseJSON(data);
			$( ".price" ).text((json.price/100).toFixed(2));
			$( ".discount" ).text((json.discount/100).toFixed(2));
			$( ".priceWithDiscount" ).text((json.priceWithDiscount/100).toFixed(2));
		}
	});

	$.ajax({
		url: "http://0.0.0.0:4567/user/cart?nocache=" + (Math.random() * 100) ,
		dataType: "text",
		success: function(data) {
			var json = $.parseJSON(data);
			var cart = "";
			for (var i = 0; i < json.length; i++){
				cart = cart + ", " + json[i].name;
			}
			$( ".usersCart" ).text("Your cart has: " + cart.substring(2));
		}
	});

}

// $(document).ready(function () {
//     var $newdiv;
//     for (var i = 0; i < 100; i++) {
//         $newdiv = $('<div class="ball" />').text(i);
//         $('.showProducts').append($newdiv);
//     }
// });