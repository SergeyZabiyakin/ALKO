package Минимум_на_стеке;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) {
        InputReader in =new InputReader(System.in);
        StringBuilder sb=new StringBuilder();
        int[] b;
        int N, I = 0;
        N =in.nextInt();
        b = new int[N + 1];
        in.nextInt();
        b[0] = Integer.MAX_VALUE;
        b[1] = in.nextInt();
        I++;
        for (int i = 1; i < N; i++) {
            int j = in.nextInt();
            if (j == 1) {
                int a = in.nextInt();
                if (b[I] > a) b[I + 1] = a;
                else b[I + 1] = b[I];
                I++;
            } else if (j == 2) I--;
            else sb.append(b[I]+"\n");
        }
        System.out.println(sb);
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 100768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}