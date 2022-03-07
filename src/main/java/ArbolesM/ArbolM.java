package ArbolesM;

import javax.swing.JTextArea;

/* @author Jhafeth */
public class ArbolM {

    private Nodo raiz;

    public ArbolM() {
        raiz = null;
    }

    private boolean esHoja(Nodo P) {
        for (int i = 1; i <= Nodo.M; i++) {
            if (P.getHijo(i) != null) {
                return false;
            }
        }
        return true;
    }

    private void Recorrer(Nodo P, int i) {
        int num1 = P.getElem(i);
        int num2 = 0;
        int c = P.CantOcupados();
        while (i <= c) {
            if (P.Ocupado(i + 1)) {
                num2 = P.getElem(i + 1);
            }
            P.setElem(num1, i + 1);
            num1 = num2;
            i++;
        }
    }

    private int getHijoDesc(Nodo P, int x) {
        int i = 1;
        while (i < Nodo.M) {
            if (x < P.getElem(i)) {
                return i;
            }
            if (x == P.getElem(i)) {
                return -1;
            }
            i++;
        }
        return Nodo.M;
    }

    private void InsertaOrd(Nodo P, int x) {
        for (int i = 1; i < Nodo.M; i++) {
            if (P.Vacio(i)) {
                P.setElem(x, i);
                return;
            } else {
                if (x == P.getElem(i)) {
                    return;
                } else {
                    if (x < P.getElem(i)) {
                        Recorrer(P, i);
                        P.setElem(x, i);
                        return;
                    }
                }
            }
        }
    }

    public void Insertar(int x) {
        if (raiz == null) {//ARBOL VACIO
            raiz = new Nodo();
            raiz.setElem(x, 1);
        } else {
            Nodo P = raiz;
            Nodo AP = null;
            int i = 0;
            while (P != null) {
                AP = P;
                if (!P.Lleno()) {//EL NODO TIENE ESPACIO
                    InsertaOrd(P, x);
                    return;
                }
                i = getHijoDesc(P, x);
                if (i == -1) {
                    return; // x esta en el árbol
                }
                P = P.getHijo(i);
            }
            Nodo Q = new Nodo();
            Q.setElem(x, 1);
            if (AP != null) {
                AP.setHijo(Q, i);
            }
        }
    }

    private void InOrden(Nodo P, JTextArea jta) {
        if (P == null) {
            //no se muestra nada
        } else {
            if (esHoja(P)) {//no tiene hijos
                int i = 1; //muestra todos los elementos de nodo P
                while (i <= P.CantOcupados()) {
                    jta.append(String.valueOf(P.getElem(i) + " "));
                    i++;
                }
            } else {
                for (int i = 1; i <= Nodo.M - 1; i++) {
                    InOrden(P.getHijo(i), jta);//hijos izq
                    jta.append(String.valueOf(P.getElem(i) + " "));//elemento
                }
                InOrden(P.getHijo(Nodo.M), jta);//ultimo hijo
            }
        }
    }

    public void InOrden(JTextArea jta) {
        InOrden(raiz, jta);
    }

    private void PreOrden(Nodo P, JTextArea jta) {
        if (P == null) {
        } else {
            if (esHoja(P)) {
                int i = 1; //muestra todos los elementos de nodo P
                while (i <= P.CantOcupados()) {
                    jta.append(String.valueOf(P.getElem(i) + " "));
                    i++;
                }
            } else {
                for (int i = 1; i <= Nodo.M - 1; i++) {
                    jta.append(String.valueOf(P.getElem(i) + " "));//elemento i
                    PreOrden(P.getHijo(i), jta);//hijo i
                }
                PreOrden(P.getHijo(Nodo.M), jta);
            }
        }
    }

    public void PreOrden(JTextArea jta) {
        PreOrden(raiz, jta);
    }

    private void PostOrden(Nodo P, JTextArea jta) {//revisar
        if (P == null) {
        } else {
            if (esHoja(P)) {
                int i = 1; //muestra todos los elementos de nodo P
                while (i <= P.CantOcupados()) {
                    jta.append(String.valueOf(P.getElem(i) + " "));
                    i++;
                }
            } else {
                for (int i = 1; i <= Nodo.M; i++) {
                    PostOrden(P.getHijo(i), jta);//hijo i
                }
                for (int j = 1; j <= Nodo.M - 1; j++) {
                    jta.append(String.valueOf(P.getElem(j) + " "));
                }
            }
        }
    }

    public void PostOrden(JTextArea jta) {
        PostOrden(raiz, jta);
    }

    private int altura(Nodo p) {
        if (p != null) {
            if (esHoja(p)) {
                return 1;
            }
            //caso general
            int a = 0;
            int b;
            for (int i = 1; i <= Nodo.M; i++) {//sacar la altura mayor de todos los hijos
                b = altura(p.getHijo(i));
                if (b > a) {
                    a = b;
                }
            }
            return 1 + a;
        }
        return 0;
    }

    public int altura() {
        return altura(raiz);
    }

    private int cantidadDeNodos(Nodo p) {
        if (p != null) {
            if (esHoja(p)) {
                return 1;
            }
            int cant = 0;
            for (int i = 1; i <= p.M; i++) {
                cant = cant + cantidadDeNodos(p.getHijo(i));
            }
            return 1 + cant;
        }
        return 0;
    }

    public int cantidadDeNodos() {
        return cantidadDeNodos(raiz);
    }

    private int cantidadMayoresX(Nodo p, int x) {

        if (p != null) {
            if (esHoja(p)) {//el nodo es hoja
                int cant = 0;
                for (int i = 1; i <= p.CantOcupados(); i++) {
                    if (p.getElem(i) > x) {
                        cant++;
                    }
                }
                return cant;
            }//caso general
            int cant = 0;
            for (int i = 1; i <= Nodo.M; i++) {// contar de todos los hijos
                cant = cant + cantidadMayoresX(p.getHijo(i), x);
            }
            // contar en el nodo actual 
            for (int i = 1; i <= p.CantOcupados(); i++) {
                if (p.getElem(i) > x) {
                    cant++;
                }
            }
            return cant;
        }
        return 0;//nodo vacio
    }

    public int cantidadMayoresX(int x) {
        return cantidadMayoresX(raiz, x);
    }

    private void mostrarMayoresX(Nodo P, JTextArea jta, int x) {
        if (P == null) {
        } else {
            int ele;
            if (esHoja(P)) {
                int i = 1; //muestra todos los elementos de nodo P que son mayores a X
                while (i <= P.CantOcupados()) {
                    ele = P.getElem(i);
                    if (ele > x) {
                        jta.append(String.valueOf(ele + " "));
                    }
                    i++;
                }
            } else {
                for (int i = 1; i <= Nodo.M - 1; i++) {
                    ele = P.getElem(i);
                    if (ele > x) {
                        jta.append(String.valueOf(ele + " "));
                    }
                    mostrarMayoresX(P.getHijo(i), jta, x);//hijo i
                }
                mostrarMayoresX(P.getHijo(Nodo.M), jta, x);
            }
        }
    }

    public void mostrarMayorX(JTextArea jta, int x) {
        mostrarMayoresX(raiz, jta, x);
    }

//nivel del elemento X
    private int nivel(Nodo p, int x) {
        int n = 0;
        if (p != null) {
            for (int i = 1; i <= p.CantOcupados(); i++) {
                if (p.getElem(i) == x) {
                    return 1;
                }
            }
            int i = getHijoDesc(p, x);
            n = nivel(p.getHijo(i), x);
            return (n != 0) ? n + 1 : 0;
        }
        return n;
    }

    public int nivel(int x) {
        return nivel(raiz, x);
    }

    private void mostrarNivelX(Nodo P, JTextArea jta, int x) {
        int nivel;
        if (P != null) {
            nivel = nivel(P.getElem(1));
            if (nivel == x) {//nodo actual es del nivel x
                for (int i = 1; i <= P.CantOcupados(); i++) {
                    jta.append(String.valueOf(P.getElem(i) + " "));
                }
            } else {//caso general
                for (int i = 1; i <= Nodo.M; i++) {
                    mostrarNivelX(P.getHijo(i), jta, x);
                }
            }
        }
    }

    public void mostarNivelX(JTextArea jta, int x) {
        mostrarNivelX(raiz, jta, x);
    }
}
