public class Main {

    public static void main(String[] args) {
        int creditSum = 1_000_000;  // Сумма кредита
        int creditTerm = 1;  // Срок кредита
        double interestRate = 9.99; // Ставка кредита

        String payPerMonth = CreditPaymentService.payPerMonth(creditSum, creditTerm, interestRate);
        System.out.println(payPerMonth);
    }
}