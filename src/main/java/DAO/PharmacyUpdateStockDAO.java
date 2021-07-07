package DAO;

/*
 *  Name of file: PharmacyUpdateStockDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the logic which interact with DB
 *  Description: This interface is responsible for handling all DB operation related to Pharmacy model
 * */
public interface PharmacyUpdateStockDAO {

    void updateStock(String pId, Integer qty);
}
