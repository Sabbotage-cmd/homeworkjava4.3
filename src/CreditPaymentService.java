import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CreditPaymentService {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.######");

    // Метод для вычисления суммы ежемесячного платежа.
    public static String payPerMonth(int creditSum, int creditTerm, double interestRate) {
        double ratePerMonth = interestRatePerMonth(interestRate);
        double coefficient = annuityCoefficient(ratePerMonth, creditTerm);
        double resultOfFormula = coefficient * creditSum;
        return decimalFormat.format(resultOfFormula);
    }

    // Метод для вычисления годовой процентной ставки в месяц
    public static double interestRatePerMonth(double interestRate) {
        double value = interestRate / (double) 12 / (double) 100;
        return Double.parseDouble(decimalFormat.format(value).replace(",", "."));
    }

    // Метод для вычисления коэффициента аннуитентного платежа
    public static double annuityCoefficient(double ratePerMonth, int creditTerm) {
        double creditTermMonth = creditTerm * 12; // Срок кредита в месяцах
        BigDecimal bracers = BigDecimal.valueOf(Math.pow((1 + ratePerMonth), creditTermMonth));
        BigDecimal numerator = bracers.multiply(BigDecimal.valueOf(ratePerMonth));
        BigDecimal denominator = bracers.subtract(BigDecimal.valueOf(1));
        BigDecimal resultDivide = numerator.divide(denominator, 6, RoundingMode.HALF_UP);
        return resultDivide.doubleValue();
    }
}