package lsi.ast.expressions;

public sealed interface Expression
        permits BinaryExpr,
                UnaryExpr,
                CastExpr,
                IdentifierExpr,
                IntLiteral,
                DoubleLiteral,
                BooleanLiteral,
                FunctionCallExpr {
}
