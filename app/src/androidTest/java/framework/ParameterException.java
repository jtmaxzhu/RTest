package framework;

/**
 * Created by Administrator on 2018/7/25.
 */

public class ParameterException extends Exception {
 /**
         * Constructs an <code>IndexOutOfBoundsException</code> with no
         * detail message.
         */
        public ParameterException() {
            super();
        }

        /**
         * Constructs an <code>IndexOutOfBoundsException</code> with the
         * specified detail message.
         *
         * @param   s   the detail message.
         */
        public ParameterException(String s) {
            super(s);
        }

    }

