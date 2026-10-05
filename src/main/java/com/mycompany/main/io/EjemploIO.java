/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main.io;

/**
 *
 * @author Jazie
 */
public class EjemploIO {
    public static void main(String[] args) {
      ArchivoIO aio = new ArchivoIO();
      aio.escribir("279565, Jaziel Aranda");
      aio.escribir("123456, Billie Eilish");
        System.out.println(aio.getLista());
      aio.eliminarPorId("279565");
      System.out.println(aio.getLista());
      aio.actualizarPorId("123456", "123456, Jorge Norzagaray");
      System.out.println(aio.getLista());
    }  
}
