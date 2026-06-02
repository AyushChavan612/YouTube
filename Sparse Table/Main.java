class SparseTable{
    public int n;
    public int[][] st;
    public int[] log;
    public int mxPow;

    public SparseTable(int n, int []arr){
        this.n = n;
//        2^mxPow = 1<<mxPow
        while((1<<mxPow) <= n){
            ++mxPow;
        }

        st = new int[n][mxPow];
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < mxPow; ++j){
                st[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < n; ++i){
            st[i][0] = arr[i];
        }

        log = new int[n+1];
        log[1]=0;

        for(int i = 2; i <= n; ++i){
            log[i] = log[i/2] + 1;
        }

        build();
    }

    public void build(){
        for(int j = 1; j < mxPow; ++j){
            for(int i = 0; i+(1<<j)-1 < n; ++i){
                st[i][j] = Math.min(st[i][j-1] , st[i+(1<<(j-1))][j-1]);
            }
        }
    }

    public int query(int l,int r){
       int largestPow = log[r-l+1];
       return Math.min(st[l][largestPow] , st[r-(1<<largestPow)+1][largestPow]);
        // int res = Integer.MAX_VALUE;
        // int len = r - l + 1 ;
        // int index = l;

        // for(int i = mxPow-1; i >= 0; --i){
        //     if(((len >> i) &1) == 0) continue;
        //      res = Math.min(res , st[index][i]);
        //      index += (1 << i);
        // }
        // return res;
    }
}

public class Main{
    static void main() {
        int []arr = {3 , 7 , 2 , 8 , 1 , 9 , 4 , 6 , 10 , 5};
        SparseTable st = new SparseTable(arr.length , arr);
        for(int i = 0; i < st.n; ++i){
            for(int j = 0; j < st.mxPow; ++j){
                System.out.print(st.st[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(st.query(0,3));
    }
}