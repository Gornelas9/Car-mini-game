/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;


public final class Autodromo implements game.Contracts.LevelContract{

    private double[] upperBound;
    private double[] lowerBound;
    private double[] checkPoints;
    private double[] startCar;
    private String name;
    private String pathToImage;
    

    /**Construtor de Autodromo
     *
     * @param name nome do nivel/autodromo
     */
    public Autodromo(String name) {
         
        try {
            this.upperBound=new double[this.getArray(name,"UpperBound").size()];
            this.lowerBound=new double[this.getArray(name,"LowerBound").size()];
            this.startCar=new double[this.getArray(name,"StartCar").size()];
            this.checkPoints=new double[this.getArray(name,"Checkpoints").size()];
           
           this.name=name;
           this.setPathToImage(this.name);
           pathToImage=this.getPathToImage();
            
            
            this.mappingBounds(this.name);
        } catch (IOException ex) {
            Logger.getLogger(Autodromo.class.getName()).log(Level.SEVERE, null, ex);
        }
   }



    /**Método responsável por retornar o nome do nível
     *
     * @return o nome do nível
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**Método responsável por especificar o nome do nível
     *
     * @param string o nome do nível
     */
    @Override
    public void setName(String string) {
        this.name="levelsJSON"+string+".txt";
    }
    
     /**Metodo responsavel por carregar as informaçoes do ficheiro
     *
     * @param string nome do veiculo e do ficheiro
     * @param key Palavra chave associada
     * @return vetor com as informaçoes 
     */
    public JSONArray getArray(String string, String key){
      JSONParser parser = new JSONParser();
      JSONObject jsonObject;
      JSONArray ja = null;
 
        try
        {
            jsonObject =(JSONObject) parser.parse(new FileReader("levelsJSON\\"+string+".txt"));
            ja=(JSONArray)jsonObject.get(key);
 

            }
        catch(FileNotFoundException e)
        {
            System.out.println("getArray:Nao encontrou o ficheiro");
        } catch (IOException ex) {
            System.out.println("getArray:Nao consegui abrir o ficheiro");
            
        } catch (ParseException ex) {
           System.out.println("getArray:Ficheiro sem dados");
        } 
        return ja;
    }
    
    /** Metodo responsavel por copiar um array para o outro
     *
     * @param array Array a copiar
     * @param array2 Array que vai adquirir a informaçao 
     */
    public void copyArray(JSONArray array,double[] array2){
     for(int i=0;i<array.size();i++){
               
               array2[i]=(double)array.get(i);
           }
 
}
    
    /**
     *
     * @param string ficheiro onde estão definidos os limites do nível
     * @return retorna o sucesso ou insucesso da operação
     * @throws IOException excepção a ser lançada em caso de erro na leitura do ficheiro
     */
    @Override
    public boolean mappingBounds(String string) throws IOException {
          
      JSONParser parser = new JSONParser();
      JSONObject jsonObject;
      JSONArray ja;
   
        try
        {
            jsonObject =(JSONObject) parser.parse(new FileReader("levelsJSON\\"+string+".txt"));
            
            ja=(JSONArray)jsonObject.get("UpperBound");
            this.copyArray(ja, this.upperBound);
            ja=(JSONArray)jsonObject.get("LowerBound");
            this.copyArray(ja, this.lowerBound);
            ja=(JSONArray)jsonObject.get("Checkpoints");
            this.copyArray(ja, this.checkPoints);
            ja=(JSONArray)jsonObject.get("StartCar");
            this.copyArray(ja, this.startCar);
            }
       catch(FileNotFoundException e)
        {
            System.out.println("mappingBounds:Nao encontrou o ficheiro");
        } catch (IOException ex) {
            System.out.println("mappingBounds:Nao consegui abrir o ficheiro");
            
        } catch (ParseException ex) {
           System.out.println("mappingBounds:Ficheiro sem dados");
        } 
        
       
        

        return true;
    }

    /**Método responsável por especificar o caminho para a imagem do nível
     *
     * @param string o caminho para a imagem do nível
     */
    @Override
    public void setPathToImage(String string) {
   
        this.pathToImage="levelImages\\"+string+".png";
        
    }

    /**Método responsável por retornar os limites inferiores do nível
     *
     * @return um array com os limites inferiores do nível
     */
    @Override
    public double[] getLowerBounds() {
        return this.lowerBound;
    }

    /**Método responsável por retornar os limites superiores do nível
     *
     * @return um array com os limites superiores do nível
     */
    @Override
    public double[] getUpperBounds() {
        return this.upperBound;
    }

    /**Método responsável por retornar a localização dos checkpoints do nível
     *
     * @return um array com a localização dos checkpoints do nível
     */
    @Override
    public double[] getCheckPoints() {
        return this.checkPoints;
    }

    /**Método reponsável por retornar a localização inicial do veículo
     * 
     * @return array com a localização inicial do veículo
     */
    @Override
    public double[] getStartCar() {
        return this.startCar;
    }

    /**Método responsável por retornar o caminho para a imagem do nível
     *
     * @return o caminho para a imagem do nível
     */
    @Override
    public String getPathToImage() {
       return this.pathToImage;
    }


}
        
        
