import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class RadixSort {
    public static void main(String[] args) {

        // List of unsorted numbers
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713));

        // Calculating the maximum number of digits
        int maxNoOfDigits = noDigits(maxNumber(list));

        // Inserting 0 in initial list to have the same number of digits
        ArrayDeque<String> listWithZero = insertZero(list, maxNoOfDigits);

        // Printing list for test purposes
        print(listWithZero);

        // Printing result
        print(radixSort(listWithZero, maxNoOfDigits));
    }


    public static ArrayDeque<String> radixSort(ArrayDeque<String> listWithZero, int maxNoOfDigits) {
        ArrayDeque<String> zero = new ArrayDeque<>();
        ArrayDeque<String> one = new ArrayDeque<>();
        ArrayDeque<String> two = new ArrayDeque<>();
        ArrayDeque<String> three = new ArrayDeque<>();
        ArrayDeque<String> four = new ArrayDeque<>();
        ArrayDeque<String> five = new ArrayDeque<>();
        ArrayDeque<String> six = new ArrayDeque<>();
        ArrayDeque<String> seven = new ArrayDeque<>();
        ArrayDeque<String> eight = new ArrayDeque<>();
        ArrayDeque<String> nine = new ArrayDeque<>();
        for (int i = maxNoOfDigits - 1; i >= 0; i--) {
            Iterator<String> it = listWithZero.iterator();
            String no;
            while (!listWithZero.isEmpty()) {
                no = listWithZero.poll();
                char c = no.charAt(i);
                switch (c) {
                    case '0' -> zero.add(no);
                    case '1' -> one.add(no);
                    case '2' -> two.offer(no);
                    case '3' -> three.offer(no);
                    case '4' -> four.offer(no);
                    case '5' -> five.offer(no);
                    case '6' -> six.offer(no);
                    case '7' -> seven.offer(no);
                    case '8' -> eight.offer(no);
                    case '9' -> nine.offer(no);
                }
            }
            addBack(zero, listWithZero);
            addBack(one, listWithZero);
            addBack(two, listWithZero);
            addBack(three, listWithZero);
            addBack(four, listWithZero);
            addBack(five, listWithZero);
            addBack(six, listWithZero);
            addBack(seven, listWithZero);
            addBack(eight, listWithZero);
            addBack(nine, listWithZero);
        }
        return listWithZero;
    }

    public static <T> void print(ArrayDeque<T> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    public static void addBack(ArrayDeque<String> digitList, ArrayDeque<String> listWithZero) {
        while (!digitList.isEmpty()) {
            listWithZero.offer(digitList.poll());
        }
    }

    public static ArrayDeque<String> insertZero(ArrayList<Integer> arr, int maxNoOfDigits) {
        ArrayDeque<String> listWithZero = new ArrayDeque<>();
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder newArrString = new StringBuilder();
            if (maxNoOfDigits != noDigits(arr.get(i))) {
                for (int j = 0; j < maxNoOfDigits - noDigits(arr.get(i)); j++) {
                    newArrString.append("0");
                }
            }
            listWithZero.offer(String.valueOf(newArrString.append(arr.get(i).toString())));
        }
        return listWithZero;
    }

    public static int noDigits(int maxNumber) {
        int noDigits = 0;
        while (maxNumber != 0) {
            maxNumber = maxNumber / 10;
            noDigits++;
        }
        return noDigits;
    }

    public static int maxNumber(ArrayList<Integer> arr) {
        int maxNumber = 0;
        for (Integer integer : arr) {
            if (integer >= maxNumber) {
                maxNumber = integer;
            }
        }
        return maxNumber;
    }
}

