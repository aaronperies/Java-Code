public class Program {
    // just making an unnecessary constructor
    public Program(){
        super();
    }

    public static void main(String[] args) {
        //this tutorial is about typecasting and converting other data types into different data types
        /*there are 2 types of casting, widening and narrowing*/
        int age = 23;
        double ageCast = age;
        System.out.println(age);
        System.out.println(ageCast);

        double value = 3.761;
        int valueCast = (int) value;
        System.out.println(value);
        System.out.println(valueCast);

        int num = 4112;
        double currency = 362.715;
        String numConvert = String.valueOf(num);
        String currencyConvert = String.valueOf(currency);
        System.out.println(num + " : " + numConvert);
        System.out.println(currency + " : " + currencyConvert);
    }
}
