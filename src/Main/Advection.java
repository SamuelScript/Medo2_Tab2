package Main;

public abstract class Advection extends Iterator {

    @Override
    protected double method(int Xi) {
        //Queriamos evitar fazer este tipo de checagem, mas o tempo não permitiu.
        if(Xi == 1) return Qs[1] - u*(deltaT/2*deltaX)*(Qs[2] - Qs[0]) + ((u*u*deltaT*deltaT) / (2*deltaX*deltaX))*(Qs[2] - 2*Qs[1] + Qs[0]);

        double advQs = Qs[Xi+1] - Qs[Xi];
        double recQs = Qs[Xi] - Qs[Xi-1];
        double slope = recQs / advQs;
        double slope_half_minus = Qs[Xi-1] - Qs[Xi-2] / recQs;

        if(advQs == 0.0) slope = 0;
        if(recQs == 0.0) slope_half_minus = 0;
        return Qs[Xi] - C*recQs - (C / 2)*(1 - C)*(fluxLimiter(slope)*advQs - fluxLimiter(slope_half_minus)*recQs);
    }

    @Override
    protected double get_deltaT() {
        return 0.8*(1/((u/deltaX*deltaX) + (2*a/(deltaX*deltaX))));
    }

    protected abstract double fluxLimiter(double slope);

    @Override
    protected String getName() {
        return "FTBS" + getChildName();
    }

    protected abstract String getChildName();

    Advection(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) {
        super(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
    }
}
