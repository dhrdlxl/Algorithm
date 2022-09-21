class Solution {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = 0;
        
        answer = move(board, aloc, bloc).distance;
        
        return answer;
    }
    
    private Result move(int[][] board, int[] loc1, int[] loc2) {
        // [종료조건 1] 더 이상 이동할 곳이 없는 경우
        if (isFinished(board, loc1)) {
            return new Result(false, 0);
        }
        // [종료조건 2] A, B의 좌표가 같은 경우
        if (loc1[0] == loc2[0] && loc1[1] == loc2[1]) {
            return new Result(true, 1);
        }
        
        boolean canWin = false;
        int winDistance = 5 * 5 + 1;
        int loseDistance = 0;
        for (int[] dir : directions) {
            int[] nextLoc = {loc1[0] + dir[0], loc1[1] + dir[1]};
            if (inRange(board.length, board[0].length, nextLoc) 
                && board[nextLoc[0]][nextLoc[1]] == 1) {
                board[loc1[0]][loc1[1]] = 0;
                Result res = move(board, loc2, nextLoc);
                board[loc1[0]][loc1[1]] = 1;
                
                if (res.result == false) {
                    canWin = true;
                    winDistance = Math.min(winDistance, res.distance);
                } else {
                    loseDistance = Math.max(loseDistance, res.distance);
                }
            }
        }
        
        return canWin == true ? 
            new Result(true, winDistance + 1) : 
            new Result(false, loseDistance + 1);
    }
    
    private boolean isFinished(int[][] board, int[] loc) {
        for (int[] dir : directions) {
            int[] nextLoc = {loc[0] + dir[0], loc[1] + dir[1]};
            if (inRange(board.length, board[0].length, nextLoc) 
                && board[nextLoc[0]][nextLoc[1]] == 1) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean inRange(int R, int C, int[] loc) {
        if (loc[0] >= 0 && loc[1] >= 0 && loc[0] < R && loc[1] < C)
            return true;
        return false;
    }
    
    class Result {
        public boolean result; //true: win, false: lose
        public int distance;
        
        public Result() { }
        
        public Result(boolean result, int distance) {
            this.result = result;
            this.distance = distance;
        }
    }
}