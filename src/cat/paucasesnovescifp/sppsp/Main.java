package cat.paucasesnovescifp.sppsp;

import cat.paucasesnovescifp.sppsp.models.Programa;

public class Main {
    public static void main(String[] args) {
        Programa programa1 = new Programa(5000,5001,true);
        Programa programa2 = new Programa(5001,5002,false);
        Programa programa3 = new Programa(5002,5003,false);
        Programa programa4 = new Programa(5003,5004,false);
        Programa programa5 = new Programa(5004,5000,false);

        programa1.start();
        programa2.start();
        programa3.start();
        programa4.start();
        programa5.start();
    }
}

