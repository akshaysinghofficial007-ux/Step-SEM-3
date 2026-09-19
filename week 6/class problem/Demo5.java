class LibraryMember {
    private static int counter=100;
    private final String memberNumber;
    private int booksBorrowed;

    public LibraryMember(int limit){ memberNumber="LIB-"+(++counter); }
    public String getMemberNumber(){ return memberNumber; }
    public void borrowBook(){ booksBorrowed++; }
    public void borrowBook(String genre){ borrowBook(); }
    public int getBooksBorrowed(){ return booksBorrowed; }
    public static int getMembersEnrolled(){ return counter-100; }
    public static boolean isValidRenewalCode(String code){
        if(code==null||code.length()!=4) return false;
        return code.charAt(0)=='R' && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2)) && Character.isUpperCase(code.charAt(3));
    }
    public static String processNightlyAudit(LibraryMember[] members){
        int processed=0,nulls=0,faculty=0,regular=0;
        for(LibraryMember m:members){
            if(m==null){nulls++;continue;}
            processed++;
            if(m instanceof FacultyMember) faculty++; else regular++;
        }
        return processed+" processed | "+nulls+" null skipped "+faculty+" faculty | "+regular+" regular";
    }
}

class FacultyMember extends LibraryMember {
    private String dept;
    public FacultyMember(int limit,String dept){ super(limit); this.dept=dept; }
}

public class Demo5 {
    public static void main(String[] args) {
        LibraryMember m1=new LibraryMember(3);
        System.out.println(m1.getMemberNumber());
        System.out.println(LibraryMember.getMembersEnrolled());
        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        m1.borrowBook(); m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());
        System.out.println(LibraryMember.processNightlyAudit(new LibraryMember[]{ new FacultyMember(5,"Physics"), null, new LibraryMember(3)}));
    }
}
