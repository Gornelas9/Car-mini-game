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

import java.util.Arrays;


public class ClassificaManagement implements game.collections.ClassificationManagementContract{
    private  int DEFAULT_SIZE=7;
    private Object objects[];
    
    /**Metodo responsavel por retornar o vetor de objetos
     *
     * @return vetor de objectos
     */
    public Object[] getObjects() {
        return objects;
    }
    
    /**Construtor de ClassificaManagement
     *
     * @param objects , vetor de objetos
     */
    public ClassificaManagement(Object[] objects) {
        this.objects = objects;
        
    }

    /**Construtor de ClassificaManagement que inicializa com um valor default
     *
     */
    public ClassificaManagement() {
        this.objects=new Object[DEFAULT_SIZE];
        
    }
    
    /**Construtor de ClassificaManagement
     *
     * @param maxSize tamanho maximo do vetor
     */
    public ClassificaManagement(int maxSize) {
        this.objects = new Object[maxSize];
        
    }
    
    /**Método responsável por ordernar a classificação
     *
     */
    
    //bubbleSort
    @Override
    public void sort() {
        
    for (int i=0;i<this.size();i++) {
             for(int j=0;j<this.size()-1;j++){
            
               if(this.objects[j] instanceof Classifica && this.objects[j+1] instanceof Classifica){
                  Classifica c= (Classifica)this.objects[j];
                   Classifica c1= (Classifica)this.objects[j+1];
                   Object aux;
                   if(c.compareTo(c1)<0){
 
                       
                       aux = this.objects[j];
                       this.objects[j]=this.objects[j+1];    //Troca
                       this.objects[j+1]=aux;
                       
                       
                   }
                  
               }
           }
         }
        
    

    }

    /**Método responsável por retornar o número de elementos existentes da classificação
     *
     * @return o número de posições não nulas
     */
    @Override
    public int size() {
        int j=0;
        
        for (Object object : objects) {
            if (object != null) {
                j++;
            }
         
        }
        
         
         return j;
    }

    /**Método responsável por aumentar o número de elementos existentes na classificação
     *
     */
    @Override
    public void increaseSize() {
         
        this.objects = Arrays.copyOf(this.objects, this.size()+1);
    }

    /**Método responsável por diminuir o número de elementos existentes na classificação
     *
     */
    @Override
    public void decreaseSize() {
        
        this.objects = Arrays.copyOf(this.objects, this.size()-1);
    }

    /**Método responsável por inserir um objeto do vetor de objetos.
     *
     * @param o  objeto a inserir no vetor
     * @return Valor que sinaliza o sucesso/insucesso da operação
     */
    @Override
    public boolean addObject(Object o) {
       int len = this.size();
       
       //array cheio mete mais 1 posicao
       if (this.objects.length == this.size()){
           this.increaseSize();
       }
           
       
       this.objects[len] = o;
       
       return true;
       
       

    }
    
     
//Metodo para ordenar o vetor
      private void ordenar_vetor(){
       for(int i=0,j=1;i<this.objects.length && j<this.objects.length;i++,j++){
       
               if(this.objects[i]==null && this.objects[j]!=null){
                   this.objects[i]=this.objects[j];
                   this.objects[j]=null;
               }
           }
       }
    
    /**Método responsável por remover um objeto do vetor de objetos.
     *
     * @param i índice correspondente ao elemento a eliminar
     * @return o objeto eliminado
     */
    @Override
    public Object removeObject(int i) {
         
       Object obj=new Object();
       
       if(i>=0){
            this.objects[i]=obj;
            this.objects[i]=null;
            ordenar_vetor();
            this.decreaseSize();
           
            return obj;
           }
       return false;
    }

    /** Método responsável por retornar um objeto existente numa determinada posição do vetor de objetos.
     *
     * @param i  índice do elemento a devolver
     * @return objeto do tipo Object
     */
    @Override
    public Object getObject(int i) {
        
        return this.objects[i];
    }

    /**Método responsável por encontrar um objeto no vetor de objetos
     *
     * @param o objeto a procurar no vetor
     * @return o indice do objeto no vetor. No caso do elemento não existir, deverá ser retornado o valor -1
     */
    @Override
    public int findObject(Object o) {
        
       for(int i=0;i<this.objects.length;i++){
           if(this.objects[i]!=null && this.objects[i].equals(o)){
               
               return i;
           }
       }
       return -1;
    }

   

}
