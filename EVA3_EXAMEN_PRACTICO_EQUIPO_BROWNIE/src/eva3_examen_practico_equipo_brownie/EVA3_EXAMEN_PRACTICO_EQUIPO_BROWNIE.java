/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_examen_practico_equipo_brownie;
import java.util.*;
import java.io.*;


/**
 *
 * @author isaaczapatto
 */
public class EVA3_EXAMEN_PRACTICO_EQUIPO_BROWNIE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cuntos alumnos quieres agregar?");
        int alumnos = scanner.nextInt();

        Estudiante[] Lista = new Estudiante[alumnos];

        int count = 0;
        while(alumnos > 0){
            Lista[count] = addStudent(scanner,Lista);
            count++;
            alumnos--;
        }

        for(int i = 0 ; i < count; i++){
            String temp = Lista[i].numeroDeLista + " " + Lista[i].nombre + " " + Lista[i].grado;
            storeData("Lista.txt", temp);
        }

        boolean student = true;

        do{
        System.out.println("Hay algun numero de estudinete en particular que desees ver? (SI/NO)");
        String buscar = scanner.next(); 
        if(buscar.equals("No")){student = false;}
        else {
            System.out.println("cual?"); 
            int buscarNo = scanner.nextInt(); 
            getStudent("Lista.txt", buscarNo);
        }     
        }while(student != false);

        System.out.println("Quieres imprimir la lista? (SI/NO)");
        String imprimir = scanner.next(); 
        if(imprimir.equals("Si")){
            loadData("Lista.txt");
        }  

        
	}

    public static Estudiante addStudent(Scanner scanner, Estudiante[]Lista){
        int noList = addStudentHelper(Lista);

        System.out.println("Ingresa el nombre:");
        String nombre = scanner.next();
        
        System.out.println("Ingresa el grado:");
        String grado = scanner.next();

        Estudiante alumno = new Estudiante(nombre,grado,noList);
        return alumno;
	}
    
    public static int addStudentHelper(Estudiante[]Lista){
        Scanner scnr = new Scanner(System.in);
        try {
            System.out.println("Ingresa un número de lista:");
            if (scnr.hasNextInt()) {
                int s = scnr.nextInt();
                if((checkNotesAndArray("Lista.txt",s,Lista) == false)){
                    return s;
                }else{
                    System.out.println("El numero que ingresaste ya esta en el sistema");
                    return addStudentHelper(Lista);
                }
            } else {
                System.out.println("El dato ingresado no es un número entero.");
                return addStudentHelper(Lista);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            return addStudentHelper(Lista);
        }
    }



    public static void getStudent(String text, int number){
        try{
            /*Here I am reading and sncanning the file */
            File file = new File(text);
            Scanner scnr = new Scanner(file);

            //This while loop introduce the values of the file into the array using next and split
            while (scnr.hasNextLine()) {
                String currentLine = scnr.nextLine();
                String [] result = currentLine.split(" ");

                if(Integer.parseInt(result[0]) == number)
                System.out.println("numero de lista: " + result[0] + " nombre: " + result[1] + " Grado: " + result[2]);
            }
        }catch (Exception e) {
            System.out.println("Error reading file");
        }

	}
    public static void storeData(String nombreArchivo, String data){
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo, true);
            fileWriter.write(data + "\n");
            fileWriter.close();
            System.out.println("Se ha agregado la línea de texto al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al agregar la línea de texto al archivo: " + e.getMessage());
        }
    }
    public static void loadData(String nombreArchivo){
        try{
            /*Here I am reading and sncanning the file */
            File file = new File(nombreArchivo);
            Scanner scnr = new Scanner(file);

            //This while loop introduce the values of the file into the array using next and split
            while (scnr.hasNextLine()) {
                String currentLine = scnr.nextLine();
                String [] result = currentLine.split(" ");
                System.out.println("numero de lista: " + result[0] + " nombre: " + result[1] + " Grado: " + result[2]);
            }
        }catch (Exception e) {
            System.out.println("Error reading file");
        }

	}
    public static boolean checkNotesAndArray(String text, int number, Estudiante[] Lista){
        boolean toReturn = false;
        try{
            /*Here I am reading and sncanning the file */
            File file = new File(text);
            Scanner scnr = new Scanner(file);

            //This while loop introduce the values of the file into the array using next and split
            while (scnr.hasNextLine()) {
                String currentLine = scnr.nextLine();
                String [] result = currentLine.split(" ");

                if(Integer.parseInt(result[0]) == number)
                toReturn =  true;
            }
        }catch (Exception e) {
            System.out.println("Error reading file");
        } 

        for(int i = 0; i < Lista.length; i++){
            if(Lista[i] == null)
            continue;
            if(Lista[i].numeroDeLista == number){
                toReturn = true;
            }
        }

        return toReturn;
    }
    


public static class Estudiante {
    String nombre;
    String grado;
    int numeroDeLista;

    Estudiante(){
    }  
    
        Estudiante(String nombre, String grado, int numeroDeLista){
            this.nombre = nombre;
            this.grado = grado;
            this.numeroDeLista = numeroDeLista;
        }   

        public void nombreGetter(){
                    System.out.println(this.nombre);
            }
        public void gradoGetter(){
                    System.out.println(this.grado);
            }
        public void numeroDeListaGetter(){
                    System.out.println(this.numeroDeLista);
            }

        public void nombreSetter(String nombre){
            this.nombre = nombre;
            }
        public void gradoSetter(String grado){
            this.grado = grado;
            }
        public void numeroDeListaSetter(int numeroDeLista){
            this.numeroDeLista = numeroDeLista;
            }

    }
}