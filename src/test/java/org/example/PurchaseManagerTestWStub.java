package org.example;
import org.example.services.PurchaseManager;
import org.example.stubs.PurchaseStoreStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PurchaseManagerTestWStub {

    private PurchaseManager purchaseManager;
    private PurchaseStoreStub stub;

    @BeforeEach
    public void initTest(){
        stub = new PurchaseStoreStub();
        purchaseManager = new PurchaseManager(stub);
    }

    @AfterEach
    public void closeTest(){
        stub = null;
        purchaseManager = null;
    }



}
