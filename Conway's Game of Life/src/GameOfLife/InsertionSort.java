package GameOfLife;

import java.util.ArrayList;

public class InsertionSort {


        public void sort(ArrayList<Integer> rows, ArrayList<java.lang.Integer> columns)
        {
            int n = rows.size();
            for (int i = 1; i < n; i++) {
                int rowKey = rows.get(i);
                int colKey = columns.get(i);
                int r = i - 1;


                while (r >= 0 && rows.get(r) > rowKey) {
                    rows.set(r+1, rows.get(r));
                    columns.set(r+1, columns.get(r));
                    r = r - 1;
                }
                rows.set(r+1, rowKey);
                columns.set(r+1, colKey);
            }
        }

        public void printArray(ArrayList<Integer> lst)
        {
            int n = lst.size();
            for (int i = 0; i < n; ++i)
                System.out.print(lst.get(i) + " ");

            System.out.println();
        }


}
