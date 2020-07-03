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

// literal:    numeric_literal | boolean_literal;
// numeric_literal: '-'? integer_literal;

INTEGER_LITERAL: DECIMAL_LITERAL;
DECIMAL_LITERAL: DECIMAL_DIGIT+;
fragment DECIMAL_DIGIT: '0' .. '9';

WS: [ \t\n\r]+ -> skip;

expression: 'moveForward()'         # moveForward
          | 'turnLeft()'            # turnLeft
          | 'toggleSwitch()'        # toggleSwitch
          | 'collectGem()'          # collectGem
          | conditional_expression  # checkTruth
          | range_expression        # rangedStep
          | function_call_expression # function_call
          ;

function_call_expression: function_name ('()' | '(' call_argument_clause ')');
call_argument_clause:  call_argument (',' call_argument)*;
call_argument: expression;

conditional_expression:  boolean_literal                                                # isBoolean
                      | 'isOnGem'                                                       # isOnGem
                      | 'isOnOpenedSwitch'                                              # isOnOpenedSwitch
                      | 'isOnClosedSwitch'                                              # isOnClosedSwitch
                      | 'isBlocked'                                                     # isBlocked
                      | 'isBlockedLeft'                                                 # isBlockedLeft
                      | 'isBlockedRight'                                                # isBlockedRight
                      | conditional_expression op=(AND | OR) conditional_expression     # isNestedCondition
                      | NOT conditional_expression                                      # isNegativeCondition
                      ;

boolean_literal: 'true' | 'false';
AND: '&&';
OR: '||';
NOT: '!';

range_expression: INTEGER_LITERAL op=(UNTIL | THROUGH) INTEGER_LITERAL # rangeHandler;
UNTIL: '..<';
THROUGH: '...';

statement: expression ';'?
         | declaration ';'?
         | loop_statement ';'?
         | branch_statement ';'?
         | control_transfer_statement ';'?
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
    | 'Void'
    ;

pattern: identifier_pattern | wildcard_pattern;
wildcard_pattern: '_';
identifier_pattern: IDENTIFIER;