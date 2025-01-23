import java.util.*;

class Solution {
    static int appleCnt;
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        appleCnt = score.length;
        
        // 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<appleCnt;i++){
            pq.add(score[i]);
        }
        
        // m개씩 뺄 수 있다면?
        while(pq.size() >= m){
            for(int i=0;i<m-1;i++){
                pq.poll();
            }
            
            int last = pq.poll();
            answer += (last * m);
        }
        
        return answer;
    }
}