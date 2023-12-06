package Main;

public class Main {
    static double length_x = 12; //Lx : entre 1 e 20 [metros]
    static double length_xf = length_x*0.2; //Uma fração de Lx
    static double u = 0.01; //Coeficiente de advecção : entre 0.1 a 2.0 [metros/segundo]
    static double a = 0.1; //Coeficiente de difusão : entre 0.001 a 0.1 [metros²/segundo]
    static double t_max = 2; // tempo máximo : entre 60 e 600 [segundos]
    static double t_int = t_max*0.5; // tempo intermediário : % t_max
    static double Ca = 5;// 5_1_2_ //Concentração inicial A
    static double Cb = 1;// 2_0_1_ //Concentração inicial B
    static double Cc = 10;//10_1_1_ //Concentração inicial C
    static final int s_partition = 100;
    static final int draw = 5;

    public static void main(String[] args) {
        Iterator trab = new Superbee(length_x, length_xf, u, a, t_max, t_int, Ca, Cb, Cc, s_partition, draw);
        trab.run();
    }
}