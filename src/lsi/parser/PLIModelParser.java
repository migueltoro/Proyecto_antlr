// Generated from C:/Users/Miguel Toro/Proyecto_antlr/antlr/grammars/PLIModel.g4 by ANTLR 4.13.2
package lsi.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PLIModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		HEAD_SECTION=46, GOAL_SECTION=47, CONSTRAINTS_SECTION=48, BOUNDS_SECTION=49, 
		BIN_SECTION=50, INT_SECTION=51, FREE_SECTION=52, SEMI_SECTION=53, ID=54, 
		INT=55, DOUBLE=56, WS=57, COMMENT=58, LINE_COMMENT=59, ERROR_CHAR=60;
	public static final int
		RULE_model = 0, RULE_head = 1, RULE_goal = 2, RULE_constraints = 3, RULE_bounds = 4, 
		RULE_bin_vars = 5, RULE_int_vars = 6, RULE_free_vars = 7, RULE_semi_continuous_vars = 8, 
		RULE_objective = 9, RULE_declaration = 10, RULE_formal_parameters = 11, 
		RULE_formal_parameter = 12, RULE_type = 13, RULE_var_collection = 14, 
		RULE_expr_collection = 15, RULE_linear_expr_collection = 16, RULE_set_of_constraints = 17, 
		RULE_set_of_bounds = 18, RULE_set_of_LinearExprs = 19, RULE_set_of_vars = 20, 
		RULE_set_of_expressions = 21, RULE_constraint = 22, RULE_atomic_constraint = 23, 
		RULE_compound_constraint = 24, RULE_bound = 25, RULE_linear_expr = 26, 
		RULE_slinear_term = 27, RULE_linear_term = 28, RULE_set_of_linear_factor = 29, 
		RULE_linear_factor = 30, RULE_var = 31, RULE_index_list = 32, RULE_index = 33, 
		RULE_pair = 34, RULE_expression = 35, RULE_function_call = 36, RULE_rel_op = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "head", "goal", "constraints", "bounds", "bin_vars", "int_vars", 
			"free_vars", "semi_continuous_vars", "objective", "declaration", "formal_parameters", 
			"formal_parameter", "type", "var_collection", "expr_collection", "linear_expr_collection", 
			"set_of_constraints", "set_of_bounds", "set_of_LinearExprs", "set_of_vars", 
			"set_of_expressions", "constraint", "atomic_constraint", "compound_constraint", 
			"bound", "linear_expr", "slinear_term", "linear_term", "set_of_linear_factor", 
			"linear_factor", "var", "index_list", "index", "pair", "expression", 
			"function_call", "rel_op"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'min'", "'max'", "'='", "'('", "')'", "','", "'Integer'", "'Double'", 
			"'Boolean'", "'String'", "'|'", "'or'", "'=>'", "'!='", "'->'", "'=='", 
			"'allDifferent'", "'permutation'", "';'", "'in'", "'MAX'", "'MIN'", "'OR'", 
			"'AND'", "'ABS'", "'PWL'", "':'", "'<='", "'+'", "'-'", "'sum'", "'['", 
			"']'", "'..'", "'*'", "'/'", "'%'", "'<'", "'>'", "'>='", "'&&'", "'||'", 
			"'!'", "'(int)'", "'(double)'", "'head section'", "'goal section'", "'constraints section'", 
			"'bounds section'", "'bin'", "'int'", "'free'", "'semi-continuous'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "HEAD_SECTION", 
			"GOAL_SECTION", "CONSTRAINTS_SECTION", "BOUNDS_SECTION", "BIN_SECTION", 
			"INT_SECTION", "FREE_SECTION", "SEMI_SECTION", "ID", "INT", "DOUBLE", 
			"WS", "COMMENT", "LINE_COMMENT", "ERROR_CHAR"
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
	public String getGrammarFileName() { return "PLIModel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PLIModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModelContext extends ParserRuleContext {
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PLIModelParser.EOF, 0); }
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public BoundsContext bounds() {
			return getRuleContext(BoundsContext.class,0);
		}
		public Bin_varsContext bin_vars() {
			return getRuleContext(Bin_varsContext.class,0);
		}
		public Int_varsContext int_vars() {
			return getRuleContext(Int_varsContext.class,0);
		}
		public Free_varsContext free_vars() {
			return getRuleContext(Free_varsContext.class,0);
		}
		public Semi_continuous_varsContext semi_continuous_vars() {
			return getRuleContext(Semi_continuous_varsContext.class,0);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HEAD_SECTION) {
				{
				setState(76);
				head();
				}
			}

			setState(79);
			goal();
			setState(80);
			constraints();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BOUNDS_SECTION) {
				{
				setState(81);
				bounds();
				}
			}

			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BIN_SECTION) {
				{
				setState(84);
				bin_vars();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INT_SECTION) {
				{
				setState(87);
				int_vars();
				}
			}

			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FREE_SECTION) {
				{
				setState(90);
				free_vars();
				}
			}

			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI_SECTION) {
				{
				setState(93);
				semi_continuous_vars();
				}
			}

			setState(96);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HeadContext extends ParserRuleContext {
		public TerminalNode HEAD_SECTION() { return getToken(PLIModelParser.HEAD_SECTION, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_head);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(HEAD_SECTION);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0)) {
				{
				{
				setState(99);
				declaration();
				}
				}
				setState(104);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GoalContext extends ParserRuleContext {
		public TerminalNode GOAL_SECTION() { return getToken(PLIModelParser.GOAL_SECTION, 0); }
		public ObjectiveContext objective() {
			return getRuleContext(ObjectiveContext.class,0);
		}
		public Linear_exprContext linear_expr() {
			return getRuleContext(Linear_exprContext.class,0);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitGoal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitGoal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_goal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(GOAL_SECTION);
			setState(106);
			objective();
			setState(107);
			linear_expr();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConstraintsContext extends ParserRuleContext {
		public TerminalNode CONSTRAINTS_SECTION() { return getToken(PLIModelParser.CONSTRAINTS_SECTION, 0); }
		public List<Set_of_constraintsContext> set_of_constraints() {
			return getRuleContexts(Set_of_constraintsContext.class);
		}
		public Set_of_constraintsContext set_of_constraints(int i) {
			return getRuleContext(Set_of_constraintsContext.class,i);
		}
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitConstraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitConstraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(CONSTRAINTS_SECTION);
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110);
				set_of_constraints();
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 126162365976023056L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoundsContext extends ParserRuleContext {
		public TerminalNode BOUNDS_SECTION() { return getToken(PLIModelParser.BOUNDS_SECTION, 0); }
		public List<Set_of_boundsContext> set_of_bounds() {
			return getRuleContexts(Set_of_boundsContext.class);
		}
		public Set_of_boundsContext set_of_bounds(int i) {
			return getRuleContext(Set_of_boundsContext.class,i);
		}
		public BoundsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bounds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterBounds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitBounds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitBounds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundsContext bounds() throws RecognitionException {
		BoundsContext _localctx = new BoundsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bounds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(BOUNDS_SECTION);
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				set_of_bounds();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 126162363828142096L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class Bin_varsContext extends ParserRuleContext {
		public TerminalNode BIN_SECTION() { return getToken(PLIModelParser.BIN_SECTION, 0); }
		public List<Set_of_varsContext> set_of_vars() {
			return getRuleContexts(Set_of_varsContext.class);
		}
		public Set_of_varsContext set_of_vars(int i) {
			return getRuleContext(Set_of_varsContext.class,i);
		}
		public Bin_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bin_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterBin_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitBin_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitBin_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bin_varsContext bin_vars() throws RecognitionException {
		Bin_varsContext _localctx = new Bin_varsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bin_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(BIN_SECTION);
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(122);
				set_of_vars();
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	@SuppressWarnings("CheckReturnValue")
	public static class Int_varsContext extends ParserRuleContext {
		public TerminalNode INT_SECTION() { return getToken(PLIModelParser.INT_SECTION, 0); }
		public List<Set_of_varsContext> set_of_vars() {
			return getRuleContexts(Set_of_varsContext.class);
		}
		public Set_of_varsContext set_of_vars(int i) {
			return getRuleContext(Set_of_varsContext.class,i);
		}
		public Int_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterInt_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitInt_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitInt_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_varsContext int_vars() throws RecognitionException {
		Int_varsContext _localctx = new Int_varsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_int_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(INT_SECTION);
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				set_of_vars();
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	@SuppressWarnings("CheckReturnValue")
	public static class Free_varsContext extends ParserRuleContext {
		public TerminalNode FREE_SECTION() { return getToken(PLIModelParser.FREE_SECTION, 0); }
		public List<Set_of_varsContext> set_of_vars() {
			return getRuleContexts(Set_of_varsContext.class);
		}
		public Set_of_varsContext set_of_vars(int i) {
			return getRuleContext(Set_of_varsContext.class,i);
		}
		public Free_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_free_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFree_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFree_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFree_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Free_varsContext free_vars() throws RecognitionException {
		Free_varsContext _localctx = new Free_varsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_free_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(FREE_SECTION);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(134);
				set_of_vars();
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	@SuppressWarnings("CheckReturnValue")
	public static class Semi_continuous_varsContext extends ParserRuleContext {
		public TerminalNode SEMI_SECTION() { return getToken(PLIModelParser.SEMI_SECTION, 0); }
		public List<Set_of_varsContext> set_of_vars() {
			return getRuleContexts(Set_of_varsContext.class);
		}
		public Set_of_varsContext set_of_vars(int i) {
			return getRuleContext(Set_of_varsContext.class,i);
		}
		public Semi_continuous_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_semi_continuous_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSemi_continuous_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSemi_continuous_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSemi_continuous_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Semi_continuous_varsContext semi_continuous_vars() throws RecognitionException {
		Semi_continuous_varsContext _localctx = new Semi_continuous_varsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_semi_continuous_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(SEMI_SECTION);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				set_of_vars();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectiveContext extends ParserRuleContext {
		public ObjectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterObjective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitObjective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitObjective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectiveContext objective() throws RecognitionException {
		ObjectiveContext _localctx = new ObjectiveContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_objective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends DeclarationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public Formal_parametersContext formal_parameters() {
			return getRuleContext(Formal_parametersContext.class,0);
		}
		public FunctionDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclarationContext extends DeclarationContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaration);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new VarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				type();
				setState(148);
				match(ID);
				setState(149);
				match(T__2);
				setState(150);
				expression(0);
				}
				break;
			case 2:
				_localctx = new FunctionDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				type();
				setState(153);
				match(ID);
				setState(154);
				match(T__3);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0)) {
					{
					setState(155);
					formal_parameters();
					}
				}

				setState(158);
				match(T__4);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Formal_parametersContext extends ParserRuleContext {
		public List<Formal_parameterContext> formal_parameter() {
			return getRuleContexts(Formal_parameterContext.class);
		}
		public Formal_parameterContext formal_parameter(int i) {
			return getRuleContext(Formal_parameterContext.class,i);
		}
		public Formal_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFormal_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFormal_parameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFormal_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parametersContext formal_parameters() throws RecognitionException {
		Formal_parametersContext _localctx = new Formal_parametersContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formal_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			formal_parameter();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(163);
				match(T__5);
				setState(164);
				formal_parameter();
				}
				}
				setState(169);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Formal_parameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public Formal_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFormal_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFormal_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFormal_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			type();
			setState(171);
			match(ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class Var_collectionContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public Var_collectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVar_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVar_collection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVar_collection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_collectionContext var_collection() throws RecognitionException {
		Var_collectionContext _localctx = new Var_collectionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_var_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			var();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(176);
				match(T__5);
				setState(177);
				var();
				}
				}
				setState(182);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_collectionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Expr_collectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterExpr_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitExpr_collection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitExpr_collection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_collectionContext expr_collection() throws RecognitionException {
		Expr_collectionContext _localctx = new Expr_collectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			expression(0);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(184);
				match(T__5);
				setState(185);
				expression(0);
				}
				}
				setState(190);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Linear_expr_collectionContext extends ParserRuleContext {
		public List<Linear_exprContext> linear_expr() {
			return getRuleContexts(Linear_exprContext.class);
		}
		public Linear_exprContext linear_expr(int i) {
			return getRuleContext(Linear_exprContext.class,i);
		}
		public Linear_expr_collectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linear_expr_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterLinear_expr_collection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitLinear_expr_collection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitLinear_expr_collection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Linear_expr_collectionContext linear_expr_collection() throws RecognitionException {
		Linear_expr_collectionContext _localctx = new Linear_expr_collectionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_linear_expr_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			linear_expr();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(192);
				match(T__5);
				setState(193);
				linear_expr();
				}
				}
				setState(198);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_constraintsContext extends ParserRuleContext {
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Set_of_constraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_constraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_constraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_constraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_constraintsContext set_of_constraints() throws RecognitionException {
		Set_of_constraintsContext _localctx = new Set_of_constraintsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_set_of_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			constraint();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(200);
				match(T__5);
				setState(201);
				index();
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(207);
				match(T__10);
				setState(208);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_boundsContext extends ParserRuleContext {
		public BoundContext bound() {
			return getRuleContext(BoundContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Set_of_boundsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_bounds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_bounds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_bounds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_bounds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_boundsContext set_of_bounds() throws RecognitionException {
		Set_of_boundsContext _localctx = new Set_of_boundsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_set_of_bounds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			bound();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(212);
				match(T__5);
				setState(213);
				index();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(219);
				match(T__10);
				setState(220);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_LinearExprsContext extends ParserRuleContext {
		public Linear_exprContext linear_expr() {
			return getRuleContext(Linear_exprContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Set_of_LinearExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_LinearExprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_LinearExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_LinearExprs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_LinearExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_LinearExprsContext set_of_LinearExprs() throws RecognitionException {
		Set_of_LinearExprsContext _localctx = new Set_of_LinearExprsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_set_of_LinearExprs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			linear_expr();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(224);
				match(T__5);
				setState(225);
				index();
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(231);
				match(T__10);
				setState(232);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_varsContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Set_of_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_varsContext set_of_vars() throws RecognitionException {
		Set_of_varsContext _localctx = new Set_of_varsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_set_of_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			var();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(236);
				match(T__5);
				setState(237);
				index();
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(243);
				match(T__10);
				setState(244);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_expressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public Set_of_expressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_expressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_expressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_expressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_expressionsContext set_of_expressions() throws RecognitionException {
		Set_of_expressionsContext _localctx = new Set_of_expressionsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_set_of_expressions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			expression(0);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(248);
					match(T__5);
					setState(249);
					index();
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(255);
				match(T__10);
				setState(256);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConstraintContext extends ParserRuleContext {
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
	 
		public ConstraintContext() { }
		public void copyFrom(ConstraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompoundConstraintExprContext extends ConstraintContext {
		public Compound_constraintContext compound_constraint() {
			return getRuleContext(Compound_constraintContext.class,0);
		}
		public CompoundConstraintExprContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterCompoundConstraintExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitCompoundConstraintExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitCompoundConstraintExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomicConstraintExprContext extends ConstraintContext {
		public Atomic_constraintContext atomic_constraint() {
			return getRuleContext(Atomic_constraintContext.class,0);
		}
		public AtomicConstraintExprContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAtomicConstraintExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAtomicConstraintExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAtomicConstraintExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_constraint);
		try {
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new AtomicConstraintExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				atomic_constraint();
				}
				break;
			case 2:
				_localctx = new CompoundConstraintExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				compound_constraint();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Atomic_constraintContext extends ParserRuleContext {
		public Atomic_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_constraint; }
	 
		public Atomic_constraintContext() { }
		public void copyFrom(Atomic_constraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalConstraintContext extends Atomic_constraintContext {
		public Linear_exprContext linear_expr() {
			return getRuleContext(Linear_exprContext.class,0);
		}
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RelationalConstraintContext(Atomic_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterRelationalConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitRelationalConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitRelationalConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atomic_constraintContext atomic_constraint() throws RecognitionException {
		Atomic_constraintContext _localctx = new Atomic_constraintContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_atomic_constraint);
		try {
			_localctx = new RelationalConstraintContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			linear_expr();
			setState(264);
			rel_op();
			setState(265);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_constraintContext extends ParserRuleContext {
		public Compound_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_constraint; }
	 
		public Compound_constraintContext() { }
		public void copyFrom(Compound_constraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DifferentValueConstraintContext extends Compound_constraintContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public DifferentValueConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterDifferentValueConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitDifferentValueConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitDifferentValueConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PermutationConstraintContext extends Compound_constraintContext {
		public Set_of_varsContext set_of_vars() {
			return getRuleContext(Set_of_varsContext.class,0);
		}
		public Set_of_expressionsContext set_of_expressions() {
			return getRuleContext(Set_of_expressionsContext.class,0);
		}
		public PermutationConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPermutationConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPermutationConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPermutationConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndBinaryConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_constraintsContext set_of_constraints() {
			return getRuleContext(Set_of_constraintsContext.class,0);
		}
		public AndBinaryConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAndBinaryConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAndBinaryConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAndBinaryConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AllDifferentConstraintContext extends Compound_constraintContext {
		public Set_of_varsContext set_of_vars() {
			return getRuleContext(Set_of_varsContext.class,0);
		}
		public AllDifferentConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAllDifferentConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAllDifferentConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAllDifferentConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MaxConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_varsContext set_of_vars() {
			return getRuleContext(Set_of_varsContext.class,0);
		}
		public MaxConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMaxConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMaxConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMaxConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MinConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_varsContext set_of_vars() {
			return getRuleContext(Set_of_varsContext.class,0);
		}
		public MinConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMinConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMinConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMinConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplicationConstraintContext extends Compound_constraintContext {
		public List<Atomic_constraintContext> atomic_constraint() {
			return getRuleContexts(Atomic_constraintContext.class);
		}
		public Atomic_constraintContext atomic_constraint(int i) {
			return getRuleContext(Atomic_constraintContext.class,i);
		}
		public ImplicationConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterImplicationConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitImplicationConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitImplicationConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualsConstraintContext extends Compound_constraintContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public EqualsConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterEqualsConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitEqualsConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitEqualsConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PiecewiseLinearConstraintContext extends Compound_constraintContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public PiecewiseLinearConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPiecewiseLinearConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPiecewiseLinearConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPiecewiseLinearConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AbsConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_varsContext set_of_vars() {
			return getRuleContext(Set_of_varsContext.class,0);
		}
		public AbsConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAbsConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAbsConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAbsConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MembershipConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_expressionsContext set_of_expressions() {
			return getRuleContext(Set_of_expressionsContext.class,0);
		}
		public MembershipConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMembershipConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMembershipConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMembershipConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndicatorConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public Atomic_constraintContext atomic_constraint() {
			return getRuleContext(Atomic_constraintContext.class,0);
		}
		public IndicatorConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndicatorConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndicatorConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndicatorConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrConstraintContext extends Compound_constraintContext {
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public List<Atomic_constraintContext> atomic_constraint() {
			return getRuleContexts(Atomic_constraintContext.class);
		}
		public Atomic_constraintContext atomic_constraint(int i) {
			return getRuleContext(Atomic_constraintContext.class,i);
		}
		public OrConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOrConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOrConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOrConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrBinaryConstraintContext extends Compound_constraintContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Set_of_constraintsContext set_of_constraints() {
			return getRuleContext(Set_of_constraintsContext.class,0);
		}
		public OrBinaryConstraintContext(Compound_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOrBinaryConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOrBinaryConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOrBinaryConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_constraintContext compound_constraint() throws RecognitionException {
		Compound_constraintContext _localctx = new Compound_constraintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_compound_constraint);
		int _la;
		try {
			int _alt;
			setState(364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new OrConstraintContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				match(T__11);
				setState(268);
				match(T__3);
				setState(269);
				rel_op();
				setState(270);
				match(INT);
				setState(271);
				match(T__5);
				setState(272);
				atomic_constraint();
				setState(275); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(273);
					match(T__10);
					setState(274);
					atomic_constraint();
					}
					}
					setState(277); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__10 );
				setState(279);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new ImplicationConstraintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				atomic_constraint();
				setState(282);
				match(T__12);
				setState(283);
				atomic_constraint();
				}
				break;
			case 3:
				_localctx = new DifferentValueConstraintContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				var();
				setState(286);
				match(T__13);
				setState(287);
				var();
				}
				break;
			case 4:
				_localctx = new IndicatorConstraintContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(289);
				var();
				setState(290);
				match(T__2);
				setState(291);
				match(INT);
				setState(292);
				match(T__14);
				setState(293);
				atomic_constraint();
				}
				break;
			case 5:
				_localctx = new EqualsConstraintContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(295);
				match(T__3);
				setState(296);
				var();
				setState(297);
				match(T__15);
				setState(298);
				var();
				setState(299);
				match(T__4);
				}
				break;
			case 6:
				_localctx = new AllDifferentConstraintContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(301);
				match(T__16);
				setState(302);
				match(T__3);
				setState(303);
				set_of_vars();
				setState(304);
				match(T__4);
				}
				break;
			case 7:
				_localctx = new PermutationConstraintContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(306);
				match(T__17);
				setState(307);
				match(T__3);
				setState(308);
				set_of_vars();
				setState(309);
				match(T__18);
				setState(310);
				set_of_expressions();
				setState(311);
				match(T__4);
				}
				break;
			case 8:
				_localctx = new MembershipConstraintContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(313);
				var();
				setState(314);
				match(T__19);
				setState(315);
				set_of_expressions();
				}
				break;
			case 9:
				_localctx = new MaxConstraintContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(317);
				var();
				setState(318);
				match(T__2);
				setState(319);
				match(T__20);
				setState(320);
				match(T__3);
				setState(321);
				set_of_vars();
				setState(322);
				match(T__4);
				}
				break;
			case 10:
				_localctx = new MinConstraintContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(324);
				var();
				setState(325);
				match(T__2);
				setState(326);
				match(T__21);
				setState(327);
				match(T__3);
				setState(328);
				set_of_vars();
				setState(329);
				match(T__4);
				}
				break;
			case 11:
				_localctx = new OrBinaryConstraintContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(331);
				var();
				setState(332);
				match(T__2);
				setState(333);
				match(T__22);
				setState(334);
				match(T__3);
				setState(335);
				set_of_constraints();
				setState(336);
				match(T__4);
				}
				break;
			case 12:
				_localctx = new AndBinaryConstraintContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(338);
				var();
				setState(339);
				match(T__2);
				setState(340);
				match(T__23);
				setState(341);
				match(T__3);
				setState(342);
				set_of_constraints();
				setState(343);
				match(T__4);
				}
				break;
			case 13:
				_localctx = new AbsConstraintContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(345);
				var();
				setState(346);
				match(T__2);
				setState(347);
				match(T__24);
				setState(348);
				match(T__3);
				setState(349);
				set_of_vars();
				setState(350);
				match(T__4);
				}
				break;
			case 14:
				_localctx = new PiecewiseLinearConstraintContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(352);
				var();
				setState(353);
				match(T__2);
				setState(354);
				match(T__25);
				setState(355);
				match(T__3);
				setState(356);
				var();
				setState(357);
				match(T__4);
				setState(358);
				match(T__26);
				setState(360); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(359);
						pair();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(362); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoundContext extends ParserRuleContext {
		public BoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bound; }
	 
		public BoundContext() { }
		public void copyFrom(BoundContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TwoSideBoundContext extends BoundContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TwoSideBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterTwoSideBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitTwoSideBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitTwoSideBound(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OneSideBoundContext extends BoundContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OneSideBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOneSideBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOneSideBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOneSideBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundContext bound() throws RecognitionException {
		BoundContext _localctx = new BoundContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_bound);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new OneSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				var();
				setState(367);
				rel_op();
				setState(368);
				expression(0);
				}
				break;
			case 2:
				_localctx = new TwoSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				expression(0);
				setState(371);
				match(T__27);
				setState(372);
				var();
				setState(373);
				match(T__27);
				setState(374);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Linear_exprContext extends ParserRuleContext {
		public Linear_termContext linear_term() {
			return getRuleContext(Linear_termContext.class,0);
		}
		public List<Slinear_termContext> slinear_term() {
			return getRuleContexts(Slinear_termContext.class);
		}
		public Slinear_termContext slinear_term(int i) {
			return getRuleContext(Slinear_termContext.class,i);
		}
		public Linear_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linear_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterLinear_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitLinear_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitLinear_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Linear_exprContext linear_expr() throws RecognitionException {
		Linear_exprContext _localctx = new Linear_exprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_linear_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			linear_term();
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28 || _la==T__29) {
				{
				{
				setState(379);
				slinear_term();
				}
				}
				setState(384);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Slinear_termContext extends ParserRuleContext {
		public Slinear_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slinear_term; }
	 
		public Slinear_termContext() { }
		public void copyFrom(Slinear_termContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusTermContext extends Slinear_termContext {
		public Linear_termContext linear_term() {
			return getRuleContext(Linear_termContext.class,0);
		}
		public PlusTermContext(Slinear_termContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPlusTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPlusTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPlusTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MinusTermContext extends Slinear_termContext {
		public Linear_termContext linear_term() {
			return getRuleContext(Linear_termContext.class,0);
		}
		public MinusTermContext(Slinear_termContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMinusTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMinusTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMinusTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Slinear_termContext slinear_term() throws RecognitionException {
		Slinear_termContext _localctx = new Slinear_termContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_slinear_term);
		try {
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__28:
				_localctx = new PlusTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(T__28);
				setState(386);
				linear_term();
				}
				break;
			case T__29:
				_localctx = new MinusTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(T__29);
				setState(388);
				linear_term();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Linear_termContext extends ParserRuleContext {
		public Linear_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linear_term; }
	 
		public Linear_termContext() { }
		public void copyFrom(Linear_termContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FactorTermContext extends Linear_termContext {
		public Linear_factorContext linear_factor() {
			return getRuleContext(Linear_factorContext.class,0);
		}
		public FactorTermContext(Linear_termContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFactorTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFactorTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFactorTerm(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SumSetOfTermContext extends Linear_termContext {
		public Set_of_linear_factorContext set_of_linear_factor() {
			return getRuleContext(Set_of_linear_factorContext.class,0);
		}
		public SumSetOfTermContext(Linear_termContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSumSetOfTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSumSetOfTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSumSetOfTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Linear_termContext linear_term() throws RecognitionException {
		Linear_termContext _localctx = new Linear_termContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_linear_term);
		try {
			setState(397);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__28:
			case T__29:
			case T__42:
			case T__43:
			case T__44:
			case ID:
			case INT:
			case DOUBLE:
				_localctx = new FactorTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				linear_factor();
				}
				break;
			case T__30:
				_localctx = new SumSetOfTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				match(T__30);
				setState(393);
				match(T__3);
				setState(394);
				set_of_linear_factor();
				setState(395);
				match(T__4);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Set_of_linear_factorContext extends ParserRuleContext {
		public Linear_factorContext linear_factor() {
			return getRuleContext(Linear_factorContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Set_of_linear_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_of_linear_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSet_of_linear_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSet_of_linear_factor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSet_of_linear_factor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_of_linear_factorContext set_of_linear_factor() throws RecognitionException {
		Set_of_linear_factorContext _localctx = new Set_of_linear_factorContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_set_of_linear_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			linear_factor();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(400);
				match(T__5);
				setState(401);
				index();
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(407);
				match(T__10);
				setState(408);
				expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Linear_factorContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Linear_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linear_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterLinear_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitLinear_factor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitLinear_factor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Linear_factorContext linear_factor() throws RecognitionException {
		Linear_factorContext _localctx = new Linear_factorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_linear_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(411);
				expression(0);
				}
				break;
			}
			setState(414);
			var();
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

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public Index_listContext index_list() {
			return getRuleContext(Index_listContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			match(ID);
			setState(417);
			match(T__31);
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126162363828142096L) != 0)) {
				{
				setState(418);
				index_list();
				}
			}

			setState(421);
			match(T__32);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Index_listContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Index_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndex_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndex_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndex_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Index_listContext index_list() throws RecognitionException {
		Index_listContext _localctx = new Index_listContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_index_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			expression(0);
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(424);
				match(T__5);
				setState(425);
				expression(0);
				}
				}
				setState(430);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(ID);
			setState(432);
			match(T__19);
			setState(433);
			expression(0);
			setState(434);
			match(T__33);
			setState(435);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PairContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(PLIModelParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(PLIModelParser.INT, i);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			match(T__3);
			setState(438);
			match(INT);
			setState(439);
			match(T__5);
			setState(440);
			match(INT);
			setState(441);
			match(T__4);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AdditiveExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RelationalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierExpressionContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public IdentifierExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultiplicativeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleLiteralExpressionContext extends ExpressionContext {
		public TerminalNode DOUBLE() { return getToken(PLIModelParser.DOUBLE, 0); }
		public DoubleLiteralExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterDoubleLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitDoubleLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitDoubleLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesizedExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntCastExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IntCastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIntCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIntCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIntCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralExpressionContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public IntegerLiteralExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIntegerLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIntegerLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIntegerLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualityExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExpressionContext extends ExpressionContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public FunctionCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFunctionCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleCastExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoubleCastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterDoubleCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitDoubleCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitDoubleCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitUnaryExpression(this);
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
		int _startState = 70;
		enterRecursionRule(_localctx, 70, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(444);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8797703634944L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(445);
				expression(8);
				}
				break;
			case 2:
				{
				_localctx = new IntCastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(446);
				match(T__43);
				setState(447);
				expression(7);
				}
				break;
			case 3:
				{
				_localctx = new DoubleCastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(448);
				match(T__44);
				setState(449);
				expression(6);
				}
				break;
			case 4:
				{
				_localctx = new ParenthesizedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(450);
				match(T__3);
				setState(451);
				expression(0);
				setState(452);
				match(T__4);
				}
				break;
			case 5:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(454);
				function_call();
				}
				break;
			case 6:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(455);
				match(ID);
				}
				break;
			case 7:
				{
				_localctx = new DoubleLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456);
				match(DOUBLE);
				}
				break;
			case 8:
				{
				_localctx = new IntegerLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(457);
				match(INT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(477);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(475);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(460);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(461);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(462);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(463);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(464);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(465);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(466);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(467);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924413784064L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(468);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(469);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(470);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__13) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(471);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(472);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(473);
						_la = _input.LA(1);
						if ( !(_la==T__40 || _la==T__41) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(474);
						expression(10);
						}
						break;
					}
					} 
				}
				setState(479);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public Expr_collectionContext expr_collection() {
			return getRuleContext(Expr_collectionContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			match(ID);
			setState(481);
			match(T__3);
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126162363828142096L) != 0)) {
				{
				setState(482);
				expr_collection();
				}
			}

			setState(485);
			match(T__4);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Rel_opContext extends ParserRuleContext {
		public Rel_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterRel_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitRel_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitRel_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rel_opContext rel_op() throws RecognitionException {
		Rel_opContext _localctx = new Rel_opContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_rel_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1924413784072L) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 35:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001<\u01ea\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000\u0003\u0000N\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000S\b\u0000\u0001\u0000"+
		"\u0003\u0000V\b\u0000\u0001\u0000\u0003\u0000Y\b\u0000\u0001\u0000\u0003"+
		"\u0000\\\b\u0000\u0001\u0000\u0003\u0000_\b\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0005\u0001e\b\u0001\n\u0001\f\u0001h\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0004\u0003p\b\u0003\u000b\u0003\f\u0003q\u0001\u0004\u0001\u0004\u0004"+
		"\u0004v\b\u0004\u000b\u0004\f\u0004w\u0001\u0005\u0001\u0005\u0004\u0005"+
		"|\b\u0005\u000b\u0005\f\u0005}\u0001\u0006\u0001\u0006\u0004\u0006\u0082"+
		"\b\u0006\u000b\u0006\f\u0006\u0083\u0001\u0007\u0001\u0007\u0004\u0007"+
		"\u0088\b\u0007\u000b\u0007\f\u0007\u0089\u0001\b\u0001\b\u0004\b\u008e"+
		"\b\b\u000b\b\f\b\u008f\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u009d\b\n\u0001\n\u0001"+
		"\n\u0003\n\u00a1\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00a6"+
		"\b\u000b\n\u000b\f\u000b\u00a9\t\u000b\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00b3\b\u000e"+
		"\n\u000e\f\u000e\u00b6\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00bb\b\u000f\n\u000f\f\u000f\u00be\t\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u00c3\b\u0010\n\u0010\f\u0010\u00c6\t\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00cb\b\u0011\n\u0011\f\u0011"+
		"\u00ce\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00d2\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00d7\b\u0012\n\u0012\f\u0012"+
		"\u00da\t\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00de\b\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00e3\b\u0013\n\u0013\f\u0013"+
		"\u00e6\t\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00ea\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00ef\b\u0014\n\u0014\f\u0014"+
		"\u00f2\t\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00f6\b\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00fb\b\u0015\n\u0015\f\u0015"+
		"\u00fe\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0102\b\u0015\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u0106\b\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0004\u0018\u0114\b\u0018\u000b"+
		"\u0018\f\u0018\u0115\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0004\u0018\u0169"+
		"\b\u0018\u000b\u0018\f\u0018\u016a\u0003\u0018\u016d\b\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0179\b\u0019\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u017d\b\u001a\n\u001a\f\u001a\u0180\t\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0186\b\u001b\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u018e\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u0193"+
		"\b\u001d\n\u001d\f\u001d\u0196\t\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u019a\b\u001d\u0001\u001e\u0003\u001e\u019d\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u01a4\b\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0005 \u01ab\b \n \f \u01ae\t"+
		" \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003#\u01cb\b#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0005#\u01dc\b#\n#\f#\u01df\t#\u0001$\u0001"+
		"$\u0001$\u0003$\u01e4\b$\u0001$\u0001$\u0001%\u0001%\u0001%\u0000\u0001"+
		"F&\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJ\u0000\t\u0001\u0000\u0001\u0002\u0001"+
		"\u0000\u0007\n\u0002\u0000\u001d\u001e++\u0001\u0000#%\u0001\u0000\u001d"+
		"\u001e\u0002\u0000\u001c\u001c&(\u0002\u0000\u0003\u0003\u000e\u000e\u0001"+
		"\u0000)*\u0003\u0000\u0003\u0003\u001c\u001c&(\u0206\u0000M\u0001\u0000"+
		"\u0000\u0000\u0002b\u0001\u0000\u0000\u0000\u0004i\u0001\u0000\u0000\u0000"+
		"\u0006m\u0001\u0000\u0000\u0000\bs\u0001\u0000\u0000\u0000\ny\u0001\u0000"+
		"\u0000\u0000\f\u007f\u0001\u0000\u0000\u0000\u000e\u0085\u0001\u0000\u0000"+
		"\u0000\u0010\u008b\u0001\u0000\u0000\u0000\u0012\u0091\u0001\u0000\u0000"+
		"\u0000\u0014\u00a0\u0001\u0000\u0000\u0000\u0016\u00a2\u0001\u0000\u0000"+
		"\u0000\u0018\u00aa\u0001\u0000\u0000\u0000\u001a\u00ad\u0001\u0000\u0000"+
		"\u0000\u001c\u00af\u0001\u0000\u0000\u0000\u001e\u00b7\u0001\u0000\u0000"+
		"\u0000 \u00bf\u0001\u0000\u0000\u0000\"\u00c7\u0001\u0000\u0000\u0000"+
		"$\u00d3\u0001\u0000\u0000\u0000&\u00df\u0001\u0000\u0000\u0000(\u00eb"+
		"\u0001\u0000\u0000\u0000*\u00f7\u0001\u0000\u0000\u0000,\u0105\u0001\u0000"+
		"\u0000\u0000.\u0107\u0001\u0000\u0000\u00000\u016c\u0001\u0000\u0000\u0000"+
		"2\u0178\u0001\u0000\u0000\u00004\u017a\u0001\u0000\u0000\u00006\u0185"+
		"\u0001\u0000\u0000\u00008\u018d\u0001\u0000\u0000\u0000:\u018f\u0001\u0000"+
		"\u0000\u0000<\u019c\u0001\u0000\u0000\u0000>\u01a0\u0001\u0000\u0000\u0000"+
		"@\u01a7\u0001\u0000\u0000\u0000B\u01af\u0001\u0000\u0000\u0000D\u01b5"+
		"\u0001\u0000\u0000\u0000F\u01ca\u0001\u0000\u0000\u0000H\u01e0\u0001\u0000"+
		"\u0000\u0000J\u01e7\u0001\u0000\u0000\u0000LN\u0003\u0002\u0001\u0000"+
		"ML\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000"+
		"\u0000OP\u0003\u0004\u0002\u0000PR\u0003\u0006\u0003\u0000QS\u0003\b\u0004"+
		"\u0000RQ\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000"+
		"\u0000\u0000TV\u0003\n\u0005\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000VX\u0001\u0000\u0000\u0000WY\u0003\f\u0006\u0000XW\u0001\u0000"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000Y[\u0001\u0000\u0000\u0000Z\\\u0003"+
		"\u000e\u0007\u0000[Z\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000"+
		"\\^\u0001\u0000\u0000\u0000]_\u0003\u0010\b\u0000^]\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0005\u0000"+
		"\u0000\u0001a\u0001\u0001\u0000\u0000\u0000bf\u0005.\u0000\u0000ce\u0003"+
		"\u0014\n\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\u0003\u0001\u0000\u0000"+
		"\u0000hf\u0001\u0000\u0000\u0000ij\u0005/\u0000\u0000jk\u0003\u0012\t"+
		"\u0000kl\u00034\u001a\u0000l\u0005\u0001\u0000\u0000\u0000mo\u00050\u0000"+
		"\u0000np\u0003\"\u0011\u0000on\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000"+
		"\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000r\u0007\u0001"+
		"\u0000\u0000\u0000su\u00051\u0000\u0000tv\u0003$\u0012\u0000ut\u0001\u0000"+
		"\u0000\u0000vw\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000x\t\u0001\u0000\u0000\u0000y{\u00052\u0000\u0000z|\u0003"+
		"(\u0014\u0000{z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}{\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u000b\u0001\u0000\u0000"+
		"\u0000\u007f\u0081\u00053\u0000\u0000\u0080\u0082\u0003(\u0014\u0000\u0081"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084"+
		"\r\u0001\u0000\u0000\u0000\u0085\u0087\u00054\u0000\u0000\u0086\u0088"+
		"\u0003(\u0014\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001"+
		"\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001"+
		"\u0000\u0000\u0000\u008a\u000f\u0001\u0000\u0000\u0000\u008b\u008d\u0005"+
		"5\u0000\u0000\u008c\u008e\u0003(\u0014\u0000\u008d\u008c\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0011\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0007\u0000\u0000\u0000\u0092\u0013\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0003\u001a\r\u0000\u0094\u0095\u00056\u0000\u0000"+
		"\u0095\u0096\u0005\u0003\u0000\u0000\u0096\u0097\u0003F#\u0000\u0097\u00a1"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0003\u001a\r\u0000\u0099\u009a\u0005"+
		"6\u0000\u0000\u009a\u009c\u0005\u0004\u0000\u0000\u009b\u009d\u0003\u0016"+
		"\u000b\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0005"+
		"\u0000\u0000\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u0093\u0001\u0000"+
		"\u0000\u0000\u00a0\u0098\u0001\u0000\u0000\u0000\u00a1\u0015\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a7\u0003\u0018\f\u0000\u00a3\u00a4\u0005\u0006\u0000"+
		"\u0000\u00a4\u00a6\u0003\u0018\f\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u0017\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a7\u0001\u0000\u0000\u0000\u00aa\u00ab\u0003\u001a\r\u0000\u00ab"+
		"\u00ac\u00056\u0000\u0000\u00ac\u0019\u0001\u0000\u0000\u0000\u00ad\u00ae"+
		"\u0007\u0001\u0000\u0000\u00ae\u001b\u0001\u0000\u0000\u0000\u00af\u00b4"+
		"\u0003>\u001f\u0000\u00b0\u00b1\u0005\u0006\u0000\u0000\u00b1\u00b3\u0003"+
		">\u001f\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b5\u001d\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00bc\u0003F#\u0000\u00b8\u00b9\u0005\u0006\u0000\u0000"+
		"\u00b9\u00bb\u0003F#\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00be"+
		"\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bd\u001f\u0001\u0000\u0000\u0000\u00be\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c4\u00034\u001a\u0000\u00c0\u00c1\u0005"+
		"\u0006\u0000\u0000\u00c1\u00c3\u00034\u001a\u0000\u00c2\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5!\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00cc\u0003,\u0016\u0000"+
		"\u00c8\u00c9\u0005\u0006\u0000\u0000\u00c9\u00cb\u0003B!\u0000\u00ca\u00c8"+
		"\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00d1"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0005\u000b\u0000\u0000\u00d0\u00d2\u0003F#\u0000\u00d1\u00cf\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2#\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d8\u00032\u0019\u0000\u00d4\u00d5\u0005\u0006\u0000\u0000"+
		"\u00d5\u00d7\u0003B!\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00da"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00dd\u0001\u0000\u0000\u0000\u00da\u00d8"+
		"\u0001\u0000\u0000\u0000\u00db\u00dc\u0005\u000b\u0000\u0000\u00dc\u00de"+
		"\u0003F#\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de%\u0001\u0000\u0000\u0000\u00df\u00e4\u00034\u001a\u0000"+
		"\u00e0\u00e1\u0005\u0006\u0000\u0000\u00e1\u00e3\u0003B!\u0000\u00e2\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e9"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e8"+
		"\u0005\u000b\u0000\u0000\u00e8\u00ea\u0003F#\u0000\u00e9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\'\u0001\u0000\u0000"+
		"\u0000\u00eb\u00f0\u0003>\u001f\u0000\u00ec\u00ed\u0005\u0006\u0000\u0000"+
		"\u00ed\u00ef\u0003B!\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f5\u0001\u0000\u0000\u0000\u00f2\u00f0"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\u000b\u0000\u0000\u00f4\u00f6"+
		"\u0003F#\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f6)\u0001\u0000\u0000\u0000\u00f7\u00fc\u0003F#\u0000"+
		"\u00f8\u00f9\u0005\u0006\u0000\u0000\u00f9\u00fb\u0003B!\u0000\u00fa\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u0101"+
		"\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0100"+
		"\u0005\u000b\u0000\u0000\u0100\u0102\u0003F#\u0000\u0101\u00ff\u0001\u0000"+
		"\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102+\u0001\u0000\u0000"+
		"\u0000\u0103\u0106\u0003.\u0017\u0000\u0104\u0106\u00030\u0018\u0000\u0105"+
		"\u0103\u0001\u0000\u0000\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0106"+
		"-\u0001\u0000\u0000\u0000\u0107\u0108\u00034\u001a\u0000\u0108\u0109\u0003"+
		"J%\u0000\u0109\u010a\u0003F#\u0000\u010a/\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0005\f\u0000\u0000\u010c\u010d\u0005\u0004\u0000\u0000\u010d\u010e"+
		"\u0003J%\u0000\u010e\u010f\u00057\u0000\u0000\u010f\u0110\u0005\u0006"+
		"\u0000\u0000\u0110\u0113\u0003.\u0017\u0000\u0111\u0112\u0005\u000b\u0000"+
		"\u0000\u0112\u0114\u0003.\u0017\u0000\u0113\u0111\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000"+
		"\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0005\u0005\u0000\u0000\u0118\u016d\u0001\u0000\u0000\u0000"+
		"\u0119\u011a\u0003.\u0017\u0000\u011a\u011b\u0005\r\u0000\u0000\u011b"+
		"\u011c\u0003.\u0017\u0000\u011c\u016d\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0003>\u001f\u0000\u011e\u011f\u0005\u000e\u0000\u0000\u011f\u0120\u0003"+
		">\u001f\u0000\u0120\u016d\u0001\u0000\u0000\u0000\u0121\u0122\u0003>\u001f"+
		"\u0000\u0122\u0123\u0005\u0003\u0000\u0000\u0123\u0124\u00057\u0000\u0000"+
		"\u0124\u0125\u0005\u000f\u0000\u0000\u0125\u0126\u0003.\u0017\u0000\u0126"+
		"\u016d\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u0004\u0000\u0000\u0128"+
		"\u0129\u0003>\u001f\u0000\u0129\u012a\u0005\u0010\u0000\u0000\u012a\u012b"+
		"\u0003>\u001f\u0000\u012b\u012c\u0005\u0005\u0000\u0000\u012c\u016d\u0001"+
		"\u0000\u0000\u0000\u012d\u012e\u0005\u0011\u0000\u0000\u012e\u012f\u0005"+
		"\u0004\u0000\u0000\u012f\u0130\u0003(\u0014\u0000\u0130\u0131\u0005\u0005"+
		"\u0000\u0000\u0131\u016d\u0001\u0000\u0000\u0000\u0132\u0133\u0005\u0012"+
		"\u0000\u0000\u0133\u0134\u0005\u0004\u0000\u0000\u0134\u0135\u0003(\u0014"+
		"\u0000\u0135\u0136\u0005\u0013\u0000\u0000\u0136\u0137\u0003*\u0015\u0000"+
		"\u0137\u0138\u0005\u0005\u0000\u0000\u0138\u016d\u0001\u0000\u0000\u0000"+
		"\u0139\u013a\u0003>\u001f\u0000\u013a\u013b\u0005\u0014\u0000\u0000\u013b"+
		"\u013c\u0003*\u0015\u0000\u013c\u016d\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0003>\u001f\u0000\u013e\u013f\u0005\u0003\u0000\u0000\u013f\u0140\u0005"+
		"\u0015\u0000\u0000\u0140\u0141\u0005\u0004\u0000\u0000\u0141\u0142\u0003"+
		"(\u0014\u0000\u0142\u0143\u0005\u0005\u0000\u0000\u0143\u016d\u0001\u0000"+
		"\u0000\u0000\u0144\u0145\u0003>\u001f\u0000\u0145\u0146\u0005\u0003\u0000"+
		"\u0000\u0146\u0147\u0005\u0016\u0000\u0000\u0147\u0148\u0005\u0004\u0000"+
		"\u0000\u0148\u0149\u0003(\u0014\u0000\u0149\u014a\u0005\u0005\u0000\u0000"+
		"\u014a\u016d\u0001\u0000\u0000\u0000\u014b\u014c\u0003>\u001f\u0000\u014c"+
		"\u014d\u0005\u0003\u0000\u0000\u014d\u014e\u0005\u0017\u0000\u0000\u014e"+
		"\u014f\u0005\u0004\u0000\u0000\u014f\u0150\u0003\"\u0011\u0000\u0150\u0151"+
		"\u0005\u0005\u0000\u0000\u0151\u016d\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0003>\u001f\u0000\u0153\u0154\u0005\u0003\u0000\u0000\u0154\u0155\u0005"+
		"\u0018\u0000\u0000\u0155\u0156\u0005\u0004\u0000\u0000\u0156\u0157\u0003"+
		"\"\u0011\u0000\u0157\u0158\u0005\u0005\u0000\u0000\u0158\u016d\u0001\u0000"+
		"\u0000\u0000\u0159\u015a\u0003>\u001f\u0000\u015a\u015b\u0005\u0003\u0000"+
		"\u0000\u015b\u015c\u0005\u0019\u0000\u0000\u015c\u015d\u0005\u0004\u0000"+
		"\u0000\u015d\u015e\u0003(\u0014\u0000\u015e\u015f\u0005\u0005\u0000\u0000"+
		"\u015f\u016d\u0001\u0000\u0000\u0000\u0160\u0161\u0003>\u001f\u0000\u0161"+
		"\u0162\u0005\u0003\u0000\u0000\u0162\u0163\u0005\u001a\u0000\u0000\u0163"+
		"\u0164\u0005\u0004\u0000\u0000\u0164\u0165\u0003>\u001f\u0000\u0165\u0166"+
		"\u0005\u0005\u0000\u0000\u0166\u0168\u0005\u001b\u0000\u0000\u0167\u0169"+
		"\u0003D\"\u0000\u0168\u0167\u0001\u0000\u0000\u0000\u0169\u016a\u0001"+
		"\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001"+
		"\u0000\u0000\u0000\u016b\u016d\u0001\u0000\u0000\u0000\u016c\u010b\u0001"+
		"\u0000\u0000\u0000\u016c\u0119\u0001\u0000\u0000\u0000\u016c\u011d\u0001"+
		"\u0000\u0000\u0000\u016c\u0121\u0001\u0000\u0000\u0000\u016c\u0127\u0001"+
		"\u0000\u0000\u0000\u016c\u012d\u0001\u0000\u0000\u0000\u016c\u0132\u0001"+
		"\u0000\u0000\u0000\u016c\u0139\u0001\u0000\u0000\u0000\u016c\u013d\u0001"+
		"\u0000\u0000\u0000\u016c\u0144\u0001\u0000\u0000\u0000\u016c\u014b\u0001"+
		"\u0000\u0000\u0000\u016c\u0152\u0001\u0000\u0000\u0000\u016c\u0159\u0001"+
		"\u0000\u0000\u0000\u016c\u0160\u0001\u0000\u0000\u0000\u016d1\u0001\u0000"+
		"\u0000\u0000\u016e\u016f\u0003>\u001f\u0000\u016f\u0170\u0003J%\u0000"+
		"\u0170\u0171\u0003F#\u0000\u0171\u0179\u0001\u0000\u0000\u0000\u0172\u0173"+
		"\u0003F#\u0000\u0173\u0174\u0005\u001c\u0000\u0000\u0174\u0175\u0003>"+
		"\u001f\u0000\u0175\u0176\u0005\u001c\u0000\u0000\u0176\u0177\u0003F#\u0000"+
		"\u0177\u0179\u0001\u0000\u0000\u0000\u0178\u016e\u0001\u0000\u0000\u0000"+
		"\u0178\u0172\u0001\u0000\u0000\u0000\u01793\u0001\u0000\u0000\u0000\u017a"+
		"\u017e\u00038\u001c\u0000\u017b\u017d\u00036\u001b\u0000\u017c\u017b\u0001"+
		"\u0000\u0000\u0000\u017d\u0180\u0001\u0000\u0000\u0000\u017e\u017c\u0001"+
		"\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f5\u0001\u0000"+
		"\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0181\u0182\u0005\u001d"+
		"\u0000\u0000\u0182\u0186\u00038\u001c\u0000\u0183\u0184\u0005\u001e\u0000"+
		"\u0000\u0184\u0186\u00038\u001c\u0000\u0185\u0181\u0001\u0000\u0000\u0000"+
		"\u0185\u0183\u0001\u0000\u0000\u0000\u01867\u0001\u0000\u0000\u0000\u0187"+
		"\u018e\u0003<\u001e\u0000\u0188\u0189\u0005\u001f\u0000\u0000\u0189\u018a"+
		"\u0005\u0004\u0000\u0000\u018a\u018b\u0003:\u001d\u0000\u018b\u018c\u0005"+
		"\u0005\u0000\u0000\u018c\u018e\u0001\u0000\u0000\u0000\u018d\u0187\u0001"+
		"\u0000\u0000\u0000\u018d\u0188\u0001\u0000\u0000\u0000\u018e9\u0001\u0000"+
		"\u0000\u0000\u018f\u0194\u0003<\u001e\u0000\u0190\u0191\u0005\u0006\u0000"+
		"\u0000\u0191\u0193\u0003B!\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0193"+
		"\u0196\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000\u0000\u0000\u0194"+
		"\u0195\u0001\u0000\u0000\u0000\u0195\u0199\u0001\u0000\u0000\u0000\u0196"+
		"\u0194\u0001\u0000\u0000\u0000\u0197\u0198\u0005\u000b\u0000\u0000\u0198"+
		"\u019a\u0003F#\u0000\u0199\u0197\u0001\u0000\u0000\u0000\u0199\u019a\u0001"+
		"\u0000\u0000\u0000\u019a;\u0001\u0000\u0000\u0000\u019b\u019d\u0003F#"+
		"\u0000\u019c\u019b\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000"+
		"\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e\u019f\u0003>\u001f\u0000"+
		"\u019f=\u0001\u0000\u0000\u0000\u01a0\u01a1\u00056\u0000\u0000\u01a1\u01a3"+
		"\u0005 \u0000\u0000\u01a2\u01a4\u0003@ \u0000\u01a3\u01a2\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01a5\u0001\u0000"+
		"\u0000\u0000\u01a5\u01a6\u0005!\u0000\u0000\u01a6?\u0001\u0000\u0000\u0000"+
		"\u01a7\u01ac\u0003F#\u0000\u01a8\u01a9\u0005\u0006\u0000\u0000\u01a9\u01ab"+
		"\u0003F#\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000\u01ab\u01ae\u0001\u0000"+
		"\u0000\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000"+
		"\u0000\u0000\u01adA\u0001\u0000\u0000\u0000\u01ae\u01ac\u0001\u0000\u0000"+
		"\u0000\u01af\u01b0\u00056\u0000\u0000\u01b0\u01b1\u0005\u0014\u0000\u0000"+
		"\u01b1\u01b2\u0003F#\u0000\u01b2\u01b3\u0005\"\u0000\u0000\u01b3\u01b4"+
		"\u0003F#\u0000\u01b4C\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005\u0004"+
		"\u0000\u0000\u01b6\u01b7\u00057\u0000\u0000\u01b7\u01b8\u0005\u0006\u0000"+
		"\u0000\u01b8\u01b9\u00057\u0000\u0000\u01b9\u01ba\u0005\u0005\u0000\u0000"+
		"\u01baE\u0001\u0000\u0000\u0000\u01bb\u01bc\u0006#\uffff\uffff\u0000\u01bc"+
		"\u01bd\u0007\u0002\u0000\u0000\u01bd\u01cb\u0003F#\b\u01be\u01bf\u0005"+
		",\u0000\u0000\u01bf\u01cb\u0003F#\u0007\u01c0\u01c1\u0005-\u0000\u0000"+
		"\u01c1\u01cb\u0003F#\u0006\u01c2\u01c3\u0005\u0004\u0000\u0000\u01c3\u01c4"+
		"\u0003F#\u0000\u01c4\u01c5\u0005\u0005\u0000\u0000\u01c5\u01cb\u0001\u0000"+
		"\u0000\u0000\u01c6\u01cb\u0003H$\u0000\u01c7\u01cb\u00056\u0000\u0000"+
		"\u01c8\u01cb\u00058\u0000\u0000\u01c9\u01cb\u00057\u0000\u0000\u01ca\u01bb"+
		"\u0001\u0000\u0000\u0000\u01ca\u01be\u0001\u0000\u0000\u0000\u01ca\u01c0"+
		"\u0001\u0000\u0000\u0000\u01ca\u01c2\u0001\u0000\u0000\u0000\u01ca\u01c6"+
		"\u0001\u0000\u0000\u0000\u01ca\u01c7\u0001\u0000\u0000\u0000\u01ca\u01c8"+
		"\u0001\u0000\u0000\u0000\u01ca\u01c9\u0001\u0000\u0000\u0000\u01cb\u01dd"+
		"\u0001\u0000\u0000\u0000\u01cc\u01cd\n\r\u0000\u0000\u01cd\u01ce\u0007"+
		"\u0003\u0000\u0000\u01ce\u01dc\u0003F#\u000e\u01cf\u01d0\n\f\u0000\u0000"+
		"\u01d0\u01d1\u0007\u0004\u0000\u0000\u01d1\u01dc\u0003F#\r\u01d2\u01d3"+
		"\n\u000b\u0000\u0000\u01d3\u01d4\u0007\u0005\u0000\u0000\u01d4\u01dc\u0003"+
		"F#\f\u01d5\u01d6\n\n\u0000\u0000\u01d6\u01d7\u0007\u0006\u0000\u0000\u01d7"+
		"\u01dc\u0003F#\u000b\u01d8\u01d9\n\t\u0000\u0000\u01d9\u01da\u0007\u0007"+
		"\u0000\u0000\u01da\u01dc\u0003F#\n\u01db\u01cc\u0001\u0000\u0000\u0000"+
		"\u01db\u01cf\u0001\u0000\u0000\u0000\u01db\u01d2\u0001\u0000\u0000\u0000"+
		"\u01db\u01d5\u0001\u0000\u0000\u0000\u01db\u01d8\u0001\u0000\u0000\u0000"+
		"\u01dc\u01df\u0001\u0000\u0000\u0000\u01dd\u01db\u0001\u0000\u0000\u0000"+
		"\u01dd\u01de\u0001\u0000\u0000\u0000\u01deG\u0001\u0000\u0000\u0000\u01df"+
		"\u01dd\u0001\u0000\u0000\u0000\u01e0\u01e1\u00056\u0000\u0000\u01e1\u01e3"+
		"\u0005\u0004\u0000\u0000\u01e2\u01e4\u0003\u001e\u000f\u0000\u01e3\u01e2"+
		"\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u01e5"+
		"\u0001\u0000\u0000\u0000\u01e5\u01e6\u0005\u0005\u0000\u0000\u01e6I\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e8\u0007\b\u0000\u0000\u01e8K\u0001\u0000"+
		"\u0000\u0000.MRUX[^fqw}\u0083\u0089\u008f\u009c\u00a0\u00a7\u00b4\u00bc"+
		"\u00c4\u00cc\u00d1\u00d8\u00dd\u00e4\u00e9\u00f0\u00f5\u00fc\u0101\u0105"+
		"\u0115\u016a\u016c\u0178\u017e\u0185\u018d\u0194\u0199\u019c\u01a3\u01ac"+
		"\u01ca\u01db\u01dd\u01e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}