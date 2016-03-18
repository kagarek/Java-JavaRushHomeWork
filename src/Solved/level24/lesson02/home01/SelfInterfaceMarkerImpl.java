package solved.level24.lesson02.home01;

/**
 * Created by ima on 4/10/2015.
 */
public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker
{
    public void method1() { method2();}
    public void method2() { method1();}
}
