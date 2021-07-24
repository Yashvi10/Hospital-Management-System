package Service;

import Interface.OfferDAO;
import Interface.OfferValidDAO;
import Model.Offers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: OfferService.java
 *  Author:  Nadish Maredia
 *  Purpose: This class is like service which will implement the OfferDAO,OfferValidDAO
 *  Description: This class will implement the actual logic how to process or query
 *               the DB and return the result
 * */
public class OfferService implements OfferDAO, OfferValidDAO {

    CustomConnection con = new CustomConnection();
    @Override
    public Integer isOfferValid(Integer offerId)  {

        Connection conn = con.Connect();

        Integer resultToReturn = 0;

        if  (conn != null)  {
            String sql = "SELECT * FROM offers where offer_id = "
                    + offerId +" AND isActive = '1';";
            Statement statement = null;
            try  {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if  (rs != null)  {
                    while(rs.next())
                    {
                        Integer o_id=rs.getInt(1);
                        if(o_id == offerId) {
                            resultToReturn = rs.getInt(3);
                            break;
                        }
                    }
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return resultToReturn;
    }

    @Override
    public List<Offers> getAllOffer() {

        Connection conn = con.Connect();

        List<Offers> offersList = new ArrayList<Offers>();

        if(conn != null) {
            String sql = "SELECT * FROM offers where isActive = '1'";
            Statement statement = null;
            try  {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if  (rs != null)  {
                    while(rs.next())
                    {
                        Offers offers = new Offers();
                        offers.setOffer_id(rs.getInt(1));
                        offers.setName(rs.getString(2));
                        offers.setDiscount(rs.getDouble(3));
                        offers.setActive(rs.getBoolean(4));

                        offersList.add(offers);
                    }
                }
            }  catch  (SQLException throwables)  {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return offersList;
    }

}
