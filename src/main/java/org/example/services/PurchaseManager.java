package org.example.services;

import org.example.dao.PurchaseStore;

public class PurchaseManager implements IPurchaseManager{

    private PurchaseStore purchaseStore;

    public PurchaseManager(PurchaseStore purchaseStore){
        this.purchaseStore = purchaseStore;
    }

    @Override
    //Todo
    public float sumOfMonth(int year, int month) {
        return 0;
    }

    @Override
    //Todo
    public float[] monthlyAverage(int year) {
        return new float[0];
    }

    @Override
    //Todo
    public float[] yearlyAveragePerCategory(int year) {
        return new float[0];
    }
}
