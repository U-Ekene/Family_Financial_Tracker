package team6.fft.datastore;

import java.util.ArrayList;
import java.util.List;
import team6.fft.model.CategoryPair;

//Contributing authors: E. Unaigwe 
public class AutoAssignCategories {
	
	private List<CategoryPair> categoryPairs;
	//Contributing authors: E. Unaigwe 
	public AutoAssignCategories() {
		
		categoryPairs = new ArrayList<>();
		
		categoryPairs.add(new CategoryPair("instacart", "Groceries"));
		categoryPairs.add(new CategoryPair("sobeys", "Groceries"));
		categoryPairs.add(new CategoryPair("superstore", "Groceries"));
		categoryPairs.add(new CategoryPair("cineplex", "Entertainment"));
		categoryPairs.add(new CategoryPair("netflix", "Entertainment"));
		categoryPairs.add(new CategoryPair("audible", "Entertainment"));
		categoryPairs.add(new CategoryPair("channels", "Entertainment"));
		categoryPairs.add(new CategoryPair("channel", "Entertainment"));
		categoryPairs.add(new CategoryPair("prime", "Entertainment"));
		categoryPairs.add(new CategoryPair("video", "Entertainment"));
		categoryPairs.add(new CategoryPair("restaurant", "Food"));
		categoryPairs.add(new CategoryPair("Baker", "Food"));
		categoryPairs.add(new CategoryPair("diner", "Food"));
		categoryPairs.add(new CategoryPair("butcher", "Food"));
		categoryPairs.add(new CategoryPair("dishes", "Food"));
		categoryPairs.add(new CategoryPair("hospital", "Health and Wellness"));
		categoryPairs.add(new CategoryPair("drug", "Health and Wellness"));
		categoryPairs.add(new CategoryPair("massage", "Health and Wellness"));
		categoryPairs.add(new CategoryPair("cleaner", "Health and Wellness"));
		categoryPairs.add(new CategoryPair("circle k", "Retail"));
		categoryPairs.add(new CategoryPair("depot", "Retail"));
		categoryPairs.add(new CategoryPair("bill", "Bills"));
		categoryPairs.add(new CategoryPair("prime", "Credit"));
		categoryPairs.add(new CategoryPair("animal", "Pet"));
		categoryPairs.add(new CategoryPair("pet", "Pet"));
	}
	//Contributing authors: E. Unaigwe 
	public String getCategory(String description) {
		for(CategoryPair pair: categoryPairs) {
			if(description.toLowerCase().contains(pair.getKeyword().toLowerCase())) {
				return pair.getCategory();
			}
		}
		return "unassigned";
	} 
	//Contributing authors: E. Unaigwe 
	public void addPair(String keyword, String category) {
		 categoryPairs.add(new CategoryPair(keyword.toLowerCase(), category));	
	}
}
