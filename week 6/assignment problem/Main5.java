class GymMember {
    private static int counter=2000;
    private final String membershipNumber;
    private int feesPaid;

    public GymMember(int fee){ membershipNumber="GYM-"+(++counter); }
    public String getMembershipNumber(){ return membershipNumber; }
    public void payFee(int amount){ feesPaid+=amount; }
    public void payFee(int amount,String mode){ payFee(amount); }
    public int getFeesPaid(){ return feesPaid; }
    public static int getMembersEnrolled(){ return counter-2000; }
    public static boolean isValidReferralCode(String code){
        if(code==null||code.length()!=4) return false;
        return code.charAt(0)=='G' && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2)) && Character.isUpperCase(code.charAt(3));
    }
    public static String processWeeklyCheckIn(GymMember[] members){
        int processed=0,nulls=0,group=0,ind=0;
        for(GymMember m:members){
            if(m==null){nulls++;continue;}
            processed++;
            if(m instanceof GroupClassMember) group++; else ind++;
        }
        return processed+" processed "+nulls+" null skipped "+group+" group | "+ind+" individual";
    }
}

class GroupClassMember extends GymMember {
    private String className;
    public GroupClassMember(int fee,String className){ super(fee); this.className=className; }
}

public class Main5 {
    public static void main(String[] args) {
        GymMember m1=new GymMember(1000);
        System.out.println(m1.getMembershipNumber());
        System.out.println(GymMember.getMembersEnrolled());
        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));
        m1.payFee(500); m1.payFee(500,"UPI");
        System.out.println(m1.getFeesPaid());
        System.out.println(GymMember.processWeeklyCheckIn(new GymMember[]{ new GroupClassMember(1500,"Zumba"), null, new GymMember(1000)}));
    }
}
