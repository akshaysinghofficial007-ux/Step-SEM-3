class CSVParser {
    public static void main(String[] args){
        String line="Ananya Verma,RA2211003010123,CSE";
        String[] parts=line.split(",");
        if(parts.length!=3) System.out.println("Invalid Record");
        else System.out.println("Name:"+parts[0]+" Roll No:"+parts[1]+" Dept:"+parts[2]);
    }
}