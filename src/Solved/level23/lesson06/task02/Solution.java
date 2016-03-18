package solved.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public static final class Constants
    {
        static final String S1 = "Server is not accessible for now.";
        static final String S2 = "User is not authorized.";
        static final String S3 = "User is banned.";
        static final String S4 = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.S1);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.S1, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.S2);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.S2, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.S3);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.S3, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.S4);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.S4, cause);
        }
    }
}
