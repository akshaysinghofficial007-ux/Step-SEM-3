class LibraryMember {
    private int[] fines=new int[10]; private int count;
    protected void chargeFine(int amount){ fines[count++]=amount; }
    public int[] getFineHistory(){ return java.util.Arrays.copyOf(fines,count); }
    public int getTotalFine(){ int sum=0; for(int f:fines) sum+=f; return sum; }
}

class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String id,int limit,String course){ super(); this.course=course; }
    @Override protected void chargeFine(int amount){ super.chargeFine(amount/2); }
}

public class Demo3 {
    public static void main(String[] args) {
        StudentMember s=new StudentMember("STU5",3,"CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());
        int[] history=s.getFineHistory();
        history[0]=999;
        System.out.println(java.util.Arrays.toString(s.getFineHistory()));
    }
}
