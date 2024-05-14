import java.util.Scanner;

public class linkedlist {
      /**
       * Node
 
 String data      */
      public static class Node {
        String data;
        Node next;
        Node(String data)
        {
            this.data=data;
        }
      }
      static Node head;
      static void add(String data)
      {
        Node newNode=new Node(data);
        if(head==null)
        {
            head=newNode;
        }else{
            Node temp=head;
            while (temp.next!=null){
                temp=temp.next;
                
            }
            temp.next=newNode;
            
        }
         
      }

      static void print(Node head)
      {
        Node temp=head;
        while(temp!=null)
        {
            System.out.println(temp.data);
            temp=temp.next;
        } 
      }

      public static void main(String... args) {
        Scanner input = new Scanner(System.in);
        int check = -1; // Initializing check variable
        while (check != 0) {
            System.out.println("Enter 1 to add a new node");
            System.out.println("Enter 2 to print the list");
            System.out.println("Enter 0 to exit");
            check = input.nextInt();

            switch (check) {
                case 1:
                    System.out.println("Enter data for the new node:");
                    String data = input.next();
                    add(data);
                    break;
                case 2:
                    System.out.println("Linked List:");
                    print(head);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input. Please enter 0, 1, or 2.");
            }
        }
        input.close();
    }
    
}