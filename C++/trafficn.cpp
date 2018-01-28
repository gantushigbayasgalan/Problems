#include<cstdio>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;

template<class T> T min(T &a, T &b) {return (a < b ? a : b); }

const int INF = 300000000;
const int LIMIT = 10001;

void dijkstra(int start, vector<pair<int, int> > *mp, bool *isUsed, int *dest){
    priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > q;
    int u, v, w;

    q.push(make_pair(0, start));
    dest[start] = 0;
    while (!q.empty()){
        u = q.top().second;
        q.pop();
        if (isUsed[u]) continue;
        for (int i = 0; i < mp[u].size(); i++){
            v = mp[u][i].second;
            w = mp[u][i].first;  
            if (!isUsed[v] && dest[u] + w < dest[v]){
                dest[v] = dest[u] + w;
                q.push(make_pair(dest[v], v));
            }
        }
        isUsed[u] = true;
    }
}

int main(){
    freopen("input.txt","r",stdin);

    int t, n, m, k, s, f, di, ci, li, uj, vj, qj;

    vector<pair<int, int> > S[LIMIT], F[LIMIT];
    bool isUsedS[LIMIT], isUsedF[LIMIT];
    int destS[LIMIT], destF[LIMIT];

    scanf("%d", &t);
    while (t-- > 0){
        scanf("%d%d%d%d%d", &n, &m ,&k, &s, &f);
        for (int i = 1; i <= n; i++){
            S[i].clear(); F[i].clear();
            isUsedS[i] = isUsedF[i] = false;
            destS[i] = destF[i] = INF;
        }
        for (int i = 0; i < m; i++){
            scanf("%d%d%d", &di, &ci, &li);
            S[di].push_back(make_pair(li, ci));
            F[ci].push_back(make_pair(li, di));
        }
        dijkstra(s, S, isUsedS, destS);
        dijkstra(f, F, isUsedF, destF);
        int answer = INF;
        for (int i = 0; i < k; i++){
            scanf("%d%d%d", &uj, &vj, &qj);
            answer = min(answer, min(destS[f], min(destS[uj] + qj + destF[vj], destS[vj] + qj + destF[uj])));
        }
        if (answer != INF) printf("%d\n", answer);
        else printf("-1\n");
    }

    return 0;
}
