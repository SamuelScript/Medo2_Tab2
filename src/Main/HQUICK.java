package Main;

public class HQUICK extends Advection {
    HQUICK(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }

    @Override
    protected double fluxLimiter(double slope) {
        if(slope <= 0) return 0;
        return (4*slope) / (3 + slope); //Assumindo slope > 0;
    }

    @Override
    protected String getChildName() {
        return " + Limitador HQUICK";
    }
}
