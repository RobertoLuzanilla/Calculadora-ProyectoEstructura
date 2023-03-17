public class Oper_Pilas {
    int cdp = -1, t = 10;
    char pila[] = new char[t];
    public boolean esVacia() {
        return cdp == -1;
    }
    public void apilar(int x) {
        if (cdp == t - 1) {
            duplicarAr();
        }
        pila[++cdp] = (char) x;
    }
    public void duplicarAr() {
        char cop[] = new char[t];
        cop = pila;
        t *= 2;
        pila = new char[t];
        for (int i = 0; i < t / 2; i++) {
            pila[i] = (char) cop[i];
        }
    }
    public void desapilar() {
        cdp--;
    }
    public char cima() {
        return pila[cdp];
    }
    public char cimayDesapilar() {
        return pila[cdp--];
    }
    public void vaciar() {
        cdp = -1;
    }
    public void checarparentesis(String Exp) {
        int prob = 0;
        for (int i = 0; i < Exp.length(); i++) {
            if (Exp.charAt(i) == '(') {
                apilar(Exp.charAt(i));
            } else if (Exp.charAt(i) == ')') {
                if (esVacia()) {
                    prob = 1;
                } else {
                    desapilar();
                }
            }
        }
        if (!esVacia()) {
            prob = 1;
        }
        if (prob == 0) {
            System.out.println("La expresion es valida. ");
            infijoposfijo(Exp);
        } else {
            System.out.println("La expresion es invalida. ");
            vaciar();
        }
    }
    public void infijoposfijo(String Exp) {
        String Posfijo = "";
        for (int i = 0; i < Exp.length(); i++) {

            if (Character.isDigit(Exp.charAt(i))) {
                Posfijo += Exp.charAt(i);

            } else if (verificarOper(Exp.charAt(i))) {

                if (esVacia() || Procedencia(Exp.charAt(i)) > Procedencia(cima())) {
                    apilar(Exp.charAt(i));

                } else {

                    while (!esVacia() && Procedencia(Exp.charAt(i)) <= Procedencia(cima())) {
                        Posfijo += cima();
                        desapilar();
                    }
                    apilar(Exp.charAt(i));
                }

            } else if (Exp.charAt(i) == '(') {
                apilar(Exp.charAt(i));

            } else if (Exp.charAt(i) == ')') {

                while (cima() != '(') {

                    Posfijo += cimayDesapilar();
                }

                desapilar();
            }
        }

        while (!esVacia()) {

            Posfijo += cimayDesapilar();

        }

        System.out.println(Posfijo);

        EvaluarPosfijo(Posfijo);
        vaciar();
    }
    boolean verificarOper(char c) {
        if (c == '+' || c == '-' || c == '/' || c == '*') {
            return true;
        }
        return false;
    }
    public int Procedencia(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '/' || c == '*') {
            return 2;
        }
        return 0;
    }
    public void EvaluarPosfijo(String Posfijo) {
        int pile[] = new int[t];
        int resultado=0, Uno, Dos;
        vaciar();
        for (int i = 0; i < Posfijo.length(); i++) {

            if (Character.isDigit(Posfijo.charAt(i))) {
                pile[++cdp] = Character.getNumericValue(Posfijo.charAt(i));
            }

            else if (Posfijo.charAt(i) == '+' || Posfijo.charAt(i) == '-' || Posfijo.charAt(i) == '/' ||
                    Posfijo.charAt(i) == '*') {

                if (Posfijo.charAt(i) == '+') {

                    if (cdp>=1) {
                        Dos=pile[cdp];
                        cdp--;
                        Uno=pile[cdp];
                        cdp--;
                        resultado=Uno+Dos;
                        pile[++cdp] = resultado;
                    } else{

                        System.out.println("\nERROR, no hay elementos");
                    }
                } else if(Posfijo.charAt(i) == '-') {

                    if (cdp>=1) {
                        Dos=pile[cdp];
                        cdp--;
                        Uno=pile[cdp];
                        cdp--;
                        resultado=Uno-Dos;
                        pile[++cdp] = resultado;
                    } else{

                        System.out.println("\nERROR, no hay elementos");
                    }

                } else if (Posfijo.charAt(i) == '*') {

                    if (cdp>=1) {
                        Dos=pile[cdp];
                        cdp--;
                        Uno=pile[cdp];
                        cdp--;
                        resultado=Uno*Dos;
                        pile[++cdp]=resultado;
                    } else{

                        System.out.println("\nERROR, no hay elementos");
                    }

                } else if (Posfijo.charAt(i) == '/') {

                    if (cdp>=1) {
                        Dos=pile[cdp];
                        cdp--;
                        Uno=pile[cdp];
                        cdp--;
                        if (Dos==0) {
                            System.out.println("ERROR, no hay elementos");
                        }
                        else {
                            resultado=Uno/Dos;
                            pile[++cdp] = resultado;
                        }
                    }
                    else {
                        System.out.println("\nERROR, no hay elementos");
                    }
                }
            }
        }
        System.out.println("El resultado es: " + resultado);
        vaciar();
    }
}