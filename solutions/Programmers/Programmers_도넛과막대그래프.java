import java.util.*;

class Solution {
    static List<Integer>[] edgeList = new ArrayList[1_000_001];
    static int[] inCnt = new int[1_000_001];
    static int[] outCnt = new int[1_000_001];
    static int edgeCnt;
    static int[] answer = new int[4];
    
    
    public int[] solution(int[][] edges) {
        for(int i=0;i<=1_000_000;i++){
            edgeList[i] = new ArrayList<>();
        }
        edgeCnt = edges.length;
        
        for(int i=0;i<edgeCnt;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            
            edgeList[a].add(b);
            inCnt[b]++;
            outCnt[a]++;
        }
        
        int centerNode = findCenter();
        // System.out.println("centerNode : " + centerNode);
        
        answer[0] = centerNode;
        
        for(Integer start: edgeList[centerNode]){
            inCnt[start]--;
            checkGraph(start);
        }
        
        return answer;
    }
    
    public int findCenter(){
        for(int i=1;i<=1_000_000;i++){
            if(inCnt[i] == 0 && outCnt[i] > 1){
                return i;
            }
        }
        
        System.out.println("no center");
        return 0;
    }
    
    public void checkGraph(int start){
        if(inCnt[start] == 0 && outCnt[start] == 0){
            answer[2]++;
            return;
        }
        
        if(inCnt[start] == 1 && outCnt[start] == 0){
            answer[2]++;
            return;
        }
        
        if(inCnt[start] > 1 && outCnt[start] > 1){
            answer[3]++;
            return;
        }
        
        int cur = start;
        
        while(true){
            if(outCnt[cur] > 1){
                answer[3]++;
                return;
            }
            
            if(outCnt[cur] == 0){
                answer[2]++;
                return;
            }
            
            int next = edgeList[cur].get(0);
            
            if(next == start){
                answer[1]++;
                return;
            }
            
            cur = next;
        }
    }
}