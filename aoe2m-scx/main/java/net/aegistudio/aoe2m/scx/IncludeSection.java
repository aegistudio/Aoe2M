package net.aegistudio.aoe2m.scx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.Translator;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.TranslateWrapper;
import net.aegistudio.aoe2m.Wrapper;

public class IncludeSection {
	public static class IncludedFile {
		public final Wrapper<Text> name = new Container<>(new Text());
		public final Wrapper<Text> content = new Container<>(new Text());
		
		public void buildIncludedFile(Translator translator) throws IOException {
			translator.string32(name);
			translator.string32(content);
		}
		
		public static void build(IncludedFile file, Translator translator) throws IOException {
			file.buildIncludedFile(translator);
		}
	}
	
	public final List<IncludedFile> includedFiles = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void build(Translator translator) throws IOException, CorruptionException {
		Wrapper<Boolean> fileIncluded = new Container<>(includedFiles.size() > 0);
		translator.bool32(fileIncluded);
		translator.constInteger(0);
		
		if(fileIncluded.getValue()) {
			Wrapper<Integer> size = new Container<>(includedFiles.size());
			translator.signed32(size);
			translator.array(size.getValue(), includedFiles, IncludedFile::new, 
					TranslateWrapper.wrap(translator, IncludedFile::build));
		}
	}
}
