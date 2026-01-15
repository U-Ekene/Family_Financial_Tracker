
package team6.fft.model;

import java.time.LocalDate;
//Contributing author: B Oladokun
public class Transaction{
    private LocalDate date;
    private String description;
    private double value;
    private String buyer;
    private String category;
  //Contributing author: B Oladokun
    public Transaction(LocalDate date, String description, double value, String buyer, String category) {
        this.date = date;
        this.description = description;
        this.value = value;
        this.buyer = buyer;
        this.category = category;
    }
  //Contributing author: B Oladokun
    public LocalDate getDate() {
        return date;
    }
  //Contributing author: B Oladokun
    public String getDescription() {
        return description;
    }
  //Contributing author: B Oladokun
    public double getValue() {
        return value;
    }
  //Contributing author: B Oladokun
    public String getBuyer() {
        return buyer;
    }
  //Contributing author: B Oladokun
    public String getCategory() {
        return category;
    }
  //Contributing author: B Oladokun
    public void setBuyer(String buyer) {
    	this.buyer = buyer;
    }
  //Contributing author: B Oladokun
    public void setCategory(String category) {
    	this.category = category;
    }
  //Contributing author: B Oladokun
    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", buyer='" + buyer + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}