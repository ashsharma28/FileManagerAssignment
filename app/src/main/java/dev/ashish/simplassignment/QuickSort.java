package dev.ashish.simplassignment;


import android.support.v7.widget.RecyclerView;

class QuickSort {
    static RecyclerView recy;


    public Comparable[] quickSort(Comparable[] A, RecyclerView mRecyclerView) {
        recy = mRecyclerView;
        sort(A, A.length - 1, 0);
        return A;
    }

    private void sort(Comparable[] A, int hi, int low) {
        if (hi <= low)
            return;

        int j = doPartition(A, hi, low);
        sort(A, j - 1, low);

        sort(A, hi, j + 1);
    }

    private int doPartition(Comparable[] A, int hi, int low) {

        int i = low, j = hi + 1;

        while (true) {
            while (A[++i].compareTo(A[low]) <= 0)
                if (i == hi) break;

            while (A[low].compareTo(A[--j]) <= 0)
                if (j == low) break;

            if (i >= j) break;
            swap(A, i, j);
        }

        swap(A, j, low);
        return j;
    }

    public Comparable[] swap(Comparable[] A, int i, int min) {
        Comparable temp;

        if (A[i] != A[min]) {
            temp = A[i];
            A[i] = A[min];
            A[min] = temp;
//            System.out.println("Swapped " + i + "-" + min);

            if (i > min) {
                recy.getAdapter().notifyItemMoved(i, min);
                recy.getAdapter().notifyItemMoved(min + 1, i);
            } else {
                recy.getAdapter().notifyItemMoved(i, min);
                recy.getAdapter().notifyItemMoved(min - 1, i);
            }
        }

        return A;

    }


}