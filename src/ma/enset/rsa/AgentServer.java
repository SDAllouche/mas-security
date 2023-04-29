package ma.enset.rsa;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
public class AgentServer extends Agent {

    @Override
    protected void setup() {
        String encodedPK= (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage recive=receive();
                if (recive != null) {
                    String cryptedEncodedMsg=recive.getContent();
                    byte[] encryptedMsg= Base64.getDecoder().decode(cryptedEncodedMsg);
                    try {
                        byte[] decodedPK=Base64.getDecoder().decode(encodedPK);
                        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
                        PrivateKey privateKey=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPK));
                        Cipher cipher=Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
                        byte[] bytes=cipher.doFinal(encryptedMsg);
                        System.out.println(new String(bytes));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    block();
                }
            }
        });
    }
}
