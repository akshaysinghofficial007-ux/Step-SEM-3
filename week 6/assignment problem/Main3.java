class GymMember {
    private int[] fees=new int[10]; private int count;
    protected void chargeLateFee(int amount){ fees[count++]=amount; }
    public int[] getLateFeeHistory(){ return java.util.Arrays.copyOf(fees,count); }
    public int getTotalLateFees(){ int sum=0; for(int f:fees) sum+=f; return sum; }
}

class PremiumMember extends GymMember {
    private String trainer;
    public PremiumMember(String id,int fee,String trainer){ super(); this.trainer=trainer; }
    @Override protected void chargeLateFee(int amount){ super.chargeLateFee(amount/2); }
}

public class Main3 {
    public static void main(String[] args) {
        PremiumMember p=new PremiumMember("MEM5",2000,"Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());
        int[] history=p.getLateFeeHistory();
        history[0]=999;
        System.out.println(java.util.Arrays.toString(p.getLateFeeHistory()));
    }
}
