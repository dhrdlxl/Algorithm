import java.util.*;

class Solution {
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (String conditions : info) {
            String[] condition = conditions.split(" ");
            
            makeSentence(0, condition, "");
        }
        
        for (String sentence : map.keySet()) {
            Collections.sort(map.get(sentence));
        }
            
        for (int t = 0; t < query.length; t++) {
            String[] condition = query[t].replaceAll(" and ", "").split(" ");
            String sentence = condition[0];
            int score = Integer.parseInt(condition[1]);
            
            if (!map.containsKey(sentence)) {
                answer[t] = 0;
            } else {
                int start = getStartIndex(map.get(sentence), score);
                answer[t] = map.get(sentence).size() - start;
            }
        }
        
        return answer;
    }
    
    public void makeSentence(int step, String[] condition, String sentence) {
        if (step == 4) {
            int score = Integer.parseInt(condition[4]);
            map.putIfAbsent(sentence.toString(), new ArrayList<>());
            map.get(sentence.toString()).add(score);
            return;
        }
        //1. -
        makeSentence(step + 1, condition, sentence + "-");
        
        //2. word
        makeSentence(step + 1, condition, sentence + condition[step]);
    }
    
    //BinearySearch(lowerbound)
    public int getStartIndex(ArrayList<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
}