package JsonObjs;

/**
 * Created by maxim on 30.04.2021.
 */
public class Wind {

    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public Wind(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Wind(){

    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
