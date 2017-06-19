import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Антон on 17.06.2017.
 */
public class Utilities {

    public Twitter getTwitter (String name){
        Twitter twitter;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        //the following is set without accesstoken- desktop client
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Waprg7hm4WMuUlMk3KSxFoDSQ")
                .setOAuthConsumerSecret("ZHjyK0DTW1HL2a06QEJZuHp89oHN7uuESGdtYoxEdDMDB1jeyi");
        TwitterFactory tf = new TwitterFactory(cb.build());
        FileInputStream fi = null;
        try {
            fi = new FileInputStream("data\\Bots\\" + name + ".obj");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(fi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccessToken accessToken = null;
        try {
            accessToken = (AccessToken) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        twitter = tf.getInstance(accessToken);
        return twitter;
    }

    public static String randomSkovorodaTweet() throws FileNotFoundException {
        String tweet = "";

        Random random = new Random();
        int counter = 89;
        do {

                Scanner scanner = new Scanner(new File("data\\HumanImitation\\TextTweets\\Skovoroda.txt"));

                for (int i = 0; i < random.nextInt(counter); i++) {
                    tweet = scanner.nextLine();
                }

        }
        while (tweet.length()>=140);
        return tweet;
    }





    public void deleteLastTweets (int t, Twitter twitter){
        try {
            Paging paging = new Paging(1,200);
            ResponseList <Status> list = twitter.getUserTimeline(twitter.getId(), paging);
            if (t==0){
                for (Status st :
                        list) {
                    twitter.destroyStatus(st.getId());
                }
            }
            else {
                Iterator <Status> iterator = list.iterator();
                for (int i = 0; i < t; i++) {
                    twitter.destroyStatus(iterator.next().getId());
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }
}
