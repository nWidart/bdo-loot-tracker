package com.bdoloottracker.price.client.omegapepega;

import com.bdoloottracker.price.dto.ItemInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OmegapepegaClient {

  private RestTemplate restTemplate = new RestTemplateBuilder().build();

  public OmegapepegaPrice get(ItemInfo itemInfo) {
    return restTemplate.getForObject(makeUrl(itemInfo), OmegapepegaPrice.class);
  }

  private String makeUrl(ItemInfo itemInfo) {
    String enhanceLevel = itemInfo.getEnhancementLevel();
    if (itemInfo.getEnhancementLevel() == null) {
      enhanceLevel = "0";
    }
    return String.format("https://omegapepega.com/%s/%s/%s", itemInfo.getRegion(), itemInfo.getItemId(), enhanceLevel);
  }
}
