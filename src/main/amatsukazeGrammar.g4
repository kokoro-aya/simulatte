/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar amatsukazeGrammar;
// 天津風（あまつかぜ）とは、「空高く吹き抜ける風」を表した単語である。

top_level: statements? EOF;

IDENTIFIER: IDENT_HEAD IDENT_CHAR*;
fragment IDENT_HEAD: [a-z] | [A-Z] | '_';
fragment IDENT_CHAR: [0-9] | IDENT_HEAD;

literal:    numeric_literal | boolean_literal | char_sequence_literal | nil_literal;
numeric_literal: '-'? (integer_literal | double_literal);
boolean_literal: 'true' | 'false';
char_sequence_literal: STRING_LITERAL | CHARACTER_LITERAL;
nil_literal: 'nil';

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

//comment: '//' ~('\r' | '\n')*
//       | '/*' ~('*/')* '*/'
//       ;

WS: [ \t\n\r]+ -> skip;

expression: assignment_expression                       # assignmentExpr
          | subscript_expression                        # subscriExpr
          | literal_expression                          # literalValueExpr
          | function_call_expression                    # function_call
          | struct_call_expression                      # struct_call
          | member_expression                           # memberExpr
          | this_expression                             # thisExpr
          | variable_expression                         # variableExpr
          | expressional_function_declaration           # exprFuncDeclExpr
          | <assoc=right> expression EXP expression     # exponentExpr
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

literal_expression: literal | array_literal;

array_literal: '{}' | '{' array_literal_item (',' array_literal_item)* '}';
array_literal_item: expression;

this_expression: 'this' IDENTIFIER;

member_expression: explicit_member_expression;
explicit_member_expression: variable_expression '.' IDENTIFIER               # namedExpMemberExpr
                          | variable_expression '.' DECIMAL_LITERAL          # numberedExpMemberExpr
                          | variable_expression '.' function_call_expression # funcExpMemberExpr
                          | explicit_member_expression '.' IDENTIFIER        # recursiveExpMemberExpr
                          | function_call_expression '.' IDENTIFIER          # memberOfFuncCallExpr
                          ;
//implicit_member_expression: '.' IDENTIFIER;

subscript_expression: (variable_expression | member_expression) '[' subscript ']';
subscript: IDENTIFIER | DECIMAL_LITERAL;

variable_expression: IDENTIFIER;

function_call_expression: function_name ('()' | '(' call_argument_clause ')');
call_argument_clause:  call_argument (',' call_argument)*;
call_argument: expression;

struct_call_expression: 'new' struct_name ('()' | '(' call_argument_clause ')');

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
EXP: '^';

GT: '>'; LT: '<'; GEQ: '>='; LEQ: '<='; EQ: '=='; NEQ: '!=';
MULEQ: '*='; DIVEQ: '/='; MODEQ: '%='; ADDEQ: '+='; SUBEQ: '-=';

AND: '&&';
OR: '||';
NOT: '!';

UNTIL: '..<';
THROUGH: '...';

dot_operator: '.' IDENTIFIER;

statement: expression ';'?
         | declaration ';'?
         | loop_statement ';'?
         | branch_statement ';'?
         | control_transfer_statement ';'?
         | return_statement ';'?
         | yield_statement ';'?
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

yield_statement: 'yield' expression;

declaration: constant_declaration # constantDecl
           | variable_declaration # variableDecl
           | function_declaration # functionDecl
           | enum_declaration     # enumDecl
           | struct_declaration   # structDecl
           ;

code_block: '{' statements? '}';

constant_declaration: 'let' pattern (':' type)? ('=' expression)?;

variable_declaration: 'var' pattern (':' type)? '=' expression;

function_declaration: 'func' GENERATOR_IDENT? function_name? function_signature function_body;
function_name: IDENTIFIER;
function_signature: parameter_clause function_result_type?;
function_result_type: ARROW type;
function_body: code_block;
ARROW: '->';
GENERATOR_IDENT: '**';

expressional_function_declaration: function_declaration;
arrowfun_declaration: function_signature '=>' function_body;

parameter_clause: '()' | '(' parameter_list ')';
parameter_list: '&'? parameter (',' '&'? parameter)*;
parameter: param_name type_annotation;
param_name: IDENTIFIER;

enum_declaration: 'enum' enum_name '{' enum_members+ '}';
enum_name: IDENTIFIER;
enum_members: enum_member (',' enum_member)*;
enum_member: IDENTIFIER;

struct_declaration: 'struct' struct_name type_inheritance_clause? parameter_clause struct_body;
struct_name: IDENTIFIER;
struct_body: '{' struct_initializer? struct_member* '}';
struct_initializer: 'init' '{' attribute_assignment* '}';
attribute_assignment: 'super.'? IDENTIFIER ':' ( function_declaration
                                               | arrowfun_declaration
                                               | expression
                                               );

struct_member: 'this.'? IDENTIFIER ':' ( function_declaration
                                       | arrowfun_declaration
                                       | expression
                                       );

type_inheritance_clause: ':>' type;
type_annotation: ':' type;

type
    : IDENTIFIER        # simpleType
    | 'Array<' type '>' # arrayType
    | '[' type ']'      # arrayTypeSub
    ;

pattern: identifier_pattern | wildcard_pattern | member_expression_pattern | subscript_expression_pattern;
wildcard_pattern: '_';
identifier_pattern: IDENTIFIER;
member_expression_pattern: member_expression;
subscript_expression_pattern: subscript_expression;