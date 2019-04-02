import java.util.*;

public class Radix{
  public static void radixsort(int[] data){
    int place = 10;
    MyLinkedList<Integer>[] buckets = new MyLinkedList[19];
    for(int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    for(int i = 0; i < data.length; i++){
      int temp = data[i] % place;
      if(data[i] < 0){
        MyLinkedList<Integer> list = buckets[9 - temp];
        list.add(data[i]);
      }else{
        MyLinkedList<Integer> list = buckets[10 + temp];
        list.add(data[i]);
      }
    }
    MyLinkedList<Integer> newData = buckets[0];
    for(int i = 1; i < buckets.length; i++){
      if(buckets[i].size() != 0){
        newData.extend(buckets[i]);
      }
    }
    radixHelper(newData);
  }
}
