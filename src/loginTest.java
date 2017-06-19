import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class loginTest {
    static List <Bot> list = new ArrayList<>();
    static List <Thread> threads = new ArrayList<>();
    private static void startBot (){

            for (Bot bot :
                    list) {
                threads.add(new Thread(bot));
            }
        for (Thread t:
             threads) {
            t.start();
        }
    }

    public static void main(String[] args) {
        String s = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Commands command;
        ArrayList <Thread> list = new ArrayList<>();


        while (true) {
            System.out.println("Enter bot-name|start|pause|resume|exit|all");
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s.equals("start"))
                command = Commands.START;
            else if (s.equals("pause"))
                command = Commands.PAUSE;
            else if (s.equals("resume"))
                command = Commands.RESUME;
            else if (s.equals("exit"))
                command = Commands.EXIT;
            else if (s.equals("all"))
                command = Commands.ALL;
            else
                command = Commands.BOT;
            switch (command){
                case BOT:
                    try {
                        list.add(new Thread(new Bot(s), s));
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case START:
                    for (Thread t :
                            list) {
                        t.start();
                    }
                    break;
                case PAUSE:
                    for (Thread bot :
                            list) {

                    }
                    break;
                case RESUME:
                    for (Thread bot :
                            list) {

                    }
                    break;
                case EXIT:
                    for (Thread bot :
                            list) {
                        bot.interrupt();
                    }
                    break;
                case ALL:
                    try {
                        Scanner scanner = new Scanner(new File("data\\Bots\\botlist.txt"));
                        while (scanner.hasNext()){
                            list.add(new Bot(scanner.nextLine()));
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

            }

        }

        /*String replyMessage="";

        ConfigurationBuilder cb = new ConfigurationBuilder();


        //the following is set without accesstoken- desktop client
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("Waprg7hm4WMuUlMk3KSxFoDSQ")
                .setOAuthConsumerSecret("ZHjyK0DTW1HL2a06QEJZuHp89oHN7uuESGdtYoxEdDMDB1jeyi");

        try {
            TwitterFactory tf = new TwitterFactory(cb.build());

            FileInputStream fi = new FileInputStream("VEnigmov.obj");
            ObjectInputStream oi = new ObjectInputStream(fi);
            AccessToken accessToken = (AccessToken) oi.readObject();
            Twitter twitter = tf.getInstance(accessToken);

            //twitter.updateStatus("#КашніковАнтонБАТЯ!");

            ResponseList<Status> list = twitter.getUserTimeline(3015573621l);
            Status st = list.get(19);
            *//*twitter.retweetStatus(st.getId());
            //twitter.createFavorite(st.getId());
            GeoLocation geoLocation = new GeoLocation(13.411, 34.951);
            long id = st.getId();

            StatusUpdate statusUpdate = new StatusUpdate("@" + st.getUser().getScreenName() + " @Pravdyk1 my friend. \n #КашніковАнтонБАТЯ! ");
            statusUpdate.setInReplyToStatusId(st.getId());
            statusUpdate.setLocation(geoLocation);
            twitter.updateStatus(statusUpdate);*//*
            System.out.println( st.getId());


            *//*StatusUpdate statusUpdate = new StatusUpdate(replyMessage);
            statusUpdate.setInReplyToStatusId(st.getId());
            twitter.updateStatus(statusUpdate);
            System.out.println("dasda");*//*
*//*
            for (Status st :
                    list) {
                if (st.getText().contains("- Enable Chrome notifications on CSGORoll")) {
                    if (!st.isRetweeted()) {
                        StatusUpdate statusUpdate = new StatusUpdate(replyMassege);
                        statusUpdate.inReplyToStatusId(st.getId());
                        twitter.retweetStatus(st.getId());
                    }
                }
            }
*//*
*//*
            try {
                System.out.println("-----");

                // get request token.
                // this will throw IllegalStateException if access token is already available
                // this is oob, desktop client version
                RequestToken requestToken = twitter.getOAuthRequestToken();

                System.out.println("Got request token.");
                System.out.println("Request token: " + requestToken.getToken());
                System.out.println("Request token secret: " + requestToken.getTokenSecret());

                System.out.println("|-----");

                AccessToken accessToken = null;

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                    String pin = br.readLine();

                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Got access token.");
                System.out.println("Access token: " + accessToken.getToken());
                System.out.println("Access token secret: " + accessToken.getTokenSecret());
                FileOutputStream fo = new FileOutputStream("data.txt");
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(accessToken);

            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    System.out.println("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }

            Status status = twitter.updateStatus(testStatus);

            System.out.println("Successfully updated the status to [" + status.getText() + "].");

            ResponseList<Status> list = twitter.getUserTimeline(814943028);
            for (Status st :
                    list) {
                //twitter.retweetStatus(st.getId());

            }


            System.out.println("ready exit");

            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);*//*
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (TwitterException e) {
            e.printStackTrace();
        }*/
    }
}