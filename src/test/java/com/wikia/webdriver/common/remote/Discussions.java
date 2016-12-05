package com.wikia.webdriver.common.remote;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;

public final class Discussions {

  private static final String DISCUSSIONS_SERVICE = "discussion/";

  public static final String ACCESS_TOKEN_HEADER = "X-Wikia-AccessToken";

  private Discussions() {
    throw new AssertionError();
  }

  public static String extractSiteIdFromMediaWikiUsing(final WebDriver driver) {
    String text = driver.findElement(By.cssSelector(".wikitable tr:nth-child(7) td:nth-child(2)")).getText();
    // Found text: "city_id: 1362702, cluster: c7, dc: sjc"
    return StringUtils.substringBetween(text, ": ", ",");
  }

  public static String service(String url) {
    return buildServicesUrl() + DISCUSSIONS_SERVICE + url;
  }

  private static String buildServicesUrl() {
    File configurationFile = new File(Configuration.getCredentialsFilePath());
    final String environment = Configuration.getEnvType().getKey();
    final String url = XMLReader.getValue(configurationFile, "services." + environment);
    Objects.requireNonNull(url,
        "Please check if your configuration file contains url for service " + environment);
    return url;
  }
}
