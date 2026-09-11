class BankRef {
    public static void main(String[] args){
        String raw=" hdf03022600042 ";
        String ref=raw.trim();
        ref=ref.substring(0,3).toUpperCase()+ref.substring(3);
        if(ref.length()!=14) System.out.println("Invalid: wrong length");
        else{
            boolean ok=true;
            for(int i=0;i<3;i++) if(!Character.isLetter(ref.charAt(i))) ok=false;
            for(int i=3;i<14;i++) if(!Character.isDigit(ref.charAt(i))) ok=false;
            if(!ok) System.out.println("Invalid");
            else{
                String bank=ref.substring(0,3);
                String date=ref.substring(3,9);
                String seq=ref.substring(9);
                System.out.println("["+bank+"] DATE:"+date.substring(0,2)+"/"+date.substring(2,4)+"/"+date.substring(4,6)+" | SEQ:"+seq);
            }
        }
    }
}