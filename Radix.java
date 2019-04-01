import java.util.*;

public class Radix{
  public static void radixsort(int[] data){
    boolean done = false;
    int place = 10;
    while(!done){
      MyLinkedList<MyLinkedList<Integer>> buckets = new MyLinkedList<MyLinkedList<Integer>>();
      for(int i = 0; i < data.length; i++){
        int temp = data[i] % place;
        if(data[i] < 0){
          MyLinkedList<Integer> list = buckets.getNode(i);
          list.add(data[i]);
        }else{
          buckets[10 + temp] = data[i];
        }
      }
    }
  }
}
