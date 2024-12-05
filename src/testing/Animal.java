package testing;

public class Animal {
    private int legs = 4;
    public int head = 1;
    protected boolean flag = true;
    int check = 0;

    public static void main(String [] args)
    {
        Animal an = new Animal();
//        System.out.println(an.flag);
//        an.check;

        Bari b = new Bari(90);
//        int temp = b.temp;

//        System.out.println(b.temp1);
        b.display();
    }
}
class Bari
{
    private int temp;
    protected int temp1 = 78;
    Bari(int temp)
    {
        this.temp = temp;
    }
    public void display(){
        System.out.println(temp);
    }
}