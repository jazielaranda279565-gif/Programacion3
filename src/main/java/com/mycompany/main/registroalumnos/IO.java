/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.registroalumnos;

/**
 *
 * @author Jazie
 */



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class IO {
    
    private Path ruta = Path.of("alumnos.txt");
    private List<String> lineas = new ArrayList<>();

    public IO() {
        // si el archivo no existe al iniciar, se crea automáticamente
        try {
            if (!Files.exists(ruta)) {
                Files.createFile(ruta);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    
    public void leer() {
        try {
            lineas = Files.readAllLines(ruta);//se leen todas las lineas
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
            lineas = new ArrayList<>();
        }
    }

    // Se escribe al final del archivo usando Java NIO 
    public void escribir(String linea) {
        try {
            Files.writeString(ruta, linea + System.lineSeparator(), StandardOpenOption.APPEND);
            System.out.println("Alumno registrado con exito");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    
    public void guardarLista(List<String> nuevasLineas) {
        try {
            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }

    public List<String> getLista() {
        leer(); // mantiene la lista actualizada
        return lineas;
    }

    // se comprueba si existe el id
    public boolean existeID(String id) {
        leer();
        for (String l : lineas) {
            String[] partes = l.split(" - ");
            if (partes.length >= 1 && partes[0].trim().equals(id.trim())) {
                return true;
            }
        }
        return false;
    }
}