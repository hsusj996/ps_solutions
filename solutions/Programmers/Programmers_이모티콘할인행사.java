import java.util.*;

class Solution {
    static int[][] users;
    static int userCnt;
    static int[] emoticons;
    static int emoticonCnt;
    static Result max = new Result(0, 0);
    static class Result implements Comparable<Result>{
        int userCnt;
        int amount;
        
        public Result(int userCnt, int amount){
            this.userCnt = userCnt;
            this.amount = amount;
        }
        
        @Override
        public int compareTo(Result r){
            if(this.userCnt > r.userCnt){
                return 1;
            } else if(this.userCnt < r.userCnt){
                return -1;
            } else{
                if(this.amount > r.amount){
                    return 1;
                } else if(this.amount < r.amount){
                    return -1;
                } else{
                    return 0;
                }
            }
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.userCnt = users.length;
        this.emoticons = emoticons;
        this.emoticonCnt = emoticons.length;
        
        int[] discount = new int[emoticonCnt];
        dfs(discount, 0);
        
        int[] ret = new int[2];
        ret[0] = max.userCnt;
        ret[1] = max.amount;
        return ret;
    }
    
    public void dfs(int[] discount, int depth){
        if(depth == emoticonCnt){
            Result result = calResult(discount);
            
            if(max.compareTo(result) < 0){
                max = result;
            }
            
            return;
        }
        
        for(int i=1;i<=4;i++){
            int discountAmount = i * 10;
            
            discount[depth] = discountAmount;
            dfs(discount, depth + 1);
        }
    }
    
    public Result calResult(int[] discount){
        int cnt = 0;
        int amount = 0;
        
        int[] emoticonCost = new int[emoticonCnt];
        for(int i=0;i<emoticonCnt;i++){
            emoticonCost[i] = emoticons[i] * (100 - discount[i]) / 100;
        }
        
        for(int i=0;i<userCnt;i++){
            int userAmount = 0;
            for(int j=0;j<emoticonCnt;j++){
                if(users[i][0] <= discount[j]){
                    userAmount += emoticonCost[j];
                }
            }
            
            if(userAmount >= users[i][1]){
                cnt++;
            } else{
                amount += userAmount;
            }
            
        }
        
        return new Result(cnt, amount);
    }
}