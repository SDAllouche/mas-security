package ma.enset.rsa;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.*;

public class SeverContainer {

    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
        String privateKey="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAo17no6SRw2Zcn/YLTrh1dRFOsmiS5Xeu89SFEukpxBwxB5/vCg3zT87TrKVidFEy7W/t7mYvpQsg1nG3hGzu/QIDAQABAkA3Xm1scVSzoGgpEKchbXPlktGUtst+ePMzEWZuk9aDb61cguOkUoUl8dcpiuGuZ3FBk76IYnjqQMg2M0xuREaDAiEA4BSHItEuVXI+kCfKmMqDVgB8kUIgbJ9Dr7KlE6Ys18sCIQC6pIBNIJrE4UHyQmRZN3Unl/1tpgr6XZ/nZ2EfVcGrVwIgGhhJ5HQXwTucrYz0uvZadRl5aDEbNYsWhs93CeLANiECIExgswxc8ljtIXKFDtGfqzansRLk5wsrsxscKBeNnXKDAiEAkWk10blzaqsER9mt7BDprQC2z38BRqQZn6AODGH2/bQ=";
        AgentController server=agentContainer.createNewAgent("server","ma.enset.rsa.AgentServer",new Object[]{privateKey});
        server.start();
    }

}
