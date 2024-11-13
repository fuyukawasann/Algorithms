package P3055;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;
    static boolean foundAnswer = false;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/P3055/input.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        queue = new LinkedList<>();
        map = new char[R][C];
        dp = new int[R][C];

        Point st = null;
        for(int r = 0; r < R; r++) {
            String line = sc.next();
            for(int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if(map[r][c] == '*') {
                    queue.add(new Point(r, c, '*'));
                } else if(map[r][c] == 'S') {
                    st = new Point(r, c, 'S');
                }
            }
        }
        queue.add(st);
    }

    static class Point {
        int y;
        int x;
        char type;

        public Point(int y, int x, char type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }

        @Override
        public String toString() {
            return "[y=" + y + ", x=" + x + ", type=" + type + "]";
        }
    }

}