package org.example;

import org.example.dao.PurchaseStore;
import org.example.entity.Category;
import org.example.entity.Purchase;
import org.example.exceptions.NonExistingMonthException;
import org.example.exceptions.YearIsInFutureException;
import org.example.services.PurchaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class PurchaseManagerTestWMockito {
    PurchaseStore purchaseStore;
    PurchaseManager purchaseManager;

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
        purchaseStore = mock(PurchaseStore.class);
        purchaseManager = new PurchaseManager(purchaseStore);

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


        category1 = new Category(0, "cat1");
        category2 = new Category(1, "cat2");
        category3 = new Category(2, "cat3");

    }

    @AfterEach
    public void closeTest(){
        purchaseStore = null;
        purchaseManager = null;

        purchase1 = null;
        purchase2 = null;
        purchase3 = null;
        purchase4 = null;
        purchase5 = null;
        purchase6 = null;

        category1 = null;
        category2 = null;
        category3 = null;
    }

    @Test
    public void testSumOfMonth() throws NonExistingMonthException, YearIsInFutureException {

        when(purchaseStore.getPurchase(new Date(2012, 0, 0),
                new Date(2012, 1, 0)))
                .thenReturn(new Purchase[]{purchase4, purchase5, purchase6});

        assertEquals(1000, purchaseManager.sumOfMonth(2012, 1));
    }

    @Test
    public void testSumOfNonExistingMonth(){
        assertThrows(NonExistingMonthException.class, () -> purchaseManager.sumOfMonth(2012, 99));
    }


    @Test
    //Todo
    public void testMontlyAverage() throws YearIsInFutureException {

        when(purchaseStore.getPurchase(new Date(2010, 0, 0),
                new Date(2011, 0, 0)))
                .thenReturn(new Purchase[]{purchase1, purchase2, purchase3});

        float[] expected1 = {0, 0, 200, 100, 100, 0, 0 , 0 , 0 , 0 , 0, 0};

        assertArrayEquals(expected1, purchaseManager.monthlyAverage(2010));
    }


    @Test
    public void testMontlyAverageOnEmptyYear() throws YearIsInFutureException {

        when(purchaseStore.getPurchase(new Date(1777, 0, 0),
                new Date(1778, 0, 0)))
                .thenReturn(new Purchase[]{});

        float[] expected1 = {0, 0, 0, 0, 0, 0, 0 , 0 , 0 , 0 , 0, 0};

        assertArrayEquals(expected1, purchaseManager.monthlyAverage(1777));
    }

    @Test
    public void testYearlyAveragePerCategory() throws YearIsInFutureException {

        when(purchaseStore.getAllCategories())
                .thenReturn(new Category[]{category1,category2,category3});

        when(purchaseStore.getPurchase(new Date(2012, Calendar.JANUARY, 0),
                new Date(2013, Calendar.JANUARY, 0)))
                .thenReturn(new Purchase[]{purchase4, purchase5, purchase6});

        float[] expected1 = {200,300,500};

        assertArrayEquals(expected1, purchaseManager.yearlyAveragePerCategory(2012));
    }

    @Test
    public void testYearlyAveragePerCategoryNonExistingYear() throws YearIsInFutureException {
        when(purchaseStore.getAllCategories())
                .thenReturn(new Category[]{category1,category2,category3});

        when(purchaseStore.getPurchase(new Date(9998, Calendar.JANUARY, 0),
                new Date(9999, Calendar.JANUARY, 0)))
                .thenReturn(new Purchase[]{});

        assertThrows(YearIsInFutureException.class, () -> purchaseManager.yearlyAveragePerCategory(2021));
    }
}
