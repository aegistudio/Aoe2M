package net.aegistudio.aoe2m.slp.cmd;

import java.io.IOException;

import net.aegistudio.uio.Translator;
import net.aegistudio.uio.Wrapper;
import net.aegistudio.uio.wrap.Container;

public enum EnumPixelCount {
	NEXT {
		@Override
		public int pixelCount(byte opcode, Translator translator, int count) throws IOException {
			Wrapper<Byte> next = new Container<Byte>((byte) count);
			translator.signed8(next);
			count = next.get();
			return count < 0? count + 256 : count;
		}
	},
	SIGNIFICANT2 {
		@Override
		public int pixelCount(byte opcode, Translator translator, int count) {
			return ((opcode & 0x00ff) >>> 2);
		}
	},
	SIGNIFICANT4 {
		@Override
		public int pixelCount(byte opcode, Translator translator, int count) {
			return ((opcode & 0x00ff) >>> 4);
		}
	},
	BASE_NEXT_OFFSET {
		@Override
		public int pixelCount(byte opcode, Translator translator, int count) throws IOException {
			int nextCount = NEXT.pixelCount(opcode, translator, count);
			return ((opcode & 0x00f0) << 4) | nextCount;
		}
	};
	
	public abstract int pixelCount(byte opcode, Translator translator, int count) throws IOException;
}
