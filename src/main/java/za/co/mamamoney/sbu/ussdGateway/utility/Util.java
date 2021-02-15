package za.co.mamamoney.sbu.ussdGateway.utility;

import org.apache.tomcat.util.bcel.Const;
import za.co.mamamoney.sbu.ussdGateway.Constants;
import za.co.mamamoney.sbu.ussdGateway.model.MenuPresented;
import za.co.mamamoney.sbu.ussdGateway.model.Session;
import za.co.mamamoney.sbu.ussdGateway.model.SessionStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
    public static final BigDecimal KES_CONVERSION =  BigDecimal.valueOf(6.1);
    public static final BigDecimal MWK_CONVERSION =  BigDecimal.valueOf(42.5);

    public static final String convertZARtoKES(String rands){
        BigDecimal kwacha = new BigDecimal(rands).multiply(KES_CONVERSION);
        kwacha.setScale(2, RoundingMode.HALF_UP);
        return kwacha.toPlainString();
    }

    public static final String convertZARtoMWK(String rands){
        BigDecimal shilling = new BigDecimal(rands).multiply(MWK_CONVERSION);
        shilling.setScale(2, RoundingMode.HALF_UP);
        return shilling.toPlainString();
    }

    public static final void processCountry(MenuPresented lastMenuPresented, Session session) {
        if(lastMenuPresented.getSelection().equals("1")){
            session.setDestinationCountry(Constants.KENYA);
        } else {
            session.setDestinationCountry(Constants.MALAWI);
        }
    }

    public static final void processRandAmount(MenuPresented lastMenuPresented, Session session) {
        session.setRandAmount(lastMenuPresented.getSelection());
    }

    public static final StringBuilder processConfirmation(MenuPresented lastMenuPresented, Session session) {
        StringBuilder sb = new StringBuilder();
        if(!lastMenuPresented.getSelection().equals("1")){
            sb.append("Transaction Cancelled.\n");
            session.setSessionStatus(SessionStatus.CANCELLED.name());
        } else {
            session.setSessionStatus(SessionStatus.COMPLETE.name());
        }
        return sb;
    }

    public static final String calculateAmount(String destination, String randAmount) {
        String amount = null;
        switch (destination){
            case Constants.KENYA:
                amount= convertZARtoKES(randAmount);
                break;
            case Constants.MALAWI:
                amount= convertZARtoMWK(randAmount);
                break;
        }
        return amount;
    }

    public static final String generateCurrency(String destination) {
        String currency = null;
        switch (destination){
            case Constants.KENYA:
                currency= Constants.KENYAN_SHILLING;
                break;
            case Constants.MALAWI:
                currency= Constants.MALAWIAN_KWACHA;
                break;
        }
        return currency;
    }
}
