#include <iostream>
using namespace std;

int n, s;
int result = 0;
int num[20];

void subSet(int idx, int sum){
    if(idx == n){ //기저 조건
        if(sum == s) result++;
        return;
    }

    subSet(idx + 1, sum + num[idx]); //현재 원소를 넣는 경우
    subSet(idx + 1, sum); //현재 원소를 넣지 않는 경우
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    
    cin >> n >> s;

    for(int i = 0; i < n; i++) cin >> num[i];

    subSet(0, 0);
    
    if(s == 0) result--; //공집합인 경우에도 재귀에서 1이 더해졌으므로 감소해줘야 함.

    cout << result << '\n';
    return 0;
}
