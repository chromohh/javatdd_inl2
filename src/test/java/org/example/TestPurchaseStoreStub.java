package org.example;
import org.example.entity.Category;
import org.example.entity.Purchase;
import org.example.stubs.PurchaseStoreStub;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

public class TestPurchaseStoreStub {

    PurchaseStoreStub stub;

    Purchase purchase1;
    Purchase purchase2;
    Purchase purchase3;
    Purchase purchase4;

    Category category1;
    Category category2;
    Category category3;

    @BeforeEach
    public void initTest(){
        stub = new PurchaseStoreStub();

        Date d1 = new Date(2010, Calendar.MARCH, 5);
        Date d2 = new Date(2010, Calendar.APRIL, 6);
        Date d3 = new Date(2010, Calendar.MAY, 7);
        Date d4 = new Date(2012, Calendar.JANUARY, 5);

        purchase1 = new Purchase(0, d1, 2,"comment1",0);
        purchase2 = new Purchase(1, d2, 1, "comment2", 2);
        purchase3 = new Purchase(2, d3, 1, "comment3", 1);
        purchase4 = new Purchase(3, d4, 5, "comment4", 2);

        stub.addPurchase(purchase1);
        stub.addPurchase(purchase2);
        stub.addPurchase(purchase3);
        stub.addPurchase(purchase4);

        category1 = new Category(0, "cat1");
        category2 = new Category(1, "cat2");
        category3 = new Category(0, "cat3");

        stub.addCategory(category1);
        stub.addCategory(category2);
        stub.addCategory(category3);
    }

    @AfterEach
    public void closeTest(){
        stub = null;
        purchase1 = null;
        purchase2 = null;
        purchase3 = null;
        purchase4 = null;
        category1 = null;
        category2 = null;
        category3 = null;
    }

    @Test
    public void testGetPurchase(){
        Purchase[] expected = {purchase1, purchase2};
        Date startDate = new Date(2010, Calendar.JANUARY, 6);
        Date endDate = new Date(2010, Calendar.MAY, 1);

        assertArrayEquals(expected, stub.getPurchase(startDate, endDate));
    }

    @Test
    public void testGetPurchaseNonExisting(){
        Purchase[] expected = {};
        Date startDate = new Date(1997, Calendar.JANUARY, 6);
        Date endDate = new Date(1996, Calendar.JANUARY, 6);

        assertArrayEquals(expected, stub.getPurchase(startDate, endDate));
    }

    @Test
    public void testGetPurchaseByCategory(){
        Purchase[] expected = {purchase2, purchase4};
        Date startDate = new Date(2010, Calendar.JANUARY, 1);
        Date endDate = new Date(2013, Calendar.JANUARY, 1);

        assertArrayEquals(expected, stub.getPurchasesByCategory(startDate, endDate, 2));
    }

    @Test
    public void testGetPurchaseNonExistingCategory(){
        Purchase[] expected = {};
        Date startDate = new Date(2010, Calendar.JANUARY, 1);
        Date endDate = new Date(2013, Calendar.JANUARY, 1);

        assertArrayEquals(expected, stub.getPurchasesByCategory(startDate, endDate, 9));
    }

    @Test
    public void testGetAllCategories(){
        Category[] expected = {category1, category2, category3};

        assertArrayEquals(expected, stub.getAllCategories());
    }
}
