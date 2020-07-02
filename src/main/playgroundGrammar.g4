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
BOOLEAN_LITERAL: TRUE | FALSE;
TRUE: 'true';
FALSE: 'false';

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
          ;

conditional_expression: 'isOnGem'                                                       # isOnGem
                      | 'isOnOpenedSwitch'                                              # isOnOpenedSwitch
                      | 'isOnClosedSwitch'                                              # isOnClosedSwitch
                      | 'isBlocked'                                                     # isBlocked
                      | 'isBlockedLeft'                                                 # isBlockedLeft
                      | 'isBlockedRight'                                                # isBlockedRight
                      | BOOLEAN_LITERAL                                                 # isBoolean
                      | conditional_expression (AND | OR) conditional_expression        # isNestedCondition
                      ;
AND: '&&';
OR: '||';

range_expression: INTEGER_LITERAL (UNTIL | THROUGH) INTEGER_LITERAL # rangeHandler;
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

for_in_statement: 'for' PATTERN 'in' expression code_block;

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

constant_declaration: 'let' PATTERN '=' expression;

variable_declaration: 'var' PATTERN '=' expression;

function_declaration: 'func' function_signature function_body;
function_signature: IDENTIFIER '()';
function_body: code_block;

PATTERN: IDENTIFIER_PATTERN | WILDCARD_PATTERN;
WILDCARD_PATTERN: '_';
IDENTIFIER_PATTERN: IDENTIFIER;