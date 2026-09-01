public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Creación de cuentas ===");
        CuentaBancaria cuenta1 = new CuentaBancaria("ES001", "Ana Pérez", 100.0);
        CuentaBancaria cuenta2 = new CuentaBancaria("ES002", "Luis Gómez"); // usa this(...)
        System.out.println(cuenta1);
        System.out.println(cuenta2);

        System.out.println("\n=== 2. Depósitos y retiros válidos ===");
        cuenta1.depositar(50.0);
        cuenta1.retirar(30.0);
        System.out.println("Saldo cuenta1 tras operaciones: " + cuenta1.getSaldo());

        System.out.println("\n=== 3. Validaciones (deben lanzar excepción) ===");
        try {
            cuenta1.depositar(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Depósito rechazado: " + e.getMessage());
        }

        try {
            cuenta1.retirar(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Retiro rechazado: " + e.getMessage());
        }

        try {
            cuenta1.retirar(999999);
        } catch (IllegalStateException e) {
            System.out.println("Retiro rechazado: " + e.getMessage());
        }

        try {
            new CuentaBancaria("ES003", "Carlos Ruiz", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Creación rechazada: " + e.getMessage());
        }

        System.out.println("\n=== 4. equals() y hashCode() ===");
        CuentaBancaria cuenta1Duplicada = new CuentaBancaria("ES001", "Ana Pérez (otro objeto)", 999.0);
        System.out.println("cuenta1.equals(cuenta1Duplicada): " + cuenta1.equals(cuenta1Duplicada)); // true, mismo numeroCuenta
        System.out.println("cuenta1.equals(cuenta2): " + cuenta1.equals(cuenta2)); // false
        System.out.println("cuenta1.hashCode() == cuenta1Duplicada.hashCode(): " +
                (cuenta1.hashCode() == cuenta1Duplicada.hashCode())); // true, porque equals -> mismo hashCode
    }
}
