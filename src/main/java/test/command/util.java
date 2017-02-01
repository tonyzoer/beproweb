package test.command;

import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;

import java.util.Random;

/**
 * Created by zoer on 01.02.17.
 */
public class util {
    static Random r=new Random();
    public static User getRandomStudentUser(){
        return new User(){{setId(r.nextInt());
        setProfile(new Profile(){{setId(r.nextInt());setStudentProfile(new StudentProfile(){{setId(r.nextInt());}});}});
        }};
    }
    public static User getRandomCompanyUser(){
        return new User(){{setId(r.nextInt());
            setProfile(new Profile(){{setId(r.nextInt());setCompanyProfile(new CompanyProfile(){{setId(r.nextInt());}});}});
        }};
    }
    public static User getRandomNooneUser(){
        return new User(){{setId(r.nextInt());
            setProfile(new Profile(){{setId(r.nextInt());}});
        }};
    }
    public static int getRandomInt(){
        return r.nextInt();
    }
}
