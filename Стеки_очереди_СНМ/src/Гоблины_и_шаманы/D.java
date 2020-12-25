package Гоблины_и_шаманы;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class D {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        List<Integer> a = new LinkedList<>();
        List<Integer> b = new LinkedList<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String c = in.next();
            if ("+".equals(c)) {
                a.add(0, in.nextInt());
            }
            if ("*".equals(c)) {
                if (a.size() == b.size())
                    b.add(0, in.nextInt());
                else
                    a.add(in.nextInt());
            }
            if ("-".equals(c)) {
                System.out.println(b.remove(b.size() - 1));
            }
            if (a.size() != 0 && a.size() > b.size())
                b.add(0, a.remove(a.size() - 1));
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
