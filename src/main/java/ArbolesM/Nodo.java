package ArbolesM;

/* @author Jhafeth */
public class Nodo {

    public static int M = 3; //vias
    private final int Elemento[];
    private final Nodo Hijo[];
    private final boolean Estado[];

    public Nodo() {
        Elemento = new int[M - 1];
        Estado = new boolean[M - 1];
        Hijo = new Nodo[M];
        for (int i = 0; i < M - 1; i++) {
            Estado[i] = false;
            Hijo[i] = null;
        }
        Hijo[M - 1] = null;
    }

    public int getElem(int i) {
        return Elemento[i - 1];
    }

    public Nodo getHijo(int i) {
        return Hijo[i - 1];
    }

    public boolean Ocupado(int i) {
        return Estado[i - 1];
    }

    public boolean Vacio(int i) {
        return !Estado[i - 1];
    }

    public void setElem(int x, int i) {
        Elemento[i - 1] = x;
        Estado[i - 1] = true;
    }

    public void setHijo(Nodo P, int i) {
        Hijo[i - 1] = P;
    }

    public void setVacio(int i) {
        Estado[i - 1] = false;
    }

    public int CantVacias() {
        int c = 0;
        for (int i = 0; i < Estado.length; i++) {
            if (Estado[i] == false) {
                c++;
            }
        }
        return c;
    }

    public int CantOcupados() {
        return (M - 1) - CantVacias();
    }

    public boolean Lleno() {
        return (CantVacias() == 0);
    }
}
