import java.util.*;

class Solution {
    static class Robot {
        int idx;
        int x;
        int y;
        int curPos;
        int goal;
        
        public Robot(int idx, int x, int y, int curPos, int goal){
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.curPos = curPos;
            this.goal = goal;
        }
    }
    
    static Queue<Robot> robots = new ArrayDeque<>();
    static int n;
    static int m;
    static int[][] board;
    static int[][] points;
    static int[][] routes;
    
    public int solution(int[][] points, int[][] routes) {
        this.n = points.length;
        this.m = routes.length;
        this.points = points;
        this.routes = routes;
        this.board = new int[101][101];
        
        int answer = 0;
        
        for(int i=0; i<m; i++){
            int curPoint = routes[i][0] - 1;
            robots.add(new Robot(i, points[curPoint][0], points[curPoint][1], 0, routes[i].length - 1));
            board[points[curPoint][0]][points[curPoint][1]]++;
            
            if(board[points[curPoint][0]][points[curPoint][1]] == 2){
                answer++;
            }
        }
        
        while(!robots.isEmpty()){
            answer += checkCollision();
        }
        
        return answer;
    }
    
    public int checkCollision(){
        int ret = 0;
        int size = robots.size();
        int[][] newBoard = new int[101][101];
        
        while(size-->0){
            Robot cur = robots.poll();
            int nextRoute = routes[cur.idx][cur.curPos + 1];
            int nextRouteX = points[nextRoute - 1][0];
            int nextRouteY = points[nextRoute - 1][1];
            
            if(cur.x < nextRouteX){
                cur.x++;
            } else if(cur.x > nextRouteX){
                cur.x--;
            } else{
                if(cur.y < nextRouteY) {
                    cur.y++;
                } else if(cur.y > nextRouteY){
                    cur.y--;
                }
            }
            
            if(cur.x == nextRouteX && cur.y == nextRouteY){
                cur.curPos++;
            }
            
            newBoard[cur.x][cur.y]++;
            
            if(newBoard[cur.x][cur.y] == 2){
                ret++;
            }
            
            if(cur.curPos < cur.goal){
                robots.add(cur);
            }
        }
        
        board = newBoard;
        return ret;
    }
}