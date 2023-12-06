package Main;

public class BurgersNonConservative extends Burgers {
    BurgersNonConservative(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }

    @Override
    protected String getChildName() {
        return " Não-Conservativo";
    }

    @Override
    protected double method(int Xi) {
        return Qs[Xi] - ((Qs[Xi]*deltaT)/deltaX)*(Qs[Xi] - Qs[Xi-1]);
    }
}
