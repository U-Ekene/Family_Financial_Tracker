package team6.fft.datastore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//Contributing author: B Oladokun
public class Categories {
    private ArrayList<String> cat = new ArrayList<>();
    private String categoryFilePath;
  //Contributing author: B Oladokun
    public Categories(String categoryFilePath) {
    	this.categoryFilePath = categoryFilePath;
        try (Scanner sc = new Scanner(new File(categoryFilePath))) {  // Use File constructor here
            while (sc.hasNextLine()) {
                cat.add(sc.nextLine());
            }
        } catch (Exception e) {
            System.err.println("Error reading categories file: " + e.getMessage());
        }
    }
  //Contributing author: B Oladokun
    public ArrayList<String> getCategories() {
        ArrayList<String> catCopy = new ArrayList<>();
        for (String temp : cat) {
            catCopy.add(new String(temp));  // Creating a copy for safety
        }
        return catCopy;
    }
  //Contributing author: B Oladokun
    public void addCategory(String category) {
        cat.add(category);
    }
    
    
}
