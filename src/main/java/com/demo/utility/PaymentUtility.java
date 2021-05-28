package com.demo.utility;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtility {
    
    private static Map<String,Double> saldo = new HashMap<>();

    static{
        saldo.put("hendro.steven@gmail.com", 1000.0);
    }

    public static void validatePayment(String email, double amount){
        if(saldo.get(email)<amount){
            throw new RuntimeException("Saldo tidak cukup");
        }
    }

}
