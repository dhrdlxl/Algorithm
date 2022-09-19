import java.util.*;

class Solution {
    private HashMap<Integer, ArrayList<Integer>> edgeBoard = new HashMap<>();
    private int[] info;
    private int maxSheep;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        this.info = info.clone();
        
        for (int[] edge : edges) {
            int parentNode = edge[0];
            int childNode = edge[1];
            
            if (!edgeBoard.containsKey(parentNode)) {
                edgeBoard.put(parentNode, new ArrayList<Integer>());
            }
            edgeBoard.get(parentNode).add(childNode);
        }
        
        dfs(0, 0, 0, new ArrayList<Integer>());
        
        answer = maxSheep;
        
        return answer;
    }
    
    private void dfs(int curNode, int sheep, int wolf, ArrayList<Integer> nextNodes) {   
        sheep += info[curNode] ^ 1; //xor
        wolf += info[curNode];
        
        if (sheep <= wolf)
            return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        ArrayList<Integer> newNextNodes = new ArrayList<>();
        newNextNodes.addAll(nextNodes);
        if (edgeBoard.containsKey(curNode)) {
            newNextNodes.addAll(edgeBoard.get(curNode));
        }
        
        newNextNodes.remove(Integer.valueOf(curNode));
        
        for (int nextNode : newNextNodes) {
            dfs(nextNode, sheep, wolf, newNextNodes);
        }
        
    }
}