package Utils;
import org.jetbrains.annotations.NotNull;

public class TopBase implements Comparable<TopBase> {
    public Integer rating;
    public String ID;

    public TopBase(int rating, String ID) {
        this.rating = rating;
        this.ID = ID;
    }

    public Integer getRating () {
        return rating;
    }

    public String getID() {
        return ID;
    }

    @Override
    public int compareTo(@NotNull TopBase topBase) {
        return this.getRating() - topBase.getRating();
    }
}
