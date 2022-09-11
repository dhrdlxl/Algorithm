import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        // 1. k진수 변경
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append((char)('0' + n % k));
            n /= k;
        }
        
        String kDigit = sb.reverse().toString();
        
        
        // 2. 0을 기준으로 소수 판단
        StringTokenizer st = new StringTokenizer(kDigit, "0");
        while (st.hasMoreTokens()) {
            long num = Long.parseLong(st.nextToken());
            if (isPrime(num)) {
                answer++;
            }
        }
        
        
        return answer;
    }
    
    private boolean isPrime(long num) {
        if (num < 2)
            return false;
        
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        
        return true;
    }
}