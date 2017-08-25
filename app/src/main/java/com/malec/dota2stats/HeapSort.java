package com.malec.dota2stats;

public class HeapSort
{
    public static void reheap(int a[], int length, int i) throws Exception
    {
        boolean done = false;

        int T = a[i];
        int parent = i;
        int child = 2 * (i + 1) - 1;

        while ((child < length) && (!done))
        {
            if (child < length - 1)
            {
                if (a[child] >= a[child + 1]) child += 1;
            }

            if (T < a[child])
            {
                done = true;
            } else
            {
                a[parent] = a[child];
                parent = child;
                child = 2 * (parent + 1) - 1;
            }
        }

        a[parent] = T;
    }

    public static void invreheap(int a[], int length, int i) throws Exception
    {
        boolean done = false;

        int T = a[length - 1 - i];
        int parent = i;
        int child = 2 * (i + 1) - 1;

        while ((child < length) && (!done))
        {
            if (child < length - 1)
            {
                if (a[length - 1 - child] <= a[length - 1 - (child + 1)])
                {
                    child += 1;
                }
            }

            if (T > a[length - 1 - child])
            {
                done = true;

            } else
            {
                a[length - 1 - parent] = a[length - 1 - child];
                parent = child;
                child = 2 * (parent + 1) - 1;
            }
        }
        a[length - 1 - parent] = T;
    }

    public static void sort(int a[]) throws Exception
    {
        for (int i = a.length - 1; i >= 0; i--)
            reheap(a, a.length, i);

        for (int i = a.length - 1; i >= 0; i--)
            invreheap(a, a.length, i);

        for (int j = 1; j < a.length; j++)
        {
            int T = a[j];
            int i = j - 1;
            while (i >= 0 && a[i] > T)
            {
                a[i + 1] = a[i];
                i -= 1;
            }
            a[i + 1] = T;
        }
    }
}
