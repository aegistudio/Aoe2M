package net.aegistudio.aoe2m.scx;

import java.io.EOFException;
import java.io.IOException;

public class StubBuilder {
	public void buildStub(FieldTranslator translator) throws IOException, CorruptionException {
		try {
			while(true) {
				Wrapper<Byte> value = new Wrapper<>((byte) 0);
				translator.signed8(value);
				String string = "0" + Integer.toHexString(value.getValue());
				System.out.print(string.substring(string.length() - 2));
				System.out.print(" ");
			}
		}
		catch(EOFException exception) {
		}
		System.out.println();
	}
}
