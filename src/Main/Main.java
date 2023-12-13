package Main;

public class Main {
    static double length_x = 12; //Lx : entre 1 e 20 [metros]
    static double length_xf = length_x*0.2; //Uma fração de Lx
    static double u = 0.01; //Coeficiente de advecção : entre 0.1 a 2.0 [metros/segundo]
    static double a = 0.001; //Coeficiente de difusão : entre 0.001 a 0.1 [metros²/segundo] (Só é usado neste programa para avaliar Delta T).
    static double t_max = 60; //Tempo máximo : entre 60 e 600 [segundos]
    static double t_int = t_max*0.5; //Tempo intermediário : % t_max
    static double Ca = 5; //Temperatura inicial A
    static double Cb = 1; //Temperatura inicial B
    static double Cc = 10; //Temperatura inicial C
    static final int s_partition = 100;
    static final int draw = 5;

    public static void main(String[] args) {
        Iterator ftbs = new FTBS(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator vanleer = new VanLeer(length_x, length_xf, u, a*10, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator hquick = new HQUICK(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        Iterator superbee = new Superbee(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);

        Iterator bconservative = new BurgersConservative(length_x, length_xf, u, a, 2, 1, Ca, Cb, Cc, s_partition, draw);
        Iterator bnotconservative = new BurgersNonConservative(length_x, length_xf, u, a, 2, 1, Ca, Cb, Cc, s_partition, draw);

        ftbs.run();
        vanleer.run();
        hquick.run();
        superbee.run();
        bconservative.run();
        bnotconservative.run();
    }
}