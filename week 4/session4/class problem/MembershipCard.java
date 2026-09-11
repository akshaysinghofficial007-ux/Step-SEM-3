class MembershipCard {
    static String libraryName; static String validUntil;
    String studentName;
    static{libraryName="SRM Central Library";validUntil="May 2027";System.out.println("Library info loaded");}
    MembershipCard(String s){studentName=s;}
    public static void main(String[] args){
        String[] names={"Ananya","Rohan","Priya","Arjun","Sneha"};
        for(String n:names){MembershipCard c=new MembershipCard(n);System.out.println("Membership card issued:"+c.studentName);}
    }
}