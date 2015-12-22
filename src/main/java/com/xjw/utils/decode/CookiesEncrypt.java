package com.xjw.utils.decode;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * cookie解析
 * @author zhq
 *
 */
public class CookiesEncrypt {
	private static final String key = "FD!@7*$)";
	/**
	 * 
	 * @param message
	 *            穿入要解密的cookie的值
	 * @param key
	 *            解密的秘钥
	 * @return 解密后的cookie的值
	 * @throws Exception
	 */
	public static String decrypt(String message) throws Exception {
		String[] split = message.split("-");
		byte[] data = new byte[split.length];
		for (int i = 0; i < split.length; i++) {
			int valueOf = Integer.valueOf(split[i], 16);
			data[i] = (byte) valueOf;
			if (data[i] < 0) {
				data[i] += 256;
			}
		}
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(data);
		String decode = URLDecoder.decode(new String(retByte), "utf-8");
		return decode;
	}

	/**
	 * 把cookie的值直接解析成map
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getCookieJson(String cookieValue) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = null;
			if (StringUtils.isNotBlank(cookieValue)){
				map = mapper.readValue(cookieValue, Map.class);
			}

		return map;
	}
}
