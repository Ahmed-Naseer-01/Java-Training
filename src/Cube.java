public class Cube {

    public float height;
    public float length;
    public float breath;
    Cube(float height, float length, float breath)
    {
        this.height = height;
        this.length = length;
        this.breath = breath;
    }

    public double area()
    {
        double area = 2*(length*height + height*breath + length*breath);
        return  area;
    }
    public double volume()
    {
        double volume = length*height*breath;
        return  volume;
    }

}
