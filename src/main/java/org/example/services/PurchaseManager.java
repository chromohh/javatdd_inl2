package org.example.services;

import org.example.dao.PurchaseStore;
import org.example.entity.Category;
import org.example.entity.Purchase;
import org.example.exceptions.NonExistingMonthException;
import org.example.exceptions.YearIsInFutureException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PurchaseManager implements IPurchaseManager{

    private PurchaseStore purchaseStore;

    public PurchaseManager(PurchaseStore purchaseStore){
        this.purchaseStore = purchaseStore;
    }

    @Override
    public float sumOfMonth(int year, int month) throws NonExistingMonthException, YearIsInFutureException {

        if(month > 12 || month < 1){
            throw new NonExistingMonthException();
        }

        if(LocalDate.now().getYear() <= year){
            throw new YearIsInFutureException();
        }

        try {
            Date startDate = new Date(year, month - 1, 0);
            Date endDate = new Date(year, month, 0);

            float sum = 0;
            Purchase[] purchaseArr = purchaseStore.getPurchase(startDate, endDate);

            for(Purchase current : purchaseArr){
                sum += current.getAmount();
            }

            return sum;

        }catch(Exception e){
            throw new NullPointerException();
        }
    }

    @Override
    public float[] monthlyAverage(int year) throws YearIsInFutureException {
        if(LocalDate.now().getYear() <= year){
            throw new YearIsInFutureException();
        }

        try{
            Date startDate = new Date(year, Calendar.JANUARY, 0);
            Date endDate = new Date(year + 1, Calendar.JANUARY, 0);

            float[] returnArr = new float[12];
            Purchase[] purchaseArr = purchaseStore.getPurchase(startDate, endDate);

            // Could probably be replaced with switch statement to only iterate once
            for(int i = 0; i < returnArr.length; i++){
                float sum = 0;

                for(Purchase currentP : purchaseArr){

                    if(currentP.getDate().getMonth() == i){
                        sum += currentP.getAmount();
                    }

                }

                returnArr[i] = sum;
            }

            return returnArr;
        }catch(Exception e){
            throw new NullPointerException();
        }
    }

    @Override
    public float[] yearlyAveragePerCategory(int year) throws YearIsInFutureException {

        if(LocalDate.now().getYear() < year){
            throw new YearIsInFutureException();
        }

        try{
            Category[] categories = purchaseStore.getAllCategories();
            float[] retArr = new float[categories.length];

            Date startDate = new Date(year, Calendar.JANUARY, 0);
            Date endDate = new Date(year + 1, Calendar.JANUARY, 0);
            Purchase[] purchases = purchaseStore.getPurchase(startDate, endDate);

            int currentIndex = 0;
            for(Category category : categories){

                float sum = 0;

                for(Purchase purchase : purchases){
                    if(purchase.getCategoryId() == category.getId()){
                        sum += purchase.getAmount();
                    }
                }

                retArr[currentIndex] = sum;

                currentIndex++;
            }

            return retArr;
        }catch(Exception e){
            throw new NullPointerException();
        }
    }
}
