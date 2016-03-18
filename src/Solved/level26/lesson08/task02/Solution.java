package solved.level26.lesson08.task02;

/* Мудрый человек думает раз, прежде чем два раза сказать.
Все методы класса Solution должны быть потоково-безопасными.
Сделайте так, чтобы оба метода могли выполняться одновременно двумя различными трэдами.
synchronized(this) для этого не подходит, используйте другой объект для лока.
*/
public class Solution {
    int var1;
    int var2;
    int var3;
    int var4;
    Object object1 = new Object();
    Object object2 = new Object();

    public static void main(String[] args)
    {
        final Solution solution = new Solution(1,2,3,4);

        Thread thread1 = new Thread() {
            @Override
            public void run()
            {
                super.run();
                System.out.println(solution.getSumOfVar1AndVar2());
                System.out.println(solution.getSumOfVar3AndVar4());
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run()
            {
                super.run();
                System.out.println(solution.getSumOfVar1AndVar2());
                System.out.println(solution.getSumOfVar3AndVar4());
            }
        };

        thread1.start();
        thread2.start();
    }

    public Solution(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public int getSumOfVar1AndVar2() {
        synchronized (object1)
        {
            return var1 + var2;
        }
    }

    public int getSumOfVar3AndVar4() {
        synchronized (object2)
        {
            return var3 + var4;
        }
    }
}
