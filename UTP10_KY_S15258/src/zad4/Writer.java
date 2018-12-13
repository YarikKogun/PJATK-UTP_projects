/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad4;


public class Writer implements Runnable {
    private Author author = null;

    public Writer(Author author){
        this.author = author;
    }
    @Override
    public void run() {
        for(int i = 0; i<author.getWords().length; i++){
            try {
                System.out.println(author.getBlockingQueue().take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Author getAuthor() {
        return author;
    }
}
