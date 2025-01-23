import java.util.*;

class Solution {
    static class Privacy {
        String duration;
        char c;
        
        public Privacy(String duration, char c){
            this.duration = duration;
            this.c = c;
        }
    }
    static Map<Character, Integer> termMap;
    static List<Privacy> privacyList;
    static int termCnt;
    static int privacyCnt;
    static String today;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ret = new ArrayList<>();
        this.termMap = new HashMap<>();
        this.privacyList = new ArrayList<>();
        this.termCnt = terms.length;
        this.privacyCnt = privacies.length;
        
        this.today = today;
        
        for(int i=0;i<termCnt;i++){
            String[] termArr = terms[i].split(" ");
            
            char c = termArr[0].charAt(0);
            int term = Integer.parseInt(termArr[1]);
            
            termMap.put(c, term);
        }
        
        for(int i=0;i<privacyCnt;i++){
            String[] privacyArr = privacies[i].split(" ");
            String duration = privacyArr[0];
            
            char c = privacyArr[1].charAt(0);
            
            privacyList.add(new Privacy(duration, c));
        }
        

        for(int i=0;i<privacyCnt;i++){
            Privacy p = privacyList.get(i);
            int term = termMap.get(p.c);
            String[] durationArr = p.duration.split("\\.");
            System.out.println(p.duration);
            int year = Integer.parseInt(durationArr[0]);
            int month = Integer.parseInt(durationArr[1]);
            int date = Integer.parseInt(durationArr[2]);
            month += term;
            int m = month % 12 == 0 ? 12 : month % 12;
            int n = month % 12 == 0 ? (month / 12) - 1 : month / 12;
            year += n;
            month = m;
            
            String expiredDate = String.valueOf(year) + ".";
            expiredDate += month < 10 ? ("0" + String.valueOf(month)) : String.valueOf(month);
            expiredDate += ".";
            expiredDate += date < 10 ? ("0" + String.valueOf(date)) : String.valueOf(date);
            
            if(today.compareTo(expiredDate) >= 0){
                ret.add(i + 1);
            }
        }
        
        int arrCnt = ret.size();
        int arr[] = new int[arrCnt];
        for(int i=0;i<arrCnt;i++){
            arr[i] = ret.get(i);
        }
        
        return arr;
    }
}