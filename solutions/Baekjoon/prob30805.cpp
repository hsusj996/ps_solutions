#include <iostream>
#include <vector>

using namespace std;

int N, M;
int arr1[100];
int arr2[100];
int p1 = -1, p2 = -1;
int maxValue = 100;
vector<int> v;

int checkArr1(int target) {
	for (int i = p1 + 1;i < N;i++) {
		if (arr1[i] == target) {
			return i;
		}
	}
	
	return -1;
}

int checkArr2(int target) {
	for (int i = p2 + 1;i < M;i++) {
		if (arr2[i] == target) {
			return i;
		}
	}

	return -1;
}

int getNextValue() {
	for (int target = maxValue;target >= 1;target--) {
		int nextPos1 = checkArr1(target);
		int nextPos2 = checkArr2(target);

		if (nextPos1 != -1 && nextPos2 != -1) {
			p1 = nextPos1;
			p2 = nextPos2;
			maxValue = target;
			return target;
		}
	}

	return -1;
}

int main() {
	cin >> N;

	for (int i = 0;i < N;i++) {
		cin >> arr1[i];
	}

	cin >> M;

	for (int i = 0;i < M;i++) {
		cin >> arr2[i];
	}

	while (p1 < N || p2 < M) {
		int nextValue = getNextValue();

		if (nextValue == -1) {
			break;
		}
		else {
			v.push_back(nextValue);
		}
	}

	cout << v.size() << "\n";
	for (int i : v) {
		cout << i << " ";
	}
}