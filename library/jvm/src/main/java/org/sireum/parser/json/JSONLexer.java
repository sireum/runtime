// $ANTLR 3.5.3 /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g 2025-03-16 09:43:36
 package org.sireum.parser.json; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class JSONLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public JSONLexer() {} 
	public JSONLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public JSONLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g"; }

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:4:7: ( ',' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:4:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:5:7: ( ':' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:5:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:6:7: ( '[' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:6:9: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:7:7: ( ']' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:7:9: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:8:7: ( 'false' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:8:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:9:7: ( 'null' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:9:9: 'null'
			{
			match("null"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:10:7: ( 'true' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:10:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:11:7: ( '{' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:11:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:12:7: ( '}' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:12:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:36:7: ( '\"' ( ESC |~ ( '\"' | '\\\\' | '\\u0000' .. '\\u001F' ) )* '\"' )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:36:9: '\"' ( ESC |~ ( '\"' | '\\\\' | '\\u0000' .. '\\u001F' ) )* '\"'
			{
			match('\"'); 
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:36:13: ( ESC |~ ( '\"' | '\\\\' | '\\u0000' .. '\\u001F' ) )*
			loop1:
			while (true) {
				int alt1=3;
				int LA1_0 = input.LA(1);
				if ( (LA1_0=='\\') ) {
					alt1=1;
				}
				else if ( ((LA1_0 >= ' ' && LA1_0 <= '!')||(LA1_0 >= '#' && LA1_0 <= '[')||(LA1_0 >= ']' && LA1_0 <= '\uFFFF')) ) {
					alt1=2;
				}

				switch (alt1) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:36:15: ESC
					{
					mESC(); 

					}
					break;
				case 2 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:36:21: ~ ( '\"' | '\\\\' | '\\u0000' .. '\\u001F' )
					{
					if ( (input.LA(1) >= ' ' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:38:8: ( ( '-' )? INT )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:38:10: ( '-' )? INT
			{
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:38:10: ( '-' )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0=='-') ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:38:10: '-'
					{
					match('-'); 
					}
					break;

			}

			mINT(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:7: ( ( '-' )? INT ( '.' ( '0' .. '9' )+ )? ( EXP )? )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:9: ( '-' )? INT ( '.' ( '0' .. '9' )+ )? ( EXP )?
			{
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:9: ( '-' )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='-') ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:9: '-'
					{
					match('-'); 
					}
					break;

			}

			mINT(); 

			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:18: ( '.' ( '0' .. '9' )+ )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='.') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:20: '.' ( '0' .. '9' )+
					{
					match('.'); 
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:24: ( '0' .. '9' )+
					int cnt4=0;
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt4 >= 1 ) break loop4;
							EarlyExitException eee = new EarlyExitException(4, input);
							throw eee;
						}
						cnt4++;
					}

					}
					break;

			}

			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:43: ( EXP )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='E'||LA6_0=='e') ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:40:43: EXP
					{
					mEXP(); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "ESC"
	public final void mESC() throws RecognitionException {
		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:13: ( '\\\\' ( '\"' | '\\\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' | 'u' HEX HEX HEX HEX ) )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:15: '\\\\' ( '\"' | '\\\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' | 'u' HEX HEX HEX HEX )
			{
			match('\\'); 
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:20: ( '\"' | '\\\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' | 'u' HEX HEX HEX HEX )
			int alt7=9;
			switch ( input.LA(1) ) {
			case '\"':
				{
				alt7=1;
				}
				break;
			case '\\':
				{
				alt7=2;
				}
				break;
			case '/':
				{
				alt7=3;
				}
				break;
			case 'b':
				{
				alt7=4;
				}
				break;
			case 'f':
				{
				alt7=5;
				}
				break;
			case 'n':
				{
				alt7=6;
				}
				break;
			case 'r':
				{
				alt7=7;
				}
				break;
			case 't':
				{
				alt7=8;
				}
				break;
			case 'u':
				{
				alt7=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:22: '\"'
					{
					match('\"'); 
					}
					break;
				case 2 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:28: '\\\\'
					{
					match('\\'); 
					}
					break;
				case 3 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:35: '/'
					{
					match('/'); 
					}
					break;
				case 4 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:41: 'b'
					{
					match('b'); 
					}
					break;
				case 5 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:47: 'f'
					{
					match('f'); 
					}
					break;
				case 6 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:53: 'n'
					{
					match('n'); 
					}
					break;
				case 7 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:59: 'r'
					{
					match('r'); 
					}
					break;
				case 8 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:65: 't'
					{
					match('t'); 
					}
					break;
				case 9 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:42:71: 'u' HEX HEX HEX HEX
					{
					match('u'); 
					mHEX(); 

					mHEX(); 

					mHEX(); 

					mHEX(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESC"

	// $ANTLR start "HEX"
	public final void mHEX() throws RecognitionException {
		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:44:13: ( ( '0' .. '9' ) | ( 'a' .. 'f' ) | ( 'A' .. 'F' ) )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HEX"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:46:13: ( '0' | ( '1' .. '9' ) ( '0' .. '9' )* )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='0') ) {
				alt9=1;
			}
			else if ( ((LA9_0 >= '1' && LA9_0 <= '9')) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:46:15: '0'
					{
					match('0'); 
					}
					break;
				case 2 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:46:21: ( '1' .. '9' ) ( '0' .. '9' )*
					{
					if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:46:36: ( '0' .. '9' )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop8;
						}
					}

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "EXP"
	public final void mEXP() throws RecognitionException {
		try {
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:48:13: ( ( 'E' | 'e' ) ( '+' | '-' )? INT )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:48:15: ( 'E' | 'e' ) ( '+' | '-' )? INT
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:48:29: ( '+' | '-' )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='+'||LA10_0=='-') ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			mINT(); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXP"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:50:3: ( ( ' ' | '\\n' | '\\r' | '\\t' )+ )
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:50:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
			{
			// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:50:5: ( ' ' | '\\n' | '\\r' | '\\t' )+
			int cnt11=0;
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\t' && LA11_0 <= '\n')||LA11_0=='\r'||LA11_0==' ') ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt11 >= 1 ) break loop11;
					EarlyExitException eee = new EarlyExitException(11, input);
					throw eee;
				}
				cnt11++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | STRING | INTEGER | NUMBER | WS )
		int alt12=13;
		alt12 = dfa12.predict(input);
		switch (alt12) {
			case 1 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:10: T__12
				{
				mT__12(); 

				}
				break;
			case 2 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:16: T__13
				{
				mT__13(); 

				}
				break;
			case 3 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:22: T__14
				{
				mT__14(); 

				}
				break;
			case 4 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:28: T__15
				{
				mT__15(); 

				}
				break;
			case 5 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:34: T__16
				{
				mT__16(); 

				}
				break;
			case 6 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:40: T__17
				{
				mT__17(); 

				}
				break;
			case 7 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:46: T__18
				{
				mT__18(); 

				}
				break;
			case 8 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:52: T__19
				{
				mT__19(); 

				}
				break;
			case 9 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:58: T__20
				{
				mT__20(); 

				}
				break;
			case 10 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:64: STRING
				{
				mSTRING(); 

				}
				break;
			case 11 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:71: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 12 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:79: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 13 :
				// /Users/robby/Repositories/Sireum/kekinian/runtime/library/shared/src/main/resources/JSON.g:1:86: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\14\uffff\2\17\3\uffff\1\17";
	static final String DFA12_eofS =
		"\22\uffff";
	static final String DFA12_minS =
		"\1\11\12\uffff\1\60\2\56\3\uffff\1\56";
	static final String DFA12_maxS =
		"\1\175\12\uffff\1\71\2\145\3\uffff\1\145";
	static final String DFA12_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\3\uffff\1\15\1\13"+
		"\1\14\1\uffff";
	static final String DFA12_specialS =
		"\22\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\16\2\uffff\1\16\22\uffff\1\16\1\uffff\1\12\11\uffff\1\1\1\13\2\uffff"+
			"\1\14\11\15\1\2\40\uffff\1\3\1\uffff\1\4\10\uffff\1\5\7\uffff\1\6\5\uffff"+
			"\1\7\6\uffff\1\10\1\uffff\1\11",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\14\11\15",
			"\1\20\26\uffff\1\20\37\uffff\1\20",
			"\1\20\1\uffff\12\21\13\uffff\1\20\37\uffff\1\20",
			"",
			"",
			"",
			"\1\20\1\uffff\12\21\13\uffff\1\20\37\uffff\1\20"
	};

	static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
	static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
	static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
	static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
	static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
	static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
	static final short[][] DFA12_transition;

	static {
		int numStates = DFA12_transitionS.length;
		DFA12_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
		}
	}

	protected class DFA12 extends DFA {

		public DFA12(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 12;
			this.eot = DFA12_eot;
			this.eof = DFA12_eof;
			this.min = DFA12_min;
			this.max = DFA12_max;
			this.accept = DFA12_accept;
			this.special = DFA12_special;
			this.transition = DFA12_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | STRING | INTEGER | NUMBER | WS );";
		}
	}

}
