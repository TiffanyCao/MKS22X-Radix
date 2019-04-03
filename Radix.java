import java.util.*;

public class Radix{

  @SuppressWarnings("unchecked")
  public static void radixsort(int[] data){
    int largest = 0;
    for(int i = 0; i < data.length; i++){
      if(Math.abs(data[i]) > 0) largest = Math.abs(data[i]);
    }
    //System.out.println(largest);
    int numDigits = 0;
    while(largest > 0){
      numDigits++;
      largest = largest / 10;
    }
    //System.out.println(numDigits);

    int place = 10;
    MyLinkedList<Integer>[] buckets = new MyLinkedList[19];
    for(int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    for(int i = 0; i < data.length; i++){
      int temp = Math.abs(data[i] % place);
      //System.out.println("temp@" + i + ": " + temp);
      if(data[i] < 0){
        MyLinkedList<Integer> list = buckets[9 - temp];
        list.add(data[i]);
        //System.out.println("list@" + (9-temp) + ": " + list);
      }else{
        MyLinkedList<Integer> list = buckets[10 + temp];
        list.add(data[i]);
        //System.out.println("list@" + (10+temp) + ": " + list);
      }
    }
    MyLinkedList<Integer> first = buckets[0];
    for(int i = 1; i < buckets.length; i++){
      if(buckets[i].size() != 0){
        first.extend(buckets[i]);
      }
    }
    //System.out.println("first: " + first);
    numDigits--;

    place = place * 10;
    while(numDigits != 0){
      //System.out.println("place: " + place);
      buckets = new MyLinkedList[19];
      for(int i = 0; i < buckets.length; i++){
        buckets[i] = new MyLinkedList<Integer>();
      }
      for(int i = 0; i < first.size(); i++){
        int temp = Math.abs((first.get(i) % place) / (place / 10));
        //System.out.println("temp@" + i + ": " + temp);
        if(first.get(i) < 0){
          MyLinkedList<Integer> list = buckets[9 - temp];
          list.add(first.get(i));
          //System.out.println("list@" + (9-temp) + ": " + list);
        }else{
          MyLinkedList<Integer> list = buckets[10 + temp];
          list.add(first.get(i));
          //System.out.println("list@" + (10+temp) + ": " + list);
        }
      }
      place = place * 10;
      MyLinkedList<Integer> newData = buckets[0];
      for(int i = 1; i < buckets.length; i++){
        if(buckets[i].size() != 0){
          newData.extend(buckets[i]);
        }
      }
      first = newData;
      //System.out.println("first: " + first);
      numDigits--;
    }

    for(int i = 0; i < first.size(); i++){
      data[i] = first.get(i);
    }
  }

  public static String toString(int[] data){
    String result = "[";
    for(int i = 0; i < data.length; i++){
      if(i != data.length - 1) result += data[i] + ", ";
      else result += data[i] + "]";
    }
    return result;
  }

  public static void main(String[] args){
    int[] data = {0, 3, -1, -100, 38, 21, 2, -122};
    System.out.println(toString(data));
    radixsort(data);
    System.out.println(toString(data));
  }
}
