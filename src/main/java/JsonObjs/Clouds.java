package JsonObjs;

/**
 * Created by maxim on 30.04.2021.
 */
public class Clouds {

    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public Clouds(int all) {
        this.all = all;
    }

    public Clouds(){

    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
