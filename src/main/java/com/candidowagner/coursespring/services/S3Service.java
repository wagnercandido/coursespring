package com.candidowagner.coursespring.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multiPartFile) {
		try {
			String fileName = multiPartFile.getOriginalFilename();
			InputStream inputStream = multiPartFile.getInputStream();
			String contentType = multiPartFile.getContentType();
			return uploadFile(fileName, inputStream, contentType);
		} catch (IOException e) {
			throw new RuntimeException("AmazonClientException" + e.getMessage());
		}

	}

	public URI uploadFile(String fileName, InputStream inputStream, String contentType) {
		try {
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(contentType);
			LOG.info("Upload Iniciado...");
			s3client.putObject(bucketName, fileName, inputStream, metaData);
			LOG.info("Upload Realizado com Sucesso!");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL to URI");
		}
	}

}
