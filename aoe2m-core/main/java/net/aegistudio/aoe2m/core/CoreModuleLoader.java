package net.aegistudio.aoe2m.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.CoreExtension;
import net.aegistudio.aoe2m.Loader;

public class CoreModuleLoader extends Loader {
	public CoreModuleLoader() {	super("extension");	}

	public Set<String> extensionClasses = new TreeSet<>();
	public Map<Class<?>, CoreExtension> extension = new HashMap<>();
	
	public void load(String name, ClassLoader loader, InputStream stream) throws Aoe2mException {
		for(String line : new BufferedReader(new InputStreamReader(stream)).lines().toArray(String[]::new)) try {
			if(line.startsWith("#")) continue;
			if(extensionClasses.contains(line)) continue;
			
			Class<?> extension = loader.loadClass(line);
			if(!CoreExtension.class.isAssignableFrom(extension))
				throw new Exception("coreload.notcoreext");
			
			Object instance = extension.newInstance();
			
			extensionClasses.add(extension.getCanonicalName());
			this.extension.put(extension, (CoreExtension) instance);
		} catch(Exception e) {
			throw new Aoe2mException(e, "coreload.loadclass");
		}
	}
	
	public interface CoreExtensionConsumer { public void accept(CoreExtension extension);	}
	public void perform(CoreExtensionConsumer consumer) throws Aoe2mException {
		for(CoreExtension extension : this.extension.values()) 
			consumer.accept(extension);
	}
	
	@SuppressWarnings("unchecked")
	public <C extends CoreExtension> C require(Class<C> type) throws Aoe2mException {
		if(!extensionClasses.contains(type.getCanonicalName()))
			throw new Aoe2mException("coreload.notexists", type.getCanonicalName());
		return (C) extension.get(type);
	}
}
