package service;

import dao.CurrencyDAO;

public class CurrencyService {

    private CurrencyDAO currencyDAO = new CurrencyDAO();

    public double convert(String from, String to, double amount) {

        double fromRate = currencyDAO.getRate(from);
        double toRate = currencyDAO.getRate(to);

        if (fromRate == -1 || toRate == -1) {
            return -1;
        }

        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }
}
