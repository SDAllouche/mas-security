package ma.enset;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class CryptographyUtils {

    public static KeyPair generateRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keypairGenerator= KeyPairGenerator.getInstance("RSA");
        keypairGenerator.initialize( 512);
        KeyPair keypair = keypairGenerator.generateKeyPair() ;
        return keypair;
    }
}
