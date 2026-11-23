// 1352. Product of the Last K Numbers
// https://leetcode.com/problems/product-of-the-last-k-numbers
// optim
class ProductOfNumbers {

    List<Integer> list;
    public ProductOfNumbers() {
        list = new ArrayList<>();
        list.add(1);
    }
    
    public void add(int num) {
        if(num == 0) {
            list.clear();
            list.add(1);
        }else {
            int n = list.size();
            list.add(list.get(n-1) * num);
        }
    }
    
    public int getProduct(int k) {
        int n = list.size();

        if(k >= n) {
            return 0;
        }

        return list.get(n-1) / list.get(n-k-1);
    }
}

// naive
class ProductOfNumbers {

    List<Integer> list;
    public ProductOfNumbers() {
        list = new ArrayList<>();
    }
    
    public void add(int num) {
        list.add(num);
    }
    
    public int getProduct(int k) {
        int n = list.size();
        int product = 1;
        for(int i=n-1;i>=n-k;i--){
            product *= list.get(i);
        }
        return product;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */