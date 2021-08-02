package zavrsni.besednik.com.remotetrainer.model.models;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.InputStream;
import java.security.SecureRandom;

public class VideoHandler {

    static String cs = "BlobEndpoint=https://videos1232.blob.core.windows.net/;SharedAccessSignature=sv=201"+
            "9-12-12&ss=b&srt=sco&sp=rwdlacx&se=2021-02-22T22:12:14Z&st=2021-02-14T14:12:14Z&spr=https,http&sig=9YhUTAWrRsJR%2BY2R66bNDD7hruwmTBPaeYaEujsYe3M%3D";

    private static CloudBlobContainer getContainer() throws Exception {
        CloudStorageAccount storageAccount = CloudStorageAccount
                .parse(cs);
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        CloudBlobContainer container = blobClient.getContainerReference("videos");

        return container;
    }

    public static String UploadVideo(InputStream video, int videoLength) throws Exception {
        CloudBlobContainer container = getContainer();

        container.createIfNotExists();

        String videoName = randomString(20);

        CloudBlockBlob imageBlob = container.getBlockBlobReference(videoName);
        imageBlob.upload(video, videoLength);

        return videoName;

    }

    static final String validChars = "abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( validChars.charAt( rnd.nextInt(validChars.length()) ) );
        return sb.toString();
    }
}
