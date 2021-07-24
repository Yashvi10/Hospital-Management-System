package Interface;

/*
 *  Name of file: UserIdVerifiedDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the methods which interact with DB
 *  Description: This interface is responsible for verify the user is found in db or not
 * */
public interface UserIdVerifiedDAO {

    Boolean isUserFound(Integer user_id);
}
