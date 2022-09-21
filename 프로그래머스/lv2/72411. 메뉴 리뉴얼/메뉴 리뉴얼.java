import java.util.*;
import java.util.Map.Entry;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        
        HashMap<String, Integer> map = new HashMap<>(); // course, 주문 수
        
        for (String order : orders) {
            //1. order 정렬
            char[] sortedOrder = order.toCharArray();
            Arrays.sort(sortedOrder);
            
            //2. order로 만들 수 있는 모든 조합 생성
            for (int bit = 1; bit < (1 << order.length()); bit++) {
                StringBuilder makeCourse = new StringBuilder();
                
                for(int i = 0; i < order.length(); i++) {
                    if (((1 << i) & bit) > 0) {
                        makeCourse.append(sortedOrder[i]);
                    }
                }
                
                String newCourse = makeCourse.toString();
                if (!map.containsKey(newCourse)) {
                    map.put(newCourse, 0);
                }
                
                map.put(newCourse, map.get(newCourse) + 1);
            }
        }
        
        //3. 구성해야 할 메뉴 개수 set
        HashSet<Integer> courseSet = new HashSet<>();
        for (int courseCount : course) {
            courseSet.add(courseCount);
        }
        
        ArrayList<CourseInfo> courseList = new ArrayList<>();
        
        for (Entry<String, Integer> entry : map.entrySet()) {
            String courseStructure = entry.getKey();
            if (entry.getValue() <= 1)
                continue;
            if (courseSet.contains(courseStructure.length())) {
                courseList.add(new CourseInfo(courseStructure, entry.getValue()));
            }
        }
        
        Collections.sort(courseList);
        int[] maxCount = new int[11];
        ArrayList<String> answers = new ArrayList<>();
        
        for (CourseInfo courseInfo : courseList) {
            String courseName = courseInfo.course;
            int orderCount = courseInfo.orderCount;
            
            if (maxCount[courseName.length()] <= orderCount) {
                maxCount[courseName.length()] = orderCount;
                answers.add(courseName);
            }
        }
        
        Collections.sort(answers);
        answer = answers.toArray(new String[answers.size()]);

        return answer;
    }
    
    class CourseInfo implements Comparable<CourseInfo>{
        public String course;
        public int orderCount;
        
        public CourseInfo(){ }
        
        public CourseInfo(String course, int orderCount) {
            this.course = course;
            this.orderCount = orderCount;
        }
        
        @Override
        public int compareTo(CourseInfo courseInfo) {
            return courseInfo.orderCount - this.orderCount;
        }
    }
}