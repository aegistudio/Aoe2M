package net.aegistudio.aoe2m.slp;

import net.aegistudio.aoe2m.slp.cmd.BlockCopy;
import net.aegistudio.aoe2m.slp.cmd.EndLine;
import net.aegistudio.aoe2m.slp.cmd.EnumPixelCount;
import net.aegistudio.aoe2m.slp.cmd.Fill;
import net.aegistudio.aoe2m.slp.cmd.Once;
import net.aegistudio.aoe2m.slp.cmd.Repeat;

public class DefaultCommandSet implements CommandSet {

	@Override
	public Command parse(byte opcode) {
		int command = opcode & 0x000f;
		int significant = opcode & 0x00f0;
		
		switch(command) {
			// Lesser block copy:
			case 0x00:	case 0x04:
			case 0x08:	case 0x0c:
				return new BlockCopy(opcode, 
						EnumPixelCount.SIGNIFICANT2, ImagePrinter::normal);
			
			// Lesser skip:
			case 0x01:
				return new Repeat(opcode, significant == 0?
						EnumPixelCount.NEXT : EnumPixelCount.SIGNIFICANT2, ImagePrinter::transparent);
			
			// Greater block copy:
			case 0x02:
				return new BlockCopy(opcode,
						EnumPixelCount.BASE_NEXT_OFFSET, ImagePrinter::normal);
			
			// Greater skip:
			case 0x03:
				return new Repeat(opcode,
						EnumPixelCount.BASE_NEXT_OFFSET, ImagePrinter::transparent);
			
			// Lesser skip:
			case 0x05:	case 0x09:	case 0x0d:
				return new Repeat(opcode,
						EnumPixelCount.SIGNIFICANT2, ImagePrinter::transparent);
			
			// Player color copy:
			case 0x06:
				return new BlockCopy(opcode, significant == 0?
						EnumPixelCount.NEXT : EnumPixelCount.SIGNIFICANT4, ImagePrinter::player);
			
			// Fill:
			case 0x07:
				return new Fill(opcode, significant == 0?
						EnumPixelCount.NEXT : EnumPixelCount.SIGNIFICANT4, ImagePrinter::normal);
			
			// Fill player color:
			case 0x0a:
				return new Fill(opcode, significant == 0?
						EnumPixelCount.NEXT : EnumPixelCount.SIGNIFICANT4, ImagePrinter::player);
			
			// Shadow.
			case 0x0b:
				return new Repeat(opcode, significant == 0?
						EnumPixelCount.NEXT : EnumPixelCount.SIGNIFICANT4, ImagePrinter::shadow);
			
			// Extended:
			case 0x0e:
				switch(significant >>> 4) {
					case 0:	case 1:	case 2:	case 3:
						return new Once(opcode, p -> {});
				
					case 4:
						return new Once(opcode, ImagePrinter::obstruct1);
						
					case 5:
						return new Repeat(opcode, 
								EnumPixelCount.NEXT, ImagePrinter::obstruct1);
						
					case 6:
						return new Once(opcode, ImagePrinter::obstruct2);
						
					case 7:
						return new Repeat(opcode, 
								EnumPixelCount.NEXT, ImagePrinter::obstruct2);
				}
			
				
			// End of line.
			case 0x0f:	default: 
				return new EndLine(opcode);
		}
	}

}
