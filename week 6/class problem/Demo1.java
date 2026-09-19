class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) throw new IllegalArgumentException("Invalid ID");
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook(){ booksBorrowed++; }
    public int getBooksBorrowed(){ return booksBorrowed; }

    public static String enrollBatch(String[] ids,int limit){
        int enrolled=0,rejected=0;
        for(String id:ids){
            try{ new LibraryMember(id,limit); enrolled++; }catch(Exception e){ rejected++; }
        }
        return "Enrolled: "+enrolled+" | Rejected: "+rejected;
    }
}

class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String id,int limit,String course){ super(id,limit); this.course=course; }
}

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(LibraryMember.enrollBatch(new String[]{"STU1","LB1","STU2"," ","STU3"},3));
        StudentMember s=new StudentMember("STU10",3,"CSE");
        s.borrowBook(); s.borrowBook();
        System.out.println(s.getBooksBorrowed());
    }
}
