package lsi.ast.types;

public sealed interface Type
        permits IntegerType,
                DoubleType,
                BooleanType,
                StringType {
}
