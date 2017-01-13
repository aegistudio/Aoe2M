package net.aegistudio.aoe2m.io;

import java.io.IOException;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;

public class StackDebugTranslator extends DebugTranslator {

	public StackDebugTranslator(Debuggable debug, FieldTranslator translator) {
		super(debug, translator);
	}
	
	private String getCaller() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement target = elements[4];
		return "(" + target.getFileName() + ":" + target.getLineNumber() + ") :: " + target.getMethodName();
	}
	
	protected void debug(String type, IOECallable next) throws IOException {
		super.debug(getCaller() + " => " + type, next);
	}
	
	protected void constDebug(String type, IOECCallable next) throws IOException, CorruptionException {
		super.constDebug(getCaller() + " => " + type, next);
	}
}
