package team6.fft.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

//Contributing author: B Oladokun
class TransactionTest {
	
	private Transaction transaction1;
    private Transaction transaction2;

    //Contributing author: B Oladokun
    @BeforeEach
    void setUp() {
        transaction1 = new Transaction(
                LocalDate.of(2024, 1, 1),
                "Groceries",
                50.0,
                "John",
                "Food"
        );

        transaction2 = new Transaction(
                LocalDate.of(2024, 1, 2),
                "Electricity Bill",
                75.0,
                "Alice",
                "Utilities"
        );
    }
	
    //Contributing author: B Oladokun
	@Test
    void testGetters() {
        assertEquals(LocalDate.of(2024, 1, 1), transaction1.getDate());
        assertEquals("Groceries", transaction1.getDescription());
        assertEquals(50.0, transaction1.getValue());
        assertEquals("John", transaction1.getBuyer());
        assertEquals("Food", transaction1.getCategory());
    }
	
	//Contributing author: B Oladokun
	@Test
    void testSetBuyer() {
        transaction1.setBuyer("Alice");

        assertEquals("Alice", transaction1.getBuyer());
    }
	
	//Contributing author: B Oladokun
    @Test
    void testSetCategory() {
        transaction1.setCategory("Utilities");

        assertEquals("Utilities", transaction1.getCategory());
    }

    //Contributing author: B Oladokun
    @Test
    void testToString() {
        assertEquals(
                "Transaction{date=2024-01-01, description='Groceries', value=50.0, buyer='John', category='Food'}",
                transaction1.toString()
        );

        assertEquals(
                "Transaction{date=2024-01-02, description='Electricity Bill', value=75.0, buyer='Alice', category='Utilities'}",
                transaction2.toString()
        );
    }


}




  
