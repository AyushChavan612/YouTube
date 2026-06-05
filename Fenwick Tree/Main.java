class FenwickTree{
    public int n;
    public int []bit;

    public FenwickTree(int n, int []arr){
        this.n = n;
        bit = new int[n+1];
        for(int i = 0; i < n; ++i){
            this.update(i,arr[i],0);
        }
    }

    public void update(int index, int value, int oldValue) {
        for(++index; index <= n; index+=(index&(-index))){
            bit[index] += value-oldValue;
        }
    }

    public int sum(int index){
        int res = 0;
        for(; index >= 1; index-=(index & (-index))){
            res += bit[index];
        }
        return res;
    }

    public int query(int l, int r){
        ++l;
        ++r;
        return sum(r)-sum(l-1);
    }
}

public class Main{
    static void main() {
        int []arr = {10 , 8 , 10,  9 , 5 , 7};
        FenwickTree bit = new FenwickTree(arr.length , arr);
        System.out.println(bit.query(1,4));
        bit.update(4,11,5);
        System.out.println(bit.query(1,4));
    }
}