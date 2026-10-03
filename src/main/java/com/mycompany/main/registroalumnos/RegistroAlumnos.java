/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main.registroalumnos;

/**
 *
 * @author Jazie
 */


import java.util.List;
import java.util.Scanner;

public class RegistroAlumnos {

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion = 0;
        IO aio = new IO();
        Scanner sc = new Scanner(System.in); // scanner para leer

        do {
            try {
                // menu
                
                System.out.println("1. Registrar nuevo alumno");
                System.out.println("2. Ver todos los alumnos");
                System.out.println("3. Buscar alumno por ID");
                System.out.println("4. Actualizar nombre de alumno");
                System.out.println("5. Eliminar alumno");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el ID: ");
                        String ID = sc.nextLine();

                        // aqui se valida si el id existe
                        if (aio.existeID(ID)) {
                            System.out.println("Error: El ID " + ID + " ya se encuentra registrado.");
                        } else {
                            System.out.print("Ingrese el nombre completo: ");
                            String Nombre = sc.nextLine();
                            aio.escribir(ID + " - " + Nombre);
                        }
                        break;

                    case 2:
                        System.out.println("\n Lista de Alumnos");
                        List<String> lineas = aio.getLista();
                        if (lineas.isEmpty()) {
                            System.out.println("El archivo está vacio.");
                        } else {
                            lineas.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.println("\n Buscar alumno por ID");
                        System.out.print("Ingrese ID: ");
                        String idBuscar = sc.nextLine();
                        List<String> listaBuscar = aio.getLista();
                        boolean encontrado = false;

                        for (String l : listaBuscar) {
                            String[] partes = l.split(" - ");
                            if (partes.length >= 1 && partes[0].trim().equals(idBuscar.trim())) {
                                System.out.println("Información del alumno: " + l);
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("No se encontró ningún alumno con el ID: " + idBuscar);
                        }
                        break;

                    case 4:
                        System.out.println("\n Actualizar Nombre de Alumno");
                        System.out.print("Ingrese ID del alumno a modificar: ");
                        String idMod = sc.nextLine();

                        if (!aio.existeID(idMod)) {
                            System.out.println("Error: El ID " + idMod + " no existe.");
                        } else {
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = sc.nextLine();

                            List<String> listaMod = aio.getLista();
                            for (int i = 0; i < listaMod.size(); i++) {
                                String[] partes = listaMod.get(i).split(" - ");
                                if (partes.length >= 1 && partes[0].trim().equals(idMod.trim())) {
                                    listaMod.set(i, idMod + " - " + nuevoNombre);
                                    break;
                                }
                            }
                            aio.guardarLista(listaMod);
                            System.out.println("¡Alumno actualizado con éxito!");
                        }
                        break;

                    case 5:
                        System.out.println("\n Eliminar Alumno");
                        System.out.print("Ingrese ID del alumno a eliminar: ");
                        String idEli = sc.nextLine();

                        if (!aio.existeID(idEli)) {
                            System.out.println("Error: El ID " + idEli + " no existe.");
                        } else {
                            List<String> listaEli = aio.getLista();
                            for (int i = 0; i < listaEli.size(); i++) {
                                String[] partes = listaEli.get(i).split(" - ");
                                if (partes.length >= 1 && partes[0].trim().equals(idEli.trim())) {
                                    listaEli.remove(i);
                                    break;
                                }
                            }
                            aio.guardarLista(listaEli);
                            System.out.println("Alumno eliminado con exito");
                        }
                        break;

                    case 6:

                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error al ingresar la opcion: " + e.getMessage());
            }
        } while (opcion != 6);

        
    }
}

