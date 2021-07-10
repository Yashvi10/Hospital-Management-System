package Interface;

import Model.Offers;

import java.util.List;

/*
 *  Name of file: OfferDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Offer model
 * */
public interface OfferDAO {

    List<Offers> getAllOffer();
}
