package Main;

public class BurgersConservative extends Burgers {
    BurgersConservative(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }

    @Override
    protected String getChildName() {
        return " Conservativo";
    }

    @Override
    protected double method(int Xi) {
        return Qs[Xi] - (deltaT/deltaX)*((Qs[Xi]*Qs[Xi]/2) - (Qs[Xi-1]*Qs[Xi-1]/2));
    }
}
