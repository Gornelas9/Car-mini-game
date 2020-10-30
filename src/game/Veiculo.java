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

import game.Contracts.PilotContract;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Veiculo extends game.classes.VehicleAbstract{

        private String type;
        private String model;
        private double speed;
        private double travao;
        private long direcao;
        private double[] dimensoes;
        private Piloto pilot;
        private String name;
        private double h;
        private double w;

    /**Construtor para inicializar o nome do veículo
     *
     * @param name Nome do veiculo
     * @param id_piloto Id do piloto
     * @param nome_piloto Nome do piloto 
     */
    public Veiculo(String name,int id_piloto,String nome_piloto) {
        super(name);
        this.pilot=new Piloto(id_piloto,nome_piloto);
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        this.dimensoes=new double[this.getArray(name,"Bounds").size()];
        
        
        
        try {
             
             super.setPathToImage("vehicleImages\\"+name+".png");
             this.mappingBounds(name);
             jsonObject = (JSONObject) parser.parse(new FileReader("vehicleJSON\\"+name+".txt"));
			
			travao = (double) jsonObject.get("Break");
			speed = (double) jsonObject.get("Speed");
			
                        model=(String)jsonObject.get("Model");
                        type=(String)jsonObject.get("Type");
                        direcao=(long)jsonObject.get("Direction");
                        
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Veiculo:Ficheiro not found");
		}catch (IOException e) {
			System.out.println("Veiculo:Ficheiro nao abriu");
		} catch (org.json.simple.parser.ParseException ex) {
                        System.out.println("Veiculo:Parse erro");
       }
    }
        
    /**Construtor para inicializar um veículo
     *
     * @param name Nome do veiculo
     * @param w Peso do veiculo
     * @param h Altura do veiculo
     * @param id_piloto id do piloto
     * @param nome_piloto nome do piloto 
     */
    public Veiculo(String name, double w, double h,int id_piloto,String nome_piloto) {
        super(name, w, h);
        
           
        this.pilot=new Piloto(id_piloto,nome_piloto);
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        
        this.dimensoes=new double[this.getArray(name,"Bounds").size()];
        
        try {
             super.setPathToImage("vehicleImages\\"+name+".png");
             
             this.mappingBounds(name);
             jsonObject = (JSONObject) parser.parse(new FileReader("vehicleJSON\\"+name+".txt"));
			
			travao = (double) jsonObject.get("Break");
			speed = (double) jsonObject.get("Speed");
			
                        model=(String)jsonObject.get("Model");
                        type=(String)jsonObject.get("Type");
                        direcao=(long)jsonObject.get("Direction");
                        
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("Veiculo:Ficheiro not found");
		}catch (IOException e) {
			System.out.println("Veiculo:Ficheiro nao abriu");
		} catch (org.json.simple.parser.ParseException ex) {
                        System.out.println("Veiculo:Parse erro");
       }
    }

    /**Metodo responsavel por carregar as informaçoes do ficheiro
     *
     * @param string_name nome do veiculo e do ficheiro
     * @param key Palavra chave associada
     * @return vetor com as informaçoes 
     */
    public JSONArray getArray(String string_name,String key){
      JSONParser parser = new JSONParser();
      JSONObject jsonObject;
      JSONArray ja = null;
      
                    
        try
        {
            jsonObject =(JSONObject) parser.parse(new FileReader("vehicleJSON\\"+string_name+".txt"));
            ja=(JSONArray)jsonObject.get(key);
 

            }
        catch (FileNotFoundException e) {
		System.out.println("getArray:Ficheiro not found");
	}catch (IOException e) {
		System.out.println("getArray:Ficheiro nao abriu");
	} catch (org.json.simple.parser.ParseException ex) {
                System.out.println("getArray:Parse erro");
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
     * @param string nome do veiculo e do ficheiro
     * @return boolean conforme o sucedido
     * @throws IOException em caso de falhar na abertura do ficheiro
     */
    @Override
    public boolean mappingBounds(String string) throws IOException {
             JSONParser parser = new JSONParser();
      JSONObject jsonObject;
      JSONArray ja;
                
        
        
        
        
        try
        {
            jsonObject =(JSONObject) parser.parse(new FileReader("vehicleJSON\\"+string+".txt"));
            
            ja=(JSONArray)jsonObject.get("Bounds");
            this.copyArray(ja, this.dimensoes);
            super.setBounds(dimensoes);
            
            }
        catch (FileNotFoundException e) {
		System.out.println("mappingBounds:Ficheiro not found");
                return false;
	}catch (IOException e) {
		System.out.println("mappingBounds:Ficheiro nao abriu");
                return false;
	} catch (org.json.simple.parser.ParseException ex) {
                System.out.println("mappingBounds:Parse erro");
                return false;
       }
        
        return true;
    }

    /**Método responsável por retornar a velocidade do veículo
     *
     * @return a velocidade do veículo
     */
    @Override
    public double getSpeedVehicle() {
       return this.speed;
    }

    /**Método responsável por retornar a velocidade do veículo
     *
     * @return a direção do veículo
     */
    @Override
    public int getDirectionVehicle() {
        return (int)this.direcao;
    }

    /**Método responsável por retornar o travão do veículo
     *
     * @return o travão do veículo
     */
    @Override
    public double getBreakPadVehicle() {
        return this.travao;
    }

    /**Método responsável por retornar o tipo do veículo
     *
     * @return o tipo do veículo
     */
    @Override
    public String getType() {
       return this.type;
    }

    /**Método responsável por retornar o modelo do veículo
     *
     * @return o modelo do veículo
     */
    @Override
    public String getModel() {
       return this.model;
    }

    /**Método responsável por especificar o piloto do veículo
     *
     * @param pc o piloto do veículo
     */
    @Override
    public void setPilot(PilotContract pc) {
       this.pilot.setId(pc.getId());
       this.pilot.setName(pc.getName());
    }

    /**Método responsável por retornar o nome do veículo
     *
     * @return o nome do veículo
     */ 
    @Override
    public String getName() {
        return this.name;
    }

    /**Método responsável por especificar o nome do veículo
     *
     * @param string o nome do veículo
     */
    @Override
    public void setName(String string) {
       this.name=string;
    }

    /**Método responsável por retornar o piloto do veículo
     *
     * @return o piloto do veículo
     */
    @Override
    public PilotContract getPilot() {
        return this.pilot;
    }

    /**Método responsável por retornar o peso do veículo
     *
     * @return o peso do veículo
     */
    @Override
    public double getW() {
        return this.h;
    }

    /**Método responsável por especificar o peso do veículo
     *
     * @param d o peso do veículo
     */
    @Override
    public void setW(double d) {
        this.w=d;
        
    }

    /**Método responsável por retornar a altura do veículo
     *
     * @return a altura do veículo
     */
    @Override
    public double getH() {
        return this.h;
    }

    /**Método responsável por especificar a altura do veículo
     *
     * @param d a altura do veículo
     */
    @Override
    public void setH(double d) {
        this.h=d;
    }

    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
