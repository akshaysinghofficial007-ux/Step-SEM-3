class Student {
    String name; int attendance;
    static String collegeName="SRM Institute of Science and Technology";
    static int studentCount=0;
    Student(String n,int a){name=n;attendance=a;studentCount++;}
    static void printCollegeInfo(){System.out.println(collegeName+" Students created:"+studentCount);}
    public static void main(String[] args){
        new Student("Ravi",90); new Student("Anitha",85);
        Student.printCollegeInfo();
    }
}