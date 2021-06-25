package com.mabaya.test.sagi.demo.campaign;


import com.mabaya.test.sagi.demo.product.Product;
import com.mabaya.test.sagi.demo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignRepository campaignRepository;


    public ResponseEntity<Object> createCampaign(Campaign campaign) {

        //campaign.setStartDate(new Date());
        campaign.setProducts(findProductsByCategory(campaign.getCategory()));
        Campaign savedCampaign = campaignRepository.save(campaign);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(savedCampaign.getName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    private List<Product> findProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();

        Iterator<Product> iterator = productRepository.findAll().iterator();
        while (iterator.hasNext()){

            Product product =  iterator.next();
            if (product.getCategory().equals(category)){
                products.add(product);
            }
        }

        return products;
    }

    public List<Campaign> retrieveAll(){

        return campaignRepository.findAll();
    }

    public Product serveAd(String category) throws Exception {

        Campaign campaign = findHighestBid(category);

        if (campaign.getProducts().isEmpty()) {
            throw new Exception("Campaigns has no products.");
        }

        int random = (int) (Math.random()*
                campaign.getProducts().size());

        Product product = campaign.getProducts().get(random);

        return product;

    }
    private Campaign findHighestBid(String category) throws Exception {
        //Method get a category and find the campaign with the highest bid in the category
        //if not existed in category, returns highest bid or null

        List<Campaign> campaigns = campaignRepository.findAll();
        Iterator<Campaign> iterator = campaigns.iterator();

        Campaign result = null;

        while (iterator.hasNext()) { //try find max in same category

            Campaign campaign = iterator.next();

            if (campaign.isActive() &&
                    campaign.getCategory().equalsIgnoreCase(category)) {

                if (result == null || campaign.getBid() > result.getBid()) {
                    result = campaign;
                }
            }
        }
        if (result == null) { // try other max category

            iterator = campaigns.iterator();

            while (iterator.hasNext()) {

                Campaign campaign = iterator.next();

                if (result == null ||
                        (campaign.isActive() && campaign.getBid() > result.getBid())) {

                    result = campaign;
                }
            }
            if (result == null) {
                throw new Exception("No Campaigns available");
            }
        }

        return result;
    }
}
