class IdCard {
    String name; int booksIssued;
    IdCard(String n,int b){name=n;booksIssued=b;}
    public static void main(String[] args){
        IdCard ravi=new IdCard("Ravi",0);
        IdCard duplicate=ravi;
        duplicate.booksIssued=3;
        IdCard separate=new IdCard("Ravi",3);
        System.out.println("Ravi booksIssued:"+ravi.booksIssued);
        System.out.println("duplicate==ravi:"+(duplicate==ravi));
        System.out.println("separate==ravi:"+(separate==ravi));
    }
}