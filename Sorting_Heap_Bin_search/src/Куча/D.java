package Куча;

import java.util.Scanner;

public class D {
    static long[] array = new long[100000];
    static int length = 0;

    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            long j =in.nextLong();
            if (j == 1) Heap_Extract();
            else {
                array[length]=(in.nextLong());
                length++;
                Build_Max_Heap(length-1);
            }
        }
    }

    static void Build_Max_Heap(int i) {
        if(i<=0)return;
        if(array[(i-1)/2]<array[i]){
            long A = array[i];
            array[i] = array[(i-1)/2];
            array[(i-1)/2] = A;
        }
        Build_Max_Heap((i-1)/2);
    }

    static void Heap_Extract() {
        System.out.println(array[0]);
        array[0] = array[length-1];
        length--;
        Max_Heapify(0);
    }

    static void Max_Heapify(int i) {
        int l = (2 * i + 1);
        int r = (2 * i + 2);
        int largest;
        if (l < length && array[l] > array[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < length && array[r] > array[largest]) {
            largest = r;
        }
        if (largest != i) {
            long A = array[i];
            array[i] = array[largest];
            array[largest] = A;
            Max_Heapify(largest);
        }
    }
}
