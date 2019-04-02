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
    data = radixHelper(newData);
  }

  public static int[] radixHelper(MyLinkedList<Integer> data){
    int done = 2;
    int place = 100;
    while(done != 0){
      MyLinkedList<Integer>[] buckets = new MyLinkedList[19];
      for(int i = 0; i < buckets.length; i++){
        buckets[i] = new MyLinkedList<Integer>();
      }
      for(int i = 0; i < data.size(); i++){
        Integer temp = (data.get(i) % place) % (place / 10);
        if(temp < 0){
          MyLinkedList<Integer> list = buckets[9 - temp];
          list.add(temp);
        }else{
          MyLinkedList<Integer> list = buckets[10 + temp];
          list.add(temp);
        }
      }
      place = place * 10;
      if(done == 1){
        done--;
      }else{
        boolean check = true;
        for(int i = 0; i < data.size(); i++){
          if((data.get(i) % place) != data.get(i)){
            check = false;
          }
        }
        if(check){
          done--;
        }
        MyLinkedList<Integer> newData = buckets[0];
        for(int i = 1; i < buckets.length; i++){
          if(buckets[i].size() != 0){
            newData.extend(buckets[i]);
          }
        }
        data = newData;
      }
    }
    int[] result = new int[data.size()];
    for(int i = 0; i < data.size(); i++){
      result[i] = data.get(i);
    }
    return result;
  }
}
