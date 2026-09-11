class MessWallet {
    private double balance;
    MessWallet(double b){balance=b<0?0:b;}
    void topUp(double amt){if(amt>0) balance+=amt;}
    void deduct(double amt){if(amt<=balance) balance-=amt;}
    double getBalance(){return balance;}
    public static void main(String[] args){
        MessWallet w=new MessWallet(500);
        w.topUp(200); w.deduct(1000);
        System.out.println("Final balance:"+w.getBalance());
    }
}