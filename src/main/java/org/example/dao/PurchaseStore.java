package org.example.dao;

import org.example.entity.Category;
import org.example.entity.Purchase;

import java.util.Date;

public class PurchaseStore implements IPurchaseStore{

    public PurchaseStore() {
    }

    @Override
    public Purchase[] getPurchase(Date startDate, Date endDate) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId) {
        return new Purchase[0];
    }

    @Override
    public Category[] getAllCategories() {
        return new Category[0];
    }
}
