import java.util.Scanner;
import java.util.Random;

class User {
    String name;
    String pan;
    String dob;

    User(String name, String pan, String dob) {
        this.name = name;
        this.pan = pan;
        this.dob = dob;
    }
}

public class SmartPasswordGenerator {

    public static String generatePassword(User user) {
        String namePart = user.name.substring(0, 2).toUpperCase();
        String panPart = user.pan.substring(5, 9);
        String dobPart = user.dob.replace("/", "");

        String specialChars = "@#%&!";
        Random random = new Random();

        char special = specialChars.charAt(random.nextInt(specialChars.length()));
        int randomNum = random.nextInt(900) + 100;

        return namePart + panPart + special + dobPart.substring(0, 4) + randomNum;
    }

    public static String checkStrength(String password) {
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;
        }

        int score = 0;
        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (password.length() >= 12 && score == 4) return "Strong";
        else if (password.length() >= 8 && score >= 3) return "Medium";
        else return "Weak";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of users: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nUser " + i);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter PAN Number: ");
            String pan = sc.nextLine();

            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            String dob = sc.nextLine();

            User user = new User(name, pan, dob);

            String password = generatePassword(user);

            System.out.println("Generated Password: " + password);
            System.out.println("Password Strength: " + checkStrength(password));
        }

        sc.close();
    }
}
