package practica1;

public class ArbolN {

	//Para crear árboles binarios, ternarios...de N ramas cada nivel
	
    private int n;
    private String raiz = "-1";
    private ArbolN hijos[] = null;

    public ArbolN(int n) {
        this.n = n;
    }

    public ArbolN(int n, String raiz) {
        this.n = n;
        this.raiz = raiz;
    }

    public void inicializaHijos() {
        hijos = new ArbolN[n];
        for (int i = 0; i < this.n; i++) {
            this.hijos[i] = new ArbolN(n, "-1");
        }
    }

    public ArbolN[] getHijos() {
        return hijos;
    }

    public void setHijos(ArbolN[] hijos) {
        this.hijos = hijos;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }


}
