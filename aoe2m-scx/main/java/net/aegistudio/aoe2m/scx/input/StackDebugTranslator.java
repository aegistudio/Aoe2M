package net.aegistudio.aoe2m.scx.input;

import java.io.IOException;

import net.aegistudio.aoe2m.scx.CorruptionException;

public class StackDebugTranslator extends DebugInputTranslator {

	public StackDebugTranslator(DebugInputStream debug, String charset) {
		super(debug, charset);
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
