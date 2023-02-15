import java.util.Scanner;
import java.util.Random;
public class TabElement {
    // Assignments == affectation
    private int comparisons_tri_rapide = 0;
    private int assignments_tri_rapide = 0;
    private int taille;

    private Element[] elements;

    public TabElement() {
        System.out.println("nombre d'elements est : ");
        Scanner entrer = new Scanner(System.in);
        taille = entrer.nextInt();
        elements = new Element[taille];

    }

    public TabElement(int taille) {
        this.taille = taille;
        elements = new Element[taille];
    }

    public TabElement(TabElement tab) {

        taille = tab.taille;
        comparisons_tri_rapide = tab.comparisons_tri_rapide;
        assignments_tri_rapide = tab.assignments_tri_rapide;
        elements = new Element[taille];
        for (int i = 0; i < taille; i++)
            elements[i] = tab.elements[i];
    }

    public int getTaille() {
        return taille;
    }

    public void litElement() {
        for (int i = 0; i < taille; i++) {
            elements[i] = new Element();
            System.out.print("la cle  : ");
            Scanner entrer = new Scanner(System.in);
            elements[i].setCle(entrer.nextInt());
            System.out.print("la valeur " + " : ");

            elements[i].setVal(entrer.nextDouble());
        }
    }

    public void litElementAleatoire() {
        for (int i = 0; i < taille; i++) {
            elements[i] = new Element();
            Random r = new Random();
            elements[i].setCle(r.nextInt(taille));
            elements[i].setVal(r.nextDouble());
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < taille; i++) {
            s += this.elements[i].toString() + "\n";
        }
        return s;
    }
    public long triABulle() {
        long comparaisons = 0; // O(1)
        long affectations = 0; // O(1)
        boolean tri = false; // O(1)
        int borne = taille; // O(1)
        while (tri == false) // O(n) elle s'execute au maximum n fois, ou n est la taille du tableau
        {
            tri = true; // O(1)
            for (int i = 0; i < borne - 1; i++) // O(n) elle s'execute au maximum n fois, ou n est la taille du tableau
            {
                comparaisons++; //O(n^2) cette ligne ne contribue pas directement à la complexité totale,
                // mais elle dépend du nombre total de comparaison effectuées

                if (elements[i].getCle() > elements[i + 1].getCle()) //O(1)
                {

                    int temp1 = elements[i].getCle(); // O(1)
                    double temp2 = elements[i].getVal(); // O(1)

                    elements[i].setCle(elements[i + 1].getCle()); // O(1)
                    elements[i].setVal(elements[i + 1].getVal()); // O(1)

                    elements[i + 1].setCle(temp1); //O(1)
                    elements[i + 1].setVal(temp2); //O(1)
                    affectations += 3; //O(1)
                    tri = false; //O(1)
                }
            }
            borne--; //O(1)
        }
        long total = comparaisons + affectations; //O(1)
        return total;
    }
    public void triRapid(int left, int right) {
        if (left < right) {
            int pivotIndex = partition(elements, left, right);
            triRapid(left, pivotIndex - 1);
            triRapid(pivotIndex + 1, right);
        }
    }
    private int partition(Element[] elements, int left, int right) {

        int pivotIndex = right;
        int pivotValue = elements[pivotIndex].getCle();
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (elements[i].getCle() < pivotValue) {
                echanger(elements, i, storeIndex);
                storeIndex++;
            }
            comparisons_tri_rapide++;
        }
        echanger(elements, storeIndex, pivotIndex);
        return storeIndex;
    }
    private void echanger(Element[] elements, int index1, int index2) {
        Element temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
        assignments_tri_rapide += 3;
    }
    public int getNombreIteration() {
        return comparisons_tri_rapide + assignments_tri_rapide;
    }
    public int triDenombrement() {
        int maxValue = Integer.MIN_VALUE; //O(1)
        int comparisonCount = 0; //O(1)
        int assignmentCount = 0; //O(1)
        for (Element e: elements) { //O(n) où n la taille du tableau
            maxValue = Math.max(maxValue, e.getCle()); //O(1)
            comparisonCount++; //O(1)
        }
        Element[] countArray = new Element[maxValue + 1]; //O(1)
        for (int i = 0; i <= maxValue; i++) { //O(m) où m la valeur maximal des clés dans le tableau `elements`
            countArray[i] = new Element(i, 0); //O(1)
            assignmentCount++; //O(1)
        }

        for (Element e: elements) { //O(n)
            countArray[e.getCle()].setVal(countArray[e.getCle()].getVal() + 1); //O(1)
            assignmentCount++; //O(1)
        }
        int j = 0; //O(1)
        for (int i = 0; i <= maxValue; i++) { //O(m*n)
            for (int k = 0; k < (int) countArray[i].getVal(); k++) {
                elements[j++] = new Element(i, countArray[i].getVal());
                assignmentCount++;
            }
        }

        return assignmentCount + comparisonCount; //O(1)

    }

}