class Palindrome {
    static boolean isPalindromeIterative(String s){
        int i=0,j=s.length()-1;
        while(i<j) if(s.charAt(i++)!=s.charAt(j--)) return false;
        return true;
    }
    static boolean isPalindromeRecursive(String s){
        if(s.length()<=1) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        return isPalindromeRecursive(s.substring(1,s.length()-1));
    }
    static boolean isPalindromeArrayReversal(String s){
        char[] arr=s.toCharArray();
        String rev=new StringBuilder(new String(arr)).reverse().toString();
        return s.equals(rev);
    }
    public static void main(String[] args){
        String s="madam";
        System.out.println(isPalindromeIterative(s));
        System.out.println(isPalindromeRecursive(s));
        System.out.println(isPalindromeArrayReversal(s));
    }
}