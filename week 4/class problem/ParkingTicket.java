class ParkingTicket {
    String vehicleNo; double ratePerMinute;
    ParkingTicket(String v,double r){vehicleNo=v;ratePerMinute=r;}
    final double calculateFine(int m){return m*ratePerMinute;}
    final void printReceipt(int m){System.out.println(vehicleNo+" - Fine: Rs "+calculateFine(m));}
    public static void main(String[] args){
        String[] nos={"TN09AB1234","TN22CD5678","TN09EF9012","TN10GH3456"};
        double[] rates={2,2,3,2};
        int[] mins={15,0,-5,8};
        for(int i=0;i<nos.length;i++){
            ParkingTicket t=new ParkingTicket(nos[i],rates[i]);
            if(mins[i]>0) t.printReceipt(mins[i]);
            else System.out.println(nos[i]+" - No fine, within allotted time");
        }
    }
}