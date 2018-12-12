package laboratorio1;

import java.util.Comparator;

public class Par implements Comparable<Par>{


    String web;
    Double pageRank;

    public Par(String pWeb, Double pPage) {
        this.web = pWeb;
        this.pageRank = pPage;
    }

    @Override
    public int compareTo(Par o) {
        return Comparators.PR.compare(this,o);
    }

    public static class Comparators {

        public static Comparator<Par> PR = new Comparator<Par>() {
            @Override
            public int compare(Par o1, Par o2) {
                return o1.pageRank.compareTo(o2.pageRank);
            }
        };
    }
}
