#include<cstdio>
#include<algorithm>
#include<iostream>
#include<queue>

using namespace std;

bool isOnMap(int n, int m, pair<int, int> moving){
    return (moving.first >= 0 && moving.first < n && moving.second >= 0 && moving.second < m);
}

int main(){
    freopen("input.txt","r",stdin);

    int n, m, x1 = -1, y1, x2, y2;
    int xPlus[4] = {0, 1, 0, -1}, yPlus[4] = {1, 0, -1, 0};
    int mirrors[100][100];
    string map[100];
   
    cin>>m>>n;
    for (int i = 0; i < n; i++){
        cin>>map[i];
        for (int j = 0; j < m; j++){
            mirrors[i][j] = 0;
            if (map[i][j] == 'C'){
                if (x1 == -1){
                    x1 = i;
                    y1 = j;
                } else {
                    x2 = i;
                    y2 = j;
                }
            }
            if (map[i][j] == '*') mirrors[i][j] = -1;
        }
    }
    
    queue<pair<int, int> > q;
    q.push(make_pair(x1, y1));
    mirrors[x1][y1] = 1;
    pair<int, int> current, moving;

    while (!q.empty()){
        current = q.front();
        q.pop();
        for (int i = 0; i < 4; i++){
            moving = current;
            moving.first += xPlus[i];
            moving.second += yPlus[i];
            while (isOnMap(n, m, moving) && mirrors[moving.first][moving.second] != -1){
                if (mirrors[moving.first][moving.second] == 0){
                    q.push(make_pair(moving.first, moving.second));
                    mirrors[moving.first][moving.second] = mirrors[current.first][current.second] + 1;
                }
                moving.first += xPlus[i];
                moving.second += yPlus[i];
            }
        }
        if (mirrors[x2][y2] != 0) break;
    }
    cout<<mirrors[x2][y2] - 2<<endl;
}
