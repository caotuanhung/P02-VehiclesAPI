package com.udacity.vehicles.service;

import com.udacity.vehicles.client.prices.Price;
import com.udacity.vehicles.client.prices.PriceClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PriceService {
    private final PriceClient priceClient;
    private final String moneyPattern = "^[0-9]+\\.?[0-9]* .+$";

    public PriceService(PriceClient priceClient) {
        this.priceClient = priceClient;
    }

    public Price savePriceStr(String priceStr, Long vehicleId) {
        Price price = formatPrice(priceStr);
        price.setVehicleId(vehicleId);
        return savePrice(price);
    }

    public void deletePrice(Long vehicleId) {
        priceClient.deletePrice(vehicleId);
    }

    private Price savePrice(Price price) {
        return priceClient.savePrice(price);
    }

    /**
     * This method will convert a price string to price object.
     * The price string must have this form ^[0-9]+.?[0-9]* [a-zA-Z]+$
     *
     * @param priceStr
     * @return price
     */
    private Price formatPrice(String priceStr) {
        if (!isValidPrice(priceStr)) {
            throw new InvalidPriceException();
        }
        try {
            String[] priceParts = priceStr.split(" ");
            BigDecimal priceNumber = BigDecimal.valueOf(Double.parseDouble(priceParts[0]));
            String currency = priceParts[1];
            Price price = new Price();
            price.setPrice(priceNumber);
            price.setCurrency(currency);
            return price;
        } catch (Exception e) {
            throw new InvalidPriceException();
        }
    }

    private boolean isValidPrice(String priceStr) {
        Pattern pattern = Pattern.compile(moneyPattern);
        Matcher matcher = pattern.matcher(priceStr);
        return matcher.matches();
    }
}
