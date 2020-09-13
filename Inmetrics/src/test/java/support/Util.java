package support;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {

    Faker faker = new Faker();

    // Generate a random username
    public String generateUsername() {
        return (faker.name().firstName() + faker.name().lastName()).toLowerCase();
    }

    // Generate a random full name
    public String generateFullName() {
        return faker.name().fullName();
    }

    // Get the actual date on Brazilian format
    public String generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    // Generate a random valid CPF
    public String generateCPF() throws Exception {
        int digit1 = 0, digit2 = 0, remainder = 0;
        String  nDigResult;
        String concatenatedNumbers;
        String cpf;

        Random randomNumber = new Random();

        // Generated numbers
        int n1 = randomNumber.nextInt(10);
        int n2 = randomNumber.nextInt(10);
        int n3 = randomNumber.nextInt(10);
        int n4 = randomNumber.nextInt(10);
        int n5 = randomNumber.nextInt(10);
        int n6 = randomNumber.nextInt(10);
        int n7 = randomNumber.nextInt(10);
        int n8 = randomNumber.nextInt(10);
        int n9 = randomNumber.nextInt(10);
        
        int sum = n9*2 + n8*3 + n7*4 + n6*5 + n5*6 + n4*7 + n3*8 + n2*9 + n1*10;

        int value = (sum / 11)*11;

        digit1 = sum-value;

        //First remainder from division by 11.
        remainder = (digit1 % 11);

        if(digit1 < 2){
            digit1 = 0;
        }
        else {
            digit1 = 11-remainder;
        }

        int sum2 = digit1 * 2 + n9*3 + n8*4 + n7*5 + n6*6 + n5*7 + n4*8 + n3*9 + n2*10 + n1*11;

        int value2 = (sum2 / 11)*11;

        digit2 = sum2-value2;

        //Second remainder from division by 11.
        remainder = (digit2 % 11);

        if(digit2 < 2){
            digit2 = 0;
        }
        else {
            digit2 = 11-remainder;
        }

        //Concatenating numbers
        concatenatedNumbers = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) +"." + String.valueOf(n4) +
                String.valueOf(n5) + String.valueOf(n6) +"."+ String.valueOf(n7) +String.valueOf(n8)  +
                String.valueOf(n9)+"-";

        //Concatenating the first remainder with the second
        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        cpf = concatenatedNumbers+nDigResult;

        return cpf;
    }

    public static void printGenerate() {
        Random random = new Random();

        for (int i = 0; i<= 100; i++) {
            //System.out.println(random.ints(1, 4).findFirst().getAsInt());
            System.out.println(random.nextInt(3) + 1);
        }
    }
}
