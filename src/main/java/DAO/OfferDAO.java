package DAO;

import Model.Offers;

import java.util.List;

/*
*  Name of file: OfferDAO.java
*  Author:  Nadish Maredia
*  Purpose: Separate the methods which interact with DB
*  Description: This interface is responsible for handling all DB operation related to Offer model
* */
public interface OfferDAO  {

    Integer isOfferValid(Integer offerId);

    List<Offers> getAllOffer();
}
