// 2043. Simple Bank System
// https://leetcode.com/problems/simple-bank-system/description/
class Bank {

    long[] accounts;

    public Bank(long[] balance) {
        accounts = new long[balance.length+1];
        int i = 1;
        for(long x : balance){
            accounts[i++] = x;
        }
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if(account1 > accounts.length || account2 > accounts.length)
            return false;
        if(accounts[account1] >= money){
            accounts[account2] += money;
            accounts[account1] -= money;
            return true;
        }
        return false;
    }
    
    public boolean deposit(int account, long money) {
        if(account > accounts.length)
            return false;
        accounts[account] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if(account > accounts.length)
            return false;
        if(accounts[account] < money)
            return false;
        accounts[account] -= money;
        return true;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */