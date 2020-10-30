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

import game.Contracts.ClassificationContract;
import game.Contracts.PilotContract;
import game.classes.VehicleAbstract;
import game.collections.ClassificationManagementContract;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Resultados extends game.classes.RaceResultsAbstract{

    private ClassificaManagement classificacoes;
    private String lvlname;
    private ClassificationContract classi_atual;
    private Classifica c;
    
    /**Construtor de resultados
     *
     * @param string nome da pista
     */
    public Resultados(String string)  {
        try {
            this.classificacoes=new ClassificaManagement(1);
            this.lvlname=string;
            
            this.loadResultsFromFile();
            classificacoes.sort();
        } catch (IOException ex) {
            Logger.getLogger(Resultados.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    public double bestlap(VehicleAbstract vehicle){
          
       double aux = 0, aux_x=9999;
        
       for(int i=0 ; i< classificacoes.size(); i++){
            c = (Classifica) classificacoes.getObject(i);
            VehicleAbstract v=c.getVehicle();
           if(v.getName()==vehicle.getName()){
               
            aux=c.getBestLap();
            
           if(aux<aux_x)
                aux_x=aux;
       }}
        return aux_x;
        
      }
    
    
    public PilotContract best (int TotaldeVoltas){
    Piloto p= null;
        int i;
        double aux=0, aux_x=99999;
                
    for(i=0; i<classificacoes.size(); i++){
        c= (Classifica) classificacoes.getObject(i);
        if(c.getTotalLaps()==TotaldeVoltas)
           aux=c.getBestLap();
        if(aux<aux_x)
            aux_x=aux;
         p= (Piloto) c.getPilot();
    }
        return p;
    
    }
  
//    public PilotContract best (int TotaldeVoltas){
//        Piloto p = null;
//        int i;
//        double aux=0, aux_x=99999;
//        
//        for(i=0; i<classificacoes.size(); i++){
//            c = (Classifica) classificacoes.getObject(i);
//            if(c.getTotalLaps()== TotaldeVoltas)
//               aux=c.getBestLap();
//            
//            if(aux<aux_x){
//                aux_x=aux;
//                p= (Piloto) c.getPilot();
//            }
//        }
//        
//        return p;
//    }
    
    
    /**Método responsável por retornar as classificações obtidas num determinado nível
     *
     * @return  coleção de classificações
     */
    @Override
    public ClassificationManagementContract getClassificationManagementContract() {
        int len = classificacoes.size();
        this.classificacoes.sort();
        
        if (len >= 7) {
            return new ClassificaManagement(Arrays.copyOf(classificacoes.getObjects(), 7));  
        }
            
        return classificacoes;  //Se tiver menos que 7 posições, senão mostra as outras posições
    }

    /**Método responsável por gravar os resulados em ficheiro
     *
     * @return o sucesso ou insucesso da operação
     * @throws IOException a exceção genérica
     * @throws ParseException a exceção de parsing
     */
    @Override
    public boolean saveResultsToFile() throws IOException, ParseException {
        JSONArray list=new JSONArray();
        
        
        for(int i=0;i<this.classificacoes.size();i++){
            JSONObject obj = new JSONObject();
           
            Classifica test=(Classifica) this.classificacoes.getObject(i);
            obj.put("Vehicle",test.getVehicle().getName());
            obj.put("PilotName", test.getPilot().getName());
            obj.put("PilotId", test.getPilot().getId());
            obj.put("BestLap", test.getBestLap());
            obj.put("TotalLaps",test.getTotalLaps());
            obj.put("BesTime", test.getTotalTime());
            
            JSONObject obj2=new JSONObject();
            obj2.put("Position",i);
            obj2.put("positionDetails", obj);
            list.add(i,obj2);
            
 
        }
    
        try (FileWriter file = new FileWriter("resultJSON\\"+"result_"+this.lvlname+".txt")) {

            file.write(list.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Save files erro");
            return false;
        }
        return true;

    }

    /**Método responsável por ler os resulados a partir de ficheiro
     *
     *
     * @return a gestão da classificação
     * @throws IOException a exceção genérica
     */
    @Override
    public ClassificationManagementContract loadResultsFromFile() throws IOException {
    
        JSONParser parser = new JSONParser();

        try {

            JSONArray objj =(JSONArray) parser.parse(new FileReader("resultJSON\\"+"result_"+this.lvlname+".txt"));
            
            Classifica clas=null;
            
            
            for(Object o:objj){
            JSONObject indice=(JSONObject) o;
            
            
           
            JSONObject total=(JSONObject)indice.get("positionDetails");
            
            clas=new Classifica(new Veiculo((String)total.get("Vehicle"),10, 11,(int)(long)total.get("PilotId"),(String)total.get("PilotName")),this.lvlname,(int) (long) total.get("TotalLaps") );
            
            
            clas.getVehicle().setName((String)total.get("Vehicle"));
            
          
            clas.setBestlap((double) total.get("BestLap"));
            clas.setTotalLaps((int) (long) total.get("TotalLaps"));
            clas.setTotalTime((double) total.get("BesTime"));
            
            classificacoes.addObject(clas);
            
            }
            

        } catch (FileNotFoundException e) {
            System.out.println("loadResultsFromFile: nao encontrou ficheiro");
        } catch (IOException e) {
            System.out.println("loadResultsFromFile: erro de abertura");
        } catch (ParseException e) {
            System.out.println("loadResultsFromFile:erro de parse");
        }

        
        return this.classificacoes;

    }

    /**Método responsável por mapear os resultados na classificação com os resultados guardados em ficheiro
     *
     * @return classificação
     * @throws IOException a exceção genérica
     */
    @Override
    public ClassificationContract mapLoadingResults() throws IOException {
        
        return this.classi_atual;

       
    }

    /**Método responsável por adicionar uma classificação
     *
     * @param cc classificação
     */
    @Override
    public void addClassification(ClassificationContract cc) {
       
       System.out.println("Seu resultado:\n"+cc.toString());
       this.classi_atual=cc;
        this.classificacoes.addObject(cc);

    }

 
}
