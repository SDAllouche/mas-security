package ma.enset.aes;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.NoSuchAlgorithmException;

public class SeverContainer {

    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        String secret="1234567890123456";
        AgentController server=agentContainer.createNewAgent("server","ma.enset.aes.AgentServer",new Object[]{secret});
        server.start();
    }

}
