public class objectcheck {
    public static class person {   
        String name="abishek";    
    
  
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof person)){
           return false;
        }

        person per=(person)obj;


        return this.name.equals(per.name);
    }
}
    public static void main(String[] args) {
        person firstob= new person();
        person secondob= new person();
        
        if(firstob.name.equals(secondob.name)){
           
            System.out.println("equal");
        }else{
            System.out.println("sorry");
        }
          

        

    }
   
}
