/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad4;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Author implements Runnable {
    private String[] words = null;
    private BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>();

    public Author(String[] words) {
        this.words = words;
    }

    @Override
    public void run() {
        for (int i = 0; i < words.length; i++) {
            try {
                blockingQueue.put(words[i]);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public String[] getWords() {
        return words;
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }

}
