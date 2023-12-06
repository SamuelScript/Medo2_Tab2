package Main;

public class Superbee extends Advection {
    Superbee(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }

    @Override
    protected double fluxLimiter(double slope) {
        if(slope <= 0) return 0;
        double minj = Math.max(2, slope);
        return Math.max(Math.min(1, 2*slope), Math.max(2, slope));
    }

    @Override
    protected String getChildName() {
        return " + Limitador Superbee";
    }
}
