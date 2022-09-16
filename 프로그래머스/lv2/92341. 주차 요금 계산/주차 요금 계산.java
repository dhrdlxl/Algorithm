import java.util.*;
import java.util.Map.Entry;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        TreeMap<String, ArrayList<Integer>> userRecord = new TreeMap<>();
        
        StringTokenizer st;
        for (String record : records) {
            st = new StringTokenizer(record);
            String time = st.nextToken();
            String carNumber = st.nextToken();
            
            String hour = time.substring(0, 2);
            String minute = time.substring(3, 5);

            int usingTime = Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
            
            if (userRecord.containsKey(carNumber) == false) {
                userRecord.put(carNumber, new ArrayList<Integer>());
            }
            userRecord.get(carNumber).add(usingTime);
        }
        
        answer = new int[userRecord.size()];
        int carIndex = 0;
        for (Entry<String, ArrayList<Integer>> entry : userRecord.entrySet()) {
            ArrayList<Integer> usingRecords = entry.getValue();
            
            if (usingRecords.size() % 2 != 0) {
                usingRecords.add(23 * 60 + 59); // 출차 시간이 없는 경우.
            }
            
            
            int usingTime = 0;
            for (int i = 0; i < usingRecords.size(); i += 2) {
                usingTime += usingRecords.get(i + 1) - usingRecords.get(i);
            }
            
            int fee = 0;
            usingTime -= basicTime;
            fee += basicFee;
            
            if (usingTime > 0) {
                fee += (usingTime / unitTime * unitFee);
                if (usingTime % unitTime > 0) {
                    fee += unitFee;
                }
            }
            
            answer[carIndex++] = fee;
        }
        
        return answer;
    }
}