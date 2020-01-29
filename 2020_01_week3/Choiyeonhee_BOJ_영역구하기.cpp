#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int arr[101][101];
bool visited[101][101]; // false °¡ default
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int m, n, k;
int x_1 = 0, y_1 = 0, x_2 = 0, y_2 = 0;
vector<int> vec;
int value = 0;

void dfs(int x, int y) {
	value++;

	visited[x][y] = true;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
			if (visited[nx][ny] == false && arr[nx][ny] == 0) dfs(nx, ny);
		}
	}
	
}

int main() {

	cin >> m >> n >> k;

	for (int i = 0; i < k; i++) {
		cin >> x_1 >> y_1 >> x_2 >> y_2;
		for (int j = y_1; j < y_2; j++) 
			for (int k = x_1; k < x_2; k++) arr[k][j] = 1;
			
	}
	
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (arr[j][i] == 0 && visited[j][i] == false) {
				value = 0;
				dfs(j, i);
				vec.push_back(value);
			}
		}
	}

	sort(vec.begin(), vec.end());
	cout << vec.size() << endl;
	for (int i = 0; i < vec.size(); i++) cout << vec[i] << " ";
}