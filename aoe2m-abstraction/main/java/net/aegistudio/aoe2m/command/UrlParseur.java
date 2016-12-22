package net.aegistudio.aoe2m.command;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import net.aegistudio.aoe2m.Aoe2mException;

public class UrlParseur implements CommandParseur {
	public final String encoding;
	public UrlParseur(String encoding) {
		this.encoding = encoding;
	}
	
	@Override
	public String encode(String parameter) throws Aoe2mException {
		try {
			return URLEncoder.encode(parameter, encoding);
		}
		catch (UnsupportedEncodingException e) {
			throw new Aoe2mException(e, "urlparseur.unsupportedenc");
		}
	}

	@Override
	public String[] parse(String command) throws Aoe2mException {
		try {
			String[] argumentList = command.split(" ");
			for(int i = 1; i < argumentList.length; i ++)
				argumentList[i] = URLDecoder.decode(argumentList[i], encoding);
			return argumentList;
		}
		catch(UnsupportedEncodingException e) {
			throw new Aoe2mException(e, "urlparseur.unsupportedenc");
		}
	}
}
