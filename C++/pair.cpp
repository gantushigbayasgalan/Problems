#include<cstdio>
#include<algorithm>
#include<vector>

using namespace std;

vector <pair<int, int>> S[10001], F[10001];

int main(){
    freopen("input.txt","r",stdin);

    int t, n, m, k, s, f, di, ci, li, uj, vj, qj;

    while (t-- > 0){
        scanf("%d%d%d%d%d", &n, &m ,&k, &s, &f);
        for (int i = 1; i <= n; i++){
            S[i].clear(); F[i].clear();
        }
        for (int i = 0; i < m; i++){
            scanf("%d%d%d", &di, &ci, &li);
            S[di].push_back(make_pair(li, ci));
            F[ci].push_back(make_pair(li, di));
        }
        for (int i = 0; i < k; i++){
            scanf("%d%d%d", &uj, &vj, &qj);
        }
    }
}
