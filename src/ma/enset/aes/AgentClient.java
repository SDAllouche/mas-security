package ma.enset.aes;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AgentClient extends Agent {

    @Override
    protected void setup() {

        String secret= (String) getArguments()[0];
        String message="Hello Server";
        try {
            SecretKey secretKey=new SecretKeySpec(secret.getBytes(),"AES");
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE , secretKey) ;
            byte[] cryptedMsg=cipher.doFinal(message.getBytes());
            String cryptedEncodedMsg= Base64.getEncoder().encodeToString(cryptedMsg);
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID( "server" , AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodedMsg);
            send(aclMessage);
            System.out.println(Arrays.toString(cryptedMsg));
            System.out.println(cryptedEncodedMsg);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
