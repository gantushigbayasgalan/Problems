#include<cstdio>
#include<algorithm>
#include<vector>
#include<set>
#include<map>
#include<iostream>

using namespace std;

vector <pair<int, int> > v;
bool isUsed[10][10];
int xPlus[8] = {1, 2, 2, 1, -1, -2, -2, -1}, yPlus[8] = {2, 1, -1, -2, -2, -1, 1, 2};
int answer, n;

bool isOnBoard(int x, int y) {
    return (0 <= x && x < n && v[x].first <= y && y < v[x].second);
}

void DFS(int x, int y, int numberOfCells) {
    int xx, yy;
    for (int i = 0; i < 8; i++) {
        xx = x + xPlus[i];
        yy = y + yPlus[i];
        if (isOnBoard(xx, yy) && !isUsed[xx][yy]) {
            isUsed[xx][yy] = true;
            DFS(xx, yy, numberOfCells - 1);
            isUsed[xx][yy] = false;
        }
    }
    answer = min(answer, numberOfCells);
}

int main() {
    freopen("input.txt","r",stdin);

    int x, y, t = 1, numberOfCells;
    scanf("%d", &n);
    while (n != 0) {
        numberOfCells = -1;
        v.clear();
        for (int i = 0; i < n; i++) {
            scanf("%d%d", &x, &y);
            v.push_back(make_pair(x, x + y));
            numberOfCells += y;
        }
        isUsed[0][0] = true;
        answer = numberOfCells;
        DFS(0, 0, numberOfCells);
        if (answer != 1) printf("Case %d, %d squares can not be reached.\n", t++, answer);
		else printf("Case %d, %d square can not be reached.\n", t++, answer);

        scanf("%d", &n);
    }
}

