package vn.anzi.modules.management.image.services;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Service
public class FileManageService {
	@Value("${s3.Properties.endpointUrl}")
	private String endpointUrl;
	@Value("${s3.Properties.bucketName}")
	private String bucketName;
	@Value("${s3.Properties.accessKey}")
	private String accessKey;
	@Value("${s3.Properties.secretKey}")
	private String secretKey;

	private AmazonS3 s3Client;

	@PostConstruct
	public void initializeS3() {
		this.s3Client = initS3Client();
	}

	private AmazonS3 initS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(
				accessKey,
				secretKey
		);
		ClientConfiguration config = new ClientConfiguration();
		config.setSignerOverride("S3SignerType");
		AmazonS3 s3Client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(
								endpointUrl,
								null
						)
				)
				.enablePathStyleAccess()
				.withClientConfiguration(config)
				.build();
		return s3Client;
	}

	public void uploadFile(String fileName, byte[] fileData) throws Exception {
		try {
			InputStream uploadStream = new ByteArrayInputStream(fileData);
			ObjectMetadata metadata = new ObjectMetadata();
			this.s3Client.putObject(this.bucketName, fileName, uploadStream, metadata);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("upload fail");
		}
	}

	public byte[] getFileObject(String keyName) throws IOException {
		byte[] content = null;
		S3Object s3Object = s3Client.getObject(bucketName, keyName);
		S3ObjectInputStream is = s3Object.getObjectContent();
		content = IOUtils.toByteArray(is);
		s3Object.close();
		return content;
	}

	public String deleteFileOnS3(String keyName) {
		s3Client.deleteObject(bucketName, keyName);
		return keyName;
	}

	/*
	public FileBase64DTO convertBase64WordToPDF(FileBase64DTO wordBase64) throws IOException {
		byte[] fileData = Base64.getDecoder().decode(wordBase64.getData());
		InputStream inFile = new ByteArrayInputStream(fileData);
		XWPFDocument doc = new XWPFDocument(inFile);
		PdfOptions pdfOptions = PdfOptions.create();
		OutputStream binaryPDF = new ByteArrayOutputStream();
		PdfConverter.getInstance().convert(doc, binaryPDF, pdfOptions);

		try (ByteArrayOutputStream outData = (ByteArrayOutputStream) binaryPDF) {
			String base64PDF = Base64.getEncoder().encodeToString(outData.toByteArray());
			return new FileBase64DTO(base64PDF);
		}
	}*/
}
