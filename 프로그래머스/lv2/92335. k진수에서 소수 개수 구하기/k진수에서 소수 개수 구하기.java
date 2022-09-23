class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        //1. 진수 표현
        StringBuilder makeNum = new StringBuilder();
        while (n > 0) {
            makeNum.append(n % k);
            n /= k;
        }
        String num = makeNum.reverse().toString();
        
        answer = countPrime(num);
        
        return answer;
    }
    
    private int countPrime(String n) {
        String[] nums = n.split("0+");
        int count = 0;
        
        for (String numString : nums) {
            long num = Long.parseLong(numString);
            
            if (isPrime(num)) {
                count++;
            }
        }
            
        return count;
    }
    
    //2. 소수 판별
    private boolean isPrime(long num) {
        if (num < 2)
            return false;
        
        for (long i = 2; (i * i) <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}