package Main;

import java.awt.*;
import java.io.IOException;

public class Main {
    static double length_x = 12; //Lx : entre 1 e 20 [metros]
    static double length_xf = length_x*0.2; //Uma fração de Lx
    static double u = 0.01; //Coeficiente de advecção : entre 0.1 a 2.0 [metros/segundo]
    static double a = 0.001; //Coeficiente de difusão : entre 0.001 a 0.1 [metros²/segundo] (Só é usado neste programa para avaliar Delta T).
    static double t_max = 60; //Tempo máximo : entre 60 e 600 [segundos]
    static double t_int = t_max*0.5; //Tempo intermediário : % t_max
    static double t_max_burgers = 2;
    static double t_int_burgers = t_max_burgers*0.5;
    static double Ca = 5; //Concentração inicial A
    static double Cb = 1; //Concentração inicial B
    static double Cc = 10; //Concentração inicial C
    static final int s_partition = 100;
    static final int draw = 0;

    public static void main(String[] args) {
        Iterator ftbs = new FTBS(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator vanleer = new VanLeer(length_x, length_xf, u, a*10, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator hquick = new HQUICK(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator superbee = new Superbee(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);

        Iterator bconservative = new BurgersConservative(length_x, length_xf, u, a, t_max_burgers, t_int_burgers, Ca, Cb, Cc, s_partition, draw);
        Iterator bnotconservative = new BurgersNonConservative(length_x, length_xf, u, a, t_max_burgers, t_int_burgers, Ca, Cb, Cc, s_partition, draw);

        ftbs.run();
        vanleer.run();
        hquick.run();
        superbee.run();
        bconservative.run();
        bnotconservative.run();

        GChart trab3 = new GChart("Comparativo dos Limitadores de Fluxo", 4);
        trab3.setLabel(0, "FTBS");
        trab3.setLabel(1, "Van Leer");
        trab3.setLabel(2, "HQUICK");
        trab3.setLabel(3, "Superbee");
        trab3.setColor(3, Color.BLACK);
        for(int i = 0; i < ftbs.getQs().length; i++) {
            double x = i * (length_x / s_partition);
            trab3.addData(0, x, ftbs.getQs()[i]);
            trab3.addData(1, x, vanleer.getQs()[i]);
            trab3.addData(2, x, hquick.getQs()[i]);
            trab3.addData(3, x, superbee.getQs()[i]);
        }

        try { trab3.savePNG("/home/gabriel/Development/export/saida fluxo.png", 900, 600); }
        catch (IOException e) {throw new RuntimeException(e);}

        GChart trab4 = new GChart("Comparativo Burgers", 2);
        trab4.setLabel(0, "Conservativo");
        trab4.setLabel(1, "Não-Conservativo");
        for(int i = 0; i < bconservative.getQs().length; i++) {
            double x = i * (length_x / s_partition);
            trab4.addData(0, x, bconservative.getQs()[i]);
            trab4.addData(1, x, bnotconservative.getQs()[i]);
        }

        try { trab4.savePNG("/home/gabriel/Development/export/saida burgers.png", 900, 600); }
        catch (IOException e) {throw new RuntimeException(e);}
    }
}