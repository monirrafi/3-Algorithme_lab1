public class MyListe implements InterfaceListe {
    Noued tete=null;
    Noued queue=null;
    int size=0;



    public MyListe(Object obj) {
        this.tete = new Noued(obj);
        this.queue = tete;
    }

    public MyListe() {
        
        
        
    }
       // Method to print the LinkedList.
       public static void printList(MyListe list)
       {
           Noued currNode = list.tete;
       
           System.out.print("[");
       
           // Traverse through the LinkedList
           while (currNode != null) {
               // Print the data at current node
               System.out.print(currNode.getData() + " ");
       
               // Go to next node
               currNode = currNode.getNext();
           }
           System.out.println("]");

        }

    @Override
    public void ajouter(Object obj) {
        
        Noued nouveauNoued = new Noued(obj);
       // Noued suivant = this.queue;
        if(queue == null){
            this.tete = nouveauNoued;
            this.queue = nouveauNoued;
        }else{
            this.queue.setNext(nouveauNoued);
            this.queue = nouveauNoued;
        }

    }

    @Override
    public void suprimer(Object obj) {
        Noued nouedSuprimer = new Noued(obj);
        
        if(!recherche(obj).equals("Null")){
            if(tete.getData() == nouedSuprimer.getData()){
                tete=tete.getNext();
            }else if(queue.getData() == nouedSuprimer.getData()){
                queue=queue.getPrevious();
            }else{
                
                Noued suivant = nouedSuprimer.getNext();
                Noued avant = nouedSuprimer.getPrevious();
//                if(suivant != null && avant != null ){
                    avant.setNext(suivant);
//                }
        
                
            }
        }
        
    }

    @Override
    public Object recherche(Object obj) {
        Noued nouedCherchee = new Noued(obj);
        boolean retour=false;
        Noued tmp = tete;
       // Noued suivant = this.queue;
       while(tmp != null){
        if(tmp.getData() == nouedCherchee.getData()){
            retour = true;
            break;
            
        }else{
            tmp = tmp.getNext();
        }
        }
        if(retour == false){  
              
			return "Null";
        }else{
            return tmp;
        }
            

	}

    

    @Override
    public void lister(Object obj) {
        // TODO Auto-generated method stub
        
    }



    public Noued getTete() {
        return tete;
    }

    public void setTete(Noued tete) {
        this.tete = tete;
    }

    public Noued getQueue() {
        return queue;
    }

    public void setQueue(Noued queue) {
        this.queue = queue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
}
