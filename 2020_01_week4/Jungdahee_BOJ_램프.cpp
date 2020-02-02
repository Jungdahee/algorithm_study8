#include <cstdio>
#include <cstring>
char c[52][52];

int main(){
  int max = 0, cnt = 0;
  int n, m, l;
  int cnt2;
  scanf("%d%d", &n, &m);
  for(int i = 0; i < n; i++){
    scanf("%s", c[i]);
  }
  scanf("%d", &l);
  for (int i = 0; i < n; i++){
    cnt = 0;
    cnt2 = 0;
    for (int k = 0; k < m; k++) if(c[i][k] == '0') cnt2++;
    if (cnt2 <= l && cnt2 % 2 == l % 2) {
      for (int j = 0; j < n; j++){
        if (strcmp(c[i], c[j]) == 0) cnt++;
      }
    }
    max = max < cnt ? cnt : max;
  }
  printf("%d\n",max);
}
