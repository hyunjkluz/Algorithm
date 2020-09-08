package com.lecture.lab2;

public class Main_CC {
    static int N = 8;
    static int[][] maze = new int[N][N];

    final static int BACKGROUND_COLOR = 0;
    final static int IMAGE_COLOR = 1;
    final static int ALREADY_COUNTED = 2;

    public static void main(String[] args) {
        printMaze();
        countCells(5, 3);
        printMaze();
    }
    public static int countCells(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N || maze[x][y] != IMAGE_COLOR) {
            return 0;
        } else {
            int count = 1;
            maze[x][y] = ALREADY_COUNTED;
            count += countCells(x, y-1) + countCells(x, y+1) + countCells(x-1, y) + countCells(x+1, y);
            return count;
        }
    }

    public static void printMaze() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }
}
