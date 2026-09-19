class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) throw new IllegalArgumentException("Invalid ID");
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() { sessionsAttended++; }
    public int getSessionsAttended() { return sessionsAttended; }

    public static String signUpBatch(String[] ids,int fee){
        int s=0,r=0;
        for(String id:ids){
            try{ new GymMember(id,fee); s++; }catch(Exception e){ r++; }
        }
        return "Signed Up: "+s+" | Rejected: "+r;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;
    public PremiumMember(String id,int fee,String trainer){ super(id,fee); this.trainerName=trainer; }
}

public class Main1 {
    public static void main(String[] args) {
        System.out.println(GymMember.signUpBatch(new String[]{"MEM1","GM1","MEM2"," ","MEM3"},1000));
        PremiumMember p=new PremiumMember("MEM01",2000,"Coach Riya");
        p.attendSession(); p.attendSession();
        System.out.println(p.getSessionsAttended());
    }
}
