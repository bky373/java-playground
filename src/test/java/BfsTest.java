import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// - https://leetcode.com/problems/number-of-islands/
public class BfsTest {
    private char[][] grid;

    @Test
    void numIslands() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
        int output = 1;
        int numOfLands = 0;
        this.grid = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numOfLands++;
                    bfs(i, j);
                }
            }
        }
        System.out.println("numOfLands = " + numOfLands);
    }

    private void bfs(int i, int j) {
        Queue<Integer> que = new LinkedList<>();
        que.add(i);
        que.add(j);
        grid[i][j] = '0';

        int[] yDir = {0, -1, 0, 1};
        int[] xDir = {1, 0, -1, 0};

        while (!que.isEmpty()) {
            int y = que.poll();
            int x = que.poll();

            for (int d = 0; d < 4; d++) {
                int ny = y + yDir[d];
                int nx = x + xDir[d];

                if (ny < 0 || ny >= grid.length || nx < 0 || nx >= grid[0].length) {
                    continue;
                }
                if (grid[ny][nx] == '1') {
                    que.add(ny);
                    que.add(nx);
                    grid[ny][nx] = '0';
                }
            }
        }
    }
}
