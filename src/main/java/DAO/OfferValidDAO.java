package DAO;

/*
 *  Name of file: OfferValidDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Offer model
 * */
public interface OfferValidDAO {

    Integer isOfferValid(Integer offerId);
}
