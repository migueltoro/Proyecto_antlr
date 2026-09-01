grammar PLIModel;

@lexer::members {

    private void lexicalError(String message) {

        getErrorListenerDispatch().syntaxError(
            this,
            null,
            getLine(),
            getCharPositionInLine(),
            message,
            null
        );
    }
}

// ============================================================================ Model
// ============================================================================

model:
	head? goal constraints bounds? bin_vars? int_vars? free_vars? semi_continuous_vars? EOF;

// ============================================================================ Sections
// ============================================================================

head: HEAD_SECTION declaration*;

goal: GOAL_SECTION objective linear_expr;

constraints: CONSTRAINTS_SECTION set_of_constraints+;

bounds: BOUNDS_SECTION set_of_bounds+;

bin_vars: BIN_SECTION set_of_vars+;

int_vars: INT_SECTION set_of_vars+;

free_vars: FREE_SECTION set_of_vars+;

semi_continuous_vars: SEMI_SECTION set_of_vars+;

objective: 'min' | 'max';

// ============================================================================ Declarations
// ============================================================================

declaration:
	type ID '=' expression					# varDeclaration
	| type ID '(' formal_parameters? ')'	# functionDeclaration;

formal_parameters: formal_parameter (',' formal_parameter)*;

formal_parameter: type ID;

type: 'Integer' | 'Double' | 'Boolean' | 'String';

// ============================================================================ Collections
// ============================================================================

var_collection: var (',' var)*;

expr_collection: expression (',' expression)*;

linear_expr_collection: linear_expr (',' linear_expr)*;

// ============================================================================ Set of Elements
// ============================================================================

set_of_constraints: constraint (',' index)* ('|' expression)?;

set_of_bounds: bound (',' index)* ('|' expression)?;

set_of_LinearExprs: linear_expr (',' index)* ('|' expression)?;

set_of_vars: var (',' index)* ('|' expression)?;

set_of_expressions: expression (',' index)* ('|' expression)?;

// ============================================================================ Constraints
// ============================================================================

constraint:
	atomic_constraint		# atomicConstraintExpr
	| compound_constraint	# compoundConstraintExpr;

atomic_constraint:
	linear_expr rel_op expression # relationalConstraint;

compound_constraint:
	'or' '(' rel_op INT ',' atomic_constraint (
		'|' atomic_constraint
	)+ ')'														# orConstraint
	| atomic_constraint '=>' atomic_constraint					# implicationConstraint
	| var '!=' var												# differentValueConstraint
	| var '=' INT '->' atomic_constraint						# indicatorConstraint
	| '(' var '==' var ')'										# equalsConstraint
	| 'allDifferent' '(' set_of_vars ')'						# allDifferentConstraint
	| 'permutation' '(' set_of_vars ';' set_of_expressions ')'	# permutationConstraint
	| var 'in' set_of_expressions								# membershipConstraint
	| var '=' 'MAX' '(' set_of_vars ')'							# maxConstraint
	| var '=' 'MIN' '(' set_of_vars ')'							# minConstraint
	| var '=' 'OR' '(' set_of_constraints ')'					# orBinaryConstraint
	| var '=' 'AND' '(' set_of_constraints ')'					# andBinaryConstraint
	| var '=' 'ABS' '(' set_of_vars ')'							# absConstraint
	| var '=' 'PWL' '(' var ')' ':' pair+						# piecewiseLinearConstraint;

// ============================================================================ Bounds
// ============================================================================

bound:
	var rel_op expression					# oneSideBound
	| expression '<=' var '<=' expression	# twoSideBound;

// ============================================================================ Linear Expressions
// ============================================================================

linear_expr: linear_term slinear_term*;

slinear_term: '+' linear_term # plusTerm | '-' linear_term # minusTerm;

linear_term:
	linear_factor							# factorTerm
	| 'sum' '(' set_of_linear_factor ')'	# sumSetOfTerm;

set_of_linear_factor:
	linear_factor (',' index)* ('|' expression)?;

linear_factor:
	expression? var
;

// ============================================================================ Variables
// ============================================================================

var: ID '[' index_list? ']';

index_list: expression (',' expression)*;

index: ID 'in' expression '..' expression;

// ============================================================================ Piecewise Linear
// ============================================================================

pair: '(' INT ',' INT ')';

// ============================================================================ Expressions
// ============================================================================

expression:
	expression ('*' | '/' | '%') expression				# multiplicativeExpression
	| expression ('+' | '-') expression					# additiveExpression
	| expression ('<' | '<=' | '>' | '>=') expression	# relationalExpression
	| expression ('=' | '!=') expression				# equalityExpression
	| expression ('&&' | '||') expression				# logicalExpression
	| ('+' | '-' | '!') expression						# unaryExpression
	| '(int)' expression								# intCastExpression
	| '(double)' expression								# doubleCastExpression
	| '(' expression ')'								# parenthesizedExpression
	| function_call										# functionCallExpression
	| ID												# identifierExpression
	| DOUBLE											# doubleLiteralExpression
	| INT												# integerLiteralExpression;

// ============================================================================ Function Calls
// ============================================================================

function_call: ID '(' expr_collection? ')';

// ============================================================================ Operators
// ============================================================================

rel_op: '>=' | '>' | '<=' | '<' | '=';

// ============================================================================ Keywords
// ============================================================================

HEAD_SECTION: 'head section';

GOAL_SECTION: 'goal section';

CONSTRAINTS_SECTION: 'constraints section';

BOUNDS_SECTION: 'bounds section';

BIN_SECTION: 'bin';

INT_SECTION: 'int';

FREE_SECTION: 'free';

SEMI_SECTION: 'semi-continuous';

// ============================================================================ Lexer
// ============================================================================

ID: [a-zA-Z_] [a-zA-Z_0-9]*;

INT: ('+' | '-')? [0-9]+;

DOUBLE: ('+' | '-')? INT '.' INT?;

WS: [ \t\r\n]+ -> skip;

COMMENT: '/*' .*? '*/' -> skip;

LINE_COMMENT: '//' ~[\r\n]* -> skip;

ERROR_CHAR:
	. {
          lexicalError(
              "Invalid character: '" + getText() + "'"
          );
      };