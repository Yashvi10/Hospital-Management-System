package Interface;

/*
 *  Name of file: OrderLastIdDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Order
 * */
public interface OrderLastIdDAO {

    Integer getLastOrderId();
}
