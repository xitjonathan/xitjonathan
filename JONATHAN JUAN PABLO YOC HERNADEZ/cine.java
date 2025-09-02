import java.util.Scanner;
public class cine { 
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String [] peliculas = {"Conjuro II", "Anabelle", "Corredores", "Crepusculo", "Rápidos y Furiosos", "Piratas", "Peppa Pig", "Dinosaurios", "Perros", "La Monja"};
        //personas que votarán
        System.out.println("¿Cuantas personas votarán?");
        int NumPersonas = input.nextInt();
        input.nextLine();

        String [] personas = new String[NumPersonas];

        for(int i = 0; i < personas.length; i++){
            System.out.println("Ingrese el nombre de la persona " + (i + 1) + " :");
            personas[i] = input.next();
        }

        int [][] votos = new int[NumPersonas][peliculas.length];
        RegistrarVotos(votos, peliculas, personas);
        MostrarReporte(votos, peliculas, personas);

        //Funciones

    }
    static void RegistrarVotos(int[][] votos, String[] peliculas, String[] personas){
        for(int i = 0; i < votos.length; i++){
            System.out.println("\nVotando: " + personas[i]);
            for(int j = 0; j < peliculas.length; j++){
                int voto;
                do {
                    System.out.println("Ingrese el voto para \"" + peliculas[j] + "\" (1 - 5)");
                    voto = input.nextInt();

                } while (voto < 1 || voto > 5);
                votos[i][j] = voto;

            }//j
        }//i
    }

    //PROMEDIO
    public static double[] CalcularPromedios(int[][] votos){
        double[] Promedios = new double[votos[0].length];
        for(int j = 0; j < votos[0].length; j++){
            int suma = 0;
            for(int i = 0; i < votos.length; i++){
                suma += votos[i][j];

            }
            Promedios[j] = (double) suma / votos.length;
        }
        return Promedios;
    }


    //mayor votadas
    public static int MasVotada(double[] Promedios){
        int Pos = 0;
        for(int i = 1; i < Promedios.length; i++){
            if(Promedios[i] > Promedios[Pos]){
                Pos = i;
            }

        }
        return Pos;
    }

    //Menos Votada
    public static int MenosVotada(double[] Promedios){
        int Pos = 0;
        for(int i = 1; i < Promedios.length; i++){
            if(Promedios[i] < Promedios[Pos]){
                Pos = i;
            }

        }
        return Pos;
    }

    //Grafico Asteriscos
    public static void MostrarGrafico(int[][] votos, String[] peliculas){
        int[] totales = new int[peliculas.length];
        for(int j = 0; j < peliculas.length; j++){
            for(int i = 0; i < votos.length; i++){
                totales[j] += votos[i][j];
            }
        }

        System.out.println("\n---Graficoo de votos---");
        for(int j = 0; j < peliculas.length; j++){
            System.out.println(peliculas[j] + " :");
            for(int k = 0; k < totales[j]; k++){
                System.out.print("*");

            }
            System.out.println(" (" + totales[j] + ") ");
        }
    }

    static void MostrarReporte(int[][] votos, String[] peliculas, String[] personas){
       double[] Promedios = CalcularPromedios(votos);

       System.out.println("\n---Reporte de cada persona---");
       for(int i = 0; i < personas.length; i++){
            System.out.print(personas[i] + " votó: ");
            for(int j = 0; j < peliculas.length; j++){
                System.out.println(peliculas[j] + " = " + votos[i][j] + " ");
            }
            System.out.println();
       }

       System.out.println("\n---Promedio de cada pelicula---");
       for(int j = 0; j < personas.length; j++){
            System.out.println(peliculas[j] + " Promedio: " + Promedios[j]);
       }

       int Mejor = MasVotada(Promedios);
       int Peor = MenosVotada(Promedios);



       System.out.println("Pelicula mas votada: " + peliculas[Mejor]);
       System.err.println("Pelicula menos votada: " +  peliculas[Peor]);

       MostrarGrafico(votos, peliculas);
    }
}
