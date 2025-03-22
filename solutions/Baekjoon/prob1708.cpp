#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;

class Point {
public:
    ll x;
    ll y;

    bool operator<(const Point &other) const {
        return (y == other.y ? x < other.x : y < other.y);
    }
};

ll CCW(Point A, Point B, Point C) {
    ll c = (A.x * B.y) + (B.x * C.y) + (C.x * A.y) - (B.x * A.y) - (C.x * B.y) - (A.x * C.y);
    return c;
}

ll getDistance(Point A, Point B) {
    return (A.x - B.x) * (A.x - B.x) +
           (A.y - B.y) * (A.y - B.y);
}

Point p;

bool cmp(Point &a, Point &b) {
    ll ccw = CCW(p, a, b);
    if (ccw == 0) {
        return getDistance(p, a) < getDistance(p, b);
    } else {
        return ccw > 0;
    }
}

void input(vector<Point> &points) {
    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        points.push_back({x, y});
    }
}

void sortPoints(vector<Point> &points) {
    sort(points.begin(), points.end());
    p = points[0];
    sort(points.begin() + 1, points.end(), cmp);
}

int ConvexHull(const vector<Point> &points) {
    Point *stk = new Point[points.size()];
    int topIdx = 0;

    stk[0] = points[0];
    stk[1] = points[1];
    topIdx = 1;
    for (int i = 2; i < points.size(); i++) {
        while (true) {
            if (topIdx < 1) {
                break;
            }
            ll ccw = CCW(stk[topIdx - 1], stk[topIdx], points[i]);
            if (ccw <= 0) {
                topIdx--;
            } else {
                break;
            }
        }
        stk[++topIdx] = points[i];
    }

    return topIdx + 1;
}

int main() {
    vector<Point> points;
    input(points);
    sortPoints(points);
    int size = ConvexHull(points);
    cout << size << endl;
}
