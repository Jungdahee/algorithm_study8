#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;

int n, m, k, result;
int lamp[50][50];

int count(int i){
    int cnt = 0;
    vector<int> v;
    for(int j = 0; j < m; j++) v.push_back(lamp[i][j]);

    for(int j = 0; j < n; j++){
        int tmp = 0;
        for(int k = 0; k < m; k++){
            if(v[k] == lamp[j][k]) tmp++;
        }
        if(tmp == m) cnt++;
    }
    return cnt;
}

int main(){ //램프
    cin >> n >> m;

    //#1. 입력 받기
    string s;
    for(int i = 0; i < n; i++){
        cin >> s;
        for(int j = 0; j < s.size(); j++){
            lamp[i][j] = s[j] - '0';
        }
    }

    cin >> k;
    for(int i = 0; i < n; i++){
        int offNum = 0;
        for(int j = 0; j < m; j++) {
            if(lamp[i][j] == 0){
                offNum++;
            }
        }

        if(offNum <= k && offNum % 2 == k  % 2) {
            int cnt = count(i);
            if(result < cnt) result = cnt;
        }
    }
    cout << result;
    return 0;
}
