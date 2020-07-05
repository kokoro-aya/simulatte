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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, IDENTIFIER=38, 
		INTEGER_LITERAL=39, DECIMAL_LITERAL=40, WS=41, AND=42, OR=43, NOT=44, 
		UNTIL=45, THROUGH=46, ARROW=47;
	public static final int
		RULE_top_level = 0, RULE_expression = 1, RULE_function_call_expression = 2, 
		RULE_call_argument_clause = 3, RULE_call_argument = 4, RULE_conditional_expression = 5, 
		RULE_boolean_literal = 6, RULE_range_expression = 7, RULE_statement = 8, 
		RULE_statements = 9, RULE_loop_statement = 10, RULE_for_in_statement = 11, 
		RULE_while_statement = 12, RULE_repeat_while_statement = 13, RULE_branch_statement = 14, 
		RULE_if_statement = 15, RULE_else_clause = 16, RULE_control_transfer_statement = 17, 
		RULE_break_statement = 18, RULE_continue_statement = 19, RULE_declaration = 20, 
		RULE_code_block = 21, RULE_constant_declaration = 22, RULE_variable_declaration = 23, 
		RULE_function_declaration = 24, RULE_function_name = 25, RULE_function_signature = 26, 
		RULE_function_result_type = 27, RULE_function_body = 28, RULE_parameter_clause = 29, 
		RULE_parameter_list = 30, RULE_parameter = 31, RULE_param_name = 32, RULE_type_annotation = 33, 
		RULE_type = 34, RULE_pattern = 35, RULE_wildcard_pattern = 36, RULE_identifier_pattern = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"top_level", "expression", "function_call_expression", "call_argument_clause", 
			"call_argument", "conditional_expression", "boolean_literal", "range_expression", 
			"statement", "statements", "loop_statement", "for_in_statement", "while_statement", 
			"repeat_while_statement", "branch_statement", "if_statement", "else_clause", 
			"control_transfer_statement", "break_statement", "continue_statement", 
			"declaration", "code_block", "constant_declaration", "variable_declaration", 
			"function_declaration", "function_name", "function_signature", "function_result_type", 
			"function_body", "parameter_clause", "parameter_list", "parameter", "param_name", 
			"type_annotation", "type", "pattern", "wildcard_pattern", "identifier_pattern"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'moveForward()'", "'turnLeft()'", "'toggleSwitch()'", "'collectGem()'", 
			"'()'", "'('", "')'", "','", "'isOnGem'", "'isOnOpenedSwitch'", "'isOnClosedSwitch'", 
			"'isBlocked'", "'isBlockedLeft'", "'isBlockedRight'", "'true'", "'false'", 
			"';'", "'for'", "'in'", "'while'", "'repeat'", "'if'", "'else'", "'break'", 
			"'continue'", "'{'", "'}'", "'let'", "'='", "'var'", "'func'", "':'", 
			"'Int'", "'Bool'", "'Double'", "'Void'", "'_'", null, null, null, null, 
			"'&&'", "'||'", "'!'", "'..<'", "'...'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "IDENTIFIER", "INTEGER_LITERAL", "DECIMAL_LITERAL", "WS", 
			"AND", "OR", "NOT", "UNTIL", "THROUGH", "ARROW"
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
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << NOT))) != 0)) {
				{
				setState(76);
				statements();
				}
			}

			setState(79);
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
	public static class CollectGemContext extends ExpressionContext {
		public CollectGemContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterCollectGem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitCollectGem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitCollectGem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoveForwardContext extends ExpressionContext {
		public MoveForwardContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterMoveForward(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitMoveForward(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitMoveForward(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RangedStepContext extends ExpressionContext {
		public Range_expressionContext range_expression() {
			return getRuleContext(Range_expressionContext.class,0);
		}
		public RangedStepContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterRangedStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitRangedStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitRangedStep(this);
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
	public static class TurnLeftContext extends ExpressionContext {
		public TurnLeftContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterTurnLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitTurnLeft(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitTurnLeft(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ToggleSwitchContext extends ExpressionContext {
		public ToggleSwitchContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterToggleSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitToggleSwitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitToggleSwitch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckTruthContext extends ExpressionContext {
		public Conditional_expressionContext conditional_expression() {
			return getRuleContext(Conditional_expressionContext.class,0);
		}
		public CheckTruthContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterCheckTruth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitCheckTruth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitCheckTruth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new MoveForwardContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(T__0);
				}
				break;
			case T__1:
				_localctx = new TurnLeftContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				match(T__1);
				}
				break;
			case T__2:
				_localctx = new ToggleSwitchContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(T__2);
				}
				break;
			case T__3:
				_localctx = new CollectGemContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(84);
				match(T__3);
				}
				break;
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case NOT:
				_localctx = new CheckTruthContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(85);
				conditional_expression(0);
				}
				break;
			case INTEGER_LITERAL:
				_localctx = new RangedStepContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(86);
				range_expression();
				}
				break;
			case IDENTIFIER:
				_localctx = new Function_callContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(87);
				function_call_expression();
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
		enterRule(_localctx, 4, RULE_function_call_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			function_name();
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(91);
				match(T__4);
				}
				break;
			case T__5:
				{
				setState(92);
				match(T__5);
				setState(93);
				call_argument_clause();
				setState(94);
				match(T__6);
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
		enterRule(_localctx, 6, RULE_call_argument_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			call_argument();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(99);
				match(T__7);
				setState(100);
				call_argument();
				}
				}
				setState(105);
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
		enterRule(_localctx, 8, RULE_call_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			expression();
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

	public static class Conditional_expressionContext extends ParserRuleContext {
		public Conditional_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_expression; }
	 
		public Conditional_expressionContext() { }
		public void copyFrom(Conditional_expressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IsOnGemContext extends Conditional_expressionContext {
		public IsOnGemContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsOnGem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsOnGem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsOnGem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsOnClosedSwitchContext extends Conditional_expressionContext {
		public IsOnClosedSwitchContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsOnClosedSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsOnClosedSwitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsOnClosedSwitch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsBooleanContext extends Conditional_expressionContext {
		public Boolean_literalContext boolean_literal() {
			return getRuleContext(Boolean_literalContext.class,0);
		}
		public IsBooleanContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsNestedConditionContext extends Conditional_expressionContext {
		public Token op;
		public List<Conditional_expressionContext> conditional_expression() {
			return getRuleContexts(Conditional_expressionContext.class);
		}
		public Conditional_expressionContext conditional_expression(int i) {
			return getRuleContext(Conditional_expressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(playgroundGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(playgroundGrammarParser.OR, 0); }
		public IsNestedConditionContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
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
	public static class IsBlockedContext extends Conditional_expressionContext {
		public IsBlockedContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsBlocked(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsBlocked(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsBlocked(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsNegativeConditionContext extends Conditional_expressionContext {
		public TerminalNode NOT() { return getToken(playgroundGrammarParser.NOT, 0); }
		public Conditional_expressionContext conditional_expression() {
			return getRuleContext(Conditional_expressionContext.class,0);
		}
		public IsNegativeConditionContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
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
	public static class IsOnOpenedSwitchContext extends Conditional_expressionContext {
		public IsOnOpenedSwitchContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsOnOpenedSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsOnOpenedSwitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsOnOpenedSwitch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsBlockedRightContext extends Conditional_expressionContext {
		public IsBlockedRightContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsBlockedRight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsBlockedRight(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsBlockedRight(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsBlockedLeftContext extends Conditional_expressionContext {
		public IsBlockedLeftContext(Conditional_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterIsBlockedLeft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitIsBlockedLeft(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitIsBlockedLeft(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_expressionContext conditional_expression() throws RecognitionException {
		return conditional_expression(0);
	}

	private Conditional_expressionContext conditional_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Conditional_expressionContext _localctx = new Conditional_expressionContext(_ctx, _parentState);
		Conditional_expressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_conditional_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
			case T__15:
				{
				_localctx = new IsBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(109);
				boolean_literal();
				}
				break;
			case T__8:
				{
				_localctx = new IsOnGemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(T__8);
				}
				break;
			case T__9:
				{
				_localctx = new IsOnOpenedSwitchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(T__9);
				}
				break;
			case T__10:
				{
				_localctx = new IsOnClosedSwitchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(T__10);
				}
				break;
			case T__11:
				{
				_localctx = new IsBlockedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(T__11);
				}
				break;
			case T__12:
				{
				_localctx = new IsBlockedLeftContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114);
				match(T__12);
				}
				break;
			case T__13:
				{
				_localctx = new IsBlockedRightContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(T__13);
				}
				break;
			case NOT:
				{
				_localctx = new IsNegativeConditionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(NOT);
				setState(117);
				conditional_expression(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IsNestedConditionContext(new Conditional_expressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_conditional_expression);
					setState(120);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(121);
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
					setState(122);
					conditional_expression(3);
					}
					} 
				}
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		enterRule(_localctx, 12, RULE_boolean_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_la = _input.LA(1);
			if ( !(_la==T__14 || _la==T__15) ) {
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

	public static class Range_expressionContext extends ParserRuleContext {
		public Range_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_expression; }
	 
		public Range_expressionContext() { }
		public void copyFrom(Range_expressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RangeHandlerContext extends Range_expressionContext {
		public Token op;
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(playgroundGrammarParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(playgroundGrammarParser.INTEGER_LITERAL, i);
		}
		public TerminalNode UNTIL() { return getToken(playgroundGrammarParser.UNTIL, 0); }
		public TerminalNode THROUGH() { return getToken(playgroundGrammarParser.THROUGH, 0); }
		public RangeHandlerContext(Range_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).enterRangeHandler(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof playgroundGrammarListener ) ((playgroundGrammarListener)listener).exitRangeHandler(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof playgroundGrammarVisitor ) return ((playgroundGrammarVisitor<? extends T>)visitor).visitRangeHandler(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_expressionContext range_expression() throws RecognitionException {
		Range_expressionContext _localctx = new Range_expressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_range_expression);
		int _la;
		try {
			_localctx = new RangeHandlerContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(INTEGER_LITERAL);
			setState(131);
			((RangeHandlerContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==UNTIL || _la==THROUGH) ) {
				((RangeHandlerContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(132);
			match(INTEGER_LITERAL);
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
		enterRule(_localctx, 16, RULE_statement);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case IDENTIFIER:
			case INTEGER_LITERAL:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				expression();
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(135);
					match(T__16);
					}
				}

				}
				break;
			case T__27:
			case T__29:
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				declaration();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(139);
					match(T__16);
					}
				}

				}
				break;
			case T__17:
			case T__19:
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				loop_statement();
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(143);
					match(T__16);
					}
				}

				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				branch_statement();
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(147);
					match(T__16);
					}
				}

				}
				break;
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				control_transfer_statement();
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(151);
					match(T__16);
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
		enterRule(_localctx, 18, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156);
				statement();
				}
				}
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << NOT))) != 0) );
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
		enterRule(_localctx, 20, RULE_loop_statement);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				for_in_statement();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(162);
				while_statement();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(163);
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
		enterRule(_localctx, 22, RULE_for_in_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__17);
			setState(167);
			pattern();
			setState(168);
			match(T__18);
			setState(169);
			expression();
			setState(170);
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
		enterRule(_localctx, 24, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(T__19);
			setState(173);
			expression();
			setState(174);
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
		enterRule(_localctx, 26, RULE_repeat_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(T__20);
			setState(177);
			code_block();
			setState(178);
			match(T__19);
			setState(179);
			expression();
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
		enterRule(_localctx, 28, RULE_branch_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
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
		enterRule(_localctx, 30, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__21);
			setState(184);
			expression();
			setState(185);
			code_block();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(186);
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
		enterRule(_localctx, 32, RULE_else_clause);
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(T__22);
				setState(190);
				code_block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(T__22);
				setState(192);
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
		enterRule(_localctx, 34, RULE_control_transfer_statement);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				break_statement();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
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
		enterRule(_localctx, 36, RULE_break_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__23);
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
		enterRule(_localctx, 38, RULE_continue_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__24);
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
		enterRule(_localctx, 40, RULE_declaration);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				constant_declaration();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				variable_declaration();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 3);
				{
				setState(205);
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
		enterRule(_localctx, 42, RULE_code_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(T__25);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__17) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << IDENTIFIER) | (1L << INTEGER_LITERAL) | (1L << NOT))) != 0)) {
				{
				setState(209);
				statements();
				}
			}

			setState(212);
			match(T__26);
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
		enterRule(_localctx, 44, RULE_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__27);
			setState(215);
			pattern();
			setState(216);
			match(T__28);
			setState(217);
			expression();
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
		enterRule(_localctx, 46, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__29);
			setState(220);
			pattern();
			setState(221);
			match(T__28);
			setState(222);
			expression();
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
		enterRule(_localctx, 48, RULE_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(T__30);
			setState(225);
			function_name();
			setState(226);
			function_signature();
			setState(227);
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
		enterRule(_localctx, 50, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
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
		enterRule(_localctx, 52, RULE_function_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			parameter_clause();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(232);
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
		enterRule(_localctx, 54, RULE_function_result_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(ARROW);
			setState(236);
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
		enterRule(_localctx, 56, RULE_function_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
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
		enterRule(_localctx, 58, RULE_parameter_clause);
		try {
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(T__4);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(T__5);
				setState(242);
				parameter_list();
				setState(243);
				match(T__6);
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
		enterRule(_localctx, 60, RULE_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			parameter();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(248);
				match(T__7);
				setState(249);
				parameter();
				}
				}
				setState(254);
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
		enterRule(_localctx, 62, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			param_name();
			setState(256);
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
		enterRule(_localctx, 64, RULE_param_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
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
		enterRule(_localctx, 66, RULE_type_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__31);
			setState(261);
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
		enterRule(_localctx, 68, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
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
		enterRule(_localctx, 70, RULE_pattern);
		try {
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				identifier_pattern();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
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
		enterRule(_localctx, 72, RULE_wildcard_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__36);
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
		enterRule(_localctx, 74, RULE_identifier_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
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
		case 5:
			return conditional_expression_sempred((Conditional_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean conditional_expression_sempred(Conditional_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0114\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\5\2P\n\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3[\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4c\n\4\3\5\3"+
		"\5\3\5\7\5h\n\5\f\5\16\5k\13\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7y\n\7\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\5\n\u008b\n\n\3\n\3\n\5\n\u008f\n\n\3\n\3\n\5\n\u0093"+
		"\n\n\3\n\3\n\5\n\u0097\n\n\3\n\3\n\5\n\u009b\n\n\5\n\u009d\n\n\3\13\6"+
		"\13\u00a0\n\13\r\13\16\13\u00a1\3\f\3\f\3\f\5\f\u00a7\n\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\5\21\u00be\n\21\3\22\3\22\3\22\3\22\5\22\u00c4\n\22"+
		"\3\23\3\23\5\23\u00c8\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\5\26\u00d1"+
		"\n\26\3\27\3\27\5\27\u00d5\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\5\34"+
		"\u00ec\n\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\5\37\u00f8"+
		"\n\37\3 \3 \3 \7 \u00fd\n \f \16 \u0100\13 \3!\3!\3!\3\"\3\"\3#\3#\3#"+
		"\3$\3$\3%\3%\5%\u010e\n%\3&\3&\3\'\3\'\3\'\2\3\f(\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL\2\6\3\2,-\3\2\21\22"+
		"\3\2/\60\3\2#&\2\u0114\2O\3\2\2\2\4Z\3\2\2\2\6\\\3\2\2\2\bd\3\2\2\2\n"+
		"l\3\2\2\2\fx\3\2\2\2\16\u0082\3\2\2\2\20\u0084\3\2\2\2\22\u009c\3\2\2"+
		"\2\24\u009f\3\2\2\2\26\u00a6\3\2\2\2\30\u00a8\3\2\2\2\32\u00ae\3\2\2\2"+
		"\34\u00b2\3\2\2\2\36\u00b7\3\2\2\2 \u00b9\3\2\2\2\"\u00c3\3\2\2\2$\u00c7"+
		"\3\2\2\2&\u00c9\3\2\2\2(\u00cb\3\2\2\2*\u00d0\3\2\2\2,\u00d2\3\2\2\2."+
		"\u00d8\3\2\2\2\60\u00dd\3\2\2\2\62\u00e2\3\2\2\2\64\u00e7\3\2\2\2\66\u00e9"+
		"\3\2\2\28\u00ed\3\2\2\2:\u00f0\3\2\2\2<\u00f7\3\2\2\2>\u00f9\3\2\2\2@"+
		"\u0101\3\2\2\2B\u0104\3\2\2\2D\u0106\3\2\2\2F\u0109\3\2\2\2H\u010d\3\2"+
		"\2\2J\u010f\3\2\2\2L\u0111\3\2\2\2NP\5\24\13\2ON\3\2\2\2OP\3\2\2\2PQ\3"+
		"\2\2\2QR\7\2\2\3R\3\3\2\2\2S[\7\3\2\2T[\7\4\2\2U[\7\5\2\2V[\7\6\2\2W["+
		"\5\f\7\2X[\5\20\t\2Y[\5\6\4\2ZS\3\2\2\2ZT\3\2\2\2ZU\3\2\2\2ZV\3\2\2\2"+
		"ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\5\3\2\2\2\\b\5\64\33\2]c\7\7\2\2^_\7\b"+
		"\2\2_`\5\b\5\2`a\7\t\2\2ac\3\2\2\2b]\3\2\2\2b^\3\2\2\2c\7\3\2\2\2di\5"+
		"\n\6\2ef\7\n\2\2fh\5\n\6\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\t"+
		"\3\2\2\2ki\3\2\2\2lm\5\4\3\2m\13\3\2\2\2no\b\7\1\2oy\5\16\b\2py\7\13\2"+
		"\2qy\7\f\2\2ry\7\r\2\2sy\7\16\2\2ty\7\17\2\2uy\7\20\2\2vw\7.\2\2wy\5\f"+
		"\7\3xn\3\2\2\2xp\3\2\2\2xq\3\2\2\2xr\3\2\2\2xs\3\2\2\2xt\3\2\2\2xu\3\2"+
		"\2\2xv\3\2\2\2y\177\3\2\2\2z{\f\4\2\2{|\t\2\2\2|~\5\f\7\5}z\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\r\3\2\2\2\u0081\177\3\2"+
		"\2\2\u0082\u0083\t\3\2\2\u0083\17\3\2\2\2\u0084\u0085\7)\2\2\u0085\u0086"+
		"\t\4\2\2\u0086\u0087\7)\2\2\u0087\21\3\2\2\2\u0088\u008a\5\4\3\2\u0089"+
		"\u008b\7\23\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u009d\3"+
		"\2\2\2\u008c\u008e\5*\26\2\u008d\u008f\7\23\2\2\u008e\u008d\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u009d\3\2\2\2\u0090\u0092\5\26\f\2\u0091\u0093\7"+
		"\23\2\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u009d\3\2\2\2\u0094"+
		"\u0096\5\36\20\2\u0095\u0097\7\23\2\2\u0096\u0095\3\2\2\2\u0096\u0097"+
		"\3\2\2\2\u0097\u009d\3\2\2\2\u0098\u009a\5$\23\2\u0099\u009b\7\23\2\2"+
		"\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u0088"+
		"\3\2\2\2\u009c\u008c\3\2\2\2\u009c\u0090\3\2\2\2\u009c\u0094\3\2\2\2\u009c"+
		"\u0098\3\2\2\2\u009d\23\3\2\2\2\u009e\u00a0\5\22\n\2\u009f\u009e\3\2\2"+
		"\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\25"+
		"\3\2\2\2\u00a3\u00a7\5\30\r\2\u00a4\u00a7\5\32\16\2\u00a5\u00a7\5\34\17"+
		"\2\u00a6\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\27"+
		"\3\2\2\2\u00a8\u00a9\7\24\2\2\u00a9\u00aa\5H%\2\u00aa\u00ab\7\25\2\2\u00ab"+
		"\u00ac\5\4\3\2\u00ac\u00ad\5,\27\2\u00ad\31\3\2\2\2\u00ae\u00af\7\26\2"+
		"\2\u00af\u00b0\5\4\3\2\u00b0\u00b1\5,\27\2\u00b1\33\3\2\2\2\u00b2\u00b3"+
		"\7\27\2\2\u00b3\u00b4\5,\27\2\u00b4\u00b5\7\26\2\2\u00b5\u00b6\5\4\3\2"+
		"\u00b6\35\3\2\2\2\u00b7\u00b8\5 \21\2\u00b8\37\3\2\2\2\u00b9\u00ba\7\30"+
		"\2\2\u00ba\u00bb\5\4\3\2\u00bb\u00bd\5,\27\2\u00bc\u00be\5\"\22\2\u00bd"+
		"\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be!\3\2\2\2\u00bf\u00c0\7\31\2\2"+
		"\u00c0\u00c4\5,\27\2\u00c1\u00c2\7\31\2\2\u00c2\u00c4\5 \21\2\u00c3\u00bf"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4#\3\2\2\2\u00c5\u00c8\5&\24\2\u00c6"+
		"\u00c8\5(\25\2\u00c7\u00c5\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8%\3\2\2\2"+
		"\u00c9\u00ca\7\32\2\2\u00ca\'\3\2\2\2\u00cb\u00cc\7\33\2\2\u00cc)\3\2"+
		"\2\2\u00cd\u00d1\5.\30\2\u00ce\u00d1\5\60\31\2\u00cf\u00d1\5\62\32\2\u00d0"+
		"\u00cd\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1+\3\2\2\2"+
		"\u00d2\u00d4\7\34\2\2\u00d3\u00d5\5\24\13\2\u00d4\u00d3\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\7\35\2\2\u00d7-\3\2\2\2"+
		"\u00d8\u00d9\7\36\2\2\u00d9\u00da\5H%\2\u00da\u00db\7\37\2\2\u00db\u00dc"+
		"\5\4\3\2\u00dc/\3\2\2\2\u00dd\u00de\7 \2\2\u00de\u00df\5H%\2\u00df\u00e0"+
		"\7\37\2\2\u00e0\u00e1\5\4\3\2\u00e1\61\3\2\2\2\u00e2\u00e3\7!\2\2\u00e3"+
		"\u00e4\5\64\33\2\u00e4\u00e5\5\66\34\2\u00e5\u00e6\5:\36\2\u00e6\63\3"+
		"\2\2\2\u00e7\u00e8\7(\2\2\u00e8\65\3\2\2\2\u00e9\u00eb\5<\37\2\u00ea\u00ec"+
		"\58\35\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\67\3\2\2\2\u00ed"+
		"\u00ee\7\61\2\2\u00ee\u00ef\5F$\2\u00ef9\3\2\2\2\u00f0\u00f1\5,\27\2\u00f1"+
		";\3\2\2\2\u00f2\u00f8\7\7\2\2\u00f3\u00f4\7\b\2\2\u00f4\u00f5\5> \2\u00f5"+
		"\u00f6\7\t\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f7\u00f3\3\2"+
		"\2\2\u00f8=\3\2\2\2\u00f9\u00fe\5@!\2\u00fa\u00fb\7\n\2\2\u00fb\u00fd"+
		"\5@!\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff?\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\5B\"\2\u0102"+
		"\u0103\5D#\2\u0103A\3\2\2\2\u0104\u0105\7(\2\2\u0105C\3\2\2\2\u0106\u0107"+
		"\7\"\2\2\u0107\u0108\5F$\2\u0108E\3\2\2\2\u0109\u010a\t\5\2\2\u010aG\3"+
		"\2\2\2\u010b\u010e\5L\'\2\u010c\u010e\5J&\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010c\3\2\2\2\u010eI\3\2\2\2\u010f\u0110\7\'\2\2\u0110K\3\2\2\2\u0111"+
		"\u0112\7(\2\2\u0112M\3\2\2\2\31OZbix\177\u008a\u008e\u0092\u0096\u009a"+
		"\u009c\u00a1\u00a6\u00bd\u00c3\u00c7\u00d0\u00d4\u00eb\u00f7\u00fe\u010d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}