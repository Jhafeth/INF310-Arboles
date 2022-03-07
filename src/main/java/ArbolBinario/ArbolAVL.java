package ArbolBinario;

import javax.swing.JTextArea;

/* @author Jhafeth */
public class ArbolAVL {

    private Nodo raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    private void preOrden(Nodo pr, JTextArea ta) {
        if (pr != null) {
            ta.append(String.valueOf(pr.getElem()) + "  ");
            preOrden(pr.getHI(), ta);
            preOrden(pr.getHD(), ta);
        }
    }

    public void preOrden(JTextArea ta) {
        preOrden(raiz, ta);
    }

    private boolean esHoja(Nodo pr) {
        return pr.getHI() == null && pr.getHD() == null;
    }

    private int factorEquilibrio(Nodo pr) {
        return (pr == null) ? 0 : (altura(pr.getHI()) - altura(pr.getHD()));
    }

    private Nodo rotarIzq(Nodo ap) {
        Nodo q = ap.getHD();
        ap.setHD(q.getHI());
        q.setHI(ap);
        return q;
    }

    private Nodo rotarDer(Nodo ap) {
        Nodo q = ap.getHI();
        ap.setHI(q.getHD());
        q.setHD(ap);
        return q;
    }

    private Nodo rotarIzqDer(Nodo ap) {
        ap.setHI(rotarIzq(ap.getHI()));
        return rotarDer(ap);
    }

    private Nodo rotarDerIzq(Nodo ap) {
        ap.setHD(rotarDer(ap.getHD()));
        return rotarIzq(ap);
    }

    private int mayor(int a, int b) {
        return (a > b) ? a : b;
    }

    private int altura(Nodo pr) {
        return (pr == null) ? 0
                : 1 + mayor(altura(pr.getHI()), altura(pr.getHD()));
    }

    public int altura() {
        return altura(raiz);
    }

    private Nodo balancear(Nodo ap, Nodo p) {
        int fe = factorEquilibrio(ap);
        if (Math.abs(fe) > 1) {//balancear
            int fep = factorEquilibrio(p);
            if (fe == 2) {
                if (fep == 1) {
                    //eq caso girar der ap=2 p=1
                    ap = rotarDer(ap);
                } else if (fep == -1) {
                    //eq caso girar izq-der ap=2 p=-1  
                    ap = rotarIzqDer(ap);
                }
            } else if (fe == -2) {
                if (fep == -1) {
                    //eq caso girar izq ap=-2 p=-1
                    ap = rotarIzq(ap);
                } else if (fep == 1) {
                    //eq caso girar der-izq ap=-2 p=1
                    ap = rotarDerIzq(ap);
                }
            }
        }
        return ap;
    }

    private Nodo insertarAVL(Nodo pr, int x) {
        if (pr == null) {
            Nodo q = new Nodo(x);
            return q;
        }
        if (x > pr.getElem()) {
            pr.setHD(insertarAVL(pr.getHD(), x));
            pr = balancear(pr, pr.getHD());
        } else if (x < pr.getElem()) {
            pr.setHI(insertarAVL(pr.getHI(), x));
            pr = balancear(pr, pr.getHI());
        } else {
        }
        return pr;
    }

    public void insertarAVL(int x) {
        this.raiz = insertarAVL(this.raiz, x);
    }
}
