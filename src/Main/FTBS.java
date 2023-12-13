package Main;

public class FTBS extends Advection {
    FTBS(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }

    @Override
    protected double method(int Xi) {
        return Qs[Xi] - C*(Qs[Xi] - Qs[Xi-1]);
    }

    @Override
    protected double fluxLimiter(double slope) {
        return 0;
    } //FTBS não limita fluxo.

    @Override
    protected String getChildName() {
        return "";
    }
}
