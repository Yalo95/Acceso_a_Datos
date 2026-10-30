import java.util.Scanner;

public class ScannerGlobal {//Scanner basado en Singleton, para que de menos errores. Más efieciente y mayor control de cuando se cierra el scanner.

    private static ScannerGlobal instance;
    private Scanner scanner;

    private ScannerGlobal() {
        this.scanner = new Scanner(System.in);
    }

    public static ScannerGlobal getInstance() {
        if (instance == null) {
            instance = new ScannerGlobal();
        }
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
