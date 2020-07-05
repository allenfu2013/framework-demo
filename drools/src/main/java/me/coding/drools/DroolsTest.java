package me.coding.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class DroolsTest {

    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        Applicant app = new Applicant();
        app.setName("zhoubapi");
        app.setAge(19);
        System.out.println("执行规则前："+app.toString());
        kieSession.execute(app);

        System.out.println("执行规则后："+app.toString());
    }
}
