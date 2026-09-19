class Participant {
    String name,teamName; boolean registered;
    Participant(String n,String t){name=n;teamName=t;registered=true;}
    Participant(String n){this(n,"Unassigned");}
    void printStatus(){System.out.println(name+" | "+teamName+" | Registered:"+registered);}
    public static void main(String[] args){
        String[] names={"Ravi","Meera","Karthik","Divya"};
        String[] teams={"ByteBusters","","CodeCrafters",""};
        for(int i=0;i<names.length;i++){
            Participant p=teams[i].isEmpty()?new Participant(names[i]):new Participant(names[i],teams[i]);
            p.printStatus();
        }
    }
}