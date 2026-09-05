class ReverseName {
    static String reverse(String s){return new StringBuilder(s).reverse().toString();}
    public static void main(String[] args){
        String name="Sunil";
        System.out.println("Original="+name+" Reversed="+reverse(name));
    }
}