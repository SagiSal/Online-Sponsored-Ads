package com.mabaya.test.sagi.demo.campaign;

import com.mabaya.test.sagi.demo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CampaignController {

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {

        this.campaignService = campaignService;
    }

    @GetMapping("/category/{category}")
    public Product getAdvertise(@PathVariable String category) throws Exception {

        return campaignService.serveAd(category);
    }

    @PostMapping("/campaign")
    public ResponseEntity<Object> addCampaign(@RequestBody Campaign campaign) {

        return campaignService.createCampaign(campaign);
    }

    @GetMapping("/campaign")
    public List<Campaign> getCampaigns(){

        return campaignService.retrieveAll();
    }

}
