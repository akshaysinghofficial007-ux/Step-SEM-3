import java.util.*;
class FirstUnique {
    public static void main(String[] args){
        String s="swiss";
        Map<Character,Integer> map=new HashMap<>();
        for(char c:s.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        for(char c:s.toCharArray()){
            if(map.get(c)==1){System.out.println(c);return;}
        }
        System.out.println("None");
    }
}