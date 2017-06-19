import twitter4j.*;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by Антон on 16.06.2017.
 */
public class Bot extends Thread {
    private String name;
    Utilities util;
    private Twitter twitter;
    private boolean suspendFlag;

    public Bot(String name) throws IOException, ClassNotFoundException {
        util = new Utilities();
        this.name = name;
        twitter = util.getTwitter(name);
        suspendFlag = false;

    }

    public void pause() {
        suspendFlag = true;
    }

    public void resumeIt () {
        suspendFlag = false;
        notify();
    }

    @Override
    public void run() {
        HumanImitation humanImitation = new HumanImitation();
        while (true) {
            humanImitation.imitate(twitter,10);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
/*
            motitorUser("hellcasecom", "Giveaway for", twitter, new Property[] {Property.RETWEET, Property.REPLY});
            try {
                Thread.sleep(900000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
        }
       /* for (int i = 0; i < 30; i++) {
            try {


                twitter.updateStatus(" da" + Utilities.randomSkovorodaTweet());
                Thread.sleep(1000);

            } catch (TwitterException | FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        } */
      // util.deleteLastTweets(0, twitter);





            /*String s;
            do {
                s = Utilities.randomSkovorodaTweet();
            }
            while (s.length()>140);
            Random random = new Random();

            try {
                twitter.updateStatus(s);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(random.nextInt(9)*10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (suspendFlag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/


    }

    private void motitorUser (String name, String regex, Twitter twitter, Property[] properties){
        try {
            User user = twitter.showUser(name);
            if (!user.isFollowRequestSent())
                twitter.createFriendship(name);

            ResponseList<Status> timeLine = twitter.getUserTimeline(name);
            for (Status st :
                    timeLine) {
                Date statusDate = st.getCreatedAt();
                Date current = new Date();
                long time = current.getTime() - statusDate.getTime();
                if (st.getText().contains(regex) && time < 43200000 && !st.isRetweetedByMe()){
                    for (Property pr:
                            properties) {
                        if (pr == Property.LIKE)
                            twitter.createFavorite(st.getId());
                        if (pr == Property.REPLY)
                            reply(st , twitter);
                        if (pr == Property.RETWEET)
                            twitter.retweetStatus(st.getId());

                    }
                }
                Thread.sleep(5000);
            }

        } catch (TwitterException | InterruptedException e) {
            e.printStackTrace();
        }


    }
    private void reply (Status st, Twitter twitter){
        Random random = new Random();
        long [] paras = new long [2];
        try {
            IDs iDs = twitter.getFollowersIDs(-1);
            long id  [] = iDs.getIDs();
            do{
                paras[0] = id[random.nextInt(id.length)];
                paras[1] = id[random.nextInt(id.length)];
            }
            while (paras[0]==paras[1]);

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        StatusUpdate statusUpdate = null;
        try {
            statusUpdate = new StatusUpdate("@" + st.getUser().getScreenName() + "  https://hellcase.com/en/profile/76561198065774880\n" +
                    "@" + twitter.showUser(paras[0]).getScreenName() + " and @"  + twitter.showUser(paras[1]).getScreenName()
                    + " - my friends");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        statusUpdate.setInReplyToStatusId(st.getId());

        try {
            twitter.updateStatus(statusUpdate);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
