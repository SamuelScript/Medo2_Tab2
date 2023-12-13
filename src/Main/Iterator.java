package Main;

import java.awt.Color;
import java.io.IOException;

public abstract class Iterator {
    protected final double length_x; //Lx : entre 1 e 20 [metros]
    private final double length_xf; //Uma fração de Lx
    protected final double u; //Coeficiente de advecção : entre 0.1 a 2.0 [metros/segundo]
    protected final double a; //Coeficiente de difusão : entre 0.001 a 0.1 [metros²/segundo]
    protected final double C; //Número de Courant para este problema
    private final double t_max; // tempo máximo : entre 60 e 600 [segundos]
    private final double t_int; // tempo intermediário : % t_max
    protected final double deltaX; //Comprimento da malha espacial
    protected final double deltaT; //Intervalo da malha temporal
    protected final double Ca; //Concentração inicial A
    protected final double Cb; //Concentração inicial B
    protected final double Cc; //Concentração inicial C
    protected double[] Qs; //Valores no interior do domínio
    private double t_draw;
    private double dt_draw;
    private GChart chart;

    protected abstract double method( int Xi );
    protected abstract String getName();
    protected abstract double get_deltaT();

    Iterator(double length_x, double length_xf, double u, double a, double t_max, double t_int, double Ca, double Cb, double Cc, int s_partition, int draw) { //Inicializa todos os valores para o tempo 0.
        this.length_x = length_x;
        this.length_xf = length_xf;
        this.u = u;
        this.a = a;
        this.t_max = t_max;
        this.t_int = t_int;
        this.deltaX = length_x/s_partition;
        this.Ca = Ca;
        this.Cb = Cb;
        this.Cc = Cc;

        deltaT = get_deltaT();
        C = u*(deltaT/deltaX);

        dt_draw = t_max / draw; //Intervalo para desenhar no gráfico
        t_draw  = dt_draw;
        chart = new GChart(getName(), draw + 1);

        Qs = new double[s_partition+1];

        int i;
        for(i = 0; i < Math.round((length_xf/length_x)*s_partition); i++) Qs[i] = Ca;
        for(i = i; i <= s_partition; i++) Qs[i] = Cb;
        for(int j = 1; j <= draw; j++) chart.setColor(j, Color.BLUE);
    }

    public void run() { //Roda quantas iterações forem necessárias até que T alcançe t_max.
        double t = 0; //Nosso tempo inicial.
        int c = 0; //Qual curva está sendo desenhada.
        double[] Qss = new double[Qs.length];
        final double dtdx = deltaT/deltaX;
        final int length = Qs.length;

        for(int i = 0; i < length; i++) chart.addData(0, i * deltaX, Qs[i]);
        chart.setLabel(0, "T = 0.0");
        c++;

        Qss[0] = Ca; //Precisamos inicializar o contorno em nosso array auxiliar também.

        for(t = deltaT; t <= t_int; t += deltaT){ //Iterar em 0 < t <= t_int.
            for(int i = 1; i < length-1; i++) { //Iterar todos os volumes da malha (Exceto contornos!)
                Qss[i] = method(i);
            }
            double[] tmp = Qss;
            Qss = Qs;
            Qs = tmp;

            if(t_draw <= t) {
                for(int i = 0; i < length; i++) chart.addData(c, i * deltaX, Qs[i]);
                chart.setLabel(c, "T = " + t);
                c++;
                t_draw += dt_draw;
            }
        }

        Qs[0] = Cc; //Após o tempo intermediário, c(0,t), t > t_int passa a ter valor Cc.
        Qss[0] = Cc; //Mesma coisa com nosso array auxiliar.

        for(t = t_int; t <= t_max; t += deltaT) { //Iterar em t_int < t <= t_max,
            for(int i = 1; i < length-1; i++) { //Iterar todos os volumes da malha (Exceto contornos!)
                Qss[i] = method(i);
            }
            double[] tmp = Qss;
            Qss = Qs;
            Qs = tmp;

            if(t_draw <= t) {
                for(int i = 0; i < length; i++) chart.addData(c, i * deltaX, Qs[i]);
                chart.setLabel(c, "T = " + t);
                c++;
                t_draw += dt_draw;
            }
        }

        for(int i = 0; i < length; i++) chart.addData(c, i * deltaX, Qs[i]);
        chart.setLabel(c, "T = " + t);

        for(int i = 1; i <= c ; i++){
            for(int j = 1; j <= c ; j++) chart.setVisible(j, false);
            chart.setVisible(i, true);
            try{ chart.savePNG("/home/gabriel/Development/export/"+getName()+" "+i+".png", 900, 600); }
            catch(IOException e) { e.printStackTrace(); }
        }
    }
}
