class GymMember {
    private String id; private int fee; private int sessions;
    public GymMember(String id,int fee){ this.id=id; this.fee=fee; }
    public String displayInfo(){ return "Standard | Sessions: "+sessions; }
}

class PremiumMember extends GymMember {
    private String trainer;
    public PremiumMember(String id,int fee,String trainer){ super(id,fee); this.trainer=trainer; }
    @Override public String displayInfo(){ return "Premium | Trainer: "+trainer+" | Sessions: 0"; }
    public String getTrainer(){ return trainer; }
}

public class Main4 {
    public static void main(String[] args) {
        GymMember[] arr={ new GymMember("MEM6",1000), new PremiumMember("MEM7",2000,"Coach Riya") };
        System.out.println(batchPrint(arr));
    }

    static String batchPrint(GymMember[] members){
        StringBuilder sb=new StringBuilder();
        for(GymMember m:members){
            sb.append(m.displayInfo()).append(" | ");
            if(m instanceof PremiumMember){
                PremiumMember pm=(PremiumMember)m;
                sb.append("[Trainer via downcast: ").append(pm.getTrainer()).append("] | ");
            }
        }
        return sb.toString();
    }
}
