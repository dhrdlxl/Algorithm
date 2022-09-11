import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, Integer> answerMap = new HashMap<>();
        HashMap<String, HashSet<String>> reportChart = new HashMap<>(); 
        
        // 0. answerMap 초기화 
        for (String id : id_list) {
            answerMap.put(id, 0);
        }
        
        // 1. report 정리
        StringTokenizer st;
        for (String issue : report) {
            st = new StringTokenizer(issue);
            String user = st.nextToken();
            String reportedID = st.nextToken();
            
            if (!reportChart.containsKey(reportedID)) {
                reportChart.put(reportedID, new HashSet<String>());
            }
            reportChart.get(reportedID).add(user);
        }
        
        // 2. k 이상일 경우 answerMap 값 증가
        Iterator<String> keys = reportChart.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            HashSet<String> users = reportChart.get(key);
            
            if (users.size() >= k) {
                Iterator<String> setKeys = users.iterator();
                while (setKeys.hasNext()) {
                    String reporter = setKeys.next();
                    answerMap.put(reporter, answerMap.get(reporter) + 1);
                }
            }
        }
        
        // 3. answer
        int index = 0;
        for (String id : id_list) {
            answer[index] = answerMap.get(id);
            index++;
        }
        
        
        return answer;
    }
}