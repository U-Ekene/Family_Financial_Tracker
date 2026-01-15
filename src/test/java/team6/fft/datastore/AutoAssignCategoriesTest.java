package team6.fft.datastore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team6.fft.datastore.AutoAssignCategories;

//Contributing authors: E Unaigwe
public class AutoAssignCategoriesTest {

    private AutoAssignCategories autoAssignCategories;

    @BeforeEach
    public void setUp() {
        autoAssignCategories = new AutoAssignCategories();
    }
    
    //Contributing authors: E Unaigwe
    @Test
    public void testGetCategory_ExistingKeyword() {
        // Test existing keywords
        assertEquals("Groceries", autoAssignCategories.getCategory("INSTACART #125"));
        assertEquals("Entertainment", autoAssignCategories.getCategory("Netflix and Chill"));
        assertEquals("Food", autoAssignCategories.getCategory("THAI Restaurant"));
    }

    //Contributing authors: E Unaigwe
    @Test
    public void testGetCategory_KeywordCaseInsensitive() {
        // Ensure case-insensitivity works
        assertEquals("Groceries", autoAssignCategories.getCategory("INSTACART delivery service"));
        assertEquals("Entertainment", autoAssignCategories.getCategory("NETFLIX binge-watching"));
    }
    
    //Contributing authors: E Unaigwe
    @Test
    public void testGetCategory_NoMatchingKeyword() {
        // Test a description with no matching keyword
        assertEquals("unassigned", autoAssignCategories.getCategory("Auto Car"));
    }
    
    //Contributing authors: E Unaigwe
    @Test
    public void testAddPair_NewKeyword() {
        // Add a new keyword-category pair
        autoAssignCategories.addPair("Lawtons", "Pharmacy");
        assertEquals("Pharmacy", autoAssignCategories.getCategory("Lawtons #345"));
    }
}

