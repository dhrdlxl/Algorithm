import java.util.*;
import java.util.Map.Entry;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, Integer> idIndex = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            idIndex.put(id_list[i], i);
        }
        
        HashMap<String, HashSet<String>> reportList = new HashMap<>();//신고 당한 사람: 신고 한 사람
        
        for (String reportInfo : report) {
            String[] reports = reportInfo.split(" ");//0: 신고한 사람, 1: 신고 당한 사람
            reportList.putIfAbsent(reports[1], new HashSet<>());
            reportList.get(reports[1]).add(reports[0]);
        }
        
        for (Entry<String, HashSet<String>> entry : reportList.entrySet()) {
            HashSet<String> getMailUser = entry.getValue();
            
            if (getMailUser.size() >= k) {//정지
                for (String userId : getMailUser) {
                    answer[idIndex.get(userId)]++;
                }
            }
            
        }
        
        return answer;
    }
}