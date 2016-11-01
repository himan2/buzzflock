package regular;

abstract class  A {
abstract int  disp();
}

class B extends A{
int disp() {return HELLO;}
}
class C extends A{
int disp() {return 123456;}
}
class abc{
public static void main(String args[]){
xyz a;
a=new B();
System.out.println("message : "+a.disp()+);
a=new C();
System.out.println("message: "+a.disp()+);
}}