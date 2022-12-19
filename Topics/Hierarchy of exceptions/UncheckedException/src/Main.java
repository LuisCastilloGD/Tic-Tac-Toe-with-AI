
import java.io.IOException;
import java.io.UncheckedIOException;

// update the class
class UncheckedException extends RuntimeException {
    public UncheckedException(){
        super();
    }

    public UncheckedException(IOException cause) {
        super(cause);
    }

    public UncheckedException(String message, IOException cause) {
        super(message, cause);
    }
}

//do not change the code
class Main {
    public static void main(String[] args) {
        UncheckedException uncheckedException = new UncheckedException();
        System.out.println(uncheckedException instanceof RuntimeException);
    }
}