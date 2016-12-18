package net.aegistudio.aoe2m;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Test;

public class TestLoader {
	public @Test void test() {
		try {
			TreeSet<String> files = new TreeSet<>(Arrays.asList("file1", "file2.x", "file3.y"));
			assertEquals(files.size(), 3);
			new Loader("testloader") {
				@Override
				protected void load(String name, ClassLoader loader, InputStream stream)
						throws IOException, Aoe2mException {
					files.remove(name);
				}
			}.load(ClassLoader.getSystemClassLoader());
			assertEquals(files.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
