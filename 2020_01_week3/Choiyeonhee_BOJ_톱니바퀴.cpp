#include <iostream>

using namespace std;

int arr[5][9];
int num; // 회전 횟수

void rotate(int index, int ns) {
	int t[8];
	if (ns == 1) {
		for (int i = 0; i < 8; i++) t[(i + 1) % 8] = arr[index][i];
	}
	else {
		for (int i = 0; i < 8; i++) t[i] = arr[index][(i + 1) % 8];
	}
	for (int i = 0; i < 8; i++) arr[index][i] = t[i];

}

void solve() {

	for (int i = 0; i < num; i++) {
		int index, ns; // 회전할 톱니바퀴 번호, 회전방향
		cin >> index >> ns; index--; // 0번부터 이므로 
		int direct[4] = { 0 };
		direct[index] = ns;
		for (int i = index; i < 3; i++) if (arr[i][2] != arr[i + 1][6]) direct[i + 1] = -direct[i];
		for (int i = index; i > 0; i--) if (arr[i][6] != arr[i - 1][2]) direct[i - 1] = -direct[i];
		for (int i = 0; i < 4; i++) {
			if (direct[i]) rotate(i, direct[i]);
		}
	}
}

int main() {
	
	int result = 0;

	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 8; j++)	cin >> arr[i][j];
	} // 0이면 N극, 1이면 S극 

	cin >> num; 
	solve();
	for (int i = 0; i < 4; i++) if (arr[i][0]) result += (1 << i);
	cout << result << endl;

}