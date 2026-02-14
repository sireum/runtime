// Adapted from https://json.org with support for comments and trailing commas

grammar JSONC;

options {
	output = AST;
	ASTLabelType = Object;
	k = 2;
}

@header { package org.sireum.parser.json; }

@lexer::header { package org.sireum.parser.json; }


valueFile: value EOF ;

value: stringLit | doubleLit | intLit | object | array | boolLit | nullLit ;

object: '{' ( keyValue ( ',' keyValue )* ','? )? '}' ;

keyValue: STRING ':' value ;

array: '[' ( value (',' value)* ','? )? ']' ;

stringLit: STRING ;

doubleLit: NUMBER ;

intLit: INTEGER ;

boolLit: 'true' | 'false' ;

nullLit: 'null' ;

STRING: '"' ( ESC | ~ ( '"' | '\\' | '\u0000' .. '\u001F' ) )* '"' ;

INTEGER: '-'? INT ;

NUMBER: '-'? INT ( '.' ( '0' .. '9' )+ )? EXP? ;

fragment ESC: '\\' ( '"' | '\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' | 'u' HEX HEX HEX HEX ) ;

fragment HEX: ( '0' .. '9' ) | ( 'a' .. 'f' ) | ( 'A' .. 'F' ) ;

fragment INT: '0' | ( '1' .. '9' ) ( '0' .. '9' )* ;

fragment EXP: ( 'E' | 'e' ) ( '+' | '-' )? INT ;

WS: ( ' ' | '\n' | '\r' | '\t' )+ {$channel=HIDDEN;} ;

LINE_COMMENT: '//' ~( '\n' | '\r' )* {$channel=HIDDEN;} ;

BLOCK_COMMENT: '/*' ( ~ '*' | '*' ~ '/' )* '*/' {$channel=HIDDEN;} ;
