class BMI {
    static String status(double bmi){
        if(bmi<18.5) return "Underweight";
        else if(bmi<25) return "Normal";
        else if(bmi<30) return "Overweight";
        else return "Obese";
    }
    public static void main(String[] args){
        double[] h={1.75,1.60};
        double[] w={70,90};
        for(int i=0;i<h.length;i++){
            double bmi=w[i]/(h[i]*h[i]);
            System.out.println("BMI="+bmi+" Status="+status(bmi));
        }
    }
}