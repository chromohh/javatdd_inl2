package org.example.dao;

import org.example.entity.Category;
import org.example.entity.Purchase;

import java.util.Date;

public interface IPurchaseStore {
    /**
     * Get purchases from startDate to endDate.
     * @param startDate
     * @param endDate
     * @return Purchases.
     */
    Purchase[] getPurchase(Date startDate, Date endDate);

    /**
     * Get purchases from startDate to endDate for category catId.
     * @param startDate
     * @param endDate
     * @param catId
     * @return Purchases.
     */
    Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId);

    /**
     * Get all categories.
     * @returnCategories.
      */
    Category[] getAllCategories();
}
