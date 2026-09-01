import java.util.Objects;

public class CuentaBancaria {

    private final String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = validarTexto(numeroCuenta, "El número de cuenta no puede estar vacío");
        this.titular = validarTexto(titular, "El titular no puede estar vacío");

        if (!Double.isFinite(saldoInicial) || saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser un número finito y no negativo");
        }

        this.saldo = saldoInicial;
    }

    public CuentaBancaria(String numeroCuenta, String titular) {
        this(numeroCuenta, titular, 0.0);
    }
    private static String validarTexto(String valor, String mensaje) {
        if (valor == null) {
            throw new IllegalArgumentException(mensaje);
        }

        String normalizado = valor.trim();
        if (normalizado.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }

        return normalizado;
    }

    private static double validarMonto(double monto, String mensaje) {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(mensaje);
        }
        return monto;
    }

    public void depositar(double monto) {
        this.saldo += validarMonto(monto, "El monto a depositar debe ser un número finito y mayor que cero");
    }

    public void retirar(double monto) {
        double montoValido = validarMonto(monto, "El monto a retirar debe ser un número finito y mayor que cero");

        if (montoValido > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente: no se puede dejar el saldo en negativo");
        }

        this.saldo -= montoValido;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return numeroCuenta.equals(that.numeroCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta);
    }
}