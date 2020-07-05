// Generated from /Users/irony/IdeaProjects/playground/src/main/playgroundGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class playgroundGrammarLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "IDENTIFIER", "IDENT_HEAD", "IDENT_CHAR", 
			"INTEGER_LITERAL", "DECIMAL_LITERAL", "DECIMAL_DIGIT", "WS", "AND", "OR", 
			"NOT", "UNTIL", "THROUGH", "ARROW"
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


	public playgroundGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "playgroundGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u018b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3"+
		"!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3"+
		"%\3&\3&\3\'\3\'\7\'\u015d\n\'\f\'\16\'\u0160\13\'\3(\5(\u0163\n(\3)\3"+
		")\5)\u0167\n)\3*\3*\3+\6+\u016c\n+\r+\16+\u016d\3,\3,\3-\6-\u0173\n-\r"+
		"-\16-\u0174\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\61\3\61\3\62"+
		"\3\62\3\62\3\62\3\63\3\63\3\63\2\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O\2Q\2S)U*W\2"+
		"Y+[,]-_.a/c\60e\61\3\2\5\5\2C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\2\u018b"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3"+
		"\2\2\2\5u\3\2\2\2\7\u0080\3\2\2\2\t\u008f\3\2\2\2\13\u009c\3\2\2\2\r\u009f"+
		"\3\2\2\2\17\u00a1\3\2\2\2\21\u00a3\3\2\2\2\23\u00a5\3\2\2\2\25\u00ad\3"+
		"\2\2\2\27\u00be\3\2\2\2\31\u00cf\3\2\2\2\33\u00d9\3\2\2\2\35\u00e7\3\2"+
		"\2\2\37\u00f6\3\2\2\2!\u00fb\3\2\2\2#\u0101\3\2\2\2%\u0103\3\2\2\2\'\u0107"+
		"\3\2\2\2)\u010a\3\2\2\2+\u0110\3\2\2\2-\u0117\3\2\2\2/\u011a\3\2\2\2\61"+
		"\u011f\3\2\2\2\63\u0125\3\2\2\2\65\u012e\3\2\2\2\67\u0130\3\2\2\29\u0132"+
		"\3\2\2\2;\u0136\3\2\2\2=\u0138\3\2\2\2?\u013c\3\2\2\2A\u0141\3\2\2\2C"+
		"\u0143\3\2\2\2E\u0147\3\2\2\2G\u014c\3\2\2\2I\u0153\3\2\2\2K\u0158\3\2"+
		"\2\2M\u015a\3\2\2\2O\u0162\3\2\2\2Q\u0166\3\2\2\2S\u0168\3\2\2\2U\u016b"+
		"\3\2\2\2W\u016f\3\2\2\2Y\u0172\3\2\2\2[\u0178\3\2\2\2]\u017b\3\2\2\2_"+
		"\u017e\3\2\2\2a\u0180\3\2\2\2c\u0184\3\2\2\2e\u0188\3\2\2\2gh\7o\2\2h"+
		"i\7q\2\2ij\7x\2\2jk\7g\2\2kl\7H\2\2lm\7q\2\2mn\7t\2\2no\7y\2\2op\7c\2"+
		"\2pq\7t\2\2qr\7f\2\2rs\7*\2\2st\7+\2\2t\4\3\2\2\2uv\7v\2\2vw\7w\2\2wx"+
		"\7t\2\2xy\7p\2\2yz\7N\2\2z{\7g\2\2{|\7h\2\2|}\7v\2\2}~\7*\2\2~\177\7+"+
		"\2\2\177\6\3\2\2\2\u0080\u0081\7v\2\2\u0081\u0082\7q\2\2\u0082\u0083\7"+
		"i\2\2\u0083\u0084\7i\2\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086\u0087"+
		"\7U\2\2\u0087\u0088\7y\2\2\u0088\u0089\7k\2\2\u0089\u008a\7v\2\2\u008a"+
		"\u008b\7e\2\2\u008b\u008c\7j\2\2\u008c\u008d\7*\2\2\u008d\u008e\7+\2\2"+
		"\u008e\b\3\2\2\2\u008f\u0090\7e\2\2\u0090\u0091\7q\2\2\u0091\u0092\7n"+
		"\2\2\u0092\u0093\7n\2\2\u0093\u0094\7g\2\2\u0094\u0095\7e\2\2\u0095\u0096"+
		"\7v\2\2\u0096\u0097\7I\2\2\u0097\u0098\7g\2\2\u0098\u0099\7o\2\2\u0099"+
		"\u009a\7*\2\2\u009a\u009b\7+\2\2\u009b\n\3\2\2\2\u009c\u009d\7*\2\2\u009d"+
		"\u009e\7+\2\2\u009e\f\3\2\2\2\u009f\u00a0\7*\2\2\u00a0\16\3\2\2\2\u00a1"+
		"\u00a2\7+\2\2\u00a2\20\3\2\2\2\u00a3\u00a4\7.\2\2\u00a4\22\3\2\2\2\u00a5"+
		"\u00a6\7k\2\2\u00a6\u00a7\7u\2\2\u00a7\u00a8\7Q\2\2\u00a8\u00a9\7p\2\2"+
		"\u00a9\u00aa\7I\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7o\2\2\u00ac\24\3\2"+
		"\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7Q\2\2\u00b0\u00b1"+
		"\7p\2\2\u00b1\u00b2\7Q\2\2\u00b2\u00b3\7r\2\2\u00b3\u00b4\7g\2\2\u00b4"+
		"\u00b5\7p\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8\7U\2\2"+
		"\u00b8\u00b9\7y\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7v\2\2\u00bb\u00bc"+
		"\7e\2\2\u00bc\u00bd\7j\2\2\u00bd\26\3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0"+
		"\7u\2\2\u00c0\u00c1\7Q\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7E\2\2\u00c3"+
		"\u00c4\7n\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7g\2\2"+
		"\u00c7\u00c8\7f\2\2\u00c8\u00c9\7U\2\2\u00c9\u00ca\7y\2\2\u00ca\u00cb"+
		"\7k\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7j\2\2\u00ce"+
		"\30\3\2\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7D\2\2\u00d2"+
		"\u00d3\7n\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7e\2\2\u00d5\u00d6\7m\2\2"+
		"\u00d6\u00d7\7g\2\2\u00d7\u00d8\7f\2\2\u00d8\32\3\2\2\2\u00d9\u00da\7"+
		"k\2\2\u00da\u00db\7u\2\2\u00db\u00dc\7D\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de"+
		"\7q\2\2\u00de\u00df\7e\2\2\u00df\u00e0\7m\2\2\u00e0\u00e1\7g\2\2\u00e1"+
		"\u00e2\7f\2\2\u00e2\u00e3\7N\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7h\2\2"+
		"\u00e5\u00e6\7v\2\2\u00e6\34\3\2\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7"+
		"u\2\2\u00e9\u00ea\7D\2\2\u00ea\u00eb\7n\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed"+
		"\7e\2\2\u00ed\u00ee\7m\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7f\2\2\u00f0"+
		"\u00f1\7T\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7i\2\2\u00f3\u00f4\7j\2\2"+
		"\u00f4\u00f5\7v\2\2\u00f5\36\3\2\2\2\u00f6\u00f7\7v\2\2\u00f7\u00f8\7"+
		"t\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7g\2\2\u00fa \3\2\2\2\u00fb\u00fc"+
		"\7h\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7n\2\2\u00fe\u00ff\7u\2\2\u00ff"+
		"\u0100\7g\2\2\u0100\"\3\2\2\2\u0101\u0102\7=\2\2\u0102$\3\2\2\2\u0103"+
		"\u0104\7h\2\2\u0104\u0105\7q\2\2\u0105\u0106\7t\2\2\u0106&\3\2\2\2\u0107"+
		"\u0108\7k\2\2\u0108\u0109\7p\2\2\u0109(\3\2\2\2\u010a\u010b\7y\2\2\u010b"+
		"\u010c\7j\2\2\u010c\u010d\7k\2\2\u010d\u010e\7n\2\2\u010e\u010f\7g\2\2"+
		"\u010f*\3\2\2\2\u0110\u0111\7t\2\2\u0111\u0112\7g\2\2\u0112\u0113\7r\2"+
		"\2\u0113\u0114\7g\2\2\u0114\u0115\7c\2\2\u0115\u0116\7v\2\2\u0116,\3\2"+
		"\2\2\u0117\u0118\7k\2\2\u0118\u0119\7h\2\2\u0119.\3\2\2\2\u011a\u011b"+
		"\7g\2\2\u011b\u011c\7n\2\2\u011c\u011d\7u\2\2\u011d\u011e\7g\2\2\u011e"+
		"\60\3\2\2\2\u011f\u0120\7d\2\2\u0120\u0121\7t\2\2\u0121\u0122\7g\2\2\u0122"+
		"\u0123\7c\2\2\u0123\u0124\7m\2\2\u0124\62\3\2\2\2\u0125\u0126\7e\2\2\u0126"+
		"\u0127\7q\2\2\u0127\u0128\7p\2\2\u0128\u0129\7v\2\2\u0129\u012a\7k\2\2"+
		"\u012a\u012b\7p\2\2\u012b\u012c\7w\2\2\u012c\u012d\7g\2\2\u012d\64\3\2"+
		"\2\2\u012e\u012f\7}\2\2\u012f\66\3\2\2\2\u0130\u0131\7\177\2\2\u01318"+
		"\3\2\2\2\u0132\u0133\7n\2\2\u0133\u0134\7g\2\2\u0134\u0135\7v\2\2\u0135"+
		":\3\2\2\2\u0136\u0137\7?\2\2\u0137<\3\2\2\2\u0138\u0139\7x\2\2\u0139\u013a"+
		"\7c\2\2\u013a\u013b\7t\2\2\u013b>\3\2\2\2\u013c\u013d\7h\2\2\u013d\u013e"+
		"\7w\2\2\u013e\u013f\7p\2\2\u013f\u0140\7e\2\2\u0140@\3\2\2\2\u0141\u0142"+
		"\7<\2\2\u0142B\3\2\2\2\u0143\u0144\7K\2\2\u0144\u0145\7p\2\2\u0145\u0146"+
		"\7v\2\2\u0146D\3\2\2\2\u0147\u0148\7D\2\2\u0148\u0149\7q\2\2\u0149\u014a"+
		"\7q\2\2\u014a\u014b\7n\2\2\u014bF\3\2\2\2\u014c\u014d\7F\2\2\u014d\u014e"+
		"\7q\2\2\u014e\u014f\7w\2\2\u014f\u0150\7d\2\2\u0150\u0151\7n\2\2\u0151"+
		"\u0152\7g\2\2\u0152H\3\2\2\2\u0153\u0154\7X\2\2\u0154\u0155\7q\2\2\u0155"+
		"\u0156\7k\2\2\u0156\u0157\7f\2\2\u0157J\3\2\2\2\u0158\u0159\7a\2\2\u0159"+
		"L\3\2\2\2\u015a\u015e\5O(\2\u015b\u015d\5Q)\2\u015c\u015b\3\2\2\2\u015d"+
		"\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015fN\3\2\2\2"+
		"\u0160\u015e\3\2\2\2\u0161\u0163\t\2\2\2\u0162\u0161\3\2\2\2\u0163P\3"+
		"\2\2\2\u0164\u0167\t\3\2\2\u0165\u0167\5O(\2\u0166\u0164\3\2\2\2\u0166"+
		"\u0165\3\2\2\2\u0167R\3\2\2\2\u0168\u0169\5U+\2\u0169T\3\2\2\2\u016a\u016c"+
		"\5W,\2\u016b\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d"+
		"\u016e\3\2\2\2\u016eV\3\2\2\2\u016f\u0170\4\62;\2\u0170X\3\2\2\2\u0171"+
		"\u0173\t\4\2\2\u0172\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0172\3\2"+
		"\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\b-\2\2\u0177"+
		"Z\3\2\2\2\u0178\u0179\7(\2\2\u0179\u017a\7(\2\2\u017a\\\3\2\2\2\u017b"+
		"\u017c\7~\2\2\u017c\u017d\7~\2\2\u017d^\3\2\2\2\u017e\u017f\7#\2\2\u017f"+
		"`\3\2\2\2\u0180\u0181\7\60\2\2\u0181\u0182\7\60\2\2\u0182\u0183\7>\2\2"+
		"\u0183b\3\2\2\2\u0184\u0185\7\60\2\2\u0185\u0186\7\60\2\2\u0186\u0187"+
		"\7\60\2\2\u0187d\3\2\2\2\u0188\u0189\7/\2\2\u0189\u018a\7@\2\2\u018af"+
		"\3\2\2\2\b\2\u015e\u0162\u0166\u016d\u0174\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}