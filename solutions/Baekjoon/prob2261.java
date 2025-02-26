import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class App {
    static int N;
    static xy[] points;
    static StringTokenizer st = null;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        points = new xy[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new xy(x, y);
        }

        Arrays.sort(points, xComparator);

        System.out.print(closestPointsDistance(points, 0, N - 1));
    }

    private static int closestPointsDistance(xy[] points, int p1, int p2) {
        int size = p2 - p1 + 1;
        int mid = (p1 + p2) / 2;

        if (size <= 3) {
            return distanceOfPoints(points, size, p1, p2);
        }

        int d1 = closestPointsDistance(points, p1, mid);
        int d2 = closestPointsDistance(points, mid + 1, p2);
        int minD = Math.min(d1, d2);
        int d3 = middleBand(p1, mid, p2, minD);

        return Math.min(d3, minD);
    }

    private static int middleBand(int start, int mid, int end, int minD) {
        ArrayList<xy> candidates = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            int xDist = points[i].x - points[mid].x;

            if (xDist * xDist < minD) {
                candidates.add(points[i]);
            }
        }

        Collections.sort(candidates, YComparator);

        for (int i = 0; i < candidates.size() - 1; i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                int yDist = candidates.get(i).y - candidates.get(j).y;

                if (yDist * yDist < minD) {
                    minD = Math.min(candidates.get(i).getDistance(candidates.get(j)), minD);
                } else {
                    break;
                }
            }
        }

        return minD;
    }

    private static int distanceOfPoints(xy[] points, int size, int p1, int p2) {
        if (size == 2) {
            return points[p1].getDistance(points[p2]);
        }

        int d1 = points[p1].getDistance(points[p1 + 1]);
        int d2 = points[p1].getDistance(points[p2]);
        int d3 = points[p1 + 1].getDistance(points[p2]);

        return Math.min(d1, Math.min(d2, d3));
    }

    static class xy {
        int x;
        int y;

        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(xy o) {
            int dx = Math.abs(this.x - o.x);
            int dy = Math.abs(this.y - o.y);

            return dx * dx + dy * dy;
        }
    }

    static Comparator<xy> YComparator = new Comparator<xy>() {
        @Override
        public int compare(xy o1, xy o2) {
            return o1.y - o2.y;
        }
    };

    static Comparator<xy> xComparator = new Comparator<xy>() {
        @Override
        public int compare(xy o1, xy o2) {
            return o1.x - o2.x;
        }
    };
}