
package com.company.analizadores

%%

%class Lexer
%public
%unicode
%line
%column
%type Token

%{

	private Token token(int type) {
		return new Token(type, yyline + 1, yycolumn + 1);
	}

	private Token token(int type, Object value) {
		return new Token(type, yyline + 1, yycolumn + 1, value);
	}

%}

%eofval{
	return token(0);
%eofval}
%eofclose

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\s\t\f]

Integer = 0|[1-9][0-9]*
Id = [a-zA-Z]\w*

%%

<YYINITIAL> {

	{Id}
	{ return token(1, yytext()); }

	{Integer}
	{ return token(2, yytext()); }

	"("
	{ return token(3, yytext()); }

	")"
	{ return token(4, yytext()); }

	"+"
	{ return token(5, yytext()); }

	"*"
	{ return token(6, yytext()); }

	{WhiteSpace}
	{ /* Ignore */ }

}

