import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

class Affichage{
        
    public void functionToPass(String message) {
        String [] split = message.split(" ");
        for (int i=0; i<split.length; i++)
            System.out.println(split[i]);
    }
    
    public void outerFunction(Object object, Method method, String message) throws Exception {
        Object[] parameters = new Object[1];
        parameters[0] = message;
        method.invoke(object, parameters);
    }

    public static void main(String[] args) throws Exception{
        Class[] parameterTypes = new Class[1];
        parameterTypes[0] = String.class;
        Method functionToPass = Affichage.class.getMethod("functionToPass", parameterTypes[0]);
        Affichage main = new Affichage();
        main.outerFunction(main, functionToPass, "This is the input");
    }
/*
    public static long calcuTime1() {
        long  startTime = System.nanoTime();
        fonc();
       long stopTime = System.nanoTime();
       return stopTime - startTime;

        
    }
    public static long calcuTime2() {
        long  startTime = System.nanoTime();
        fonc2();
       long stopTime = System.nanoTime();
       return stopTime - startTime;

        
    }
    public static void fonc() {
        int age = 50;
        if(age>=18){
            System.out.println("good");
        }else{
            System.out.println("not good");
        }

    }
    public static int fonc2() {
        int a=0;
        for(int i=0;i<300;i++){
            a++;
        }
        return a;
    }
    public static void main(String[] args) {

        System.out.println(calcuTime1());   
        System.out.println(fonc2());   
        System.out.println(calcuTime2());   

    }*/
}