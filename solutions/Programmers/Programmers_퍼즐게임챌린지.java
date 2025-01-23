class Solution {
    static final int MAX = 100_000;
    static final int MIN = 1;
    static int[] diffs;
    static int[] times;
    static long limit;
    static int n;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        this.n = diffs.length;
        return binarySearch();
    }
    
    public int binarySearch() {
        int start = MIN;
        int end = MAX;
        int mid = 0;
        
        while(start <= end){
            mid = (start + end) / 2;
            
            boolean flag = checkPossibleCase(mid);
            
            if(flag){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }
        
        return start;
    }
    
    public boolean checkPossibleCase(int level){
        boolean ret = false;
        long time_sum = 0;
        int time_cur = 0;
        int time_prev = 0;
        int idx = 0;
        while(idx < n) {
            time_cur = times[idx];
            time_prev = idx > 0 ? times[idx - 1] : 0;
            
            if(diffs[idx] <= level) {
                time_sum += time_cur;
            } else{
                time_sum += (time_prev + time_cur) * (diffs[idx] - level);
                time_sum += time_cur;
            }
            
            if(time_sum > limit){
                return false;
            }
            
            idx++;
        }
        
        return true;
    }
}