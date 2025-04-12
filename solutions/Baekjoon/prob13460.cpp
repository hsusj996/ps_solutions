#include <iostream>
#include <queue>
#include <string>

using namespace std;

class xy {
public:
    int x;
    int y;

    bool operator==(const xy &other) const {
        return x == other.x && y == other.y;
    }
};

class Node {
public:
    xy r;
    xy b;

    bool directionCheck(int d) const {
        switch (d) {
            case 0:
                return r.x < b.x;
            case 1:
                return r.y > b.y;
            case 2:
                return r.x > b.x;
            case 3:
                return r.y < b.y;
            default:
                return false;
        }
    }
};

const int dir[2][4] = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
int N, M;
char board[10][10];
bool visited[10][10][10][10];
xy hole;
xy r, b;

void init() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < M; j++) {
            board[i][j] = s[j];
            if (board[i][j] == 'O') {
                hole.x = i;
                hole.y = j;
            } else if (board[i][j] == 'R') {
                r.x = i;
                r.y = j;
                board[i][j] = '.';
            } else if (board[i][j] == 'B') {
                b.x = i;
                b.y = j;
                board[i][j] = '.';
            }
        }
    }
}

Node getNextNode(Node cur, int d) {
    xy nextR = cur.r;
    xy nextB = cur.b;
    if (cur.directionCheck(d)) {
        while (true) {
            int nx = nextR.x + dir[0][d];
            int ny = nextR.y + dir[1][d];

            if (board[nx][ny] == 'O') {
                nextR.x = nx;
                nextR.y = ny;
                break;
            } else if (board[nx][ny] != '.') {
                board[nextR.x][nextR.y] = '#';
                break;
            }

            nextR.x = nx;
            nextR.y = ny;
        }

        while (true) {
            int nx = nextB.x + dir[0][d];
            int ny = nextB.y + dir[1][d];

            if (board[nx][ny] == 'O') {
                nextB.x = nx;
                nextB.y = ny;
                break;
            } else if (board[nx][ny] != '.') {
                board[nextB.x][nextB.y] = '#';
                break;
            }

            nextB.x = nx;
            nextB.y = ny;
        }
    } else {
        while (true) {
            int nx = nextB.x + dir[0][d];
            int ny = nextB.y + dir[1][d];

            if (board[nx][ny] == 'O') {
                nextB.x = nx;
                nextB.y = ny;
                break;
            } else if (board[nx][ny] != '.') {
                board[nextB.x][nextB.y] = '#';
                break;
            }

            nextB.x = nx;
            nextB.y = ny;
        }

        while (true) {
            int nx = nextR.x + dir[0][d];
            int ny = nextR.y + dir[1][d];

            if (board[nx][ny] == 'O') {
                nextR.x = nx;
                nextR.y = ny;
                break;
            } else if (board[nx][ny] != '.') {
                board[nextR.x][nextR.y] = '#';
                break;
            }

            nextR.x = nx;
            nextR.y = ny;
        }
    }

    board[nextR.x][nextR.y] = '.';
    board[nextB.x][nextB.y] = '.';
    board[hole.x][hole.y] = 'O';
    return {nextR, nextB};
}

int bfs() {
    queue<Node> q;
    q.push({r, b});
    visited[r.x][r.y][b.x][b.y] = true;
    int round = 0;
    while (q.size() > 0 && round < 10) {
        int size = q.size();
        while (size-- > 0) {
            Node cur = q.front();
            q.pop();

            for (int i = 0; i < 4; i++) {
                Node nextNode = getNextNode(cur, i);

                if (visited[nextNode.r.x][nextNode.r.y][nextNode.b.x][nextNode.b.y]) continue;

                bool rf = hole == nextNode.r ? true : false;
                bool bf = hole == nextNode.b ? true : false;

                if (bf) {
                    continue;
                } else if (rf) {
                    return round + 1;
                }

                visited[nextNode.r.x][nextNode.r.y][nextNode.b.x][nextNode.b.y] = true;
                q.push(nextNode);
            }
        }
        round++;
    }

    return -1;
}

int solution() {
    int ret = bfs();
    return ret;
}

int main() {
    init();
    int ans = solution();
    cout << ans;
}
