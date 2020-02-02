#include<iostream>
 
#define endl "\n"
using namespace std;
 
int N, K, Answer;
 
void Input()
{
    cin >> N >> K;
}
 
int Add_Water(int x)
{
    int Cnt = 0;
    while (x > 0)
    {
        //5면
        // 2가 되고 -> 1이되 
        if (x % 2 == 1) Cnt++;
        x = x / 2;
    }
    return Cnt;
}
 
void Solution()
{
    if (N <= K) cout << 0 << endl;
    else
    {
        while (1)
        {
            int Temp_Result = Add_Water(N);
            if (Temp_Result <= K) break;
 
            Answer++;
            N++;
        }
        cout << Answer << endl;
    }
 
}
 
void Solve()
{
    Input();
    Solution();
}
 
int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
 
    //freopen("Input.txt", "r", stdin);
    Solve();
 
    return 0;
}


출처: https://yabmoons.tistory.com/199 [얍문's Coding World..]
