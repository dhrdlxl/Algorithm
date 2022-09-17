class Solution {
    private int maxDiff = -101;
    private int[] info;
    private int[] answer;

    public int[] solution(int n, int[] info) {
        // int[] answer = {};
        this.info = info.clone();
        
        // 전체 탐색 
        simulation(0, n, new int[11]);
        
        if (maxDiff <= 0) {
            return new int[]{-1};
        }
        
        return answer;
    }

    private void simulation(int score, int arrowCount, int[] scoreBoard) {
        if (score == 10) {
            scoreBoard[0] = arrowCount;
     
            int scoreSum = 0;
            for (int i = 0; i <= 10; i++) {
                if (info[i] < scoreBoard[i]) {
                    scoreSum += (10 - i);
                } else if (info[i] > 0 && info[i] >= scoreBoard[i]){
                    scoreSum -= (10 - i);
                }
            }
             
            if (scoreSum > maxDiff) {
                maxDiff = scoreSum;
                answer = scoreBoard.clone();
            }
            
            return;
        }
        
        
        for (int i = arrowCount; i >= 0 ; i--) {
            scoreBoard[10 - score] = i;
            simulation(score + 1, arrowCount - i, scoreBoard);
            scoreBoard[10 - score] = 0;
        }
    }
}