package test;

import static org.junit.Assert.assertTrue;
import net.lightbody.bmp.proxy.ProxyServer;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HttpsCrawlerTest
{
    private final ProxyServer proxy   = new ProxyServer(9090);
    final FirefoxProfile      profile = new FirefoxProfile();
    private FirefoxDriver     driver;

    @Test
    public void shouldOpenHttpsPages() throws Exception
    {
        this.proxy.start();

        final Proxy proxy = this.proxy.seleniumProxy();

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        this.driver = new FirefoxDriver(new FirefoxBinary(), this.profile, capabilities);

        this.driver.get("https://www.google.de/");
        assertTrue(this.driver.getTitle().contains("Google"));
    }

    @After
    public void teardown()
    {
        this.proxy.cleanup();
        this.driver.close();
    }
}
