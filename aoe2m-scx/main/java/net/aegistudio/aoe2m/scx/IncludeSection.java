package net.aegistudio.aoe2m.scx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.uio.wrap.*;
import net.aegistudio.uio.*;

public class IncludeSection {
	public static class IncludedFile {
		public final TextContainer name = new TextContainer();
		public final TextContainer content = new TextContainer();
		
		public void buildIncludedFile(Translator translator) throws IOException {
			translator.string32(name.stringWrapper());
			translator.string32(content.stringWrapper());
		}
		
		public static void build(IncludedFile file, Translator translator) throws IOException {
			file.buildIncludedFile(translator);
		}
	}
	
	public final List<IncludedFile> includedFiles = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptException {
		BooleanContainer fileIncluded = new BooleanContainer(includedFiles.size() > 0);
		translator.signed32(fileIncluded.bool32());
		translator.constInteger(0);
		
		if(fileIncluded.get()) {
			Wrapper<Integer> size = new Container<>(includedFiles.size());
			translator.signed32(size);
			translator.array(size.get(), includedFiles, 
					IncludedFile::new, IncludedFile::build);
		}
	}
}
