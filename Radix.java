import java.util.*;

public class Radix{

  /**A method that sorts an array of integers using the radix sort algorithm
  *@param int[] data
  */
  @SuppressWarnings("unchecked")
  public static void radixsort(int[] data){
    //find the largest number
    int largest = 0;
    for(int i = 0; i < data.length; i++){
      if(Math.abs(data[i]) > largest) largest = Math.abs(data[i]);
    }
    //System.out.println("largest: " + largest);
    //find the number of digits in the largest number
    int numDigits = 0;
    while(largest > 0){
      numDigits++;
      largest = largest / 10;
    }
    //System.out.println("numDigits: " + numDigits);

    int place = 10;
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20]; //creating buckets
    for(int i = 0; i < buckets.length; i++){
      buckets[i] = new MyLinkedList<Integer>();
    }
    //first round of sorting
    for(int i = 0; i < data.length; i++){
      int temp = Math.abs(data[i] % place); //finding the digit at the ones place
      //System.out.println("temp@" + i + ": " + temp);
      if(data[i] < 0){ //negative
        buckets[9 - temp].add(data[i]);
        //System.out.println("list@" + (9-temp) + ": " + list);
      }else{ //positive
        buckets[10 + temp].add(data[i]);
        //System.out.println("list@" + (10+temp) + ": " + list);
      }
    }

    //linking the bucket lists
    MyLinkedList<Integer> first = buckets[0];
    for(int i = 1; i < buckets.length; i++){
      if(buckets[i].size() != 0){
        first.extend(buckets[i]);
      }
    }
    //System.out.println("first: " + first);
    numDigits--; //the number of digits left to check decreases

    place = place * 10; //increase the digit place
    while(numDigits != 0){
      //System.out.println("place: " + place);
      buckets = new MyLinkedList[20]; //new buckets made
      for(int i = 0; i < buckets.length; i++){
        buckets[i] = new MyLinkedList<Integer>();
      }
      while(first.hasNext()){ //iterator
        int temp = first.next(); //storage for the number
        int toAdd = Math.abs((temp % place) / (place / 10)); //find the digit
        if(temp < 0){ //negative
          buckets[9 - toAdd].add(temp);
        }else{ //positive
          buckets[10 + toAdd].add(temp);
        }
      }

      place = place * 10; //increase the digit place
      //linking the bucket lists
      MyLinkedList<Integer> newData = buckets[0];
      for(int i = 1; i < buckets.length; i++){
        if(buckets[i].size() != 0){
          newData.extend(buckets[i]);
        }
      }
      first = newData; //set the new list to first
      //System.out.println("first: " + first);
      numDigits--; //the number of digits left to check decreases
    }
    //System.out.println(first);

    int index = 0; //copying over to the original array
    while(first.hasNext()){
      data[index] = first.next();
      index++;
    }
  }

  /**A method that prints out an array
  *@param int[] data
  *@return String
  */
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

    int[] data1 = {10002, 23124, 187123178, -1721678181, -12383127, -13184728, 19327272};
    System.out.println(toString(data1));
    radixsort(data1);
    System.out.println(toString(data1));

    int[] data3 = new int[1000];
    for(int i = 999; i >= 0; i--){
      data3[999-i] = i;
    }
    System.out.println(toString(data3));
    radixsort(data3);
    System.out.println(toString(data3));
  }
}
