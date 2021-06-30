package DAO;

import Model.Pharmacy;

import java.util.List;

public interface PharmacyDAO {

    List<Pharmacy> getAllMedicines();

    void updateStock(String pId, Integer qty);
}
