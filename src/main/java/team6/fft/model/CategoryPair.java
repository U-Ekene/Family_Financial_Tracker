package team6.fft.model;

//E. Unaigwe 
public class CategoryPair {
	
	private String keyword;
    private String category;

    public CategoryPair(String keyword, String category) {
        this.keyword = keyword;
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getCategory() {
        return category;
    }
}
