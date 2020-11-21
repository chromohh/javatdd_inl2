package org.example.entity;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Purchase {


    private int id;
    private Date date;
    private float amount;
    private String comment;
    private int categoryId;

    public Purchase(int id, Date date, float amount, String comment, int categoryId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.comment = comment;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "Id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id &&
                Float.compare(purchase.amount, amount) == 0 &&
                categoryId == purchase.categoryId &&
                Objects.equals(date, purchase.date) &&
                Objects.equals(comment, purchase.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, comment, categoryId);
    }
}
