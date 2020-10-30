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

import game.classes.RacingGame;
import game.classes.VehicleAbstract;
import java.util.logging.Level;
import java.util.logging.Logger;

public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Pede ao utilizador as informaçoes
        
        
//        Resultados r= new Resultados("level1");
//        Classifica cl;
//        int a;
//       cl= (Classifica) r.getClassificationManagementContract().getObject(0);
//       cl.getBestLap();
//       a= r.getClassificationManagementContract().size() -1;
//       cl= (Classifica) r.getClassificationManagementContract().getObject(a);
       
//    Resultados r= new Resultados("level4");
//        r.best(1);
//    Piloto p1= (Piloto) r.best(1);
//        System.out.println("test:"+ p1.getName());


    
        InfoCorrida c=new InfoCorrida();

        String nome_piloto=c.scanpiloto_name();
        int id_piloto=c.scanpiloto_id();
        String nivel=c.scanLevel();
        String veiculo=c.scanVeiculo();
        int n_voltas=c.scanVoltas();
        try {
        
        
        
            //Inicia o veiculo
            Veiculo v1=new Veiculo(veiculo,5,5,id_piloto,nome_piloto);
           
            //Instancia o jogo
            RacingGame game1= new RacingGame();
            //Adiciona o carro
            game1.addCar(v1);
            //Instancia o autodromo
            Autodromo lvl=new Autodromo(nivel);
            //Instancia a classificaçao do jogador
            Classifica calis_now=new Classifica(v1,nivel, 1);
            //Adiciona o nivel ou autodromo
            game1.addLevel(lvl);
            //Adicionar o numero de voltas
            game1.addNumberOfLaps(n_voltas);
            //Adicionar a classificaçao
            game1.setClassification(calis_now);
            //Instancia os resultados da pista
            game1.setRaceResults(new Resultados(nivel));
            //Começa o jogo
            game1.startGame();
            
        } catch (Exception ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
   
    
}