package net.aegistudio.aoe2m;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import net.aegistudio.aoe2m.l10n.Localization;

/**
 * Read reaction from the console.
 * 
 * @author luohaoran
 */

public class ConsoleReaction implements Reaction {

	@Override
	public void info(Type type, String text) {
		PrintStream output = System.out;
		if(type.equals(Type.ERROR))
			output = System.err;
		
		output.println(text);
	}
	
	private String readLine() {
		try {
			return new BufferedReader(new InputStreamReader(System.in))
				.readLine();
		}
		catch(Exception e) {
			return "";
		}
	}

	@Override
	public boolean yesNo(Type type, String text) {
		info(type, text);
		while(true) {
			System.out.println(Localization.localize("console.yesno"));
			switch(readLine()) {
				case "Yes": case "yes":
				case "Y": case "y":
					return true;
				
				case "No": case "no":
				case "N": case "n":
					return false;
					
				default:
					return false;
			}
		}
	}

	@Override
	public Boolean yesNoCancel(Type type, String text) {
		info(type, text);
		System.out.println(Localization.localize("console.yesnocancel"));
		switch(readLine()) {
			case "Yes": case "yes":
			case "Y": case "y":
				return true;
				
			case "No": case "no":
			case "N": case "n":
				return false;
				
			default:
				return null;
		}
	}

	@Override
	public Boolean retryAbortIgnore(Type type, String text) {
		info(type, text);
		System.out.println(Localization.localize("console.retryabortignore"));
		switch(readLine()) {
			case "Retry": case "retry":
			case "R": case "r":
				return true;
				
			case "Abort": case "abort":
			case "A": case "a":
				return false;
				
			default:
				return null;
		}
	}
}
