package ie.cm.activities;

import ie.cm.R;
import ie.cm.models.Coffee;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class Edit extends Base {
	private Context context;
	private Boolean isFavourite;
	private Coffee aCoffee;
	private ImageView favouriteImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.edit); activityInfo = getIntent().getExtras();
		aCoffee = getCoffeeObject(activityInfo.getInt("coffeeID"));
		favouriteImage = (ImageView) findViewById(R.id.favouriteImageView);
		((TextView)findViewById(R.id.coffeeNameTextView)).setText(aCoffee.name);
		((TextView)findViewById(R.id.coffeeShopTextView)).setText(aCoffee.shop);
		((EditText)findViewById(R.id.nameEditText)).setText(aCoffee.name);
		((EditText)findViewById(R.id.shopEditText)).setText(aCoffee.shop);
		((EditText)findViewById(R.id.priceEditText)).setText(""+aCoffee.price);
		((RatingBar) findViewById(R.id.coffeeRatingBar)).setRating((float)aCoffee.rating);

		if (aCoffee.favourite == true) { favouriteImage.setImageResource(R.drawable.ic_favourite_on); isFavourite = true;
		} else {
			favouriteImage.setImageResource(R.drawable.ic_favourite_off);
			isFavourite = false; }
	}

	private Coffee getCoffeeObject(int id) {

		for (Coffee c : coffeeList)
			if (c.coffeeId == id)
				return c;

		return null;
	}

	private int getCoffeeIndex(Coffee obj) {

		for (Coffee c : coffeeList)
			if (c.coffeeId == obj.coffeeId)
				return coffeeList.indexOf(c);

		return -1;
	}

	public void update(View v) {

		String coffeeName = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
		String coffeeShop = ((EditText) findViewById(R.id.shopEditText)).getText().toString();
		String coffeePriceStr = ((EditText) findViewById(R.id.priceEditText)).getText().toString();
		double ratingValue =((RatingBar) findViewById(R.id.coffeeRatingBar)).getRating();
		double coffeePrice;


		try {
			coffeePrice = Double.parseDouble(coffeePriceStr);
		} catch (NumberFormatException e) {
			coffeePrice = 0.0;
		}

		if ((coffeeName.length() > 0) && (coffeeShop.length() > 0) && (coffeePriceStr.length() > 0)) {
			aCoffee.name = coffeeName;
			aCoffee.shop = coffeeShop;
			aCoffee.price = coffeePrice;
			aCoffee.rating = ratingValue;

			// Update coffee & return home


		} else
			toastMessage("You must Enter Something for Name and Shop");

		goToActivity(this,Home.class, activityInfo);
	}

	public void toggle(View view) {

		if (isFavourite) {
			aCoffee.favourite = false;
			toastMessage("Removed From Favourites");
			isFavourite = false; favouriteImage.setImageResource(R.drawable.ic_favourite_off);
		} else {
			aCoffee.favourite = true;
			toastMessage("Added to Favourites !!");
			isFavourite = true; favouriteImage.setImageResource(R.drawable.ic_favourite_on);
		}

	}
}
