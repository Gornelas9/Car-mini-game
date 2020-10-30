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

import java.util.Objects;


public class Piloto implements game.Contracts.PilotContract{

    private int id;
    private String name;

    /**
     *
     * @param id representa o identificador de um piloto
     * @param name representa o nome de um piloto
     */
    public Piloto(int id,String name) {
       this.id=id;
       this.name=name;
    }

    /**Método responsável por retornar o nome do piloto
     *
     * @return nome do piloto
     * 
     */
    @Override
    public String getName() {
        return this.name;
        
    }

    /**Método responsável por especificar o nome do piloto
     *
     * @param string nome do piloto
     * 
     */
    @Override
    public void setName(String string) {
        this.name=string;
    }

    /**Método responsável por retornar o id do piloto
     *
     * @return id do piloto
     * 
     */
    @Override
    public int getId() {

       return this.id;
    }

    /**Método responsável por especificar o id do piloto
     *
     * @param i id do pioloto
     * 
     */
    @Override
    public void setId(int i) {
        this.id=i;
    }

    @Override
    public String toString() {
        return "Piloto{" + "id=" + id + ", name=" + name + '}';
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
        final Piloto other = (Piloto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
