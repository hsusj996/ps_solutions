#include <iostream>
#include <string>
#include <vector>
#include <stack>
using namespace std;

class Node {
public:
	int r;
	int c;
};

int d[2][4] = { {-1, 0, 1, 0},{0, 1, 0, -1} };
int visited[1000][1000];
int board[1000][1000];
int N, M;
int ans;

void init() {
	ans = 0;
}

void input() {
	int n, m;
	cin >> n >> m;
	N = n; M = m;

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < M; j++) {
			switch (s[j]) {
			case 'U':
				board[i][j] = 0;
				break;
			case 'R':
				board[i][j] = 1;
				break;
			case 'D':
				board[i][j] = 2;
				break;
			case 'L':
				board[i][j] = 3;
				break;
			}
		}
	}
}

bool isNewCycle(int r, int c) {
	bool ret = false;
	stack<Node> route;
	route.push({ r, c });
	visited[r][c] = 1;

	int dir = board[r][c];
	int nr = r + d[0][dir];
	int nc = c + d[1][dir];

	while (visited[nr][nc] == 0) {
		route.push({ nr, nc });
		visited[nr][nc] = 1;
		int nDir = board[nr][nc];
		nr += d[0][nDir];
		nc += d[1][nDir];
	}
	
	ret = visited[nr][nc] == 2 ? false : true;

	while (!route.empty()) {
		Node cNode = route.top();
		route.pop();
		visited[cNode.r][cNode.c] = 2;
	}

	return ret;
}

void printForDebug() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << visited[i][j] << " ";
		}
		cout << "\n";
	}
	cout << "\n";
	cout << "\n";
	cout << "\n";
}

void solution() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (visited[i][j]) continue;

			if (isNewCycle(i, j)) {
				ans++;
			}

			//printForDebug();
		}
	}
}

void print() {
	cout << ans;
}

int main() {
	init();
	input();
	solution();
	print();
}