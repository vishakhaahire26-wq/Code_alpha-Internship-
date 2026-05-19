// ============================================================
//  TASK 3 — AI Chatbot
//  CodeAlpha Java Internship
// ============================================================

import java.util.Scanner;

public class AIChatbot {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("     AI CHATBOT - CodeAlpha Internship      ");
        System.out.println("============================================");
        System.out.println("  Hello! I am your Java AI Assistant.");
        System.out.println("  Ask me anything about Java, OOP, or");
        System.out.println("  the CodeAlpha internship!");
        System.out.println("  Type 'bye' to exit.");
        System.out.println("============================================");
        System.out.println();
        System.out.println("Suggested questions:");
        System.out.println("  > What is Java?");
        System.out.println("  > What is OOP?");
        System.out.println("  > What is inheritance?");
        System.out.println("  > Tell me about CodeAlpha");
        System.out.println("  > What are the internship perks?");
        System.out.println();

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Bot: Please type something!");
                continue;
            }

            String response = getResponse(input);

            System.out.println("Bot: " + response);
            System.out.println();

            // Exit condition
            if (input.toLowerCase().matches(".*(bye|goodbye|exit|quit).*")) {
                break;
            }
        }
    }

    // ── RESPONSE ENGINE ───────────────────────────────────────
    static String getResponse(String input) {
        // Convert to lowercase for easy matching
        String msg = input.toLowerCase().trim();

        // ── Greetings ─────────────────────────────────────────
        if (msg.matches(".*(hello|hi|hey|hii|howdy).*")) {
            return "Hello! Great to meet you!\n     How can I help you today?\n     Type 'help' to see what I can answer.";
        }

        if (msg.matches(".*(how are you|how r u|how do you do).*")) {
            return "I am doing great, thank you!\n     Ready to answer your Java questions!";
        }

        if (msg.matches(".*(good morning|good afternoon|good evening|good night).*")) {
            return "Good day to you!\n     Hope you are having a great coding session!";
        }

        // ── Help ──────────────────────────────────────────────
        if (msg.matches(".*(help|what can you|topics|commands).*")) {
            return "I can answer questions about:\n" +
                   "     > Java basics (class, object, array, loop)\n" +
                   "     > OOP (inheritance, polymorphism, etc.)\n" +
                   "     > Exception handling\n" +
                   "     > String methods\n" +
                   "     > CodeAlpha internship details\n" +
                   "     > The 3 project tasks";
        }

        // ── CodeAlpha ─────────────────────────────────────────
        if (msg.matches(".*(codealpha|code alpha|internship|intern program).*")) {
            return "CodeAlpha is a leading software company!\n" +
                   "     They offer Java internship programs where you learn:\n" +
                   "     > Java fundamentals & OOP\n" +
                   "     > Data structures\n" +
                   "     > Spring & Hibernate frameworks\n" +
                   "     > Real-world project development\n" +
                   "     > Expert mentorship and guidance!";
        }

        if (msg.matches(".*(certificate|offer letter|perk|benefit|reward).*")) {
            return "CodeAlpha Internship Perks:\n" +
                   "     > Offer Letter\n" +
                   "     > QR-Verified Completion Certificate\n" +
                   "     > Unique ID Certificate\n" +
                   "     > Letter of Recommendation\n" +
                   "     > Job Placement Support\n" +
                   "     > Resume Building Support";
        }

        // ── Java Basics ───────────────────────────────────────
        if (msg.matches(".*(what is java|about java|java language|explain java).*")) {
            return "Java is a popular programming language!\n" +
                   "     > Created in 1995 by Sun Microsystems\n" +
                   "     > High-level and Object-Oriented\n" +
                   "     > Platform Independent: Write Once, Run Anywhere\n" +
                   "     > Used for Android, web apps, enterprise software";
        }

        if (msg.matches(".*(what is oop|about oop|oop concept|object oriented).*")) {
            return "OOP = Object Oriented Programming\n" +
                   "     It has 4 main pillars:\n" +
                   "     1. Encapsulation  - hiding data inside a class\n" +
                   "     2. Inheritance    - child class gets parent's code\n" +
                   "     3. Polymorphism   - one method, many forms\n" +
                   "     4. Abstraction    - show only what is needed";
        }

        if (msg.matches(".*(inheritance|extends|parent|child class|superclass).*")) {
            return "Inheritance = child class inherits from parent class.\n\n" +
                   "     Example:\n" +
                   "     class Animal {              // parent\n" +
                   "         void sound() { }        // method\n" +
                   "     }\n" +
                   "     class Dog extends Animal {  // child\n" +
                   "         void fetch() { }        // extra method\n" +
                   "     }\n" +
                   "     Dog gets BOTH sound() and fetch()!";
        }

        if (msg.matches(".*(encapsulation|private|getter|setter|data hiding).*")) {
            return "Encapsulation = wrapping data inside a class.\n\n" +
                   "     Example:\n" +
                   "     class Student {\n" +
                   "         private String name;  // hidden from outside\n\n" +
                   "         public String getName() {  // getter\n" +
                   "             return name;\n" +
                   "         }\n" +
                   "     }";
        }

        if (msg.matches(".*(polymorphism|overloading|overriding|override|overload).*")) {
            return "Polymorphism = one thing, many forms.\n\n" +
                   "     Method Overloading (same name, different params):\n" +
                   "     void print(int x)    { ... }\n" +
                   "     void print(String s) { ... }\n\n" +
                   "     Method Overriding (child changes parent method):\n" +
                   "     @Override\n" +
                   "     void sound() { System.out.println(\"Woof!\"); }";
        }

        if (msg.matches(".*(abstraction|abstract class|interface).*")) {
            return "Abstraction = hiding complex details, showing essentials.\n\n" +
                   "     Interface (all abstract methods):\n" +
                   "     interface Shape {\n" +
                   "         double area();  // no body\n" +
                   "     }\n\n" +
                   "     Abstract Class (mix of abstract + normal):\n" +
                   "     abstract class Vehicle {\n" +
                   "         abstract void start();\n" +
                   "         void stop() { System.out.println(\"Stopped\"); }\n" +
                   "     }";
        }

        if (msg.matches(".*(class|object|instance|new keyword).*")) {
            return "Class = a blueprint or template.\n" +
                   "     Object = a real instance of a class.\n\n" +
                   "     Example:\n" +
                   "     class Car {             // this is a class\n" +
                   "         String color;\n" +
                   "         void drive() { }\n" +
                   "     }\n\n" +
                   "     Car myCar = new Car();  // this is an object";
        }

        if (msg.matches(".*(array|arraylist|list|collection).*")) {
            return "Array = fixed size storage.\n" +
                   "     int[] marks = new int[5];  // stores 5 integers\n\n" +
                   "     ArrayList = dynamic size (grows automatically):\n" +
                   "     ArrayList<String> names = new ArrayList<>();\n" +
                   "     names.add(\"Rahul\");\n" +
                   "     names.get(0);  // returns Rahul\n" +
                   "     names.size();  // returns 1";
        }

        if (msg.matches(".*(loop|for loop|while loop|for each|iteration).*")) {
            return "Java has 3 main loops:\n\n" +
                   "     1. for loop:\n" +
                   "        for (int i = 0; i < 5; i++) {\n" +
                   "            System.out.println(i);\n" +
                   "        }\n\n" +
                   "     2. while loop:\n" +
                   "        while (condition) { ... }\n\n" +
                   "     3. for-each loop:\n" +
                   "        for (String s : list) { ... }";
        }

        if (msg.matches(".*(exception|error|try|catch|finally|handle).*")) {
            return "Exception handling prevents your program from crashing!\n\n" +
                   "     try {\n" +
                   "         int x = 10 / 0;  // risky code\n" +
                   "     } catch (ArithmeticException e) {\n" +
                   "         System.out.println(\"Cannot divide by zero!\");\n" +
                   "     } finally {\n" +
                   "         System.out.println(\"This always runs\");\n" +
                   "     }";
        }

        if (msg.matches(".*(string|string method|text method).*")) {
            return "Common String methods in Java:\n" +
                   "     str.length()          - number of characters\n" +
                   "     str.toUpperCase()     - convert to CAPS\n" +
                   "     str.toLowerCase()     - convert to small\n" +
                   "     str.contains(\"abc\")  - check if text exists\n" +
                   "     str.split(\",\")        - split into array\n" +
                   "     str.trim()            - remove extra spaces\n" +
                   "     str.equals(\"other\")  - compare two strings";
        }

        if (msg.matches(".*(constructor|initialize object|new object).*")) {
            return "Constructor = special method to create an object.\n\n" +
                   "     class Student {\n" +
                   "         String name;\n" +
                   "         int age;\n\n" +
                   "         // This is a constructor:\n" +
                   "         Student(String n, int a) {\n" +
                   "             this.name = n;\n" +
                   "             this.age  = a;\n" +
                   "         }\n" +
                   "     }\n\n" +
                   "     Usage: Student s = new Student(\"Rahul\", 20);";
        }

        if (msg.matches(".*(scanner|user input|take input|read input).*")) {
            return "Use Scanner to take input from the user:\n\n" +
                   "     import java.util.Scanner;\n\n" +
                   "     Scanner sc = new Scanner(System.in);\n\n" +
                   "     System.out.print(\"Enter name: \");\n" +
                   "     String name = sc.nextLine();\n\n" +
                   "     System.out.print(\"Enter age: \");\n" +
                   "     int age = sc.nextInt();";
        }

        // ── Tasks ─────────────────────────────────────────────
        if (msg.matches(".*(task 1|grade tracker|student grade).*")) {
            return "Task 1 = Student Grade Tracker!\n" +
                   "     File: StudentGradeTracker.java\n" +
                   "     Features:\n" +
                   "     > Add students with grades\n" +
                   "     > Calculate average, highest, lowest\n" +
                   "     > Letter grades (A to F)\n" +
                   "     > Search students\n" +
                   "     > View class summary report";
        }

        if (msg.matches(".*(task 3|chatbot|ai|this task|about this).*")) {
            return "You are using Task 3 = AI Chatbot!\n" +
                   "     File: AIChatbot.java\n" +
                   "     I use keyword matching to reply to your questions.\n" +
                   "     I know about: Java, OOP, CodeAlpha, and all 3 tasks!";
        }

        if (msg.matches(".*(task 4|hotel|reservation|room booking).*")) {
            return "Task 4 = Hotel Reservation System!\n" +
                   "     File: HotelReservation.java\n" +
                   "     Features:\n" +
                   "     > View available rooms (Standard, Deluxe, Suite)\n" +
                   "     > Book a room\n" +
                   "     > Cancel a booking\n" +
                   "     > View all bookings";
        }

        // ── Farewell ──────────────────────────────────────────
        if (msg.matches(".*(thank|thanks|thank you|thx).*")) {
            return "You are welcome! Happy to help.\n" +
                   "     Best of luck with your CodeAlpha internship!";
        }

        if (msg.matches(".*(bye|goodbye|exit|quit).*")) {
            return "Goodbye! Keep coding and stay awesome!\n" +
                   "     Best of luck with your internship submission!";
        }

        // ── Default fallback ──────────────────────────────────
        return "I am not sure about that.\n" +
               "     Try asking me about:\n" +
               "     > Java basics  (class, object, array, loop)\n" +
               "     > OOP concepts (inheritance, polymorphism)\n" +
               "     > CodeAlpha internship details\n" +
               "     > The 3 project tasks\n" +
               "     Or type 'help' to see all topics!";
    }
}