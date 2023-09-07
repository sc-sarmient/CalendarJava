package proytiempo;

import java.util.Scanner;

public class ProyTiempo {

    //Funcion para ver si es bisiesto
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

    // Función para verificar si un año es bisiesto, en booleano.
    public static boolean esAñoBisiesto(int año) {
        if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
            return true; // Es bisiesto
        } else {
            return false; // No es bisiesto
        }
    }

    //Funcion para Calcular el dia de inicio de la semanadel año
    public static int CalculaDSIA(int año) {
        // Fórmula para calcular el día de inicio de la semana del año
        int a = año - 1; // Año base (1 d.C.) es 1 año antes del año dado
        int b = a / 4 - a / 100 + a / 400; // Cálculo de ajuste

        /*
        a / 4 calcula la cantidad de años bisiestos en el rango desde el año base hasta el año dado año.
        a / 100 calcula la cantidad de años que son múltiplos de 100 en ese rango. Como no todos los años múltiplos de 100 son años bisiestos. 
        Para ajustar, se resta la cantidad de años múltiplos de 100 en el rango.
        a / 400 nuevamente ajusta el cálculo teniendo en cuenta que algunos años múltiplos de 400 sí son bisiestos. 
        Entonces, se suma la cantidad de años múltiplos de 400 en el rango.
         */
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
        System.out.println("\t" + "\t   " + M[mes] + " de " + año);
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

    //Funcion OPCION 3 - Calcula que dia cae una fecha. Hacemos uso de Zellers formula
    public static String CalcularDiaSemana(int dia, int mes, int año) {
        String[] diasSemana = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

        int a = (14 - mes) / 12;
        int y = año - a;
        int m = mes + 12 * a - 2;
        int d = (dia + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7;

        return diasSemana[d];
    }

    public static int NumDiasDelAño(int dia, int mes) {
        int totalDias = 0;
        int tdm[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 1; i <= (mes - 1); i++) {
            totalDias = totalDias + tdm[i];
        }

        totalDias = totalDias + dia;

        return totalDias;

    } //Fin del metodo NumDiasDelAño

    //Funcion opcion 4 - Calcula la diferencia en años, meses y dias de una fecha a otra.
    public static void CalcularDiferenciaFechas(int dia1, int mes1, int año1, int dia2, int mes2, int año2) {
        // Calcula la diferencia de años, meses y días
        int diferenciaAños = año2 - año1;
        int diferenciaMeses = mes2 - mes1;
        int diferenciaDias = dia2 - dia1;

        // Ajusta la diferencia de meses y años si los días son negativos
        if (diferenciaDias < 0) {
            diferenciaMeses = diferenciaMeses - 1;
            int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int maxDiasEnMes1 = diasPorMes[mes1];

            // Considera los años bisiestos
            if (esAñoBisiesto(año1) && mes1 == 2) {
                maxDiasEnMes1 = 29;
            }

            diferenciaDias = diferenciaDias + maxDiasEnMes1 - 2;
        }

        // Ajusta la diferencia de meses si es negativa
        if (diferenciaMeses < 0) {
            diferenciaAños = diferenciaAños - 1;
            diferenciaMeses = diferenciaMeses + 12;
        }

        System.out.println("Diferencia: " + Math.abs(diferenciaAños) + " años, " + diferenciaMeses + " meses y " + diferenciaDias + " días.");
    }

    // Funcion opcion 5 - Calcula cuantos dias han transcurrido de una fecha a otra
    public static int CalcularDiferenciaDias(int dia1, int mes1, int año1, int dia2, int mes2, int año2) {
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Verifica si es el mismo año
        if (año1 == año2) {
            // Verifica si es el mismo mes
            if (mes1 == mes2) {
                return dia2 - dia1;
            } else {
                int diasTranscurridos = diasPorMes[mes1] - dia1;
                for (int i = mes1 + 1; i < mes2; i++) {
                    diasTranscurridos = diasTranscurridos + diasPorMes[i];
                }
                diasTranscurridos = diasTranscurridos + dia2;
                return diasTranscurridos;
            }
        } else {
            int diasTranscurridos = diasPorMes[mes1] - dia1;
            for (int i = mes1 + 1; i <= 12; i++) {
                diasTranscurridos = diasTranscurridos + diasPorMes[i];
            }

            for (int i = año1 + 1; i < año2; i++) {
                if (esAñoBisiesto(i)) {
                    diasTranscurridos = diasTranscurridos + 366;
                } else {
                    diasTranscurridos = diasTranscurridos + 365;
                }
            }

            for (int i = 1; i < mes2; i++) {
                diasTranscurridos = diasTranscurridos + diasPorMes[i];
            }
            diasTranscurridos = diasTranscurridos + dia2;
            return diasTranscurridos;
        }
    }

    //Funcion del menu 
    public static void mostrarMenu(Scanner leer) {

        System.out.println("\t\t  " + "  Menú");
        System.out.println("==============================================");
        System.out.println("1. Calendario del mes.");
        System.out.println("2. Calendario del año.");
        System.out.println("3. Día de la semana de una fecha.");
        System.out.println("4. Número de Años, meses y días entre dos fechas.");
        System.out.println("5. Número de días entre dos fechas.");
        System.out.println("6. Salir.");
        System.out.println("==============================================");
        System.out.print("Elija una opción (1-6): ");
        int opcion = leer.nextInt();

        if (opcion == 1) {
            System.out.print("Ingrese el mes (1-12): ");
            int mes = leer.nextInt();
            System.out.print("Ingrese el año: ");
            int año = leer.nextInt();
            ImprimeMes(mes, año);
        } else if (opcion == 2) {
            System.out.print("Ingrese el año: ");
            int añoCalendario = leer.nextInt();
            System.out.println("\t\tCALENDARIO DEL AÑO " + añoCalendario);
            for (int i = 1; i <= 12; i++) {
                ImprimeMes(i, añoCalendario);
            }
        } else if (opcion == 3) {
            System.out.print("Ingrese el día: ");
            int diaFecha = leer.nextInt();
            System.out.print("Ingrese el mes: ");
            int mesFecha = leer.nextInt();
            System.out.print("Ingrese el año: ");
            int añoFecha = leer.nextInt();
            String diaSemana = CalcularDiaSemana(diaFecha, mesFecha, añoFecha);
            System.out.println("==============================================");
            System.out.println("El día de la semana de la fecha es: " + diaSemana);
        } else if (opcion == 4) {
            System.out.print("Ingrese la primera fecha (dia mes año): ");
            int dia1 = leer.nextInt();
            int mes1 = leer.nextInt();
            int año1 = leer.nextInt();

            System.out.print("Ingrese la segunda fecha (dia mes año): ");
            int dia2 = leer.nextInt();
            int mes2 = leer.nextInt();
            int año2 = leer.nextInt();
            System.out.println("==============================================");
            CalcularDiferenciaFechas(dia1, mes1, año1, dia2, mes2, año2);
        } else if (opcion == 5) {
            System.out.print("Ingrese la primera fecha (dia mes año): ");
            int dia1 = leer.nextInt();
            int mes1 = leer.nextInt();
            int año1 = leer.nextInt();

            System.out.print("Ingrese la segunda fecha (dia mes año): ");
            int dia2 = leer.nextInt();
            int mes2 = leer.nextInt();
            int año2 = leer.nextInt();

            int diasEntreFechas = CalcularDiferenciaDias(dia1, mes1, año1, dia2, mes2, año2);
            System.out.println("==============================================");
            System.out.println("Número de días entre las dos fechas: " + Math.abs(diasEntreFechas));

        } else if (opcion == 6) {
            System.out.println("Saliendo del programa.");
            return; // Salir de la función y, por lo tanto, del programa
        } else {
            System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        Scanner OpcionMenu = new Scanner(System.in);
        System.out.println("\t" + "Hecho por: Jose David Nuñez");
        System.out.println("==============================================");
        System.out.println("\t" + "  BIENVENIDO AL CALENDARIO");
        System.out.println("==============================================");
        mostrarMenu(OpcionMenu);

    }
}
