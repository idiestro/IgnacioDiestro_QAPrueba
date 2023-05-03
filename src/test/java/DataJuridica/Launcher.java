package DataJuridica;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

import DataJuridica.utils.Utils;

public class Launcher {

    public static void  main(String[] args){
        try{
            ArrayList<String> testCases = Utils.getTestCases();
            LauncherDiscoveryRequest discoveryRequest = LauncherDiscoveryRequestBuilder.request()
                    .selectors(testCases.stream().map(DiscoverySelectors::selectClass).collect(Collectors.toList()))
                    .build();

            org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();
            launcher.execute(discoveryRequest);

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
