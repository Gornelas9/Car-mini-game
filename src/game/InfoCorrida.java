/*
* Nome: Gonçalo Ornelas Soares 
* Número: 8170172
* Turma: T1
*
* Nome: Mário Jorge Mendes Leite
* Número: 8170573
* Turma: T3
*/
package game;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;


public class InfoCorrida {
        
    /**Metodo responsavel por permitir ao utilizador escolher um veiculo
     *
     * @return nome do veiculo
     */
    public String scanVeiculo(){
        String[] files = listFiles("vehicleJSON");
        Scanner in = new Scanner(System.in);
        String lixo="";
        boolean ok=false;
        int car=-1;
        int len = files.length;
        System.out.println("Veiculos: ");
        for(int i=0; i<len; i++) {
            System.out.println(i+1 + ") " + files[i]);
        }
        while(!ok || car<0 || car>=len){
        
        System.out.print("Escolha o veiculo:");
        try{
            car = in.nextInt()-1;
            ok=true;
        }catch(InputMismatchException e){
            System.out.println("Veiculo invalido");
            lixo=in.nextLine(); //limpar o buffer
        }
                
        
        }
        

        System.out.println("Veiculo escolhido: " + files[car]);
        
        return files[car];
    }
    
    /**Metodo responsavel por permitir ao utilizador escolher um nivel/Autodromo
     *
     * @return nome do nivel/Autodromo
     */
    public String scanLevel(){
        String[] files = listFiles("levelsJSON");
        Scanner in = new Scanner(System.in);
        String lixo="";
        boolean ok=false;
        int level=-1;
        int len = files.length;
        System.out.println("Niveis: ");
        for(int i=0; i<len; i++) {
            System.out.println(i+1 + ") " + files[i]);
        }
        while(!ok || level<0 || level>=len){
        
        System.out.print("Escolha o nivel:");
        try{
            level = in.nextInt()-1;
            ok=true;
        }catch(InputMismatchException e){
            System.out.println("Nivel invalido");
            lixo=in.nextLine(); //limpar o buffer
        }
                
        
        }
        

        System.out.println("Nivel escolhido: " + files[level]);
        
        return files[level];
    }
        
    /**Metodo responsavel por permitir ao utilizador inserir o seu nome
     *
     * @return nome do piloto
     */
    public String scanpiloto_name(){
        Scanner input=new Scanner(System.in);
        System.out.print("Insira o seu nome:");
        String nome_piloto=input.next();
        return nome_piloto;
        }
        
    /**Metodo responsavel por permitir ao utilizador inserir o seu id
     *
     * @return id do piloto
     */
    public int scanpiloto_id(){
            
        Scanner in = new Scanner(System.in);
        String lixo="";
        boolean ok=false;
        int id=-1;
        
        while(!ok || id<=0){
        
        System.out.print("Escolha o seu id:");
        try{
            id = in.nextInt();
            ok=true;
        }catch(InputMismatchException e){
            System.out.println("id invalido");
            lixo=in.nextLine(); //limpar o buffer
        }

        }
  
        
        return id;
        }
    
    /**Metodo responsavel por permitir ao utilizador inserir o numero de voltas
     *
     * @return numero de voltas
     */
    public int scanVoltas(){
            
        Scanner in = new Scanner(System.in);
        String lixo="";
        boolean ok=false;
        int voltas=-1;
        
        while(!ok || voltas<=0){
        
        System.out.print("Numero de voltas:");
        try{
            voltas = in.nextInt();
            ok=true;
        }catch(InputMismatchException e){
            System.out.println("Numero invalido");
            lixo=in.nextLine(); //limpar o buffer
        }

        }
  
        
        return voltas;
        }
    
    
     /*Metodo responsavel por listar o que existe disponivel
     *
     * @return vetor com o que esta disponivel
     */
    
    private String[] listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        String[] a = new String[fList.length];
        for (int i=0; i<fList.length; i++) {    
                String nome = fList[i].getName();
                a[i] = nome.substring(0, nome.length()-4);
            
        }
        return a;
    }
}
