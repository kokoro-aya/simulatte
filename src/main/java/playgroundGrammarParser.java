// Generated from /Users/irony/IdeaProjects/playground/src/main/playgroundGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class playgroundGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		IDENTIFIER=32, DECIMAL_LITERAL=33, STRING_LITERAL=34, CHARACTER_LITERAL=35, 
		WS=36, ADD=37, SUB=38, MUL=39, DIV=40, MOD=41, EXP=42, GT=43, LT=44, GEQ=45, 
		LEQ=46, EQ=47, NEQ=48, MULEQ=49, DIVEQ=50, MODEQ=51, ADDEQ=52, SUBEQ=53, 
		AND=54, OR=55, NOT=56, UNTIL=57, THROUGH=58, ARROW=59;
	public static final int
		RULE_top_level = 0, RULE_literal = 1, RULE_numeric_literal = 2, RULE_char_sequence_literal = 3, 
		RULE_integer_literal = 4, RULE_double_literal = 5, RULE_expression = 6, 
		RULE_assignment_expression = 7, RULE_literal_expression = 8, RULE_member_expression = 9, 
		RULE_variable_expression = 10, RULE_function_call_expression = 11, RULE_call_argument_clause = 12, 
		RULE_call_argument = 13, RULE_boolean_literal = 14, RULE_statement = 15, 
		RULE_statements = 16, RULE_loop_statement = 17, RULE_for_in_statement = 18, 
		RULE_while_statement = 19, RULE_repeat_while_statement = 20, RULE_branch_statement = 21, 
		RULE_if_statement = 22, RULE_else_clause = 23, RULE_control_transfer_statement = 24, 
		RULE_break_statement = 25, RULE_continue_statement = 26, RULE_return_statement = 27, 
		RULE_declaration = 28, RULE_code_block = 29, RULE_constant_declaration = 30, 
		RULE_variable_declaration = 31, RULE_function_declaration = 32, RULE_function_name = 33, 
		RULE_function_signature = 34, RULE_function_result_type = 35, RULE_function_body = 36, 
		RULE_parameter_clause = 37, RULE_parameter_list = 38, RULE_parameter = 39, 
		RULE_param_name = 40, RULE_type_annotation = 41, RULE_type = 42, RULE_pattern = 43, 
		RULE_wildcard_pattern = 44, RULE_identifier_pattern = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"top_level", "literal", "numeric_literal", "char_sequence_literal", "integer_literal", 
			"double_literal", "expression", "assignment_expression", "literal_expression", 
			"member_expression", "variable_expression", "function_call_expression", 
			"call_argument_clause", "call_argument", "boolean_literal", "statement", 
			"statements", "loop_statement", "for_in_statement", "while_statement", 
			"repeat_while_statement", "branch_statement", "if_statement", "else_clause", 
			"control_transfer_statement", "break_statement", "continue_statement", 
			"return_statement", "declaration", "code_block", "constant_declaration", 
			"variable_declaration", "function_declaration", "function_name", "function_signature", 
			"function_result_type", "function_body", "parameter_clause", "parameter_list", 
			"parameter", "param_name", "type_annotation", "type", "pattern", "wildcard_pattern", 
			"identifier_pattern"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'('", "')'", "'='", "'()'", "','", "'true'", "'false'", 
			"';'", "'for'", "'in'", "'while'", "'repeat'", "'if'", "'else'", "'break'", 
			"'continue'", "'return'", "'{'", "'}'", "'let'", "'var'", "'func'", "':'", 
			"'Int'", "'Bool'", "'Double'", "'Character'", "'String'", "'Void'", "'_'", 
			null, null, null, null, null, "'+'", "'-'", "'*'", "'/'", "'%'", "'^'", 
			"'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'*='", "'/='", "'%='", 
			"'+='", "'-='", "'&&'", "'||'", "'!'", "'..<'", "'...'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "IDENTIFIER", "DECIMAL_LITERAL", 
			"STRING_LITERAL", "CHARACTER_LITERAL", "WS", "ADD", "SUB", "MUL", "DIV", 
			"MOD", "EXP", "GT", "LT", "GEQ", "LEQ", "EQ", "NEQ", "MULEQ", "DIVEQ", 
			"MODEQ", "ADDEQ", "SUBEQ", "AND", "OR", "NOT", "UNTIL", "THROUGH", "ARROW"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "playgroundGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public playgroundGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Top_levelContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(playgroundGrammarParser.EOF, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public Top_levelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top_level; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterTop_level(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitTop_level(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitTop_level(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Top_levelContext top_level() throws RecognitionException {
		Top_levelContext _localctx = new Top_levelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_top_level);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__30) | (1L << IDENTIFIER) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL) | (1L << CHARACTER_LITERAL) | (1L << SUB) | (1L << NOT))) != 0)) {
				{
				setState(92);
				statements();
				}
			}

			setState(95);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Numeric_literalContext numeric_literal() {
			return getRuleContext(Numeric_literalContext.class,0);
		}
		public Boolean_literalContext boolean_literal() {
			return getRuleContext(Boolean_literalContext.class,0);
		}
		public Char_sequence_literalContext char_sequence_literal() {
			return getRuleContext(Char_sequence_literalContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_literal);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case DECIMAL_LITERAL:
			case SUB:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				numeric_literal();
				}
				break;
			case T__6:
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				boolean_literal();
				}
				break;
			case STRING_LITERAL:
			case CHARACTER_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				char_sequence_literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_literalContext extends ParserRuleContext {
		public Integer_literalContext integer_literal() {
			return getRuleContext(Integer_literalContext.class,0);
		}
		public Double_literalContext double_literal() {
			return getRuleContext(Double_literalContext.class,0);
		}
		public TerminalNode SUB() { return getToken(playgroundGrammarParser.SUB, 0); }
		public Numeric_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterNumeric_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitNumeric_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitNumeric_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_literalContext numeric_literal() throws RecognitionException {
		Numeric_literalContext _localctx = new Numeric_literalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numeric_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB) {
				{
				setState(102);
				match(SUB);
				}
			}

			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(105);
				integer_literal();
				}
				break;
			case 2:
				{
				setState(106);
				double_literal();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Char_sequence_literalContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(playgroundGrammarParser.STRING_LITERAL, 0); }
		public TerminalNode CHARACTER_LITERAL() { return getToken(playgroundGrammarParser.CHARACTER_LITERAL, 0); }
		public Char_sequence_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char_sequence_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterChar_sequence_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitChar_sequence_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitChar_sequence_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Char_sequence_literalContext char_sequence_literal() throws RecognitionException {
		Char_sequence_literalContext _localctx = new Char_sequence_literalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_char_sequence_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==CHARACTER_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integer_literalContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(playgroundGrammarParser.DECIMAL_LITERAL, 0); }
		public Integer_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterInteger_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitInteger_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitInteger_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_literalContext integer_literal() throws RecognitionException {
		Integer_literalContext _localctx = new Integer_literalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_integer_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(DECIMAL_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_literalContext extends ParserRuleContext {
		public List<TerminalNode> DECIMAL_LITERAL() { return getTokens(playgroundGrammarParser.DECIMAL_LITERAL); }
		public TerminalNode DECIMAL_LITERAL(int i) {
			return getToken(playgroundGrammarParser.DECIMAL_LITERAL, i);
		}
		public Double_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterDouble_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitDouble_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitDouble_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_literalContext double_literal() throws RecognitionException {
		Double_literalContext _localctx = new Double_literalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_double_literal);
		int _la;
		try {
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(DECIMAL_LITERAL);
				setState(114);
				match(T__0);
				setState(116);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(115);
					match(DECIMAL_LITERAL);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DECIMAL_LITERAL) {
					{
					setState(118);
					match(DECIMAL_LITERAL);
					}
				}

				setState(121);
				match(T__0);
				setState(122);
				match(DECIMAL_LITERAL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignmentExprContext extends ExpressionContext {
		public Assignment_expressionContext assignment_expression() {
			return getRuleContext(Assignment_expressionContext.class,0);
		}
		public AssignmentExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterAssignmentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitAssignmentExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitAssignmentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolComparativeExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(playgroundGrammarParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(playgroundGrammarParser.NEQ, 0); }
		public BoolComparativeExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterBoolComparativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitBoolComparativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitBoolComparativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExponentExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EXP() { return getToken(playgroundGrammarParser.EXP, 0); }
		public ExponentExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterExponentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitExponentExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitExponentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(playgroundGrammarParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(playgroundGrammarParser.SUB, 0); }
		public AddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitAddSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AriAssignmentExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULEQ() { return getToken(playgroundGrammarParser.MULEQ, 0); }
		public TerminalNode DIVEQ() { return getToken(playgroundGrammarParser.DIVEQ, 0); }
		public TerminalNode MODEQ() { return getToken(playgroundGrammarParser.MODEQ, 0); }
		public TerminalNode ADDEQ() { return getToken(playgroundGrammarParser.ADDEQ, 0); }
		public TerminalNode SUBEQ() { return getToken(playgroundGrammarParser.SUBEQ, 0); }
		public AriAssignmentExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterAriAssignmentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitAriAssignmentExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitAriAssignmentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Function_callContext extends ExpressionContext {
		public Function_call_expressionContext function_call_expression() {
			return getRuleContext(Function_call_expressionContext.class,0);
		}
		public Function_callContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExpressionContext {
		public Member_expressionContext member_expression() {
			return getRuleContext(Member_expressionContext.class,0);
		}
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AriComparativeExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GT() { return getToken(playgroundGrammarParser.GT, 0); }
		public TerminalNode LT() { return getToken(playgroundGrammarParser.LT, 0); }
		public TerminalNode GEQ() { return getToken(playgroundGrammarParser.GEQ, 0); }
		public TerminalNode LEQ() { return getToken(playgroundGrammarParser.LEQ, 0); }
		public AriComparativeExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterAriComparativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitAriComparativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitAriComparativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralValueExprContext extends ExpressionContext {
		public Literal_expressionContext literal_expression() {
			return getRuleContext(Literal_expressionContext.class,0);
		}
		public LiteralValueExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterLiteralValueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitLiteralValueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitLiteralValueExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(playgroundGrammarParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(playgroundGrammarParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(playgroundGrammarParser.MOD, 0); }
		public MulDivModExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterMulDivModExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitMulDivModExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitMulDivModExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableExprContext extends ExpressionContext {
		public Variable_expressionContext variable_expression() {
			return getRuleContext(Variable_expressionContext.class,0);
		}
		public VariableExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterVariableExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitVariableExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitVariableExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsNestedConditionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(playgroundGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(playgroundGrammarParser.OR, 0); }
		public IsNestedConditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsNestedCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsNestedCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsNestedCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsNegativeConditionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(playgroundGrammarParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IsNegativeConditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsNegativeCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsNegativeCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsNegativeCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RangeExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode UNTIL() { return getToken(playgroundGrammarParser.UNTIL, 0); }
		public TerminalNode THROUGH() { return getToken(playgroundGrammarParser.THROUGH, 0); }
		public RangeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterRangeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitRangeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitRangeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterParenthesisExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitParenthesisExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitParenthesisExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new AssignmentExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(126);
				assignment_expression();
				}
				break;
			case 2:
				{
				_localctx = new LiteralValueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				literal_expression();
				}
				break;
			case 3:
				{
				_localctx = new Function_callContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				function_call_expression();
				}
				break;
			case 4:
				{
				_localctx = new MemberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				member_expression();
				}
				break;
			case 5:
				{
				_localctx = new VariableExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				variable_expression();
				}
				break;
			case 6:
				{
				_localctx = new IsNegativeConditionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(NOT);
				setState(132);
				expression(6);
				}
				break;
			case 7:
				{
				_localctx = new ParenthesisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(T__1);
				setState(134);
				expression(0);
				setState(135);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(163);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExponentExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(139);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(140);
						((ExponentExprContext)_localctx).op = match(EXP);
						setState(141);
						expression(10);
						}
						break;
					case 2:
						{
						_localctx = new MulDivModExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(142);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(143);
						((MulDivModExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivModExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(144);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new AddSubExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(145);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(146);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(147);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new IsNestedConditionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(148);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(149);
						((IsNestedConditionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((IsNestedConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(150);
						expression(8);
						}
						break;
					case 5:
						{
						_localctx = new AriComparativeExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(151);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(152);
						((AriComparativeExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GEQ) | (1L << LEQ))) != 0)) ) {
							((AriComparativeExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(153);
						expression(6);
						}
						break;
					case 6:
						{
						_localctx = new BoolComparativeExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(154);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(155);
						((BoolComparativeExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((BoolComparativeExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(156);
						expression(5);
						}
						break;
					case 7:
						{
						_localctx = new AriAssignmentExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(157);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(158);
						((AriAssignmentExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULEQ) | (1L << DIVEQ) | (1L << MODEQ) | (1L << ADDEQ) | (1L << SUBEQ))) != 0)) ) {
							((AriAssignmentExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(159);
						expression(4);
						}
						break;
					case 8:
						{
						_localctx = new RangeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(160);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(161);
						((RangeExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==UNTIL || _la==THROUGH) ) {
							((RangeExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Assignment_expressionContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Assignment_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterAssignment_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitAssignment_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitAssignment_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assignment_expressionContext assignment_expression() throws RecognitionException {
		Assignment_expressionContext _localctx = new Assignment_expressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			pattern();
			setState(169);
			match(T__3);
			setState(170);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_expressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Literal_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterLiteral_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitLiteral_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitLiteral_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_expressionContext literal_expression() throws RecognitionException {
		Literal_expressionContext _localctx = new Literal_expressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_expressionContext extends ParserRuleContext {
		public Variable_expressionContext variable_expression() {
			return getRuleContext(Variable_expressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(playgroundGrammarParser.IDENTIFIER, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(playgroundGrammarParser.DECIMAL_LITERAL, 0); }
		public Function_call_expressionContext function_call_expression() {
			return getRuleContext(Function_call_expressionContext.class,0);
		}
		public Member_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterMember_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitMember_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitMember_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_expressionContext member_expression() throws RecognitionException {
		Member_expressionContext _localctx = new Member_expressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_member_expression);
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				variable_expression();
				setState(175);
				match(T__0);
				setState(176);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				variable_expression();
				setState(179);
				match(T__0);
				setState(180);
				match(DECIMAL_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
				variable_expression();
				setState(183);
				match(T__0);
				setState(184);
				function_call_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_expressionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(playgroundGrammarParser.IDENTIFIER, 0); }
		public Variable_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterVariable_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitVariable_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitVariable_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_expressionContext variable_expression() throws RecognitionException {
		Variable_expressionContext _localctx = new Variable_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_variable_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_call_expressionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public Call_argument_clauseContext call_argument_clause() {
			return getRuleContext(Call_argument_clauseContext.class,0);
		}
		public Function_call_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_call_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_call_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_call_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_call_expressionContext function_call_expression() throws RecognitionException {
		Function_call_expressionContext _localctx = new Function_call_expressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_function_call_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			function_name();
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(191);
				match(T__4);
				}
				break;
			case T__1:
				{
				setState(192);
				match(T__1);
				setState(193);
				call_argument_clause();
				setState(194);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Call_argument_clauseContext extends ParserRuleContext {
		public List<Call_argumentContext> call_argument() {
			return getRuleContexts(Call_argumentContext.class);
		}
		public Call_argumentContext call_argument(int i) {
			return getRuleContext(Call_argumentContext.class,i);
		}
		public Call_argument_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_argument_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterCall_argument_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitCall_argument_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitCall_argument_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_argument_clauseContext call_argument_clause() throws RecognitionException {
		Call_argument_clauseContext _localctx = new Call_argument_clauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_call_argument_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			call_argument();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(199);
				match(T__5);
				setState(200);
				call_argument();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Call_argumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Call_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterCall_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitCall_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitCall_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_argumentContext call_argument() throws RecognitionException {
		Call_argumentContext _localctx = new Call_argumentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_call_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_literalContext extends ParserRuleContext {
		public Boolean_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterBoolean_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitBoolean_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitBoolean_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_literalContext boolean_literal() throws RecognitionException {
		Boolean_literalContext _localctx = new Boolean_literalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boolean_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==T__7) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public Loop_statementContext loop_statement() {
			return getRuleContext(Loop_statementContext.class,0);
		}
		public Branch_statementContext branch_statement() {
			return getRuleContext(Branch_statementContext.class,0);
		}
		public Control_transfer_statementContext control_transfer_statement() {
			return getRuleContext(Control_transfer_statementContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		int _la;
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__6:
			case T__7:
			case T__30:
			case IDENTIFIER:
			case DECIMAL_LITERAL:
			case STRING_LITERAL:
			case CHARACTER_LITERAL:
			case SUB:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				expression(0);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(211);
					match(T__8);
					}
				}

				}
				break;
			case T__20:
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				declaration();
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(215);
					match(T__8);
					}
				}

				}
				break;
			case T__9:
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				loop_statement();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(219);
					match(T__8);
					}
				}

				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
				branch_statement();
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(223);
					match(T__8);
					}
				}

				}
				break;
			case T__15:
			case T__16:
				enterOuterAlt(_localctx, 5);
				{
				setState(226);
				control_transfer_statement();
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(227);
					match(T__8);
					}
				}

				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 6);
				{
				setState(230);
				return_statement();
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(231);
					match(T__8);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(236);
				statement();
				}
				}
				setState(239); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__30) | (1L << IDENTIFIER) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL) | (1L << CHARACTER_LITERAL) | (1L << SUB) | (1L << NOT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Loop_statementContext extends ParserRuleContext {
		public For_in_statementContext for_in_statement() {
			return getRuleContext(For_in_statementContext.class,0);
		}
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public Repeat_while_statementContext repeat_while_statement() {
			return getRuleContext(Repeat_while_statementContext.class,0);
		}
		public Loop_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterLoop_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitLoop_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitLoop_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_statementContext loop_statement() throws RecognitionException {
		Loop_statementContext _localctx = new Loop_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_loop_statement);
		try {
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				for_in_statement();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				while_statement();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				repeat_while_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_in_statementContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public For_in_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_in_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFor_in_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFor_in_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFor_in_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_in_statementContext for_in_statement() throws RecognitionException {
		For_in_statementContext _localctx = new For_in_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_for_in_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__9);
			setState(247);
			pattern();
			setState(248);
			match(T__10);
			setState(249);
			expression(0);
			setState(250);
			code_block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitWhile_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitWhile_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(T__11);
			setState(253);
			expression(0);
			setState(254);
			code_block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Repeat_while_statementContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Repeat_while_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterRepeat_while_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitRepeat_while_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitRepeat_while_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Repeat_while_statementContext repeat_while_statement() throws RecognitionException {
		Repeat_while_statementContext _localctx = new Repeat_while_statementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_repeat_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__12);
			setState(257);
			code_block();
			setState(258);
			match(T__11);
			setState(259);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Branch_statementContext extends ParserRuleContext {
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Branch_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterBranch_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitBranch_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitBranch_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Branch_statementContext branch_statement() throws RecognitionException {
		Branch_statementContext _localctx = new Branch_statementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_branch_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			if_statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public Else_clauseContext else_clause() {
			return getRuleContext(Else_clauseContext.class,0);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIf_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(T__13);
			setState(264);
			expression(0);
			setState(265);
			code_block();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(266);
				else_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_clauseContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Else_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterElse_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitElse_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitElse_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Else_clauseContext else_clause() throws RecognitionException {
		Else_clauseContext _localctx = new Else_clauseContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_else_clause);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				match(T__14);
				setState(270);
				code_block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				match(T__14);
				setState(272);
				if_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Control_transfer_statementContext extends ParserRuleContext {
		public Break_statementContext break_statement() {
			return getRuleContext(Break_statementContext.class,0);
		}
		public Continue_statementContext continue_statement() {
			return getRuleContext(Continue_statementContext.class,0);
		}
		public Control_transfer_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_control_transfer_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterControl_transfer_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitControl_transfer_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitControl_transfer_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Control_transfer_statementContext control_transfer_statement() throws RecognitionException {
		Control_transfer_statementContext _localctx = new Control_transfer_statementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_control_transfer_statement);
		try {
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				break_statement();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				continue_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Break_statementContext extends ParserRuleContext {
		public Break_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterBreak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitBreak_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitBreak_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Break_statementContext break_statement() throws RecognitionException {
		Break_statementContext _localctx = new Break_statementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_break_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Continue_statementContext extends ParserRuleContext {
		public Continue_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterContinue_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitContinue_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitContinue_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continue_statementContext continue_statement() throws RecognitionException {
		Continue_statementContext _localctx = new Continue_statementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_continue_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_statementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__17);
			setState(284);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public Constant_declarationContext constant_declaration() {
			return getRuleContext(Constant_declarationContext.class,0);
		}
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_declaration);
		try {
			setState(289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				constant_declaration();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				variable_declaration();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 3);
				{
				setState(288);
				function_declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Code_blockContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public Code_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterCode_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitCode_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitCode_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Code_blockContext code_block() throws RecognitionException {
		Code_blockContext _localctx = new Code_blockContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_code_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__18);
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__30) | (1L << IDENTIFIER) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL) | (1L << CHARACTER_LITERAL) | (1L << SUB) | (1L << NOT))) != 0)) {
				{
				setState(292);
				statements();
				}
			}

			setState(295);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_declarationContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Constant_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterConstant_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitConstant_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitConstant_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_declarationContext constant_declaration() throws RecognitionException {
		Constant_declarationContext _localctx = new Constant_declarationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(T__20);
			setState(298);
			pattern();
			setState(299);
			match(T__3);
			setState(300);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declarationContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(T__21);
			setState(303);
			pattern();
			setState(304);
			match(T__3);
			setState(305);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_declarationContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public Function_signatureContext function_signature() {
			return getRuleContext(Function_signatureContext.class,0);
		}
		public Function_bodyContext function_body() {
			return getRuleContext(Function_bodyContext.class,0);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(T__22);
			setState(308);
			function_name();
			setState(309);
			function_signature();
			setState(310);
			function_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(playgroundGrammarParser.IDENTIFIER, 0); }
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_signatureContext extends ParserRuleContext {
		public Parameter_clauseContext parameter_clause() {
			return getRuleContext(Parameter_clauseContext.class,0);
		}
		public Function_result_typeContext function_result_type() {
			return getRuleContext(Function_result_typeContext.class,0);
		}
		public Function_signatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_signature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_signature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_signature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_signatureContext function_signature() throws RecognitionException {
		Function_signatureContext _localctx = new Function_signatureContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_function_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			parameter_clause();
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(315);
				function_result_type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_result_typeContext extends ParserRuleContext {
		public TerminalNode ARROW() { return getToken(playgroundGrammarParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Function_result_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_result_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_result_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_result_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_result_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_result_typeContext function_result_type() throws RecognitionException {
		Function_result_typeContext _localctx = new Function_result_typeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_function_result_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(ARROW);
			setState(319);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_bodyContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public Function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterFunction_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitFunction_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitFunction_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_bodyContext function_body() throws RecognitionException {
		Function_bodyContext _localctx = new Function_bodyContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_function_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			code_block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_clauseContext extends ParserRuleContext {
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Parameter_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterParameter_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitParameter_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitParameter_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_clauseContext parameter_clause() throws RecognitionException {
		Parameter_clauseContext _localctx = new Parameter_clauseContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_parameter_clause);
		try {
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				match(T__4);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				match(T__1);
				setState(325);
				parameter_list();
				setState(326);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_listContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterParameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitParameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitParameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			parameter();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(331);
				match(T__5);
				setState(332);
				parameter();
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public Param_nameContext param_name() {
			return getRuleContext(Param_nameContext.class,0);
		}
		public Type_annotationContext type_annotation() {
			return getRuleContext(Type_annotationContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			param_name();
			setState(339);
			type_annotation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(playgroundGrammarParser.IDENTIFIER, 0); }
		public Param_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterParam_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitParam_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitParam_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_nameContext param_name() throws RecognitionException {
		Param_nameContext _localctx = new Param_nameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_param_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_annotationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Type_annotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterType_annotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitType_annotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitType_annotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_annotationContext type_annotation() throws RecognitionException {
		Type_annotationContext _localctx = new Type_annotationContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_type_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(T__23);
			setState(344);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public Identifier_patternContext identifier_pattern() {
			return getRuleContext(Identifier_patternContext.class,0);
		}
		public Wildcard_patternContext wildcard_pattern() {
			return getRuleContext(Wildcard_patternContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_pattern);
		try {
			setState(350);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				identifier_pattern();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(349);
				wildcard_pattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Wildcard_patternContext extends ParserRuleContext {
		public Wildcard_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterWildcard_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitWildcard_pattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitWildcard_pattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Wildcard_patternContext wildcard_pattern() throws RecognitionException {
		Wildcard_patternContext _localctx = new Wildcard_patternContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_wildcard_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(T__30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_patternContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(playgroundGrammarParser.IDENTIFIER, 0); }
		public Identifier_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIdentifier_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIdentifier_pattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIdentifier_pattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Identifier_patternContext identifier_pattern() throws RecognitionException {
		Identifier_patternContext _localctx = new Identifier_patternContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_identifier_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0167\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\5\2`\n\2\3\2\3\2\3\3\3\3\3\3\5\3g\n\3\3\4\5"+
		"\4j\n\4\3\4\3\4\5\4n\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\5\7w\n\7\3\7\5\7"+
		"z\n\7\3\7\3\7\5\7~\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\b\u008c\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a6\n\b\f\b\16\b\u00a9"+
		"\13\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00bd\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u00c7\n\r\3\16\3\16\3\16\7\16\u00cc\n\16\f\16\16\16\u00cf\13\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\5\21\u00d7\n\21\3\21\3\21\5\21\u00db\n\21\3"+
		"\21\3\21\5\21\u00df\n\21\3\21\3\21\5\21\u00e3\n\21\3\21\3\21\5\21\u00e7"+
		"\n\21\3\21\3\21\5\21\u00eb\n\21\5\21\u00ed\n\21\3\22\6\22\u00f0\n\22\r"+
		"\22\16\22\u00f1\3\23\3\23\3\23\5\23\u00f7\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\5\30\u010e\n\30\3\31\3\31\3\31\3\31\5\31\u0114\n\31\3\32\3"+
		"\32\5\32\u0118\n\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\5\36\u0124\n\36\3\37\3\37\5\37\u0128\n\37\3\37\3\37\3 \3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3$\3$\5$\u013f\n$\3%\3%\3%\3&"+
		"\3&\3\'\3\'\3\'\3\'\3\'\5\'\u014b\n\'\3(\3(\3(\7(\u0150\n(\f(\16(\u0153"+
		"\13(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3-\3-\5-\u0161\n-\3.\3.\3/\3/\3/\2"+
		"\3\16\60\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668"+
		":<>@BDFHJLNPRTVXZ\\\2\f\3\2$%\3\2)+\3\2\'(\3\289\3\2-\60\3\2\61\62\3\2"+
		"\63\67\3\2;<\3\2\t\n\3\2\33 \2\u016a\2_\3\2\2\2\4f\3\2\2\2\6i\3\2\2\2"+
		"\bo\3\2\2\2\nq\3\2\2\2\f}\3\2\2\2\16\u008b\3\2\2\2\20\u00aa\3\2\2\2\22"+
		"\u00ae\3\2\2\2\24\u00bc\3\2\2\2\26\u00be\3\2\2\2\30\u00c0\3\2\2\2\32\u00c8"+
		"\3\2\2\2\34\u00d0\3\2\2\2\36\u00d2\3\2\2\2 \u00ec\3\2\2\2\"\u00ef\3\2"+
		"\2\2$\u00f6\3\2\2\2&\u00f8\3\2\2\2(\u00fe\3\2\2\2*\u0102\3\2\2\2,\u0107"+
		"\3\2\2\2.\u0109\3\2\2\2\60\u0113\3\2\2\2\62\u0117\3\2\2\2\64\u0119\3\2"+
		"\2\2\66\u011b\3\2\2\28\u011d\3\2\2\2:\u0123\3\2\2\2<\u0125\3\2\2\2>\u012b"+
		"\3\2\2\2@\u0130\3\2\2\2B\u0135\3\2\2\2D\u013a\3\2\2\2F\u013c\3\2\2\2H"+
		"\u0140\3\2\2\2J\u0143\3\2\2\2L\u014a\3\2\2\2N\u014c\3\2\2\2P\u0154\3\2"+
		"\2\2R\u0157\3\2\2\2T\u0159\3\2\2\2V\u015c\3\2\2\2X\u0160\3\2\2\2Z\u0162"+
		"\3\2\2\2\\\u0164\3\2\2\2^`\5\"\22\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7"+
		"\2\2\3b\3\3\2\2\2cg\5\6\4\2dg\5\36\20\2eg\5\b\5\2fc\3\2\2\2fd\3\2\2\2"+
		"fe\3\2\2\2g\5\3\2\2\2hj\7(\2\2ih\3\2\2\2ij\3\2\2\2jm\3\2\2\2kn\5\n\6\2"+
		"ln\5\f\7\2mk\3\2\2\2ml\3\2\2\2n\7\3\2\2\2op\t\2\2\2p\t\3\2\2\2qr\7#\2"+
		"\2r\13\3\2\2\2st\7#\2\2tv\7\3\2\2uw\7#\2\2vu\3\2\2\2vw\3\2\2\2w~\3\2\2"+
		"\2xz\7#\2\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\3\2\2|~\7#\2\2}s\3\2\2\2"+
		"}y\3\2\2\2~\r\3\2\2\2\177\u0080\b\b\1\2\u0080\u008c\5\20\t\2\u0081\u008c"+
		"\5\22\n\2\u0082\u008c\5\30\r\2\u0083\u008c\5\24\13\2\u0084\u008c\5\26"+
		"\f\2\u0085\u0086\7:\2\2\u0086\u008c\5\16\b\b\u0087\u0088\7\4\2\2\u0088"+
		"\u0089\5\16\b\2\u0089\u008a\7\5\2\2\u008a\u008c\3\2\2\2\u008b\177\3\2"+
		"\2\2\u008b\u0081\3\2\2\2\u008b\u0082\3\2\2\2\u008b\u0083\3\2\2\2\u008b"+
		"\u0084\3\2\2\2\u008b\u0085\3\2\2\2\u008b\u0087\3\2\2\2\u008c\u00a7\3\2"+
		"\2\2\u008d\u008e\f\f\2\2\u008e\u008f\7,\2\2\u008f\u00a6\5\16\b\f\u0090"+
		"\u0091\f\13\2\2\u0091\u0092\t\3\2\2\u0092\u00a6\5\16\b\f\u0093\u0094\f"+
		"\n\2\2\u0094\u0095\t\4\2\2\u0095\u00a6\5\16\b\13\u0096\u0097\f\t\2\2\u0097"+
		"\u0098\t\5\2\2\u0098\u00a6\5\16\b\n\u0099\u009a\f\7\2\2\u009a\u009b\t"+
		"\6\2\2\u009b\u00a6\5\16\b\b\u009c\u009d\f\6\2\2\u009d\u009e\t\7\2\2\u009e"+
		"\u00a6\5\16\b\7\u009f\u00a0\f\5\2\2\u00a0\u00a1\t\b\2\2\u00a1\u00a6\5"+
		"\16\b\6\u00a2\u00a3\f\3\2\2\u00a3\u00a4\t\t\2\2\u00a4\u00a6\5\16\b\4\u00a5"+
		"\u008d\3\2\2\2\u00a5\u0090\3\2\2\2\u00a5\u0093\3\2\2\2\u00a5\u0096\3\2"+
		"\2\2\u00a5\u0099\3\2\2\2\u00a5\u009c\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5"+
		"\u00a2\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\17\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab\5X-\2\u00ab\u00ac"+
		"\7\6\2\2\u00ac\u00ad\5\16\b\2\u00ad\21\3\2\2\2\u00ae\u00af\5\4\3\2\u00af"+
		"\23\3\2\2\2\u00b0\u00b1\5\26\f\2\u00b1\u00b2\7\3\2\2\u00b2\u00b3\7\"\2"+
		"\2\u00b3\u00bd\3\2\2\2\u00b4\u00b5\5\26\f\2\u00b5\u00b6\7\3\2\2\u00b6"+
		"\u00b7\7#\2\2\u00b7\u00bd\3\2\2\2\u00b8\u00b9\5\26\f\2\u00b9\u00ba\7\3"+
		"\2\2\u00ba\u00bb\5\30\r\2\u00bb\u00bd\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc"+
		"\u00b4\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\25\3\2\2\2\u00be\u00bf\7\"\2"+
		"\2\u00bf\27\3\2\2\2\u00c0\u00c6\5D#\2\u00c1\u00c7\7\7\2\2\u00c2\u00c3"+
		"\7\4\2\2\u00c3\u00c4\5\32\16\2\u00c4\u00c5\7\5\2\2\u00c5\u00c7\3\2\2\2"+
		"\u00c6\u00c1\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c7\31\3\2\2\2\u00c8\u00cd"+
		"\5\34\17\2\u00c9\u00ca\7\b\2\2\u00ca\u00cc\5\34\17\2\u00cb\u00c9\3\2\2"+
		"\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\33"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\5\16\b\2\u00d1\35\3\2\2\2\u00d2"+
		"\u00d3\t\n\2\2\u00d3\37\3\2\2\2\u00d4\u00d6\5\16\b\2\u00d5\u00d7\7\13"+
		"\2\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00ed\3\2\2\2\u00d8"+
		"\u00da\5:\36\2\u00d9\u00db\7\13\2\2\u00da\u00d9\3\2\2\2\u00da\u00db\3"+
		"\2\2\2\u00db\u00ed\3\2\2\2\u00dc\u00de\5$\23\2\u00dd\u00df\7\13\2\2\u00de"+
		"\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00ed\3\2\2\2\u00e0\u00e2\5,"+
		"\27\2\u00e1\u00e3\7\13\2\2\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00ed\3\2\2\2\u00e4\u00e6\5\62\32\2\u00e5\u00e7\7\13\2\2\u00e6\u00e5"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ed\3\2\2\2\u00e8\u00ea\58\35\2\u00e9"+
		"\u00eb\7\13\2\2\u00ea\u00e9\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3"+
		"\2\2\2\u00ec\u00d4\3\2\2\2\u00ec\u00d8\3\2\2\2\u00ec\u00dc\3\2\2\2\u00ec"+
		"\u00e0\3\2\2\2\u00ec\u00e4\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed!\3\2\2\2"+
		"\u00ee\u00f0\5 \21\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef"+
		"\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2#\3\2\2\2\u00f3\u00f7\5&\24\2\u00f4"+
		"\u00f7\5(\25\2\u00f5\u00f7\5*\26\2\u00f6\u00f3\3\2\2\2\u00f6\u00f4\3\2"+
		"\2\2\u00f6\u00f5\3\2\2\2\u00f7%\3\2\2\2\u00f8\u00f9\7\f\2\2\u00f9\u00fa"+
		"\5X-\2\u00fa\u00fb\7\r\2\2\u00fb\u00fc\5\16\b\2\u00fc\u00fd\5<\37\2\u00fd"+
		"\'\3\2\2\2\u00fe\u00ff\7\16\2\2\u00ff\u0100\5\16\b\2\u0100\u0101\5<\37"+
		"\2\u0101)\3\2\2\2\u0102\u0103\7\17\2\2\u0103\u0104\5<\37\2\u0104\u0105"+
		"\7\16\2\2\u0105\u0106\5\16\b\2\u0106+\3\2\2\2\u0107\u0108\5.\30\2\u0108"+
		"-\3\2\2\2\u0109\u010a\7\20\2\2\u010a\u010b\5\16\b\2\u010b\u010d\5<\37"+
		"\2\u010c\u010e\5\60\31\2\u010d\u010c\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"/\3\2\2\2\u010f\u0110\7\21\2\2\u0110\u0114\5<\37\2\u0111\u0112\7\21\2"+
		"\2\u0112\u0114\5.\30\2\u0113\u010f\3\2\2\2\u0113\u0111\3\2\2\2\u0114\61"+
		"\3\2\2\2\u0115\u0118\5\64\33\2\u0116\u0118\5\66\34\2\u0117\u0115\3\2\2"+
		"\2\u0117\u0116\3\2\2\2\u0118\63\3\2\2\2\u0119\u011a\7\22\2\2\u011a\65"+
		"\3\2\2\2\u011b\u011c\7\23\2\2\u011c\67\3\2\2\2\u011d\u011e\7\24\2\2\u011e"+
		"\u011f\5\16\b\2\u011f9\3\2\2\2\u0120\u0124\5> \2\u0121\u0124\5@!\2\u0122"+
		"\u0124\5B\"\2\u0123\u0120\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0122\3\2"+
		"\2\2\u0124;\3\2\2\2\u0125\u0127\7\25\2\2\u0126\u0128\5\"\22\2\u0127\u0126"+
		"\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\7\26\2\2"+
		"\u012a=\3\2\2\2\u012b\u012c\7\27\2\2\u012c\u012d\5X-\2\u012d\u012e\7\6"+
		"\2\2\u012e\u012f\5\16\b\2\u012f?\3\2\2\2\u0130\u0131\7\30\2\2\u0131\u0132"+
		"\5X-\2\u0132\u0133\7\6\2\2\u0133\u0134\5\16\b\2\u0134A\3\2\2\2\u0135\u0136"+
		"\7\31\2\2\u0136\u0137\5D#\2\u0137\u0138\5F$\2\u0138\u0139\5J&\2\u0139"+
		"C\3\2\2\2\u013a\u013b\7\"\2\2\u013bE\3\2\2\2\u013c\u013e\5L\'\2\u013d"+
		"\u013f\5H%\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013fG\3\2\2\2\u0140"+
		"\u0141\7=\2\2\u0141\u0142\5V,\2\u0142I\3\2\2\2\u0143\u0144\5<\37\2\u0144"+
		"K\3\2\2\2\u0145\u014b\7\7\2\2\u0146\u0147\7\4\2\2\u0147\u0148\5N(\2\u0148"+
		"\u0149\7\5\2\2\u0149\u014b\3\2\2\2\u014a\u0145\3\2\2\2\u014a\u0146\3\2"+
		"\2\2\u014bM\3\2\2\2\u014c\u0151\5P)\2\u014d\u014e\7\b\2\2\u014e\u0150"+
		"\5P)\2\u014f\u014d\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152O\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\5R*\2\u0155"+
		"\u0156\5T+\2\u0156Q\3\2\2\2\u0157\u0158\7\"\2\2\u0158S\3\2\2\2\u0159\u015a"+
		"\7\32\2\2\u015a\u015b\5V,\2\u015bU\3\2\2\2\u015c\u015d\t\13\2\2\u015d"+
		"W\3\2\2\2\u015e\u0161\5\\/\2\u015f\u0161\5Z.\2\u0160\u015e\3\2\2\2\u0160"+
		"\u015f\3\2\2\2\u0161Y\3\2\2\2\u0162\u0163\7!\2\2\u0163[\3\2\2\2\u0164"+
		"\u0165\7\"\2\2\u0165]\3\2\2\2!_fimvy}\u008b\u00a5\u00a7\u00bc\u00c6\u00cd"+
		"\u00d6\u00da\u00de\u00e2\u00e6\u00ea\u00ec\u00f1\u00f6\u010d\u0113\u0117"+
		"\u0123\u0127\u013e\u014a\u0151\u0160";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}