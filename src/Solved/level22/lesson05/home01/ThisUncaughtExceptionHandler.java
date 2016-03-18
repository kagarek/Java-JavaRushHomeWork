package solved.level22.lesson05.home01;

public class ThisUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        String e_type = e.getClass().getSimpleName();
        String e_cause = e.getMessage();
        String t_name = t.getName();
        return String.format(string, e_type, e_cause, t_name);
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        String e_type = e.getCause().getClass().getSimpleName();
        String e_cause = e.getMessage();
        String t_name = t.getName();
        return String.format(string, e_cause ,e_type, t_name);
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        String e_type = e.getCause().getClass().getSimpleName();
        String e_cause = e.toString();
        String t_name = t.getName();
        return String.format(string, t_name, e_type, e_cause);
    }
}

