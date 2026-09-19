class LibraryMember {
    private String id; private int limit; private int books;
    public LibraryMember(String id,int limit){ this.id=id; this.limit=limit; }
    public void borrowBook(){ books++; }
    public int getBooksBorrowed(){ return books; }
    public String displayInfo(){ return "General Member Books Borrowed: "+books; }
}

class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String id,int limit,String course){ super(id,limit); this.course=course; }
    @Override public String displayInfo(){ return "Student Member Course: "+course+" | Books Borrowed: "+getBooksBorrowed(); }
    public String getCourse(){ return course; }
}

class HonorsStudentMember extends StudentMember {
    private int bonus;
    public HonorsStudentMember(String id,int limit,String course,int bonus){ super(id,limit,course); this.bonus=bonus; }
    @Override public String displayInfo(){ return "Honors Student Member | Course: "+getCourse()+" Bonus Limit: "+bonus+" | Books Borrowed: "+getBooksBorrowed(); }
}

class FacultyMember extends LibraryMember {
    private String dept;
    public FacultyMember(String id,int limit,String dept){ super(id,limit); this.dept=dept; }
    @Override public String displayInfo(){ return "Faculty Member | Department: "+dept+" | Books Borrowed: "+getBooksBorrowed(); }
}

public class Demo2 {
    public static void main(String[] args) {
        StudentMember s=new StudentMember("STU2",3,"CSE");
        HonorsStudentMember h=new HonorsStudentMember("STU3",3,"ECE",2);
        FacultyMember f=new FacultyMember("STU4",5,"Physics");

        s.borrowBook(); s.borrowBook();
        h.borrowBook();
        f.borrowBook(); f.borrowBook(); f.borrowBook();

        System.out.println(s.displayInfo());
        System.out.println(h.displayInfo());
        System.out.println(f.displayInfo());

        System.out.println(classifyGeneration(h));
        System.out.println(classifyGeneration(f));
        System.out.println("Total Books: "+getTotalBooksBorrowed(new LibraryMember[]{s,h,f}));
    }

    static String classifyGeneration(LibraryMember m){
        if(m instanceof HonorsStudentMember) return "Multilevel descendant (3 generations deep)";
        if(m instanceof FacultyMember) return "Hierarchical sibling (independent branch)";
        return "Base generation";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members){
        int total=0; for(LibraryMember m:members) total+=m.getBooksBorrowed(); return total;
    }
}
