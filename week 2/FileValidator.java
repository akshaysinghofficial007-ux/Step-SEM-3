class FileValidator {
    public static void main(String[] args){
        String f="Assignment1.PDF";
        int idx=f.lastIndexOf('.');
        if(idx==-1) System.out.println("Rejected");
        else{
            String ext=f.substring(idx+1);
            if(ext.equalsIgnoreCase("pdf")||ext.equalsIgnoreCase("docx")||ext.equalsIgnoreCase("zip"))
                System.out.println("Accepted");
            else System.out.println("Rejected - invalid file type");
        }
    }
}