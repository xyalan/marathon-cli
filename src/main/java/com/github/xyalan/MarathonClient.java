package com.github.xyalan;

import com.github.xyalan.utils.MarathonException;
import com.github.xyalan.utils.ModelUtils;
import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class MarathonClient {
	private static class MarathonErrorDecoder implements ErrorDecoder {
		private Logger log = LoggerFactory.getLogger(this.getClass());

		@Override
		public Exception decode(String methodKey, Response response)  {
			String body = Optional.ofNullable(response.body()).map(b -> {
				try {
					return b.asReader();
				} catch (IOException e) {
					log.error("failed to read response reader", e);
				}
				return null;
			}).map(b -> {
				try {
					BufferedReader br = new BufferedReader(b);
					String line = null;
					StringBuilder sb = new StringBuilder("");
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					return sb.toString();
				} catch (IOException e) {
					log.error("failed to read response reader", e);
				}
				return null;
			}).orElse("");
			return new MarathonException(response.status(), response.reason() + ", message:" + body);
		}
	}
	
	public static Marathon getInstance(String endpoint) {
		GsonDecoder decoder = new GsonDecoder(ModelUtils.GSON);
		GsonEncoder encoder = new GsonEncoder(ModelUtils.GSON);
		return Feign.builder().encoder(encoder).decoder(decoder)
				.errorDecoder(new MarathonErrorDecoder())
				.target(Marathon.class, endpoint);
	}
}
