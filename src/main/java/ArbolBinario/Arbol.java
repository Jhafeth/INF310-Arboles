package ArbolBinario;

import javax.swing.JTextArea;

/* @author Jhafeth */
public class Arbol {

    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    private boolean esHoja(Nodo pr) {
        return pr.getHI() == null && pr.getHD() == null;
    }

    public void insertar(int x) {
        Nodo q = new Nodo(x);
        if (raiz == null) {
            raiz = q;
            return;
        }
        Nodo ap = null;
        Nodo p = raiz;
        while (p != null) {
            ap = p;
            if (x < p.getElem()) {
                p = p.getHI();
            } else if (x > p.getElem()) {
                p = p.getHD();
            } else {
                return;
            }
        }
        if (x < ap.getElem()) {
            ap.setHI(q);
        } else {
            ap.setHD(q);
        }
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

    private void inOrden(Nodo pr, JTextArea ta) {
        if (pr != null) {
            inOrden(pr.getHI(), ta);
            ta.append(String.valueOf(pr.getElem()) + "  ");
            inOrden(pr.getHD(), ta);
        }
    }

    public void inOrden(JTextArea ta) {
        inOrden(raiz, ta);
    }

    private void postOrden(Nodo pr, JTextArea ta) {
        if (pr != null) {
            postOrden(pr.getHI(), ta);
            postOrden(pr.getHD(), ta);
            ta.append(String.valueOf(pr.getElem()) + "  ");
        }
    }

    public void postOrden(JTextArea ta) {
        postOrden(raiz, ta);
    }

    private int cantidad(Nodo pr) {
        return (pr == null) ? 0 : (esHoja(pr)) ? 1 : cantidad(pr.getHI()) + cantidad(pr.getHD()) + 1;
    }

    public int cantidad() {
        return cantidad(raiz);
    }

    private int sumar(Nodo pr) {
        return (pr == null) ? 0 : (esHoja(pr)) ? pr.getElem() : sumar(pr.getHI()) + sumar(pr.getHD()) + pr.getElem();
    }

    public int sumar() {
        return sumar(raiz);
    }

    //sumar pares 
    private int sumarPares(Nodo pr) {
        return (pr == null) ? 0
                : (esHoja(pr)) ? ((pr.getElem() % 2 == 0) ? pr.getElem() : 0)
                : ((pr.getElem() % 2 == 0) ? (pr.getElem() + sumarPares(pr.getHI()) + sumarPares(pr.getHD()))
                : (sumarPares(pr.getHI()) + sumarPares(pr.getHD())));
    }

    public int sumarPares() {
        return sumarPares(raiz);
    }

    //mostrar pares
    private void mostrarPares(Nodo pr, JTextArea ta) {
        if (pr != null) {
            int p = pr.getElem();
            mostrarPares(pr.getHI(), ta);
            mostrarPares(pr.getHD(), ta);
            if (p % 2 == 0) {
                ta.append(String.valueOf(p + " "));
            }
        }
    }

    public void mostrarPares(JTextArea ta) {
        mostrarPares(raiz, ta);
    }

    //cantidad pares
    private int cantidadPares(Nodo pr) {
        return (pr == null) ? 0
                : (esHoja(pr)) ? ((pr.getElem() % 2 == 0) ? 1 : 0)
                : (pr.getElem() % 2 == 0) ? cantidadPares(pr.getHI()) + cantidadPares(pr.getHD()) + 1 : cantidadPares(pr.getHI()) + cantidadPares(pr.getHD());
    }

    public int cantidadPares() {
        return cantidadPares(raiz);
    }

    //mostrar padres
    private void mostrarPadres(Nodo pr, JTextArea ta) {
        if (pr != null) {
            if (!esHoja(pr)) {
                ta.append(String.valueOf(pr.getElem()) + "  ");
            }
            mostrarPadres(pr.getHI(), ta);
            mostrarPadres(pr.getHD(), ta);
        }
    }

    public void mostrarPadres(JTextArea ta) {
        mostrarPadres(raiz, ta);
    }

    //altura
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

    //existe
    private boolean existe(Nodo pr, int x) {
        return (pr == null) ? false
                : (esHoja(pr)) ? pr.getElem() == x
                : pr.getElem() == x || existe(pr.getHI(), x) || existe(pr.getHD(), x);
    }

    public boolean existe(int x) {
        return existe(raiz, x);
    }

    // mayor a x
    private void mostrarMayores(Nodo pr, int x, JTextArea ta) {
        if (pr != null) {
            if (pr.getElem() > x) {
                ta.append(String.valueOf(pr.getElem()) + "  ");
            }
            mostrarMayores(pr.getHI(), x, ta);
            mostrarMayores(pr.getHD(), x, ta);
        }
    }

    public void mostrarMayores(JTextArea ta, int x) {
        mostrarMayores(raiz, x, ta);
    }

    //devolver abuelo
    private boolean esHijo(Nodo pr, int hijo) {
        if (pr == null) {
            return false;
        }
        if (!esHoja(pr)) {
            if (pr.getHI() != null && pr.getHI().getElem() == hijo) {
                return true;
            }
            if (pr.getHD() != null && pr.getHD().getElem() == hijo) {
                return true;
            }
        }
        return false;
    }

    private int devolverAbuelo(Nodo pr, int x) {
        if (pr == null) {
            return 0;
        }
        if (altura(pr) >= 3) {
            if (pr.getHI() != null && esHijo(pr.getHI(), x)) {
                return pr.getElem();
            }
            if (pr.getHD() != null && esHijo(pr.getHD(), x)) {
                return pr.getElem();
            }
        }
        int ai = devolverAbuelo(pr.getHI(), x);
        int ad = devolverAbuelo(pr.getHD(), x);
        if (ai != 0) {
            return ai;
        } else if (ad != 0) {
            return ad;
        } else {
            return 0;
        }
    }

    public int devolverAbuelo(int x) {
        return devolverAbuelo(raiz, x);
    }

    //devolver padre
    private int devolverPadre(Nodo pr, int hijo) {
        if (pr == null) {
            return 0;
        }
        if (esHoja(pr)) {
            return 0;
        }
        if (esHijo(pr, hijo)) {
            return pr.getElem();
        }
        if (hijo > pr.getElem()) {
            return devolverPadre(pr.getHD(), hijo);
        } else {
            return devolverPadre(pr.getHI(), hijo);
        }
    }

    public int devolverPadre(int hijo) {
        return devolverPadre(raiz, hijo);
    }

    //mostrar nietos
    private void mostrarNietos(Nodo pr, int abuelo, JTextArea ta) {
        //caso base arbol vacio
        if (pr == null) {
            return;
        } else {//caso general
            if (pr.getElem() == abuelo) {//pillamos al abuelo
                if (pr.getHI() != null) {//tiene hijo izquierdo
                    Nodo hijoIzquierdo = pr.getHI();
                    if (hijoIzquierdo.getHI() != null) {
                        ta.append(hijoIzquierdo.getHI().getElem() + " ");
                    }
                    if (hijoIzquierdo.getHD() != null) {
                        ta.append(hijoIzquierdo.getHD().getElem() + " ");
                    }
                }
                if (pr.getHD() != null) {//tiene hijo derecho
                    Nodo hijoDerecho = pr.getHD();
                    if (hijoDerecho.getHI() != null) {
                        ta.append(hijoDerecho.getHI().getElem() + " ");
                    }
                    if (hijoDerecho.getHD() != null) {
                        ta.append(hijoDerecho.getHD().getElem() + " ");
                    }
                }
            }
            if (abuelo > pr.getElem()) {
                mostrarNietos(pr.getHD(), abuelo, ta);
            } else {
                mostrarNietos(pr.getHI(), abuelo, ta);
            }
        }
    }

    public void mostrarNietos(int abuelo, JTextArea ta) {
        mostrarNietos(this.raiz, abuelo, ta);
    }

    // arboles iguales
    private boolean iguales(Nodo pa, Nodo pb) {
        //caso base ambos arboles sin elementos        
        if (pa == null && pb == null) {
            return true;
        }
        //caso si uno de los nodos esta vacio retorna falso
        if (!(pa != null && pb != null)) {
            return false;
        }
        //caso ambos son hojas
        if (esHoja(pa) && esHoja(pb)) {
            return pa.getElem() == pb.getElem();
        }
        //caso general
        return (pa.getElem() == pb.getElem())
                && iguales(pa.getHI(), pb.getHI())
                && iguales(pa.getHD(), pb.getHD());
    }

    public boolean iguales(Arbol B) {
        return iguales(this.raiz, B.getRaiz());
    }

    //eliminar nodo
    public void eliminar(int x) {
        Nodo p = raiz;
        Nodo ap = null;
        while (p != null && p.getElem() != x) {
            ap = p;
            if (x > p.getElem()) {
                p = p.getHD();
            } else {
                p = p.getHI();
            }
        }
        if (p == null) {
            return;
        }
        if (esHoja(p)) {
            elimCaso1(ap, p);
        } else if ((p.getHD() != null && p.getHI() == null) || (p.getHD() == null && p.getHI() != null)) {
            elimCaso2(ap, p);
        } else {
            elimCaso3(p);
        }
    }

    private void elimCaso1(Nodo ap, Nodo p) {
        if (ap == null) {
            raiz = null;
        } else {
            if (p == ap.getHD()) {
                ap.setHD(null);
            } else {
                ap.setHI(null);
            }
        }
    }

    private void elimCaso2(Nodo ap, Nodo p) {
        if (ap == null) {
            if (p.getHD() != null) {
                raiz = p.getHD();
            } else {
                raiz = p.getHI();
            }
        } else {
            if (p == ap.getHD()) {
                if (p.getHD() != null) {
                    ap.setHD(p.getHD());
                } else {
                    ap.setHD(p.getHI());
                }
            } else if (p.getHD() != null) {
                ap.setHD(p.getHD());
            } else {
                ap.setHI(p.getHI());
            }
        }
    }

    private void elimCaso3(Nodo p) {
        Nodo s = p.getHD();
        Nodo as = null;
        while (s.getHI() != null) {
            as = s;
            s = s.getHI();
        }
        int aux = p.getElem();
        p.setElem(s.getElem());
        s.setElem(aux);
        if (as == null) {
            p.setHD(null);
        } else {
            as.setHI(null);
        }
    }

    private int factorEquilibrio(Nodo pr) {
        return (altura(pr.getHI()) - altura(pr.getHD()));
    }

    public int factorEquilibrio(int x) {
        Nodo p = this.raiz;
        while (p != null && p.getElem() != x) {//buscar el nodo
            if (x > p.getElem()) {
                p = p.getHD();
            } else {
                p = p.getHI();
            }
        }
        return factorEquilibrio(p);
    }

    //verificar si un arbol es subarbol de otro
    //pp es arbol principal y ps es subarbol
    private boolean subArbol(Nodo pp, Nodo ps) {
        if (pp == null) {
            return false;
        }

        if (pp.getElem() == ps.getElem()) {
            return iguales(pp, ps);
        }

        return (ps.getElem() > pp.getElem()) ? subArbol(pp.getHD(), ps) : subArbol(pp.getHI(), ps);

    }

    public boolean subArbol(Arbol sub) {
        return subArbol(this.raiz, sub.getRaiz());
    }

    //mostrar la cantidad de padres con un solo hijo
    private int padresUnicos(Nodo pr) {
        if (pr == null) {
            return 0;
        }
        if (esHoja(pr)) {
            return 0;
        }
        int cantidad = 0;
        if (pr.getHD() == null && pr.getHI() != null) {
            //tiene solo hijo izquierdo
            cantidad = 1;
        } else if (pr.getHI() == null && pr.getHD() != null) {
            //tiene solo hijo derecho
            cantidad = 1;
        }
        return cantidad + padresUnicos(pr.getHI()) + padresUnicos(pr.getHD());
    }

    public int padresUnicos() {
        return padresUnicos(raiz);
    }

    // mostrar hermano
    public int mostrarHermano(int x) {
        return mostrarHermano(null, this.raiz, x);
    }

    private int mostrarHermano(Nodo padre, Nodo hijo, int hermano) {
        //arbol vacio        
        if (hijo == null) {
            return 0;
        }
        //llegamos al hijo que buscamos su hermano
        if (hijo.getElem() == hermano) {//llegamos al hijo que buscamos su hermano
            if (padre == null) {
                return 0;
            } else {
                if (hijo.getElem() > padre.getElem()) {
                    return (padre.getHI() != null) ? padre.getHI().getElem() : 0;
                } else {
                    return (padre.getHD() != null) ? padre.getHD().getElem() : 0;
                }
            }

        }
        //llamada recursiva
        if (hermano > hijo.getElem()) {// esta a la derecha
            return mostrarHermano(hijo, hijo.getHD(), hermano);
        } else {// esta a la izquierda
            return mostrarHermano(hijo, hijo.getHI(), hermano);
        }
    }

    // mostrar tio
    private int mostrarTio(Nodo padre, Nodo hijo, int sobrino) {
        if (hijo == null) {
            return 0;
        }
        if (sobrino == hijo.getElem()) {
            return mostrarHermano(padre.getElem());
        }
        if (sobrino > hijo.getElem()) {
            // a la derecha
            return mostrarTio(hijo, hijo.getHD(), sobrino);
        } else {
            // a la izquierda
            return mostrarTio(hijo, hijo.getHI(), sobrino);
        }
    }

    public int mostrarTio(int x) {
        return mostrarTio(null, raiz, x);
    }

    // devolver el nivel de un nodo
    private int nivel(Nodo pr, int x) {
        if (pr == null) {
            return 0;
        }
        int n = 0;
        while (pr != null && pr.getElem() != x) {
            n++;
            if (x > pr.getElem()) {
                pr = pr.getHD();
            } else if (x < pr.getElem()) {
                pr = pr.getHI();
            }
        }
        if (pr.getElem() == x) {
            n++;
        } else {
            n = 0;
        }
        return n;
    }

    public int nivel(int n) {
        return nivel(this.raiz, n);
    }
}
