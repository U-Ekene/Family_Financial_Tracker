package team6.fft.datastore;
//Contributing authors: S Nwachukwu
public class Buyer {
	private String buyerName;
	private String buyerAlias;
	//Contributing authors: S Nwachukwu
	public Buyer(String buyerNameIn) {
		buyerName = buyerNameIn;
		buyerAlias = getInitials(buyerNameIn);
	}
	//Contributing authors: S Nwachukwu
    public String getBuyerName() {
        return buyerName;
    }
  //Contributing authors: S Nwachukwu
    public String getBuyerID() {
        return buyerAlias;
    }
  //Contributing authors: S Nwachukwu
	public String toString() {
        return "Buyer Name: " + buyerName + ", Buyer ID: " + buyerAlias;
    }
	//Contributing authors: S Nwachukwu
	public String getInitials(String input) {
		StringBuilder initials = new StringBuilder();

		for (String word : input.split("\\s+")) {  // Split by one or more spaces
		    if (!word.isEmpty()) {
		        initials.append(word.charAt(0));
		    }
		}

		return initials.toString();
	}
}
