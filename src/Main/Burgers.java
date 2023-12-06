package Main;

public abstract class Burgers extends Iterator{
    @Override
    protected String getName() {
        return "Burgers" + getChildName();
    }

    protected abstract String getChildName();

    Burgers(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }
    @Override
    protected double get_deltaT() {
        return deltaX / Math.max(Ca, Math.max(Cb, Cc));
    }
}
