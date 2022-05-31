public class MyClass {
    @Test public static void m1() { }
    public static void m2() { }
    @Test public static void m3() {
        throw new RuntimeException("Exception");
    }
    public static void m4() { }
    @Test public static void m5() { }
    public static void m6() { }
    @Test public static void m7() {
        throw new RuntimeException("No suitable parameters");
    }
    public static void m8() { }
    @Test public void m9(){
        throw new RuntimeException("Not static");
    }
    @Test public static void m11(double x){
        if(x>1000)
            throw new RuntimeException("Invalid number "+x);
    }
    @Test public static void m10(int line, String error){
        throw  new RuntimeException("Exception "+error+" at line "+line);
    }
    @Test public void m12(double x,int y, String z){
        if(x>3000 ||y<2000)
        throw  new RuntimeException("Exception for generated values: "+x+", "+y+", "+z);
    }

}