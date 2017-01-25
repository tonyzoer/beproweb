package test;

import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.Profile;

/**
 * Created by zoer on 23.01.17.
 */
public class test {
    public static void main(String[] args) {
        try {
            Profile prf= (Profile) MySqlDaoFactory.getInstance().getDao(Profile.class).getByPK(231);
            System.out.println(prf);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }
}
