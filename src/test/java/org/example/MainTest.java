package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    HashMap<String, Integer> inventory;
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @BeforeEach
    void setup(){
        inventory = new HashMap<>();
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testAddProductValidQuantity(){
        String actualResult = Main.addProduct(inventory, "Banana", 30);
        assertTrue(inventory.containsKey("Banana"));
        assertEquals(30, inventory.get("Banana"));
        assertEquals("Product added!", actualResult);
    }

    @Test
    void testAddProductInvalidQuantity(){
        String actualResult = Main.addProduct(inventory, "Mango", 0);
        assertFalse(inventory.containsKey("Mango"));
        assertNull(inventory.get("Mango"));
        assertEquals("Invalid quantity.", actualResult);
    }

    @Test
    void testAddExistingProduct(){
        //Main.addProduct(inventory, "Banana", 30);
        inventory.put("Banana", 30);
        String actualResult = Main.addProduct(inventory, "Banana", 50);
        assertTrue(inventory.containsKey("Banana"));
        assertEquals(80, inventory.get("Banana"));
        assertEquals("Product added!", actualResult);

    }

    @Test
    void testCheckExistingProduct(){
        //Main.addProduct(inventory, "Milk", 20);
        inventory.put("Milk", 20);
        String actualResult = Main.checkProduct(inventory, "Milk");
        assertTrue(inventory.containsKey("Milk"));
        assertEquals(20, inventory.get("Milk"));
        assertEquals("Milk is in stock: 20", actualResult);
    }

    @Test
    void testCheckNotExistingProduct(){
        String actualResult = Main.checkProduct(inventory, "Ice Cream");
        assertFalse(inventory.containsKey("Ice Cream"));
        assertEquals("Ice Cream is not in the inventory.", actualResult);
    }

    @Test
    void testRemoveExistingProduct(){
        inventory.put("Eggs", 50);
        String actualResult = Main.removeProduct(inventory, "Eggs");
        assertFalse(inventory.containsKey("Eggs"));
        assertNull(inventory.get("Eggs"));
        assertEquals("Product removed.", actualResult);
    }

    @Test
    void testRemoveNotExistingProduct(){
        String actualResult = Main.removeProduct(inventory, "Pizza");
        assertFalse(inventory.containsKey("Eggs"));
        assertNull(inventory.get("Eggs"));
        assertEquals("Pizza is not in the inventory.", actualResult);
    }

    @Test
    public void testUpdateExistingProductValidQuantity() {
        inventory.put("Bread", 10);

        provideInput("25\n");

        String actualResult = Main.updateProduct(inventory, "Bread");

        assertEquals("Stock updated.", actualResult);
        assertEquals(25, inventory.get("Bread"));
    }

    @Test
    public void testUpdateExistingProductInvalidQuantity(){
        inventory.put("Bread", 10);

        provideInput("-4\n");

        String actualResult = Main.updateProduct(inventory, "Bread");
        assertEquals("Invalid quantity.", actualResult);
        assertEquals(10, inventory.get("Bread"));
    }

    @Test
    public void testUpdateNotExistingProductValidQuantity(){

        provideInput("15\n");

        String actualResult = Main.updateProduct(inventory, "Tofu");
        assertFalse(inventory.containsKey("Tofu"));
        assertEquals("Tofu is not in the inventory.", actualResult);
    }

}