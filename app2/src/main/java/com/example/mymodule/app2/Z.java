package com.example.mymodule.app2;

/**
 * Created by zf on 2015/3/12.
 */
public class Z {

    public static class NoteResult {
        int min = 0;
        int max = 0;
        int minTuo = 0;

        @Override
        public String toString() {
            return "NoteResult{" +
                    "min=" + min +
                    ", max=" + max +
                    ", minTuo=" + minTuo +
                    '}';
        }

        public int getMinTuo() {
            return minTuo;
        }

        public void setMinTuo(int minTuo) {
            this.minTuo = minTuo;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }


    static int Combination(int Am, int An) {
        int li = 1;
        int j = 1;
        for (int i = Am - An + 1; i <= Am; i++) {
            li = li * i / (j++);
        }
        return li;
    }

    static int Max(int a, int b) {
        return a > b ? a : b;
    }

    static int Min(int a, int b) {
        return a <= b ? a : b;
    }

    public static NoteResult Cal(int A, int B, int C, int D, int E) throws Exception {
        if (B > A || C > A || (D + E) > A || D >= C || (D + E) < C) {
            throw new Exception("Error Param");
        }

        NoteResult result = new NoteResult();

		/*
        总a  开b 选c  胆d  拖e

		当c<=b是

		下限=C(c-d,  max(c-d,  b+e-a));
		上限=C(c-d,  min(b-d,  e));

		当c>b时
		下限=C(c-d-min(e, b),  e-min(e, b));
		上限=C(c-d,  e-b+min(b, d));
		*/

        if (C <= B) {
            int x = C - D;
            int y = Max(C - D, B + E - A);
            result.minTuo = y;
            result.min = Combination(y, x);

            y = Min(B - D, E);
            result.max = Combination(y, x);
        } else {
            int x = C - D - Min(E, B);
            int y = E - Min(E, B);
            result.minTuo = y;
            result.min = Combination(y, x);

            x = C - D - (B - Min(B, D));
            y = E - (B - Min(B, D));

            result.max = Combination(y, x);
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            int A = 20;
            int B = 5;
            int C = 3;
            int D = 0;
            int E = 3;

            NoteResult result = Cal(A, B, C, D, E);

            System.out.println(result.toString());


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
