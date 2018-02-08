package entiry;

/**
 * Created by weijie on 2018/2/8.
 */

public class Bill {

    private int id;
    private String type;
    private float percent;


    public int getId() {
        return id;
    }

    public Bill setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Bill setType(String type) {
        this.type = type;
        return this;
    }

    public float getPercent() {
        return percent;
    }

    public Bill setPercent(float percent) {
        this.percent = percent;
        return this;
    }
}
