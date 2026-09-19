class GymMember {
    private String id; private int fee; private int sessions;
    public GymMember(String id,int fee){ this.id=id; this.fee=fee; }
    public void attendSession(){ sessions++; }
    public int getSessionsAttended(){ return sessions; }
    public String displayInfo(){ return "Standard Member | Sessions: "+sessions; }
}

class PremiumMember extends GymMember {
    private String trainer;
    public PremiumMember(String id,int fee,String trainer){ super(id,fee); this.trainer=trainer; }
    @Override public String displayInfo(){ return "Premium Member | Trainer: "+trainer+" | Sessions: "+getSessionsAttended(); }
    public String getTrainer(){ return trainer; }
}

class EliteMember extends PremiumMember {
    private String locker;
    public EliteMember(String id,int fee,String trainer,String locker){ super(id,fee,trainer); this.locker=locker; }
    @Override public String displayInfo(){ return "Elite Member | Trainer: "+getTrainer()+" | Locker: "+locker+" | Sessions: "+getSessionsAttended(); }
}

class GroupClassMember extends GymMember {
    private String className;
    public GroupClassMember(String id,int fee,String className){ super(id,fee); this.className=className; }
    @Override public String displayInfo(){ return "Group Class Member | Class: "+className+" | Sessions: "+getSessionsAttended(); }
}

public class Main2 {
    public static void main(String[] args) {
        PremiumMember p=new PremiumMember("MEM2",2000,"Coach Riya");
        EliteMember e=new EliteMember("MEM3",3000,"Coach Arjun","L12");
        GroupClassMember g=new GroupClassMember("MEM4",1500,"Zumba");

        System.out.println(p.displayInfo());
        System.out.println(e.displayInfo());
        System.out.println(g.displayInfo());

        p.attendSession(); p.attendSession(); p.attendSession();
        e.attendSession(); e.attendSession();
        g.attendSession(); g.attendSession(); g.attendSession(); g.attendSession();

        System.out.println("Total Sessions: "+getTotalSessionsAttended(new GymMember[]{p,e,g}));
    }

    static String classifyGeneration(GymMember m){
        if(m instanceof EliteMember) return "Multilevel descendant (3 generations deep)";
        if(m instanceof GroupClassMember) return "Hierarchical sibling (independent branch)";
        return "Base generation";
    }

    static int getTotalSessionsAttended(GymMember[] members){
        int total=0; for(GymMember m:members) total+=m.getSessionsAttended(); return total;
    }
}
