package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by jo930_000 on 2016-09-29.
 */
public class QuickSort {

    ArrayList<Integer> QuickList = new ArrayList<Integer>();
    String data;
    long start_time;
    long end_time;

    public void QuickRun() throws IOException {

        FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/IdeaProjects/Algorithm_hw03/src/hw03_00_201202143_normal_partition.txt");

        FileInputStream input = new FileInputStream("C:/Users/jo930_000/IdeaProjects/Algorithm_hw03/src/data04.txt");
        InputStreamReader reader = new InputStreamReader(input);
        StreamTokenizer token = new StreamTokenizer(reader);

        while((token.nextToken() != StreamTokenizer.TT_EOF)) {
            switch(token.ttype){
                case StreamTokenizer.TT_NUMBER :
                    QuickList.add((int) token.nval);
                    break;
            }
        }

        input.close();

        // 정렬
        start_time = System.currentTimeMillis();
        QuickSort(QuickList, 0, QuickList.size()-1);
        end_time = System.currentTimeMillis();
        System.out.println("normal_elapse time : " + (end_time - start_time) + "ms");

        data = QuickList.get(0).toString();
        output.write(data.getBytes());
        for(int index = 1; index < QuickList.size(); index++) {
            data =  "," + QuickList.get(index).toString();
            output.write(data.getBytes());
        }

        output.close();

    }

    public void RandomQuickRun() throws IOException {

        FileOutputStream output = new FileOutputStream("C:/Users/jo930_000/IdeaProjects/Algorithm_hw03/src/hw03_00_201202143_randomized_partition.txt");

        FileInputStream input = new FileInputStream("C:/Users/jo930_000/IdeaProjects/Algorithm_hw03/src/data04.txt");
        InputStreamReader reader = new InputStreamReader(input);
        StreamTokenizer token = new StreamTokenizer(reader);

        while((token.nextToken() != StreamTokenizer.TT_EOF)) {
            switch(token.ttype){
                case StreamTokenizer.TT_NUMBER :
                    QuickList.add((int) token.nval);
                    break;
            }
        }

        input.close();

        // 정렬
        start_time = System.currentTimeMillis();
        RandomizedQuickSort(QuickList, 0, QuickList.size()-1);
        end_time = System.currentTimeMillis();
        System.out.println("random_elapse time : " + (end_time - start_time) + "ms");

        data = QuickList.get(0).toString();
        output.write(data.getBytes());
        for(int index = 1; index < QuickList.size(); index++) {
            data =  "," + QuickList.get(index).toString();
            output.write(data.getBytes());
        }

        output.close();

    }

    public void QuickSort(ArrayList<Integer> arr, int start, int end) {
        int q;
        if (start < end) {
            q = partition(arr, start ,end);
            this.QuickSort(arr, start, q-1);
            this.QuickSort(arr, q+1, end);
        }
    }

    public void RandomizedQuickSort(ArrayList<Integer> arr, int start, int end) {
        int q;
        if (start < end) {
            q = randomizedPartition(arr, start ,end);
            this.RandomizedQuickSort(arr, start, q-1);
            this.RandomizedQuickSort(arr, q+1, end);
        }
    }

    public int partition(ArrayList<Integer> arr, int start, int end) {
        int x = arr.get(end);
        int i = start - 1;

        for(int j = start; j <= end-1; j++ ) {
            if(arr.get(j) <= x ) {
                i = i + 1;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        i = i + 1;
        int temp = arr.get(i);
        arr.set(i, arr.get(end));
        arr.set(end, temp);

        return i;
    }

    public int randomizedPartition(ArrayList<Integer> arr, int start, int end) {
        int pivot;
        int i =(int)(Math.random() * (end-start+1) + start);  // p~r까지의 랜덤숫자 뽑기 : (Math.random()*(최댓값-최솟값+1) +최솟값)
        int n =(int)(Math.random() * (end-start+1) + start);
        int k =(int)(Math.random() * (end-start+1) + start);

        // pivot 정하기
        pivot = pickMedian(i, n, k);

        int temp = arr.get(pivot);
        arr.set(pivot, arr.get(end));
        arr.set(end, temp);

        return partition(arr, start, end);
    }

    public int pickMedian(int i, int n ,int k) {
        int pivot;

        if(i < n) {
            if(n < k) pivot = n;
            else if(k < i) pivot = i;
            else pivot = k;
        }
        else {
            if(i < k) pivot = i;
            else if (k < n) pivot = n;
            else pivot = k;
        }

        return pivot;
    }

}
