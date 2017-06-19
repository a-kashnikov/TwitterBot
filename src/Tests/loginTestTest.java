package Tests;

import org.junit.jupiter.api.Test;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Антон on 14.06.2017.
 */
class loginTestTest {


    @Test
    public void mainTest() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        //the following is set without accesstoken- desktop client
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Waprg7hm4WMuUlMk3KSxFoDSQ")
                .setOAuthConsumerSecret("ZHjyK0DTW1HL2a06QEJZuHp89oHN7uuESGdtYoxEdDMDB1jeyi");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Twitter twitter1 = tf.getInstance();
        assertTrue(twitter==twitter1);
    }

}