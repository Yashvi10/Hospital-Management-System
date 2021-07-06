package Services;

import DAO.OfferDAO;
import Model.Offers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
* This class is responsible for performing all operation related to offers
* */
public class OfferService implements OfferDAO  {

    @Override
    public Integer isOfferValid(Integer offerId)  {
        CustomConnection con = new CustomConnection();
        Connection conn = con.Connect();

        Integer resultToReturn = 0;

        if  (conn != null)  {
            String sql = "SELECT * FROM CSCI5308_8_DEVINT.offers where offer_id = "
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
            }
        }
        return resultToReturn;
    }

    @Override
    public List<Offers> getAllOffer() {
        CustomConnection con = new CustomConnection();
        Connection conn = con.Connect();

        List<Offers> offersList = new ArrayList<Offers>();

        if(conn != null) {
            String sql = "SELECT * FROM CSCI5308_8_DEVINT.offers where isActive = '1'";
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
            }
        }
        return offersList;
    }

}
