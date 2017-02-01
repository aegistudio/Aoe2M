package net.aegistudio.aoe2m.scx;

import java.io.IOException;

import net.aegistudio.aoe2m.Container;
import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.FieldTranslator;
import net.aegistudio.aoe2m.Text;
import net.aegistudio.aoe2m.VariantList;
import net.aegistudio.aoe2m.Wrapper;

public class IncludeSection {
	public static class IncludedFile {
		public final Wrapper<Text> name = new Container<>(new Text());
		public final Wrapper<Text> content = new Container<>(new Text());
		
		public void buildIncludedFile(FieldTranslator translator) throws IOException {
			translator.string32(name);
			translator.string32(content);
		}
		
		public static void build(IncludedFile file, FieldTranslator translator) throws IOException {
			file.buildIncludedFile(translator);
		}
	}
	
	public final VariantList<IncludedFile> includedFiles 
		= new VariantList<>(IncludedFile::new, IncludedFile::build);
	
	public void build(FieldTranslator translator) throws IOException, CorruptionException {
		Wrapper<Boolean> fileIncluded = new Container<>(includedFiles.size() > 0);
		translator.bool32(fileIncluded);
		translator.constUnsigned32(0);
		
		if(fileIncluded.getValue()) includedFiles.build(translator);
	}
}
