import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = console.nextLine(); // входное выражение
        System.out.print("Ответ: " + Calc(input));  // ответ
    }

    public static String Calc(String input) throws Exception {
        int number1, number2;
        String result,function;
        boolean roman_numerals;

        String[] searchNumder = input.split("[+\\-*/]"); // поиск введённых чисел и арифметических знаков {"94", "3"}
        if (searchNumder.length !=2) throw new Exception("Должно быть 2 числа, и одно арифметическое действие!"); // (1+2+3+4+5)
        function = searchFunction(input);
        if (function == null) throw new Exception("Введите корректное арифметическое действие");
        // проверка на римские числа
        if ((RomanNumbers.roman_numeral(searchNumder[0])) && (RomanNumbers.roman_numeral(searchNumder[1]))){
            number1 = RomanNumbers.convertor(searchNumder[0]); // конвертируем числа в арабские, для дальнейших вычислений
            number2 = RomanNumbers.convertor(searchNumder[1]);
            roman_numerals = true;
        }
        // если 2 числа арабские
        else if ((!RomanNumbers.roman_numeral(searchNumder[0])) && (!RomanNumbers.roman_numeral(searchNumder[1]))){
            number1 = Integer.parseInt(searchNumder[0]);
            number2 = Integer.parseInt(searchNumder[1]);
            roman_numerals = false;
        }
        // если одно число арабское, а другое римское
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if ((number1 > 10) || number2 > 10){
            throw new Exception("Ошибка! Числа должны быть от 1 до 10");
            //
        }
        int arabNumber = calculator(number1, number2, function);
        if (roman_numerals){
            // при меньше или равным нулю, генерируем исключение с ошибкой
            if (arabNumber <= 0){
                throw new Exception("Римское число должно быть Больше ноля!");
            }
            //конвертируем из арабского числа в римское
            result = RomanNumbers.convertor2(arabNumber);
        } else {
            // конверитруем арабское число в String
            result = String.valueOf(arabNumber);
        }
        //возвращаем ответ
        return result;
    }

    static String searchFunction(String input){
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }
    static int calculator(int num1, int num2, String searchNumder) {
        if (searchNumder.equals("+")) return num1+num2; // вычисляем
        else if (searchNumder.equals("-")) return num1-num2;
        else if (searchNumder.equals("*")) return num1*num2;
        else return num1/num2;
    }
}

class RomanNumbers {
    static String[] romanNumber = new String[]{
            //массив с римскими цифрами, т.к счёт с "0", то индекс римского числа (II) = арабскому числу (2)

            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", 
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", 
            "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", 
            "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", 
            "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static boolean roman_numeral(String num) { // num "94"
        for (int i = 0; i < romanNumber.length; i++) {
            if (num.equals(romanNumber[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertor(String roman_num){ // например: "III"
        for(int i = 0; i < romanNumber.length; i++){
            if (roman_num.equals(romanNumber[i])){
                return i; //т.к индекс эквивалентен значению арабского числа
            }
        }
        return -1;
    }

    public static String convertor2(int arabNumber){
        return romanNumber[arabNumber];
    }
}