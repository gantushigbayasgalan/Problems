#include<cstdio>
#include<iostream>
#include<algorithm>
#include<string>
#include<queue>

using namespace std;

bool isOnBoard(int x, int y){
    return (0 <= x && x < 8 && 0 <= y && y < 8);
}

int main(){
    freopen("input.txt", "r", stdin);

    int T, startX, startY, finishX, finishY, x, y;
    string start, finish;
    queue<pair<int, int> > q;
    int xPlus[8] = {1, 2, 2, 1, -1, -2, -2, -1}, yPlus[8] = {2, 1, -1, -2, -2, -1, 1, 2};
    int board[8][8];

    cin>>T;
    for (int t = 0; t < T; t++){
        cin>>start>>finish;
        startX = start[0] - 97; startY = start[1] - 49;
        finishX = finish[0] - 97; finishY = finish[1] - 49;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = 0;

        q.push(make_pair(startX, startY));
        board[startX][startY] = 1;
        while (!q.empty()){
            pair<int, int> top = q.front();
            q.pop();
            for (int i = 0; i < 8; i++){
                x = top.first + xPlus[i];
                y = top.second + yPlus[i];
                if (isOnBoard(x, y) && board[x][y] == 0){
                    board[x][y] = board[top.first][top.second] + 1;
                    q.push(make_pair(x, y));
                }
            }
        } 
        cout<<board[finishX][finishY] - 1<<endl;
    }
}
