/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jazie
 */
public class ArchivoIO {
    File archivo = new File("alumnos_io.txt");
    List<String> lineas = new ArrayList<>();

    public void escribir(String linea) {
        leer(); //Mantener actualizado
        try (
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))
        ) {
            for (String linea_saved : lineas) {
                escritor.write(linea_saved);
                escritor.newLine();
            }
            escritor.write(linea);
            escritor.newLine();

            System.out.println("Archivo guardado con exito");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    public void leer() {
        lineas = new ArrayList<>(); //Limpiamos arreglo para evitar duplicados
        try (
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }

    public List<String> getLista() {
        return lineas;
    }

    
   
    public void actualizarPorId(String id, String nuevaLinea) {
        leer(); 
        boolean encontrado = false;

        for (int i = 0; i < lineas.size(); i++) {
            // Asumimos que el ID es el primer dato y está separado por una coma
            if (lineas.get(i).startsWith(id + ",")) {
                lineas.set(i, nuevaLinea); // Reemplazamos la línea vieja por la nueva
                encontrado = true;
                break; // Terminamos el ciclo al encontrarlo
            }
        }

        if (encontrado) {
            guardarTodo(); // Sobrescribimos el archivo con los datos actualizados
            System.out.println("Registro con ID " + id + " actualizado con éxito.");
        } else {
            System.out.println("No se encontró ningún registro con el ID: " + id);
        }
    }

    
    public void eliminarPorId(String id) {
        leer(); 

        
        boolean removido = lineas.removeIf(linea -> linea.startsWith(id + ","));

        if (removido) {
            guardarTodo(); 
            System.out.println("Registro con ID " + id + " eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún registro con el ID: " + id);
        }
    }

    
    private void guardarTodo() {
        try (
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))
        ) {
            for (String linea_actual : lineas) {
                escritor.write(linea_actual);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }
}
