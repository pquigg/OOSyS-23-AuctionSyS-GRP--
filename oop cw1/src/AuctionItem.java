import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AuctionItem {
    private Date startTime;
    private Date endTime;
    private int currentBid;
    
    public AuctionItem(Date startTime, Date endTime, int startingBid) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentBid = startingBid;
    }
    
    public long getTimeRemaining() {
        long timeDiffMillis = endTime.getTime() - System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(timeDiffMillis);
    }

}
