package org.example.services;

import org.example.exceptions.NonExistingMonthException;
import org.example.exceptions.YearIsInFutureException;

public interface IPurchaseManager {
    /**
     * Calculate the sum of all purchases for a month.
     * @param year
     * @param month
     */
    float sumOfMonth(int year, int month) throws NonExistingMonthException, YearIsInFutureException;

    /**
     * For a specified year, calculate each months average purchases.
     * @param year
     * @return Array of averages per month.
     */
    float[] monthlyAverage(int year) throws YearIsInFutureException;
    /**
     * For a specified year, calculate each category's average purchases.
     * @param year
     * @return Array of averages per category.
     */
    float[] yearlyAveragePerCategory(int year) throws YearIsInFutureException;
}
