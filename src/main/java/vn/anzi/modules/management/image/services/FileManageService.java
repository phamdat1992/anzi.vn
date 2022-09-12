package vn.anzi.modules.management.image.services;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public void uploadFile(String fileName, String base64) throws Exception {
		try {
			String data = base64.replaceFirst("^data:image/[^;]*;base64,?","");
			InputStream uploadStream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(extractMimeType(base64));
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

	public byte[] pngBytesToJpgBytes(byte[] pngBytes) throws IOException {
		ByteArrayInputStream baIns = new ByteArrayInputStream(pngBytes);
		BufferedImage bufferedImage = ImageIO.read(baIns);
		ByteArrayOutputStream baOuts = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "JPG", baOuts);
		return baOuts.toByteArray();
	}

	public static String extractMimeType(final String encoded) {
		final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
		final Matcher matcher = mime.matcher(encoded);

		if (!matcher.find()) {
			return "";
		}

		return matcher.group(1).toLowerCase();
	}
}
