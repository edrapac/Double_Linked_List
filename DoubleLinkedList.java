package doublelinkedlist;
/*
easy as cake, exact same structure as a linked list but now easier due to the fact that the nodes in a doubley-
list contain pointers to not only the next node but also the previous adjacent node making methods used to acces the
node much easier to implement 10/19/17
 */

public class DoubleLinkedList { //keep same as with single linked list, class should be all caps when same name as package
    public Node head = null;
    public Node tail = null;
    public Node current = null;

    class Node{ //first need to re organize to include next and previous pointers correctly
        Node previous;
        Node next;
        Object data;
        Node(Node previous, Object data, Node next){ //the next part keeps track of what is next in line
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }
    public String toString(){
        return "The current node is " + current.data+ " the current tail is "+tail.data;
    }
    public void prepend(Object data){
        if(head==null){
            head = new Node(null,data,null); //the next node is null
            current = head;
            //System.out.println(current.data); //wtf so its recognizing this

        }
        else { //if you are adding a head to an already existing list
            current = new Node(null,data,head); //now the next node is the previous "head" node
            head=current;
            current = head;

        }
    }
    public void append(Object data){
        if(head.next == null){
            tail = new Node(head,data,null);
            head.next = tail; //need to re update head because it's "next" is no longer null, its now tail
            current = tail;
        }
        else {
            tail.next = new Node(tail,data,null);
            tail = tail.next;
            current=tail;
        }
    }

    public int indexer(){
        Node counter = null;
        counter = head;
        int i = 1;
        if(counter.data!=null){
            while(counter.next!=null){
                i++;
                counter = counter.next;
            }
        }
        else{
            System.out.println("This is an empty linked list right now");
        }
        return i;
    }

    public Object indexfinder(int j){
        Node counter = null;
        counter = head;
        int i = 1;
        if(counter.data!=null){
            while(i!=j){
                i++;
                counter = counter.next;
            }
        }
        else{
            System.out.println("This is an empty linked list right now");
        }
        return counter.data;
    }

    public void indexadder(int j,Object data){
        Node counter = null;
        counter = head;
        int i = 0;
        if(counter.data!=null){
            while(i!=j){
                i++;
                counter = counter.next;
            }
        }
        else{
            System.out.println("This is an empty linked list right now");
        }
        if (i==j){
            current = counter;
            Node added = new Node(current,data,current.next);
            current.next = added;
        }
    }

    public void insert(Object data){
        if(current!=null){
            Node successor = new Node(current.previous,data,current.next);
            current.next = successor;
            current = successor;
        }
        else{
            head = tail;
            tail = new Node(null,data,null);
            current = head;
        }

    }
    public void crawlList(){ //crawls list from start using COPY VARIABLES
        if(head.next!=null){
            Node counter;
            counter = current;
            Node temphead = head;
            counter = temphead;
            while(counter!=null){
                System.out.println(counter.data + " ->");
                counter = counter.next;
            }
        }
        else{
            System.out.print(head.data + " this is a 1 length list");
        }
    }
    public Node findSecondToLast(){ // used in tandem with the removeTail() method, only way to "go back" in reference
        Node counter;
        counter = current;
        Node temphead = head;
        counter = temphead;
        if (counter.next ==tail){
            return counter;
        }
        else{
            while(counter.next!=tail){
                counter = counter.next;
            }
        }
        System.out.println("the second to last is " + counter.data);
        return counter;
    }

    public void removeTail(){ //also pretty self explanatory
        Node counter;
        counter = current;
        Node temphead = head;
        counter = temphead;
        if(counter.next==tail){
            tail = counter;
        }
        else{
            while(counter.next!=tail){
                counter = counter.next;
            }
            tail = counter;
        }
        System.out.println("The current tail is now ------------------------->"+tail.data);
    }

    public void removehead(){ //pretty self explanatory
        head = head.next;
        current = head;
    }
    public static void main(String[] args) { //finshed product 10/19/17 no further method improvement needed
        DoubleLinkedList link  = new DoubleLinkedList(); //Nodes data is now more ambiguous Object type
        link.prepend("Eggs");
        link.append("Cheese");
        link.prepend("This should now come before eggs");
        link.prepend("this should come before everything");
        link.insert("where will this go?"); //comes after most recent operation, not exactly a very precise method
        link.crawlList();
        System.out.println("---->" + link.toString()+"<-----"); //use to determine what current node we are on
        link.findSecondToLast();
        link.removeTail();
        System.out.println("The node in 3rd place is: " + link.indexfinder(3));
        link.indexadder(3,"this should now come after eggs");
        link.crawlList();
        System.out.println("This linked list currently has: " + link.indexer()+ " nodes");

    }
}
