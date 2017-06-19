import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Антон on 19.06.2017.
 */
public class HumanImitation {
    private boolean isPicture = false;

    public void imitate (Twitter twitter, int period){
        Random random = new Random();
        if (random.nextInt(period)==1) {
            File file;
            String path = "data\\HumanImitation\\";
            if (random.nextInt(2) == 0) {
                file = new File(path + "pictures");
                isPicture = true;
            } else
                file = new File(path + "TextTweets");
            File[] directionFiles = file.listFiles();
            if (isPicture){
                StatusUpdate statusUpdate = new StatusUpdate("");
                assert directionFiles != null;
                statusUpdate.setMedia(directionFiles[random.nextInt(directionFiles.length)]);
                try {
                    twitter.updateStatus(statusUpdate);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                isPicture = false;
            }
            else {
                assert directionFiles != null;
                File fileForStatus = directionFiles[random.nextInt(directionFiles.length)];
                int counter = 0;
                String status = "";

                try {
                    FileReader fileReader = new FileReader(fileForStatus);
                    LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                    Scanner scanner = new Scanner(fileForStatus);
                    while (lineNumberReader.readLine()!=null)
                        counter++;
                    lineNumberReader.close();
                    for (int i = 0; i < random.nextInt(counter); i++) {
                       status = scanner.nextLine();
                    }
                    twitter.updateStatus(status);
                } catch (TwitterException | IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
