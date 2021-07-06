package DAO;

import Model.Offers;

import java.util.List;

public interface OfferDAO  {

    Integer isOfferValid(Integer offerId);

    List<Offers> getAllOffer();
}
