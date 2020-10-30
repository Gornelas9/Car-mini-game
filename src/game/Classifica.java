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
import game.Contracts.RaceResultsComparator;
import game.classes.VehicleAbstract;
import java.util.Objects;


public class Classifica implements game.Contracts.ClassificationContract{
    
    private VehicleAbstract veiculo;
    private String lvl;
    private int n;
    private double totalTime;
    private double bestlap;
    
    /** Construtor de Classifica
     *
     * @param d uma instancia de classifica
     */
    public Classifica(Classifica d) {
        this.lvl=d.getLevel();
        this.n=d.getTotalLaps();
        this.totalTime=d.getTotalTime();
        this.bestlap=d.getBestLap();
        this.veiculo=d.getVehicle();
        
    }
    
    /**Construtor de Classifica
     *
     * @param va veiculo usado na corrida
     * @param lvl nome do nivel da corrida
     * @param n numero de voltas
     */
    public Classifica(VehicleAbstract va,String lvl,int n) {
        this.veiculo=va;
        this.lvl=lvl;
        this.n=n;
        this.totalTime=0;
        this.bestlap=-1;
    }

    /**Método responsável por retornar o nível da classificação
     *
     * @return o nível da classificação
     */
    @Override
    public String getLevel() {
        
        return this.lvl;
    }

    /**Método responsável por especificar o nível da classificação
     *
     * @param string level - o nível da classificação
     */
    @Override
    public void setLevel(String string) {
        this.lvl=string;
    }

    /**Método responsável por especificar o número total de voltas
     *
     * @param i o número total de voltas
     */
    @Override
    public void setTotalLaps(int i) {
        this.n=i;
    }

    /**Método responsável por retornar o piloto
     *
     * @return o piloto
     */
    @Override
    public PilotContract getPilot() {
        return veiculo.getPilot();
    }

    /**Método responsável por retornar o número total de voltas
     *
     * @return o número total de voltas

     */
    @Override
    public int getTotalLaps() {
        return n;
    }

    /**Método responsável por especificar o piloto
     *
     * @param pc o piloto
     */
    @Override
    public void setPilot(PilotContract pc) {
        this.veiculo.setPilot(pc);
    }

    /** Método responsável por retornar o veículo
     *
     * @return o veículo
     */
    @Override
    public VehicleAbstract getVehicle() {
        return this.veiculo;
    }

    /**Método responsável por especificar o veículo
     *
     * @param va o veículo
     */
    @Override
    public void setVehicle(VehicleAbstract va) {
        this.veiculo= va;
    }

    /**Método responsável por devolver o tempo da melhor volta
     *
     * @return o tempo da melhor volta
     */
    @Override
    public double getBestLap() {
        return this.bestlap;
        
    }

    /**Método responsável por adicionar o tempo de uma volta
     *
     * @param d  tempo de uma volta
     */
    @Override
    public void addLap(double d) {
        if(this.bestlap==-1){
            this.bestlap=d;
        }
        if(d<this.bestlap){
            this.bestlap=d;
        }
         
            
    }

    /**Método responsável por retornar o tempo total
     *
     * @return o tempo total
     */
    @Override
    public double getTotalTime() {
        return this.totalTime;
    }

    /**Método responsável por especificar o tempo total
     *
     * @param d o tempo total
     */
    @Override
    public void setTotalTime(double d) {
        this.totalTime=d;
    }

    /**Metodo resposavel por comparar os melhores resultados
     *
     * @param rrc objeto com o qual se pretende comparar
     * @return superior a 0 se o objeto a comparar for superior,
     * inferior a 0 se o objeto a comparar for inferior,
     * 0 se os dois objetos forem iguais
     */
    @Override
    public int compareTo(RaceResultsComparator rrc) {
        if(rrc instanceof Classifica){
            Classifica r=(Classifica)rrc;
            if(r.getBestLap()>this.getBestLap())
                return 1;
            else if(r.getBestLap()<this.getBestLap())
                return -1;
            
        }
        return 0;
    }

    /**Definir a melhor volta
     *
     * @param bestlap  tempo da melhor volta
     */
    public void setBestlap(double bestlap) {
        this.bestlap = bestlap;
    }

    @Override
    public String toString() {
        return "Driver:"+this.veiculo.getPilot().getName()+" (id:"+this.veiculo.getPilot().getId()+")\n"+"Vehicle:"+this.veiculo.getName()+"\nBest lap:"+this.bestlap+"\nTotal Time:"+this.totalTime;
               
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
        final Classifica other = (Classifica) obj;
        if (this.n != other.n) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalTime) != Double.doubleToLongBits(other.totalTime)) {
            return false;
        }
        if (Double.doubleToLongBits(this.bestlap) != Double.doubleToLongBits(other.bestlap)) {
            return false;
        }
        if (!Objects.equals(this.lvl, other.lvl)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        return true;
    }
    
    
 
}
