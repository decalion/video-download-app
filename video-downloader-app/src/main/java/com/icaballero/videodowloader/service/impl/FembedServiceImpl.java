package com.icaballero.videodowloader.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import com.icaballero.videodowloader.domain.models.fembed.FembedResponse;
import com.icaballero.videodowloader.exception.DownloadVideoException;
import com.icaballero.videodowloader.service.DownloadService;
import com.icaballero.videodowloader.util.Constants;

/**
 * Fembed Service Implements
 * 
 * @author Ismael Caballero Hernandez
 *
 */
@Service
public class FembedServiceImpl implements DownloadService {

	private Logger log = LoggerFactory.getLogger(FembedServiceImpl.class);

	@Autowired
	private RestTemplate rest;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void getVideo(String url, String rute) throws DownloadVideoException {

		log.info("[FembedServiceImpl.getVideo()] - Start Method");

		log.info("Start Download From : " + url);

		String name;
		String title;

		try {

			name = url.replace("https://www.fembed.com/v/", "");
			log.debug(name);

			String htmlResponse = callHTMLFembed(url);

			title = getTitleFromHtml(htmlResponse);

			FembedResponse fembedJson = callFembedApi(Constants.FEMBED_API_URL + name);

			saveVideo(fembedJson.getData().get(0).getFile(), fembedJson.getData().get(0).getType(), title, rute);

			log.debug(fembedJson.getData().get(0).getFile());

			log.info("[FembedServiceImpl.getVideo()] - END Method");

		} catch (Exception e) {

			throw new DownloadVideoException("Error download Video from Fembed. { " + e.getMessage() + " }");

		}

	}

	/**
	 * Saved video on Desktop
	 * 
	 * @param video
	 * @param type
	 * @param title
	 * @param rute
	 */
	private void saveVideo(String videourl, String type, String title, String rute) {

		log.info("[FembedServiceImpl.saveVideo()] - Start Method");

		log.debug(videourl);
		try {

			File directorio = new File(rute);

			if (directorio.exists() && directorio.isDirectory()) {

				log.info("[FembedServiceImpl.saveVideo()] - Directory exist : + " + rute);

				// Obtener el video
				File video = getVideo(videourl);

				log.debug("[FembedServiceImpl.saveVideo()] - Video : + " + video.getName());

				File output = new File(rute + "\\" + title + "." + type);

				log.info("[FembedServiceImpl.saveVideo()] - OutputName : + " + output.getName());
				// Se abre el fichero original para lectura
				FileInputStream fileInput = new FileInputStream(video);
				BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

				// Se abre el fichero donde se hará la copia
				FileOutputStream fileOutput = new FileOutputStream(output);
				BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

				// Bucle para leer de un fichero y escribir en el otro.
				byte[] array = new byte[1024];
				int leidos = bufferedInput.read(array);
				while (leidos > 0) {
					bufferedOutput.write(array, 0, leidos);
					leidos = bufferedInput.read(array);
				}

				// Cierre de los ficheros
				bufferedInput.close();
				bufferedOutput.close();

			}

			log.info("[FembedServiceImpl.saveVideo()] - End Method");
		}

		catch (Exception e) {

			log.error("[FembedServiceImpl.saveVideo()]  - Error to Save Video on Desktop.");
			throw new DownloadVideoException("Error to Save Video on Desktop. { " + e.getMessage() + " }");
		}

	}

	/**
	 * Get video
	 * 
	 * @param video
	 * @return
	 */
	private File getVideo(String videourl) {

		log.info("[FembedServiceImpl.getVideo()] - Start Method");

		log.debug(videourl);

		try {

			File file = rest.execute(videourl, HttpMethod.GET, null, clientHttpResponse -> {
				File ret = File.createTempFile("download", "tmp");
				StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
				return ret;
			});

			return file;

		} catch (Exception e) {
			log.error("[FembedServiceImpl.getVideo()]");
			throw new DownloadVideoException("Error to Call " + videourl + " {  " + e.getMessage() + " }");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getServiceName() {

		return Constants.FEMBED;
	}

	/**
	 * Get Title from HTML
	 * 
	 * @param html
	 * @return
	 */
	private String getTitleFromHtml(String html) {
		log.debug("[FembedServiceImpl.getTitleFromHtml()] - Start Method");
		String title;

		int firtsTtitle = html.indexOf("<title>") + 7;
		int EndTtitle = html.indexOf("</title>");

		title = html.substring(firtsTtitle, EndTtitle);

		if (title.contains(".")) {
			title = title.substring(0, title.lastIndexOf("."));

		}

		log.debug(title);

		log.debug("[FembedServiceImpl.getTitleFromHtml()] - End Method");

		return title;
	}

	/**
	 * Call Fembed url to get Html content
	 * 
	 * @param url
	 * @return
	 */
	private String callHTMLFembed(String url) {
		log.info("[FembedServiceImpl.callHTMLFembed()] - Start Method");

		log.debug(url);

		try {
//			HttpHeaders requestHeaders = new HttpHeaders();
//			MediaType mediaType = new MediaType("text", "html", utf8);
//			requestHeaders.setContentType(mediaType);
//			requestHeaders.add(HttpHeaders.CONTENT_TYPE, "text/html;charset=UTF-8");
//
//			HttpEntity<String> entity = new HttpEntity<String>("parameters", requestHeaders);

			// new ParameterizedTypeReference<String>() {}
			ResponseEntity<String> htmlResponse = rest.getForEntity(url, String.class);
			Charset utf8 = Charset.forName("UTF-8");

			if (htmlResponse.getStatusCode() == HttpStatus.OK) {

				byte[] bytes = htmlResponse.getBody().getBytes(utf8);
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				InputStreamReader isr = new InputStreamReader(bais);
				String bodyresponse = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));

				log.info("[FembedServiceImpl.callHTMLFembed()] - End Method");
				return bodyresponse;

			} else {

				log.error("[FembedServiceImpl.callHTMLFembed()] Status code : " + htmlResponse.getStatusCodeValue());
				throw new DownloadVideoException(
						"Error to Call " + url + " Status code : " + htmlResponse.getStatusCodeValue());
			}

		} catch (Exception e) {
			log.error("[FembedServiceImpl.callHTMLFembed()]");
			throw new DownloadVideoException("Error to Call " + url + " { " + e.getMessage() + " }");
		}

	}

	/**
	 * Call Fembed Api to get Video
	 * 
	 * @param url
	 * @return
	 */
	private FembedResponse callFembedApi(String url) {
		log.info("[FembedServiceImpl.callFembedApi()] - Start Method");

		log.debug(url);

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(headers);

			ResponseEntity<FembedResponse> response = rest.exchange(url, HttpMethod.POST, entity, FembedResponse.class);

			if (response.getStatusCode() == HttpStatus.OK) {

				log.info("[FembedServiceImpl.callFembedApi()] - End Method");

				return response.getBody();

			} else {

				log.error("[FembedServiceImpl.callFembedApi()] Status code : " + response.getStatusCodeValue());
				throw new DownloadVideoException(
						"Error to Call " + url + " Status code : " + response.getStatusCodeValue());
			}

		} catch (Exception e) {
			log.error("[FembedServiceImpl.callFembedApi()]");
			throw new DownloadVideoException("Error to Call " + url + " {  " + e.getMessage() + " }");
		}

	}

}
