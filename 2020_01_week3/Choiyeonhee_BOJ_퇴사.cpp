#include <iostream>
#include <algorithm>

using namespace std;

int max_result = 0; // 최대 이익 
int num;
int day[16];
int price[16];

void dfs(int cur, int sum) {
	max_result = max(max_result, sum);

	if (cur == num + 1) return;
	if (cur + day[cur] <= num + 1) dfs(cur + day[cur], sum + price[cur]);
	if (cur + 1 <= num + 1) dfs(cur + 1, sum);
}

int main() {

	int time, pay;
	cin >> num;

	for (int i = 1; i <= num; i++) {
		cin >> time >> pay;
		day[i] = time; price[i] = pay;
	}
	dfs(1, 0);
	cout << max_result << endl;
}