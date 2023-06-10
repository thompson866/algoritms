package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] initialArray = generateArray(100_000);
        long timeForBubbleSorting = calculateTime(
                10,
                () -> sortBubble(Arrays.copyOf(initialArray, initialArray.length))
        );
        long timeForSelectionSorting = calculateTime(
                10,
                () -> sortSelection(Arrays.copyOf(initialArray, initialArray.length))
        );
        long timeForInsertionSorting = calculateTime(
                10,
                () -> sortInsertion(Arrays.copyOf(initialArray, initialArray.length))
        );

        System.out.println("timeForBubbleSorting = " + timeForBubbleSorting);
        System.out.println("timeForSelectionSorting = " + timeForSelectionSorting);
        System.out.println("timeForInsertionSorting = " + timeForInsertionSorting);
    }

    private static long calculateTime(int iteration, Runnable runnable) {
        long sum = 0;
        for (int i = 0; i < iteration; i++) {
            long start = System.currentTimeMillis();
            runnable.run();
            sum += System.currentTimeMillis() - start;
        }
        return sum / iteration;
    }

    private static int[] generateArray(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(0, 100);
        }
        return array;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

}