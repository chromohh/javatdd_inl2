package org.example.stubs;

import org.example.dao.PurchaseStore;
import org.example.entity.Category;
import org.example.entity.Purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseStoreStub extends PurchaseStore {
    private List<Category> categories = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    public void addPurchase(Purchase purchase){
        purchases.add(purchase);
    }

    public PurchaseStoreStub() {
    }

    @Override
    public Purchase[] getPurchase(Date startDate, Date endDate) {
        try{
            Purchase[] retArr;
            retArr = purchases.stream()
                    .filter(o -> o.getDate().after(startDate) && o.getDate().before(endDate))
                    .toArray(Purchase[]::new);
            return retArr;

        }catch(Exception e){
            throw new NullPointerException();
        }
    }

    @Override
    public Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId) {
        try{
            Purchase[] retArr;
            retArr = purchases.stream()
                    .filter(o -> o.getDate().after(startDate) && o.getDate().before(endDate))
                    .filter(o -> o.getCategoryId() == catId)
                    .toArray(Purchase[]::new);
            return retArr;

        }catch(Exception e){
            throw new NullPointerException();
        }
    }

    @Override
    public Category[] getAllCategories() {
        return categories.toArray(Category[]::new);
    }
}
