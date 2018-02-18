#include<cstdio>
#include<algorithm>
#include<queue>
#include<string>
#include<iostream>
#include<vector>

using namespace std;

vector<int> A[10001];

pair<int, int> DFS(int node, int parent){
    pair<int, int> top = make_pair(0, node);
    pair<int, int> returnedPair;

    for (int i = 0; i < A[node].size(); i++){
        if (A[node][i] != parent){
            returnedPair = DFS(A[node][i], node);
            if (top.first < returnedPair.first) top = returnedPair;
        }
    }
    top.first++;
    return top;
}

int main(){
    freopen("input.txt", "r", stdin);

    int n, x, y;
    cin>>n;
    for (int i = 1; i < n; i++){
        cin>>x>>y;
        A[x].push_back(y);
        A[y].push_back(x);
    }
    pair<int, int> longest = DFS(1, -1);
    longest = DFS(longest.second, -1);
    cout<<longest.first - 1<<endl;
}
