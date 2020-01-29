#include <iostream>
#include <queue>

using namespace std;

int miro[101][101];
int dist[101][101] = { {0},{0} };
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int h, w;

void bfs() {

	queue<pair<int, int>> q;
	q.push(make_pair(1, 1));
	dist[1][1] = 1; // 1,1부터 시작

	while (!q.empty()) {

		pair<int, int> p = q.front();
		q.pop();
		int cx = p.first;
		int cy = p.second;

		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];

			if (nx >= 1 && nx <= h && ny >= 1 && ny <= w) {
				if (dist[nx][ny] == 0 && miro[nx][ny] == 1) {
					q.push(make_pair(nx, ny)); 
						dist[nx][ny] = dist[cx][cy] + 1;
				}
			}
		}
	}
	cout << dist[h][w] << endl;
}

int main() {

	cin >> h >> w;

	for (int i = 1; i <= h; i++) {
		for (int j = 1; j <= w; j++) scanf_s("%1d", &miro[i][j]);
	}

	bfs();
}