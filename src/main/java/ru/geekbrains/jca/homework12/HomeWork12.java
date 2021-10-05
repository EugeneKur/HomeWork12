package ru.geekbrains.jca.homework12;

public class HomeWork12 {
    private static final int size = 10000000;
    private static final int half = size / 2;

    public static void main(String[] args) {
        oneStreamArray();
        twoStreamArray();
    }


    private static void oneStreamArray () {
        float [] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr [i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println("Time one stream: " + (System.currentTimeMillis() - a));
    }

    private static void twoStreamArray () {
        float [] arr = new float[size];
        float [] arr1 = new float[half];
        float [] arr2 = new float[half];
        for (int i = 0; i < arr.length; i++) {
            arr [i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, half);
        System.arraycopy(arr, half, arr2, 0, half);

        new Thread() {
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }.start();
        System.arraycopy(arr1, 0, arr, 0, half);
        System.arraycopy(arr2, 0, arr, half, half);
        System.currentTimeMillis();
        System.out.println("Time two stream: " + (System.currentTimeMillis() - a));

    }



}
