class VowelConsonant {
    public static void main(String[] args){
        String s="Java Programming".toLowerCase();
        int v=0,c=0;
        for(char ch:s.toCharArray()){
            if(ch==' ') continue;
            if("aeiou".indexOf(ch)>=0) v++; else c++;
        }
        System.out.println("Vowels="+v+" Consonants="+c);
    }
}