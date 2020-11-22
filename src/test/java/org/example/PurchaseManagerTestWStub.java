package org.example;
import org.example.entity.Category;
import org.example.entity.Purchase;
import org.example.exceptions.NonExistingMonthException;
import org.example.exceptions.YearIsInFutureException;
import org.example.services.PurchaseManager;
import org.example.stubs.PurchaseStoreStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseManagerTestWStub {

    PurchaseManager purchaseManager;
    PurchaseStoreStub stub;

    Purchase purchase1;
    Purchase purchase2;
    Purchase purchase3;
    Purchase purchase4;
    Purchase purchase5;
    Purchase purchase6;

    Category category1;
    Category category2;
    Category category3;

    @BeforeEach
    public void initTest(){
        stub = new PurchaseStoreStub();
        purchaseManager = new PurchaseManager(stub);

        Date d1 = new Date(2010, Calendar.MARCH, 5);
        Date d2 = new Date(2010, Calendar.APRIL, 6);
        Date d3 = new Date(2010, Calendar.MAY, 7);
        Date d4 = new Date(2012, Calendar.JANUARY, 5);
        Date d5 = new Date(2012, Calendar.JANUARY, 7);
        Date d6 = new Date(2012, Calendar.JANUARY, 12);

        purchase1 = new Purchase(0, d1, 200,"comment1",0);
        purchase2 = new Purchase(1, d2, 100, "comment2", 2);
        purchase3 = new Purchase(2, d3, 100, "comment3", 2);
        purchase4 = new Purchase(3, d4, 500, "comment4", 2);
        purchase5 = new Purchase(4, d5, 200, "comment5", 0);
        purchase6 = new Purchase(5, d6, 300, "comment6", 1);

        stub.addPurchase(purchase1);
        stub.addPurchase(purchase2);
        stub.addPurchase(purchase3);
        stub.addPurchase(purchase4);
        stub.addPurchase(purchase5);
        stub.addPurchase(purchase6);

        category1 = new Category(0, "cat1");
        category2 = new Category(1, "cat2");
        category3 = new Category(2, "cat3");

        stub.addCategory(category1);
        stub.addCategory(category2);
        stub.addCategory(category3);
    }

    @AfterEach
    public void closeTest(){
        stub = null;
        purchaseManager = null;

        purchase1 = null;
        purchase2 = null;
        purchase3 = null;
        purchase4 = null;

        category1 = null;
        category2 = null;
        category3 = null;
    }

    @Test
    public void testSumOfMonth() throws NonExistingMonthException, YearIsInFutureException {
        assertEquals(1000, purchaseManager.sumOfMonth(2012, 1));
        assertEquals(100, purchaseManager.sumOfMonth(2010, 5));
    }

    @Test
    public void testSumOfNonExistingMonth() throws NonExistingMonthException {
        assertThrows(NonExistingMonthException.class, () -> purchaseManager.sumOfMonth(2012, 99));
    }

    @Test
    public void testSumOfNonExistingYear() throws YearIsInFutureException{
        assertThrows(YearIsInFutureException.class, () -> purchaseManager.sumOfMonth(9999, 12));
    }

    @Test
    public void testMontlyAverage() throws YearIsInFutureException {
        float[] expected1 = {0, 0, 200, 100, 100, 0, 0 , 0 , 0 , 0 , 0, 0};
        float[] expected2 = {1000, 0, 0, 0, 0, 0, 0 , 0 , 0 , 0 , 0, 0};

        assertArrayEquals(expected1, purchaseManager.monthlyAverage(2010));
        assertArrayEquals(expected2, purchaseManager.monthlyAverage(2012));
    }


    @Test
    public void testMontlyAverageOnEmptyYear() throws YearIsInFutureException {
        float[] expected1 = {0, 0, 0, 0, 0, 0, 0 , 0 , 0 , 0 , 0, 0};

        assertArrayEquals(expected1, purchaseManager.monthlyAverage(1843));
    }

    @Test
    public void testMontlyAverageOnFutureYear() throws YearIsInFutureException {
        assertThrows(YearIsInFutureException.class, () -> purchaseManager.monthlyAverage(9999));
    }


    @Test
    public void testYearlyAveragePerCategory() throws YearIsInFutureException {
        float[] expected1 = {200,300,500};
        float[] expected2 = {200,0,200};

        assertArrayEquals(expected1, purchaseManager.yearlyAveragePerCategory(2012));
        assertArrayEquals(expected2, purchaseManager.yearlyAveragePerCategory(2010));
    }

    @Test
    public void testYearlyAveragePerCategoryNonExistingYear() throws YearIsInFutureException {
        float[] expected1 = {0,0,0};

        assertArrayEquals(expected1, purchaseManager.yearlyAveragePerCategory(4));
    }

    @Test
    public void testYearlyAveragePerCategoryFutureYear() throws YearIsInFutureException {
        assertThrows(YearIsInFutureException.class, () -> purchaseManager.yearlyAveragePerCategory(9999));
    }

}
