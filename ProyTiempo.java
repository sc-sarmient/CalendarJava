package proytiempo;

import java.util.Scanner;

public class ProyTiempo {

// Funcion que se encarga de verificar si el año es bisiesto.

    public static int Bisiesto(int año) {
        if (año % 100 == 0) {
            if (año % 400 == 0) {
                return 1;
            } else {
                return 0;
            }

        } else {
            if (año % 4 == 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

//Funcion encargada de calcular el dsia (dia de la semana que inicia el año)

    public static int CalculaDSIA(int año) {
        // Fórmula para calcular el día de inicio de la semana del año
        int a = año - 1; // Año base (1 d.C.) es 1 año antes del año dado
        int b = a / 4 - a / 100 + a / 400; // Cálculo de ajuste

        int dsia = (a + b) % 7;

        // Ajusta el valor para que 0 sea domingo, 1 sea lunes, 2 sea martes, etc.
        dsia = (dsia + 1) % 7;

        return dsia;
    }

    public static void ImprimeMes(int mes, int año) {
        int dsia = CalculaDSIA(año);

        String M[] = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int ds[] = {0, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};
        int tdm[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int dia = 0;

        tdm[2] = 28 + Bisiesto(año);
        int dsim = (dsia + ds[mes]) % 7;
        if (mes >= 3) {
            dsim = dsim + Bisiesto(año);
        }

        if (dsim >= 7) {
            dsim = dsim - 7;
        }

        System.out.println("");
        System.out.println("\t" + "\t   " + M[mes]);
        System.out.println("==================================================");
        System.out.println("Do" + "\t" + "Lu" + "\t" + "Ma" + "\t" + "Mi" + "\t" + "Ju" + "\t" + "Vi" + "\t" + "Sa");

        for (int c = 1; c <= 42; c++) {

            if (c > dsim && dia < tdm[mes]) {
                dia = dia + 1;
                System.out.print(dia + "\t");
            } else {
                System.out.print("  " + "\t");
            }

            if (c % 7 == 0) {
                System.out.println("");
            }

        }

        System.out.println("==================================================");

    }

    public static void main(String[] args) {

        System.out.println("==============================================");
        Scanner leer = new Scanner(System.in);
        System.out.println("\t" + "  BIENVENIDO AL CALENDARIO");
        System.out.println("==============================================");
        System.out.print("Ingrese un año: ");
        int año = leer.nextInt();
        System.out.println("==============================================");
        System.out.println("\t" + "  CALENDARIO DEL AÑO " + año);
        System.out.println("==============================================");

        for (int mes = 1; mes <= 12; mes++) {
            ImprimeMes(mes, año); 
        }

    }
}
