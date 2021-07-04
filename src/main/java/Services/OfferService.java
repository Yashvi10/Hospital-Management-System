package Services;

import DAO.OfferDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OfferService implements OfferDAO {

    @Override
    public Integer isOfferValid(Integer offerId) {
        CustomConnection con = new CustomConnection();
        Connection conn = con.Connect();

        Integer resultToReturn = 0;

        if(conn != null) {
            String sql = "SELECT * FROM CSCI5308_8_DEVINT.offers where offer_id = " + offerId +" AND isActive = '1';";
            Statement statement = null;

            try {
                statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if(rs != null){
                    while(rs.next())
                    {
                        Integer o_id=rs.getInt(1);
                        if(o_id == offerId) {
                            resultToReturn = rs.getInt(3);
                            break;
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return resultToReturn;

    }
}
