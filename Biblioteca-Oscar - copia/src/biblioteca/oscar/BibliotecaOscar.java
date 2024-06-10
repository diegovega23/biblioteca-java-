package biblioteca.oscar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class BibliotecaOscar {

    public static void main(String[] args) throws IOException {
        Lista niños = IN_niños("src/biblioteca/oscar/Lectores.txt");
        Scanner scanner = new Scanner(System.in);

        boolean seguirAgregando = true;
        while (seguirAgregando) {
            System.out.println("Desea agregar mas lectores? (si/no)");
            String anadirlectores = scanner.nextLine();

            if (anadirlectores.equalsIgnoreCase("si")) {
                System.out.println("Ingrese el nombre del lector:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el apellido del lector:");
                String apellido = scanner.nextLine();

                niños.addNiño(new Niño(nombre, apellido, false));
            } else if (anadirlectores.equalsIgnoreCase("no")) {
                seguirAgregando = false;
            } else {
                System.out.println("Respuesta invalida. Por favor, ingrese 'si' o 'no'.");
            }
        }

        Random random = new Random();

        if (niños.estaVacia()) {
            System.out.println("No hay niños en la lista.");
            return;
        }

        Nodo actualHorario = niños.getCabeza();
        Nodo actualAntihorario = niños.getCabeza();

        while (true) {  int pasosHorario = random.nextInt(niños.obtenerTamaño()) + 1;
            int pasosAntihorario = random.nextInt(niños.obtenerTamaño()) + 1;

            actualHorario = recorrerHorario(actualHorario, pasosHorario);
            actualAntihorario = recorrerAntihorario(actualAntihorario, pasosAntihorario);

            if (actualHorario == actualAntihorario) {
                System.out.println(actualHorario.getNiño() + " debe levantarse a leer.");
                registrarLectura("src/biblioteca/oscar/Lectoresout.txt", actualHorario.getNiño());
                actualHorario.getNiño().setHaLeido(true);
            } else {
                System.out.println(actualHorario.getNiño().toString() + " debe levantarse a leer en sentido horario.");
                System.out.println(actualAntihorario.getNiño() + " debe levantarse a leer en sentido antihorario.");
                registrarLectura("src/biblioteca/oscar/Lectoresout.txt", actualHorario.getNiño());
                registrarLectura("src/biblioteca/oscar/Lectoresout.txt", actualAntihorario.getNiño());
                actualHorario.getNiño().setHaLeido(true);
                actualAntihorario.getNiño().setHaLeido(true);
            }

            // Condición de parada: salir cuando todos los niños hayan leído
            boolean todosHanLeido = true;
            Nodo nodo = niños.getCabeza();
            do {
                if (!nodo.getNiño().isHaLeido()) {
                    todosHanLeido = false;
                    break;
                }
                nodo = nodo.getSiguiente();
            } while (nodo != niños.getCabeza());

            if (todosHanLeido) {
                break;
            }

            // Actualizar los nodos actuales para la siguiente ronda
            actualHorario = actualHorario.getSiguiente();
            actualAntihorario = actualAntihorario.getAnterior();
        }
            

    System.out.println("Gracias por participar en la actividad de lectura.");
    }

    //archivos IN de lectores (reader-lector del .txt)
    public static Lista IN_niños(String archivo) throws IOException {
        Lista lista_niños = new Lista();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] elemento = linea.split("\\|");
            String nombre = elemento[0].trim(); //se guarda el nombre
            String apellido = elemento[1].trim(); //se guarda el apellido
            lista_niños.addNiño(new Niño(nombre, apellido, false));
        }
        br.close();
        return lista_niños;
    }

    public static void registrarLectura(String archivo, Niño niño) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(niño.getNombre().toString() + " " + niño.getApellido().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //recorredores en los nodos
    public static Nodo recorrerHorario(Nodo nodo, int pasos) {
        for (int i = 0; i < pasos; i++) {
            nodo = nodo.getSiguiente();
        }
        System.out.println(nodo.getNiño().toString() + " debe levantarse a leer en sentido horario.");
        return nodo;
    }

    public static Nodo recorrerAntihorario(Nodo nodo, int pasos) {
        for (int i = 0; i < pasos; i++) {
            do {
                nodo = nodo.getAnterior();
            } while (nodo.getNiño().isHaLeido());
        }
        System.out.println(nodo.getNiño().toString() + " debe levantarse a leer en sentido antihorario.");
        return nodo;
        }
    }