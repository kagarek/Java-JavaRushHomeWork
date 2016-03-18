package solved.level21.lesson05.task03;

import java.util.Date;

/* Ошибка в equals/hashCode
Исправьте ошибки реализаций методов equals и hashCode для класса Solution
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution1 = (Solution) o;

        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (anInt != solution1.anInt) return false;
        if (date != null ? !date.equals(solution1.date) : solution1.date != null) return false;
        if (solution != null ? !solution.equals(solution1.solution) : solution1.solution != null) return false;
        if (string != null ? !string.equals(solution1.string) : solution1.string != null) return false;

        return true;
    }
    /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        Solution solution1 = (Solution) o;
        if (anInt != solution1.anInt) return false;
        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;

        if (string != null ? !string.equals(solution1.string) : solution1.string != null) return false;

        if (date != null ? !date.equals(solution1.date) : solution1.date != null) return false;

        if (solution != null
                ?
                solution1.solution != null
                        ?
                        !solution.equals(solution1.solution)
                        :
                        true
                :
                solution1.solution != null) return false;

        return true;
    }*/

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = anInt;
        result = 31 * result + (string != null ? string.hashCode() : 0);
        temp = Double.doubleToLongBits(aDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

/*    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }*/

    public static void main(String[] args)
    {
        Date date1 = new Date("1985/09/12");
        Solution s = new Solution(2,"test1",3.0d, date1, null);

        Solution s1 = new Solution(1,"test",2.0d, date1, s);
        Solution s2 = new Solution(1,"test",2.0d, date1, s);
        System.out.println("1. Equals (not null) " + s1.equals(s2));

        s1 = new Solution(1,null,0d, null, null);
        s2 = new Solution(1,null,0d, null, null);

        System.out.println("2. Equals (null) " + s1.equals(s2));

        s1 = new Solution(1,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, date1, s);
        System.out.println("3. Not equals (int vs. int) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.5d, date1, s);
        System.out.println("4. Not equals (double vs. double) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test1",2.0d, date1, s);
        System.out.println("5. Not equals (string vs. string) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,null,2.0d, date1, s);
        System.out.println("6. Not equals (string vs. null) " + s1.equals(s2));

        s1 = new Solution(2,null,2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, date1, s);
        System.out.println("7. Not equals (null vs. string) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, new Date(), s);
        System.out.println("8. Not equals (date date1 vs. new Date()) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, null, s);
        s2 = new Solution(2,"test",2.0d, date1, s);
        System.out.println("9. Not equals (date null vs. date1) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, null, s);
        System.out.println("10. Not equals (date date1 vs. null) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, date1, s1);
        System.out.println("11. Not equals (solution s vs. s1) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, null);
        s2 = new Solution(2,"test",2.0d, date1, s);
        System.out.println("12. Not equals (solution null vs. s) " + s1.equals(s2));

        s1 = new Solution(2,"test",2.0d, date1, s);
        s2 = new Solution(2,"test",2.0d, date1, null);
        System.out.println("13. Not equals (solution s vs. null) " + s1.equals(s2));
    }
}
