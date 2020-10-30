/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author LENOVO
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Resultados r = new Resultados("level1");

Veiculo v= new Veiculo("f1",2.0, 4.0, 4, "ff");

        System.out.println("test:"+ r.bestlap(v));
    }
    
}
