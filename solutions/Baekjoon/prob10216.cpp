//boj10216

#include <iostream>

using namespace std;

class Node {
public:
    int x;
    int y;
    int r;
};

Node *nodes;
int *root;

void makeSets(int n) {
    for (int i = 0; i < n; i++) {
        root[i] = i;
    }
}

int find(int n) {
    if (root[n] == n) {
        return n;
    } else {
        return root[n] = find(root[n]);
    }
}

void unite(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);

    if (aRoot == bRoot) {
        return;
    }

    root[bRoot] = aRoot;
}

double getDistance(int a, int b) {
    int dx = nodes[a].x - nodes[b].x;
    int dy = nodes[a].y - nodes[b].y;

    return sqrt(dx * dx + dy * dy);
}

bool circleTouch(int a, int b) {
    double distance = getDistance(a, b);
    double rSum = (double) nodes[a].r + nodes[b].r;

    if (distance > rSum) {
        return false;
    } else {
        return true;
    }
}

int main() {
    int T;
    cin >> T;

    while (T-- > 0) {
        int n;
        cin >> n;

        root = new int[n];
        nodes = new Node[n];

        makeSets(n);
        for (int i = 0; i < n; i++) {
            int x, y, r;
            cin >> x >> y >> r;

            nodes[i] = {x, y, r};
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (circleTouch(i, j)) {
                    unite(i, j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (find(i) == i ? 1 : 0);
        }
        cout << ans << endl;

        delete[] root;
        delete[] nodes;
    }
}
