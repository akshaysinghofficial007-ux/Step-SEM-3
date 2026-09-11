class MaskPhone {
    public static void main(String[] args){
        String phone="9876543210";
        if(phone.length()==10 && phone.matches("\\d+"))
            System.out.println("XXXXXX-"+phone.substring(6));
        else System.out.println("Invalid phone number");
    }
}