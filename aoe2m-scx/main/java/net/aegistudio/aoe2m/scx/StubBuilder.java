package net.aegistudio.aoe2m.scx;

import java.io.EOFException;
import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Wrapper;

public class StubBuilder {
	public void buildStub(Translator translator) throws IOException, CorruptionException {
		int accum = 0;
		byte[] buffer = new byte[16];
		
		try {
			while(true) {
				if(accum % 16 == 0) {
					String string = "00000000" + Integer.toHexString(accum);
					System.out.print(string.substring(string.length() - 8));
					System.out.print(": ");
				}
				
				Wrapper<Byte> value = new Container<>((byte) 0);
				translator.signed8(value);
				String string = "0" + Integer.toHexString(value.getValue());
				System.out.print(string.substring(string.length() - 2));
				System.out.print(" ");
				
				buffer[accum % 16] = value.getValue();
				accum ++;
				if(accum % 256 == 0) try { Thread.sleep(1000L); } catch(Exception e) {}
				if(accum % 16 == 0) {
					System.out.print("   ");
					for(int i = 0; i < buffer.length; i ++) {
						char current = (char) buffer[i];
						if(current >= ' ' && current < 0x80)
							System.out.print(current);
						else System.out.print('.');
					}
					System.out.println();
				}
				//else if(accum % 8 == 0) System.out.print(" ");
			}
		}
		catch(EOFException exception) {
		}
		System.out.println();
	}
}
