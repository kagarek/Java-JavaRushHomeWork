package solved.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by igormakarychev on 12/17/15.
 */
public class CustomInvocationHandler implements InvocationHandler
{
    private SomeInterfaceWithMethods someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods)
    {
        this.someInterfaceWithMethods = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getName() + " in");
        method.invoke(someInterfaceWithMethods,args);
        System.out.println(method.getName() + " out");
        return null;

    }
}
