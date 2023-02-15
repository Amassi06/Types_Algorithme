public class Element {
    private int cle;
    private double val;
    public Element() {
        cle = 0;
        val = 0.0;
    }
    public Element(int c, double v) {
        cle = c;
        val = v;
    }
    public Element(Element e) {
        cle = e.cle;
        val = e.val;
    }

    public int getCle() {
        return cle;
    }
    public double getVal() {
        return val;
    }

    public void setCle(int c) {
        cle = c;
    }
    public void setVal(double v) {
        val = v;
    }

    public String toString() {
        return "cle : " + cle + "  -->  valeur : " + val;
    }
}