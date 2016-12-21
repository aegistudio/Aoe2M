package net.aegistudio.aoe2m.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.aegistudio.aoe2m.Action;
import net.aegistudio.aoe2m.Aoe2mException;
import net.aegistudio.aoe2m.CoreExtension;
import net.aegistudio.aoe2m.CoreModel;
import net.aegistudio.aoe2m.Document;
import net.aegistudio.aoe2m.Reaction;
import net.aegistudio.aoe2m.Reaction.Type;
import net.aegistudio.aoe2m.ViewModel;
import net.aegistudio.aoe2m.command.Command;
import net.aegistudio.aoe2m.command.CommandParseur;
import net.aegistudio.aoe2m.impcall.MapObservable;
import net.aegistudio.aoe2m.impcall.MapObserver;
import net.aegistudio.aoe2m.impcall.ValueObservable;
import net.aegistudio.aoe2m.impcall.ValueObserver;

public class CoreApplication implements CoreModel, ViewModel {
	public Map<String, Command> commands = new TreeMap<>();
	public CoreModuleLoader coreModuleLoader = new CoreModuleLoader();
	public PerformReactor performReactor = new PassPerformReactor();
	public Reaction reaction = null;
	
	public final ValueObservable<Document> current = new ValueObservable<>();
	public ValueObserver<Document> current() {	return current;	}
	
	public final MapObservable<String, Document> openDocument = new MapObservable<>(new TreeMap<>());
	public MapObserver<String, Document> all() {	return openDocument;	}
	
	public CommandParseur parseur = null;
	public CommandParseur parseur() {	return parseur;		}
	
	@Override
	public void register(String name, Command command) {
		commands.put(name, command);
	}

	@Override
	public void perform(Action action) {
		try {
			performReactor.perform(action);
		}
		catch(Aoe2mException e) {
			reaction.info(Type.ERROR, e.toString());
		}
	}
	
	@Override
	public <C extends CoreExtension> C require(Class<C> type) throws Aoe2mException {	
		return coreModuleLoader.require(type);
	}
	
	@Override
	public void execute(String command) {
		try {
			String[] arguments = parseur.parse(command);
			if(arguments.length == 0) return;
			
			Command target = commands.get(arguments[0]);
			if(target == null) 
				throw new Aoe2mException("command.notexist\n" + arguments[0]);
			
			String[] parameters = new String[arguments.length - 1];
			System.arraycopy(arguments, 1, parameters, 0, parameters.length);
			target.execute(parameters);
		}
		catch(Aoe2mException e) {
			reaction.info(Type.ERROR, e.toString());
		}
	}

	@Override
	public List<String> complete(String command) {
		try {
			String[] arguments = parseur.parse(command);
			if(arguments.length > 1) {
				// Sub complete command.
				String[] parameters = new String[arguments.length - 1];
				System.arraycopy(arguments, 1, parameters, 0, parameters.length);
				
				Command target = commands.get(arguments[0]);
				if(target == null)
					throw new Aoe2mException("command.notexist\n" + arguments[0]);
				return target.complete(parameters);
			}
			else {
				// Only complete command.
				String name = arguments.length == 0? "" : arguments[0];
				return Arrays.asList(commands.keySet().parallelStream()
					.filter(s -> s.startsWith(name))
					.toArray(String[]::new));
			}
		}
		catch(Aoe2mException e) {
			reaction.info(Type.WARNING, e.toString());
			return new ArrayList<>();
		}
	}
}
