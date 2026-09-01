# CuentaBancaria — Java OOP Exercise

A small `CuentaBancaria` (BankAccount) class demonstrating encapsulation,
class invariants, constructor chaining with `this(...)`, and a correct
`equals()`/`hashCode()` implementation.

## Files

- `CuentaBancaria.java` — the class itself.
- `Main.java` — a demo that exercises validation and equality.

## How to run

```bash
javac CuentaBancaria.java Main.java
java Main
```

## Design decisions

The `titular` and `saldo` fields are declared `private` so that external code
cannot mutate the account's internal state directly; this encapsulation
forces every state change to go through methods (`depositar`, `retirar`) that
can enforce business rules. The class maintains the invariant `saldo >= 0`
at every point in its lifecycle: the full constructor rejects a negative
initial balance, and `retirar` refuses to withdraw more than the current
balance, so the object can never be observed in an invalid state. The
secondary constructor delegates to the full constructor via
`this(numeroCuenta, titular, 0.0)` instead of repeating the validation logic,
which keeps invariant-checking code in a single place and prevents divergent
validation rules from creeping in later. `equals()` and `hashCode()` are both
based solely on `numeroCuenta`, since that is the field that uniquely
identifies a bank account regardless of who currently holds it or how much
money it contains, which keeps the two methods consistent with each other as
required by the Java contract. Because `hashCode()` is derived from the exact
same field used in `equals()`, two objects considered equal always produce
the same hash code, so `CuentaBancaria` instances behave correctly when
stored in hash-based collections such as `HashSet` or used as keys in a
`HashMap`. Finally, `numeroCuenta` is declared `final`, reflecting the
decision that an account's identity should never change after creation,
which also protects the equals/hashCode contract from being silently broken
by later mutation.
