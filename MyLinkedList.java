public class MyLinkedList<E>{

  public class Node{
    private E data;
    private Node next, prev;

    /**A Node constructor that takes in an int value
    *@param value becomes that data value
    *The next and previous nodes are set to null
    */
    public Node(E value){
      data = value;
    }

    /**A Node constructor for single linked dodes
    *@param value becomes the data value
    *@param after becomes the next node which the variable next will reference
    */
    public Node(E value, Node after){
      data = value;
      next = after;
    }

    /**A Node constructor that takes in an int value and two nodes
    *@param value becomes that data value
    *@param before becomes the previous node which the variable prev will reference
    *@param after becomes the next node which the variable next will reference
    */
    public Node(E value, Node before, Node after){
      data = value;
      next = after;
      prev = before;
    }

    /**A method that returns the data value of a node*/
    private E get(){
      return data;
    }

    /**A method that returns the next Node
    *@return Node
    */
    public Node next(){
      return next;
    }

    /**A method that returns the previous Node
    *@return Node
    */
    public Node prev(){
      return prev;
    }

    /**A method that sets the data value of a node
    *@param value becomes the data value
    */
    public void set(E value){
      data = value;
    }

    /**A method that sets the next node
    *@param after is the node that the variable next will reference
    */
    public void setNext(Node after){
      next = after;
    }

    /*A method that sets the previous node
    *@param before is the node that the variable prev will reference
    */
    public void setPrev(Node before){
      prev = before;
    }

    /**A method that returns the data value as a toString*/
    public String toString(){
      String result = "" + data;
      return result;
    }
  }

  private int length;
  private Node start, end;
  private int current;
  private Node atCurrent;

  /**A constructor for MyLinkedList
  *the length is 0
  *the start and end nodes are empty
  */
  public void MyLinkedList(){
    length = 0;
    start = null;
    end = null;
    current = 0;
  }

  /**A method that returns length
  *@return size
  */
  public int size(){
    return length;
  }

  /**A method that empties the list
  */
  public void clear(){
    length = 0;
    start = null;
    end = null;
    current = 0;
  }

  public void clearIter(){
    current = 0;
  }

  public int getCurrent(){
    return current;
  }

  public boolean hasNext(){
    if(current < this.size()){
      return true;
    }
    return false;
  }

  public E getatCurrent(){
    return atCurrent.get();
  }

  public E next(){
    current++;
    if(current == this.size()){
      return atCurrent.get();
    }else{
      atCurrent = atCurrent.next;
      Node temp = atCurrent.prev;
      return temp.get();
    }
  }


  /**A method to get the nth node in the list
  *@param index is the index of the node wanted
  */
  private Node getNode(int index){
    Node temp = start;
    for(int i = 0; i < index; i++){ //loops through the list until it gets to the index
      temp = temp.next();
    }
    return temp;
  }

  /**A method that adds a new node to MyLinkedList
  *@param value becomes the data value of the new node added to the list
  *if the node is the first node of the list, it will become the start and end of the list
  *else, it will become the new end, with the previous end becoming the prev of the new end
  */
  public boolean add(E value){
    Node temp = new Node(value); //creates a new node
    if(length == 0){ //if this is the first node...
      start = temp; //label it as start
      atCurrent = start;
    }else{
      temp.setPrev(end); //else set the previous end node to this node's prev
      end.setNext(temp); //make the previous end node set its next to this node
    }
    end = temp; //this node becomes the new end
    length++;
    return true;
  }

  /**A method for getting the data value from a given index
  *@param index is the "index" of the Node
  *index should not be greater than or equal to the length or less than 0
  */
  public E get(int index){
    if(index >= length || index < 0){ //if the index given is out of bounds
      throw new IndexOutOfBoundsException();
    }
    Node current = getNode(index);
    return current.get(); //returns the data
  }

  /**A method to set a specific node in the linked list to an integer
  *@param index is the "index" of the Node you want to change
  *@param value is the new Integer that you want the data to be
  *index should not be greater than or equal to the length or less than 0
  */
  public E set(int index, E value){
    if(index >= length || index < 0){ //if the index given is out of bounds
      throw new IndexOutOfBoundsException();
    }
    Node current = getNode(index);
    E temp = current.get();
    current.set(value); //sets the data value to given integer
    return temp; //returns old data value
  }

  /**A method to see if a given integer is part of the lists
  *@param value is the Integer value the user is looking for
  *returns true if the Integer is in the list, false otherwise
  */
  public boolean contains(E value){
    Node current = start;
    for(int i = 0; i < length; i++){ //loops through the list
      if((current.get()).equals(value)) return true; //looks for the Integer
      if(i != length-1) current = current.next(); //if the current node is not the last node then make current the next node
    }
    return false;
  }

  /**A method threturns the first occurrence of the given integer
  *@param value is the Integer the user is looking for
  *returns the index if the Integer is in the list
  *returns -1 if the Integer isn't in the list
  */
  public int indexOf(E value){
    if(!contains(value)){ //if the integer is not in the list
      return -1;
    }
    Node current = start;
    for(int i = 0; i < length; i++){ //loops through the list
      if((current.get()).equals(value)) return i; //if the data of current is equal to the Integer
      if(i != length-1) current = current.next(); //if the current node is not the last node then make current the next node
    }
    return -1;
  }

  /**A method that adds a node to the list at anywhere within the bounds
  *@param index is the index of the new node
  *it shouldn't be greater than the length or less than 0
  *@param value is the Integer of this new Node being added to the list
  */
  public void add(int index, E value){
    if(index > length || index < 0){ //if the index is out of bounds
      throw new IndexOutOfBoundsException();
    }
    Node addition = new Node(value); //creates new node
    if(index == length){ //if this node is the first node to be added or the last node
      add(value);
    }else if(index == 0){ //if this new node becomes the first node...
      addition.setNext(start); //the new start node makes the old start node its next node
      start.setPrev(addition); //the old start node makes the new start node its previous node
      start = addition; //the new node becomes the new start node
      length++;
    }else{
      Node current = getNode(index-1); //get the node right before this index
      Node temp = current.next(); //the node previously at this index
      current.setNext(addition); //the node right before this index sets this new node as its next node
      addition.setPrev(current); //the new node sets the node in front of it as its previous node
      addition.setNext(temp); //the new node sets the node that was previously at its index as the next node
      temp.setPrev(addition); //the node previously at this index sets its previous node as this new node
      length++;
    }
  }

  /**A method to remove the Integer at a specific index
  *@param index is the given index of the node to be removed
  *index should be greater than or equal to 0 and less than the length
  */
  public E remove(int index){
    if(index >= length || index < 0){ //if the index is out of bounds
      throw new IndexOutOfBoundsException();
    }
    E temp;
    if(index == 0 && length == 1){ //if the node is the only node...
      temp = start.get();
      start = null; //set start and end to null
      end = null;
      atCurrent = null;
      current = 0;
      length--;
      return temp;
    }else if(index == 0){ //if the first node is being removed...
      temp = start.get();
      start = start.next(); //make the second node the start node
      start.setPrev(null); //make the new start node forget its previous node
      length--;
      return temp;
    }else if(index == length-1){ //if the last node is being removed...
      temp = end.get();
      end = end.prev(); //make the second to last node the end node
      end.setNext(null); //make the new end node set its next node to null
      length--;
      return temp;
    }else{
      Node current = getNode(index-1); //get the node right before this index
      Node toRemove = current.next(); //the node to be removed
      Node toLink = toRemove.next(); //the node right after the one to be removed
      current.setNext(toLink); //the node before the one removed makes the node after the one removed its next node
      toLink.setPrev(current); //the node after the one removed makes the node before the one removed its previous node
      length--;
      return toRemove.get(); //return the value removed
    }
  }

  /**A method that removes an Integer value
  *@param value is the Integer the user wants to remove
  */
  public boolean remove(E value){
    int temp = indexOf(value);
    if(temp == -1){ //if the value is not in the list
      return false;
    }
    remove(temp); //find the index of the Integer and remove it
    return true;
  }

  /**A method that combines two lists
  *@param other is the list to be added to this list
  *upon extension, the other list will be cleared, while this list will retain the nodes of both lists
  */
  public void extend(MyLinkedList<E> other){
    if(this.length == 0){ //if this list is empty...
      this.start = other.start; //set this start to the other start
      this.end = other.end; //set this end to the other end
      length = other.length; //set this length to the other length
    }else if(other.length != 0){ //if the other list isn't empty
      this.length = this.size() + other.size(); //add the two lengths together
      (this.end).setNext(other.start); //set the next node of this list's end to the other list's start node
      (other.start).setPrev(this.end); //the start of the other list sets its previous node to the end of this list
      this.end = other.end; //this list's end node becomes the other list's end node
    }
    other.length = 0; //clear out the other list;
    other.start = null; //make the other list's start and end null
    other.end = null;
    other.current = 0;
    this.current = 0;
    this.atCurrent = start;
  }

  /*A method that lists all the data values of the nodes in the linked list
  */
  public String toString(){
    String result = "[";
    for(int i = 0; i < length; i++){
      result += "" + (getNode(i)).get();
      if(i != length-1) result += ", ";
    }
    return result + "]";
  }

  public static void main(String[] args) {
    MyLinkedList<Integer> test = new MyLinkedList<Integer>();
    test.add(1);
    test.add(2);
    test.add(3);
    test.add(4);
    test.add(5);
    test.add(6);
    test.add(7);
    test.add(8);
    while(test.hasNext()){
      System.out.println(test.next());
    }
  }

}
