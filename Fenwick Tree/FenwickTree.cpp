#include<bits/stdc++.h>
using namespace std;

class FenwickTree {
public:
    int n;
    vector<int> bit;

    FenwickTree(int n, const vector<int>& arr) {
        this->n = n;
        bit.assign(n + 1, 0);
        for (int i = 0; i < n; ++i) {
            update(i, arr[i], 0);
        }
    }

    void update(int idx, int val, int oldVal) {
        for (++idx; idx <= n; idx += (idx & -idx)) {
            bit[idx] += val - oldVal;
        }
    }

    int sum(int idx) {
        int res = 0;
        for (; idx >= 1; idx -= (idx & -idx)) {
            res += bit[idx];
        }
        return res;
    }

    int query(int l, int r) {
        ++l;
        ++r;
        return sum(r) - sum(l - 1);
    }
};

int main() {
    vector<int> arr = {10, 8, 10, 9, 5, 7};
    FenwickTree bit(arr.size(), arr);
    
    cout << bit.query(1, 4) << "\n";
    bit.update(4, 11, 5);
    cout << bit.query(1, 4) << "\n";
    
    return 0;
}