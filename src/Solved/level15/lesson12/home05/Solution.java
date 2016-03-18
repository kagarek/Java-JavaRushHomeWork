package solved.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {

    Solution(){}
    Solution(Integer i, Integer s){}
    Solution(String i, String s){}

    private Solution(String s) {}
    private Solution(Number n) {}
    private Solution(Integer i) {}

    protected Solution(Short s) {}
    protected Solution(Double d) {}
    protected Solution(Float f) {}

    public Solution(Object o) {}
    public Solution(Object o1, Object o2) {}
    public Solution(String s, Number n) {}
}

