package org.example.java.tutorial.java15.sealed;

public sealed interface Expr {
    int eval();
}

record Const(int value) implements Expr {
    @Override
    public int eval() {
        return value;
    }
}

// Const can inherit from Expr because it in the same file
// subclass of Expr must be final, sealed or non-sealed. In this case, Add and Mul are final because they are records (record is implicitly final)

record Add(Expr left, Expr right) implements Expr {
    @Override
    public int eval() {
        return left.eval() + right.eval();
    }
}

record Mul(Expr left, Expr right) implements Expr {
    @Override
    public int eval() {
        return left.eval() * right.eval();
    }
}
