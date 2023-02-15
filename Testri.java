public class Testri {
    public static void main(String[] args) {
        final int TAILLEMAX = 2000000;
        for (int i = 1000; i <= TAILLEMAX; i += 1000) {
            TabElement tab = new TabElement(i);
            tab.litElementAleatoire();
            TabElement tab2 = new TabElement(tab);
            TabElement tab3 = new TabElement(tab);

            long begin2 = System.nanoTime();
            tab2.triRapid(0, i - 1);
            double temp2 = ((System.nanoTime() - begin2) * 1E-6);
            int nbr_iter2 = tab2.getNombreIteration();

            System.out.println("TriRapide[" + i + "]" + "Temps d'execution: " + String.format("%.3f", temp2) + " ms: Nombre d'operations totale:" + nbr_iter2);

            long begin3 = System.nanoTime();
            int nbr_iter3 = tab3.triDenombrement();
            double temp3 = ((System.nanoTime() - begin3) * 1E-6);

            System.out.println("TriDenombrement[" + i + "]" + "Temps d'execution: " + String.format("%.3f", temp3) + " ms: Nombre d'operations totale: " + nbr_iter3);

            long begin = System.nanoTime();
            long nbr_iter = tab.triABulle();
            double temp = ((System.nanoTime() - begin) * 1E-6);

            System.out.println("TriàBulle[" + i + "]" + "Temps d'execution: " + String.format("%.3f", temp) + " ms: Nombre d'operations totale: " + nbr_iter);
        }
    }
}