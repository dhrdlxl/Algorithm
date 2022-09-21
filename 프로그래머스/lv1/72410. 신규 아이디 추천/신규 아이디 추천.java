import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        //step1
        new_id = new_id.toLowerCase();
        
        //step2
        new_id = new_id.replaceAll("[^a-z0-9-_.]*","");
        
        //step3
        new_id = new_id.replaceAll("(\\.){2,}+", ".");
        
        //step4
        new_id = new_id.replaceAll("^(\\.)|(\\.)$", "");
        
        //step5
        if (new_id.length() == 0)
            new_id = "a";
        
        //step6
        if(new_id.length() >= 16){
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("(\\.)$", "");
        }
            
        while(new_id.length() <= 2) {
            new_id += (new_id.charAt(new_id.length() - 1));
        }
        
        answer = new_id;
        return answer;
    }
}