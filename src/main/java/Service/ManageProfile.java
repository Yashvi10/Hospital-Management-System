package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 *  Name of file: ManageProfile.java
 *  Author:  Abimbola Babalola
 *  Purpose: This class manages basic operations for UserManagement.java
 *  Description: This class updates a saved profile, resets a user's password, encrypts a password string
 *              and validates email
 */

public class ManageProfile {

    Connection conn;
    Connection con;
    ResultSet resultSet;
    Statement statement;
    int checkRecord=0;

    public ManageProfile(){}


    public String encryptPassword(String password)  {
        StringBuilder string=new StringBuilder();
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] passByte=md.digest();

            for (byte b : passByte) {
                string.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
        }
        catch(NoSuchAlgorithmException e){
            e.getMessage();
        }
        return string.toString();
    }

    public boolean checkCredentials(String email, String confirmEmail, String password, String confirmPassword){
        boolean check=false;
        if ( (email.equals(confirmEmail)) &&(password.equals(confirmPassword)))  {
            check= true;
        }
        return check;
    }
}
