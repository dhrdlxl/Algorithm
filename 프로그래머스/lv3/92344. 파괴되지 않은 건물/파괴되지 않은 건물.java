class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int R = board.length;
        int C = board[0].length;
        int[][] cumulativeSum = new int[R + 1][C + 1]; //누적합 배열
        
        // 1. 전투
        for (int[] action : skill) {
            int type = action[0];
            int r1 = action[1];
            int c1 = action[2];
            int r2 = action[3];
            int c2 = action[4];
            int degree = action[5];
            int sign = 1;
            if (type == 1) { // attack
                sign = -1;
            }
            
            //## 누적합을 위한 배열 채우기
            cumulativeSum[r1][c1] += degree * sign;
            cumulativeSum[r2 + 1][c2 + 1] += degree * sign;
            cumulativeSum[r1][c2 + 1] += degree * sign * -1;
            cumulativeSum[r2 + 1][c1] += degree * sign * -1;
        }
        
        // 2. 누적합 배열 변환
        for (int row = 0; row < R + 1; row++) {
            int sum = 0;
            for (int col = 0; col < C + 1; col++) {
                cumulativeSum[row][col] += sum;
                sum = cumulativeSum[row][col];
            }
        }
        
        for (int col = 0; col < C + 1; col++) {
            int sum = 0;
            for (int row = 0; row < R + 1; row++) {
                cumulativeSum[row][col] += sum;
                sum = cumulativeSum[row][col];
            }
        }
        
        // 3. 결과 확인
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (board[row][col] + cumulativeSum[row][col] >= 1) {
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}