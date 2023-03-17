import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String Exp;
        Oper_Pilas oP = new Oper_Pilas();

        do {
            System.out.println("Introduce lo que quieras hacer");
            System.out.println("1.Calcular una expresion matematica\n 2.Salir");
            opcion=sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Introduzca la expresion matematica: ");
                    Exp = sc.nextLine();
                    oP.checarparentesis(Exp);

                    break;
            }
        } while (opcion != 2);
    }

}