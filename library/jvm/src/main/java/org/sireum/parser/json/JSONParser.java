// $ANTLR 3.5.3 /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g 2025-03-16 09:43:36
 package org.sireum.parser.json; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class JSONParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ESC", "EXP", "HEX", "INT", "INTEGER", 
		"NUMBER", "STRING", "WS", "','", "':'", "'['", "']'", "'false'", "'null'", 
		"'true'", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int ESC=4;
	public static final int EXP=5;
	public static final int HEX=6;
	public static final int INT=7;
	public static final int INTEGER=8;
	public static final int NUMBER=9;
	public static final int STRING=10;
	public static final int WS=11;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public JSONParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public JSONParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return JSONParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g"; }


	public static class valueFile_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "valueFile"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:16:1: valueFile : value EOF ;
	public final JSONParser.valueFile_return valueFile() throws RecognitionException {
		JSONParser.valueFile_return retval = new JSONParser.valueFile_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF2=null;
		ParserRuleReturnScope value1 =null;

		Object EOF2_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:16:10: ( value EOF )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:16:12: value EOF
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_value_in_valueFile50);
			value1=value();
			state._fsp--;

			adaptor.addChild(root_0, value1.getTree());

			EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_valueFile52); 
			EOF2_tree = (Object)adaptor.create(EOF2);
			adaptor.addChild(root_0, EOF2_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "valueFile"


	public static class value_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "value"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:1: value : ( stringLit | doubleLit | intLit | object | array | boolLit | nullLit );
	public final JSONParser.value_return value() throws RecognitionException {
		JSONParser.value_return retval = new JSONParser.value_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope stringLit3 =null;
		ParserRuleReturnScope doubleLit4 =null;
		ParserRuleReturnScope intLit5 =null;
		ParserRuleReturnScope object6 =null;
		ParserRuleReturnScope array7 =null;
		ParserRuleReturnScope boolLit8 =null;
		ParserRuleReturnScope nullLit9 =null;


		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:6: ( stringLit | doubleLit | intLit | object | array | boolLit | nullLit )
			int alt1=7;
			switch ( input.LA(1) ) {
			case STRING:
				{
				alt1=1;
				}
				break;
			case NUMBER:
				{
				alt1=2;
				}
				break;
			case INTEGER:
				{
				alt1=3;
				}
				break;
			case 19:
				{
				alt1=4;
				}
				break;
			case 14:
				{
				alt1=5;
				}
				break;
			case 16:
			case 18:
				{
				alt1=6;
				}
				break;
			case 17:
				{
				alt1=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:8: stringLit
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_stringLit_in_value60);
					stringLit3=stringLit();
					state._fsp--;

					adaptor.addChild(root_0, stringLit3.getTree());

					}
					break;
				case 2 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:20: doubleLit
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_doubleLit_in_value64);
					doubleLit4=doubleLit();
					state._fsp--;

					adaptor.addChild(root_0, doubleLit4.getTree());

					}
					break;
				case 3 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:32: intLit
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_intLit_in_value68);
					intLit5=intLit();
					state._fsp--;

					adaptor.addChild(root_0, intLit5.getTree());

					}
					break;
				case 4 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:41: object
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_object_in_value72);
					object6=object();
					state._fsp--;

					adaptor.addChild(root_0, object6.getTree());

					}
					break;
				case 5 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:50: array
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_array_in_value76);
					array7=array();
					state._fsp--;

					adaptor.addChild(root_0, array7.getTree());

					}
					break;
				case 6 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:58: boolLit
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_boolLit_in_value80);
					boolLit8=boolLit();
					state._fsp--;

					adaptor.addChild(root_0, boolLit8.getTree());

					}
					break;
				case 7 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:18:68: nullLit
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_nullLit_in_value84);
					nullLit9=nullLit();
					state._fsp--;

					adaptor.addChild(root_0, nullLit9.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "value"


	public static class object_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "object"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:1: object : '{' ( keyValue ( ',' keyValue )* )? '}' ;
	public final JSONParser.object_return object() throws RecognitionException {
		JSONParser.object_return retval = new JSONParser.object_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal10=null;
		Token char_literal12=null;
		Token char_literal14=null;
		ParserRuleReturnScope keyValue11 =null;
		ParserRuleReturnScope keyValue13 =null;

		Object char_literal10_tree=null;
		Object char_literal12_tree=null;
		Object char_literal14_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:7: ( '{' ( keyValue ( ',' keyValue )* )? '}' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:9: '{' ( keyValue ( ',' keyValue )* )? '}'
			{
			root_0 = (Object)adaptor.nil();


			char_literal10=(Token)match(input,19,FOLLOW_19_in_object92); 
			char_literal10_tree = (Object)adaptor.create(char_literal10);
			adaptor.addChild(root_0, char_literal10_tree);

			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:13: ( keyValue ( ',' keyValue )* )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==STRING) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:15: keyValue ( ',' keyValue )*
					{
					pushFollow(FOLLOW_keyValue_in_object96);
					keyValue11=keyValue();
					state._fsp--;

					adaptor.addChild(root_0, keyValue11.getTree());

					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:24: ( ',' keyValue )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( (LA2_0==12) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:20:26: ',' keyValue
							{
							char_literal12=(Token)match(input,12,FOLLOW_12_in_object100); 
							char_literal12_tree = (Object)adaptor.create(char_literal12);
							adaptor.addChild(root_0, char_literal12_tree);

							pushFollow(FOLLOW_keyValue_in_object102);
							keyValue13=keyValue();
							state._fsp--;

							adaptor.addChild(root_0, keyValue13.getTree());

							}
							break;

						default :
							break loop2;
						}
					}

					}
					break;

			}

			char_literal14=(Token)match(input,20,FOLLOW_20_in_object110); 
			char_literal14_tree = (Object)adaptor.create(char_literal14);
			adaptor.addChild(root_0, char_literal14_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "object"


	public static class keyValue_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "keyValue"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:22:1: keyValue : STRING ':' value ;
	public final JSONParser.keyValue_return keyValue() throws RecognitionException {
		JSONParser.keyValue_return retval = new JSONParser.keyValue_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING15=null;
		Token char_literal16=null;
		ParserRuleReturnScope value17 =null;

		Object STRING15_tree=null;
		Object char_literal16_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:22:9: ( STRING ':' value )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:22:11: STRING ':' value
			{
			root_0 = (Object)adaptor.nil();


			STRING15=(Token)match(input,STRING,FOLLOW_STRING_in_keyValue118); 
			STRING15_tree = (Object)adaptor.create(STRING15);
			adaptor.addChild(root_0, STRING15_tree);

			char_literal16=(Token)match(input,13,FOLLOW_13_in_keyValue120); 
			char_literal16_tree = (Object)adaptor.create(char_literal16);
			adaptor.addChild(root_0, char_literal16_tree);

			pushFollow(FOLLOW_value_in_keyValue122);
			value17=value();
			state._fsp--;

			adaptor.addChild(root_0, value17.getTree());

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "keyValue"


	public static class array_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "array"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:1: array : '[' ( value ( ',' value )* )? ']' ;
	public final JSONParser.array_return array() throws RecognitionException {
		JSONParser.array_return retval = new JSONParser.array_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal18=null;
		Token char_literal20=null;
		Token char_literal22=null;
		ParserRuleReturnScope value19 =null;
		ParserRuleReturnScope value21 =null;

		Object char_literal18_tree=null;
		Object char_literal20_tree=null;
		Object char_literal22_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:6: ( '[' ( value ( ',' value )* )? ']' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:8: '[' ( value ( ',' value )* )? ']'
			{
			root_0 = (Object)adaptor.nil();


			char_literal18=(Token)match(input,14,FOLLOW_14_in_array130); 
			char_literal18_tree = (Object)adaptor.create(char_literal18);
			adaptor.addChild(root_0, char_literal18_tree);

			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:12: ( value ( ',' value )* )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( ((LA5_0 >= INTEGER && LA5_0 <= STRING)||LA5_0==14||(LA5_0 >= 16 && LA5_0 <= 19)) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:14: value ( ',' value )*
					{
					pushFollow(FOLLOW_value_in_array134);
					value19=value();
					state._fsp--;

					adaptor.addChild(root_0, value19.getTree());

					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:20: ( ',' value )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( (LA4_0==12) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:24:21: ',' value
							{
							char_literal20=(Token)match(input,12,FOLLOW_12_in_array137); 
							char_literal20_tree = (Object)adaptor.create(char_literal20);
							adaptor.addChild(root_0, char_literal20_tree);

							pushFollow(FOLLOW_value_in_array139);
							value21=value();
							state._fsp--;

							adaptor.addChild(root_0, value21.getTree());

							}
							break;

						default :
							break loop4;
						}
					}

					}
					break;

			}

			char_literal22=(Token)match(input,15,FOLLOW_15_in_array146); 
			char_literal22_tree = (Object)adaptor.create(char_literal22);
			adaptor.addChild(root_0, char_literal22_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "array"


	public static class stringLit_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "stringLit"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:26:1: stringLit : STRING ;
	public final JSONParser.stringLit_return stringLit() throws RecognitionException {
		JSONParser.stringLit_return retval = new JSONParser.stringLit_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING23=null;

		Object STRING23_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:26:10: ( STRING )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:26:12: STRING
			{
			root_0 = (Object)adaptor.nil();


			STRING23=(Token)match(input,STRING,FOLLOW_STRING_in_stringLit154); 
			STRING23_tree = (Object)adaptor.create(STRING23);
			adaptor.addChild(root_0, STRING23_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stringLit"


	public static class doubleLit_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "doubleLit"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:28:1: doubleLit : NUMBER ;
	public final JSONParser.doubleLit_return doubleLit() throws RecognitionException {
		JSONParser.doubleLit_return retval = new JSONParser.doubleLit_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUMBER24=null;

		Object NUMBER24_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:28:10: ( NUMBER )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:28:12: NUMBER
			{
			root_0 = (Object)adaptor.nil();


			NUMBER24=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_doubleLit162); 
			NUMBER24_tree = (Object)adaptor.create(NUMBER24);
			adaptor.addChild(root_0, NUMBER24_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "doubleLit"


	public static class intLit_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "intLit"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:30:1: intLit : INTEGER ;
	public final JSONParser.intLit_return intLit() throws RecognitionException {
		JSONParser.intLit_return retval = new JSONParser.intLit_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token INTEGER25=null;

		Object INTEGER25_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:30:7: ( INTEGER )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:30:9: INTEGER
			{
			root_0 = (Object)adaptor.nil();


			INTEGER25=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_intLit171); 
			INTEGER25_tree = (Object)adaptor.create(INTEGER25);
			adaptor.addChild(root_0, INTEGER25_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "intLit"


	public static class boolLit_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "boolLit"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:32:1: boolLit : ( 'true' | 'false' );
	public final JSONParser.boolLit_return boolLit() throws RecognitionException {
		JSONParser.boolLit_return retval = new JSONParser.boolLit_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set26=null;

		Object set26_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:32:8: ( 'true' | 'false' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
			{
			root_0 = (Object)adaptor.nil();


			set26=input.LT(1);
			if ( input.LA(1)==16||input.LA(1)==18 ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set26));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "boolLit"


	public static class nullLit_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "nullLit"
	// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:34:1: nullLit : 'null' ;
	public final JSONParser.nullLit_return nullLit() throws RecognitionException {
		JSONParser.nullLit_return retval = new JSONParser.nullLit_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal27=null;

		Object string_literal27_tree=null;

		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:34:8: ( 'null' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:34:10: 'null'
			{
			root_0 = (Object)adaptor.nil();


			string_literal27=(Token)match(input,17,FOLLOW_17_in_nullLit191); 
			string_literal27_tree = (Object)adaptor.create(string_literal27);
			adaptor.addChild(root_0, string_literal27_tree);

			}

			retval.stop = input.LT(-1);

			adaptor.setTokenBoundaries(root_0, retval.start, retval.stop);
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nullLit"

	// Delegated rules



	public static final BitSet FOLLOW_value_in_valueFile50 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_valueFile52 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_stringLit_in_value60 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_doubleLit_in_value64 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_intLit_in_value68 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_in_value72 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_in_value76 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolLit_in_value80 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nullLit_in_value84 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_object92 = new BitSet(new long[]{0x0000000000100400L});
	public static final BitSet FOLLOW_keyValue_in_object96 = new BitSet(new long[]{0x0000000000101000L});
	public static final BitSet FOLLOW_12_in_object100 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_keyValue_in_object102 = new BitSet(new long[]{0x0000000000101000L});
	public static final BitSet FOLLOW_20_in_object110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_keyValue118 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_keyValue120 = new BitSet(new long[]{0x00000000000F4700L});
	public static final BitSet FOLLOW_value_in_keyValue122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_array130 = new BitSet(new long[]{0x00000000000FC700L});
	public static final BitSet FOLLOW_value_in_array134 = new BitSet(new long[]{0x0000000000009000L});
	public static final BitSet FOLLOW_12_in_array137 = new BitSet(new long[]{0x00000000000F4700L});
	public static final BitSet FOLLOW_value_in_array139 = new BitSet(new long[]{0x0000000000009000L});
	public static final BitSet FOLLOW_15_in_array146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_stringLit154 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_doubleLit162 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_intLit171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_nullLit191 = new BitSet(new long[]{0x0000000000000002L});
}
