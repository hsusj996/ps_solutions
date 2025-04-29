#include <iostream>
#include <vector>
using namespace std;

class V {
public:
	int parent;
	vector<int> childs;
};

int subTreeSize[100001];
V graph[100001];
vector<int> edgeList[100001];
vector<int> query;
int N, R, Q;

void init() {
	for (int i = 0; i <= 100000; i++) {
		subTreeSize[i] = -1;
	}
}

void input() {
	int n, r, q;
	cin >> n >> r >> q;
	N = n; R = r; Q = q;

	for (int i = 0; i < N - 1; i++) {
		int v1, v2;
		cin >> v1 >> v2;
		edgeList[v1].push_back(v2);
		edgeList[v2].push_back(v1);
	}

	for (int i = 0; i < Q; i++) {
		int q;
		cin >> q;
		query.push_back(q);
	}
}

void graphArrange(int v, int parent) {
	for (int connV : edgeList[v]) {
		if (connV == parent) continue;

		graph[connV].parent = v;
		graph[v].childs.push_back(connV);
		graphArrange(connV, v);
	}
}

int getVertexCnt(int v) {
	if (subTreeSize[v] != -1) return subTreeSize[v];

	int ret = 1;
	for (int child : graph[v].childs) {
		ret += getVertexCnt(child);
	}

	return subTreeSize[v] = ret;
}

int main() {
	init();
	input();
	graphArrange(R, -1);

	for (int i : query) {
		cout << getVertexCnt(i) << "\n";
	}
}