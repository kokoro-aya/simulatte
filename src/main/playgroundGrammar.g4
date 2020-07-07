/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar playgroundGrammar;

top_level: statements? EOF;

IDENTIFIER: IDENT_HEAD IDENT_CHAR*;
fragment IDENT_HEAD: [a-z] | [A-Z] | '_';
fragment IDENT_CHAR: [0-9] | IDENT_HEAD;

literal:    numeric_literal | boolean_literal | char_sequence_literal;
numeric_literal: '-'? (integer_literal | double_literal);
char_sequence_literal: STRING_LITERAL | CHARACTER_LITERAL;

integer_literal: DECIMAL_LITERAL;
double_literal: DECIMAL_LITERAL '.' DECIMAL_LITERAL?
              | DECIMAL_LITERAL? '.' DECIMAL_LITERAL
              ;
DECIMAL_LITERAL: DECIMAL_DIGIT+;
fragment DECIMAL_DIGIT: '0' .. '9';

STRING_LITERAL: '"' (ESC|.)*? '"';
fragment ESC: '\\' | '\\\\';

CHARACTER_LITERAL: '\'' CHAR '\'';
fragment CHAR: ~["\\EOF\n];

WS: [ \t\n\r]+ -> skip;

expression: assignment_expression                       # assignmentExpr
          | literal_expression                          # literalValueExpr
          | function_call_expression                    # function_call
          | member_expression                           # memberExpr
          | variable_expression                         # variableExpr
          | <assoc=right> expression EXP expression   # exponentExpr
          | expression op=(MUL | DIV | MOD) expression  # mulDivModExpr
          | expression op=(ADD | SUB) expression        # addSubExpr
          | expression op=(AND | OR) expression         # isNestedCondition
          | NOT expression                              # isNegativeCondition
          | expression op=(GT | LT | GEQ | LEQ) expression # ariComparativeExpr
          | expression op=(EQ | NEQ) expression         # boolComparativeExpr
          | pattern op=(MULEQ | DIVEQ | MODEQ | ADDEQ | SUBEQ) expression # ariAssignmentExpr
          | '(' expression ')'                          # parenthesisExpr
          | expression op=(UNTIL | THROUGH) expression  # rangeExpression
          ;

assignment_expression: pattern '=' expression;

literal_expression: literal;

member_expression: variable_expression '.' IDENTIFIER
                 | variable_expression '.' DECIMAL_LITERAL
                 | variable_expression '.' function_call_expression
                 ;
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
EXP: '^';

GT: '>'; LT: '<'; GEQ: '>='; LEQ: '<='; EQ: '=='; NEQ: '!=';
MULEQ: '*='; DIVEQ: '/='; MODEQ: '%='; ADDEQ: '+='; SUBEQ: '-=';

variable_expression: IDENTIFIER;

function_call_expression: function_name ('()' | '(' call_argument_clause ')');
call_argument_clause:  call_argument (',' call_argument)*;
call_argument: expression;

boolean_literal: 'true' | 'false';
AND: '&&';
OR: '||';
NOT: '!';

UNTIL: '..<';
THROUGH: '...';

statement: expression ';'?
         | declaration ';'?
         | loop_statement ';'?
         | branch_statement ';'?
         | control_transfer_statement ';'?
         | return_statement ';'?
         ;

statements: statement+;

loop_statement: for_in_statement
              | while_statement
              | repeat_while_statement
              ;

for_in_statement: 'for' pattern 'in' expression code_block;

while_statement: 'while' expression code_block;

repeat_while_statement: 'repeat' code_block 'while' expression;

branch_statement: if_statement;

if_statement: 'if' expression code_block else_clause?;
else_clause: 'else' code_block | 'else' if_statement;

control_transfer_statement: break_statement | continue_statement;

break_statement: 'break';
continue_statement: 'continue';

return_statement: 'return' expression;

declaration: constant_declaration
           | variable_declaration
           | function_declaration
           ;

code_block: '{' statements? '}';

constant_declaration: 'let' pattern '=' expression;

variable_declaration: 'var' pattern '=' expression;

function_declaration: 'func' function_name function_signature function_body;
function_name: IDENTIFIER;
function_signature: parameter_clause function_result_type?;
function_result_type: ARROW type;
function_body: code_block;
ARROW: '->';

parameter_clause: '()' | '(' parameter_list ')';
parameter_list: parameter (',' parameter)*;
parameter: param_name type_annotation;
param_name: IDENTIFIER;

type_annotation: ':' type;

type
    : 'Int'
    | 'Bool'
    | 'Double'
    | 'Character'
    | 'String'
    | 'Void'
    ;

pattern: identifier_pattern | wildcard_pattern;
wildcard_pattern: '_';
identifier_pattern: IDENTIFIER;