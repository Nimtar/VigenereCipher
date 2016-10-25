package ru.vsu.amm.infosecurity;

import java.util.Scanner;

public class Vigenere {

    private static String input;
    private static Scanner in = new Scanner(System.in);
    private static String keyword;

    public static void main (String[] args) {
        System.out.println("First of all type \"E\" if you want to encrypt "
            + "text or type \"D\" if you want do decrypt encrypted text");

        boolean isEncryption = setEncryption();

        initialize();

        String result;
        if (isEncryption) {
            result = encrypt(input, keyword);
        } else {
            result = decrypt(input, keyword);
        }

        System.out.println(
            (isEncryption ? "Encrypted " : "Decrypted ") + " " + "string is\n "
                + result);
    }

    private static boolean setEncryption () {
        String s;
        do {
            s = in.next();
            s = s.toUpperCase();
        } while (!"E".equals(s) && !"D".equals(s));

        return "E".equals(s);
    }

    private static void initialize () {
        in.nextLine();
        do {
            promptForUserText();
            input = in.nextLine().toLowerCase();
        } while (!input.matches("[A-Za-z]+"));

        do {
            promptForKeyword();
            keyword = in.nextLine().toLowerCase();
        } while (!keyword.matches("[A-Za-z]+"));

    }

    private static String encrypt (String message, String keyword) {
        String cipherText = "";
        for (int i = 0; i < message.length(); ++i) {
            cipherText += (char)('a' + (message.charAt(i) - 'a'
                + keyword.charAt(i % keyword.length()) - 'a') % 26);
        }

        return cipherText;
    }

    private static String decrypt (String ciphertext, String keyword) {
        String message = "";
        for (int i = 0; i < ciphertext.length(); ++i) {
            message += (char)('a' + (26 + (ciphertext.charAt(i) - 'a') - (
                keyword.charAt(i % keyword.length()) - 'a')) % 26);
        }

        return message;
    }

    private static void promptForUserText () {
        System.out.println("Please type non-zero length string containing "
            + "english letters only");
    }

    private static void promptForKeyword () {
        System.out.println("Please type a keyword which will be used for "
            + "encryption and for decryption, containing letters only");
    }

}
