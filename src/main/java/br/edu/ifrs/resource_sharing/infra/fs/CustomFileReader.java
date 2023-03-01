package br.edu.ifrs.resource_sharing.infra.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFileReader {
	private static final ClassLoader CL =
			Thread.currentThread().getContextClassLoader();

	public static List<String> readFile(String file, String separator) {
		try (InputStream stream = CL.getResourceAsStream(file)) {
			assert stream != null;
			InputStreamReader streamReader = new InputStreamReader(
					stream, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			String[] result = reader.lines().collect(Collectors.joining(" "))
					.split(separator);
			streamReader.close();
			reader.close();
			return new ArrayList<>(Arrays.asList(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
}
