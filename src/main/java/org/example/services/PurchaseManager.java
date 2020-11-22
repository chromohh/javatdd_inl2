package org.example.services;

import org.example.dao.PurchaseStore;
import org.example.entity.Purchase;

import java.util.Calendar;
import java.util.Date;

public class PurchaseManager implements IPurchaseManager{

    private PurchaseStore purchaseStore;

    public PurchaseManager(PurchaseStore purchaseStore){
        this.purchaseStore = purchaseStore;
    }

    @Override
    public float sumOfMonth(int year, int month) {
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
    public float[] monthlyAverage(int year) {
        try{
            Date startDate = new Date(year, Calendar.JANUARY, 0);
            Date endDate = new Date(year + 1, Calendar.JANUARY, 0);

            float[] returnArr = new float[12];
            Purchase[] purchaseArr = purchaseStore.getPurchase(startDate, endDate);

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
    //Todo
    public float[] yearlyAveragePerCategory(int year) {
        return new float[0];
    }
}
