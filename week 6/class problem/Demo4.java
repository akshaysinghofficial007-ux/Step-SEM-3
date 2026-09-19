class LibraryMember {
    private String id; private int limit; private int books;
    public LibraryMember(String id,int limit){ this.id=id; this.limit=limit; }
    public String displayInfo(){ return "General | Books: "+books; }
}

class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String id,int limit,String course){ super(id,limit); this.course=course; }
    @Override public String displayInfo(){ return "Student Course: "+course+" | Books: 0"; }
    public String getCourse(){ return course; }
}

public class Demo4 {
    public static void main(String[] args) {
        LibraryMember[] arr={ new LibraryMember("LB5",3), new StudentMember("STU6",3,"ECE") };
        System.out.println(batchPrint(arr));
    }

    static String batchPrint(LibraryMember[] members){
        StringBuilder sb=new StringBuilder();
        for(LibraryMember m:members){
            sb.append(m.displayInfo()).append(" | ");
            if(m instanceof StudentMember){
                StudentMember sm=(StudentMember)m;
                sb.append("[Course via downcast: ").append(sm.getCourse()).append("] ");
            }
        }
        return sb.toString();
    }
}
