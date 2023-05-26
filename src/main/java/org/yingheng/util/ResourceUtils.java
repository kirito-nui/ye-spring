package org.yingheng.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 20:50
 */
public class ResourceUtils {

	public static final String FILE_URL_PREFIX = "file:";
	public static final String URL_PROTOCOL_FILE = "file";


	public static URI toURI(URL url) throws URISyntaxException {
		return toURI(url.toString());
	}

	public static URI toURI(String location) throws URISyntaxException {
		return new URI(StringUtils.replace(location, " ", "%20"));
	}
}
