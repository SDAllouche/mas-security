package ma.enset.rsa;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ClientContainer {

    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        String publicKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKNe56OkkcNmXJ/2C064dXURTrJokuV3rvPUhRLpKcQcMQef7woN80/O06ylYnRRMu1v7e5mL6ULINZxt4Rs7v0CAwEAAQ==";
        AgentController client=agentContainer.createNewAgent("client","ma.enset.rsa.AgentClient",new Object[]{publicKey});
        client.start();
    }

}
