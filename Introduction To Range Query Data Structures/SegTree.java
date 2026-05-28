import java.util.Arrays;

class SegmentTree{
    public int n;
    public int []sgt;

    public SegmentTree(int n){
        this.n = n;
        sgt = new int[4*n];
        Arrays.fill(sgt , Integer.MAX_VALUE);
    }

    public void merge(int node){
        sgt[node] = Math.min(sgt[2*node] , sgt[2*node+1]);
    }

    public void build(int l, int r, int node, int []arr){
        if(l == r){
            sgt[node] = arr[l];
            return ;
        }
        int mid = (l + r) / 2;
        build(l,mid,2*node,arr);
        build(mid+1,r,2*node+1,arr);
        merge(node);
    }

    public void update(int l, int r, int node, int targetIndex, int value){
        if(l == r){
            sgt[node] = value;
            return ;
        }
        int mid = (l + r) / 2;
        if(targetIndex <= mid){
            update(l,mid,2*node,targetIndex, value);
        }else{
            update(mid+1,r,2*node+1,targetIndex, value);
        }
        merge(node);
    }

    public int query(int l, int r, int node, int L, int R){
        if(r < L || R < l){
            return Integer.MAX_VALUE;
        }
        if(L <= l && r <= R){
            return sgt[node];
        }
        int mid = (l + r) / 2;
        int left = query(l,mid,2*node,L,R);
        int right = query(mid+1,r,2*node+1,L,R);
        return Math.min(left , right);
    }
}

public class SegTree{
    public static void main(String[] args) {
        int [] arr = {1 , 8 , 4 , 10};
        int n = 4;
        SegmentTree sgt = new SegmentTree(n);
        sgt.build(0 , 3 , 1 , arr);
        System.out.println(sgt.query(0,3,1,2 , 3));
        sgt.update(0 , 3 , 1 , 1 , -7);
        System.out.println(sgt.query(0,3,1,0 , 3));
    }
}

