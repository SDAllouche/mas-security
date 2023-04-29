package ma.enset.rsa;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

        String encodedPK= (String) getArguments()[0];
        String message="Hello Server";
        try {
            byte[] decodedPK=Base64.getDecoder().decode(encodedPK);
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey=keyFactory.generatePublic(new X509EncodedKeySpec(decodedPK));
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE , publicKey) ;
            byte[] cryptedMsg=cipher.doFinal(message.getBytes());
            String cryptedEncodedMsg= Base64.getEncoder().encodeToString(cryptedMsg);
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID( "server" , AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodedMsg);
            send(aclMessage);
            System.out.println(Arrays.toString(cryptedMsg));
            System.out.println(cryptedEncodedMsg);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException |
                 NoSuchPaddingException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
